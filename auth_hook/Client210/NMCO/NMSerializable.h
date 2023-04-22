#define _XDEF_NEXONLOGIN 1
#ifdef _XDEF_NEXONLOGIN

#ifndef __NMSERIALIZABLE_H_C48A5C4B_0911_4e1d_9F0F_768E31A69EAE__
#define __NMSERIALIZABLE_H_C48A5C4B_0911_4e1d_9F0F_768E31A69EAE__

#ifndef _GENERAL_TYPES_H_

#pragma warning ( disable:4786 )
#pragma warning ( disable:4018 )
#include <vector>
#include <Windows.h>
//#pragma warning ( default:4786 )
//#pragma warning ( default:4018 )

// Interger 
typedef unsigned __int64	_UInt64_;
typedef signed __int64		_SInt64_;
typedef unsigned int		_UInt32_;
typedef signed int			_SInt32_;
typedef unsigned short		_UInt16_;
typedef signed short		_SInt16_;
typedef unsigned char		_UInt8_;
typedef signed char			_SInt8_;

// Floating Point
typedef float				_Float32_;
typedef double				_Float64_;

// String
typedef	char*				_LPSTR_;
typedef const char*			_LPCSTR_;
typedef wchar_t*			_LPWSTR_;
typedef const wchar_t*		_LPCWSTR_;

#define		IN
#define		OUT

#endif

// NMTypes
typedef _UInt64_			NMIDCode;
typedef _UInt32_			NMGameCode;
typedef _UInt32_			NMGuildCode;
typedef _UInt64_			NMGuildKey;
typedef _UInt32_			NMGameServerCode;

class CNMSerializable;

// NM Base Classes
class	CNMSimpleStream
{
	friend class	CNMSimpleStreamEncoder;
	friend class	CNMSimpleStreamDecoder;
	friend class	CNMSerializableEncoder;
	friend class	CNMSerializableDecoder;

protected:
	BYTE*				m_pBuf;
	BOOL				m_bReference;
	_UInt32_			m_uBufLen;
	_UInt32_			m_uBufMaxLen;
	_UInt32_			m_uBufPtr;
	BOOL				m_bIsValid;

public:
	CNMSimpleStream( void );
	~CNMSimpleStream( void );

	BOOL				IsValid( void ) const;
	BOOL				SetBuffer( BYTE* pBuf, BOOL bReference = FALSE );
	void				ResetPtr( void );
	BYTE*				GetBufferPtr( void );
	_UInt32_			GetBufferSize( void );

protected:
	void				ResetBuffer( void );
	BOOL				RequestBuffer( _UInt32_ uBufSize );
};

class CNMSimpleStreamEncoder
{
protected:
	CNMSimpleStream*	m_pStream;

public:
	CNMSimpleStreamEncoder( CNMSimpleStream& ssStream );
	~CNMSimpleStreamEncoder( void );

protected:
	void				EncodeLen( _UInt32_ uLen );

public:
	void				Encode1( _UInt8_ uValue );
	void				Encode2( _UInt16_ uValue );
	void				Encode4( _UInt32_ uValue );
	void				Encode8( _UInt64_ uValue );
	void				EncodeString( _LPCWSTR_ szValue );
	void				EncodeString( _LPCSTR_ szValue );
	void				EncodeBinary( LPVOID pBin, _UInt32_ uLen );
};

class CNMSimpleStreamDecoder
{
protected:
	CNMSimpleStream*	m_pStream;
	_LPWSTR_			m_pTempStr;
	_LPSTR_				m_pTempAscStr;

public:
	CNMSimpleStreamDecoder( CNMSimpleStream& ssStream );
	~CNMSimpleStreamDecoder( void );

protected:
	virtual _UInt32_	DecodeLen( void );

public:
	virtual _UInt8_		Decode1( void );
	virtual _UInt16_	Decode2( void );
	virtual _UInt32_	Decode4( void );
	virtual _UInt64_	Decode8( void );
	virtual _LPCWSTR_	DecodeStringW( void );
	virtual _LPCWSTR_	DecodeStringW( _LPWSTR_ pOutBuf, _UInt32_ uMaxStrLen );
	virtual _LPCSTR_	DecodeStringA( void );
	virtual _LPCSTR_	DecodeStringA( _LPSTR_ pOutBuf, _UInt32_ uMaxStrLen );
	virtual LPCTSTR		DecodeString( void );
	virtual LPCTSTR		DecodeString( LPTSTR pOutBuf, _UInt32_ uMaxStrLen );

	virtual _UInt32_	DecodeBinary( LPVOID pOutBuf, _UInt32_ uBufLen );
};

class	CNMSerializableEncoder : public CNMSimpleStreamEncoder
{
protected:
	_UInt32_			m_uObjectLen;
	_UInt32_			m_uLenPtr;

public:
	CNMSerializableEncoder( CNMSimpleStream& ssStream, CNMSerializable* pSerializable );
	~CNMSerializableEncoder( void );

	inline void			CheckFunction( void ) {};

protected:
	_UInt32_			CloseObjectData( void );

};

class	CNMSerializableDecoder : public CNMSimpleStreamDecoder
{
protected:
	_UInt32_			m_uObjectLen;
	BOOL				m_bIsValid;
	_UInt8_				m_uDecodedVersion;
	_UInt32_			m_uStartPtr;

public:
	CNMSerializableDecoder( CNMSimpleStream& ssStream, CNMSerializable* pSerializable );
	~CNMSerializableDecoder( void );

