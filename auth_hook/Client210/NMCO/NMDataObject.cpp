#include "NMDataObject.h"
#include <string>
#include <algorithm>


#ifndef STRSAFE_NO_DEPRECATE
#define STRSAFE_NO_DEPRECATE
#endif
#include <strsafe.h>

NMVirtualKey const		keyVirtualAll	= { kGameCode_NULL, 0 };
NMVirtualKey const		keyNexoncom		= { kGameCode_nexoncom, 1 };
NMVirtualKey const		keyKart			= { kGameCode_kartrider, 1 };

void VerifyGameCode( NMGameCode& uGameCode )
{
	if ( GET_GAMEMAINCODE( uGameCode ) == kMainGameCode_nexoncom )
	{
		switch ( GET_GAMESUBCODE( uGameCode ) )
		{
		case kSubGameCode_kartrider:
		case kSubGameCode_karttest:
		case kSubGameCode_warrock:
		case kSubGameCode_bigshot:
		case kSubGameCode_zera:
		case kSubGameCode_zeratest:
		case kSubGameCode_zeratest_internal:
		case kSubGameCode_plug:
		case kSubGameCode_lunia:
		case kSubGameCode_luniatest:
		case kSubGameCode_nanaimo:
		case kSubGameCode_nanaimotest:
		case kSubGameCode_kickoff:
		case kSubGameCode_kickofftest:
		case kSubGameCode_ninedragons:
		case kSubGameCode_koongpa:
		case kSubGameCode_talesweaver:
		case kSubGameCode_elsword:
		case kSubGameCode_elswordtest:
		case kSubGameCode_combatarms:
			break;
		default:
			uGameCode	= ( kMainGameCode_nexoncom << 16 ) | kSubGameCode_NULL;
			break;
		}
	}
	else if ( GET_GAMEMAINCODE( uGameCode ) == kMainGameCode_nexonjp )
	{
		switch ( GET_GAMESUBCODE( uGameCode ) )
		{
		case kSubGameCode_lunia:
		case kSubGameCode_luniatest:
			break;
		default:
			uGameCode	= ( kMainGameCode_nexonjp << 16 ) | kSubGameCode_NULL;
			break;
		}
	}
	else if ( GET_GAMEMAINCODE( uGameCode ) == kMainGameCode_nexoncn )
	{
		switch ( GET_GAMESUBCODE( uGameCode ) )
		{
		case kSubGameCode_kartrider:
		case kSubGameCode_karttest:
		case kSubGameCode_bigshot:
		case kSubGameCode_talesweaver:
			break;
		default:
			uGameCode	= ( kMainGameCode_nexoncn << 16 ) | kSubGameCode_NULL;
			break;
		}
	}
	else if ( GET_GAMEMAINCODE( uGameCode ) == kMainGameCode_nexontw )
	{
		switch ( GET_GAMESUBCODE( uGameCode ) )
		{
		case kSubGameCode_kartrider:
		case kSubGameCode_karttest:
			break;
		default:
			uGameCode	= ( kMainGameCode_nexontw << 16 ) | kSubGameCode_NULL;
			break;
		}
	}
	else if ( GET_GAMEMAINCODE( uGameCode ) == kMainGameCode_nexonus )
	{
		switch ( GET_GAMESUBCODE( uGameCode ) )
		{
		case kSubGameCode_kartrider:
		case kSubGameCode_karttest:
			break;
		default:
			uGameCode	= ( kMainGameCode_nexonus << 16 ) | kSubGameCode_NULL;
			break;
		}
	}
}

bool operator==( const NMVirtualKey& keyLeft, const NMVirtualKey& keyRight )
{
	return ( keyLeft.uGameCode == keyRight.uGameCode && keyLeft.uVirtualIDCode == keyRight.uVirtualIDCode );
}

bool operator==( const NMFriendKey& keyLeft, const NMFriendKey& keyRight )
{
	return  ( keyLeft.uIDCode == keyRight.uIDCode && keyLeft.keyOwner == keyRight.keyOwner && keyLeft.keyVirtual == keyRight.keyVirtual );
}

bool operator==( const NMFriendCode& icLeft, const NMFriendCode& icRight )
{
	return ( icLeft.uIDCode == icRight.uIDCode && icLeft.keyVirtual == icRight.keyVirtual );
}

namespace wapp
{
	void CopyNString( LPTSTR pszBuffer, LPCTSTR pszSource, size_t bufferSize )
	{
		if ( pszSource )
		{
			_tcsncpy( pszBuffer, pszSource, bufferSize );
			pszBuffer[ bufferSize - 1 ] = 0;
		}
		else
		{
			pszBuffer[ 0 ] = 0;
		}
	}
};

CNMAvatarItemInfo::CNMAvatarItemInfo( void ) : CNMSerializable( 'ai', 1, 1 )
{
	this->uEmotion		= kEmotion_NULL;
	this->uIconItem		= 0;
}

