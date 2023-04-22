#include "NMSerializable.h"
#include <Windows.h>

//	***************
//	CNMSimpleStream
//	***************

enum
{
	kStartMarker		= 0x1fca34,
	kEndMarker			= 0x8a119e,

	kStartMarkerSize	= 4,
	kBufLenSize			= 4,
	kEndMarkerSize		= 4,

	kExtraSize			= kStartMarkerSize + kBufLenSize + kEndMarkerSize,
};

#ifdef	_NMSERVER
#	define	_NMHeapAlloc( size )	CNMHeapManager().GetInstance().Alloc( size )
#	define	_NMHeapFree( ptr )		CNMHeapManager().GetInstance().Free( ptr )
#else
#	define	_NMHeapAlloc( size )	::HeapAlloc( ::GetProcessHeap(), NULL, size )
#	define	_NMHeapFree( ptr )		::HeapFree( ::GetProcessHeap(), NULL, ptr )
#endif


CNMSimpleStream::CNMSimpleStream( void )
{
	this->m_pBuf		= NULL;
	this->m_bReference	= FALSE;

	this->ResetBuffer();
	this->RequestBuffer( 1024 );
}

CNMSimpleStream::~CNMSimpleStream( void )
{
	this->ResetBuffer();
}

BOOL CNMSimpleStream::IsValid( void ) const
{
	return this->m_bIsValid;
}

BOOL CNMSimpleStream::SetBuffer( BYTE* pBuf, BOOL bReference )
{
	if ( pBuf == NULL )
		return FALSE;

	this->ResetBuffer();

	// Check Start Marker
	if ( *( (_UInt32_*) pBuf ) == kStartMarker )
	{
		this->m_uBufLen = *((_UInt32_*) ( pBuf + kStartMarkerSize ));

		// Check End Marker
		if ( *( (_UInt32_*) ( pBuf + kStartMarkerSize + kBufLenSize + this->m_uBufLen ) ) != kEndMarker )
			goto fail;

		this->m_bReference = bReference;

		if ( bReference )
		{
			this->m_pBuf = pBuf;
		}
		else
		{
			this->m_pBuf = (BYTE*) _NMHeapAlloc( this->m_uBufLen + kExtraSize );
			if ( this->m_pBuf == NULL )
				goto fail;
			
			*( (_UInt32_*) this->m_pBuf )							= kStartMarker;
			::CopyMemory( ( this->m_pBuf + kStartMarkerSize + kBufLenSize ), pBuf + kStartMarkerSize + kBufLenSize, this->m_uBufLen );
			*( (_UInt32_*) ( this->m_pBuf + kStartMarkerSize + kBufLenSize + this->m_uBufLen ) )
																	= kEndMarker;
			this->m_uBufMaxLen										= this->m_uBufLen + kExtraSize;
		}

		this->m_uBufPtr		= kStartMarkerSize + kBufLenSize;
		this->m_bIsValid	= TRUE;

		return TRUE;
	}

fail:
	this->ResetBuffer();

	return FALSE;
}

void CNMSimpleStream::ResetPtr( void )
{
	this->m_uBufPtr		= kStartMarkerSize + kBufLenSize;
}

BYTE* CNMSimpleStream::GetBufferPtr( void )
{
	*( (_UInt32_*) ( this->m_pBuf ) )							= kStartMarker;
	*( (_UInt32_*) ( this->m_pBuf + kStartMarkerSize ) )		= this->m_uBufLen;
	*( (_UInt32_*) ( this->m_pBuf + kStartMarkerSize + kBufLenSize + this->m_uBufLen ) )
																= kEndMarker;
	return this->m_pBuf;
}

_UInt32_ CNMSimpleStream::GetBufferSize( void )
{
	return ( this->m_uBufLen + kExtraSize );
}

void CNMSimpleStream::ResetBuffer( void )
{
	if ( !this->m_bReference && this->m_pBuf != NULL )
	{
		_NMHeapFree( this->m_pBuf );
	}

	this->m_pBuf		= NULL;
	this->m_bReference	= FALSE;
	this->m_uBufLen		= 0;
	this->m_uBufMaxLen	= 0;
	this->m_uBufPtr		= 0;
	this->m_bIsValid	= FALSE;
}