	inline	_UInt32_	GetVersion( void )		{ return this->m_uDecodedVersion; }
	inline	BOOL		IsValid( void )			{ return this->m_bIsValid; }

protected:
	virtual _UInt32_	DecodeLen( void );

public:
	virtual _UInt8_		Decode1( void );
	virtual _UInt16_	Decode2( void );
	virtual _UInt32_	Decode4( void );
	virtual _UInt64_	Decode8( void );
	virtual _LPCWSTR_	DecodeStringW( void );
	virtual _LPCWSTR_	DecodeStringW( _LPWSTR_ pOutBuf, _UInt32_ uMaxStrLen = 0 );
	virtual _UInt32_	DecodeBinary( LPVOID pOutBuf, _UInt32_ uBufLen = 0 );
};

#define		DECLARE_SERIALIZABLE	\
	virtual	BOOL		Serialize( CNMSimpleStream& ssStream );\
	virtual	BOOL		PriorSerialize( CNMSerializableEncoder& encoder, CNMSimpleStream& ssStream );\
	virtual	BOOL		Serialize( CNMSerializableEncoder& encoder, CNMSimpleStream& ssStream );\
	virtual BOOL		DeSerialize( CNMSimpleStream& ssStream );\
	virtual BOOL		PriorDeSerialize( CNMSerializableDecoder& decoder, CNMSimpleStream& ssStream );\
	virtual BOOL		DeSerialize( CNMSerializableDecoder& decoder, CNMSimpleStream& ssStream );


#define		BEGIN_SERIALIZE( className )	\
	BOOL	className::Serialize( CNMSimpleStream& ssStream )		\
	{																\
		CNMSerializableEncoder	encoder( ssStream, this );			\
		return className::Serialize( encoder, ssStream );			\
	}																\
	BOOL	className::PriorSerialize( CNMSerializableEncoder& encoder, CNMSimpleStream& ssStream )\
	{																\
		if ( this->m_uObjectVersion == 1 )							\
			return className::Serialize( encoder, ssStream );		\
		return className::Serialize( ssStream );					\
	}																\
	BOOL	className::Serialize( CNMSerializableEncoder& encoder, CNMSimpleStream& ssStream )\
	{

#define		END_SERIALIZE()											\
		encoder.CheckFunction();									\
		return TRUE;												\
	}

#define		BEGIN_DESERIALIZE( className )							\
	BOOL	className::DeSerialize( CNMSimpleStream& ssStream )		\
	{																\
		CNMSerializableDecoder	decoder( ssStream, this );			\
		if ( !decoder.IsValid() )	return FALSE;					\
		return className::DeSerialize( decoder, ssStream );			\
	}																\
	BOOL	className::PriorDeSerialize( CNMSerializableDecoder& decoder, CNMSimpleStream& ssStream )		\
	{																\
		if ( decoder.GetVersion() == 2 )							\
			return className::DeSerialize( ssStream );				\
		return className::DeSerialize( decoder, ssStream );			\
	}																\
	BOOL	className::DeSerialize( CNMSerializableDecoder& decoder, CNMSimpleStream& ssStream )\
	{				

#define		END_DESERIALIZE()										\
		return decoder.IsValid();									\
	}

#define		PRIOR_SERIALIZE( className )							\
	className::PriorSerialize( encoder, ssStream );						

#define		PRIOR_DESERIALIZE( className )							\
	className::PriorDeSerialize( decoder, ssStream );					

#define		OBJECT_ENCODE( objectName )								\
	objectName.Serialize( ssStream );

#define		OBJECT_DECODE( objectName )								\
	objectName.DeSerialize( ssStream );

#define		ITERATE( type, var, iter )								\
	for ( type::iterator iter = var.begin(), __iterEnd = var.end(); iter != __iterEnd; ++iter )

#define		ITERATE_CONST( type, var, iter )						\
	for ( type::const_iterator iter = var.begin(), __iterEnd = var.end(); iter != __iterEnd; ++iter )

#define		LIST_ENCODE( listType, listVar )						\
	{																\
		encoder.Encode4( static_cast<_UInt32_>( listVar.size() ) );	\
		ITERATE( listType, listVar, iter )							\
			OBJECT_ENCODE( ( *iter ) );								\
	}

#define		LIST_DECODE( listType, listVar )						\
	{																\
		listType::value_type	__x_elem;							\
		_UInt32_				__x_uCount;							\
		__x_uCount	= decoder.Decode4();							\
		listVar.clear();											\
		for ( _UInt32_ __x_i = 0; __x_i < __x_uCount; ++__x_i )		\
		{															\
			OBJECT_DECODE( __x_elem );								\
			listVar.push_back( __x_elem );							\
		}															\
	}

class	CNMSerializable
{
	friend class	CNMSerializableEncoder;
	friend class	CNMSerializableDecoder;

protected:
	_UInt16_	m_uObjectMainCode;
	_UInt8_		m_uObjectSubCode;
	_UInt8_		m_uObjectVersion;

public:
	CNMSerializable( _UInt16_ uObjectMainCode, _UInt8_ uObjectSubCode, _UInt8_ uObjectVersion );

	DECLARE_SERIALIZABLE

	virtual const CNMSerializable&	operator=( const CNMSerializable& rvalue );
};

#endif	//	#ifndef __NMSERIALIZABLE_H_C48A5C4B_0911_4e1d_9F0F_768E31A69EAE__

#endif