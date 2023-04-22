#include "NMGeneral.h"
#include "NMFunctionObject.h"

#define	INIT_STRING( dstName, srcName, len )													\
	if ( srcName != NULL ) { ::_tcsncpy( dstName, srcName, len ); dstName[ len - 1 ] = 0; }		\
	else { dstName[ 0 ] = 0; }

#define	INIT_STRING2( name, len )		INIT_STRING( this->name, name, len )

#define	INIT_STRINGW( dstName, srcName, len )													\
	if ( srcName != NULL ) { ::wcsncpy( dstName, srcName, len ); dstName[ len - 1 ] = 0; }		\
	else { dstName[ 0 ] = 0; }

#define	INIT_STRINGW2( name, len )		INIT_STRINGW( this->name, name, len )

#define BEGIN_NMFUNC_SERIALIZE( className )		\
	BEGIN_SERIALIZE( className )				\
	PRIOR_SERIALIZE( CNMFunc )

#define BEGIN_NMFUNC_DESERIALIZE( className )	\
	BEGIN_DESERIALIZE( className )				\
	PRIOR_DESERIALIZE( CNMFunc )

#define END_NMFUNC_SERIALIZE		END_SERIALIZE
#define END_NMFUNC_DESERIALIZE		END_DESERIALIZE

//=========================================
// class CNMFunc
//=========================================

CNMFunc::CNMFunc( _UInt32_ uCode, _UInt8_ uObjectVersion, _UInt32_ uSerialKey, BOOL bUseEncrypt )
	: CNMSerializable( 'fo', 1, uObjectVersion )
//	: CNMSerializable( 'fo', 1, 2 )
	, m_uCode( uCode )
	, m_bCalling( FALSE )
	, m_bHandled( FALSE )
	, m_bPassThrough( FALSE )
	, m_bUseEncrypt( bUseEncrypt )
	, uSerialKey( uSerialKey )
	, bSuccess( FALSE )
{
}

BEGIN_SERIALIZE( CNMFunc )
//{
	UNREFERENCED_PARAMETER(ssStream);

	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uSerialKey );
	}
	else
	{
		encoder.Encode4( this->bSuccess );
	}
//}
END_SERIALIZE()

BEGIN_DESERIALIZE( CNMFunc )
//{
	UNREFERENCED_PARAMETER(ssStream);

	if ( this->IsCalling() )
	{
		this->uSerialKey	= decoder.Decode4();
	}
	else
	{
		this->bSuccess		= decoder.Decode4();
	}
//}
END_DESERIALIZE()

//=========================================
// class CNMInitFunc
//=========================================

CNMInitFunc::CNMInitFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode		= kGameCode_NULL;
	this->szDomain[ 0 ]	= 0;
	this->uSupportFlag	= static_cast<UINT32>( kSupportFlag_FromGameCode );
}

CNMInitFunc::CNMInitFunc( _UInt32_ uGameCode, LPCTSTR szDomain, _UInt32_ uSupportFlag )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode		= uGameCode;
	INIT_STRING2( szDomain, DOMAIN_SIZE );
	this->uSupportFlag	= uSupportFlag;
}

BEGIN_NMFUNC_SERIALIZE( CNMInitFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
		encoder.EncodeString( this->szDomain );
		encoder.Encode4( this->uSupportFlag );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMInitFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode		= decoder.Decode4();
		decoder.DecodeString( this->szDomain, DOMAIN_SIZE );
		this->uSupportFlag	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRegisterCallbackFunc
//=========================================

CNMRegisterCallbackFunc::CNMRegisterCallbackFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->hWnd		= NULL;
	this->uMessage	= 0;
}

CNMRegisterCallbackFunc::CNMRegisterCallbackFunc( HWND hWnd, _UInt32_ uMessage )
	: CNMFunc( kCode, 1 )
{
	this->hWnd		= hWnd;
	this->uMessage	= uMessage;
}

BEGIN_NMFUNC_SERIALIZE( CNMRegisterCallbackFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( static_cast<_UInt32_>( (UINT_PTR)this->hWnd ) );
		encoder.Encode4( this->uMessage );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRegisterCallbackFunc )
//{
	if ( this->IsCalling() )
	{
		this->hWnd		= reinterpret_cast<HWND>( (UINT_PTR)decoder.Decode4() );
		this->uMessage	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMResetCallbackFunc
//=========================================

CNMResetCallbackFunc::CNMResetCallbackFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->hWnd	 = NULL;
}

CNMResetCallbackFunc::CNMResetCallbackFunc( HWND hWnd )
	: CNMFunc( kCode, 1 )
{
	this->hWnd	 = hWnd;
}

BEGIN_NMFUNC_SERIALIZE( CNMResetCallbackFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( static_cast<_UInt32_>( (UINT_PTR)this->hWnd ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMResetCallbackFunc )
//{
	if ( this->IsCalling() )
	{
		this->hWnd		= reinterpret_cast<HWND>( (UINT_PTR)decoder.Decode4() );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMAttachToNMCOServerFunc
//=========================================

CNMAttachToNMCOServerFunc::CNMAttachToNMCOServerFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uProcessId	= 0;
	this->uGameCode		= 0;
	this->uSupportFlag	= 0;
	this->uLocaleID		= kLocaleID_KR;
}

CNMAttachToNMCOServerFunc::CNMAttachToNMCOServerFunc( _UInt32_ uProcessId, _UInt32_ uGameCode, _UInt32_ uSupportFlag, NMLOCALEID uLocaleID )
	: CNMFunc( kCode, 1 )
{
	this->uProcessId	= uProcessId;
	this->uGameCode		= uGameCode;
	this->uSupportFlag	= uSupportFlag;
	this->uLocaleID		= uLocaleID;
}

BEGIN_NMFUNC_SERIALIZE( CNMAttachToNMCOServerFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uProcessId );
		encoder.Encode4( this->uGameCode );
		encoder.Encode4( this->uSupportFlag );
		encoder.Encode4( this->uLocaleID );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMAttachToNMCOServerFunc )
//{
	if ( this->IsCalling() )
	{
		this->uProcessId	= decoder.Decode4();
		this->uGameCode		= decoder.Decode4();
		this->uSupportFlag	= decoder.Decode4();
		this->uLocaleID		= static_cast<NMLOCALEID>( decoder.Decode4() );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMDetachFromNMCOServerFunc
//=========================================

CNMDetachFromNMCOServerFunc::CNMDetachFromNMCOServerFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uProcessId	= 0;
}

CNMDetachFromNMCOServerFunc::CNMDetachFromNMCOServerFunc( _UInt32_ uProcessId )
	: CNMFunc( kCode, 1 )
{
	this->uProcessId	= uProcessId;
}

BEGIN_NMFUNC_SERIALIZE( CNMDetachFromNMCOServerFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uProcessId );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMDetachFromNMCOServerFunc )
//{
	if ( this->IsCalling() )
	{
		this->uProcessId	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMBringForwardStandAloneMsgFunc
//=========================================

CNMBringForwardStandAloneMsgFunc::CNMBringForwardStandAloneMsgFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szDomain[ 0 ]		= NULL;
}

CNMBringForwardStandAloneMsgFunc::CNMBringForwardStandAloneMsgFunc( LPCTSTR szDomain )
	: CNMFunc( kCode, 1 )
{
	UNREFERENCED_PARAMETER( szDomain );
}

BEGIN_NMFUNC_SERIALIZE( CNMBringForwardStandAloneMsgFunc )
//{
	if ( this->IsCalling() )
	{
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMBringForwardStandAloneMsgFunc )
//{
	if ( this->IsCalling() )
	{
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMStartStandAloneMsgFunc
//=========================================

CNMStartStandAloneMsgFunc::CNMStartStandAloneMsgFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szDomain[ 0 ]		= NULL;
}

CNMStartStandAloneMsgFunc::CNMStartStandAloneMsgFunc( LPCTSTR szDomain )
	: CNMFunc( kCode, 1 )
{
	_tcsncpy( this->szDomain, szDomain, DOMAIN_SIZE );
}

BEGIN_NMFUNC_SERIALIZE( CNMStartStandAloneMsgFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szDomain );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMStartStandAloneMsgFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szDomain, DOMAIN_SIZE );
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	class CNMInitClientObject
//////////////////////////////////////////////////////////////////////////

CNMInitClientObjectFunc::CNMInitClientObjectFunc() : CNMFunc(kCode, 1)
{
	this->uGameCode				= kGameCode_NULL;
	this->szPassport[0]			= 0;
	this->uSupportFlag			= static_cast<UINT32>( kSupportFlag_FromGameCode );
	this->bAutoMessengerLogin	= TRUE;
}

CNMInitClientObjectFunc::CNMInitClientObjectFunc(NMGameCode uGameCode, LPCTSTR pszPassport, UINT32 uSupportFlag, BOOL bAutoMessengerLogin) : CNMFunc(kCode, 1)
{
	this->uGameCode				= uGameCode;
	::StringCchCopy(this->szPassport, PASSPORT_SIZE, pszPassport);
	this->uSupportFlag			= uSupportFlag;
	this->bAutoMessengerLogin	= bAutoMessengerLogin;
}

BEGIN_NMFUNC_SERIALIZE( CNMInitClientObjectFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
		encoder.EncodeString( this->szPassport );
		encoder.Encode4( this->uSupportFlag );
		encoder.Encode1( static_cast<UINT8>( this->bAutoMessengerLogin ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMInitClientObjectFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode				= decoder.Decode4();
		decoder.DecodeString( this->szPassport, PASSPORT_SIZE );
		this->uSupportFlag			= decoder.Decode4();
		this->bAutoMessengerLogin	= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMLoginFunc
//=========================================

CNMLoginFunc::CNMLoginFunc( void )
	: CNMFunc( kCode, 1, 0, TRUE )
{
	this->szLoginId[ 0 ]	= 0;
	this->szPassword[ 0 ]	= 0;
	this->uGameCode			= kGameCode_NULL;
	this->uVersion			= 0;
	this->uLoginGameCode	= kGameCode_nexoncom;
}

CNMLoginFunc::CNMLoginFunc( LPCTSTR szLoginId, LPCTSTR szPassword, _UInt64_ uVersion, NMGameCode uLoginGameCode/* = kGameCode_nexoncom */ )
	: CNMFunc( kCode, 1 )
{
	INIT_STRING2( szLoginId, LOGINID_SIZE );
	INIT_STRING2( szPassword, LOGINID_SIZE );
	this->uGameCode			= kGameCode_NULL;
	this->uVersion			= uVersion;
	this->uLoginGameCode	= uLoginGameCode;
}

BEGIN_NMFUNC_SERIALIZE( CNMLoginFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szLoginId );
		encoder.EncodeString( this->szPassword );
		encoder.Encode4( this->uGameCode );
		encoder.Encode8( this->uVersion );
		encoder.Encode4( this->uLoginGameCode );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMLoginFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szLoginId, LOGINID_SIZE );
		decoder.DecodeString( this->szPassword, LOGINID_SIZE );
		this->uGameCode			= decoder.Decode4();
		this->uVersion			= decoder.Decode8();
		this->uLoginGameCode	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMLogoutFunc
//=========================================

CNMLogoutFunc::CNMLogoutFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->bApplyToAllSession = FALSE;
}

CNMLogoutFunc::CNMLogoutFunc( BOOL bApplyToAllSession )
	: CNMFunc( kCode, 1 )
{
	this->bApplyToAllSession = bApplyToAllSession;
}

BEGIN_NMFUNC_SERIALIZE( CNMLogoutFunc )
//{
	encoder.Encode1( static_cast<UINT8>( this->bApplyToAllSession ) );
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMLogoutFunc )
//{
	this->bApplyToAllSession = decoder.Decode1();
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMLoginVirtualFunc
//=========================================

CNMLoginVirtualFunc::CNMLoginVirtualFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szUserId[ 0 ]		= 0;
	INITIALIZE_VIRTUALKEY( this->keyUser );
	this->szPosition[ 0 ]	= 0;
}

CNMLoginVirtualFunc::CNMLoginVirtualFunc( LPCTSTR szUserId, _UInt32_ uVirtualIdCode, LPCTSTR szPosition )
	: CNMFunc( kCode, 1 )
{
	INIT_STRING2( szUserId, NICKNAME_SIZE );
	this->keyUser.uGameCode			= kGameCode_NULL;
	this->keyUser.uVirtualIDCode	= uVirtualIdCode;
	INIT_STRING2( szPosition, POSITION_SIZE );
}

BEGIN_NMFUNC_SERIALIZE( CNMLoginVirtualFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szUserId );
		ENCODE_VIRTUALKEY( this->keyUser );
		encoder.EncodeString( this->szPosition );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMLoginVirtualFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szUserId, NICKNAME_SIZE );
		DECODE_VIRTUALKEY( this->keyUser );
		decoder.DecodeString( this->szPosition, POSITION_SIZE );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMLogoutVirtualFunc
//=========================================

CNMLogoutVirtualFunc::CNMLogoutVirtualFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyUser );
}

CNMLogoutVirtualFunc::CNMLogoutVirtualFunc( NMVirtualKey keyUser )
	: CNMFunc( kCode, 1 )
{
	this->keyUser	= keyUser;
}

BEGIN_NMFUNC_SERIALIZE( CNMLogoutVirtualFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_VIRTUALKEY( this->keyUser );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMLogoutVirtualFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_VIRTUALKEY( this->keyUser );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetMyVirtualUserListFunc
//=========================================

CNMGetMyVirtualUserListFunc::CNMGetMyVirtualUserListFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode		= kGameCode_NULL;
}

CNMGetMyVirtualUserListFunc::CNMGetMyVirtualUserListFunc( _UInt32_ uGameCode )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode		= uGameCode;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetMyVirtualUserListFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
	}
	else
	{
		LIST_ENCODE( NMVirtualUserList, this->aVirtualUser );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetMyVirtualUserListFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode	= decoder.Decode4();
	}
	else
	{
		LIST_DECODE( NMVirtualUserList, this->aVirtualUser );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMChangeMyPositionFunc
//=========================================

CNMChangeMyPositionFunc::CNMChangeMyPositionFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szPosition[ 0 ]	= 0;
	INITIALIZE_VIRTUALKEY( this->keyUser );
}

CNMChangeMyPositionFunc::CNMChangeMyPositionFunc( LPCTSTR szPosition )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyUser );
	INIT_STRING2( szPosition, POSITION_SIZE );
}

CNMChangeMyPositionFunc::CNMChangeMyPositionFunc( NMVirtualKey keyUser, LPCTSTR szPosition )
	: CNMFunc( kCode, 1 )
{
	this->keyUser	= keyUser;
	INIT_STRING2( szPosition, POSITION_SIZE );
}

BEGIN_NMFUNC_SERIALIZE( CNMChangeMyPositionFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_VIRTUALKEY( this->keyUser );
		encoder.EncodeString( this->szPosition );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMChangeMyPositionFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_VIRTUALKEY( this->keyUser );
		decoder.DecodeString( this->szPosition, POSITION_SIZE );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMSendRefreshMessageFunc
//=========================================

CNMSendRefreshMessageFunc::CNMSendRefreshMessageFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uRefreshType		= kRefresh_NULL;
}

CNMSendRefreshMessageFunc::CNMSendRefreshMessageFunc( NMREFRESHTYPE uRefreshType )
	: CNMFunc( kCode, 1 )
{
	this->uRefreshType		= uRefreshType;
}

BEGIN_NMFUNC_SERIALIZE( CNMSendRefreshMessageFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uRefreshType );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSendRefreshMessageFunc )
//{
	if ( this->IsCalling() )
	{
		this->uRefreshType	= (NMREFRESHTYPE) decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetMyInfoFunc
//=========================================

CNMGetMyInfoFunc::CNMGetMyInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uSessionKeyHigh	= 0;
	this->uSessionKeyLow	= 0;
	this->uUnreadNoteCount	= 0;
	this->uUnreadMailCount	= 0;
	this->szPassport[ 0 ]	= NULL;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetMyInfoFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_ENCODE( this->ruiUserInfo );
		encoder.Encode8( this->uSessionKeyHigh );
		encoder.Encode8( this->uSessionKeyLow );
		encoder.Encode4( this->uUnreadNoteCount );
		encoder.Encode4( this->uUnreadMailCount );
		encoder.EncodeString( this->szPassport );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetMyInfoFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_DECODE( this->ruiUserInfo );
		this->uSessionKeyHigh	= decoder.Decode8();
		this->uSessionKeyLow	= decoder.Decode8();
		this->uUnreadNoteCount	= decoder.Decode4();
		this->uUnreadMailCount	= decoder.Decode4();
		decoder.DecodeString( this->szPassport, PASSPORT_SIZE );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetUserDataListFunc
//=========================================

CNMGetUserDataListFunc::CNMGetUserDataListFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uStartPtr		= 0;
	this->uTotalSize	= 0;
	this->uCurrentSize	= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetUserDataListFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uStartPtr );
	}
	else
	{
		encoder.Encode4( this->uTotalSize );
		encoder.EncodeBinary( this->aBuffer, this->uCurrentSize );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetUserDataListFunc )
//{
	if ( this->IsCalling() )
	{
		this->uStartPtr		= decoder.Decode4();
	}
	else
	{
		this->uTotalSize	= decoder.Decode4();
		this->uCurrentSize	= decoder.DecodeBinary( this->aBuffer );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMChangeMyInfoFunc
//=========================================

CNMChangeMyInfoFunc::CNMChangeMyInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uFlag				= 0;
	this->uStatus			= kStatus_Unknown;
	this->szWord[ 0 ]		= NULL;
	this->szNickName[ 0 ]	= NULL;
	this->uEmotion			= CNMAvatarItemInfo::kEmotion_NULL;
	this->uMaskInfo			= 0xFFFFFFFF;
}

CNMChangeMyInfoFunc::CNMChangeMyInfoFunc( NMUSERSTATUS uStatus, LPCTSTR szWord, LPCTSTR szNickName, CNMAvatarItemInfo::NMEMOTION uEmotion, _UInt32_ uMaskInfo )
	: CNMFunc( kCode, 1 )
{
	this->uFlag				= 0;
	if ( uStatus != kStatus_Unknown )
	{
		this->uStatus	= uStatus;
		this->uFlag		|= kFlag_Status;
	}
	if ( szWord != NULL )
	{
		NMSAFE_STRCOPY( this->szWord, szWord, WORD_SIZE );
		this->uFlag		|= kFlag_Word;
	}
	if ( szNickName != NULL )
	{
		NMSAFE_STRCOPY( this->szNickName, szNickName, NICKNAME_SIZE );
		this->uFlag		|= kFlag_NickName;
	}
	if ( uEmotion != CNMAvatarItemInfo::kEmotion_NULL )
	{
		this->uEmotion	= uEmotion;
		this->uFlag		|= kFlag_Emotion;
	}
	if ( uMaskInfo != 0xFFFFFFFF )
	{
		this->uMaskInfo	= uMaskInfo;
		this->uFlag		|= kFlag_MaskInfo;
	}
}

BEGIN_NMFUNC_SERIALIZE( CNMChangeMyInfoFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uFlag );
		if ( this->uFlag & kFlag_Status )	encoder.Encode4( this->uStatus );
		if ( this->uFlag & kFlag_Word )		encoder.EncodeString( this->szWord );
		if ( this->uFlag & kFlag_NickName )	encoder.EncodeString( this->szNickName );
		if ( this->uFlag & kFlag_Emotion )	encoder.Encode4( this->uEmotion );
		if ( this->uFlag & kFlag_MaskInfo )	encoder.Encode4( this->uMaskInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMChangeMyInfoFunc )
//{
	if ( this->IsCalling() )
	{
		this->uFlag	= decoder.Decode4();
		if ( this->uFlag & kFlag_Status )	this->uStatus	= ( NMUSERSTATUS )decoder.Decode4();
		if ( this->uFlag & kFlag_Word )		decoder.DecodeString( this->szWord, WORD_SIZE );
		if ( this->uFlag & kFlag_NickName )	decoder.DecodeString( this->szNickName, NICKNAME_SIZE );
		if ( this->uFlag & kFlag_Emotion )	this->uEmotion	= ( CNMAvatarItemInfo::NMEMOTION )decoder.Decode4();
		if ( this->uFlag & kFlag_MaskInfo )	this->uMaskInfo	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetConfigFunc
//=========================================

CNMGetConfigFunc::CNMGetConfigFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uFlag						= 0;
	this->uAllowChatType			= kConfigAllowValue_Unknown;
	this->uAllowNoteType			= kConfigAllowValue_Unknown;
	this->uAllowGameInviteType		= kConfigAllowValue_Unknown;
	this->uAllowRequestFriendType	= kConfigAllowValue_Unknown;
}

CNMGetConfigFunc::CNMGetConfigFunc( _UInt32_ uFlag )
	: CNMFunc( kCode, 1 )
{
	this->uFlag						= uFlag;
	this->uAllowChatType			= kConfigAllowValue_Unknown;
	this->uAllowNoteType			= kConfigAllowValue_Unknown;
	this->uAllowGameInviteType		= kConfigAllowValue_Unknown;
	this->uAllowRequestFriendType	= kConfigAllowValue_Unknown;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetConfigFunc )
//{
	encoder.Encode4( this->uFlag );

	if ( this->IsReturn() )
	{
		if ( this->uFlag & kFlag_AllowChatType )			encoder.Encode1( static_cast<UINT8>( this->uAllowChatType ) );
		if ( this->uFlag & kFlag_AllowNoteType )			encoder.Encode1( static_cast<UINT8>( this->uAllowNoteType ) );
		if ( this->uFlag & kFlag_AllowGameInviteType )		encoder.Encode1( static_cast<UINT8>( this->uAllowGameInviteType ) );
		if ( this->uFlag & kFlag_AllowRequestFriendType )	encoder.Encode1( static_cast<UINT8>( this->uAllowRequestFriendType ) );
		if ( this->uFlag & kFlag_MyGame )					OBJECT_ENCODE( this->mgiGameInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetConfigFunc )
//{
	this->uFlag	= decoder.Decode4();

	if ( this->IsReturn() )
	{
		if ( this->uFlag & kFlag_AllowChatType )			this->uAllowChatType			= ( NMALLOWTYPE )decoder.Decode1();
		if ( this->uFlag & kFlag_AllowNoteType )			this->uAllowNoteType			= ( NMALLOWTYPE )decoder.Decode1();
		if ( this->uFlag & kFlag_AllowGameInviteType )		this->uAllowGameInviteType		= ( NMALLOWTYPE )decoder.Decode1();
		if ( this->uFlag & kFlag_AllowRequestFriendType )	this->uAllowRequestFriendType	= ( NMALLOWTYPE )decoder.Decode1();
		if ( this->uFlag & kFlag_MyGame )					OBJECT_DECODE( this->mgiGameInfo );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMChangeConfigFunc
//=========================================

CNMChangeConfigFunc::CNMChangeConfigFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uFlag						= 0;
	this->uAllowChatType			= kConfigAllowValue_Unknown;
	this->uAllowNoteType			= kConfigAllowValue_Unknown;
	this->uAllowGameInviteType		= kConfigAllowValue_Unknown;
	this->uAllowRequestFriendType	= kConfigAllowValue_Unknown;
}

CNMChangeConfigFunc::CNMChangeConfigFunc( NMALLOWTYPE uAllowChatType, NMALLOWTYPE uAllowNoteType, NMALLOWTYPE uAllowGameInviteType, NMALLOWTYPE uAllowRequestFriendType, CNMMyGameInfo* pmgiGameInfo )
	: CNMFunc( kCode, 1 )
{
	this->uFlag							= 0;
	if ( uAllowChatType != kConfigAllowValue_Unknown )
	{
		this->uFlag						|= kFlag_AllowChatType;
		this->uAllowChatType			= uAllowChatType;
	}
	if ( uAllowNoteType != kConfigAllowValue_Unknown )
	{
		this->uFlag						|= kFlag_AllowNoteType;
		this->uAllowNoteType			= uAllowNoteType;
	}
	if ( uAllowGameInviteType != kConfigAllowValue_Unknown )
	{
		this->uFlag						|= kFlag_AllowGameInviteType;
		this->uAllowGameInviteType		= uAllowGameInviteType;
	}
	if ( uAllowRequestFriendType != kConfigAllowValue_Unknown )
	{
		this->uFlag						|= kFlag_AllowRequestFriendType;
		this->uAllowRequestFriendType	= uAllowRequestFriendType;
	}
	if ( pmgiGameInfo != NULL )
	{
		this->uFlag						|= kFlag_MyGame;
		this->mgiGameInfo				= ( *pmgiGameInfo );
	}
}

BEGIN_NMFUNC_SERIALIZE( CNMChangeConfigFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uFlag );
		if ( this->uFlag & kFlag_AllowChatType )			encoder.Encode1( static_cast<UINT8>( this->uAllowChatType ) );
		if ( this->uFlag & kFlag_AllowNoteType )			encoder.Encode1( static_cast<UINT8>( this->uAllowNoteType ) );
		if ( this->uFlag & kFlag_AllowGameInviteType )		encoder.Encode1( static_cast<UINT8>( this->uAllowGameInviteType ) );
		if ( this->uFlag & kFlag_AllowRequestFriendType )	encoder.Encode1( static_cast<UINT8>( this->uAllowRequestFriendType ) );
		if ( this->uFlag & kFlag_MyGame )					OBJECT_ENCODE( this->mgiGameInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMChangeConfigFunc )
//{
	if ( this->IsCalling() )
	{
		this->uFlag	= decoder.Decode4();
		if ( this->uFlag & kFlag_AllowChatType )			this->uAllowChatType			= ( NMALLOWTYPE )decoder.Decode1();
		if ( this->uFlag & kFlag_AllowNoteType )			this->uAllowNoteType			= ( NMALLOWTYPE )decoder.Decode1();
		if ( this->uFlag & kFlag_AllowGameInviteType )		this->uAllowGameInviteType		= ( NMALLOWTYPE )decoder.Decode1();
		if ( this->uFlag & kFlag_AllowRequestFriendType )	this->uAllowRequestFriendType	= ( NMALLOWTYPE )decoder.Decode1();
		if ( this->uFlag & kFlag_MyGame )					OBJECT_DECODE( this->mgiGameInfo );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetLocalConfigFunc
//=========================================

CNMGetLocalConfigFunc::CNMGetLocalConfigFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uConfigType		= kLocalConfig_NULL;
	this->szValueOut[ 0 ]	= NULL;
}

CNMGetLocalConfigFunc::CNMGetLocalConfigFunc( NMLOCALCONFIGTYPE uConfigType )
	: CNMFunc( kCode, 1 )
{
	this->uConfigType		= uConfigType;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetLocalConfigFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( ( _UInt32_ )this->uConfigType );
	}
	else
	{
		encoder.EncodeString( this->szValueOut );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetLocalConfigFunc )
//{
	if ( this->IsCalling() )
	{
		this->uConfigType	= ( NMLOCALCONFIGTYPE )decoder.Decode4();
	}
	else
	{
		decoder.DecodeString( this->szValueOut, 1024 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMChangeLocalConfigFunc
//=========================================

CNMChangeLocalConfigFunc::CNMChangeLocalConfigFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uConfigType		= kLocalConfig_NULL;
	this->szValue[ 0 ]		= NULL;
}

CNMChangeLocalConfigFunc::CNMChangeLocalConfigFunc( NMLOCALCONFIGTYPE uConfigType, LPCTSTR szValue )
	: CNMFunc( kCode, 1 )
{
	this->uConfigType		= uConfigType;
	INIT_STRING2( szValue, 1024 );
}

BEGIN_NMFUNC_SERIALIZE( CNMChangeLocalConfigFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( ( _UInt32_ )this->uConfigType );
		encoder.EncodeString( this->szValue );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMChangeLocalConfigFunc )
//{
	if ( this->IsCalling() )
	{
		this->uConfigType	= ( NMLOCALCONFIGTYPE )decoder.Decode4();
		decoder.DecodeString( this->szValue, 1024 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetCountFunc
//=========================================

CNMGetCountFunc::CNMGetCountFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uType		= kType_NULL;
	this->uCount	= 0;
}

CNMGetCountFunc::CNMGetCountFunc( _UInt32_ uType )
	: CNMFunc( kCode, 1 )
{
	this->uType		= uType;
	this->uCount	= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetCountFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uType );
	}
	else
	{
		encoder.Encode4( this->uCount );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetCountFunc )
//{
	if ( this->IsCalling() )
	{
		this->uType		= decoder.Decode4();
	}
	else
	{
		this->uCount	 = decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMSetCountFunc
//=========================================

CNMSetCountFunc::CNMSetCountFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uType		= kType_NULL;
	this->uCount	= 0;
}

CNMSetCountFunc::CNMSetCountFunc( _UInt32_ uType, _UInt32_ uCount )
	: CNMFunc( kCode, 1 )
{
	this->uType		= uType;
	this->uCount	= uCount;
}

BEGIN_NMFUNC_SERIALIZE( CNMSetCountFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uType );
		encoder.Encode4( this->uCount );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSetCountFunc )
//{
	if ( this->IsCalling() )
	{
		this->uType		= decoder.Decode4();
		this->uCount	 = decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMChangeNicknameFunc
//=========================================

CNMChangeNicknameFunc::CNMChangeNicknameFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyCharacter );
	this->szNickname[ 0 ] = 0;
}

CNMChangeNicknameFunc::CNMChangeNicknameFunc( NMVirtualKey keyCharacter, LPCTSTR szNickname )
	: CNMFunc( kCode, 1 )
{
	this->keyCharacter = keyCharacter;
	INIT_STRING2( szNickname, NICKNAME_SIZE );
}

BEGIN_NMFUNC_SERIALIZE( CNMChangeNicknameFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_VIRTUALKEY( this->keyCharacter );
		encoder.EncodeString( this->szNickname );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMChangeNicknameFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_VIRTUALKEY( this->keyCharacter );
		decoder.DecodeString( this->szNickname, NICKNAME_SIZE );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestNewsFunc
//=========================================

CNMRequestNewsFunc::CNMRequestNewsFunc( void )
	: CNMFunc( kCode, 1 )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestNewsFunc )
//{
if ( this->IsCalling() )
{
}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestNewsFunc )
//{
if ( this->IsCalling() )
{
}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCheckNewsFunc
//=========================================

CNMCheckNewsFunc::CNMCheckNewsFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uEventGameCode = kGameCode_NULL;
	INITIALIZE_VIRTUALKEY( this->keyVirtual );
}

CNMCheckNewsFunc::CNMCheckNewsFunc( NMGameCode uEventGameCode, NMVirtualKey keyVirtual/* = keyVirtualAll */ )
	: CNMFunc( kCode, 1 )
{
	this->uEventGameCode = uEventGameCode;
	this->keyVirtual = keyVirtual;
}

BEGIN_NMFUNC_SERIALIZE( CNMCheckNewsFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uEventGameCode );
		ENCODE_VIRTUALKEY( this->keyVirtual );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCheckNewsFunc )
//{
	if ( this->IsCalling() )
	{
		this->uEventGameCode = decoder.Decode4();
		DECODE_VIRTUALKEY( this->keyVirtual );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetDomainFunc
//=========================================

CNMGetDomainFunc::CNMGetDomainFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szDomain[ 0 ]	 = 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetDomainFunc )
//{
	if ( this->IsReturn() )
	{
		encoder.EncodeString( this->szDomain );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetDomainFunc )
//{
	if ( this->IsReturn() )
	{
		decoder.DecodeString( this->szDomain, DOMAIN_SIZE );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetVersionFunc
//=========================================

CNMGetVersionFunc::CNMGetVersionFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uModuleIndex	 = 0;
}

CNMGetVersionFunc::CNMGetVersionFunc( _UInt32_ uModuleIndex )
	: CNMFunc( kCode, 1 )
{
	this->uModuleIndex	 = uModuleIndex;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetVersionFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uModuleIndex );
	}
	else
	{
		encoder.Encode8( this->uVersion );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetVersionFunc )
//{
	if ( this->IsCalling() )
	{
		this->uModuleIndex	= decoder.Decode4();
	}
	else
	{
		this->uVersion		= decoder.Decode8();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMSetStatInfoFunc
//=========================================

CNMSetStatInfoFunc::CNMSetStatInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uType					= kStatType_NULL;
	this->uClientDllGameCode	= 0;
	this->uGameCode				= 0;
	this->uData1				= 0;
	this->uData2				= 0;
}

CNMSetStatInfoFunc::CNMSetStatInfoFunc( CNMSetStatInfoFunc::NMSTATTYPE uType, UINT32 uClientDllGameCode, UINT32 uGameCode, UINT32 uData1, UINT32 uData2 )
	: CNMFunc( kCode, 1 )
{
	this->uType					= uType;
	this->uClientDllGameCode	= uClientDllGameCode;
	this->uGameCode				= uGameCode;
	this->uData1				= uData1;
	this->uData2				= uData2;
}

BEGIN_NMFUNC_SERIALIZE( CNMSetStatInfoFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uType );
		encoder.Encode4( this->uClientDllGameCode );
		encoder.Encode4( this->uGameCode );
		encoder.Encode4( this->uData1 );
		encoder.Encode4( this->uData2 );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSetStatInfoFunc )
//{
	if ( this->IsCalling() )
	{
		this->uType					= static_cast<CNMSetStatInfoFunc::NMSTATTYPE>( decoder.Decode4() );
		this->uClientDllGameCode	= decoder.Decode4();
		this->uGameCode				= decoder.Decode4();
		this->uData1				= decoder.Decode4();
		this->uData2				= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

CNMSetLocaleFunc::CNMSetLocaleFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uLocaleID		= kLocaleID_KR;
	this->uRegionCode	= 0;
}

CNMSetLocaleFunc::CNMSetLocaleFunc( NMLOCALEID uLocaleID, UINT32 uRegionCode )
	: CNMFunc( kCode, 1 )
{
	this->uLocaleID		= uLocaleID;
	this->uRegionCode	= uRegionCode;
}

BEGIN_NMFUNC_SERIALIZE( CNMSetLocaleFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uLocaleID );
		encoder.Encode4( this->uRegionCode );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSetLocaleFunc )
//{
	if ( this->IsCalling() )
	{
		this->uLocaleID		= static_cast<NMLOCALEID>( decoder.Decode4() );
		this->uRegionCode	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetNexonPassportFunc
//=========================================

CNMGetNexonPassportFunc::CNMGetNexonPassportFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->bSecondary			= FALSE;
	this->szNexonPassport[ 0 ]	= 0;
}

CNMGetNexonPassportFunc::CNMGetNexonPassportFunc( BOOL bSecondary )
	: CNMFunc( kCode, 1 )
{
	this->bSecondary			= bSecondary;
	this->szNexonPassport[ 0 ]	= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetNexonPassportFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->bSecondary );
	}
	else
	{
		encoder.EncodeString( this->szNexonPassport );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetNexonPassportFunc )
//{
	if ( this->IsCalling() )
	{
		this->bSecondary = decoder.Decode4();
	}
	else
	{
		decoder.DecodeString( this->szNexonPassport, PASSPORT_SIZE );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetFriendListFunc
//=========================================

CNMGetFriendListFunc::CNMGetFriendListFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMGetFriendListFunc::CNMGetFriendListFunc( NMVirtualKey keyVirtuality )
	: CNMFunc( kCode, 1 )
{
	this->keyVirtuality		= keyVirtuality;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetFriendListFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_VIRTUALKEY( this->keyVirtuality );
	}
	else
	{
		LIST_ENCODE( NMCategoryUserList, this->aCateUserList );
		LIST_ENCODE( NMFriendList, this->aUnrelated );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetFriendListFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_VIRTUALKEY( this->keyVirtuality );
	}
	else
	{
		LIST_DECODE( NMCategoryUserList, this->aCateUserList );
		LIST_DECODE( NMFriendList, this->aUnrelated );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetFriendInfoFunc
//=========================================

CNMGetFriendInfoFunc::CNMGetFriendInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMGetFriendInfoFunc::CNMGetFriendInfoFunc( NMFriendKey keyFriend )
	: CNMFunc( kCode, 1 )
{
	this->keyFriend		= keyFriend;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetFriendInfoFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDKEY( this->keyFriend );
	}
	else
	{
		OBJECT_ENCODE( this->fiFriendInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetFriendInfoFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDKEY( this->keyFriend );
	}
	else
	{
		OBJECT_DECODE( this->fiFriendInfo );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestNewFriendFunc
//=========================================

CNMRequestNewFriendFunc::CNMRequestNewFriendFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMRequestNewFriendFunc::CNMRequestNewFriendFunc( CNMRequestNewFriendData& rnfData )
	: CNMFunc( kCode, 1 )
{
	this->rnfData	 = rnfData;
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestNewFriendFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->rnfData );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestNewFriendFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->rnfData );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMConfirmNewFriendFunc
//=========================================

CNMConfirmNewFriendFunc::CNMConfirmNewFriendFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uSerialNo				= 0;
	this->uConfirmCode			= kConfirmLater;
	this->uInsertCategoryCode	= 0;
}

CNMConfirmNewFriendFunc::CNMConfirmNewFriendFunc( _UInt32_ uSerialNo, NMCONFIRMCODE uConfirmCode, _UInt32_ uInsertCategoryCode )
	: CNMFunc( kCode, 1 )
{
	this->uSerialNo				= uSerialNo;
	this->uConfirmCode			= uConfirmCode;
	this->uInsertCategoryCode	= uInsertCategoryCode;
}

BEGIN_NMFUNC_SERIALIZE( CNMConfirmNewFriendFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uSerialNo );
		encoder.Encode1( static_cast<UINT8>( this->uConfirmCode ) );
		encoder.Encode4( this->uInsertCategoryCode );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMConfirmNewFriendFunc )
//{
	if ( this->IsCalling() )
	{
		this->uSerialNo				= decoder.Decode4();
		this->uConfirmCode			= ( NMCONFIRMCODE )decoder.Decode1();
		this->uInsertCategoryCode	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMBlockFriendFunc
//=========================================

CNMBlockFriendFunc::CNMBlockFriendFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDKEY( this->keyFriend );
	this->bBlock	= FALSE;
}

CNMBlockFriendFunc::CNMBlockFriendFunc( NMFriendKey keyFriend, BOOL bBlock )
	: CNMFunc( kCode, 1 )
{
	this->keyFriend		= keyFriend;
	this->bBlock		= bBlock;
}

BEGIN_NMFUNC_SERIALIZE( CNMBlockFriendFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDKEY( this->keyFriend );
		encoder.Encode1( static_cast<UINT8>( this->bBlock ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMBlockFriendFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDKEY( this->keyFriend );
		this->bBlock	= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMChangeFriendMemoFunc
//=========================================

CNMChangeFriendMemoFunc::CNMChangeFriendMemoFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uIDCode		= 0;
	this->szMemo[ 0 ]	= NULL;
}

CNMChangeFriendMemoFunc::CNMChangeFriendMemoFunc( _UInt64_ uIDCode, LPCTSTR szMemo )
	: CNMFunc( kCode, 1 )
{
	this->uIDCode		= uIDCode;
	INIT_STRING2( szMemo, MEMO_SIZE );
}

BEGIN_NMFUNC_SERIALIZE( CNMChangeFriendMemoFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode8( this->uIDCode );
		encoder.EncodeString( this->szMemo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMChangeFriendMemoFunc )
//{
	if ( this->IsCalling() )
	{
		this->uIDCode	= decoder.Decode8();
		decoder.DecodeString( this->szMemo, MEMO_SIZE );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMAddFriendToCategoryFunc
//=========================================

CNMAddFriendToCategoryFunc::CNMAddFriendToCategoryFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDKEY( this->keyFriend );
	this->uCategoryCode		= 0;
	this->bSystemCall		= FALSE;
}

CNMAddFriendToCategoryFunc::CNMAddFriendToCategoryFunc( NMFriendKey keyFriend, UINT32 uCategoryCode, BOOL bSystemCall )
	: CNMFunc( kCode, 1 )
{
	this->keyFriend			= keyFriend;
	this->uCategoryCode		= uCategoryCode;
	this->bSystemCall		= bSystemCall;
}

BEGIN_NMFUNC_SERIALIZE( CNMAddFriendToCategoryFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDKEY( this->keyFriend );
		encoder.Encode4( this->uCategoryCode );
		encoder.Encode1( static_cast<UINT8>( this->bSystemCall ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMAddFriendToCategoryFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDKEY( this->keyFriend );
		this->uCategoryCode		= decoder.Decode4();
		this->bSystemCall		= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMDeleteFriendFromCategoryFunc
//=========================================

CNMDeleteFriendFromCategoryFunc::CNMDeleteFriendFromCategoryFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDKEY( this->keyFriend );
	this->uCategoryCode		= 0;
	this->bSystemCall		= FALSE;
}

CNMDeleteFriendFromCategoryFunc::CNMDeleteFriendFromCategoryFunc( NMFriendKey keyFriend, UINT32 uCategoryCode, BOOL bSystemCall )
	: CNMFunc( kCode, 1 )
{
	this->keyFriend			= keyFriend;
	this->uCategoryCode		= uCategoryCode;
	this->bSystemCall		= bSystemCall;
}

BEGIN_NMFUNC_SERIALIZE( CNMDeleteFriendFromCategoryFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDKEY( this->keyFriend );
		encoder.Encode4( this->uCategoryCode );
		encoder.Encode1( static_cast<UINT8>( this->bSystemCall ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMDeleteFriendFromCategoryFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDKEY( this->keyFriend );
		this->uCategoryCode		= decoder.Decode4();
		this->bSystemCall		= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMMoveFriendCategoryFunc
//=========================================

CNMMoveFriendCategoryFunc::CNMMoveFriendCategoryFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDKEY( this->keyFriend );
	this->uFromCateCode		= 0;
	this->uToCateCode		= 0;
	this->bSystemCall		= FALSE;
}

CNMMoveFriendCategoryFunc::CNMMoveFriendCategoryFunc( NMFriendKey keyFriend, UINT32 uFromCateCode, UINT32 uToCateCode, BOOL bSystemCall )
	: CNMFunc( kCode, 1 )
{
	this->keyFriend			= keyFriend;
	this->uFromCateCode		= uFromCateCode;
	this->uToCateCode		= uToCateCode;
	this->bSystemCall		= bSystemCall;
}

BEGIN_NMFUNC_SERIALIZE( CNMMoveFriendCategoryFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDKEY( this->keyFriend );
		encoder.Encode4( this->uFromCateCode );
		encoder.Encode4( this->uToCateCode );
		encoder.Encode1( static_cast<UINT8>( this->bSystemCall ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMMoveFriendCategoryFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDKEY( this->keyFriend );
		this->uFromCateCode		= decoder.Decode4();
		this->uToCateCode		= decoder.Decode4();
		this->bSystemCall		= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMAddCategoryFunc
//=========================================

CNMAddCategoryFunc::CNMAddCategoryFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyOwner );
	this->szCategoryName[ 0 ]		= NULL;
	this->uProperty					= kCateProp_None;
	this->uAllowType				= kConfigAllowValue_FromAll;
}

CNMAddCategoryFunc::CNMAddCategoryFunc( NMVirtualKey keyOwner, LPCTSTR szCategoryName, NMCATEGORYPROPERTY uProperty, NMALLOWTYPE uAllowType )
	: CNMFunc( kCode, 1 )
{
	this->keyOwner		= keyOwner;
	INIT_STRING2( szCategoryName, CATEGORY_SIZE );
	this->uProperty		= uProperty;
	this->uAllowType	= uAllowType;
}

BEGIN_NMFUNC_SERIALIZE( CNMAddCategoryFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_VIRTUALKEY( this->keyOwner );
		encoder.EncodeString( this->szCategoryName );
		encoder.Encode4( this->uProperty );
		encoder.Encode4( this->uAllowType );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMAddCategoryFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_VIRTUALKEY( this->keyOwner );
		decoder.DecodeString( this->szCategoryName, CATEGORY_SIZE );
		this->uProperty		= static_cast<NMCATEGORYPROPERTY>( decoder.Decode4() );
		this->uAllowType	= static_cast<NMALLOWTYPE>( decoder.Decode4() );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMDeleteCategoryFunc
//=========================================

CNMDeleteCategoryFunc::CNMDeleteCategoryFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uCategoryCode		= 0;
	this->bSystemCall		= FALSE;
}

CNMDeleteCategoryFunc::CNMDeleteCategoryFunc( UINT32 uCategoryCode, BOOL bSystemCall )
	: CNMFunc( kCode, 1 )
{
	this->uCategoryCode		= uCategoryCode;
	this->bSystemCall		= bSystemCall;
}

BEGIN_NMFUNC_SERIALIZE( CNMDeleteCategoryFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uCategoryCode );
		encoder.Encode1( static_cast<UINT8>( this->bSystemCall ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMDeleteCategoryFunc )
//{
	if ( this->IsCalling() )
	{
		this->uCategoryCode		= decoder.Decode4();
		this->bSystemCall		= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMChangeCategoryNameFunc
//=========================================

CNMChangeCategoryNameFunc::CNMChangeCategoryNameFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uCategoryCode			= 0;
	this->szCategoryName[ 0 ]	= NULL;
	this->bSystemCall			= FALSE;
}

CNMChangeCategoryNameFunc::CNMChangeCategoryNameFunc( UINT32 uCategoryCode, LPCTSTR szCategoryName, BOOL bSystemCall )
	: CNMFunc( kCode, 1 )
{
	this->uCategoryCode		= uCategoryCode;
	INIT_STRING2( szCategoryName, CATEGORY_SIZE );
	this->bSystemCall		= bSystemCall;
}

BEGIN_NMFUNC_SERIALIZE( CNMChangeCategoryNameFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uCategoryCode );
		encoder.EncodeString( this->szCategoryName );
		encoder.Encode1( static_cast<UINT8>( this->bSystemCall ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMChangeCategoryNameFunc )
//{
	if ( this->IsCalling() )
	{
		this->uCategoryCode		= decoder.Decode4();
		decoder.DecodeString( this->szCategoryName, CATEGORY_SIZE );
		this->bSystemCall		= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMChangeCategoryPropertyFunc
//=========================================

CNMChangeCategoryPropertyFunc::CNMChangeCategoryPropertyFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uCategoryCode			= 0;
	this->uProperty				= kCateProp_None;
	this->bSystemCall			= FALSE;
}

CNMChangeCategoryPropertyFunc::CNMChangeCategoryPropertyFunc( UINT32 uCategoryCode, NMCATEGORYPROPERTY uProperty, BOOL bSystemCall )
	: CNMFunc( kCode, 1 )
{
	this->uCategoryCode		= uCategoryCode;
	this->uProperty			= uProperty;
	this->bSystemCall		= bSystemCall;
}

BEGIN_NMFUNC_SERIALIZE( CNMChangeCategoryPropertyFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uCategoryCode );
		encoder.Encode4( this->uProperty );
		encoder.Encode1( static_cast<UINT8>( this->bSystemCall ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMChangeCategoryPropertyFunc )
//{
	if ( this->IsCalling() )
	{
		this->uCategoryCode		= decoder.Decode4();
		this->uProperty			= static_cast<NMCATEGORYPROPERTY>( decoder.Decode4() );
		this->bSystemCall		= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMChangeCategoryAllowTypeFunc
//=========================================

CNMChangeCategoryAllowTypeFunc::CNMChangeCategoryAllowTypeFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uCategoryCode			= 0;
	this->uAllowType			= kConfigAllowValue_FromAll;
	this->bSystemCall			= FALSE;
}

CNMChangeCategoryAllowTypeFunc::CNMChangeCategoryAllowTypeFunc( UINT32 uCategoryCode, NMALLOWTYPE uAllowType, BOOL bSystemCall )
	: CNMFunc( kCode, 1 )
{
	this->uCategoryCode		= uCategoryCode;
	this->uAllowType		= uAllowType;
	this->bSystemCall		= bSystemCall;
}

BEGIN_NMFUNC_SERIALIZE( CNMChangeCategoryAllowTypeFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uCategoryCode );
		encoder.Encode4( this->uAllowType );
		encoder.Encode1( static_cast<UINT8>( this->bSystemCall ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMChangeCategoryAllowTypeFunc )
//{
	if ( this->IsCalling() )
	{
		this->uCategoryCode		= decoder.Decode4();
		this->uAllowType		= static_cast<NMALLOWTYPE>( decoder.Decode4() );
		this->bSystemCall		= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetNoteBoxFunc
//=========================================

CNMGetNoteBoxFunc::CNMGetNoteBoxFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uBoxType		= kNoteBoxType_TempBox;
	this->uNoteSerial	= 0xFFFFFFFF;
	this->uStartPtr		= 0;
}

CNMGetNoteBoxFunc::CNMGetNoteBoxFunc( NMNOTEBOXTYPE uBoxType, _UInt32_ uNoteSerial )
	: CNMFunc( kCode, 1 )
{
	this->uBoxType		= uBoxType;
	this->uNoteSerial	= uNoteSerial;
	this->uStartPtr		= 0;
}

CNMGetNoteBoxFunc::CNMGetNoteBoxFunc( NMNOTEBOXTYPE uBoxType, _UInt32_ uNoteSerial, _UInt32_ uStartPtr )
	: CNMFunc( kCode, 1 )
{
	this->uBoxType		= uBoxType;
	this->uNoteSerial	= uNoteSerial;
	this->uStartPtr		= uStartPtr;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetNoteBoxFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode1( static_cast<UINT8>( this->uBoxType ) );
		encoder.Encode4( this->uNoteSerial );
		encoder.Encode4( this->uStartPtr );
	}
	else
	{
		LIST_ENCODE( NMNoteList, this->aNotes );
		encoder.Encode1( static_cast<UINT8>( this->bMore ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetNoteBoxFunc )
//{
	if ( this->IsCalling() )
	{
		this->uBoxType		= ( NMNOTEBOXTYPE )decoder.Decode1();
		this->uNoteSerial	= decoder.Decode4();
		this->uStartPtr		= decoder.Decode4();
	}
	else
	{
		LIST_DECODE( NMNoteList, this->aNotes );
		this->bMore			= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMSendNoteMsgFunc
//=========================================

CNMSendNoteMsgFunc::CNMSendNoteMsgFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->bSaveToPermBox		= FALSE;
}

CNMSendNoteMsgFunc::CNMSendNoteMsgFunc( CNMNoteInfo::NMNOTETYPE uNoteType, CNMNoteInfo::NMNOTESUBTYPE uSubType, LPCTSTR szTargetId, LPCTSTR szMessage, LPCTSTR szArgument, LPCTSTR szCustomData, BOOL bSaveToPermBox )
	: CNMFunc( kCode, 1 )
{
	this->bSaveToPermBox		= bSaveToPermBox;
	this->noteInfo.uNoteType	= uNoteType;
	this->noteInfo.uSubType		= uSubType;
	INITIALIZE_VIRTUALKEY(this->noteInfo.keyVirtual);
	INIT_STRING( this->noteInfo.szTargetID, szTargetId, TARGETID_SIZE );
	INIT_STRING( this->noteInfo.szMessage, szMessage, NOTEMSG_SIZE );
	INIT_STRING( this->noteInfo.szArgument, szArgument, NOTEARG_SIZE );
	INIT_STRING( this->noteInfo.szCustomData, szCustomData, NOTECUSTOM_SIZE );
}

CNMSendNoteMsgFunc::CNMSendNoteMsgFunc( CNMNoteInfo::NMNOTETYPE uNoteType, CNMNoteInfo::NMNOTESUBTYPE uSubType, NMVirtualKey keyFromVirtual, LPCTSTR szTargetId, LPCTSTR szMessage, LPCTSTR szArgument, LPCTSTR szCustomData, BOOL bSaveToPermBox )
	: CNMFunc( kCode, 1 )
{
	this->bSaveToPermBox				= bSaveToPermBox;
	this->noteInfo.uNoteType			= uNoteType;
	this->noteInfo.uSubType				= uSubType;
	this->noteInfo.keyVirtual			= keyFromVirtual;
	INIT_STRING( this->noteInfo.szTargetID, szTargetId, TARGETID_SIZE );
	INIT_STRING( this->noteInfo.szMessage, szMessage, NOTEMSG_SIZE );
	INIT_STRING( this->noteInfo.szArgument, szArgument, NOTEARG_SIZE );
	INIT_STRING( this->noteInfo.szCustomData, szCustomData, NOTECUSTOM_SIZE );
}

BEGIN_NMFUNC_SERIALIZE( CNMSendNoteMsgFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode1( static_cast<UINT8>( this->bSaveToPermBox ) );
		OBJECT_ENCODE( this->noteInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSendNoteMsgFunc )
//{
	if ( this->IsCalling() )
	{
		this->bSaveToPermBox = decoder.Decode1();
		OBJECT_DECODE( this->noteInfo );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNProcessNoteFunc
//=========================================

CNMProcessNoteFunc::CNMProcessNoteFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uType			= 0;
	this->uNoteSerial	= 0;
}

CNMProcessNoteFunc::CNMProcessNoteFunc( _UInt32_ uType, _UInt32_ uNoteSerial )
	: CNMFunc( kCode, 1 )
{
	this->uType			= uType;
	this->uNoteSerial	= uNoteSerial;
}

BEGIN_NMFUNC_SERIALIZE( CNMProcessNoteFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode1( static_cast<UINT8>( this->uType ) );
		encoder.Encode4( this->uNoteSerial );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMProcessNoteFunc )
//{
	if ( this->IsCalling() )
	{
		this->uType			= decoder.Decode1();
		this->uNoteSerial	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMSendNoteInfoFunc
//////////////////////////////////////////////////////////////////////////

CNMSendNoteInfoFunc::CNMSendNoteInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->bSaveToPermBox		= FALSE;
	this->noteInfo.bSendFlag	= TRUE;
}

CNMSendNoteInfoFunc::CNMSendNoteInfoFunc( CNMNoteInfo & noteInfo, BOOL bSaveToPermBox )
	: CNMFunc( kCode, 1 )
{
	this->bSaveToPermBox		= bSaveToPermBox;
	this->noteInfo				= noteInfo;
	this->noteInfo.bSendFlag	= TRUE;
}

BEGIN_NMFUNC_SERIALIZE( CNMSendNoteInfoFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode1( static_cast<UINT8>( this->bSaveToPermBox ) );
		OBJECT_ENCODE( this->noteInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSendNoteInfoFunc )
//{
	if ( this->IsCalling() )
	{
		this->bSaveToPermBox = decoder.Decode1();
		OBJECT_DECODE( this->noteInfo );
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMGetNoteBox2Func
//////////////////////////////////////////////////////////////////////////

CNMGetNoteBox2Func::CNMGetNoteBox2Func( void )
	: CNMFunc( kCode, 1 )
{
	this->uBoxType		= kNoteBoxType_TempBox;
	this->uNoteSerial	= 0xFFFFFFFF;
	this->uStartPtr		= 0;
}

CNMGetNoteBox2Func::CNMGetNoteBox2Func( NMNOTEBOXTYPE uBoxType, UINT32 uNoteSerial, UINT32 uStartPtr /* = 0 */ )
	: CNMFunc( kCode, 1 )
{
	this->uBoxType		= uBoxType;
	this->uNoteSerial	= uNoteSerial;
	this->uStartPtr		= uStartPtr;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetNoteBox2Func )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode1( static_cast<UINT8>( this->uBoxType ) );
		encoder.Encode4( this->uNoteSerial );
		encoder.Encode4( this->uStartPtr );
	}
	else
	{
		LIST_ENCODE( NMNoteInfoList, this->aNotes );
		encoder.Encode1( static_cast<UINT8>( this->bMore ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetNoteBox2Func )
//{
	if ( this->IsCalling() )
	{
		this->uBoxType		= ( NMNOTEBOXTYPE )decoder.Decode1();
		this->uNoteSerial	= decoder.Decode4();
		this->uStartPtr		= decoder.Decode4();
	}
	else
	{
		LIST_DECODE( NMNoteInfoList, this->aNotes );
		this->bMore			= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMGetRejectedUserListFunc
//////////////////////////////////////////////////////////////////////////

CNMGetRejectedUserListFunc::CNMGetRejectedUserListFunc()
	: CNMFunc(kCode, 1)
{
}

BEGIN_NMFUNC_SERIALIZE( CNMGetRejectedUserListFunc )
//{
	if (this->IsReturn())
	{
		LIST_ENCODE(NMRejectedUserList, this->rejectedUserList);
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetRejectedUserListFunc )
//{
	if (this->IsReturn())
	{
		LIST_DECODE(NMRejectedUserList, this->rejectedUserList);
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMAppendRejectedUserFunc
//////////////////////////////////////////////////////////////////////////

CNMAppendRejectedUserFunc::CNMAppendRejectedUserFunc()
	: CNMFunc(kCode, 1)
{
}

CNMAppendRejectedUserFunc::CNMAppendRejectedUserFunc(CNMRejectedUser & rejectedUser)
	: CNMFunc(kCode, 1)
{
	this->rejectedUser = rejectedUser;
}

BEGIN_NMFUNC_SERIALIZE( CNMAppendRejectedUserFunc )
//{
	if (this->IsCalling())
	{
		OBJECT_ENCODE(this->rejectedUser);
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMAppendRejectedUserFunc )
//{
	if (this->IsCalling())
	{
		OBJECT_DECODE(this->rejectedUser);
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMRemoveRejectedUserFunc
//////////////////////////////////////////////////////////////////////////

CNMRemoveRejectedUserFunc::CNMRemoveRejectedUserFunc()
	: CNMFunc(kCode, 1)
{
}

CNMRemoveRejectedUserFunc::CNMRemoveRejectedUserFunc(CNMRejectedUser const & rejectedUser)
	: CNMFunc(kCode, 1)
{
	this->rejectedUser = rejectedUser;
}

CNMRemoveRejectedUserFunc::CNMRemoveRejectedUserFunc(CNMCharacter const & rejectedCharacter, LPCTSTR pszMemo /* = NULL */)
	: CNMFunc(kCode, 1)
{
	this->rejectedUser = CNMRejectedUser(rejectedCharacter, pszMemo);
}

BEGIN_NMFUNC_SERIALIZE( CNMRemoveRejectedUserFunc )
//{
	if (this->IsCalling())
	{
		OBJECT_ENCODE(this->rejectedUser);
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRemoveRejectedUserFunc )
//{
	if (this->IsCalling())
	{
		OBJECT_DECODE(this->rejectedUser);
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetMyGuildListFunc
//=========================================

CNMGetMyGuildListFunc::CNMGetMyGuildListFunc( void )
	: CNMFunc( kCode, 1 )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMGetMyGuildListFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_ENCODE( this->gliGuildList );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetMyGuildListFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_DECODE( this->gliGuildList );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetMyGuildListExFunc
//=========================================

CNMGetMyGuildListExFunc::CNMGetMyGuildListExFunc( void )
	: CNMFunc( kCode, 1 )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMGetMyGuildListExFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_ENCODE( this->gliGuildList );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetMyGuildListExFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_DECODE( this->gliGuildList );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMMonitorGuildOnlineInfoFunc
//=========================================

CNMMonitorGuildOnlineInfoFunc::CNMMonitorGuildOnlineInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uGuildSerialNo	= 0;
	this->bMonitorAttach	= FALSE;
	this->uServingProcessID	= FALSE;
}

CNMMonitorGuildOnlineInfoFunc::CNMMonitorGuildOnlineInfoFunc( _UInt32_ uGuildSerialNo, BOOL bMonitorAttach )
	: CNMFunc( kCode, 1 )
{
	this->uGuildSerialNo	= uGuildSerialNo;
	this->bMonitorAttach	= bMonitorAttach;
	this->uServingProcessID	= 0;
}

CNMMonitorGuildOnlineInfoFunc::CNMMonitorGuildOnlineInfoFunc( _UInt32_ uGuildSerialNo, BOOL bMonitorAttach, BOOL uServingProcessID )
	: CNMFunc( kCode, 1 )
{
	this->uGuildSerialNo	= uGuildSerialNo;
	this->bMonitorAttach	= bMonitorAttach;
	this->uServingProcessID	= uServingProcessID;
}

BEGIN_NMFUNC_SERIALIZE( CNMMonitorGuildOnlineInfoFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGuildSerialNo );
		encoder.Encode1( static_cast<UINT8>( this->bMonitorAttach ) );
		encoder.Encode4( this->uServingProcessID );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMMonitorGuildOnlineInfoFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGuildSerialNo	= decoder.Decode4();
		this->bMonitorAttach	= decoder.Decode1();
		this->uServingProcessID	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMMonitorGuildOnlineInfoExFunc
//=========================================

CNMMonitorGuildOnlineInfoExFunc::CNMMonitorGuildOnlineInfoExFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uGuildKey			= 0;
	this->bMonitorAttach	= FALSE;
	this->uServingProcessID	= FALSE;
}

CNMMonitorGuildOnlineInfoExFunc::CNMMonitorGuildOnlineInfoExFunc( NMGuildKey uGuildKey, BOOL bMonitorAttach )
	: CNMFunc( kCode, 1 )
{
	this->uGuildKey	=		 uGuildKey;
	this->bMonitorAttach	= bMonitorAttach;
	this->uServingProcessID	= 0;
}

CNMMonitorGuildOnlineInfoExFunc::CNMMonitorGuildOnlineInfoExFunc( NMGuildKey uGuildKey, BOOL bMonitorAttach, BOOL uServingProcessID )
	: CNMFunc( kCode, 1 )
{
	this->uGuildKey			= uGuildKey;
	this->bMonitorAttach	= bMonitorAttach;
	this->uServingProcessID	= uServingProcessID;
}

BEGIN_NMFUNC_SERIALIZE( CNMMonitorGuildOnlineInfoExFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode8( this->uGuildKey );
		encoder.Encode1( static_cast<UINT8>( this->bMonitorAttach ) );
		encoder.Encode4( this->uServingProcessID );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMMonitorGuildOnlineInfoExFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGuildKey			= decoder.Decode8();
		this->bMonitorAttach	= decoder.Decode1();
		this->uServingProcessID	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMSendGuildChatMessageFunc
//=========================================

CNMSendGuildChatMessageFunc::CNMSendGuildChatMessageFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uGuildKey			= 0;
	::ZeroMemory( this->uToIDCode, sizeof( this->uToIDCode ) );
	this->uMemberLevel		= 0;
}

CNMSendGuildChatMessageFunc::CNMSendGuildChatMessageFunc( NMGuildKey uGuildKey, _UInt8_ uMemberLevel, NMIDCode uToIDCode[ 10 ], CNMChatMessage cmChatMsg )
	: CNMFunc( kCode, 1 )
{
	this->uGuildKey			= uGuildKey;
	::CopyMemory( this->uToIDCode, uToIDCode, sizeof( this->uToIDCode ) );
	this->uMemberLevel		= uMemberLevel;
	this->cmChatMsg			= cmChatMsg;
}

BEGIN_NMFUNC_SERIALIZE( CNMSendGuildChatMessageFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode8( this->uGuildKey );
		for ( _UInt32_ i = 0; i < 10; ++i )
			encoder.Encode8( this->uToIDCode[ i ] );
		encoder.Encode1( this->uMemberLevel );
		OBJECT_ENCODE( this->cmChatMsg );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSendGuildChatMessageFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGuildKey				= decoder.Decode8();
		for ( _UInt32_ i = 0; i < 10; ++i )
			this->uToIDCode[ i ]	= decoder.Decode8();
		this->uMemberLevel			= decoder.Decode1();
		OBJECT_DECODE( this->cmChatMsg );
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMGetGuildListFunc
//////////////////////////////////////////////////////////////////////////

CNMGetGuildListFunc::CNMGetGuildListFunc() : CNMFunc( kCode, 1 )
{
}

CNMGetGuildListFunc::CNMGetGuildListFunc( CNMGuildListExInfo guildList, CNMCharacter gameCharacter ) : CNMFunc( kCode, 1 )
{
	this->guildList		= guildList;
	this->gameCharacter	= gameCharacter;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetGuildListFunc )
//{
	if (this->IsCalling())
	{
		OBJECT_ENCODE(this->gameCharacter);
	}
	else
	{
		OBJECT_ENCODE(this->guildList);
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetGuildListFunc )
//{
	if (this->IsCalling())
	{
		OBJECT_DECODE(this->gameCharacter);
	}
	else
	{
		OBJECT_DECODE(this->guildList);
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMExecutePatcherFunc
//=========================================

CNMExecutePatcherFunc::CNMExecutePatcherFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szUrl[ 0 ]			= 0;
	this->szPatchDir[ 0 ]		= 0;
	this->szExecCmdLine[ 0 ]	= 0;
	this->bWaitParentExit		= TRUE;
	this->bWaitPatchEnd			= FALSE;
}

CNMExecutePatcherFunc::CNMExecutePatcherFunc( LPCTSTR szUrl, LPCTSTR szPatchDir, LPCTSTR szExecCmdLine, BOOL bWaitParentExit, BOOL bWaitPatchEnd )
	: CNMFunc( kCode, 1 )
{
	INIT_STRING2( szUrl, 256 );
	INIT_STRING2( szPatchDir, 256 );
	INIT_STRING2( szExecCmdLine, 2000 );
	this->bWaitParentExit	= bWaitParentExit;
	this->bWaitPatchEnd		= bWaitPatchEnd;
}

BEGIN_NMFUNC_SERIALIZE( CNMExecutePatcherFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szUrl );
		encoder.EncodeString( this->szPatchDir );
		encoder.EncodeString( this->szExecCmdLine );
		encoder.Encode4( this->bWaitParentExit );
		encoder.Encode4( this->bWaitPatchEnd );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMExecutePatcherFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szUrl, 256 );
		decoder.DecodeString( this->szPatchDir, 256 );
		decoder.DecodeString( this->szExecCmdLine, 2000 );
		this->bWaitParentExit	= decoder.Decode4();
		this->bWaitPatchEnd		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMExecuteLauncherFunc
//=========================================

CNMExecuteLauncherFunc::CNMExecuteLauncherFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode				= kGameCode_NULL;
	this->uLaunchType			= 0;
	this->szArgument[ 0 ]		= 0;
	this->bWaitParentExit		= TRUE;
}

CNMExecuteLauncherFunc::CNMExecuteLauncherFunc( _UInt32_ uGameCode, _UInt32_ uLaunchType, LPCTSTR szArgument, BOOL bWaitParentExit )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode			= uGameCode;
	this->uLaunchType		= uLaunchType;
	INIT_STRING2( szArgument, 2000 );
	this->bWaitParentExit	= bWaitParentExit;
}

BEGIN_NMFUNC_SERIALIZE( CNMExecuteLauncherFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
		encoder.Encode4( this->uLaunchType );
		encoder.EncodeString( this->szArgument );
		encoder.Encode4( this->bWaitParentExit );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMExecuteLauncherFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode			= decoder.Decode4();
		this->uLaunchType		= decoder.Decode4();
		decoder.DecodeString( this->szArgument, 2000 );
		this->bWaitParentExit	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMExecuteNGMPatcherFunc
//=========================================

CNMExecuteNGMPatcherFunc::CNMExecuteNGMPatcherFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode				= kGameCode_NULL;
	this->szUrl[ 0 ]			= 0;
	this->szPatchDir[ 0 ]		= 0;
	this->szExecCmdLine[ 0 ]	= 0;
	this->bWaitParentExit		= TRUE;
	this->bSmallDialog			= FALSE;
}

CNMExecuteNGMPatcherFunc::CNMExecuteNGMPatcherFunc( _UInt32_ uGameCode, LPCTSTR szUrl, LPCTSTR szPatchDir, LPCTSTR szExecCmdLine, BOOL bWaitParentExit, BOOL bSmallDialog )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode			= uGameCode;
	INIT_STRING2( szUrl, 256 );
	INIT_STRING2( szPatchDir, 256 );
	INIT_STRING2( szExecCmdLine, 1024 );
	this->bWaitParentExit	= bWaitParentExit;
	this->bSmallDialog		= bSmallDialog;
}

BEGIN_NMFUNC_SERIALIZE( CNMExecuteNGMPatcherFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
		encoder.EncodeString( this->szUrl );
		encoder.EncodeString( this->szPatchDir );
		encoder.EncodeString( this->szExecCmdLine );
		encoder.Encode4( this->bWaitParentExit );
		encoder.Encode4( this->bSmallDialog );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMExecuteNGMPatcherFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode			= decoder.Decode4();
		decoder.DecodeString( this->szUrl, 256 );
		decoder.DecodeString( this->szPatchDir, 256 );
		decoder.DecodeString( this->szExecCmdLine, 1024 );
		this->bWaitParentExit	= decoder.Decode4();
		this->bSmallDialog		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMExecuteNGMLauncherFunc
//=========================================

CNMExecuteNGMLauncherFunc::CNMExecuteNGMLauncherFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode				= kGameCode_NULL;
	this->uLaunchType			= 0;
	this->szArgument[ 0 ]		= 0;
	this->bWaitParentExit		= TRUE;
	this->szPassport[ 0 ]		= 0;
}

CNMExecuteNGMLauncherFunc::CNMExecuteNGMLauncherFunc( _UInt32_ uGameCode, _UInt32_ uLaunchType, LPCTSTR szArgument, BOOL bWaitParentExit, LPCTSTR szPassport )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode			= uGameCode;
	this->uLaunchType		= uLaunchType;
	INIT_STRING2( szArgument, 1024 );
	this->bWaitParentExit	= bWaitParentExit;
	INIT_STRING2( szPassport, 1024 );
}

BEGIN_NMFUNC_SERIALIZE( CNMExecuteNGMLauncherFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
		encoder.Encode4( this->uLaunchType );
		encoder.EncodeString( this->szArgument );
		encoder.Encode4( this->bWaitParentExit );
		encoder.EncodeString( this->szPassport );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMExecuteNGMLauncherFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode			= decoder.Decode4();
		this->uLaunchType		= decoder.Decode4();
		decoder.DecodeString( this->szArgument, 1024 );
		this->bWaitParentExit	= decoder.Decode4();
		decoder.DecodeString( this->szPassport, 1024 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMIsNGMInstalledFunc
//=========================================

CNMIsNGMInstalledFunc::CNMIsNGMInstalledFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->bInstalled			= FALSE;
}

BEGIN_NMFUNC_SERIALIZE( CNMIsNGMInstalledFunc )
//{
	if ( this->IsCalling() )
	{
	}
	else
	{
		encoder.Encode4( this->bInstalled );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMIsNGMInstalledFunc )
//{
	if ( this->IsCalling() )
	{
	}
	else
	{
		this->bInstalled	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMExecuteCommonFunc
//=========================================

CNMExecuteCommonFunc::CNMExecuteCommonFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uType				= 0;
	this->szArgument[ 0 ]	= 0;
}

CNMExecuteCommonFunc::CNMExecuteCommonFunc( _UInt32_ uType, LPCTSTR szArgument )
	: CNMFunc( kCode, 1 )
{
	this->uType				= uType;
	INIT_STRING2( szArgument, DOMAIN_SIZE );
}

BEGIN_NMFUNC_SERIALIZE( CNMExecuteCommonFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uType );
		encoder.EncodeString( this->szArgument );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMExecuteCommonFunc )
//{
	if ( this->IsCalling() )
	{
		this->uType	= decoder.Decode4();
		decoder.DecodeString( this->szArgument, DOMAIN_SIZE );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetUrlFunc
//=========================================

CNMGetUrlFunc::CNMGetUrlFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uType			= kURLType_Page;
	this->uPage			= kURL_NULL;
	this->uGameCode		= kGameCode_NULL;
	this->uGuildSerial	= 0;
	this->szUrl[ 0 ]	= NULL;
	this->uParam		= 0;
	this->szParam[ 0 ]	= NULL;
	this->bLoginDirect	= FALSE;
	this->bIsPopup		= FALSE;
	this->bNoActiveX	= FALSE;
	this->szUrlOut[ 0 ]	= NULL;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetUrlFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uType );

		if ( this->uType == kURLType_Page )			encoder.Encode4( this->uPage );
		if ( this->uType == kURLType_Game )			encoder.Encode4( this->uGameCode );
		if ( this->uType == kURLType_Guild )		encoder.Encode4( this->uGuildSerial );
		if ( this->uType == kURLType_DirectURL )	encoder.EncodeString( this->szUrl );

		encoder.Encode4( this->uParam );
		encoder.EncodeString( this->szParam );
		encoder.Encode1( static_cast<UINT8>( this->bLoginDirect ) );
		encoder.Encode1( static_cast<UINT8>( this->bIsPopup ) );
		encoder.Encode1( static_cast<UINT8>( this->bNoActiveX ) );
	}
	else
	{
		encoder.EncodeString( this->szUrlOut );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetUrlFunc )
//{
	if ( this->IsCalling() )
	{
		this->uType			= ( NMURLTYPE )decoder.Decode4();

		if ( this->uType == kURLType_Page )			this->uPage			= ( NMURLPAGE )decoder.Decode4();
		if ( this->uType == kURLType_Game )			this->uGameCode		= decoder.Decode4();
		if ( this->uType == kURLType_Guild )		this->uGuildSerial	= decoder.Decode4();
		if ( this->uType == kURLType_DirectURL )	decoder.DecodeString( this->szUrl, 256 );

		this->uParam		= decoder.Decode4();
		decoder.DecodeString( this->szParam, 256 );
		this->bLoginDirect	= decoder.Decode1();
		this->bIsPopup		= decoder.Decode1();
		this->bNoActiveX	= decoder.Decode1();
	}
	else
	{
		decoder.DecodeString( this->szUrlOut, 1024 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetUrlExFunc
//=========================================

CNMGetUrlExFunc::CNMGetUrlExFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uType			= kURLType_Page;
	this->uPage			= kURL_NULL;
	this->uGameCode		= kGameCode_NULL;
	this->uGuildKey		= 0;
	this->szUrl[ 0 ]	= NULL;
	this->uParam		= 0;
	this->szParam[ 0 ]	= NULL;
	this->bLoginDirect	= FALSE;
	this->bIsPopup		= FALSE;
	this->bNoActiveX	= FALSE;
	this->uMaskGameCode	= kGameCode_nexoncom;
	this->szUrlOut[ 0 ]	= NULL;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetUrlExFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uType );

		if ( this->uType == kURLType_Page )			encoder.Encode4( this->uPage );
		if ( this->uType == kURLType_Game )			encoder.Encode4( this->uGameCode );
		if ( this->uType == kURLType_Guild )		encoder.Encode8( this->uGuildKey );
		if ( this->uType == kURLType_DirectURL )	encoder.EncodeString( this->szUrl );

		encoder.Encode4( this->uParam );
		encoder.EncodeString( this->szParam );
		encoder.Encode1( static_cast<UINT8>( this->bLoginDirect ) );
		encoder.Encode1( static_cast<UINT8>( this->bIsPopup ) );
		encoder.Encode1( static_cast<UINT8>( this->bNoActiveX ) );
		encoder.Encode4( this->uMaskGameCode );
	}
	else
	{
		encoder.EncodeString( this->szUrlOut );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetUrlExFunc )
//{
	if ( this->IsCalling() )
	{
		this->uType			= ( NMURLTYPE )decoder.Decode4();

		if ( this->uType == kURLType_Page )			this->uPage			= ( NMURLPAGE )decoder.Decode4();
		if ( this->uType == kURLType_Game )			this->uGameCode		= decoder.Decode4();
		if ( this->uType == kURLType_Guild )		this->uGuildKey		= decoder.Decode8();
		if ( this->uType == kURLType_DirectURL )	decoder.DecodeString( this->szUrl, 256 );

		this->uParam		= decoder.Decode4();
		decoder.DecodeString( this->szParam, 256 );
		this->bLoginDirect	= decoder.Decode1();
		this->bIsPopup		= decoder.Decode1();
		this->bNoActiveX	= decoder.Decode1();
		this->uMaskGameCode	= decoder.Decode4();
	}
	else
	{
		decoder.DecodeString( this->szUrlOut, 1024 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMDownloadFileFunc
//=========================================

CNMDownloadFileFunc::CNMDownloadFileFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szUrl[ 0 ]		= NULL;
	this->szFileName[ 0 ]	= NULL;
}

CNMDownloadFileFunc::CNMDownloadFileFunc( LPCTSTR szUrl, LPCTSTR szFileName )
	: CNMFunc( kCode, 1 )
{
	INIT_STRING2( szUrl, 256 );
	INIT_STRING2( szFileName, 256 );
}

BEGIN_NMFUNC_SERIALIZE( CNMDownloadFileFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szUrl );
		encoder.EncodeString( this->szFileName );
	}
	else
	{
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMDownloadFileFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szUrl, 256 );
		decoder.DecodeString( this->szFileName, 256 );
	}
	else
	{
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMUploadFileFunc
//=========================================

CNMUploadFileFunc::CNMUploadFileFunc( void )
: CNMFunc( kCode, 1 )
{
	this->szUrl[ 0 ]		= NULL;
	this->szFileName[ 0 ]	= NULL;
	this->szPostField[ 0 ]	= NULL;
	this->szResponse[ 0 ]	= NULL;
}

CNMUploadFileFunc::CNMUploadFileFunc( LPCTSTR szUrl, LPCTSTR szFileName, LPCTSTR szPostField )
: CNMFunc( kCode, 1 )
{
	INIT_STRING2( szUrl, 256 );
	INIT_STRING2( szFileName, 256 );
	INIT_STRING2( szPostField, 256 );
	this->szResponse[ 0 ]	= NULL;
}

BEGIN_NMFUNC_SERIALIZE( CNMUploadFileFunc )
//{
if ( this->IsCalling() )
{
	encoder.EncodeString( this->szUrl );
	encoder.EncodeString( this->szFileName );
	encoder.EncodeString( this->szPostField );
}
else
{
	encoder.EncodeString( this->szResponse );
}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMUploadFileFunc )
//{
if ( this->IsCalling() )
{
	decoder.DecodeString( this->szUrl, 256 );
	decoder.DecodeString( this->szFileName, 256 );
	decoder.DecodeString( this->szPostField, 256 );
}
else
{
	decoder.DecodeString( this->szResponse, 1024 );
}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetSupportGameListFunc
//=========================================

CNMGetSupportGameListFunc::CNMGetSupportGameListFunc( void )
: CNMFunc( kCode, 1 )
{
	this->bAll = TRUE;
}

CNMGetSupportGameListFunc::CNMGetSupportGameListFunc( BOOL bAll )
: CNMFunc( kCode, 1 )
{
	this->bAll = bAll;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetSupportGameListFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode1( static_cast<UINT8>( this->bAll )  );
	}
	else
	{
		LIST_ENCODE( NMGameCodeList, this->aGameCodeList );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetSupportGameListFunc )
//{
	if ( this->IsCalling() )
	{
		this->bAll = decoder.Decode1();
	}
	else
	{
		LIST_DECODE( NMGameCodeList, this->aGameCodeList );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetGameServerListFunc
//=========================================

CNMGetGameServerListFunc::CNMGetGameServerListFunc( void )
: CNMFunc( kCode, 1 )
{
	this->uGameCode		= kGameCode_NULL;
}

CNMGetGameServerListFunc::CNMGetGameServerListFunc( NMGameCode uGameCode )
: CNMFunc( kCode, 1 )
{
	this->uGameCode		= uGameCode;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetGameServerListFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
	}
	else
	{
		LIST_ENCODE( NMGameServerCodeList, this->aGameServerCodeList );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetGameServerListFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode = decoder.Decode4();
	}
	else
	{
		LIST_DECODE( NMGameServerCodeList, this->aGameServerCodeList );
	}
	//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetGameFullNameFunc
//=========================================

CNMGetGameFullNameFunc::CNMGetGameFullNameFunc( void )
: CNMFunc( kCode, 1 )
{
	this->uGameCode				= kGameCode_NULL;
	this->szGameFullName[ 0 ]	= NULL;
}

CNMGetGameFullNameFunc::CNMGetGameFullNameFunc( NMGameCode uGameCode )
: CNMFunc( kCode, 1 )
{
	this->uGameCode				= uGameCode;
	this->szGameFullName[ 0 ]	= NULL;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetGameFullNameFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
	}
	else
	{
		encoder.EncodeString( this->szGameFullName );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetGameFullNameFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode = decoder.Decode4();
	}
	else
	{
		decoder.DecodeString( this->szGameFullName, 128 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetGameShortNameFunc
//=========================================

CNMGetGameShortNameFunc::CNMGetGameShortNameFunc( void )
: CNMFunc( kCode, 1 )
{
	this->uGameCode				= kGameCode_NULL;
	this->szGameShortName[ 0 ]	= NULL;
}

CNMGetGameShortNameFunc::CNMGetGameShortNameFunc( NMGameCode uGameCode )
: CNMFunc( kCode, 1 )
{
	this->uGameCode				= uGameCode;
	this->szGameShortName[ 0 ]	= NULL;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetGameShortNameFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
	}
	else
	{
		encoder.EncodeString( this->szGameShortName );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetGameShortNameFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode = decoder.Decode4();
	}
	else
	{
		decoder.DecodeString( this->szGameShortName, 128 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetGameFriendTitleFunc
//=========================================

CNMGetGameFriendTitleFunc::CNMGetGameFriendTitleFunc( void )
: CNMFunc( kCode, 1 )
{
	this->uGameCode					= kGameCode_NULL;
	this->szGameFriendTitle[ 0 ]	= NULL;
}

CNMGetGameFriendTitleFunc::CNMGetGameFriendTitleFunc( NMGameCode uGameCode )
: CNMFunc( kCode, 1 )
{
	this->uGameCode					= uGameCode;
	this->szGameFriendTitle[ 0 ]	= NULL;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetGameFriendTitleFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
	}
	else
	{
		encoder.EncodeString( this->szGameFriendTitle );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetGameFriendTitleFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode = decoder.Decode4();
	}
	else
	{
		decoder.DecodeString( this->szGameFriendTitle, 128 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetGameServerNameFunc
//=========================================

CNMGetGameServerNameFunc::CNMGetGameServerNameFunc( void )
: CNMFunc( kCode, 1 )
{
	this->uGameCode				= kGameCode_NULL;
	this->uGameServerCode		= 0;
	this->szGameServerName[ 0 ]	= NULL;
}

CNMGetGameServerNameFunc::CNMGetGameServerNameFunc( NMGameCode uGameCode, NMGameServerCode uGameServerCode )
: CNMFunc( kCode, 1 )
{
	this->uGameCode				= uGameCode;
	this->uGameServerCode		= uGameServerCode;
	this->szGameServerName[ 0 ]	= NULL;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetGameServerNameFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
		encoder.Encode4( this->uGameServerCode );
	}
	else
	{
		encoder.EncodeString( this->szGameServerName );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetGameServerNameFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode			= decoder.Decode4();
		this->uGameServerCode	= decoder.Decode4();
	}
	else
	{
		decoder.DecodeString( this->szGameServerName, 128 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetConnConfigFunc
//=========================================

CNMGetConnConfigFunc::CNMGetConnConfigFunc( void )
	: CNMFunc( kCode, 1 )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMGetConnConfigFunc )
//{
	if ( this->IsCalling() )
	{
	}
	else
	{
		OBJECT_ENCODE( this->m_ConnConfig );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetConnConfigFunc )
//{
	if ( this->IsCalling() )
	{
	}
	else
	{
		OBJECT_DECODE( this->m_ConnConfig );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMSetConnConfigFunc
//=========================================

CNMSetConnConfigFunc::CNMSetConnConfigFunc( void )
	: CNMFunc( kCode, 1 )
{
	m_ConnConfig.szLoginServerIp[0]	= 0;
	m_ConnConfig.uLoginServerPort	= 0;
	m_ConnConfig.szStatServerIp[0]	= 0;
	m_ConnConfig.uStatServerPort	= 0;
	m_ConnConfig.bAutoPatch			= TRUE;
}

CNMSetConnConfigFunc::CNMSetConnConfigFunc( const CNMConnConfig & ConnConfig )
	: CNMFunc( kCode, 1 )
{
	m_ConnConfig = ConnConfig;
}

BEGIN_NMFUNC_SERIALIZE( CNMSetConnConfigFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->m_ConnConfig );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSetConnConfigFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->m_ConnConfig );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetPathFunc
//=========================================

CNMGetPathFunc::CNMGetPathFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uPathType	= kCommon;
}

CNMGetPathFunc::CNMGetPathFunc( _UInt32_ uPathType )
	: CNMFunc( kCode, 1 )
{
	this->uPathType	= uPathType;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetPathFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uPathType );
	}
	else
	{
		encoder.EncodeString( this->szPath );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetPathFunc )
//{
	if ( this->IsCalling() )
	{
		this->uPathType	= decoder.Decode4();
	}
	else
	{
		decoder.DecodeString( this->szPath, MAX_PATH );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMSetSessionValueFunc
//=========================================

CNMSetSessionValueFunc::CNMSetSessionValueFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szName[ 0 ]		= 0;
	this->szValue[ 0 ]		= 0;
	this->bAttachToProcess	= TRUE;
	this->uProcessId		= 0;
}

CNMSetSessionValueFunc::CNMSetSessionValueFunc( LPCTSTR szName, LPCTSTR szValue, BOOL bAttachToProcess )
	: CNMFunc( kCode, 1 )
{
	INIT_STRING2( szName, 32 );
	INIT_STRING2( szValue, 256 );
	this->bAttachToProcess	= bAttachToProcess;
	this->uProcessId		= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMSetSessionValueFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szName );
		encoder.EncodeString( this->szValue );
		encoder.Encode1( static_cast<UINT8>( this->bAttachToProcess ) );
		encoder.Encode4( this->uProcessId );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSetSessionValueFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szName, 32 );
		decoder.DecodeString( this->szValue, 256 );
		this->bAttachToProcess	= decoder.Decode1();
		this->uProcessId		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetSessionValueFunc
//=========================================

CNMGetSessionValueFunc::CNMGetSessionValueFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szName[ 0 ]		= 0;
	this->szValue[ 0 ]		= 0;
}

CNMGetSessionValueFunc::CNMGetSessionValueFunc( LPCTSTR szName )
	: CNMFunc( kCode, 1 )
{
	INIT_STRING2( szName, 32 );
	this->szValue[ 0 ]		= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetSessionValueFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szName );
	}
	else
	{
		encoder.EncodeString( this->szValue );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetSessionValueFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szName, 32 );
	}
	else
	{
		decoder.DecodeString( this->szValue, 256 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetGameListFunc
//=========================================

CNMGetGameListFunc::CNMGetGameListFunc( void )
	: CNMFunc( kCode, 1 )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMGetGameListFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_ENCODE( this->glGameList );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetGameListFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_DECODE( this->glGameList );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetUserInfoFunc
//=========================================

CNMGetUserInfoFunc::CNMGetUserInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szTargetID[ 0 ]	= NULL;
	this->uGameCode			= 0;
}

CNMGetUserInfoFunc::CNMGetUserInfoFunc( LPCTSTR szTargetID, _UInt32_ uGameCode )
	: CNMFunc( kCode, 1 )
{
	INIT_STRING2( szTargetID, LOGINID_SIZE );
	this->uGameCode			= uGameCode;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetUserInfoFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szTargetID );
		encoder.Encode4( this->uGameCode );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetUserInfoFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szTargetID, LOGINID_SIZE );
		this->uGameCode		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMFindUserFunc
//=========================================

CNMFindUserFunc::CNMFindUserFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uToken			= 0;
}

CNMFindUserFunc::CNMFindUserFunc( CNMFindUserRequestInfo& furiRequestInfo )
	: CNMFunc( kCode, 1 )
{
	this->uToken			= 0;
	this->furiRequestInfo	= furiRequestInfo;
}

BEGIN_NMFUNC_SERIALIZE( CNMFindUserFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->furiRequestInfo );
	}
	else
	{
		encoder.Encode4( this->uToken );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMFindUserFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->furiRequestInfo );
	}
	else
	{
		this->uToken	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGetFindUserResultFunc
//=========================================

CNMGetFindUserResultFunc::CNMGetFindUserResultFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uToken	= 0;
}

CNMGetFindUserResultFunc::CNMGetFindUserResultFunc( _UInt32_ uToken )
	: CNMFunc( kCode, 1 )
{
	this->uToken	= uToken;
}

BEGIN_NMFUNC_SERIALIZE( CNMGetFindUserResultFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uToken );
	}
	else
	{
		LIST_ENCODE( NMFindUserInfoList, this->aFoundUser );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGetFindUserResultFunc )
//{
	if ( this->IsCalling() )
	{
		this->uToken	= decoder.Decode4();
	}
	else
	{
		LIST_DECODE( NMFindUserInfoList, this->aFoundUser );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMSendNoteExMsgFunc
//=========================================

CNMSendNoteExMsgFunc::CNMSendNoteExMsgFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMSendNoteExMsgFunc::CNMSendNoteExMsgFunc( CNMNoteInfoEx::NMNOTEEXTYPE uNoteExType, LPCTSTR szMessage )
	: CNMFunc( kCode, 1 )
{
	this->nieNoteEx.uNoteExType	= uNoteExType;
	INIT_STRING( this->nieNoteEx.szMessage, szMessage, NOTEMSG_SIZE );
}

BEGIN_NMFUNC_SERIALIZE( CNMSendNoteExMsgFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->nieNoteEx );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSendNoteExMsgFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->nieNoteEx );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMSendReportMsgFunc
//=========================================

CNMSendReportMsgFunc::CNMSendReportMsgFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMSendReportMsgFunc::CNMSendReportMsgFunc( CNMReportInfo::NMREPORTTYPE uType, CNMReportInfo::NMREPORTSUBTYPE uSubType, LPCTSTR szTargetId, LPCTSTR szReason, LPCTSTR szEvidence, _UInt32_ uGameCode, _UInt32_ uAppliedGameCode )
	: CNMFunc( kCode, 1 )
{
	this->riReportInfo.uType			= uType;
	this->riReportInfo.uSubType			= uSubType;
	this->riReportInfo.uGameCode		= uGameCode;
	this->riReportInfo.uAppliedGameCode	= uAppliedGameCode;
	INIT_STRING( this->riReportInfo.szTargetId, szTargetId, LOGINID_SIZE );
	INIT_STRING( this->riReportInfo.szReason, szReason, 4000 );
	INIT_STRING( this->riReportInfo.szEvidence, szEvidence, 20000 );
}

BEGIN_NMFUNC_SERIALIZE( CNMSendReportMsgFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->riReportInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMSendReportMsgFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->riReportInfo );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestChatSessionFunc
//=========================================

CNMRequestChatSessionFunc::CNMRequestChatSessionFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDCODE( this->fcFriendCode );
	this->uServingProcessID			= 0;

	this->uExistingSerialKey		= 0;
	this->uCreatedSerialKey			= 0;
}

CNMRequestChatSessionFunc::CNMRequestChatSessionFunc( NMFriendCode fcFriendCode, _UInt32_ uServingProcessID )
	: CNMFunc( kCode, 1 )
{
	this->fcFriendCode		= fcFriendCode;
	this->uServingProcessID	= uServingProcessID;

	this->uExistingSerialKey		= 0;
	this->uCreatedSerialKey			= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestChatSessionFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDCODE( this->fcFriendCode );
		encoder.Encode4( this->uServingProcessID );
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uExistingSerialKey );
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestChatSessionFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDCODE( this->fcFriendCode );
		this->uServingProcessID		= decoder.Decode4();
	}
	else if ( this->IsReturn() )
	{
		this->uExistingSerialKey	= decoder.Decode4();
		this->uCreatedSerialKey		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestMultiChatSessionFunc
//=========================================

CNMRequestMultiChatSessionFunc::CNMRequestMultiChatSessionFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyVirtuality );
	this->uServingProcessID			= 0;

	this->uCreatedSerialKey			= 0;
}

CNMRequestMultiChatSessionFunc::CNMRequestMultiChatSessionFunc( NMVirtualKey keyVirtuality, NMFriendCodeList& aFriendCodes, _UInt32_ uServingProcessID )
	: CNMFunc( kCode, 1 )
{
	this->keyVirtuality			= keyVirtuality;
	this->aFriendCodes			= aFriendCodes;
	this->uServingProcessID		= uServingProcessID;

	this->uCreatedSerialKey		= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestMultiChatSessionFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_VIRTUALKEY( this->keyVirtuality );
		encoder.Encode4( this->uServingProcessID );
		encoder.Encode4( static_cast<_UInt32_>( this->aFriendCodes.size() ) );
		ITERATE_CONST( NMFriendCodeList, this->aFriendCodes, iter )
		{
			ENCODE_FRIENDCODE( (*iter) );
		}
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestMultiChatSessionFunc )
//{
	if ( this->IsCalling() )
	{
		_UInt32_		uTargetCount;

		DECODE_VIRTUALKEY( this->keyVirtuality );
		this->uServingProcessID	= decoder.Decode4();
		uTargetCount			= decoder.Decode4();

		this->aFriendCodes.clear();

		for ( _UInt32_ i = 0; i < uTargetCount; ++i )
		{
			NMFriendCode	fcFriendCode;

			DECODE_FRIENDCODE( fcFriendCode );
			this->aFriendCodes.push_back( fcFriendCode );
		}
	}
	else if ( this->IsReturn() )
	{
		this->uCreatedSerialKey	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestFileUploadSessionFunc
//=========================================

CNMRequestFileUploadSessionFunc::CNMRequestFileUploadSessionFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDCODE( this->fcFriendCode );
	this->uServingProcessID		= 0;

	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}

CNMRequestFileUploadSessionFunc::CNMRequestFileUploadSessionFunc( NMFriendCode fcFriendCode, _UInt32_ uServingProcessID, NMFilePathList aFilePath )
	: CNMFunc( kCode, 1 )
{
	this->fcFriendCode			= fcFriendCode;
	this->uServingProcessID		= uServingProcessID;
	this->aFilePath				= aFilePath;

	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestFileUploadSessionFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDCODE( this->fcFriendCode );
		encoder.Encode4( this->uServingProcessID );
		LIST_ENCODE( NMFilePathList, this->aFilePath );
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uExistingSerialKey );
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestFileUploadSessionFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDCODE( this->fcFriendCode );
		this->uServingProcessID	= decoder.Decode4();
		LIST_DECODE( NMFilePathList, this->aFilePath );
	}
	else if ( this->IsReturn() )
	{
		this->uExistingSerialKey	= decoder.Decode4();
		this->uCreatedSerialKey		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestWBSessionFunc
//=========================================

CNMRequestWBSessionFunc::CNMRequestWBSessionFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDCODE( this->fcFriendCode );
	this->uAssocSerialKey		= 0;
	this->uServingProcessID		= 0;

	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}

CNMRequestWBSessionFunc::CNMRequestWBSessionFunc( NMFriendCode fcFriendCode, _UInt32_ uAssocSerialKey, _UInt32_ uServingProcessID )
	: CNMFunc( kCode, 1 )
{
	this->fcFriendCode			= fcFriendCode;
	this->uAssocSerialKey		= uAssocSerialKey;
	this->uServingProcessID		= uServingProcessID;

	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestWBSessionFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDCODE( this->fcFriendCode );
		encoder.Encode4( this->uAssocSerialKey );
		encoder.Encode4( this->uServingProcessID );
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uExistingSerialKey );
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestWBSessionFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDCODE( this->fcFriendCode );
		this->uAssocSerialKey		= decoder.Decode4();
		this->uServingProcessID		= decoder.Decode4();
	}
	else if ( this->IsReturn() )
	{
		this->uExistingSerialKey	= decoder.Decode4();
		this->uCreatedSerialKey		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestChatSessionExFunc
//=========================================

CNMRequestChatSessionExFunc::CNMRequestChatSessionExFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDKEY( this->keyFriend );
	this->uServingProcessID			= 0;

	this->uExistingSerialKey		= 0;
	this->uCreatedSerialKey			= 0;
}

CNMRequestChatSessionExFunc::CNMRequestChatSessionExFunc( NMFriendKey keyFriend, _UInt32_ uServingProcessID )
	: CNMFunc( kCode, 1 )
{
	this->keyFriend			= keyFriend;
	this->uServingProcessID	= uServingProcessID;

	this->uExistingSerialKey		= 0;
	this->uCreatedSerialKey			= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestChatSessionExFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDKEY( this->keyFriend );
		encoder.Encode4( this->uServingProcessID );
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uExistingSerialKey );
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestChatSessionExFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDKEY( this->keyFriend );
		this->uServingProcessID		= decoder.Decode4();
	}
	else if ( this->IsReturn() )
	{
		this->uExistingSerialKey	= decoder.Decode4();
		this->uCreatedSerialKey		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestMultiChatSessionExFunc
//=========================================

CNMRequestMultiChatSessionExFunc::CNMRequestMultiChatSessionExFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyVirtuality );
	this->uServingProcessID			= 0;

	this->uCreatedSerialKey			= 0;
}

CNMRequestMultiChatSessionExFunc::CNMRequestMultiChatSessionExFunc( NMVirtualKey keyVirtuality, NMFriendKeyList& aFriendKey, _UInt32_ uServingProcessID )
	: CNMFunc( kCode, 1 )
{
	this->keyVirtuality			= keyVirtuality;
	this->aFriendKey			= aFriendKey;
	this->uServingProcessID		= uServingProcessID;

	this->uCreatedSerialKey		= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestMultiChatSessionExFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_VIRTUALKEY( this->keyVirtuality );
		encoder.Encode4( this->uServingProcessID );
		encoder.Encode4( static_cast<_UInt32_>( this->aFriendKey.size() ) );
		ITERATE_CONST( NMFriendKeyList, this->aFriendKey, iter )
		{
			ENCODE_FRIENDKEY( (*iter) );
		}
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestMultiChatSessionExFunc )
//{
	if ( this->IsCalling() )
	{
		_UInt32_		uTargetCount;

		DECODE_VIRTUALKEY( this->keyVirtuality );
		this->uServingProcessID	= decoder.Decode4();
		uTargetCount			= decoder.Decode4();

		this->aFriendKey.clear();

		for ( _UInt32_ i = 0; i < uTargetCount; ++i )
		{
			NMFriendKey	keyFriend;

			DECODE_FRIENDKEY( keyFriend );
			this->aFriendKey.push_back( keyFriend );
		}
	}
	else if ( this->IsReturn() )
	{
		this->uCreatedSerialKey	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestFileUploadSessionExFunc
//=========================================

CNMRequestFileUploadSessionExFunc::CNMRequestFileUploadSessionExFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDKEY( this->keyFriend );
	this->uServingProcessID		= 0;

	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}

CNMRequestFileUploadSessionExFunc::CNMRequestFileUploadSessionExFunc( NMFriendKey keyFriend, _UInt32_ uServingProcessID, NMFilePathList aFilePath )
	: CNMFunc( kCode, 1 )
{
	this->keyFriend				= keyFriend;
	this->uServingProcessID		= uServingProcessID;
	this->aFilePath				= aFilePath;

	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestFileUploadSessionExFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDKEY( this->keyFriend );
		encoder.Encode4( this->uServingProcessID );
		LIST_ENCODE( NMFilePathList, this->aFilePath );
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uExistingSerialKey );
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestFileUploadSessionExFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDKEY( this->keyFriend );
		this->uServingProcessID	= decoder.Decode4();
		LIST_DECODE( NMFilePathList, this->aFilePath );
	}
	else if ( this->IsReturn() )
	{
		this->uExistingSerialKey	= decoder.Decode4();
		this->uCreatedSerialKey		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestWBSessionExFunc
//=========================================

CNMRequestWBSessionExFunc::CNMRequestWBSessionExFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDKEY( this->keyFriend );
	this->uAssocSerialKey		= 0;
	this->uServingProcessID		= 0;

	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}

CNMRequestWBSessionExFunc::CNMRequestWBSessionExFunc( NMFriendKey keyFriend, _UInt32_ uAssocSerialKey, _UInt32_ uServingProcessID )
	: CNMFunc( kCode, 1 )
{
	this->keyFriend				= keyFriend;
	this->uAssocSerialKey		= uAssocSerialKey;
	this->uServingProcessID		= uServingProcessID;

	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestWBSessionExFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDKEY( this->keyFriend );
		encoder.Encode4( this->uAssocSerialKey );
		encoder.Encode4( this->uServingProcessID );
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uExistingSerialKey );
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestWBSessionExFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDKEY( this->keyFriend );
		this->uAssocSerialKey		= decoder.Decode4();
		this->uServingProcessID		= decoder.Decode4();
	}
	else if ( this->IsReturn() )
	{
		this->uExistingSerialKey	= decoder.Decode4();
		this->uCreatedSerialKey		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	CNMJoinToDefaultGuildChatRoomFunc
//////////////////////////////////////////////////////////////////////////
CNMJoinToDefaultGuildChatRoomFunc::CNMJoinToDefaultGuildChatRoomFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyVirtual );
}

CNMJoinToDefaultGuildChatRoomFunc::CNMJoinToDefaultGuildChatRoomFunc( NMVirtualKey keyVirtual )
	: CNMFunc( kCode, 1 )
{
	this->keyVirtual = keyVirtual;
}

BEGIN_NMFUNC_SERIALIZE( CNMJoinToDefaultGuildChatRoomFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_VIRTUALKEY( this->keyVirtual );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMJoinToDefaultGuildChatRoomFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_VIRTUALKEY( this->keyVirtual );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMReplySessionRequestFunc
//=========================================

CNMReplySessionRequestFunc::CNMReplySessionRequestFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uSessionID		= 0;
	this->uServingProcessID	= 0;
	this->bAccept			= FALSE;
}

CNMReplySessionRequestFunc::CNMReplySessionRequestFunc( _UInt64_ uSessionID, BOOL bAccept )
	: CNMFunc( kCode, 1 )
{
	this->uSessionID		= uSessionID;
	this->uServingProcessID	= 0;
	this->bAccept			= bAccept;
}

BEGIN_NMFUNC_SERIALIZE( CNMReplySessionRequestFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode8( this->uSessionID );
		encoder.Encode4( this->uServingProcessID );
		encoder.Encode1( static_cast<UINT8>( this->bAccept ) );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMReplySessionRequestFunc )
//{
	if ( this->IsCalling() )
	{
		this->uSessionID		= decoder.Decode8();
		this->uServingProcessID	= decoder.Decode4();
		this->bAccept			= decoder.Decode1();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCreateChatRoomFunc
//=========================================

CNMCreateChatRoomFunc::CNMCreateChatRoomFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szServerAddr[ 0 ]			= 0;
	this->uServerPort				= 0;
}
CNMCreateChatRoomFunc::CNMCreateChatRoomFunc( _UInt32_ uRoomType, _UInt32_ uClubSerialNo, LPCTSTR szClubTitle, LPCTSTR szRoomTitle, _UInt32_ uLimitLevel, _UInt32_ uMaxSize, _UInt32_ uFlag, LPCTSTR szPassword, LPCTSTR szServerAddr, _UInt16_ uServerPort )
	: CNMFunc( kCode, 1 )
{
	this->criRoomInfo.uRoomType		= uRoomType;
	this->criRoomInfo.uClubSerialNo	= uClubSerialNo;
	INIT_STRING( this->criRoomInfo.szClubTitle, szClubTitle, GUILDNAME_SIZE );
	INIT_STRING( this->criRoomInfo.szRoomTitle, szRoomTitle, ROOMTITLE_SIZE );
	this->criRoomInfo.uLimitLevel	= uLimitLevel;
	this->criRoomInfo.uMaxSize		= uMaxSize;
	this->criRoomInfo.uFlag			= uFlag;
	INIT_STRING( this->criRoomInfo.szPassword, szPassword, ROOMPWD_SIZE );
	INIT_STRING2( szServerAddr,32 );
	this->uServerPort				= uServerPort;
}

BEGIN_NMFUNC_SERIALIZE( CNMCreateChatRoomFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->criRoomInfo );
		OBJECT_ENCODE( this->crmiMyInfo );
		encoder.EncodeString( this->szServerAddr );
		encoder.Encode2( this->uServerPort );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCreateChatRoomFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->criRoomInfo );
		OBJECT_DECODE( this->crmiMyInfo );
		decoder.DecodeString( this->szServerAddr, 32 );
		this->uServerPort	= decoder.Decode2();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCreateChatRoomExFunc
//=========================================

CNMCreateChatRoomExFunc::CNMCreateChatRoomExFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szServerAddr[ 0 ]			= 0;
	this->uServerPort				= 0;
	this->uVirtualIdCode			= 1;
}
CNMCreateChatRoomExFunc::CNMCreateChatRoomExFunc( _UInt32_ uRoomType, NMGuildKey uGuildKey, LPCTSTR szGuildTitle, LPCTSTR szRoomTitle, _UInt32_ uLimitLevel, _UInt32_ uMaxSize, _UInt32_ uFlag, LPCTSTR szPassword, LPCTSTR szServerAddr, _UInt16_ uServerPort, UINT32 uVirtualIdCode/* = 1 */ )
	: CNMFunc( kCode, 1 )
{
	this->criRoomInfo.uRoomType		= uRoomType;
	this->criRoomInfo.uGuildKey		= uGuildKey;
	INIT_STRING( this->criRoomInfo.szGuildTitle, szGuildTitle, GUILDNAME_SIZE );
	INIT_STRING( this->criRoomInfo.szRoomTitle, szRoomTitle, ROOMTITLE_SIZE );
	this->criRoomInfo.uLimitLevel	= uLimitLevel;
	this->criRoomInfo.uMaxSize		= uMaxSize;
	this->criRoomInfo.uFlag			= uFlag;
	INIT_STRING( this->criRoomInfo.szPassword, szPassword, ROOMPWD_SIZE );
	INIT_STRING2( szServerAddr,32 );
	this->uServerPort				= uServerPort;
	this->uVirtualIdCode			= uVirtualIdCode;
}

BEGIN_NMFUNC_SERIALIZE( CNMCreateChatRoomExFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->criRoomInfo );
		OBJECT_ENCODE( this->crmiMyInfo );
		encoder.EncodeString( this->szServerAddr );
		encoder.Encode2( this->uServerPort );
		encoder.Encode4( this->uVirtualIdCode );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCreateChatRoomExFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->criRoomInfo );
		OBJECT_DECODE( this->crmiMyInfo );
		decoder.DecodeString( this->szServerAddr, 32 );
		this->uServerPort		= decoder.Decode2();
		this->uVirtualIdCode	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMJoinToChatRoomFunc
//=========================================

CNMJoinToChatRoomFunc::CNMJoinToChatRoomFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szServerAddr[ 0 ]			= 0;
	this->uServerPort				= 0;

	this->uExistingSerialKey		= 0;
	this->uCreatedSerialKey			= 0;
}

CNMJoinToChatRoomFunc::CNMJoinToChatRoomFunc( _UInt32_ uClubSerialNo, _UInt32_ uRoomSerialNo, LPCTSTR szPassword, LPCTSTR szServerAddr, _UInt16_ uServerPort )
	: CNMFunc( kCode, 1 )
{
	this->criRoomInfo.uClubSerialNo	= uClubSerialNo;
	this->criRoomInfo.uRoomSerialNo	= uRoomSerialNo;
	INIT_STRING( this->criRoomInfo.szPassword, szPassword, ROOMPWD_SIZE );
	INIT_STRING2( szServerAddr,32 );
	this->uServerPort				= uServerPort;

	this->uExistingSerialKey		= 0;
	this->uCreatedSerialKey			= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMJoinToChatRoomFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->criRoomInfo );
		OBJECT_ENCODE( this->crmiMyInfo );
		encoder.EncodeString( this->szServerAddr );
		encoder.Encode2( this->uServerPort );
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uExistingSerialKey );
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMJoinToChatRoomFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->criRoomInfo );
		OBJECT_DECODE( this->crmiMyInfo );
		decoder.DecodeString( this->szServerAddr, 32 );
		this->uServerPort	= decoder.Decode2();
	}
	else if ( this->IsReturn() )
	{
		this->uExistingSerialKey	= decoder.Decode4();
		this->uCreatedSerialKey		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMJoinToChatRoomExFunc
//=========================================

CNMJoinToChatRoomExFunc::CNMJoinToChatRoomExFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szServerAddr[ 0 ]			= 0;
	this->uServerPort				= 0;
	this->uVirtualIdCode			= 1;

	this->uExistingSerialKey		= 0;
	this->uCreatedSerialKey			= 0;
}