#define GET_BLOCK_SIZE( x )		( ( ( (x) / kBlockSize ) + 1 ) * kBlockSize )

BOOL CNMSimpleStream::RequestBuffer( _UInt32_ uBufSize )
{
	enum
	{
		kBlockSize	= 4096,
	};

	if ( this->m_bReference || !this->m_bIsValid )
	{
		this->ResetBuffer();
	}

	if ( this->m_pBuf == NULL )
	{
		this->m_uBufMaxLen	= GET_BLOCK_SIZE( uBufSize + kExtraSize );
		this->m_pBuf		= (BYTE*) _NMHeapAlloc( this->m_uBufMaxLen );
		this->m_uBufLen		= 0;
		this->m_bIsValid	= TRUE;

		// Set Start Marker
		if ( this->m_pBuf )
		{
			*( (_UInt32_*) this->m_pBuf )	= kStartMarker;
			this->m_uBufPtr					= kStartMarkerSize + kBufLenSize;
		}
		else
		{
			this->m_bIsValid	= FALSE;
			return FALSE;
		}
	}
	else if ( ( this->m_uBufPtr + uBufSize ) > ( this->m_uBufMaxLen - kEndMarkerSize ) )
	{
		BYTE*	pNewBuf;
		_UInt32_	uNewBufLen;

		uNewBufLen			= GET_BLOCK_SIZE( this->m_uBufPtr + uBufSize + kEndMarkerSize );
		pNewBuf				= (BYTE*) _NMHeapAlloc( uNewBufLen );

		if ( pNewBuf )
		{
			::CopyMemory( pNewBuf, this->m_pBuf, this->m_uBufPtr );
			_NMHeapFree( this->m_pBuf );

			this->m_pBuf		= pNewBuf;
			this->m_uBufMaxLen	= uNewBufLen;
		}
		else
		{
			this->ResetBuffer();
			return FALSE;
		}
	}

	return TRUE;
}

//	**********************
//	CNMSimpleStreamEncoder
//	**********************

CNMSimpleStreamEncoder::CNMSimpleStreamEncoder( CNMSimpleStream& ssStream )
{
	this->m_pStream = &ssStream;
}

CNMSimpleStreamEncoder::~CNMSimpleStreamEncoder( void )
{
}

void CNMSimpleStreamEncoder::EncodeLen( _UInt32_ uLen )
{
	if ( this->m_pStream && this->m_pStream->IsValid() )
	{
		if ( uLen < ( 0x1 << 6 ) )
		{	// 1 byte : 00
			_UInt8_	uEncVal = _UInt8_( uLen );

			uEncVal = ( uEncVal << 2 ) | 0x00;

			if ( this->m_pStream->RequestBuffer( 1 ) )
			{
				::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, &uEncVal, 1 );
				this->m_pStream->m_uBufPtr	+= 1;
				this->m_pStream->m_uBufLen	+= 1;
			}
		}
		else if ( uLen < ( 0x1 << 14 ) )
		{	// 2 byte : 01
			_UInt16_	uEncVal =  _UInt16_( uLen );

			uEncVal = ( uEncVal << 2 ) | 0x0001;

			if ( this->m_pStream->RequestBuffer( 2 ) )
			{
				::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, &uEncVal, 2 );
				this->m_pStream->m_uBufPtr	+= 2;
				this->m_pStream->m_uBufLen	+= 2;
			}
		}
		else if ( uLen < ( 0x1 << 30 ) )
		{	// 4 byte : 10
			_UInt32_	uEncVal = _UInt32_( uLen );

			uEncVal = ( uEncVal << 2 ) | 0x00000002;

			if ( this->m_pStream->RequestBuffer( 4 ) )
			{
				::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, &uEncVal, 4 );
				this->m_pStream->m_uBufPtr	+= 4;
				this->m_pStream->m_uBufLen	+= 4;
			}
		}
		else
		{	// 5 byte : 11
			_UInt8_	uEncVal1 = 0x03;
			_UInt32_	uEncVal2 = _UInt32_( uLen );

			if ( this->m_pStream->RequestBuffer( 5 ) )
			{
				::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, &uEncVal1, 1 );
				this->m_pStream->m_uBufPtr	+= 1;
				this->m_pStream->m_uBufLen	+= 1;

				::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, &uEncVal2, 4 );
				this->m_pStream->m_uBufPtr	+= 4;
				this->m_pStream->m_uBufLen	+= 4;
			}
		}
	}
}