BEGIN_SERIALIZE( CNMAvatarItemInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode1( static_cast<UINT8>( this->uEmotion ) );
	encoder.Encode4( this->uIconItem );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMAvatarItemInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uEmotion		= (NMEMOTION) decoder.Decode1();
	this->uIconItem		= decoder.Decode4();
//}
END_DESERIALIZE()

CNMAvatarFullInfo::CNMAvatarFullInfo( void ) : CNMAvatarItemInfo()
{
	++this->m_uObjectSubCode;

	this->uSex			= kSex_Unknown;
	this->uMsgXPos		= 0;
	this->uMsgYPos		= 0;

	for ( _UInt32_ i = 0; i < FULL_LAYER_SIZE; ++i )
	{
		this->uFullItem[ i ]		= 0;
		this->uFullMaskInfo[ i ]	= 0;
	}
}

bool CNMAvatarFullInfo::operator==( CNMAvatarFullInfo& rInfo )
{
	if ( this->uSex != rInfo.uSex
		|| this->uMsgXPos != rInfo.uMsgXPos
		|| this->uMsgYPos != rInfo.uMsgYPos )
		return FALSE;

	for ( _UInt32_ i = 0; i < FULL_LAYER_SIZE; ++i )
	{
		if ( this->uFullItem[ i ] != rInfo.uFullItem[ i ]
			|| this->uFullMaskInfo[ i ] != rInfo.uFullMaskInfo[ i ] )
			return FALSE;
	}

	return TRUE;
}

BEGIN_SERIALIZE( CNMAvatarFullInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_SERIALIZE( CNMAvatarItemInfo );

	encoder.Encode1( static_cast<UINT8>( this->uSex ) );
	encoder.Encode2( this->uMsgXPos );
	encoder.Encode2( this->uMsgYPos );

	for ( _UInt32_ i = 0; i < FULL_LAYER_SIZE; ++i )
	{
		encoder.Encode4( this->uFullItem[ i ] );
		encoder.Encode4( this->uFullMaskInfo[ i ] );
	}
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMAvatarFullInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_DESERIALIZE( CNMAvatarItemInfo );

	this->uSex			= (NMSEXTYPE) decoder.Decode1();
	this->uMsgXPos		= decoder.Decode2();
	this->uMsgYPos		= decoder.Decode2();

	for ( _UInt32_ i = 0; i < FULL_LAYER_SIZE; ++i )
	{
		this->uFullItem[ i ]		= decoder.Decode4();
		this->uFullMaskInfo[ i ]	= decoder.Decode4();
	}
//}
END_DESERIALIZE()

CNMDefaultUserInfo::CNMDefaultUserInfo( void ) : CNMSerializable( 'ui', 1, 1 )
{
	::ZeroMemory( this->szLoginID, sizeof( this->szLoginID ) );
	this->uIDCode				= 0;

	this->uFlag					= 0;
	::ZeroMemory( this->szWord, sizeof( this->szWord ) );

	this->uStatus				= kStatus_Unknown;
	this->uLocalIP				= 0;
	this->uVirtualIP			= 0;
	this->uPort					= 0;
}

BEGIN_SERIALIZE( CNMDefaultUserInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);
	
	encoder.EncodeString( this->szLoginID );
	encoder.Encode8( this->uIDCode );

	encoder.Encode4( this->uFlag );
	encoder.EncodeString( this->szWord );

	encoder.Encode4( this->uStatus );
	encoder.Encode4( this->uLocalIP );
	encoder.Encode4( this->uVirtualIP );
	encoder.Encode2( this->uPort );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMDefaultUserInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	decoder.DecodeString( this->szLoginID, LOGINID_SIZE );
	this->uIDCode				= decoder.Decode8();

	this->uFlag					= decoder.Decode4();
	decoder.DecodeString( this->szWord, WORD_SIZE );

	this->uStatus				= (NMUSERSTATUS) decoder.Decode4();
	this->uLocalIP				= decoder.Decode4();
	this->uVirtualIP			= decoder.Decode4();
	this->uPort					= decoder.Decode2();
//}
END_DESERIALIZE()

CNMRealFriendInfo::CNMRealFriendInfo() : CNMDefaultUserInfo()
{
	++this->m_uObjectSubCode;

	::ZeroMemory( this->szMemo, sizeof( this->szMemo ) );

	::ZeroMemory( &this->stBirthday, sizeof( this->stBirthday ) );
	this->uBirthdayType	= 0;
}

BEGIN_SERIALIZE( CNMRealFriendInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_SERIALIZE( CNMDefaultUserInfo );
	
	encoder.EncodeString( this->szMemo );
	OBJECT_ENCODE( this->aiAvatarItemInfo );

	if ( this->m_uObjectSubCode == 2 )
	{
		encoder.Encode4( this->stBirthday.wYear );
		encoder.Encode1( static_cast<_UInt8_>(this->stBirthday.wMonth) );
		encoder.Encode1( static_cast<_UInt8_>(this->stBirthday.wDay) );

		encoder.Encode1( this->uBirthdayType );
	}
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMRealFriendInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_DESERIALIZE( CNMDefaultUserInfo );

	decoder.DecodeString( this->szMemo, MEMO_SIZE );
	OBJECT_DECODE( this->aiAvatarItemInfo );

	if ( this->m_uObjectSubCode == 2 )
	{
		this->stBirthday.wYear	= static_cast<WORD>( decoder.Decode4() );
		this->stBirthday.wMonth	= decoder.Decode1();
		this->stBirthday.wDay	= decoder.Decode1();

		this->uBirthdayType		= decoder.Decode1();
	}
//}
END_DESERIALIZE()

CNMVirtualFriendInfo::CNMVirtualFriendInfo( void ) : CNMSerializable( 'fv', 1, 1 )
{
	INITIALIZE_FRIENDKEY( this->keyFriend );

	::ZeroMemory( this->szNickName, sizeof( this->szNickName ) );
	::ZeroMemory( this->szPosition, sizeof( this->szPosition ) );
	
	this->uVirtualStatus	= kVirtualStatus_Offline;
	this->uFlag				= 0;
	this->uLevel			= 0;
}

BEGIN_SERIALIZE( CNMVirtualFriendInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	ENCODE_FRIENDKEY( this->keyFriend );

	encoder.EncodeString( this->szNickName );
	encoder.EncodeString( this->szPosition );

	encoder.Encode4( this->uVirtualStatus );
	encoder.Encode4( this->uFlag );
	encoder.Encode4( this->uLevel );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMVirtualFriendInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	DECODE_FRIENDKEY( this->keyFriend );

	decoder.DecodeString( this->szNickName, NICKNAME_SIZE );
	decoder.DecodeString( this->szPosition, POSITION_SIZE );

	this->uVirtualStatus	= decoder.Decode4();
	this->uFlag				= decoder.Decode4();
	this->uLevel			= decoder.Decode4();
//}
END_DESERIALIZE()

CNMFriendInfo::CNMFriendInfo( void ) : CNMRealFriendInfo()
{
	++this->m_uObjectSubCode;

	INITIALIZE_FRIENDKEY( this->keyFriend );
	::ZeroMemory( this->szNickName, sizeof( this->szNickName ) );
	::ZeroMemory( this->szPosition, sizeof( this->szPosition ) );

	this->bIsVirtualOnline	= FALSE;
	this->uLevel			= 0;
}

BEGIN_SERIALIZE( CNMFriendInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_SERIALIZE( CNMRealFriendInfo );

	ENCODE_FRIENDKEY( this->keyFriend );
	encoder.EncodeString( this->szNickName );
	encoder.EncodeString( this->szPosition );

	encoder.Encode1( static_cast<UINT8>( this->bIsVirtualOnline ) );
	encoder.Encode4( this->uLevel );

	encoder.Encode4( this->stBirthday.wYear );
	encoder.Encode1( static_cast<_UInt8_>(this->stBirthday.wMonth) );
	encoder.Encode1( static_cast<_UInt8_>(this->stBirthday.wDay) );

	encoder.Encode1( this->uBirthdayType );
	
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMFriendInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_DESERIALIZE( CNMRealFriendInfo );

	DECODE_FRIENDKEY( this->keyFriend );
	decoder.DecodeString( this->szNickName, NICKNAME_SIZE );
	decoder.DecodeString( this->szPosition, POSITION_SIZE );

	this->bIsVirtualOnline	= decoder.Decode1();
	this->uLevel			= decoder.Decode4();

	this->stBirthday.wYear	= static_cast<WORD>( decoder.Decode4() );
	this->stBirthday.wMonth	= decoder.Decode1();
	this->stBirthday.wDay	= decoder.Decode1();

	this->uBirthdayType		= decoder.Decode1();
//}
END_DESERIALIZE()

NMFriendCode CNMFriendInfo::GetFriendCode( void )
{
	NMFriendCode		fcFriendCode;

	fcFriendCode.uIDCode		= this->uIDCode;
	fcFriendCode.keyVirtual		= this->keyFriend.keyVirtual;

	return fcFriendCode;
}

CNMRealUserInfo::CNMRealUserInfo( void ) : CNMDefaultUserInfo()
{
	++this->m_uObjectSubCode;

	::ZeroMemory( this->szName, sizeof( this->szName ) );
	::ZeroMemory( this->szNickName, sizeof( this->szNickName ) );
	this->uMaskInfo		= 0;
	this->uSex			= kSex_Unknown;
	this->uAge			= 0;
	this->uAreaCode		= 0;
	::ZeroMemory( this->szAreaName, sizeof( this->szAreaName ) );
	this->uSchoolCode	= 0;
	::ZeroMemory( this->szSchoolName, sizeof( this->szSchoolName ) );
	::ZeroMemory( &this->stBirthday, sizeof( this->stBirthday ) );
	this->uBirthdayType	= 0;
	::ZeroMemory( this->szMabiID, sizeof( this->szMabiID ) );

	this->uAllowChatType			= kConfigAllowValue_Unknown;
	this->uAllowNoteType			= kConfigAllowValue_Unknown;
	this->uAllowGameInviteType		= kConfigAllowValue_Unknown;
	this->uAllowRequestFriendType	= kConfigAllowValue_Unknown;
}

BEGIN_SERIALIZE( CNMRealUserInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_SERIALIZE( CNMDefaultUserInfo );

	encoder.EncodeString( this->szName );
	encoder.EncodeString( this->szNickName );
	encoder.Encode4( this->uMaskInfo );
	encoder.Encode1( static_cast<UINT8>( this->uSex ) );
	encoder.Encode4( this->uAge );
	encoder.Encode4( this->uAreaCode );
	encoder.EncodeString( this->szAreaName );
	encoder.Encode4( this->uSchoolCode );
	encoder.EncodeString( this->szSchoolName );
	encoder.Encode4( this->stBirthday.wYear );
	encoder.Encode1( static_cast<_UInt8_>(this->stBirthday.wMonth) );
	encoder.Encode1( static_cast<_UInt8_>(this->stBirthday.wDay) );
	
	OBJECT_ENCODE( this->aiAvatarInfo );

	encoder.Encode1( static_cast<UINT8>( this->uAllowChatType ) );
	encoder.Encode1( static_cast<UINT8>( this->uAllowNoteType ) );
	encoder.Encode1( static_cast<UINT8>( this->uAllowGameInviteType ) );
	encoder.Encode1( static_cast<UINT8>( this->uAllowRequestFriendType ) );

	if ( this->m_uObjectSubCode == 2 )
	{
		encoder.Encode1( this->uBirthdayType );
		encoder.EncodeString( this->szMabiID );
	}
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMRealUserInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_DESERIALIZE( CNMDefaultUserInfo );

	decoder.DecodeString( this->szName, NXNAME_SIZE );
	decoder.DecodeString( this->szNickName, NICKNAME_SIZE );
	this->uMaskInfo					= decoder.Decode4();
	this->uSex						= (NMSEXTYPE) decoder.Decode1();
	this->uAge						= decoder.Decode4();
	this->uAreaCode					= decoder.Decode4();
	decoder.DecodeString( this->szAreaName, NXAREA_SIZE );
	this->uSchoolCode				= decoder.Decode4();
	decoder.DecodeString( this->szSchoolName, NXSCHOOL_SIZE );
	this->stBirthday.wYear			= static_cast<WORD>( decoder.Decode4() );
	this->stBirthday.wMonth			= decoder.Decode1();
	this->stBirthday.wDay			= decoder.Decode1();

	OBJECT_DECODE( this->aiAvatarInfo );

	this->uAllowChatType			= (NMALLOWTYPE) decoder.Decode1();
	this->uAllowNoteType			= (NMALLOWTYPE) decoder.Decode1();
	this->uAllowGameInviteType		= (NMALLOWTYPE) decoder.Decode1();
	this->uAllowRequestFriendType	= (NMALLOWTYPE) decoder.Decode1();

	if ( this->m_uObjectSubCode == 2 )
	{
		this->uBirthdayType				= decoder.Decode1();
		decoder.DecodeString( this->szMabiID, LOGINID_SIZE );
	}
//}
END_DESERIALIZE()

CNMSelfUserInfo::CNMSelfUserInfo( void ) : CNMRealUserInfo()
{
}

CNMVirtualUserInfo::CNMVirtualUserInfo( void ) : CNMSerializable( 'uv', 1, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyVirtual );
	::ZeroMemory( this->szNickName, sizeof( this->szNickName ) );
	::ZeroMemory( this->szPosition, sizeof( this->szPosition ) );
	this->bIsVirtualOnline	= FALSE;
	this->bIsStealth		= FALSE;
	this->uLoginTick		= 0;
	this->uLevel			= 0;
}

BEGIN_SERIALIZE( CNMVirtualUserInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	ENCODE_VIRTUALKEY( this->keyVirtual );
	encoder.EncodeString( this->szNickName );
	encoder.EncodeString( this->szPosition );
	encoder.Encode1( static_cast<UINT8>( this->bIsVirtualOnline ) );
	encoder.Encode1( static_cast<UINT8>( this->bIsStealth ) );
	encoder.Encode1( static_cast<UINT8>( this->uLoginTick ) );
	encoder.Encode4( this->uLevel );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMVirtualUserInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	DECODE_VIRTUALKEY( this->keyVirtual );
	decoder.DecodeString( this->szNickName, NICKNAME_SIZE );
	decoder.DecodeString( this->szPosition, POSITION_SIZE );
	this->bIsVirtualOnline		= decoder.Decode1();
	this->bIsStealth			= decoder.Decode1();
	this->uLoginTick			= decoder.Decode1();
	this->uLevel				= decoder.Decode4();
//}
END_DESERIALIZE()

CNMFriendList::CNMFriendList( void ) : CNMSerializable( 'Fl', 1, 1 )
{
}

BEGIN_SERIALIZE( CNMFriendList )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_ENCODE( NMFriendList, this->aMembers );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMFriendList )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_DECODE( NMFriendList, this->aMembers );
//}
END_DESERIALIZE();

CNMCategoryInfo::CNMCategoryInfo( void ) : CNMSerializable( 'ci', 1, 1 )
{
	this->uCategoryCode		= 0;
	INITIALIZE_VIRTUALKEY( this->keyOwner );
	ZeroMemory( this->szCategoryName, sizeof( this->szCategoryName ) );
	this->uProperty			= 0;
	this->uAllowType		= 0;
}

BEGIN_SERIALIZE( CNMCategoryInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uCategoryCode );
	ENCODE_VIRTUALKEY( this->keyOwner );
	encoder.EncodeString( this->szCategoryName );

	if ( this->m_uObjectSubCode == 1 )
	{
		encoder.Encode1( this->uProperty );
		encoder.Encode1( this->uAllowType );
	}
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMCategoryInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uCategoryCode		= decoder.Decode4();
	DECODE_VIRTUALKEY( this->keyOwner );
	decoder.DecodeString( this->szCategoryName, CATEGORY_SIZE );

	if ( this->m_uObjectSubCode == 1 )
	{
		this->uProperty			= decoder.Decode1();
		this->uAllowType		= decoder.Decode1();
	}
//}
END_DESERIALIZE()

CNMCateFriendInfo::CNMCateFriendInfo( void ) : CNMCategoryInfo()
{
	++this->m_uObjectSubCode;
}

BEGIN_SERIALIZE( CNMCateFriendInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_SERIALIZE( CNMCategoryInfo );

	LIST_ENCODE( NMFriendList, this->aFriends );

	encoder.Encode1( this->uProperty );
	encoder.Encode1( this->uAllowType );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMCateFriendInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_DESERIALIZE( CNMCategoryInfo );

	LIST_DECODE( NMFriendList, this->aFriends );	

	this->uProperty			= decoder.Decode1();
	this->uAllowType		= decoder.Decode1();
//}
END_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMRejectedUser
//////////////////////////////////////////////////////////////////////////

CNMRejectedUser::CNMRejectedUser()
	: CNMSerializable('ru', 1, 1)
{
	wapp::CopyNString(this->szMemo, NULL, MEMO_SIZE);
}

CNMRejectedUser::CNMRejectedUser(CNMCharacter const & rejectedCharacter, LPCTSTR pszMemo /* = NULL */)
	: CNMSerializable('ru', 1, 1)
{
	this->rejectedCharacter = rejectedCharacter;
	wapp::CopyNString(this->szMemo, pszMemo, MEMO_SIZE);
}

BEGIN_SERIALIZE(CNMRejectedUser)
//{
	UNREFERENCED_PARAMETER(ssStream);

	OBJECT_ENCODE(this->rejectedCharacter);
	encoder.EncodeString(this->szMemo);
//}
END_SERIALIZE()

BEGIN_DESERIALIZE(CNMRejectedUser)
//{
	UNREFERENCED_PARAMETER(ssStream);

	OBJECT_DECODE(this->rejectedCharacter);
	decoder.DecodeString(this->szMemo, MEMO_SIZE);
//}
END_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMNoteInfo
//////////////////////////////////////////////////////////////////////////

CNMNoteInfo::CNMNoteInfo( void ) : CNMSerializable( 'ni', 1, 1 )
{
	this->uNoteSerial		= 0;
	this->uNoteType			= kNoteType_NULL;
	this->uSubType			= 0;
	INITIALIZE_VIRTUALKEY( this->keyVirtual );
	this->bReadFlag			= FALSE;
	::ZeroMemory( &this->stTime, sizeof( this->stTime ) );

	::ZeroMemory( this->szTargetID, sizeof( this->szTargetID ) );

	::ZeroMemory( this->szFromLoginID, sizeof( this->szFromLoginID ) );
	::ZeroMemory( this->szFromNickName, sizeof( this->szFromNickName ) );

	::ZeroMemory( this->szMessage, sizeof( this->szMessage ) );
	::ZeroMemory( this->szArgument, sizeof( this->szArgument ) );
	::ZeroMemory( this->szCustomData, sizeof( this->szCustomData ) );
}

CNMNoteInfo::CNMNoteInfo( CNMNoteInfo2 & rhs ) : CNMSerializable( 'ni', 1, 1 )
{
	this->uNoteSerial	= rhs.uNoteSerial;
	this->uNoteType		= static_cast<CNMNoteInfo::NMNOTETYPE>(rhs.uNoteType);
	this->uSubType		= rhs.uNoteSubType;
	this->keyVirtual	= rhs.GetSender().GetVKey();
	this->bReadFlag		= rhs.bReadFlag;
	this->stTime		= rhs.stTime;

	CNMNoteInfoHelper::CharacterListToString(rhs.receiverList, this->szTargetID, TARGETID_SIZE);
	wapp::CopyNString(this->szFromLoginID, rhs.GetSender().szLoginID, LOGINID_SIZE);
	wapp::CopyNString(this->szFromNickName, rhs.GetSender().szNickname, NICKNAME_SIZE);

	wapp::CopyNString(this->szMessage, rhs.szMessage, NOTEMSG_SIZE);
	wapp::CopyNString(this->szArgument, rhs.szArgument, NOTEARG_SIZE);
	wapp::CopyNString(this->szCustomData, rhs.szCustomData, NOTECUSTOM_SIZE);
}

BEGIN_SERIALIZE( CNMNoteInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uNoteSerial );
	encoder.Encode4( this->uNoteType );
	encoder.Encode4( this->uSubType );
	ENCODE_VIRTUALKEY( this->keyVirtual );
	encoder.Encode1( static_cast<UINT8>( this->bReadFlag ) );

	encoder.Encode2( this->stTime.wYear );
	encoder.Encode1( static_cast<_UInt8_>(this->stTime.wMonth) );
	encoder.Encode1( static_cast<_UInt8_>(this->stTime.wDay) );
	encoder.Encode1( static_cast<_UInt8_>(this->stTime.wHour) );
	encoder.Encode1( static_cast<_UInt8_>(this->stTime.wMinute) );
	encoder.Encode1( static_cast<_UInt8_>(this->stTime.wSecond) );

	encoder.EncodeString( this->szTargetID );

	encoder.EncodeString( this->szFromLoginID );
	encoder.EncodeString( this->szFromNickName );

	encoder.EncodeString( this->szMessage );
	encoder.EncodeString( this->szArgument );
	encoder.EncodeString( this->szCustomData );
	OBJECT_ENCODE( this->aiAvatarInfo );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMNoteInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uNoteSerial		= decoder.Decode4();
	this->uNoteType			= (NMNOTETYPE) decoder.Decode4();
	this->uSubType			= decoder.Decode4();
	DECODE_VIRTUALKEY( this->keyVirtual );
	this->bReadFlag			= decoder.Decode1();

	this->stTime.wYear		= decoder.Decode2();
	this->stTime.wMonth		= decoder.Decode1();
	this->stTime.wDay		= decoder.Decode1();
	this->stTime.wHour		= decoder.Decode1();
	this->stTime.wMinute	= decoder.Decode1();
	this->stTime.wSecond	= decoder.Decode1();

	decoder.DecodeString( this->szTargetID, TARGETID_SIZE );

	decoder.DecodeString( this->szFromLoginID, LOGINID_SIZE );
	decoder.DecodeString( this->szFromNickName, NICKNAME_SIZE );

	decoder.DecodeString( this->szMessage, NOTEMSG_SIZE );
	decoder.DecodeString( this->szArgument, NOTEARG_SIZE );
	decoder.DecodeString( this->szCustomData, NOTECUSTOM_SIZE );
	OBJECT_DECODE( this->aiAvatarInfo );
//}
END_DESERIALIZE()

CNMNoteInfoEx::CNMNoteInfoEx( void ) : CNMSerializable( 'ne', 1, 1 )
{
	this->uNoteExType	= kNoteExType_NULL;
	ZeroMemory( this->szMessage, sizeof( this->szMessage ) );
}

BEGIN_SERIALIZE( CNMNoteInfoEx )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uNoteExType );
	encoder.EncodeString( this->szMessage );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMNoteInfoEx )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uNoteExType		= (NMNOTEEXTYPE) decoder.Decode4();
	decoder.DecodeString( this->szMessage, NOTEEXMSG_SIZE );
//}
END_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMCharacter
//////////////////////////////////////////////////////////////////////////

CNMCharacter::CNMCharacter()
: CNMSerializable('cr', 1, 1)
{
	::ZeroMemory(this->szLoginID, sizeof(this->szLoginID));
	INITIALIZE_VIRTUALKEY(this->vkey);
	::ZeroMemory(this->szNickname, sizeof(this->szNickname));
}

CNMCharacter::CNMCharacter(NMGameCode gameCode, LPCTSTR pszNickname, LPCTSTR pszLoginID /* = NULL */, UINT32 uVirtualIDCode /* = 0 */)
: CNMSerializable('cr', 1, 1)
{
	wapp::CopyNString(this->szLoginID, pszLoginID, LOGINID_SIZE);
	this->vkey.uGameCode		= gameCode;
	this->vkey.uVirtualIDCode	= uVirtualIDCode;
	wapp::CopyNString(this->szNickname, pszNickname, NICKNAME_SIZE);
}

CNMCharacter::CNMCharacter(NMVirtualKey vkey, LPCTSTR pszNickname, LPCTSTR pszLoginID /* = NULL */)
: CNMSerializable('cr', 1, 1)
{
	wapp::CopyNString(this->szLoginID, pszLoginID, LOGINID_SIZE);
	this->vkey = vkey;
	wapp::CopyNString(this->szNickname, pszNickname, NICKNAME_SIZE);
}

BEGIN_SERIALIZE(CNMCharacter)
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.EncodeString(this->szLoginID);
	ENCODE_VIRTUALKEY(this->vkey);
	encoder.EncodeString(this->szNickname);
//}
END_SERIALIZE()

BEGIN_DESERIALIZE(CNMCharacter)
//{
	UNREFERENCED_PARAMETER(ssStream);

	decoder.DecodeString(this->szLoginID, LOGINID_SIZE);
	DECODE_VIRTUALKEY(this->vkey);
	decoder.DecodeString(this->szNickname, NICKNAME_SIZE);
//}
END_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMNoteInfo2
//////////////////////////////////////////////////////////////////////////

const CNMCharacter CNMNoteInfo2::defaultReceiver;

CNMNoteInfo2::CNMNoteInfo2() : CNMSerializable('n2', 1, 1)
{
	this->uNoteSerial		= 0;
	this->uNoteType			= kNoteType_NULL;
	this->uNoteSubType		= kNoteSubType_NULL;
	::ZeroMemory(&this->stTime, sizeof(this->stTime));
	this->bReadFlag			= FALSE;
	this->bSendFlag			= FALSE;

	::ZeroMemory(this->szMessage, sizeof(this->szMessage));
	::ZeroMemory(this->szArgument, sizeof(this->szArgument));
	::ZeroMemory(this->szCustomData, sizeof(this->szCustomData));
}

CNMNoteInfo2::CNMNoteInfo2( CNMNoteInfo & rhs ) : CNMSerializable( 'n2', 1, 1 )
{
	this->uNoteSerial	= rhs.uNoteSerial;
	this->uNoteType		= static_cast<CNMNoteInfo2::NMNOTETYPE>(rhs.uNoteType);
	this->uNoteSubType	= static_cast<CNMNoteInfo2::NMNOTESUBTYPE>(rhs.uSubType);
	::CopyMemory(&this->stTime, &rhs.stTime, sizeof(SYSTEMTIME));
	this->bReadFlag		= FALSE;
	this->bSendFlag		= FALSE;

	::CopyMemory(this->szMessage, rhs.szMessage, NOTEMSG_SIZE);
	::CopyMemory(this->szArgument, rhs.szArgument, NOTEARG_SIZE);
	::CopyMemory(this->szCustomData, rhs.szCustomData, NOTECUSTOM_SIZE);

	::CopyMemory(this->sender.szLoginID, rhs.szFromLoginID, LOGINID_SIZE);
	this->sender.vkey	= rhs.keyVirtual;
	::CopyMemory(this->sender.szNickname, rhs.szFromNickName, NICKNAME_SIZE);

	CNMNoteInfoHelper::StringToCharacterList(rhs.szTargetID, this->receiverList, rhs.keyVirtual.uGameCode);
}

CNMNoteInfo2 & CNMNoteInfo2::operator = ( CNMNoteInfo & rhs )
{
	this->uNoteSerial	= rhs.uNoteSerial;
	this->uNoteType		= static_cast<CNMNoteInfo2::NMNOTETYPE>(rhs.uNoteType);
	this->uNoteSubType	= static_cast<CNMNoteInfo2::NMNOTESUBTYPE>(rhs.uSubType);
	::CopyMemory(&this->stTime, &rhs.stTime, sizeof(SYSTEMTIME));
	this->bReadFlag		= FALSE;
	this->bSendFlag		= FALSE;

	::CopyMemory(this->szMessage, rhs.szMessage, NOTEMSG_SIZE);
	::CopyMemory(this->szArgument, rhs.szArgument, NOTEARG_SIZE);
	::CopyMemory(this->szCustomData, rhs.szCustomData, NOTECUSTOM_SIZE);

	::CopyMemory(this->sender.szLoginID, rhs.szFromLoginID, LOGINID_SIZE);
	this->sender.vkey	= rhs.keyVirtual;
	::CopyMemory(this->sender.szNickname, rhs.szFromNickName, NICKNAME_SIZE);

	receiverList.clear();
	CNMNoteInfoHelper::StringToCharacterList(rhs.szTargetID, this->receiverList, rhs.keyVirtual.uGameCode);

	return *this;
}

BEGIN_SERIALIZE(CNMNoteInfo2)
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4(this->uNoteSerial);
	encoder.Encode4(this->uNoteType);
	encoder.Encode4(this->uNoteSubType);
	encoder.Encode2(this->stTime.wYear);
	encoder.Encode1(static_cast<UINT8>(this->stTime.wMonth));
	encoder.Encode1(static_cast<UINT8>(this->stTime.wDay));
	encoder.Encode1(static_cast<UINT8>(this->stTime.wHour));
	encoder.Encode1(static_cast<UINT8>(this->stTime.wMinute));
	encoder.Encode1(static_cast<UINT8>(this->stTime.wSecond));
	encoder.Encode1(static_cast<UINT8>(this->bReadFlag));
	encoder.Encode1(static_cast<UINT8>(this->bSendFlag));

	encoder.EncodeString(this->szMessage);
	encoder.EncodeString(this->szArgument);
	encoder.EncodeString(this->szCustomData);

	OBJECT_ENCODE(this->sender);
	LIST_ENCODE(NMCharacterList, this->receiverList);
//}
END_SERIALIZE()

BEGIN_DESERIALIZE(CNMNoteInfo2)
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uNoteSerial		= decoder.Decode4();
	this->uNoteType			= static_cast<NMNOTETYPE>(decoder.Decode4());
	this->uNoteSubType		= static_cast<NMNOTESUBTYPE>(decoder.Decode4());
	this->stTime.wYear		= decoder.Decode2();
	this->stTime.wMonth		= decoder.Decode1();
	this->stTime.wDay		= decoder.Decode1();
	this->stTime.wHour		= decoder.Decode1();
	this->stTime.wMinute	= decoder.Decode1();
	this->stTime.wSecond	= decoder.Decode1();
	this->bReadFlag			= decoder.Decode1();
	this->bSendFlag			= decoder.Decode1();

	decoder.DecodeString(this->szMessage, NOTEMSG_SIZE);
	decoder.DecodeString(this->szArgument, NOTEARG_SIZE);
	decoder.DecodeString(this->szCustomData, NOTECUSTOM_SIZE);

	OBJECT_DECODE(this->sender);
	LIST_DECODE(NMCharacterList, this->receiverList);
//}
END_DESERIALIZE()

void CNMNoteInfo2::SetReceiver( CNMCharacter const & receiver )
{
	if ( !this->receiverList.empty() )
	{
		this->receiverList[0] = receiver;
	}
	else
	{
		this->receiverList.push_back( receiver );
	}
}

//////////////////////////////////////////////////////////////////////////
//	ID list <-> Character list
//////////////////////////////////////////////////////////////////////////

namespace CNMNoteInfoHelper
{
	BOOL SafeAppendString(LPTSTR pszBuffer, size_t nBufferSize, LPCTSTR pszAppend, size_t & nBufferIndex)
	{
		if (pszBuffer && pszAppend)
		{
			size_t nAppendSize = _tcslen(pszAppend);
			if (nBufferIndex + nAppendSize < nBufferSize)
			{
				_tcscpy(pszBuffer + nBufferIndex, pszAppend);
				nBufferIndex += nAppendSize;
				return TRUE;
			}
		}
		return FALSE;
	}

	BOOL CharacterListToString(NMCharacterList const & characterList, LPTSTR pszBuffer, size_t nBufferSize)
	{
		size_t nBufferIndex = 0;

		for(NMCharacterList::const_iterator i = characterList.begin(), z = characterList.end(); i != z; ++i)
		{
			if ((0 < nBufferIndex) && !SafeAppendString(pszBuffer, nBufferSize, _T(";"), nBufferIndex))
			{
				return FALSE;
			}

			if (!SafeAppendString(pszBuffer, nBufferSize, i->GetName(), nBufferIndex))
			{
				return FALSE;
			}
		}

		return (0 < nBufferIndex);
	}

	typedef std::basic_string<TCHAR> CNMString;
	typedef std::vector<CNMString>	 CNMStringList;

	CNMStringList tokenize(const CNMString& str,const CNMString& delimiters)
	{
		CNMStringList tokens;

		// skip delimiters at beginning.
		CNMString::size_type lastPos = str.find_first_not_of(delimiters, 0);

		// find first "non-delimiter".
		CNMString::size_type pos = str.find_first_of(delimiters, lastPos);

		while (CNMString::npos != pos || CNMString::npos != lastPos)
		{
			// found a token, add it to the vector.
			CNMString strToken = str.substr(lastPos, pos - lastPos);
			strToken.erase( std::remove( strToken.begin(), strToken.end(), _T(' ') ), strToken.end() );
			strToken.erase( std::remove( strToken.begin(), strToken.end(), _T('\t') ), strToken.end() );
			strToken.erase( std::remove( strToken.begin(), strToken.end(), _T('\n') ), strToken.end() );
			strToken.erase( std::remove( strToken.begin(), strToken.end(), _T('\r') ), strToken.end() );
			if ( !strToken.empty() )
			{
				tokens.push_back( strToken );
			}

			// skip delimiters.  Note the "not_of"
			lastPos = str.find_first_not_of(delimiters, pos);

			// find next "non-delimiter"
			pos = str.find_first_of(delimiters, lastPos);
		}

		return tokens;
	}

	BOOL StringToCharacterList(LPCTSTR pszBuffer, NMCharacterList & characterList, NMGameCode gameCode)
	{
		static const CNMString strDelimiters(_T(";"));
		BOOL bResult = FALSE;

		characterList.clear();

		CNMString strText(pszBuffer);

		CNMStringList aToken = tokenize( strText, strDelimiters );
		
		for ( CNMStringList::iterator i = aToken.begin(), z = aToken.end(); i != z; ++i )
		{
			CNMCharacter character;

			character.vkey.uGameCode = gameCode;
			wapp::CopyNString( character.szNickname, i->c_str(), NICKNAME_SIZE );

			characterList.push_back(character);

			bResult = TRUE;
		}

		return bResult;
	}
};

//////////////////////////////////////////////////////////////////////////
//	CNMReportInfo
//////////////////////////////////////////////////////////////////////////

CNMReportInfo::CNMReportInfo( void ) : CNMSerializable( 'ri', 1, 1 )
{
	this->uType				= kReportType_NULL;
	this->uSubType			= kReportSubType_NULL;
	this->uGameCode			= kGameCode_NULL;
	this->uAppliedGameCode	= kGameCode_NULL;
	ZeroMemory( this->szTargetId, sizeof( this->szTargetId ) );
	ZeroMemory( this->szReason, sizeof( this->szReason ) );
	ZeroMemory( this->szEvidence, sizeof( this->szEvidence ) );
}

BEGIN_SERIALIZE( CNMReportInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode1( static_cast<UINT8>( this->uType ) );
	encoder.Encode1( static_cast<UINT8>( this->uSubType ) );
	encoder.Encode4( this->uGameCode );
	encoder.Encode4( this->uAppliedGameCode );
	encoder.EncodeString( this->szTargetId );
	encoder.EncodeString( this->szReason );
	encoder.EncodeString( this->szEvidence );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMReportInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uType				= ( NMREPORTTYPE )decoder.Decode1();
	this->uSubType			= ( NMREPORTSUBTYPE )decoder.Decode1();
	this->uGameCode			= decoder.Decode4();
	this->uAppliedGameCode	= decoder.Decode4();
	decoder.DecodeString( this->szTargetId, LOGINID_SIZE );
	decoder.DecodeString( this->szReason, 4000 );
	decoder.DecodeString( this->szEvidence, 20000 );
//}
END_DESERIALIZE()

CNMChatMessage::CNMChatMessage( void ) : CNMSerializable( 'cm', 1, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyVirtual );
	::ZeroMemory( this->szSenderName, sizeof( this->szSenderName ) );
	::ZeroMemory( this->szChatHeader, sizeof( this->szChatHeader ) );
	::ZeroMemory( this->szFontHeader, sizeof( this->szFontHeader ) );
	::ZeroMemory( this->szChatMessage, sizeof( this->szChatMessage ) );
}

BEGIN_SERIALIZE( CNMChatMessage )
//{
	UNREFERENCED_PARAMETER(ssStream);

	ENCODE_VIRTUALKEY( this->keyVirtual );
	OBJECT_ENCODE( this->aiAvatarItemInfo );
	encoder.EncodeString( this->szSenderName );
	encoder.EncodeString( this->szChatHeader );
	encoder.EncodeString( this->szFontHeader );
	encoder.EncodeString( this->szChatMessage );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMChatMessage )
	DECODE_VIRTUALKEY( this->keyVirtual );
	OBJECT_DECODE( this->aiAvatarItemInfo );
	decoder.DecodeString( this->szSenderName, CHATSENDER_SIZE );
	decoder.DecodeString( this->szChatHeader, CHATHDR_SIZE );
	decoder.DecodeString( this->szFontHeader, CHATHDR_SIZE );
	decoder.DecodeString( this->szChatMessage, CHATMSG_SIZE );
END_DESERIALIZE()

CNMFileEvent::CNMFileEvent( void ) : CNMSerializable( 'fe', 1, 1 )
{
	this->uEventId		= 0;
	::ZeroMemory( this->szFileName, sizeof( this->szFileName ) );
	this->uStatus		= kFileStatus_Unknown;
	this->uParam1		= 0;
	this->uParam2		= 0;
	this->bSendFlag		= FALSE;
}

BEGIN_SERIALIZE( CNMFileEvent )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uEventId );
	encoder.EncodeString( this->szFileName );
	encoder.Encode4( this->uStatus );
	encoder.Encode4( this->uParam1 );
	encoder.Encode4( this->uParam2 );
	encoder.Encode1( static_cast<UINT8>( this->bSendFlag ) );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMFileEvent )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uEventId		= decoder.Decode4();
	decoder.DecodeString( this->szFileName, FILENAME_SIZE );
	this->uStatus		= (NMFILESTATUS) decoder.Decode4();
	this->uParam1		= decoder.Decode4();
	this->uParam2		= decoder.Decode4();
	this->bSendFlag		= decoder.Decode1();
//}
END_DESERIALIZE()

CNMFilePath::CNMFilePath( void ) : CNMSerializable( 'fp', 1, 1 )
{
	::ZeroMemory( this->szValue, sizeof( this->szValue ) );
}

BEGIN_SERIALIZE( CNMFilePath )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.EncodeString( this->szValue );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMFilePath )
//{
	UNREFERENCED_PARAMETER(ssStream);

	decoder.DecodeString( this->szValue, MAX_PATH );