CNMJoinToChatRoomExFunc::CNMJoinToChatRoomExFunc( NMGuildKey uGuildKey, _UInt32_ uRoomSerialNo, LPCTSTR szPassword, LPCTSTR szServerAddr, _UInt16_ uServerPort, _UInt32_ uFlag, UINT32 uVirtualIdCode )
	: CNMFunc( kCode, 1 )
{
	this->criRoomInfo.uGuildKey		= uGuildKey;
	this->criRoomInfo.uRoomSerialNo	= uRoomSerialNo;
	INIT_STRING( this->criRoomInfo.szPassword, szPassword, ROOMPWD_SIZE );
	this->criRoomInfo.uFlag			= uFlag;

	INIT_STRING2( szServerAddr,32 );
	this->uServerPort				= uServerPort;
	this->uVirtualIdCode			= uVirtualIdCode;

	this->uExistingSerialKey		= 0;
	this->uCreatedSerialKey			= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMJoinToChatRoomExFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->criRoomInfo );
		OBJECT_ENCODE( this->crmiMyInfo );
		encoder.EncodeString( this->szServerAddr );
		encoder.Encode2( this->uServerPort );
		encoder.Encode4( this->uVirtualIdCode );
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uExistingSerialKey );
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMJoinToChatRoomExFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->criRoomInfo );
		OBJECT_DECODE( this->crmiMyInfo );
		decoder.DecodeString( this->szServerAddr, 32 );
		this->uServerPort		= decoder.Decode2();
		this->uVirtualIdCode	= decoder.Decode4();
	}
	else if ( this->IsReturn() )
	{
		this->uExistingSerialKey	= decoder.Decode4();
		this->uCreatedSerialKey		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRequestSessionToOtherFunc
//=========================================
CNMRequestSessionToOtherFunc::CNMRequestSessionToOtherFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uSessionType			= kSessionType_General;
	this->uInviteMode			= CNMSessionInfo::kInviteMode_Stranger;
	this->uServingProcessID		= 0;
	INITIALIZE_VIRTUALKEY( this->keyVirtuality );
	this->szRequestID[ 0 ]		= NULL;
	this->uRequesteeGameCode	= kGameCode_NULL;
	this->uAssocSerialKey		= 0;
	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}