void CNMSimpleStreamEncoder::Encode1( _UInt8_ uValue )
{
	if ( this->m_pStream && this->m_pStream->RequestBuffer( 1 ) )
	{
		;
		::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, &uValue, 1 );
		this->m_pStream->m_uBufPtr	+= 1;
		this->m_pStream->m_uBufLen	+= 1;
	}
}

void CNMSimpleStreamEncoder::Encode2( _UInt16_ uValue )
{
	if ( this->m_pStream && this->m_pStream->RequestBuffer( 2 ) )
	{
		::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, &uValue, 2 );
		this->m_pStream->m_uBufPtr	+= 2;
		this->m_pStream->m_uBufLen	+= 2;
	}
}

void CNMSimpleStreamEncoder::Encode4( _UInt32_ uValue )
{
	if ( this->m_pStream && this->m_pStream->RequestBuffer( 4 ) )
	{
		::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, &uValue, 4 );
		this->m_pStream->m_uBufPtr	+= 4;
		this->m_pStream->m_uBufLen	+= 4;
	}
}

void CNMSimpleStreamEncoder::Encode8( _UInt64_ uValue )
{
	if ( this->m_pStream && this->m_pStream->RequestBuffer( 8 ) )
	{
		::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, &uValue, 8 );
		this->m_pStream->m_uBufPtr	+= 8;
		this->m_pStream->m_uBufLen	+= 8;
	}
}

void CNMSimpleStreamEncoder::EncodeString( _LPCWSTR_ szValue )
{
	if ( this->m_pStream && szValue )
	{
		_UInt32_ uLen = static_cast<_UInt32_>( wcslen( szValue ) );

		this->EncodeLen( uLen );

		if ( this->m_pStream->RequestBuffer( uLen << 1 ) )
		{
			::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, szValue, uLen << 1 );
			this->m_pStream->m_uBufPtr	+= ( uLen << 1 );
			this->m_pStream->m_uBufLen	+= ( uLen << 1 );
		}
	}
}

void CNMSimpleStreamEncoder::EncodeString( _LPCSTR_ szValue )
{
	_UInt32_	uLen	= static_cast<_UInt32_>( strlen( szValue ) );
	WCHAR*	szTemp		= new WCHAR[ uLen + 1 ];

	::MultiByteToWideChar( CP_ACP, 0, szValue, -1, szTemp, uLen + 1 );
	szTemp[ uLen ] = NULL;

	this->EncodeString( szTemp );

	delete [] szTemp;
}

void CNMSimpleStreamEncoder::EncodeBinary( LPVOID pBin, _UInt32_ uLen )
{
	if ( this->m_pStream && pBin )
	{
		this->EncodeLen( uLen );

		if ( this->m_pStream->RequestBuffer( uLen ) )
		{
			::CopyMemory( this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, pBin, uLen );
			this->m_pStream->m_uBufPtr	+= uLen;
			this->m_pStream->m_uBufLen	+= uLen;
		}
	}
}

//	**********************
//	CNMSimpleStreamDecoder
//	**********************


CNMSimpleStreamDecoder::CNMSimpleStreamDecoder( CNMSimpleStream& ssStream )
{
	this->m_pStream		= &ssStream;
	this->m_pTempStr	= NULL;
	this->m_pTempAscStr	= NULL;
}