//}
END_DESERIALIZE()

CNMMyGameInfo::CNMMyGameInfo( void ): CNMSerializable( 'mg', 1, 1 )
{
	::ZeroMemory( this->aGameCodes, sizeof( this->aGameCodes ) );
}

BEGIN_SERIALIZE( CNMMyGameInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	for ( _UInt32_ i = 0; i < MYGAME_COUNT; ++i )
	{
		encoder.Encode4( this->aGameCodes[ i ] );
	}
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMMyGameInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	for ( _UInt32_ i = 0; i < MYGAME_COUNT; ++i )
	{
		this->aGameCodes[ i ] = decoder.Decode4();
	}
//}
END_DESERIALIZE()

#ifndef	_NMSERVER
CNMGuildInfo::CNMGuildInfo( void ) : CNMSerializable( 'gi', 1, 1 )
{
	this->uSerialNo			= 0;
	::ZeroMemory( this->szName, sizeof( this->szName ) );
	::ZeroMemory( this->szId, sizeof( this->szId ) );
	::ZeroMemory( this->szHomeUrl, sizeof( this->szHomeUrl ) );
	this->uGameCode			= 0;
	this->uLevel			= 0;
	this->uMemberCount		= 0;
	this->uType				= kGuildType_Unknown;
	this->bIsMaster			= FALSE;
	this->bHasNewContent	= FALSE;
}

BEGIN_SERIALIZE( CNMGuildInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uSerialNo );
	encoder.EncodeString( this->szName );
	encoder.EncodeString( this->szId );
	encoder.EncodeString( this->szHomeUrl );
	encoder.Encode4( this->uGameCode );
	encoder.Encode4( this->uLevel );
	encoder.Encode4( this->uMemberCount );
	encoder.Encode4( this->uType );
	encoder.Encode1( static_cast<UINT8>( this->bIsMaster ) );
	encoder.Encode1( static_cast<UINT8>( this->bHasNewContent ) );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGuildInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uSerialNo			= decoder.Decode4();
	decoder.DecodeString( this->szName, GUILDNAME_SIZE );
	decoder.DecodeString( this->szId, GUILDID_SIZE );
	decoder.DecodeString( this->szHomeUrl, GUILDHOME_SIZE );
	this->uGameCode			= decoder.Decode4();
	this->uLevel			= decoder.Decode4();
	this->uMemberCount		= decoder.Decode4();
	this->uType				= (NMGUILDTYPE) decoder.Decode4();
	this->bIsMaster			= decoder.Decode1();
	this->bHasNewContent	= decoder.Decode1();
//}
END_DESERIALIZE()

CNMGuildListInfo::CNMGuildListInfo( void ) : CNMSerializable( 'ga', 1, 1 )
{
}

BEGIN_SERIALIZE( CNMGuildListInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_ENCODE( NMGuildInfoList, this->aGuilds );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGuildListInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_DECODE( NMGuildInfoList, this->aGuilds );
//}
END_DESERIALIZE()
#endif	// _NMSERVER

CNMGuildInfoEx::CNMGuildInfoEx( void ) : CNMSerializable( 'ge', 1, 1 )
{
	this->uGuildKey			= 0;

	::ZeroMemory( this->szName, sizeof( this->szName ) );
	::ZeroMemory( this->szId, sizeof( this->szId ) );
	this->uGameCode			= 0;

	::ZeroMemory( this->szHomeUrl, sizeof( this->szHomeUrl ) );
	this->uLevel			= 0;
	this->uMemberCount		= 0;
	this->bHasNewContent	= FALSE;

	this->bIsMaster			= FALSE;
}

BEGIN_SERIALIZE( CNMGuildInfoEx )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode8( this->uGuildKey );

	encoder.EncodeString( this->szName );
	encoder.EncodeString( this->szId );
	encoder.Encode4( this->uGameCode );

	encoder.EncodeString( this->szHomeUrl );
	encoder.Encode4( this->uLevel );
	encoder.Encode4( this->uMemberCount );
	encoder.Encode1( static_cast<UINT8>( this->bHasNewContent ) );

	encoder.Encode1( static_cast<UINT8>( this->bIsMaster ) );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGuildInfoEx )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uGuildKey			= decoder.Decode8();

	decoder.DecodeString( this->szName, GUILDNAME_SIZE );
	decoder.DecodeString( this->szId, GUILDID_SIZE );
	this->uGameCode			= decoder.Decode4();

	decoder.DecodeString( this->szHomeUrl, GUILDHOME_SIZE );
	this->uLevel			= decoder.Decode4();
	this->uMemberCount		= decoder.Decode4();
	this->bHasNewContent	= decoder.Decode1();

	this->bIsMaster			= decoder.Decode1();
//}
END_DESERIALIZE()

CNMGuildListExInfo::CNMGuildListExInfo( void ) : CNMSerializable( 'el', 1, 1 )
{
}

BEGIN_SERIALIZE( CNMGuildListExInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_ENCODE( NMGuildInfoExList, this->aGuilds );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGuildListExInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_DECODE( NMGuildInfoExList, this->aGuilds );
//}
END_DESERIALIZE()

CNMFindUserRequestInfo::CNMFindUserRequestInfo() : CNMSerializable( 'fr', 1, 1 )
{
	this->uToken			= 0;
	ZeroMemory( this->szName, sizeof( this->szName ) );
	this->uAgeMin			= 0;
	this->uAgeMax			= 0;
	this->uSex				= kSex_Unknown;
	this->uRegion			= 0;
	this->uSchoolCode		= 0;
	this->bOnlineUserOnly	= FALSE;
}

BEGIN_SERIALIZE( CNMFindUserRequestInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uToken );
	encoder.EncodeString( this->szName );
	encoder.Encode4( this->uAgeMin );
	encoder.Encode4( this->uAgeMax );
	encoder.Encode4( this->uSex );
	encoder.Encode4( this->uRegion );
	encoder.Encode4( this->uSchoolCode );
	encoder.Encode1( static_cast<UINT8>( this->bOnlineUserOnly ) );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMFindUserRequestInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uToken			= decoder.Decode4();
	decoder.DecodeString( this->szName, NXNAME_SIZE );
	this->uAgeMin			= decoder.Decode4();
	this->uAgeMax			= decoder.Decode4();
	this->uSex				= (NMSEXTYPE) decoder.Decode4();
	this->uRegion			= decoder.Decode4();
	this->uSchoolCode		= decoder.Decode4();
	this->bOnlineUserOnly	= decoder.Decode1();
//}
END_DESERIALIZE()

CNMFindUserInfo::CNMFindUserInfo( void ) : CNMSerializable( 'fu', 1, 1 )
{
	this->uIdCode		= 0;
	ZeroMemory( this->szId, sizeof( this->szId ) );
	ZeroMemory( this->szName, sizeof( this->szName ) );
	ZeroMemory( this->szNickName, sizeof( this->szNickName ) );
	this->uAge			= 0;
	this->uSex			= kSex_Unknown;
	this->uAreaCode		= 0;
	this->uSchoolCode	= 0;
	this->uStatus		= kStatus_Unknown;
}

BEGIN_SERIALIZE( CNMFindUserInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode8( this->uIdCode );
	encoder.EncodeString( this->szId );
	encoder.EncodeString( this->szName );
	encoder.EncodeString( this->szNickName );
	encoder.Encode4( this->uAge );
	encoder.Encode4( this->uSex );
	encoder.Encode4( this->uAreaCode );
	encoder.Encode4( this->uSchoolCode );
	OBJECT_ENCODE( this->aiAvatarInfo );
	encoder.Encode4( this->uStatus );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMFindUserInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uIdCode		= decoder.Decode8();
	decoder.DecodeString( this->szId, LOGINID_SIZE );
	decoder.DecodeString( this->szName, NXNAME_SIZE );
	decoder.DecodeString( this->szNickName, NICKNAME_SIZE );
	this->uAge			= decoder.Decode4();
	this->uSex			= (NMSEXTYPE) decoder.Decode4();
	this->uAreaCode		= decoder.Decode4();
	this->uSchoolCode	= decoder.Decode4();
	OBJECT_DECODE( this->aiAvatarInfo );
	this->uStatus		= (NMUSERSTATUS) decoder.Decode4();
//}
END_DESERIALIZE()

CNMGuildMemberInfo::CNMGuildMemberInfo( void ) : CNMSerializable( 'gm', 1, 1 )
{
	this->uIdCode			= 0;
	::ZeroMemory( this->szLoginId, sizeof( this->szLoginId ) );
	::ZeroMemory( this->szNickName, sizeof( this->szNickName ) );
	this->bIsGameOnline		= FALSE;
	this->uLevel			= 0;
	this->uGameLevel		= 0;
	this->uVirtualIdCode	= 1;
}

BEGIN_SERIALIZE( CNMGuildMemberInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode8( this->uIdCode );
	encoder.EncodeString( this->szLoginId );
	encoder.EncodeString( this->szNickName );
	OBJECT_ENCODE( this->aiAvatarInfo );
	encoder.Encode1( static_cast<UINT8>( this->bIsGameOnline ) );
	encoder.Encode1( this->uLevel );
	encoder.Encode4( this->uGameLevel );
	encoder.Encode4( this->uVirtualIdCode );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGuildMemberInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uIdCode			= decoder.Decode8();
	decoder.DecodeString( this->szLoginId, LOGINID_SIZE );
	decoder.DecodeString( this->szNickName, NICKNAME_SIZE );
	OBJECT_DECODE( this->aiAvatarInfo );
	this->bIsGameOnline		= decoder.Decode1();
	this->uLevel			= decoder.Decode1();
	this->uGameLevel		= decoder.Decode4();
	this->uVirtualIdCode	= decoder.Decode4();
//}
END_DESERIALIZE()

CNMGuildOnlineInfoEx::CNMGuildOnlineInfoEx( void ) : CNMSerializable( 'oe', 1, 1 )
{
	this->uGuildKey				= 0;
	this->uChatRoomSerialNo		= 0;
	this->uChatRoomMemberCount	= 0;
	::ZeroMemory( this->szChatServerIpAddr, sizeof( this->szChatServerIpAddr ) );
	this->uChatServerPort		= 0;
}

BEGIN_SERIALIZE( CNMGuildOnlineInfoEx )
//{
	UNREFERENCED_PARAMETER(ssStream);

	NMGuildMemberList::iterator		iterBegin, iterEnd, iterCur;

	encoder.Encode8( this->uGuildKey );
	encoder.Encode4( this->uChatRoomSerialNo );
	encoder.Encode4( this->uChatRoomMemberCount );
	encoder.EncodeString( this->szChatServerIpAddr );
	encoder.Encode2( this->uChatServerPort );
	
	encoder.Encode4( static_cast<_UInt32_>( this->aOnlineGuildMembers.size() ) );

	iterBegin	= this->aOnlineGuildMembers.begin();
	iterEnd		= this->aOnlineGuildMembers.end();
	for ( iterCur = iterBegin; iterCur != iterEnd; ++iterCur )
	{
#ifdef _NMSERVER
		OBJECT_ENCODE( (*iterCur).second );
#else
		OBJECT_ENCODE( (*iterCur) );
#endif
	}
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGuildOnlineInfoEx )
//{
	UNREFERENCED_PARAMETER(ssStream);

	_UInt32_	uSize;

	this->uGuildKey				= decoder.Decode8();
	this->uChatRoomSerialNo		= decoder.Decode4();
	this->uChatRoomMemberCount	= decoder.Decode4();
	decoder.DecodeString( this->szChatServerIpAddr, 32 );
	this->uChatServerPort		= decoder.Decode2();
	
	uSize						= decoder.Decode4();

	this->aOnlineGuildMembers.clear();

	for( _UInt32_ i = 0; i < uSize; ++i )
	{
		CNMGuildMemberInfo		gmiMemberInfo;
		
		OBJECT_DECODE( gmiMemberInfo );

#ifdef _NMSERVER
		this->aOnlineGuildMembers.insert(
			NMGuildMemberList::value_type( 
				gmiMemberInfo.uIdCode, 
				gmiMemberInfo ) );
#else
		this->aOnlineGuildMembers.push_back( gmiMemberInfo );
#endif
	}
//}
END_DESERIALIZE();

CNMGuildOnlineInfo::CNMGuildOnlineInfo( void ) : CNMSerializable( 'go', 1, 1 )
{
	this->uGuildSerialNo		= 0;
	this->uChatRoomSerialNo		= 0;
	this->uChatRoomMemberCount	= 0;
	::ZeroMemory( this->szChatServerIpAddr, sizeof( this->szChatServerIpAddr ) );
	this->uChatServerPort		= 0;
}

BEGIN_SERIALIZE( CNMGuildOnlineInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	NMGuildMemberList::iterator		iterBegin, iterEnd, iterCur;

	encoder.Encode4( this->uGuildSerialNo );
	encoder.Encode4( this->uChatRoomSerialNo );
	encoder.Encode4( this->uChatRoomMemberCount );
	encoder.EncodeString( this->szChatServerIpAddr );
	encoder.Encode2( this->uChatServerPort );
	
	encoder.Encode4( static_cast<_UInt32_>( this->aOnlineGuildMembers.size() ) );

	iterBegin	= this->aOnlineGuildMembers.begin();
	iterEnd		= this->aOnlineGuildMembers.end();
	for ( iterCur = iterBegin; iterCur != iterEnd; ++iterCur )
	{
#ifdef _NMSERVER
		OBJECT_ENCODE( (*iterCur).second );
#else
		OBJECT_ENCODE( (*iterCur) );
#endif
	}
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGuildOnlineInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	_UInt32_	uSize;

	this->uGuildSerialNo		= decoder.Decode4();
	this->uChatRoomSerialNo		= decoder.Decode4();
	this->uChatRoomMemberCount	= decoder.Decode4();
	decoder.DecodeString( this->szChatServerIpAddr, 32 );
	this->uChatServerPort		= decoder.Decode2();
	
	uSize						= decoder.Decode4();

	this->aOnlineGuildMembers.clear();

	for( _UInt32_ i = 0; i < uSize; ++i )
	{
		CNMGuildMemberInfo		gmiMemberInfo;
		
		OBJECT_DECODE( gmiMemberInfo );

#ifdef _NMSERVER
		this->aOnlineGuildMembers.insert(
			NMGuildMemberList::value_type( 
				gmiMemberInfo.uIdCode, 
				gmiMemberInfo ) );
#else
		this->aOnlineGuildMembers.push_back( gmiMemberInfo );
#endif
	}
//}
END_DESERIALIZE();

CNMGuildOnlineInfo& CNMGuildOnlineInfo::operator=( CNMGuildOnlineInfoEx& infoEx )
{
	this->uGuildSerialNo		= GET_GUILDSERIAL( infoEx.uGuildKey );
	this->uChatRoomSerialNo		= infoEx.uChatRoomSerialNo;
	this->uChatRoomMemberCount	= infoEx.uChatRoomMemberCount;
	wapp::CopyNString( this->szChatServerIpAddr, infoEx.szChatServerIpAddr, 32 );
	this->uChatServerPort		= infoEx.uChatServerPort;
	this->aOnlineGuildMembers		= infoEx.aOnlineGuildMembers;

	return *this;
}

CNMGuildOnlineInfo::operator CNMGuildOnlineInfoEx( void )
{
	CNMGuildOnlineInfoEx	infoEx;

	infoEx.uGuildKey				= this->uGuildSerialNo;
	infoEx.uChatRoomSerialNo		= this->uChatRoomSerialNo;
	infoEx.uChatRoomMemberCount		= this->uChatRoomMemberCount;
	wapp::CopyNString( infoEx.szChatServerIpAddr, this->szChatServerIpAddr, 32 );
	infoEx.uChatServerPort			= this->uChatServerPort;
	infoEx.aOnlineGuildMembers		= this->aOnlineGuildMembers;

	return infoEx;
}

CNMSessionInfo::CNMSessionInfo( void ) : CNMSerializable( 'si', 1, 1 )
{
	this->uType					= kSessionType_General;
	INITIALIZE_VIRTUALKEY( this->keyVirtuality );
	this->bPassive				= FALSE;

	this->uInviteMode			= 0;
	this->uMode					= 0;
	this->uId					= 0;
	INITIALIZE_FRIENDCODE( this->fcAssocFriend );

	this->uStatus				= 0;
	this->uServingProcessID		= 0;
}

BEGIN_SERIALIZE( CNMSessionInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uType );
	ENCODE_VIRTUALKEY( this->keyVirtuality );
	encoder.Encode1( static_cast<UINT8>( this->bPassive ) );

	encoder.Encode4( this->uInviteMode );
	encoder.Encode4( this->uMode );
	encoder.Encode8( this->uId );
	ENCODE_FRIENDCODE( this->fcAssocFriend );

	encoder.Encode4( this->uStatus );
	encoder.Encode4( this->uServingProcessID );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMSessionInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uType					= ( NMSESSIONTYPE )decoder.Decode4();
	DECODE_VIRTUALKEY( this->keyVirtuality );
	this->bPassive				= decoder.Decode1();

	this->uInviteMode			= decoder.Decode4();
	this->uMode					= decoder.Decode4();
	this->uId					= decoder.Decode8();
	DECODE_FRIENDCODE( this->fcAssocFriend );

	this->uStatus				= decoder.Decode4();
	this->uServingProcessID		= decoder.Decode4();
//}
END_DESERIALIZE()

CNMSessionMemberInfo::CNMSessionMemberInfo( void ) : CNMSerializable( 'sm', 1, 1 )
{
	this->uIDCode		= 0;
	::ZeroMemory( this->szLoginID, sizeof( this->szLoginID ) );
	::ZeroMemory( this->szNickName, sizeof( this->szNickName ) );
	INITIALIZE_VIRTUALKEY( this->keyVirtual );
	::ZeroMemory( this->szWord, sizeof( this->szWord ) );
	this->uStatus		= kStatus_Unknown;
}

BEGIN_SERIALIZE( CNMSessionMemberInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode8( this->uIDCode );
	encoder.EncodeString( this->szLoginID );
	encoder.EncodeString( this->szWord );

	ENCODE_VIRTUALKEY( this->keyVirtual );
	encoder.EncodeString( this->szNickName );
	encoder.Encode4( this->uStatus );
	OBJECT_ENCODE( this->aiAvatarInfo );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMSessionMemberInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uIDCode		= decoder.Decode8();
	decoder.DecodeString( this->szLoginID, LOGINID_SIZE );
	decoder.DecodeString( this->szWord, WORD_SIZE );

	DECODE_VIRTUALKEY( this->keyVirtual );
	decoder.DecodeString( this->szNickName, NICKNAME_SIZE );
	this->uStatus		= decoder.Decode4();
	OBJECT_DECODE( this->aiAvatarInfo );
//}
END_DESERIALIZE()

CNMSessionMemberList::CNMSessionMemberList( void ) : CNMSerializable( 'ml', 1, 1 )
{
}

BEGIN_SERIALIZE( CNMSessionMemberList )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_ENCODE( NMSessionMemberList, this->aMembers );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMSessionMemberList )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_DECODE( NMSessionMemberList, this->aMembers );
//}
END_DESERIALIZE()

CNMChatRoomMemberInfo::CNMChatRoomMemberInfo( void ) : CNMRealUserInfo()
{
	++this->m_uObjectSubCode;

	::ZeroMemory( this->szTel, sizeof( this->szTel ) );
	INITIALIZE_VIRTUALKEY( this->keyVirtual );
	this->uLevel		= 0;
	::ZeroMemory( this->szLevelName, sizeof( this->szLevelName ) );
	this->uFlag			= 0;
	this->uIpAddr		= 0;
}

BEGIN_SERIALIZE( CNMChatRoomMemberInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_SERIALIZE( CNMRealUserInfo );

	encoder.EncodeString( this->szTel );
	ENCODE_VIRTUALKEY( this->keyVirtual );
	encoder.Encode4( this->uLevel );
	encoder.EncodeString( this->szLevelName );
	encoder.Encode4( this->uFlag );
	encoder.Encode4( this->uIpAddr );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMChatRoomMemberInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	PRIOR_DESERIALIZE( CNMRealUserInfo );

	decoder.DecodeString( this->szTel, NXTEL_SIZE );
	DECODE_VIRTUALKEY( this->keyVirtual );
	this->uLevel		= decoder.Decode4();
	decoder.DecodeString( this->szLevelName, GUILDLEVEL_SIZE );
	this->uFlag			= decoder.Decode4();
	this->uIpAddr		= decoder.Decode4();
//}
END_DESERIALIZE()

CNMChatRoomMemberList::CNMChatRoomMemberList( void ) : CNMSerializable( 'ma', 1, 1 )
{
}

BEGIN_SERIALIZE( CNMChatRoomMemberList )
//{
	UNREFERENCED_PARAMETER(ssStream);

	_UInt32_					uSize = static_cast<_UInt32_>( this->aMemberList.size() );

	encoder.Encode4( uSize );
	
	for ( _UInt32_ i = 0; i < uSize; ++i )
	{
		OBJECT_ENCODE( this->aMemberList[ i ] );
	}
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMChatRoomMemberList )
//{
	UNREFERENCED_PARAMETER(ssStream);

	_UInt32_					uSize;

	uSize		= decoder.Decode4();

	this->aMemberList.clear();

	for ( _UInt32_ i = 0; i < uSize; ++i )
	{
		CNMChatRoomMemberInfo	memberInfo;

		OBJECT_DECODE( memberInfo );
		
		this->aMemberList.push_back( memberInfo );
	}
//}
END_DESERIALIZE()

CNMGuildMemberLevelInfo::CNMGuildMemberLevelInfo( void ) : CNMSerializable( 'me', 1, 1 )
{
	this->uLevel		= 0;
	ZeroMemory( this->szName, sizeof( this->szName ) );
}

BEGIN_SERIALIZE( CNMGuildMemberLevelInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode1( this->uLevel );
	encoder.EncodeString( this->szName );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGuildMemberLevelInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uLevel		= decoder.Decode1();
	decoder.DecodeString( this->szName, GUILDLEVEL_SIZE );
//}
END_DESERIALIZE()

CNMChatRoomInfoEx::CNMChatRoomInfoEx( void ) : CNMSerializable( 're', 1, 1 )
{
	this->uRoomType			= 0;
	this->uGuildKey			= 0;
	this->uRoomSerialNo		= 0;
	this->uGameCode			= 0;
	ZeroMemory( this->szGuildTitle, sizeof( this->szGuildTitle ) );
	ZeroMemory( this->szRoomTitle, sizeof( this->szRoomTitle ) );
	this->uLimitLevel		= 0;
	this->uMaxSize			= 0;
	this->uFlag				= 0;
	ZeroMemory( this->szPassword, sizeof( this->szPassword ) );
	this->uOwnerIdCode		= 0;
	this->uStatus			= 0;
	this->uRequestProcId	= 0;
}

BEGIN_SERIALIZE( CNMChatRoomInfoEx )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uRoomType );
	encoder.Encode8( this->uGuildKey );
	encoder.Encode4( this->uRoomSerialNo );
	encoder.Encode4( this->uGameCode );
	encoder.EncodeString( this->szGuildTitle );
	encoder.EncodeString( this->szRoomTitle );
	encoder.Encode4( this->uLimitLevel );
	encoder.Encode4( this->uMaxSize );
	encoder.Encode4( this->uFlag );
	encoder.EncodeString( this->szPassword );
	encoder.Encode8( this->uOwnerIdCode );
	encoder.Encode4( this->uStatus );
	encoder.Encode4( this->uRequestProcId );
	LIST_ENCODE( NMGuildMemberLevelList, this->aLevelInfo );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMChatRoomInfoEx )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uRoomType			= decoder.Decode4();
	this->uGuildKey			= decoder.Decode8();
	this->uRoomSerialNo		= decoder.Decode4();
	this->uGameCode			= decoder.Decode4();
	decoder.DecodeString( this->szGuildTitle, GUILDNAME_SIZE );
	decoder.DecodeString( this->szRoomTitle, ROOMTITLE_SIZE );
	this->uLimitLevel		= decoder.Decode4();
	this->uMaxSize			= decoder.Decode4();
	this->uFlag				= decoder.Decode4();
	decoder.DecodeString( this->szPassword, ROOMPWD_SIZE );
	this->uOwnerIdCode		= decoder.Decode8();
	this->uStatus			= decoder.Decode4();
	this->uRequestProcId	= decoder.Decode4();
	LIST_DECODE( NMGuildMemberLevelList, this->aLevelInfo );
//}
END_DESERIALIZE()

#ifndef	_NMSERVER
CNMChatRoomInfo::CNMChatRoomInfo( void ) : CNMSerializable( 'ri', 1, 1 )
{
	this->uRoomType			= 0;
	this->uClubSerialNo		= 0;
	this->uRoomSerialNo		= 0;
	ZeroMemory( this->szClubTitle, sizeof( this->szClubTitle ) );
	ZeroMemory( this->szRoomTitle, sizeof( this->szRoomTitle ) );
	this->uLimitLevel		= 0;
	this->uMaxSize			= 0;
	this->uFlag				= 0;
	ZeroMemory( this->szPassword, sizeof( this->szPassword ) );
	this->uOwnerIdCode		= 0;
	this->uStatus			= 0;
	this->uRequestProcId	= 0;
	ZeroMemory( this->szLevel1Name, sizeof( this->szLevel1Name ) );
	ZeroMemory( this->szLevel2Name, sizeof( this->szLevel2Name ) );
	ZeroMemory( this->szLevel3Name, sizeof( this->szLevel3Name ) );
	ZeroMemory( this->szLevel4Name, sizeof( this->szLevel4Name ) );
	ZeroMemory( this->szLevel5Name, sizeof( this->szLevel5Name ) );
}

BEGIN_SERIALIZE( CNMChatRoomInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uRoomType );
	encoder.Encode4( this->uClubSerialNo );
	encoder.Encode4( this->uRoomSerialNo );
	encoder.EncodeString( this->szClubTitle );
	encoder.EncodeString( this->szRoomTitle );
	encoder.Encode4( this->uLimitLevel );
	encoder.Encode4( this->uMaxSize );
	encoder.Encode4( this->uFlag );
	encoder.EncodeString( this->szPassword );
	encoder.Encode8( this->uOwnerIdCode );
	encoder.Encode4( this->uStatus );
	encoder.Encode4( this->uRequestProcId );
	encoder.EncodeString( this->szLevel1Name );
	encoder.EncodeString( this->szLevel2Name );
	encoder.EncodeString( this->szLevel3Name );
	encoder.EncodeString( this->szLevel4Name );
	encoder.EncodeString( this->szLevel5Name );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMChatRoomInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uRoomType			= decoder.Decode4();
	this->uClubSerialNo		= decoder.Decode4();
	this->uRoomSerialNo		= decoder.Decode4();
	decoder.DecodeString( this->szClubTitle, GUILDNAME_SIZE );
	decoder.DecodeString( this->szRoomTitle, ROOMTITLE_SIZE );
	this->uLimitLevel		= decoder.Decode4();
	this->uMaxSize			= decoder.Decode4();
	this->uFlag				= decoder.Decode4();
	decoder.DecodeString( this->szPassword, ROOMPWD_SIZE );
	this->uOwnerIdCode		= decoder.Decode8();
	this->uStatus			= decoder.Decode4();
	this->uRequestProcId	= decoder.Decode4();
	decoder.DecodeString( this->szLevel1Name, GUILDLEVEL_SIZE );
	decoder.DecodeString( this->szLevel2Name, GUILDLEVEL_SIZE );
	decoder.DecodeString( this->szLevel3Name, GUILDLEVEL_SIZE );
	decoder.DecodeString( this->szLevel4Name, GUILDLEVEL_SIZE );
	decoder.DecodeString( this->szLevel5Name, GUILDLEVEL_SIZE );
//}
END_DESERIALIZE()

CNMChatRoomInfo& CNMChatRoomInfo::operator=( CNMChatRoomInfoEx& infoEx )
{
	this->uRoomType			= infoEx.uRoomType;
	this->uClubSerialNo		= GET_GUILDSERIAL( infoEx.uGuildKey );
	this->uRoomSerialNo		= infoEx.uRoomSerialNo;
	this->uGameCode			= infoEx.uGameCode;
	wapp::CopyNString( this->szClubTitle, infoEx.szGuildTitle, GUILDNAME_SIZE );
	wapp::CopyNString( this->szRoomTitle, infoEx.szRoomTitle, ROOMTITLE_SIZE );
	this->uLimitLevel		= infoEx.uLimitLevel;
	this->uMaxSize			= infoEx.uMaxSize;
	this->uFlag				= infoEx.uFlag;
	wapp::CopyNString( this->szPassword, infoEx.szPassword, ROOMPWD_SIZE );
	this->uOwnerIdCode		= infoEx.uOwnerIdCode;
	this->uStatus			= infoEx.uStatus;
	this->uRequestProcId	= infoEx.uRequestProcId;

	ITERATE( NMGuildMemberLevelList, infoEx.aLevelInfo, iter )
	{
		switch( ( *iter ).uLevel )
		{
		case 1:	wapp::CopyNString( this->szLevel1Name, ( *iter ).szName, GUILDLEVEL_SIZE ); break;
		case 2:	wapp::CopyNString( this->szLevel2Name, ( *iter ).szName, GUILDLEVEL_SIZE ); break;
		case 3:	wapp::CopyNString( this->szLevel3Name, ( *iter ).szName, GUILDLEVEL_SIZE ); break;
		case 4:	wapp::CopyNString( this->szLevel4Name, ( *iter ).szName, GUILDLEVEL_SIZE ); break;
		case 5:	wapp::CopyNString( this->szLevel5Name, ( *iter ).szName, GUILDLEVEL_SIZE ); break;
		default:	break;
		}
	}

	return *this;
}

CNMChatRoomInfo::operator CNMChatRoomInfoEx( void )
{
	CNMChatRoomInfoEx		infoEx;
	CNMGuildMemberLevelInfo	liInfo;

	infoEx.uRoomType		= this->uRoomType;
	infoEx.uGuildKey		= this->uClubSerialNo;
	infoEx.uRoomSerialNo	= this->uRoomSerialNo;
	infoEx.uGameCode		= this->uGameCode;
	wapp::CopyNString( infoEx.szGuildTitle, this->szClubTitle, GUILDNAME_SIZE );
	wapp::CopyNString( infoEx.szRoomTitle, this->szRoomTitle, ROOMTITLE_SIZE );
	infoEx.uLimitLevel		= this->uLimitLevel;
	infoEx.uMaxSize			= this->uMaxSize;
	infoEx.uFlag			= this->uFlag;
	wapp::CopyNString( infoEx.szPassword, this->szPassword, ROOMPWD_SIZE );
	infoEx.uOwnerIdCode		= this->uOwnerIdCode;
	infoEx.uStatus			= this->uStatus;
	infoEx.uRequestProcId	= this->uRequestProcId;

	liInfo.uLevel = 1; wapp::CopyNString( liInfo.szName, this->szLevel1Name, GUILDLEVEL_SIZE ); infoEx.aLevelInfo.push_back( liInfo );
	liInfo.uLevel = 2; wapp::CopyNString( liInfo.szName, this->szLevel2Name, GUILDLEVEL_SIZE ); infoEx.aLevelInfo.push_back( liInfo );
	liInfo.uLevel = 3; wapp::CopyNString( liInfo.szName, this->szLevel3Name, GUILDLEVEL_SIZE ); infoEx.aLevelInfo.push_back( liInfo );
	liInfo.uLevel = 4; wapp::CopyNString( liInfo.szName, this->szLevel4Name, GUILDLEVEL_SIZE ); infoEx.aLevelInfo.push_back( liInfo );
	liInfo.uLevel = 5; wapp::CopyNString( liInfo.szName, this->szLevel5Name, GUILDLEVEL_SIZE ); infoEx.aLevelInfo.push_back( liInfo );

	return infoEx;
}
#endif	// _NMSERVER

CNMMessageData::CNMMessageData( void ) : CNMSerializable( 'md', 1, 1 )
{
	this->nCode		= 0;
	ZeroMemory( this->szMessage, sizeof( this->szMessage ) );
}

CNMMessageData::CNMMessageData( INT32 nCode )
	: CNMSerializable( 'md', 1, 1 )
{
	this->nCode		= nCode;
	ZeroMemory( this->szMessage, sizeof( this->szMessage ) );
}

BEGIN_SERIALIZE( CNMMessageData )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->nCode );
	encoder.EncodeString( this->szMessage );
//}
END_SERIALIZE();

BEGIN_DESERIALIZE( CNMMessageData )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->nCode		= decoder.Decode4();
	decoder.DecodeString( this->szMessage, SERVER_MSG_SIZE );
//}
END_DESERIALIZE()

CNMRequestNewFriendData::CNMRequestNewFriendData( void ) : CNMSerializable( 'rf', 1, 1 )
{
	this->uSerialNo				= 0;
	::ZeroMemory( this->szRequestID, sizeof( this->szRequestID ) );
	this->uRequesteeGameCode	= 0;
	INITIALIZE_VIRTUALKEY( this->keyToVirtual );
	INITIALIZE_VIRTUALKEY( this->keyFromVirtual );
	::ZeroMemory( this->szFromLoginID, sizeof( this->szFromLoginID ) );
	::ZeroMemory( this->szFromNickName, sizeof( this->szFromNickName ) );
	this->uInsertCategoryCode	= 0;
	ZeroMemory( this->szMessage, sizeof( this->szMessage ) );
	this->bAddToNxFriend		= FALSE;
}

BEGIN_SERIALIZE( CNMRequestNewFriendData )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uSerialNo );
	encoder.EncodeString( this->szRequestID );
	encoder.Encode4( this->uRequesteeGameCode );
	ENCODE_VIRTUALKEY( this->keyToVirtual );
	ENCODE_VIRTUALKEY( this->keyFromVirtual );
	encoder.EncodeString( this->szFromLoginID );
	encoder.EncodeString( this->szFromNickName );
	encoder.Encode4( this->uInsertCategoryCode );
	encoder.EncodeString( this->szMessage );
	encoder.Encode1( static_cast<UINT8>( this->bAddToNxFriend ) );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMRequestNewFriendData )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uSerialNo				= decoder.Decode4();
	decoder.DecodeString( this->szRequestID, LOGINID_SIZE );
	this->uRequesteeGameCode	= decoder.Decode4();
	DECODE_VIRTUALKEY( this->keyToVirtual );
	DECODE_VIRTUALKEY( this->keyFromVirtual );
	decoder.DecodeString( this->szFromLoginID, LOGINID_SIZE );
	decoder.DecodeString( this->szFromNickName, NICKNAME_SIZE );
	this->uInsertCategoryCode	= decoder.Decode4();
	decoder.DecodeString( this->szMessage, MSG_SIZE );
	this->bAddToNxFriend		= decoder.Decode1();