CNMRequestSessionToOtherFunc::CNMRequestSessionToOtherFunc( NMSESSIONTYPE uSessionType, _UInt32_ uInviteMode, NMVirtualKey keyVirtuality, LPCTSTR szRequestID, _UInt32_ uRequesteeGameCode, NMFilePathList& aFileList )
	: CNMFunc( kCode, 1 )
{
	this->uSessionType			= uSessionType;
	this->uInviteMode			= uInviteMode;
	this->uServingProcessID		= 0;
	this->keyVirtuality			= keyVirtuality;
	NMSAFE_STRCOPY( this->szRequestID, szRequestID, LOGINID_SIZE );
	this->uRequesteeGameCode	= uRequesteeGameCode;
	this->aFilePath				= aFileList;
	this->uAssocSerialKey		= 0;
	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}


CNMRequestSessionToOtherFunc::CNMRequestSessionToOtherFunc( NMSESSIONTYPE uSessionType, _UInt32_ uInviteMode, NMVirtualKey keyVirtuality, LPCTSTR szRequestID, _UInt32_ uRequesteeGameCode, _UInt32_ uAssocSerialKey )
	: CNMFunc( kCode, 1 )
{
	this->uSessionType			= uSessionType;
	this->uInviteMode			= uInviteMode;
	this->uServingProcessID		= 0;
	this->keyVirtuality			= keyVirtuality;
	NMSAFE_STRCOPY( this->szRequestID, szRequestID, LOGINID_SIZE );
	this->uRequesteeGameCode	= uRequesteeGameCode;
	this->uAssocSerialKey		= uAssocSerialKey;
	this->uExistingSerialKey	= 0;
	this->uCreatedSerialKey		= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMRequestSessionToOtherFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uSessionType );
		encoder.Encode4( this->uInviteMode );
		encoder.Encode4( this->uServingProcessID );
		ENCODE_VIRTUALKEY( this->keyVirtuality );
		
		encoder.EncodeString( this->szRequestID );
		encoder.Encode4( this->uRequesteeGameCode );
		LIST_ENCODE( NMFilePathList, this->aFilePath );
		encoder.Encode4( this->uAssocSerialKey );
	}
	else if ( this->IsReturn() )
	{
		encoder.Encode4( this->uExistingSerialKey );
		encoder.Encode4( this->uCreatedSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRequestSessionToOtherFunc )
//{
	if ( this->IsCalling() )
	{
		this->uSessionType		= (NMSESSIONTYPE) decoder.Decode4();
		this->uInviteMode		= decoder.Decode4();
		this->uServingProcessID	= decoder.Decode4();
		DECODE_VIRTUALKEY( this->keyVirtuality );

		decoder.DecodeString( this->szRequestID, LOGINID_SIZE );
		this->uRequesteeGameCode= decoder.Decode4();
		LIST_DECODE( NMFilePathList, this->aFilePath );
		this->uAssocSerialKey	= decoder.Decode4();
	}
	else if ( this->IsReturn() )
	{
		this->uExistingSerialKey	= decoder.Decode4();
		this->uCreatedSerialKey		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRRegisterCallbackFunc
//=========================================

CNMCRRegisterCallbackFunc::CNMCRRegisterCallbackFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->hWnd		= 0;
	this->uMsg		= 0;
}

CNMCRRegisterCallbackFunc::CNMCRRegisterCallbackFunc( _UInt32_ uSerialKey, HWND hWnd, _UInt32_ uMsg )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->hWnd		= hWnd;
	this->uMsg		= uMsg;
}

BEGIN_NMFUNC_SERIALIZE( CNMCRRegisterCallbackFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( static_cast<_UInt32_>( (UINT_PTR)this->hWnd ) );
		encoder.Encode4( this->uMsg );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRRegisterCallbackFunc )
//{
	if ( this->IsCalling() )
	{
		this->hWnd	= reinterpret_cast<HWND>( (UINT_PTR)decoder.Decode4() );
		this->uMsg	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRCloseFunc
//=========================================

CNMCRCloseFunc::CNMCRCloseFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMCRCloseFunc::CNMCRCloseFunc( _UInt32_ uSerialKey )
	: CNMFunc( kCode, 1, uSerialKey )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMCRCloseFunc )
//{
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRCloseFunc )
//{
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRGetRoomInfoFunc
//=========================================

CNMCRGetRoomInfoFunc::CNMCRGetRoomInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMCRGetRoomInfoFunc::CNMCRGetRoomInfoFunc( _UInt32_ uSerialKey )
	: CNMFunc( kCode, 1, uSerialKey )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMCRGetRoomInfoFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_ENCODE( this->criInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRGetRoomInfoFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_DECODE( this->criInfo );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRGetRoomInfoExFunc
//=========================================

CNMCRGetRoomInfoExFunc::CNMCRGetRoomInfoExFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMCRGetRoomInfoExFunc::CNMCRGetRoomInfoExFunc( _UInt32_ uSerialKey )
	: CNMFunc( kCode, 1, uSerialKey )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMCRGetRoomInfoExFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_ENCODE( this->criInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRGetRoomInfoExFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_DECODE( this->criInfo );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRChangeRoomInfoFunc
//=========================================

CNMCRChangeRoomInfoFunc::CNMCRChangeRoomInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uChangeFlag		= 0;
	this->szRoomTitle[ 0 ]	= 0;
	this->uLimitLevel		= 0;
	this->uMaxSize			= 0;
	this->uFlag				= 0;
	this->uOwnerIdCode		= 0;
	this->szPassword[ 0 ]	= 0;
}

CNMCRChangeRoomInfoFunc::CNMCRChangeRoomInfoFunc( _UInt32_ uSerialKey, LPCTSTR szRoomTitle, _UInt32_* pLimitLevel, _UInt32_* pMaxSize, _UInt32_* pFlag, LPCTSTR szPassword, _UInt64_* pOwnerIdCode )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->uChangeFlag		= 0;
	if ( szRoomTitle != NULL )
	{
		INIT_STRING2( szRoomTitle, ROOMTITLE_SIZE );
		this->uChangeFlag	|= kFlag_RoomTitle;
	}
	if ( pLimitLevel != NULL )
	{
		this->uLimitLevel	= *pLimitLevel;
		this->uChangeFlag	|= kFlag_LimitLevel;
	}
	if ( pMaxSize != NULL )
	{
		this->uMaxSize		= *pMaxSize;
		this->uChangeFlag	|= kFlag_MaxSize;
	}
	if ( pFlag != NULL )
	{
		this->uFlag			= *pFlag;
		this->uChangeFlag	|= kFlag_Flag;
	}
	if ( pOwnerIdCode != NULL )
	{
		this->uOwnerIdCode	= *pOwnerIdCode;
		this->uChangeFlag	|= kFlag_OwnerIdCode;
	}
	if ( szPassword != NULL )
	{
		INIT_STRING2( szPassword, ROOMPWD_SIZE );
		this->uChangeFlag	|= kFlag_Password;
	}
}

BEGIN_NMFUNC_SERIALIZE( CNMCRChangeRoomInfoFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uChangeFlag );
		if ( this->uChangeFlag & kFlag_RoomTitle )		encoder.EncodeString( this->szRoomTitle );
		if ( this->uChangeFlag & kFlag_LimitLevel )		encoder.Encode4( this->uLimitLevel );
		if ( this->uChangeFlag & kFlag_MaxSize )		encoder.Encode4( this->uMaxSize );
		if ( this->uChangeFlag & kFlag_Flag )			encoder.Encode4( this->uFlag );
		if ( this->uChangeFlag & kFlag_OwnerIdCode )	encoder.Encode8( this->uOwnerIdCode );
		if ( this->uChangeFlag & kFlag_Password )		encoder.EncodeString( this->szPassword );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRChangeRoomInfoFunc )
//{
	if ( this->IsCalling() )
	{
		this->uChangeFlag	= decoder.Decode4();
		if ( this->uChangeFlag & kFlag_RoomTitle )		decoder.DecodeString( this->szRoomTitle, ROOMTITLE_SIZE );
		if ( this->uChangeFlag & kFlag_LimitLevel )		this->uLimitLevel	= decoder.Decode4();
		if ( this->uChangeFlag & kFlag_MaxSize )		this->uMaxSize		= decoder.Decode4();
		if ( this->uChangeFlag & kFlag_Flag )			this->uFlag			= decoder.Decode4();
		if ( this->uChangeFlag & kFlag_OwnerIdCode )	this->uOwnerIdCode	= decoder.Decode8();
		if ( this->uChangeFlag & kFlag_Password )		decoder.DecodeString( this->szPassword, ROOMPWD_SIZE );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRChangeMyInfoFunc
//=========================================

CNMCRChangeMyInfoFunc::CNMCRChangeMyInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMCRChangeMyInfoFunc::CNMCRChangeMyInfoFunc( _UInt32_ uSerialKey, CNMChatRoomMemberInfo& crmiInfo )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->crmiInfo = crmiInfo;
}