CNMSimpleStreamDecoder::~CNMSimpleStreamDecoder( void )
{
	if ( this->m_pTempStr )
	{
		_NMHeapFree( (LPVOID) this->m_pTempStr );
		this->m_pTempStr = NULL;
	}

	if ( this->m_pTempAscStr )
	{
		_NMHeapFree( (LPVOID) this->m_pTempAscStr );
		this->m_pTempAscStr = NULL;
	}
}

_UInt32_ CNMSimpleStreamDecoder::DecodeLen( void )
{
	_UInt32_	uRetVal = 0;

	if (   ( this->m_pStream )
		&& ( this->m_pStream->IsValid() )
		&& ( this->m_pStream->m_uBufPtr + 1 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
	{
		_UInt32_	uEncodeType = ( (_UInt8_) this->m_pStream->m_pBuf[ this->m_pStream->m_uBufPtr ] ) & 0x03;

		switch ( uEncodeType )
		{
			case 0:	// 1 byte
			{
				_UInt8_	uRetVal8;

				::CopyMemory( &uRetVal8, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, 1 );
				uRetVal						= uRetVal8 >> 2;
				this->m_pStream->m_uBufPtr	+= 1;
			}
			break;

			case 1:	// 2 byte
			if ( this->m_pStream->m_uBufPtr + 2 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize )
			{
				_UInt16_	uRetVal16;

				::CopyMemory( &uRetVal16, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, 2 );
				uRetVal						= uRetVal16 >> 2;
				this->m_pStream->m_uBufPtr	+= 2;
			}
			break;

			case 2: // 4 byte
			if ( this->m_pStream->m_uBufPtr + 4 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize )
			{
				::CopyMemory( &uRetVal, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, 4 );
				uRetVal						= uRetVal >> 2;
				this->m_pStream->m_uBufPtr	+= 4;
			}
			break;

			case 3:	// 5 byte
			if ( this->m_pStream->m_uBufPtr + 5 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize )
			{
				::CopyMemory( &uRetVal, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr + 1, 4 );
				this->m_pStream->m_uBufPtr	+= 5;
			}
			break;

			default:
				break;
//				check( FALSE );
		}
	}

	return uRetVal;
}

_UInt8_ CNMSimpleStreamDecoder::Decode1( void )
{
	_UInt8_	uRetVal = 0;

	if ( this->m_pStream 
		&& this->m_pStream->IsValid()
		&& ( this->m_pStream->m_uBufPtr + 1 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
	{
		::CopyMemory( &uRetVal, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, 1 );

		this->m_pStream->m_uBufPtr	+= 1;
	}

	return uRetVal;
}

_UInt16_ CNMSimpleStreamDecoder::Decode2( void )
{
	_UInt16_	uRetVal = 0;

	if ( this->m_pStream 
		&& this->m_pStream->IsValid() 
		&& ( this->m_pStream->m_uBufPtr + 2 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
	{
		::CopyMemory( &uRetVal, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, 2 );

		this->m_pStream->m_uBufPtr	+= 2;
	}

	return uRetVal;
}

_UInt32_ CNMSimpleStreamDecoder::Decode4( void )
{
	_UInt32_	uRetVal = 0;

	if ( this->m_pStream 
		&& this->m_pStream->IsValid() 
		&& ( this->m_pStream->m_uBufPtr + 4 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
	{
		::CopyMemory( &uRetVal, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, 4 );

		this->m_pStream->m_uBufPtr	+= 4;
	}

	return uRetVal;
}

_UInt64_ CNMSimpleStreamDecoder::Decode8( void )
{
	_UInt64_	uRetVal = 0;

	if ( this->m_pStream 
		&& this->m_pStream->IsValid() 
		&& ( this->m_pStream->m_uBufPtr + 8 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
	{
		::CopyMemory( &uRetVal, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, 8 );

		this->m_pStream->m_uBufPtr	+= 8;
	}

	return uRetVal;
}

_LPCWSTR_ CNMSimpleStreamDecoder::DecodeStringW( void )
{
	if ( this->m_pTempStr )
	{
		_NMHeapFree( this->m_pTempStr );
		this->m_pTempStr = NULL;
	}

	if ( this->m_pStream && this->m_pStream->IsValid() )
	{
		_UInt32_	uLen	= this->DecodeLen();

		if ( uLen > 0 
			&& ( this->m_pStream->m_uBufPtr + ( uLen << 1 ) <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
		{
			this->m_pTempStr = (_LPWSTR_) _NMHeapAlloc( ( uLen + 1 ) << 1 );

			::CopyMemory( this->m_pTempStr, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, uLen << 1 );

			this->m_pStream->m_uBufPtr	+= ( uLen << 1 );
			this->m_pTempStr[ uLen ]	= NULL;
		}
		else if ( uLen == 0 )
		{
			return L"";
		}
	}

	return this->m_pTempStr;
}

_LPCWSTR_ CNMSimpleStreamDecoder::DecodeStringW( _LPWSTR_ pOutBuf, _UInt32_ uMaxStrLen )
{
	if ( this->m_pStream && this->m_pStream->IsValid() )
	{
		_UInt32_	uLen	= this->DecodeLen();

		if ( uLen > 0 
			&& ( this->m_pStream->m_uBufPtr + ( uLen << 1 ) <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
		{
			::CopyMemory( pOutBuf, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, ( uMaxStrLen == 0 ) ? ( uLen << 1 ) : ( __min( uLen, uMaxStrLen ) << 1 ) );

			this->m_pStream->m_uBufPtr								+= ( uLen << 1 );
			pOutBuf[ ( uMaxStrLen == 0 ) ? uLen : __min( uLen, uMaxStrLen - 1 ) ]	= NULL;
		}
		else if ( uLen == 0 )
		{
			pOutBuf[ 0 ]	= NULL;
		}
	}

	return pOutBuf;
}

_LPCSTR_ CNMSimpleStreamDecoder::DecodeStringA( void )
{
	_LPCWSTR_	szTemp	= this->DecodeStringW();

	if ( szTemp )
	{
		_UInt32_	uLen	= static_cast<_UInt32_>( wcslen( szTemp ) );

		if ( this->m_pTempAscStr )
			_NMHeapFree( this->m_pTempAscStr );

		this->m_pTempAscStr = (_LPSTR_) _NMHeapAlloc( ( uLen + 1 ) << 1 );
		::WideCharToMultiByte( CP_ACP, 0, szTemp, -1, this->m_pTempAscStr, ( uLen + 1 ) << 1, NULL, NULL );

		this->m_pTempAscStr[ ( uLen << 1 ) + 1 ] = NULL;

		return this->m_pTempAscStr;
	}

	return NULL;
}

_LPCSTR_ CNMSimpleStreamDecoder::DecodeStringA( _LPSTR_ pOutBuf, _UInt32_ uMaxStrLen )
{
	_LPCWSTR_	szTemp	= this->DecodeStringW();

	if ( szTemp )
	{
		_UInt32_	uLen	= static_cast<_UInt32_>( wcslen( szTemp ) );

		::WideCharToMultiByte( CP_ACP, 0, szTemp, -1, pOutBuf, ( uMaxStrLen == 0 ? ( uLen << 1 ) + 1 : uMaxStrLen ), NULL, NULL );
		pOutBuf[ ( uMaxStrLen == 0 ) ? uLen : uMaxStrLen - 1 ]	= NULL;

		return pOutBuf;
	}

	return NULL;
}

LPCTSTR CNMSimpleStreamDecoder::DecodeString( void )
{
#if defined( UNICODE ) || defined( _UNICODE )
	return this->DecodeStringW();
#else
	return this->DecodeStringA();
#endif
}

LPCTSTR CNMSimpleStreamDecoder::DecodeString( LPTSTR pOutBuf, _UInt32_ uMaxStrLen )
{
#if defined( UNICODE ) || defined( _UNICODE )
		return this->DecodeStringW( pOutBuf, uMaxStrLen );
#else
		return this->DecodeStringA( pOutBuf, uMaxStrLen );
#endif
}

_UInt32_ CNMSimpleStreamDecoder::DecodeBinary( LPVOID pOutBuf, _UInt32_ uBufLen )
{
	_UInt32_	uRetVal = 0;
	
	if ( this->m_pStream && this->m_pStream->IsValid() )
	{
		_UInt32_	uLen	= this->DecodeLen();

		if ( uLen > 0 
			&& ( this->m_pStream->m_uBufPtr + uLen <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
		{
			uRetVal = ( uBufLen == 0 ) ? uLen : __min( uLen, uBufLen );

			::CopyMemory( pOutBuf, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, uRetVal );

			this->m_pStream->m_uBufPtr	+= uLen;
		}
	}

	return uRetVal;
}

CNMSerializableEncoder::CNMSerializableEncoder( CNMSimpleStream& ssStream, CNMSerializable* pSerializable ) : CNMSimpleStreamEncoder( ssStream )
{
	_UInt32_		uObjectHeader = ( pSerializable->m_uObjectMainCode << 16 ) 
							|	( pSerializable->m_uObjectSubCode << 8 )
							|	( pSerializable->m_uObjectVersion );
	
	this->m_uObjectLen	= 0;
	this->m_uLenPtr		= ssStream.m_uBufPtr + 4;

	this->Encode4( uObjectHeader );		// Object Header
	this->Encode4( 0 );					// Object Length : dummy value at this time
}

CNMSerializableEncoder::~CNMSerializableEncoder( void )
{
	this->CloseObjectData();
}

_UInt32_ CNMSerializableEncoder::CloseObjectData( void )
{
	if ( this->m_pStream && this->m_pStream->IsValid() )
	{
		this->m_uObjectLen = ( this->m_pStream->m_uBufPtr - this->m_uLenPtr - 4 );
		::CopyMemory( this->m_pStream->m_pBuf + this->m_uLenPtr, &this->m_uObjectLen, 4 );
	}

	return this->m_uObjectLen;
}

CNMSerializableDecoder::CNMSerializableDecoder( CNMSimpleStream& ssStream, CNMSerializable* pSerializable ) : CNMSimpleStreamDecoder( ssStream )
{
	_UInt32_		uObjectHeader;
	_UInt32_		uSavedPtr	= this->m_pStream->m_uBufPtr;

	uObjectHeader		= CNMSimpleStreamDecoder::Decode4();
	this->m_uObjectLen	= CNMSimpleStreamDecoder::Decode4();

	this->m_uStartPtr	= this->m_pStream->m_uBufPtr;

	if ( ( uObjectHeader >> 16 ) == pSerializable->m_uObjectMainCode )
	{
		this->m_bIsValid			= TRUE;
		this->m_uDecodedVersion		= static_cast<UINT8>( uObjectHeader & 0x000000FF );
	}
	else
	{
		this->m_pStream->m_uBufPtr	= uSavedPtr;
		this->m_bIsValid			= FALSE;

	}
}

CNMSerializableDecoder::~CNMSerializableDecoder( void )
{
	if ( this->m_bIsValid )
	{
		this->m_pStream->m_uBufPtr = __min( 
			this->m_uStartPtr + this->m_uObjectLen,
			kStartMarkerSize + kBufLenSize + this->m_pStream->m_uBufLen );
	}
}

_UInt32_ CNMSerializableDecoder::DecodeLen( void )
{
	_UInt32_	uRetVal = 0;

	if ( this->m_pStream 
		&& this->m_pStream->IsValid() 
		&& ( this->m_pStream->m_uBufPtr + 1 <= this->m_uStartPtr + this->m_uObjectLen )
		&& ( this->m_pStream->m_uBufPtr + 1 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
	{
		_UInt32_	uEncodeType = ( (_UInt8_) this->m_pStream->m_pBuf[ this->m_pStream->m_uBufPtr ] ) & 0x03;

		switch ( uEncodeType )
		{
			case 0:	// 1 byte
			{
				_UInt8_	uRetVal8;

				::CopyMemory( &uRetVal8, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, 1 );
				uRetVal						= uRetVal8 >> 2;
				this->m_pStream->m_uBufPtr	+= 1;
			}
			break;

			case 1:	// 2 byte
			if ( ( this->m_pStream->m_uBufPtr + 2 <= this->m_uStartPtr + this->m_uObjectLen )
				&& (this->m_pStream->m_uBufPtr + 2 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
			{
				_UInt16_	uRetVal16;

				::CopyMemory( &uRetVal16, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, 2 );
				uRetVal						= uRetVal16 >> 2;
				this->m_pStream->m_uBufPtr	+= 2;
			}
			break;

			case 2: // 4 byte
			if ( ( this->m_pStream->m_uBufPtr + 4 <= this->m_uStartPtr + this->m_uObjectLen ) 
				&& ( this->m_pStream->m_uBufPtr + 4 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
			{
				::CopyMemory( &uRetVal, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, 4 );
				uRetVal						= uRetVal >> 2;
				this->m_pStream->m_uBufPtr	+= 4;
			}
			break;

			case 3:	// 5 byte
			if ( ( this->m_pStream->m_uBufPtr + 5 <= this->m_uStartPtr + this->m_uObjectLen ) 
				&& ( this->m_pStream->m_uBufPtr + 5 <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
			{
				::CopyMemory( &uRetVal, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr + 1, 4 );
				this->m_pStream->m_uBufPtr	+= 5;
			}
			break;

			default:
			break;
//				check( FALSE );
		}
	}

	return uRetVal;
}

_UInt8_ CNMSerializableDecoder::Decode1( void )
{
	if ( this->m_pStream->m_uBufPtr + 1 > this->m_uStartPtr + this->m_uObjectLen )
		return 0;
	return CNMSimpleStreamDecoder::Decode1();
}

_UInt16_ CNMSerializableDecoder::Decode2( void )
{
	if ( this->m_pStream->m_uBufPtr + 2 > this->m_uStartPtr + this->m_uObjectLen )
		return 0;
	return CNMSimpleStreamDecoder::Decode2();
}

_UInt32_ CNMSerializableDecoder::Decode4( void )
{
	if ( this->m_pStream->m_uBufPtr + 4 > this->m_uStartPtr + this->m_uObjectLen )
		return 0;
	return CNMSimpleStreamDecoder::Decode4();
}

_UInt64_ CNMSerializableDecoder::Decode8( void )
{
	if ( this->m_pStream->m_uBufPtr + 8 > this->m_uStartPtr + this->m_uObjectLen )
		return 0;
	return CNMSimpleStreamDecoder::Decode8();
}

_LPCWSTR_ CNMSerializableDecoder::DecodeStringW( void )
{
	if ( this->m_pTempStr )
	{
		_NMHeapFree( this->m_pTempStr );
		this->m_pTempStr = NULL;
	}

	if ( this->m_pStream && this->m_pStream->IsValid() )
	{
		_UInt32_	uLen	= this->DecodeLen();

		if ( uLen > 0 
			&& ( this->m_pStream->m_uBufPtr <= this->m_uStartPtr + this->m_uObjectLen + ( uLen << 1 ) )
			&& ( this->m_pStream->m_uBufPtr + ( uLen << 1 ) <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
		{
			this->m_pTempStr = (_LPWSTR_) _NMHeapAlloc( ( uLen + 1 ) << 1 );

			::CopyMemory( this->m_pTempStr, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, uLen << 1 );

			this->m_pStream->m_uBufPtr	+= ( uLen << 1 );
			this->m_pTempStr[ uLen ]	= NULL;
		}
	}

	if ( this->m_pTempStr != NULL )
		return this->m_pTempStr;

	return L"";
}

_LPCWSTR_ CNMSerializableDecoder::DecodeStringW( _LPWSTR_ pOutBuf, _UInt32_ uMaxStrLen )
{
	if ( this->m_pStream && this->m_pStream->IsValid() )
	{
		_UInt32_	uLen	= this->DecodeLen();

		if ( uLen > 0 
			&& ( this->m_pStream->m_uBufPtr <= this->m_uStartPtr + this->m_uObjectLen + ( uLen << 1 ) )
			&& ( this->m_pStream->m_uBufPtr + ( uLen << 1 ) <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
		{
			::CopyMemory( pOutBuf, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, ( uMaxStrLen == 0 ) ? ( uLen << 1 ) : ( __min( uLen, uMaxStrLen ) << 1 ) );

			this->m_pStream->m_uBufPtr								+= ( uLen << 1 );
			pOutBuf[ ( uMaxStrLen == 0 ) ? uLen : __min( uLen, uMaxStrLen - 1 ) ]	= NULL;
		}
		else if ( uLen == 0 )
		{
			pOutBuf[ 0 ]	= NULL;
		}
	}
	else
	{
		pOutBuf[ 0 ]	= NULL;
	}

	return pOutBuf;
}

_UInt32_ CNMSerializableDecoder::DecodeBinary( LPVOID pOutBuf, _UInt32_ uBufLen )
{
	_UInt32_	uRetVal = 0;
	
	if ( this->m_pStream && this->m_pStream->IsValid() )
	{
		_UInt32_	uLen	= this->DecodeLen();

		if ( uLen > 0 
			&& ( this->m_pStream->m_uBufPtr <= this->m_uStartPtr + this->m_uObjectLen + uLen )
			&& ( this->m_pStream->m_uBufPtr + uLen <= this->m_pStream->m_uBufLen + kStartMarkerSize + kBufLenSize ) )
		{
			uRetVal = ( uBufLen == 0 ) ? uLen : __min( uLen, uBufLen );

			::CopyMemory( pOutBuf, this->m_pStream->m_pBuf + this->m_pStream->m_uBufPtr, uRetVal );

			this->m_pStream->m_uBufPtr	+= uLen;
		}
	}

	return uRetVal;
}

CNMSerializable::CNMSerializable( _UInt16_ uObjectMainCode, _UInt8_ uObjectSubCode, _UInt8_ uObjectVersion )
{
	this->m_uObjectMainCode	= uObjectMainCode;
	this->m_uObjectSubCode	= uObjectSubCode;
	this->m_uObjectVersion	= uObjectVersion;
}

BOOL CNMSerializable::Serialize( CNMSimpleStream& ssStream )
{
	UNREFERENCED_PARAMETER(ssStream);

	return FALSE;
}

BOOL CNMSerializable::DeSerialize( CNMSimpleStream& ssStream )
{
	UNREFERENCED_PARAMETER(ssStream);

	return FALSE;
}

BOOL CNMSerializable::Serialize( CNMSerializableEncoder& encoder, CNMSimpleStream& ssStream )
{
	UNREFERENCED_PARAMETER(encoder);
	UNREFERENCED_PARAMETER(ssStream);

	return FALSE;
}

BOOL CNMSerializable::PriorSerialize( CNMSerializableEncoder& encoder, CNMSimpleStream& ssStream )
{
	UNREFERENCED_PARAMETER(encoder);
	UNREFERENCED_PARAMETER(ssStream);

	return FALSE;
}

BOOL CNMSerializable::PriorDeSerialize( CNMSerializableDecoder& decoder, CNMSimpleStream& ssStream )
{
	UNREFERENCED_PARAMETER(decoder);
	UNREFERENCED_PARAMETER(ssStream);

	return FALSE;
}

BOOL CNMSerializable::DeSerialize( CNMSerializableDecoder& decoder, CNMSimpleStream& ssStream )
{
	UNREFERENCED_PARAMETER(decoder);
	UNREFERENCED_PARAMETER(ssStream);

	return FALSE;
}

const CNMSerializable& CNMSerializable::operator =( const CNMSerializable& rValue )
{
	CNMSimpleStream		ssStream;

	((CNMSerializable *) &rValue)->Serialize( ssStream );
	ssStream.ResetPtr();
	((CNMSerializable *) this)->DeSerialize( ssStream );

	return (*this);
}