//}
END_DESERIALIZE()

CNMRequestSessionData::CNMRequestSessionData( void ) : CNMSerializable( 'rs', 1, 1 )
{
	this->uDataKey		= 0;
	this->uSessionType	= 0;
	ZeroMemory( this->szLoginId, sizeof( this->szLoginId ) );
	ZeroMemory( this->szNickName, sizeof( this->szNickName ) );
}

BEGIN_SERIALIZE( CNMRequestSessionData )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uDataKey );
	encoder.Encode4( this->uSessionType );
	encoder.EncodeString( this->szLoginId );
	encoder.EncodeString( this->szNickName );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMRequestSessionData )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uDataKey		= decoder.Decode4();
	this->uSessionType	= decoder.Decode4();
	decoder.DecodeString( this->szLoginId, LOGINID_SIZE );
	decoder.DecodeString( this->szNickName, NICKNAME_SIZE );
//}
END_DESERIALIZE()

CNMGameInfo::CNMGameInfo( void ) : CNMSerializable( 'gf', 1, 1 )
{
	this->uGameCode				= kGameCode_NULL;
	this->uGameCodePrerequired	= kGameCode_NULL;
	ZeroMemory( this->szID, sizeof( this->szID ) );
	ZeroMemory( this->szName, sizeof( this->szName ) );
	ZeroMemory( this->szCustomID, sizeof( this->szCustomID ) );
	ZeroMemory( this->szRegistryRoot, sizeof( this->szRegistryRoot ) );
	ZeroMemory( this->szRegistryExePath, sizeof( this->szRegistryExePath ) );
	ZeroMemory( this->szSetupFileUrl, sizeof( this->szSetupFileUrl ) );
	this->uAgeLimit				= 0;
	this->uMask					= 0;
	ZeroMemory( this->szIconUrl, sizeof( this->szIconUrl ) );
	ZeroMemory( this->szHomeUrl, sizeof( this->szHomeUrl ) );
	this->uOldGameCode			= kGameCode_NULL;
}