BEGIN_NMFUNC_SERIALIZE( CNMCRChangeMyInfoFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->crmiInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRChangeMyInfoFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->crmiInfo );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRGetMemberListFunc
//=========================================

CNMCRGetMemberListFunc::CNMCRGetMemberListFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMCRGetMemberListFunc::CNMCRGetMemberListFunc( _UInt32_ uSerialKey )
	: CNMFunc( kCode, 1, uSerialKey )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMCRGetMemberListFunc )
//{
	if ( this->IsReturn() )
	{
		LIST_ENCODE( NMChatRoomMemberList, this->aMembers );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRGetMemberListFunc )
//{
	if ( this->IsReturn() )
	{
		LIST_DECODE( NMChatRoomMemberList, this->aMembers );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRInviteUserFunc
//=========================================

CNMCRInviteUserFunc::CNMCRInviteUserFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szToId[ 0 ]		= 0;
	this->szMessage[ 0 ]	= 0;
}

CNMCRInviteUserFunc::CNMCRInviteUserFunc( _UInt32_ uSerialKey, LPCTSTR szToId, LPCTSTR szMessage )
	: CNMFunc( kCode, 1, uSerialKey )
{
	INIT_STRING2( szToId, 1024 );
	INIT_STRING2( szMessage, 1024 );
}

BEGIN_NMFUNC_SERIALIZE( CNMCRInviteUserFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szToId );
		encoder.EncodeString( this->szMessage );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRInviteUserFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szToId, 1024 );
		decoder.DecodeString( this->szMessage, 1024 );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRBanUserFunc
//=========================================

CNMCRBanUserFunc::CNMCRBanUserFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uIdCode		= 0;
}

CNMCRBanUserFunc::CNMCRBanUserFunc( _UInt32_ uSerialKey, _UInt64_ uIdCode )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->uIdCode		= uIdCode;
}

BEGIN_NMFUNC_SERIALIZE( CNMCRBanUserFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode8( this->uIdCode );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRBanUserFunc )
//{
	if ( this->IsCalling() )
	{
		this->uIdCode		= decoder.Decode8();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRSendChatMessageFunc
//=========================================

CNMCRSendChatMessageFunc::CNMCRSendChatMessageFunc( void )
	: CNMFunc( kCode, 1 )
{
	::ZeroMemory( this->aToIdCode, sizeof( this->aToIdCode ) );
}

CNMCRSendChatMessageFunc::CNMCRSendChatMessageFunc( _UInt32_ uSerialKey, _UInt64_ aToIdCode[], CNMChatMessage& chatMsg )
	: CNMFunc( kCode, 1, uSerialKey )
{
	if (aToIdCode)
	{
		::CopyMemory( this->aToIdCode, aToIdCode, sizeof( this->aToIdCode ) );
	}
	else
	{
		::ZeroMemory( this->aToIdCode, sizeof( this->aToIdCode ) );
	}
	this->chatMsg		= chatMsg;
}

BEGIN_NMFUNC_SERIALIZE( CNMCRSendChatMessageFunc )
//{
	if ( this->IsCalling() )
	{
		for ( _UInt32_ i = 0; i < 10; ++i )
			encoder.Encode8( this->aToIdCode[ i ] );
		OBJECT_ENCODE( this->chatMsg );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRSendChatMessageFunc )
//{
	if ( this->IsCalling() )
	{
		for ( _UInt32_ i = 0; i < 10; ++i )
			this->aToIdCode[ i ] = decoder.Decode8();
		OBJECT_DECODE( this->chatMsg );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCRGetMemberInfoFunc
//=========================================

CNMCRGetMemberInfoFunc::CNMCRGetMemberInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uType	= kType_NULL;
}

CNMCRGetMemberInfoFunc::CNMCRGetMemberInfoFunc( _UInt32_ uSerialKey, _UInt32_ uType )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->uType	= uType;
}

BEGIN_NMFUNC_SERIALIZE( CNMCRGetMemberInfoFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uType );
	}
	else
	{
		OBJECT_ENCODE( this->crmiInfo );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCRGetMemberInfoFunc )
//{
	if ( this->IsCalling() )
	{
		this->uType = decoder.Decode4();
	}
	else
	{
		OBJECT_DECODE( this->crmiInfo );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGSRegisterCallbackFunc
//=========================================

CNMGSRegisterCallbackFunc::CNMGSRegisterCallbackFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->hWnd			= 0;
	this->uMsg			= 0;
}

CNMGSRegisterCallbackFunc::CNMGSRegisterCallbackFunc( _UInt32_ uSerialKey, HWND hWnd, _UInt32_ uMsg )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->hWnd			= hWnd;
	this->uMsg			= uMsg;
}

BEGIN_NMFUNC_SERIALIZE( CNMGSRegisterCallbackFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( static_cast<_UInt32_>( (UINT_PTR)this->hWnd ) );
		encoder.Encode4( this->uMsg );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGSRegisterCallbackFunc )
//{
	if ( this->IsCalling() )
	{
		this->hWnd			= reinterpret_cast<HWND>( (UINT_PTR)decoder.Decode4() );
		this->uMsg			= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGSWantCloseFunc
//=========================================

CNMGSWantCloseFunc::CNMGSWantCloseFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->bForceClose	= FALSE;
}

CNMGSWantCloseFunc::CNMGSWantCloseFunc( _UInt32_ uSerialKey, BOOL bForceClose )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->bForceClose	= bForceClose;
}

BEGIN_NMFUNC_SERIALIZE( CNMGSWantCloseFunc )
//{
	encoder.Encode1( static_cast<UINT8>( this->bForceClose ) );
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGSWantCloseFunc )
//{
	this->bForceClose	= decoder.Decode1();
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGSGetSessionInfoFunc
//=========================================

CNMGSGetSessionInfoFunc::CNMGSGetSessionInfoFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMGSGetSessionInfoFunc::CNMGSGetSessionInfoFunc( _UInt32_ uSerialKey )
	: CNMFunc( kCode, 1, uSerialKey )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMGSGetSessionInfoFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_ENCODE( this->siInfo );
		LIST_ENCODE( NMSessionMemberList, this->aMembers );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGSGetSessionInfoFunc )
//{
	if ( this->IsReturn() )
	{
		OBJECT_DECODE( this->siInfo );
		LIST_DECODE( NMSessionMemberList, this->aMembers );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGSSetServingProcessIDFunc
//=========================================

CNMGSSetServingProcessIDFunc::CNMGSSetServingProcessIDFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMGSSetServingProcessIDFunc::CNMGSSetServingProcessIDFunc( _UInt32_ uSerialKey, _UInt32_ uServingProcessID )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->uServingProcessID		= uServingProcessID;
}

BEGIN_NMFUNC_SERIALIZE( CNMGSSetServingProcessIDFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uServingProcessID );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGSSetServingProcessIDFunc )
//{
	if ( this->IsCalling() )
	{
		this->uServingProcessID	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()


//=========================================
// class CNMGSGetMemberListFunc
//=========================================

CNMGSGetMemberListFunc::CNMGSGetMemberListFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMGSGetMemberListFunc::CNMGSGetMemberListFunc( _UInt32_ uSerialKey )
	: CNMFunc( kCode, 1, uSerialKey )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMGSGetMemberListFunc )
//{
	if ( this->IsReturn() )
	{
		LIST_ENCODE( NMSessionMemberList, this->aMembers );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGSGetMemberListFunc )
//{
	if ( this->IsReturn() )
	{
		LIST_DECODE( NMSessionMemberList, this->aMembers );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGSGetInviteCandidateListFunc
//=========================================

CNMGSGetInviteCandidateListFunc::CNMGSGetInviteCandidateListFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMGSGetInviteCandidateListFunc::CNMGSGetInviteCandidateListFunc( _UInt32_ uSerialKey )
	: CNMFunc( kCode, 1, uSerialKey )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMGSGetInviteCandidateListFunc )
//{
	if ( this->IsReturn() )
	{
		LIST_ENCODE( NMFriendList, this->aCandidates );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGSGetInviteCandidateListFunc )
//{
	if ( this->IsReturn() )
	{
		LIST_DECODE( NMFriendList, this->aCandidates );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGSInviteUserFunc
//=========================================

CNMGSInviteUserFunc::CNMGSInviteUserFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDCODE( this->fcFriendCode );
}

CNMGSInviteUserFunc::CNMGSInviteUserFunc( _UInt32_ uSerialKey, NMFriendCode fcFriendCode )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->fcFriendCode		= fcFriendCode;
}

BEGIN_NMFUNC_SERIALIZE( CNMGSInviteUserFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDCODE( this->fcFriendCode );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGSInviteUserFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDCODE( this->fcFriendCode );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGSInviteUserExFunc
//=========================================

CNMGSInviteUserExFunc::CNMGSInviteUserExFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_FRIENDKEY( this->keyFriend );
}

CNMGSInviteUserExFunc::CNMGSInviteUserExFunc( _UInt32_ uSerialKey, NMFriendKey keyFriend )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->keyFriend		= keyFriend;
}

BEGIN_NMFUNC_SERIALIZE( CNMGSInviteUserExFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_FRIENDKEY( this->keyFriend );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMGSInviteUserExFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_FRIENDKEY( this->keyFriend );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMGSInviteUserFunc
//=========================================

CNMCSSendChatMessageFunc::CNMCSSendChatMessageFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMCSSendChatMessageFunc::CNMCSSendChatMessageFunc( _UInt32_ uSerialKey,CNMChatMessage& chatMsg )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->chatMsg		= chatMsg;
}

BEGIN_NMFUNC_SERIALIZE( CNMCSSendChatMessageFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->chatMsg );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCSSendChatMessageFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->chatMsg );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMFUDSGetFileEventFunc
//=========================================

CNMFUDSGetFileEventFunc::CNMFUDSGetFileEventFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMFUDSGetFileEventFunc::CNMFUDSGetFileEventFunc( _UInt32_ uSerialKey )
	: CNMFunc( kCode, 1, uSerialKey )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMFUDSGetFileEventFunc )
//{
	if ( this->IsReturn() )
	{
		LIST_ENCODE( NMFileEventList, this->aEventList );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMFUDSGetFileEventFunc )
//{
	if ( this->IsReturn() )
	{
		LIST_DECODE( NMFileEventList, this->aEventList );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMFUSSendFileFunc
//=========================================

CNMFUSSendFileFunc::CNMFUSSendFileFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szFilePath[ 0 ]	= 0;
}

CNMFUSSendFileFunc::CNMFUSSendFileFunc( _UInt32_ uSerialKey, LPCTSTR szFilePath )
	: CNMFunc( kCode, 1, uSerialKey )
{
	INIT_STRING2( szFilePath, MAX_PATH );
}

BEGIN_NMFUNC_SERIALIZE( CNMFUSSendFileFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szFilePath );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMFUSSendFileFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szFilePath, MAX_PATH );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMFUSControlFunc
//=========================================

CNMFUSControlFunc::CNMFUSControlFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uType			= 0;
	this->uEventId		= 0;
}

CNMFUSControlFunc::CNMFUSControlFunc( _UInt32_ uSerialKey, _UInt32_ uType, _UInt32_ uEventId )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->uType			= uType;
	this->uEventId		= uEventId;
}

BEGIN_NMFUNC_SERIALIZE( CNMFUSControlFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uType );
		encoder.Encode4( this->uEventId );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMFUSControlFunc )
//{
	if ( this->IsCalling() )
	{
		this->uType			= decoder.Decode4();
		this->uEventId		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMFDSControlFunc
//=========================================

CNMFDSControlFunc::CNMFDSControlFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uType			= 0;
	this->uEventId		= 0;
}

CNMFDSControlFunc::CNMFDSControlFunc( _UInt32_ uSerialKey, _UInt32_ uType, _UInt32_ uEventId )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->uType			= uType;
	this->uEventId		= uEventId;
}

BEGIN_NMFUNC_SERIALIZE( CNMFDSControlFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uType );
		encoder.Encode4( this->uEventId );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMFDSControlFunc )
//{
	if ( this->IsCalling() )
	{
		this->uType			= decoder.Decode4();
		this->uEventId		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMFDSGetDownloadDirFunc
//=========================================

CNMFDSGetDownloadDirFunc::CNMFDSGetDownloadDirFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szDir[ 0 ]	= 0;
}

CNMFDSGetDownloadDirFunc::CNMFDSGetDownloadDirFunc( _UInt32_ uSerialKey )
	: CNMFunc( kCode, 1, uSerialKey )
{
	INIT_STRING2( szDir, MAX_PATH );
}

BEGIN_NMFUNC_SERIALIZE( CNMFDSGetDownloadDirFunc )
//{
	if ( this->IsReturn() )
	{
		encoder.EncodeString( this->szDir );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMFDSGetDownloadDirFunc )
//{
	if ( this->IsReturn() )
	{
		decoder.DecodeString( this->szDir, MAX_PATH );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMFDSSetDownloadDirFunc
//=========================================

CNMFDSSetDownloadDirFunc::CNMFDSSetDownloadDirFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szDir[ 0 ]	= 0;
}

CNMFDSSetDownloadDirFunc::CNMFDSSetDownloadDirFunc( _UInt32_ uSerialKey, LPCTSTR szDir )
	: CNMFunc( kCode, 1, uSerialKey )
{
	INIT_STRING2( szDir, MAX_PATH );
}

BEGIN_NMFUNC_SERIALIZE( CNMFDSSetDownloadDirFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szDir );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMFDSSetDownloadDirFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szDir, MAX_PATH );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMWSSendWBMessageFunc
//=========================================

CNMWSSendWBMessageFunc::CNMWSSendWBMessageFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uContentId	= 0;
	this->uDataLen		= 0;
	::ZeroMemory( this->aData, sizeof( this->aData ) );
}

CNMWSSendWBMessageFunc::CNMWSSendWBMessageFunc( _UInt32_ uSerialKey, _UInt32_ uContentId, const BYTE* aData, _UInt32_ uDataLen )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->uContentId	= uContentId;
	this->uDataLen		= uDataLen;
	::CopyMemory( this->aData, aData, uDataLen );
}

BEGIN_NMFUNC_SERIALIZE( CNMWSSendWBMessageFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uContentId );
		encoder.EncodeBinary( this->aData, this->uDataLen );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMWSSendWBMessageFunc )
//{
	if ( this->IsCalling() )
	{
		this->uContentId	= decoder.Decode4();
		this->uDataLen		= decoder.DecodeBinary( this->aData, kMaxDataLen );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMWSGetAssocSerialKeyFunc
//=========================================

CNMWSGetAssocSerialKeyFunc::CNMWSGetAssocSerialKeyFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->uAssocSerialKey	= 0;
}

CNMWSGetAssocSerialKeyFunc::CNMWSGetAssocSerialKeyFunc( _UInt32_ uSerialKey )
	: CNMFunc( kCode, 1, uSerialKey )
{
	this->uAssocSerialKey	= 0;
}

BEGIN_NMFUNC_SERIALIZE( CNMWSGetAssocSerialKeyFunc )
//{
	if ( this->IsReturn() )
	{
		encoder.Encode4( this->uAssocSerialKey );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMWSGetAssocSerialKeyFunc )
//{
	if ( this->IsReturn() )
	{
		this->uAssocSerialKey	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMCustomNotifyFunc
//=========================================

CNMCustomNotifyFunc::CNMCustomNotifyFunc( void )
	: CNMFunc( kCode, 1 )
{
}

CNMCustomNotifyFunc::CNMCustomNotifyFunc( UINT32 uType, LPCTSTR szArgument, UINT32 uPolicyFlag/* = CNMCustomNotifyData::kCustomNotifyPolicy_Default*/ )
: CNMFunc( kCode, 1 )
{
	this->cnData.uType			= uType;
	INIT_STRING( this->cnData.szArgument, szArgument, MSG_SIZE );
	this->cnData.uPolicyFlag	= uPolicyFlag;
}

CNMCustomNotifyFunc::CNMCustomNotifyFunc( CNMCustomNotifyData& cnData )
: CNMFunc( kCode, 1 )
{
	this->cnData	= cnData;
}

BEGIN_NMFUNC_SERIALIZE( CNMCustomNotifyFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_ENCODE( this->cnData );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMCustomNotifyFunc )
//{
	if ( this->IsCalling() )
	{
		OBJECT_DECODE( this->cnData );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMChangeMyLevelFunc
//=========================================

CNMChangeMyLevelFunc::CNMChangeMyLevelFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyVirtual );
	this->uLevel		= 0;
	this->uLevelType	= 0;
}

CNMChangeMyLevelFunc::CNMChangeMyLevelFunc( _UInt32_ uLevel, UINT32 uLevelType )
: CNMFunc( kCode, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyVirtual );
	this->uLevel		= uLevel;
	this->uLevelType	= uLevelType;
}

BEGIN_NMFUNC_SERIALIZE( CNMChangeMyLevelFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_VIRTUALKEY( this->keyVirtual );
		encoder.Encode4( this->uLevel );
		encoder.Encode4( this->uLevelType );
	}
	//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMChangeMyLevelFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_VIRTUALKEY( this->keyVirtual );
		this->uLevel		= decoder.Decode4();
		this->uLevelType	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMRemoveMyVirtualUserFunc
//=========================================

CNMRemoveMyVirtualUserFunc::CNMRemoveMyVirtualUserFunc( void )
	: CNMFunc( kCode, 1 )
{
	INITIALIZE_VIRTUALKEY( this->keyVirtual );
}

CNMRemoveMyVirtualUserFunc::CNMRemoveMyVirtualUserFunc( NMVirtualKey & keyVirtual )
	: CNMFunc( kCode, 1 )
{
	this->keyVirtual = keyVirtual;
}

BEGIN_NMFUNC_SERIALIZE( CNMRemoveMyVirtualUserFunc )
//{
	if ( this->IsCalling() )
	{
		ENCODE_VIRTUALKEY( this->keyVirtual );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMRemoveMyVirtualUserFunc )
//{
	if ( this->IsCalling() )
	{
		DECODE_VIRTUALKEY( this->keyVirtual );
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMLoginPassportFunc
//=========================================

CNMLoginPassportFunc::CNMLoginPassportFunc( void )
	: CNMFunc( kCode, 1, 0, TRUE )
{
	this->szLoginId[ 0 ]	= 0;
	this->szPassword[ 0 ]	= 0;
	this->uGameCode			= kGameCode_NULL;
	this->uVersion			= 0;
	this->pbyPassport[ 0 ]	= 0;
	this->cbyPassportLength	= 0;
	this->uLoginGameCode	= kGameCode_nexoncom;
	this->uSupportFlag		= static_cast<UINT32>( kSupportFlag_FromGameCode );
}

CNMLoginPassportFunc::CNMLoginPassportFunc( LPCTSTR szLoginId, LPCTSTR szPassword, _UInt64_ uVersion, PBYTE pbyPassport, INT32 cbyPassportLength, NMGameCode uLoginGameCode/* = kGameCode_nexoncom */ )
	: CNMFunc( kCode, 1 )
{
	INIT_STRING2( szLoginId, LOGINID_SIZE );
	INIT_STRING2( szPassword, LOGINID_SIZE );
	this->uGameCode			= kGameCode_NULL;
	this->uVersion			= uVersion;
	this->cbyPassportLength	= min( cbyPassportLength, PASSPORT_SIZE );
	::ZeroMemory( this->pbyPassport, PASSPORT_SIZE );
	::CopyMemory( this->pbyPassport, pbyPassport, this->cbyPassportLength );
	this->uLoginGameCode	= uLoginGameCode;
	this->uSupportFlag		= static_cast<UINT32>( kSupportFlag_FromGameCode );
}

CNMLoginPassportFunc::CNMLoginPassportFunc( LPCTSTR szLoginId, LPCTSTR szPassword, PBYTE pbyPassport, INT32 cbyPassportLength, UINT32 uSupportFlag /*= kSupportFlag_FromGameCode*/)
	: CNMFunc( kCode, 1 )
{
	INIT_STRING2( szLoginId, LOGINID_SIZE );
	INIT_STRING2( szPassword, LOGINID_SIZE );
	this->uGameCode			= kGameCode_NULL;
	this->uVersion			= 0;
	this->cbyPassportLength	= min( cbyPassportLength, PASSPORT_SIZE );
	::ZeroMemory( this->pbyPassport, PASSPORT_SIZE );
	::CopyMemory( this->pbyPassport, pbyPassport, this->cbyPassportLength );
	this->uLoginGameCode	= uLoginGameCode;
	this->uSupportFlag		= uSupportFlag;
}

BEGIN_NMFUNC_SERIALIZE( CNMLoginPassportFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szLoginId );
		encoder.EncodeString( this->szPassword );
		encoder.Encode4( this->uGameCode );
		encoder.Encode8( this->uVersion );
		encoder.EncodeBinary( this->pbyPassport, this->cbyPassportLength );
		encoder.Encode4( this->uLoginGameCode );
		encoder.Encode4( this->uSupportFlag );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMLoginPassportFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szLoginId, LOGINID_SIZE );
		decoder.DecodeString( this->szPassword, LOGINID_SIZE );
		this->uGameCode			= decoder.Decode4();
		this->uVersion			= decoder.Decode8();
		this->cbyPassportLength	= decoder.DecodeBinary( this->pbyPassport, PASSPORT_SIZE );
		this->uLoginGameCode	= decoder.Decode4();
		this->uSupportFlag		= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMLoginNexonPassportFunc
//=========================================

CNMLoginNexonPassportFunc::CNMLoginNexonPassportFunc( void )
	: CNMFunc( kCode, 1 )
{
	this->szPassport[ 0 ]	= 0;
	this->uGameCode			= kGameCode_NULL;
	this->uVersion			= uVersion;
	this->uLoginGameCode	= uLoginGameCode;
}

CNMLoginNexonPassportFunc::CNMLoginNexonPassportFunc( LPCTSTR szPassport, _UInt64_ uVersion, NMGameCode uLoginGameCode )
	: CNMFunc( kCode, 1)
{
	INIT_STRING2( szPassport, PASSPORT_SIZE );
	this->uGameCode			= kGameCode_NULL;
	this->uVersion			= uVersion;
	this->uLoginGameCode	= uLoginGameCode;
}

BEGIN_NMFUNC_SERIALIZE( CNMLoginNexonPassportFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.EncodeString( this->szPassport );
		encoder.Encode4( this->uGameCode );
		encoder.Encode8( this->uVersion );
		encoder.Encode4( this->uLoginGameCode );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMLoginNexonPassportFunc )
//{
	if ( this->IsCalling() )
	{
		decoder.DecodeString( this->szPassport, PASSPORT_SIZE );
		this->uGameCode			= decoder.Decode4();
		this->uVersion			= decoder.Decode8();
		this->uLoginGameCode	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	class CNMLoginMessengerFunc
//////////////////////////////////////////////////////////////////////////

CNMLoginMessengerFunc::CNMLoginMessengerFunc()
	: CNMFunc( kCode, 1 )
{
	this->uSupportFlag	= static_cast<UINT32>( kSupportFlag_FromGameCode );
}

CNMLoginMessengerFunc::CNMLoginMessengerFunc( UINT32 uSupportFlag )
	: CNMFunc( kCode, 1 )
{
	this->uSupportFlag	= uSupportFlag;
}

BEGIN_NMFUNC_SERIALIZE( CNMLoginMessengerFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uSupportFlag );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMLoginMessengerFunc )
//{
	if ( this->IsCalling() )
	{
		this->uSupportFlag = decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	class CNMLogoutMessengerFunc
//////////////////////////////////////////////////////////////////////////

CNMLogoutMessengerFunc::CNMLogoutMessengerFunc() : CNMFunc( kCode, 1 )
{
}

BEGIN_NMFUNC_SERIALIZE( CNMLogoutMessengerFunc )
//{
	if ( this->IsCalling() )
	{
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMLogoutMessengerFunc )
//{
	if ( this->IsCalling() )
	{
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	class CNMLoginAuthFunc
//////////////////////////////////////////////////////////////////////////

CNMLoginAuthFunc::CNMLoginAuthFunc()
	: CNMFunc( kCode, 1 )
{
	this->uLoginType		= kLoginType_None;
	this->szPassport[0]		= 0;
	this->szNexonID[0]		= 0;
	this->szPassword[0]		= 0;
	this->bSecondary		= FALSE;
	this->nErrorCode		= kLoginAuth_ServerFailed;
}

CNMLoginAuthFunc::CNMLoginAuthFunc( LPCTSTR pszPassport )
	: CNMFunc( kCode, 1 )
{
	this->uLoginType		= kLoginType_Passport;
	::StringCchCopy( this->szPassport, PASSPORT_SIZE, pszPassport );
	this->szNexonID[0]		= 0;
	this->szPassword[0]		= 0;
	this->bSecondary		= FALSE;
	this->nErrorCode		= kLoginAuth_ServerFailed;
}

CNMLoginAuthFunc::CNMLoginAuthFunc( LPCTSTR pszNexonID, LPCTSTR pszPassword )
	: CNMFunc( kCode, 1 )
{
	this->uLoginType		= kLoginType_IDPassword;
	this->szPassport[0]		= 0;
	::StringCchCopy( this->szNexonID, LOGINID_SIZE, pszNexonID );
	::StringCchCopy( this->szPassword, PASSWORD_SIZE, pszPassword );
	this->bSecondary		= FALSE;
	this->nErrorCode		= kLoginAuth_ServerFailed;
}

BEGIN_NMFUNC_SERIALIZE( CNMLoginAuthFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uLoginType );
		encoder.EncodeString( this->szPassport );
		encoder.EncodeString( this->szNexonID );
		encoder.EncodeString( this->szPassword );
		encoder.Encode4( this->bSecondary );
	}
	else
	{
		encoder.Encode4( this->nErrorCode );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMLoginAuthFunc )
//{
	if ( this->IsCalling() )
	{
		this->uLoginType	= decoder.Decode4();
		decoder.DecodeString( this->szPassport, PASSPORT_SIZE );
		decoder.DecodeString( this->szNexonID, LOGINID_SIZE );
		decoder.DecodeString( this->szPassword, PASSWORD_SIZE );
		this->bSecondary	= decoder.Decode4();
	}
	else
	{
		this->nErrorCode	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	class CNMLogoutAuthFunc
//////////////////////////////////////////////////////////////////////////

CNMLogoutAuthFunc::CNMLogoutAuthFunc()
	: CNMFunc( kCode, 1 )
{
	this->bApplyToAllSession	= FALSE;
	this->bSecondary			= FALSE;
}

CNMLogoutAuthFunc::CNMLogoutAuthFunc( BOOL bApplyToAllSession, BOOL bSecondary )
	: CNMFunc( kCode, 1 )
{
	this->bApplyToAllSession	= bApplyToAllSession;
	this->bSecondary			= bSecondary;
}

BEGIN_NMFUNC_SERIALIZE( CNMLogoutAuthFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode1( static_cast<UINT8>( this->bApplyToAllSession ) );
		encoder.Encode4( this->bSecondary );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMLogoutAuthFunc )
//{
	if ( this->IsCalling() )
	{
		this->bApplyToAllSession	= decoder.Decode1();
		this->bSecondary			= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//////////////////////////////////////////////////////////////////////////
//	class CNMInitializeFunc
//////////////////////////////////////////////////////////////////////////

CNMInitializeFunc::CNMInitializeFunc()
	: CNMFunc( kCode, 1 )
{
	this->uGameCode		= kGameCode_NULL;
	this->uSupportFlag	= static_cast<UINT32>( kSupportFlag_FromGameCode );
}

CNMInitializeFunc::CNMInitializeFunc( NMGameCode uGameCode, UINT32 uSupportFlag )
	: CNMFunc( kCode, 1 )
{
	this->uGameCode		= uGameCode;
	this->uSupportFlag	= uSupportFlag;
}

BEGIN_NMFUNC_SERIALIZE( CNMInitializeFunc )
//{
	if ( this->IsCalling() )
	{
		encoder.Encode4( this->uGameCode );
		encoder.Encode4( this->uSupportFlag );
	}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMInitializeFunc )
//{
	if ( this->IsCalling() )
	{
		this->uGameCode		= decoder.Decode4();
		this->uSupportFlag	= decoder.Decode4();
	}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// class CNMKartComebackFunc
//=========================================

CNMKartComebackFunc::CNMKartComebackFunc( void )
: CNMFunc( kCode, 1 )
{
	this->bComeback			= FALSE;
}

CNMKartComebackFunc::CNMKartComebackFunc( NMKartDormantList& aList )
: CNMFunc( kCode, 1 )
{
	this->aList = aList;

	this->bComeback			= FALSE;
}

CNMKartComebackFunc::CNMKartComebackFunc( UINT32 uOID )
: CNMFunc( kCode, 1 )
{
	CNMKartDormant			kdItem;

	kdItem.uOIDUser			= uOID;

	this->aList.push_back( kdItem );

	this->bComeback			= FALSE;
}

BEGIN_NMFUNC_SERIALIZE( CNMKartComebackFunc )
//{
if ( this->IsCalling() )
{
	LIST_ENCODE( NMKartDormantList, this->aList );
}
else
{
	encoder.Encode4( this->bComeback );
}
//}
END_NMFUNC_SERIALIZE()

BEGIN_NMFUNC_DESERIALIZE( CNMKartComebackFunc )
//{
if ( this->IsCalling() )
{
	LIST_DECODE( NMKartDormantList, this->aList );
}
else
{
	this->bComeback		= decoder.Decode4();
}
//}
END_NMFUNC_DESERIALIZE()

//=========================================
// Etc.
//=========================================

#define NMFUNC_MAP( funcClassName )		case funcClassName::kCode: return new funcClassName;
CNMFunc* CNMUtil::CreateFuncFromCode( _UInt32_ uCode )
{
	switch ( uCode )
	{
		// control
		NMFUNC_MAP( CNMInitFunc )
		NMFUNC_MAP( CNMRegisterCallbackFunc )
		NMFUNC_MAP( CNMResetCallbackFunc )
		NMFUNC_MAP( CNMAttachToNMCOServerFunc )
		NMFUNC_MAP( CNMDetachFromNMCOServerFunc )
		NMFUNC_MAP( CNMBringForwardStandAloneMsgFunc )
		NMFUNC_MAP( CNMStartStandAloneMsgFunc )
		NMFUNC_MAP( CNMInitClientObjectFunc )

		// session control
		NMFUNC_MAP( CNMLoginFunc )
		NMFUNC_MAP( CNMLogoutFunc )
		NMFUNC_MAP( CNMLoginVirtualFunc )
		NMFUNC_MAP( CNMLogoutVirtualFunc )
		NMFUNC_MAP( CNMGetMyVirtualUserListFunc )
		NMFUNC_MAP( CNMChangeMyPositionFunc )
		NMFUNC_MAP( CNMSendRefreshMessageFunc )
		NMFUNC_MAP( CNMChangeMyLevelFunc )
		NMFUNC_MAP( CNMRemoveMyVirtualUserFunc )
		NMFUNC_MAP( CNMLoginPassportFunc )
		NMFUNC_MAP( CNMLoginNexonPassportFunc )
		NMFUNC_MAP( CNMLoginMessengerFunc )
		NMFUNC_MAP( CNMLogoutMessengerFunc )
		NMFUNC_MAP( CNMLoginAuthFunc )
		NMFUNC_MAP( CNMLogoutAuthFunc )
		NMFUNC_MAP( CNMInitializeFunc )

		// user info
		NMFUNC_MAP( CNMGetMyInfoFunc )
		NMFUNC_MAP( CNMGetUserDataListFunc )
		NMFUNC_MAP( CNMChangeMyInfoFunc )
		NMFUNC_MAP( CNMGetConfigFunc )
		NMFUNC_MAP( CNMChangeConfigFunc )
		NMFUNC_MAP( CNMGetLocalConfigFunc )
		NMFUNC_MAP( CNMChangeLocalConfigFunc )
		NMFUNC_MAP( CNMGetCountFunc )
		NMFUNC_MAP( CNMSetCountFunc )
		NMFUNC_MAP( CNMChangeNicknameFunc )
		NMFUNC_MAP( CNMRequestNewsFunc )
		NMFUNC_MAP( CNMCheckNewsFunc )

		// cinent info
		NMFUNC_MAP( CNMGetDomainFunc )
		NMFUNC_MAP( CNMGetVersionFunc )
		NMFUNC_MAP( CNMSetStatInfoFunc )
		NMFUNC_MAP( CNMSetLocaleFunc )
		NMFUNC_MAP( CNMGetNexonPassportFunc )

		// friend
		NMFUNC_MAP( CNMGetFriendListFunc )
		NMFUNC_MAP( CNMGetFriendInfoFunc )
		NMFUNC_MAP( CNMRequestNewFriendFunc )
		NMFUNC_MAP( CNMConfirmNewFriendFunc )
		NMFUNC_MAP( CNMBlockFriendFunc )
		NMFUNC_MAP( CNMChangeFriendMemoFunc )
		NMFUNC_MAP( CNMAddFriendToCategoryFunc )
		NMFUNC_MAP( CNMDeleteFriendFromCategoryFunc )
		NMFUNC_MAP( CNMMoveFriendCategoryFunc )

		// category
		NMFUNC_MAP( CNMAddCategoryFunc )
		NMFUNC_MAP( CNMDeleteCategoryFunc )
		NMFUNC_MAP( CNMChangeCategoryNameFunc )
		NMFUNC_MAP( CNMChangeCategoryPropertyFunc )
		NMFUNC_MAP( CNMChangeCategoryAllowTypeFunc )

		// note
		NMFUNC_MAP( CNMGetNoteBoxFunc )
		NMFUNC_MAP( CNMSendNoteMsgFunc )
		NMFUNC_MAP( CNMProcessNoteFunc )
		NMFUNC_MAP( CNMSendNoteInfoFunc )
		NMFUNC_MAP( CNMGetNoteBox2Func )

		// note rejected user list
		NMFUNC_MAP( CNMGetRejectedUserListFunc )
		NMFUNC_MAP( CNMAppendRejectedUserFunc )
		NMFUNC_MAP( CNMRemoveRejectedUserFunc )

		// guild
		NMFUNC_MAP( CNMGetMyGuildListFunc )
		NMFUNC_MAP( CNMGetMyGuildListExFunc )
		NMFUNC_MAP( CNMMonitorGuildOnlineInfoFunc )
		NMFUNC_MAP( CNMMonitorGuildOnlineInfoExFunc )
		NMFUNC_MAP( CNMSendGuildChatMessageFunc )
		NMFUNC_MAP( CNMGetGuildListFunc )

		// client util
		NMFUNC_MAP( CNMExecutePatcherFunc )
		NMFUNC_MAP( CNMExecuteLauncherFunc )
		NMFUNC_MAP( CNMExecuteCommonFunc )
		NMFUNC_MAP( CNMGetUrlFunc )
		NMFUNC_MAP( CNMGetPathFunc )
		NMFUNC_MAP( CNMSetSessionValueFunc )
		NMFUNC_MAP( CNMGetSessionValueFunc )
		NMFUNC_MAP( CNMGetGameListFunc )
		NMFUNC_MAP( CNMGetUrlExFunc )
		NMFUNC_MAP( CNMDownloadFileFunc )
		NMFUNC_MAP( CNMUploadFileFunc )
		NMFUNC_MAP( CNMGetSupportGameListFunc )
		NMFUNC_MAP( CNMGetGameServerListFunc )
		NMFUNC_MAP( CNMGetGameFullNameFunc )
		NMFUNC_MAP( CNMGetGameShortNameFunc )
		NMFUNC_MAP( CNMGetGameFriendTitleFunc )
		NMFUNC_MAP( CNMGetGameServerNameFunc )

		NMFUNC_MAP( CNMGetConnConfigFunc )
		NMFUNC_MAP( CNMSetConnConfigFunc )

		NMFUNC_MAP( CNMExecuteNGMPatcherFunc )
		NMFUNC_MAP( CNMExecuteNGMLauncherFunc )
		NMFUNC_MAP( CNMIsNGMInstalledFunc )

		// server util
		NMFUNC_MAP( CNMGetUserInfoFunc )
		NMFUNC_MAP( CNMFindUserFunc )
		NMFUNC_MAP( CNMGetFindUserResultFunc )
		NMFUNC_MAP( CNMSendNoteExMsgFunc )
		NMFUNC_MAP( CNMSendReportMsgFunc )
		NMFUNC_MAP( CNMCustomNotifyFunc )

		// create cession
		NMFUNC_MAP( CNMRequestChatSessionFunc )
		NMFUNC_MAP( CNMRequestChatSessionExFunc )
		NMFUNC_MAP( CNMRequestMultiChatSessionFunc )
		NMFUNC_MAP( CNMRequestFileUploadSessionFunc )
		NMFUNC_MAP( CNMRequestWBSessionFunc )
		NMFUNC_MAP( CNMReplySessionRequestFunc )
		NMFUNC_MAP( CNMCreateChatRoomFunc )
		NMFUNC_MAP( CNMJoinToChatRoomFunc )
		NMFUNC_MAP( CNMRequestSessionToOtherFunc )
		NMFUNC_MAP( CNMCreateChatRoomExFunc )
		NMFUNC_MAP( CNMJoinToChatRoomExFunc )
		NMFUNC_MAP( CNMJoinToDefaultGuildChatRoomFunc )

		// chat room
		NMFUNC_MAP( CNMCRRegisterCallbackFunc )
		NMFUNC_MAP( CNMCRCloseFunc )
		NMFUNC_MAP( CNMCRGetRoomInfoFunc )
		NMFUNC_MAP( CNMCRChangeRoomInfoFunc )
		NMFUNC_MAP( CNMCRChangeMyInfoFunc )
		NMFUNC_MAP( CNMCRGetMemberListFunc )
		NMFUNC_MAP( CNMCRInviteUserFunc )
		NMFUNC_MAP( CNMCRBanUserFunc )
		NMFUNC_MAP( CNMCRSendChatMessageFunc )
		NMFUNC_MAP( CNMCRGetMemberInfoFunc )
		NMFUNC_MAP( CNMCRGetRoomInfoExFunc )

		// general session
		NMFUNC_MAP( CNMGSRegisterCallbackFunc )
		NMFUNC_MAP( CNMGSWantCloseFunc )
		NMFUNC_MAP( CNMGSGetSessionInfoFunc )
		NMFUNC_MAP( CNMGSSetServingProcessIDFunc )
		NMFUNC_MAP( CNMGSGetMemberListFunc )
		NMFUNC_MAP( CNMGSInviteUserFunc )
		NMFUNC_MAP( CNMGSInviteUserExFunc )
		NMFUNC_MAP( CNMGSGetInviteCandidateListFunc )

		// chat session
		NMFUNC_MAP( CNMCSSendChatMessageFunc )

		// file up/down
		NMFUNC_MAP( CNMFUDSGetFileEventFunc )

		// file upload session
		NMFUNC_MAP( CNMFUSSendFileFunc )
		NMFUNC_MAP( CNMFUSControlFunc )

		// file download session
		NMFUNC_MAP( CNMFDSControlFunc )
		NMFUNC_MAP( CNMFDSGetDownloadDirFunc )
		NMFUNC_MAP( CNMFDSSetDownloadDirFunc )

		// white-board session
		NMFUNC_MAP( CNMWSSendWBMessageFunc )
		NMFUNC_MAP( CNMWSGetAssocSerialKeyFunc )

		// event
		NMFUNC_MAP( CNMKartComebackFunc )
	}

//	check( FALSE );
	return NULL;
}