BEGIN_SERIALIZE( CNMGameInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uGameCode );
	encoder.Encode4( this->uGameCodePrerequired );
	encoder.EncodeString( this->szID );
	encoder.EncodeString( this->szName );
	encoder.EncodeString( this->szCustomID );
	encoder.EncodeString( this->szRegistryRoot );
	encoder.EncodeString( this->szRegistryExePath );
	encoder.EncodeString( this->szSetupFileUrl );
	encoder.Encode1( this->uAgeLimit );
	encoder.Encode4( this->uMask );
	encoder.EncodeString( this->szIconUrl );
	encoder.EncodeString( this->szHomeUrl );
	encoder.Encode4( this->uOldGameCode );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGameInfo )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uGameCode				= decoder.Decode4();
	this->uGameCodePrerequired	= decoder.Decode4();
	decoder.DecodeString( this->szID, 32 );
	decoder.DecodeString( this->szName, 64 );
	decoder.DecodeString( this->szCustomID, 32 );
	decoder.DecodeString( this->szRegistryRoot, 1024 );
	decoder.DecodeString( this->szRegistryExePath, 1024 );
	decoder.DecodeString( this->szSetupFileUrl, 1024 );
	this->uAgeLimit				= decoder.Decode1();
	this->uMask					= decoder.Decode4();
	decoder.DecodeString( this->szIconUrl, 1024 );
	decoder.DecodeString( this->szHomeUrl, 1024 );
	this->uOldGameCode			= decoder.Decode4();
//}
END_DESERIALIZE()

CNMGameListData::CNMGameListData( void ) : CNMSerializable( 'gd', 1, 1 )
{
}

BEGIN_SERIALIZE( CNMGameListData )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_ENCODE( NMGameInfoList, this->aGameInfo );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGameListData )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_DECODE( NMGameInfoList, this->aGameInfo );
//}
END_DESERIALIZE()

CNMCustomNotifyData::CNMCustomNotifyData( void ) : CNMSerializable( 'cn', 1, 1 )
{
	this->uType				= kCustomNotify_NULL;

	this->uToIDCode			= 0;
	INITIALIZE_VIRTUALKEY( this->keyToVirtual );

	this->uFromIDCode			= 0;
	INITIALIZE_VIRTUALKEY( this->keyFromVirtual );
	ZeroMemory( this->szFromLoginID, sizeof( this->szFromLoginID ) );
	ZeroMemory( this->szFromNickName, sizeof( this->szFromNickName ) );

	ZeroMemory( this->szArgument, sizeof( this->szArgument ) );
	ZeroMemory( &this->stDateTime, sizeof( this->stDateTime ) );

	this->uPolicyFlag		= kCustomNotifyPolicy_Default;
}

BEGIN_SERIALIZE( CNMCustomNotifyData )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uType );

	encoder.Encode8( this->uToIDCode );
	ENCODE_VIRTUALKEY( this->keyToVirtual );

	encoder.Encode8( this->uFromIDCode );
	ENCODE_VIRTUALKEY( this->keyFromVirtual );
	encoder.EncodeString( this->szFromLoginID );
	encoder.EncodeString( this->szFromNickName );

	encoder.EncodeString( this->szArgument );
	encoder.Encode4( this->stDateTime.wYear );
	encoder.Encode1( static_cast<_UInt8_>(this->stDateTime.wMonth) );
	encoder.Encode1( static_cast<_UInt8_>(this->stDateTime.wDay) );

	encoder.Encode4( this->uPolicyFlag );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMCustomNotifyData )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uType				= decoder.Decode4();

	this->uToIDCode			= decoder.Decode8();
	DECODE_VIRTUALKEY( this->keyToVirtual );

	this->uToIDCode			= decoder.Decode8();
	DECODE_VIRTUALKEY( this->keyFromVirtual );
	decoder.DecodeString( this->szFromLoginID, LOGINID_SIZE );
	decoder.DecodeString( this->szFromNickName, NICKNAME_SIZE);

	decoder.DecodeString( this->szArgument, MSG_SIZE );
	this->stDateTime.wYear	= static_cast<WORD>( decoder.Decode4() );
	this->stDateTime.wMonth	= decoder.Decode1();
	this->stDateTime.wDay	= decoder.Decode1();

	this->uPolicyFlag		= decoder.Decode4();
//}
END_DESERIALIZE()

CNMGameCode::CNMGameCode( void ) : CNMSerializable( 'gc', 1, 1 )
{
}

BEGIN_SERIALIZE( CNMGameCode )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uGameCode );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGameCode )
//{
UNREFERENCED_PARAMETER(ssStream);

	this->uGameCode = decoder.Decode4();
//}
END_DESERIALIZE()

CNMGameServerCode::CNMGameServerCode( void ) : CNMSerializable( 'gs', 1, 1 )
{
}

BEGIN_SERIALIZE( CNMGameServerCode )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uGameServerCode );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMGameServerCode )
//{
UNREFERENCED_PARAMETER(ssStream);

	this->uGameServerCode = decoder.Decode4();
//}
END_DESERIALIZE()

CNMConnConfig::CNMConnConfig( void )
	: CNMSerializable( 'cc', 1, 1 )
{
	this->szLoginServerIp[0]	= 0;
	this->uLoginServerPort		= 0;
	this->szStatServerIp[0]		= 0;
	this->uStatServerPort		= 0;
	this->bAutoPatch			= TRUE;
}

CNMConnConfig::CNMConnConfig(
	LPCTSTR	szLoginServerIp,
	UINT16	uLoginServerPort,
	LPCTSTR	szStatServerIp,
	UINT16	uStatServerPort,
	BOOL	bAutoPatch/* = TRUE*/ )
	: CNMSerializable( 'cc', 1, 1 )
{
	wapp::CopyNString( this->szLoginServerIp, szLoginServerIp, IPADDR_SIZE );
	this->uLoginServerPort		= uLoginServerPort;
	wapp::CopyNString( this->szStatServerIp, szStatServerIp, IPADDR_SIZE );
	this->uStatServerPort		= uStatServerPort;
	this->bAutoPatch			= bAutoPatch;
}

BEGIN_SERIALIZE( CNMConnConfig )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.EncodeString( this->szLoginServerIp );
	encoder.Encode2( this->uLoginServerPort );
	encoder.EncodeString( this->szStatServerIp );
	encoder.Encode2( this->uStatServerPort );
	encoder.Encode1( static_cast<BYTE>(this->bAutoPatch) );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMConnConfig )
//{
	UNREFERENCED_PARAMETER(ssStream);

	decoder.DecodeString( this->szLoginServerIp, IPADDR_SIZE );
	this->uLoginServerPort	= decoder.Decode2();
	decoder.DecodeString( this->szStatServerIp, IPADDR_SIZE );
	this->uStatServerPort	= decoder.Decode2();
	this->bAutoPatch = static_cast<BOOL>( decoder.Decode1() );
//}
END_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//
//	CNMKartDormant
//
//////////////////////////////////////////////////////////////////////////

CNMKartDormant::CNMKartDormant( void )
	: CNMSerializable( 'kd', 1, 1 )
{
	this->uOIDUser	= 0;
	this->bDormant	= FALSE;
}

CNMKartDormant::CNMKartDormant( UINT32 uOIDUser, BOOL bDormant )
	: CNMSerializable( 'kd', 1, 1 )
{
	this->uOIDUser	= uOIDUser;
	this->bDormant	= bDormant;
}

BEGIN_SERIALIZE( CNMKartDormant )
//{
	UNREFERENCED_PARAMETER(ssStream);

	encoder.Encode4( this->uOIDUser );
	encoder.Encode1( static_cast<BYTE>( this->bDormant ) );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMKartDormant )
//{
	UNREFERENCED_PARAMETER(ssStream);

	this->uOIDUser	= decoder.Decode4();
	this->bDormant	= static_cast<BOOL>( decoder.Decode1() );
//}
END_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//
//	CNMKartDormantList
//
//////////////////////////////////////////////////////////////////////////

CNMKartDormantList::CNMKartDormantList( void )
	: CNMSerializable( 'kl', 1, 1 )
{
}

BEGIN_SERIALIZE( CNMKartDormantList )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_ENCODE( NMKartDormantList, this->aKartDormant );
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMKartDormantList )
//{
	UNREFERENCED_PARAMETER(ssStream);

	LIST_DECODE( NMKartDormantList, this->aKartDormant );
//}
END_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//
//	클라이언트에서 접속할 APP 서버 정보
//
//////////////////////////////////////////////////////////////////////////

CAppServerInfo::CAppServerInfo()
	: CNMSerializable('as', 1, 1)
{
	this->uID			= 0;
	this->uPort			= 0;
}

CAppServerInfo::CAppServerInfo(UINT32 uID, LPCTSTR pszIPAddress, UINT16 uPort)
	: CNMSerializable('as', 1, 1)
{
	this->uID			= uID;
	::StringCchCopy(this->szIPAddress, IPADDR_SIZE, pszIPAddress);
	this->uPort			= uPort;
}

BEGIN_SERIALIZE(CAppServerInfo)
	UNREFERENCED_PARAMETER(ssStream);
	encoder.Encode4(this->uID);
	encoder.EncodeString(this->szIPAddress);
	encoder.Encode2(this->uPort);
END_SERIALIZE()

BEGIN_DESERIALIZE(CAppServerInfo)
	UNREFERENCED_PARAMETER(ssStream);
	this->uID = decoder.Decode4();
	decoder.DecodeString(this->szIPAddress, IPADDR_SIZE);
	this->uPort = decoder.Decode2();
END_DESERIALIZE()

CAppServerInfoListEx::CAppServerInfoListEx()
	: CNMSerializable('al', 1, 1)
{
}

BEGIN_SERIALIZE(CAppServerInfoListEx)
	UNREFERENCED_PARAMETER(ssStream);
	LIST_ENCODE(CAppServerInfoList, this->aAppServerInfo);
END_SERIALIZE()

BEGIN_DESERIALIZE(CAppServerInfoListEx)
	UNREFERENCED_PARAMETER(ssStream);
	LIST_DECODE(CAppServerInfoList, this->aAppServerInfo);
END_DESERIALIZE()
