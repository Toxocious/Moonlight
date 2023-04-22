#define _XDEF_NEXONLOGIN
#ifdef _XDEF_NEXONLOGIN

#ifndef __NMDATAOBJECT_H_51C2349E_4DF4_4898_AEBD_E86B4E28CA3F__
#define __NMDATAOBJECT_H_51C2349E_4DF4_4898_AEBD_E86B4E28CA3F__

#include <tchar.h>
#include <strsafe.h>
#include "NMSerializable.h"

// String Length Define
#define DOMAIN_SIZE			(1024)
#define LOGINID_SIZE		(32)
#define	PASSWORD_SIZE		(64)
#define	NICKNAME_SIZE		(32)
#define	PASSPORT_SIZE		(4096)
#define	NXPASSPORT_SIZE		(1024)
#define	WORD_SIZE			(64)
#define	MEMO_SIZE			(32)
#define	NXNAME_SIZE			(32)
#define	NXAREA_SIZE			(32)
#define	NXSCHOOL_SIZE		(128)
#define	NXTEL_SIZE			(32)
#define	CATEGORY_SIZE		(32)
#define	POSITION_SIZE		(256)
#define LAYER_SIZE			(40)
#define	MSG_SIZE			(256)
#define	CHATSENDER_SIZE		(64)
#define CHATHDR_SIZE		(256)
#define CHATMSG_SIZE		(1024)
#define	SERVER_MSG_SIZE		(4096)
#define	TARGETID_SIZE		(1024)
#define	NOTEMSG_SIZE		(1024)
#define	NOTEARG_SIZE		(256)
#define	NOTECUSTOM_SIZE		(256)
#define	NOTEEXMSG_SIZE		(32768)
#define MYGAME_COUNT		(10)
#define	FULL_LAYER_SIZE		(38)
#define	FILENAME_SIZE		(256)
#define	GUILDNAME_SIZE		(64)
#define	GUILDID_SIZE		(32)
#define	GUILDHOME_SIZE		(256)
#define GUILDLEVEL_SIZE		(64)
#define	ROOMTITLE_SIZE		(1024)
#define	ROOMPWD_SIZE		(64)
#define IPADDR_SIZE			(32)

// Locale ID
#ifndef _NMLOCALEID_DEFINED_
#define _NMLOCALEID_DEFINED_
enum	NMLOCALEID
{
	kLocaleID_Null			= 0x00000000,

	kLocaleID_KR			= 0x00000001,	//	Korea, Republic of
	kLocaleID_KR_Test		= 0x10000001,	//	Test

	kLocaleID_JP			= 0x00000100,	//	Japan
	kLocaleID_TW			= 0x00000102,	//	Taiwan
	kLocaleID_CN_CNC		= 0x00000111,	//	China / CNC
	kLocaleID_CN_CT			= 0x00000112,	//	China / CT
	kLocaleID_US			= 0x00000200,	//	United States
};
#endif

// Enumeration Types
enum	NMUSERSTATUS
{
	kStatus_Unknown			= 0,

	kStatus_Online			= 10,		//	인증 OK, 메신저 OK
	kStatus_Busy			= 11,
	kStatus_Away			= 12,
	kStatus_AppearOffline	= 13,
	kStatus_Offline			= 14,		//	인증 --, 메신저 --
	kStatus_GameOnline		= 15,
	kStatus_Error			= 16,
	kStatus_Authenticated	= 17,		//	인증 OK, 메신저 --
};

enum	NMSEXTYPE
{
	kSex_Unknown		= 0,

	kSex_Male			= 1,
	kSex_Female			= 2,
};

enum	NMURLTYPE
{
	kURLType_NULL		= 0,

	kURLType_Page		= 1,
	kURLType_Game		= 2,
	kURLType_Guild		= 3,
	kURLType_DirectURL	= 4,
};

enum	NMURLPAGE
{
	kURL_NULL						= 0,

	kURL_MainHome					= 1,
	kURL_WebGameHome				= 2,
	kURL_RPGGameHome				= 3,
	kURL_CasualGameHome				= 4,
	kURL_ClubHome					= 5,
	kURL_PlazaHome					= 6,
	kURL_ItemMallHome				= 7,
	kURL_MyPage						= 8,
	kURL_Support					= 9,
	kURL_Help						= 10,
	kURL_Register					= 11,
	kURL_MyGiftBox					= 12,
	kURL_NXPaybill					= 13,
	kURL_MemberPopup_OID			= 14,
	kURL_AgreementPopup				= 15,
	kURL_PrivacyPopup				= 16,
	kURL_SearchIDPopup				= 17,
	kURL_SearchPass					= 18,
	kURL_MoneyRefillForWebGame		= 19,	// uParam = game code
	kURL_MyRoomForWegGame			= 20,
	kURL_ChangeClothes				= 21,
	kURL_ChangeMyInfo				= 22,
	kURL_MailHome					= 23,
	kURL_WriteMail_General			= 24,
	kURL_WriteMail_Target			= 25,
	kURL_MemberPopup_LoginID		= 26,
	kURL_Penalty					= 27,
	kURL_KartBox_OID				= 28,
	kURL_KartBox_RiderID			= 29,
	kURL_KartBox_RiderName			= 30,
	kURL_NGMInstallGuide			= 31,
};

enum	NMSESSIONTYPE
{
	kSessionType_General			= 0,
	kSessionType_Chat				= 1,
	kSessionType_FileUpload			= 2,
	kSessionType_FileDownload		= 3,
	kSessionType_WhiteBoard			= 4,
};

enum	NMSYSTEMMSGCODE
{
	kSystemMsgCode_NULL					= 0,

	kSystemMsgCode_AdminNotice			= 1,
	kSystemMsgCode_Advertisement		= 2,
	kSystemMsgCode_ErrorMessage			= 3,
	kSystemMsgCode_GeneralMessage		= 4,
	kSystemMsgCode_WebNotice			= 5,
	kSystemMsgCode_Notify				= 6,

	kCustomMsgCode_RequestFriendOK		= 1,
	kCustomMsgCode_RequestFriendFailed	= 2,
	kCustomMsgCode_NoteReply			= 3,
};

enum	NMREJECTCODE
{
	kRejectCode_Unknown				= 0,

	kRejectCode_Reject				= 1,
	kRejectCode_NotOnline			= 2,
	kRejectCode_NoResponse			= 3,
	kRejectCode_NoSession			= 4,
	kRejectCode_NotSupported		= 5,
};

enum	NMCONFIRMCODE
{
	kConfirmUnknown					= 0,

	kConfirmOK						= 1,
	kConfirmDenied					= 2,
	kConfirmLater					= 3,

	kConfirmNext					= 4,
};

enum	NMFILESTATUS
{
	kFileStatus_Unknown				= 0,

	kFileStatus_WaitSend			= 1,
	kFileStatus_SendFile			= 2,
	kFileStatus_SendComplete		= 3,
	kFileStatus_WaitConfirm			= 4,
	kFileStatus_GetFile				= 5,
	kFileStatus_GetComplete			= 6,

	kFileStatus_FileOpenError		= 100,
	kFileStatus_FileReadError		= 101,
	kFileStatus_FileWriteError		= 102,
	kFileStatus_NetworkError		= 103,
	kFileStatus_SessionError		= 104,
	kFileStatus_CantSupport			= 105,

	kFileStatus_UserAbort			= 200,
};

enum	NMNOTEBOXTYPE
{
	kNoteBoxType_NULL				= 0,

	kNoteBoxType_Volatile			= 1,
	kNoteBoxType_TempBox			= 2,
	kNoteBoxType_PermBox			= 3,

	kNoteBoxType_RecvBox			= 10,
	kNoteBoxType_SendBox			= 11,
	kNoteBoxType_KeepBox			= 12,
};

enum	NMNOTEOPERATION
{
	kNoteOperation_NULL				= 0,

	kNoteOperation_SetReadFlag		= 1,
	kNoteOperation_DeleteTempNote	= 2,
	kNoteOperation_DeletePermNote	= 3,
	kNoteOperation_SaveToPermBox	= 4,
};

enum	NMCONFIGTYPE
{
	kConfigType_NULL				= 0,

	// Save to Server
	kConfigType_AllowChat			= 1,
	kConfigType_AllowNote			= 2,
	kConfigType_AllowGameInvite		= 3,
	kConfigType_AllowFileShare		= 4,
	kConfigType_AllowFriendRequest	= 5,
};

enum	NMREFRESHTYPE
{
	kRefresh_NULL					= 0,

	kRefresh_Avatar					= 1,
	kRefresh_GameMoney				= 2,
	kRefresh_NickName				= 3,
	kRefresh_Config					= 4,

	kRefresh_GuildInfo				= 100,
	kRefresh_GuildList				= 101,
};

enum	NMALLOWTYPE
{
	kConfigAllowValue_Unknown				= 0,

	kConfigAllowValue_FromAll				= 1,
	kConfigAllowValue_FromFriend			= 2,
	kConfigAllowValue_FromNoBody			= 3,
	kConfigAllowValue_FromFriendOrMatched	= 4,
};

enum	NMLOCALCONFIGTYPE
{
	kLocalConfig_NULL				= 0,

	kLocalConfig_FileDownloadDir,
	kLocalConfig_LogChatMsg,
	kLocalConfig_ChatLogDir,
	kLocalConfig_ShowGameStatus,

	kLocalConfig_Start				= kLocalConfig_FileDownloadDir,
	kLocalConfig_End				= kLocalConfig_ShowGameStatus,
	kLocalConfig_Count				= ( kLocalConfig_End - kLocalConfig_Start + 1 ),
};

enum	NMCATEGORYPROPERTY
{
	kCateProp_None					= 0,
	kCateProp_DefaultCategory		= 1 << 0,
	kCateProp_CateNotDelete			= 1 << 1,
	kCateProp_CateNotRename			= 1 << 2,
	kCateProp_UserNotAdd			= 1 << 3,
	kCateProp_UserNotDelete			= 1 << 4,
};

#define	IS_DEFAULTCATEGORY( x )		( ( x & kCateProp_DefaultCategory ) ? TRUE : FALSE )
#define	SET_DEFAULTCATEGORY( x )	{ x |= ( kCateProp_DefaultCategory | kCateProp_CateNotDelete );\
									  x &= ~( kCateProp_UserNotAdd | kCateProp_UserNotDelete ); }
#define	RESET_DEFAULTCATEGORY( x )	{ x &= ~( kCateProp_DefaultCategory | kCateProp_CateNotDelete ); }


enum
{
	kUserFlag_NULL				= 0x0000000,

	kUserFlag_Blocked			= 0x00000001,
	kUserFlag_WaitingConfirm	= 0x00000002,
	kUserFlag_AddNxFriend		= 0x00000004,

	// For Virtual Friend Only. Do not save to DB
	kUserFlag_LevelUp			= 0x00000010,
	kUserFlag_LicenseUp			= 0x00000020,
	
	kUserFlag_GeneralLevelUp	= 0x00000100,
	kUserFlag_PVPLevelUp		= 0x00000200,
	kUserFlag_ClassChanged		= 0x00000400,
	kUserFlag_GeneralGradeUp	= 0x00000800,
};

// Macros
#define		IS_ONLINE( userStatus )					( userStatus != kStatus_Unknown && userStatus != kStatus_AppearOffline && userStatus != kStatus_Offline )
#define		IS_BLOCKED( userFlag )					( ( userFlag & kUserFlag_Blocked ) != 0 )
#define		IS_WAITCONFIRM( userFlag )				( ( userFlag & kUserFlag_WaitingConfirm ) != 0 )
#define		SET_BLOCKED( userFlag )					userFlag = ( userFlag | kUserFlag_Blocked )
#define		SET_WAITCONFIRM( userFlag )				userFlag = ( userFlag | kUserFlag_WaitingConfirm )
#define		RESET_BLOCKED( userFlag )				userFlag = ( userFlag & ( ~kUserFlag_Blocked ) )
#define		RESET_WAITCONFIRM( userFlag )			userFlag = ( userFlag & ( ~kUserFlag_WaitingConfirm ) )

#define		USER_OPEN_NAME_TYPE( userMask )			( ( userMask >> 14 ) & 0x00000003 )
#define		USER_OPEN_SEX_TYPE( userMask )			( kOpenType_All )
#define		USER_OPEN_AGE_TYPE( userMask )			( ( userMask >> 18 ) & 0x00000003 )
#define		USER_OPEN_BIRTHDAY_TYPE( userMask )		( ( userMask >> 20 ) & 0x00000003 )
#define		USER_OPEN_SCHOOL_TYPE( userMask )		( ( userMask >> 22 ) & 0x00000003 )
#define		USER_OPEN_AREA_TYPE( userMask )			( ( userMask >> 24 ) & 0x00000003 )
#define		USER_OPEN_TEL_TYPE( userMask )			( ( userMask >> 26 ) & 0x00000003 )
#define		USER_OPEN_MOBILE_TYPE( userMask )		( ( userMask >> 28 ) & 0x00000003 )
#define		USER_OPEN_TYPE_ALL( userMask )			( ( userMask >> 14 ) & 0x0000FFFF )
#define		USER_TYPE( userMask )					( ( userMask >> 29 ) & 0x00000003 )

#define		SET_USER_OPEN_TYPE( userMask, type, offset )	userMask = ( ( type & 0x00000003 ) << offset ) | ( userMask & ( ~( 0x00000003 << offset ) ) )
#define		SET_USER_OPEN_NAME_TYPE( userMask, type )		SET_USER_OPEN_TYPE( userMask, type, 14 )
#define		SET_USER_OPEN_SEX_TYPE( userMask, type )		( FALSE )
#define		SET_USER_OPEN_AGE_TYPE( userMask, type )		SET_USER_OPEN_TYPE( userMask, type, 18 )
#define		SET_USER_OPEN_BIRTHDAY_TYPE( userMask, type )	SET_USER_OPEN_TYPE( userMask, type, 20 )
#define		SET_USER_OPEN_SCHOOL_TYPE( userMask, type )		SET_USER_OPEN_TYPE( userMask, type, 22 )
#define		SET_USER_OPEN_AREA_TYPE( userMask, type )		SET_USER_OPEN_TYPE( userMask, type, 24 )
#define		SET_USER_OPEN_TEL_TYPE( userMask, type )		SET_USER_OPEN_TYPE( userMask, type, 26 )
#define		SET_USER_OPEN_MOBILE_TYPE( userMask, type )		SET_USER_OPEN_TYPE( userMask, type, 28 )
#define		SET_USER_OPEN_ALL( userMask, newMask )			( userMask = ( newMask & 0x3FFFC000 ) | ( userMask & ~( 0x3FFFC000 ) ) )

#define		USER_IS_BLOCKED_BYADMIN( userMask )		( ( userMask & 0x00000200 ) != 0 )
#define		USER_IS_BLOCKED_BYWARNING( userMask )	( ( userMask & 0x00000002 ) != 0 )
#define		USER_IS_BLOCKED_BYLOGINFAIL( userMask )	( ( userMask & 0x00000001 ) != 0 )
#define		USER_IS_BLOCKED( userMask )				( ( userMask & 0x00000203 ) != 0 )
#define		USER_IS_WRONG_OWNER( userMask )			( ( userMask & 0x00000100 ) != 0 )
#define		USER_IS_MAINID( userMask )				( ( userMask & 0x00000010 ) != 0 )
#define		USER_IS_TEMPID( userMask )				( ( userMask & 0x0000000C ) != 0 )
#define		USER_IS_CHILD_TEMPID( userMask )		( ( userMask & 0x00000004 ) != 0 )
#define		USER_IS_FOREIGNER_TEMPID( userMask )	( ( userMask & 0x00000008 ) != 0 )

#define		GET_HASHCODE( idcode )					_UInt8_( idcode >> 56 )
#define		GET_OID( idcode )						_UInt32_( _UInt32_( idcode >> 32 ) & 0xFFFFFFFF )
#define		GET_GAMECODE( idcode )					_UInt32_( idcode )
#define		GET_GAMEMAINCODE( idcode )				_UInt16_( _UInt32_( idcode ) >> 16 )
#define		GET_GAMESUBCODE( idcode )				_UInt16_( idcode )

#define		MAKE_GAMECODE( maincode, subcode )		( ( maincode << 16 ) | subcode )

#define		VERIFY_GAMECODE( gamecode )				VerifyGameCode( gamecode );

// Global Constants
enum
{
	kMaxSessionMemberSize		= 30,
	kMaxHashCodeValue			= 128,
};

enum
{	// Open Type
	kOpenType_NULL				= 0,

	kOpenType_All				= 1,
	kOpenType_FriendsOnly		= 2,
	kOpenType_Closed			= 3,
};

enum
{	// Main Game Codes
	kMainGameCode_NULL			= 0,

	kMainGameCode_Start			= 0x0001,

	kMainGameCode_nexoncom		= 0x0001,
	kMainGameCode_baram			= 0x0002,
	kMainGameCode_lod			= 0x0003,
	kMainGameCode_elan			= 0x0004,
	kMainGameCode_asgard		= 0x0006,
	kMainGameCode_mabi			= 0x0007,
	kMainGameCode_tales			= 0x0008,
	kMainGameCode_maplestory	= 0x0009,
	kMainGameCode_ca			= 0x000B,
	kMainGameCode_qplay			= 0x000C,
	kMainGameCode_zera			= 0x000D,
	kMainGameCode_zeratest		= 0x000E,
	kMainGameCode_zeratest_internal
								= 0x000F,
	kMainGameCode_lunia			= 0x0010,

	kMainGameCode_nexonjp		= 0x0100,
	kMainGameCode_nexoncn		= 0x0101,
	kMainGameCode_nexontw		= 0x0102,
	kMainGameCode_nexonus		= 0x0200,

	kMainGameCode_guild			= 0x1000,
	kMainGameCode_gworld		= 0x1001,
	kMainGameCode_reservedid	= 0x1002,
	kMainGameCode_guildzera		= 0x1003,
	kMainGameCode_navertoolbar	= 0x1004,
};

enum
{
	// Sub Game Codes
	kSubGameCode_NULL			= 0,

	// for Application Login
	kSubGameCode_messenger		= 0x0001,
	kSubGameCode_plug			= 0x0002,

	// for Web Login
	kSubGameCode_web_main		= 0x1001,

	// for Kart Related Game
	kSubGameCode_kartrider		= 0x2101,
	kSubGameCode_karttest		= 0x2102,

	// for Nexon Game
	kSubGameCode_zera			= 0x2202,
	kSubGameCode_bigshot		= 0x2203,
	kSubGameCode_nexonbyul		= 0x2206,
	kSubGameCode_combatarms		= 0x2207,
	kSubGameCode_talesweaver	= 0x2208,
	kSubGameCode_cso			= 0x2009,
	kSubGameCode_ca				= 0x200A,
	kSubGameCode_heroes			= 0x200B,
	kSubGameCode_petcity		= 0x200C,

	// for Publishing Game
	kSubGameCode_warrock		= 0x7004,
	kSubGameCode_lunia			= 0x7005,
	kSubGameCode_ninedragons	= 0x7006,
	kSubGameCode_nanaimo		= 0x7007,
	kSubGameCode_kickoff		= 0x7008,	//	94216
	kSubGameCode_koongpa		= 0x7009,
	kSubGameCode_fighterstory	= 0x700A,
	kSubGameCode_slapshot		= 0x700B,	//	94219
	kSubGameCode_elsword		= 0x7010,
	kSubGameCode_sp1			= 0x7011,

	// for Not Game
	kSubGameCode_navertoolbar	= 0xE001,
	kSubGameCode_scrapper		= 0xE002,

	// for TEST
	kSubGameCode_testgame		= 0xF001,
	kSubGameCode_zeratest		= 0xF002,
	kSubGameCode_luniatest		= 0xF003,
	kSubGameCode_zeratest_internal
								= 0xF004,
	kSubGameCode_kickofftest
								= 0xF005,
	kSubGameCode_nanaimotest
								= 0xF006,
	kSubGameCode_csotest		= 0xF007,
	kSubGameCode_csointernal	= 0xF008,
	kSubGameCode_csodevelopment	= 0xF009,
	kSubGameCode_elswordtest	= 0xF00A,
};

enum
{
	// Sub Guild Codes
	kSubGuildCode_NULL				= 0,

	// for game guild
	kSubGuildCode_kart				= 2,
	kSubGuildCode_warrock			= 3,
	kSubGuildCode_bigshot			= 4,
	kSubGuildCode_zera				= 5,
	kSubGuildCode_zeratest			= 0x03E9,
	kSubGuildCode_zeratest_internal	= 0x03EA,
	kSubGuildCode_lunia				= 6,
	kSubGuildCode_manmani			= 7,
	kSubGuildCode_luniatest			= 8,
	kSubGuildCode_koongpa			= 9,

	// for friendship guild
	kSubGuildCode_friendship		= 511,

	// fo all guild
	kSubGuildCode_all				= 0xF000,
};

enum
{
	// Game Code List
	kGameCode_NULL				= ( ( ( kMainGameCode_NULL ) << 16 ) | kSubGameCode_NULL ),
	kCategoryCode_NULL			= 0,

	kGameCode_nexoncom			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_NULL ),
	kGameCode_nexonjp			= MAKE_GAMECODE( kMainGameCode_nexonjp		, kSubGameCode_NULL ),
	kGameCode_nexoncn			= MAKE_GAMECODE( kMainGameCode_nexoncn		, kSubGameCode_NULL ),
	kGameCode_nexontw			= MAKE_GAMECODE( kMainGameCode_nexontw		, kSubGameCode_NULL ),
	kGameCode_nexonus			= MAKE_GAMECODE( kMainGameCode_nexonus		, kSubGameCode_NULL ),
	kGameCode_messenger			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_messenger ),
	kGameCode_plug				= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_plug ),
	kGameCode_web				= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_web_main ),

	kGameCode_baram				= MAKE_GAMECODE( kMainGameCode_baram		, kSubGameCode_NULL ),
	kGameCode_lod				= MAKE_GAMECODE( kMainGameCode_lod			, kSubGameCode_NULL ),
	kGameCode_elan				= MAKE_GAMECODE( kMainGameCode_elan			, kSubGameCode_NULL ),
	kGameCode_asgard			= MAKE_GAMECODE( kMainGameCode_asgard		, kSubGameCode_NULL ),
	kGameCode_mabi				= MAKE_GAMECODE( kMainGameCode_mabi			, kSubGameCode_NULL ),
	kGameCode_tales				= MAKE_GAMECODE( kMainGameCode_tales		, kSubGameCode_NULL ),
	kGameCode_maplestory		= MAKE_GAMECODE( kMainGameCode_maplestory	, kSubGameCode_NULL ),
	kGameCode_ca				= MAKE_GAMECODE( kMainGameCode_ca			, kSubGameCode_NULL ),
	kGameCode_ca_jp				= MAKE_GAMECODE( kMainGameCode_nexonjp		, kSubGameCode_ca ),
	kGameCode_qplay				= MAKE_GAMECODE( kMainGameCode_qplay		, kSubGameCode_NULL ),

	kGameCode_kartrider			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_kartrider ),
	kGameCode_karttest			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_karttest ),
	kGameCode_kartrider_cn		= MAKE_GAMECODE( kMainGameCode_nexoncn		, kSubGameCode_kartrider ),
	kGameCode_karttest_cn		= MAKE_GAMECODE( kMainGameCode_nexoncn		, kSubGameCode_karttest ),
	kGameCode_kartrider_tw		= MAKE_GAMECODE( kMainGameCode_nexontw		, kSubGameCode_kartrider ),
	kGameCode_karttest_tw		= MAKE_GAMECODE( kMainGameCode_nexontw		, kSubGameCode_karttest ),
	kGameCode_kartrider_us		= MAKE_GAMECODE( kMainGameCode_nexonus		, kSubGameCode_kartrider ),
	kGameCode_karttest_us		= MAKE_GAMECODE( kMainGameCode_nexonus		, kSubGameCode_karttest ),	

	kGameCode_zera				= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_zera ),
	kGameCode_bigshot			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_bigshot ),
	kGameCode_bigshot_cn		= MAKE_GAMECODE( kMainGameCode_nexoncn		, kSubGameCode_bigshot ),
	kGameCode_nexonbyul			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_nexonbyul ),
	kGameCode_combatarms		= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_combatarms ),
	kGameCode_talesweaver		= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_talesweaver ),
	kGameCode_talesweaver_cn	= MAKE_GAMECODE( kMainGameCode_nexoncn		, kSubGameCode_talesweaver ),
	kGameCode_cso				= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_cso ),
	kGameCode_heroes			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_heroes ),
	kGameCode_petcity			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_petcity ),

	kGameCode_warrock			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_warrock ),
	kGameCode_lunia				= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_lunia ),
	kGameCode_lunia_jp			= MAKE_GAMECODE( kMainGameCode_nexonjp		, kSubGameCode_lunia ),
	kGameCode_ninedragons		= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_ninedragons ),
	kGameCode_nanaimo			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_nanaimo ),
	kGameCode_kickoff			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_kickoff ),
	kGameCode_koongpa			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_koongpa ),
	kGameCode_fighterstory		= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_fighterstory ),
	kGameCode_slapshot			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_slapshot ),
	kGameCode_elsword			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_elsword ),
	kGameCode_sp1				= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_sp1 ),

	kGameCode_navertoolbar		= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_navertoolbar ),
	kGameCode_scrapper			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_scrapper ),	

	kGameCode_testgame			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_testgame ),
	kGameCode_zeratest			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_zeratest ),
	kGameCode_luniatest			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_luniatest ),
	kGameCode_luniatest_jp		= MAKE_GAMECODE( kMainGameCode_nexonjp		, kSubGameCode_luniatest ),
	kGameCode_zeratest_internal	
								= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_zeratest_internal ),
	kGameCode_kickofftest		= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_kickofftest ),
	kGameCode_nanaimotest		= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_nanaimotest ),
	kGameCode_csotest			= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_csotest ),
	kGameCode_csointernal		= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_csointernal ),
	kGameCode_csodevelopment	= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_csodevelopment ),
	kGameCode_elswordtest		= MAKE_GAMECODE( kMainGameCode_nexoncom		, kSubGameCode_elswordtest ),	

	kGameCode_guild				= MAKE_GAMECODE( kMainGameCode_guild		, kSubGameCode_NULL ),
	kGameCode_gworld			= MAKE_GAMECODE( kMainGameCode_gworld		, kSubGameCode_NULL ),
	kGameCode_reservedid		= MAKE_GAMECODE( kMainGameCode_reservedid	, kSubGameCode_NULL ),
};

#define		MAKE_GUILDCODE( subcode )				MAKE_GAMECODE( kMainGameCode_guild, subcode )
#define		MAKE_GUILDKEY( guildcode, guildserial )	( ( ( _UInt64_ )guildcode << 32 ) | guildserial )

#define		GET_GUILDCODE( guildkey )				_UInt32_( _UInt32_( guildkey >> 32 ) & 0xFFFFFFFF )
#define		GET_GUILDSERIAL( guildkey )				_UInt32_( guildkey )

enum
{
	// Guild Code List
	kGuildCode_NULL				= MAKE_GUILDCODE( kSubGuildCode_NULL ),

	kGuildCode_kart				= MAKE_GUILDCODE( kSubGuildCode_kart ),
	kGuildCode_warrock			= MAKE_GUILDCODE( kSubGuildCode_warrock ),
	kGuildCode_bigshot			= MAKE_GUILDCODE( kSubGuildCode_bigshot ),
	kGuildCode_zera				= MAKE_GUILDCODE( kSubGuildCode_zera ),
	kGuildCode_zeratest			= MAKE_GUILDCODE( kSubGuildCode_zeratest ),
	kGuildCode_zeratest_internal
								= MAKE_GUILDCODE( kSubGuildCode_zeratest_internal ),
	kGuildCode_lunia			= MAKE_GUILDCODE( kSubGuildCode_lunia ),
	kGuildCode_manmani			= MAKE_GUILDCODE( kSubGuildCode_manmani ),
	kGuildCode_luniatest		= MAKE_GUILDCODE( kSubGuildCode_luniatest ),
	kGuildCode_koongpa			= MAKE_GUILDCODE( kSubGuildCode_koongpa ),

	kGuildCode_friendship		= MAKE_GUILDCODE( kSubGuildCode_friendship ),

	kGuildCode_all				= MAKE_GUILDCODE( kSubGuildCode_all ),
};

enum	NMLoginAuthReplyCode
{	//	Reply code for login result to authentication server
	kLoginAuth_OK,									//	성공

	kLoginAuth_ServiceShutdown			= 20002,	//	서비스 종료
	kLoginAuth_BlockedIP				= 20007,	//	제한된 아이피
	kLoginAuth_NotAllowedLocale			= 20003,	//	시스템에 맞지 않는 로케일
	kLoginAuth_ServerFailed				= 20000,	//	인증서버 실패
	kLoginAuth_WrongID					= 20006,	//	해당 아이디가 없거나 오류가 있음
	kLoginAuth_WrongPassword			= 20026,	//	암호가 맞지 않음
	kLoginAuth_WrongOwner				= 20028,	//	본인 아님 인증된 유저
	kLoginAuth_WithdrawnUser			= 20027,	//	탈퇴한 회원
	kLoginAuth_UserNotExists			= 20025,	//	존재하지 않는 아이디
	kLoginAuth_TempBlockedByLoginFail	= 20008,	//	여러 차례 로그인 실패로 인해 임시로 사용 제한
	kLoginAuth_TempBlockedByWarning		= 20009,	//	운영자에 의해 임시로 사용 제한
	kLoginAuth_BlockedByAdmin			= 20010,	//	운영자에 의해 사용 제한
	kLoginAuth_NotAllowedServer			= 20021,	//	인증서버에서 받아들이지 않음
	kLoginAuth_InvalidPassport			= 20015,	//	패스포트가 맞지 않음
	
	kLoginAuth_ModuleNotInitialized		= 30002,	//	메신저 모듈 초기화가 안되어 있음
	kLoginAUth_ModuleInitializeFailed	= 30003,	//	메신저 모듈 초기화에 실패
};

enum	NMMessengerReplyCode
{	// Result Code for Messenger Reply
	kMessengerReplyOK										= 0,
	kMessengerReplyNewUser									= 1,

	kMessengerReplyNotMine									= -1,
	kMessengerReplyWrongId									= -2,
	kMessengerReplyWrongPwd									= -3,
	kMessengerReplyWrongClientVersion						= -4,
	kMessengerReplyWrongMsgVersion							= -5,
	kMessengerReplyServiceShutdown							= -6,
	kMessengerReplyLockedByAnotherProcess					= -7,
	kMessengerReplySwitchSA									= -8,
	kMessengerReplyWrongOwner								= -9,
	kMessengerReplyBlockByAdmin								= -10,
	kMessengerReplyTempBlockByWarning						= -11,
	kMessengerReplyTempBlockByLoginFail						= -12,
	kMessengerReplyMabiInfoSoapFail							= -13,
	kMessengerReplyWrongPassport							= -14,
	kMessengerReplyBlockedIp								= -15,
	kMessengerReplyTempUser									= -16,
	kMessengerReplyNotAuthenticated							= -17,

	kMessengerReplyInvalidExternalPassportType				= -18,
	kMessengerReplyExternalPassportDecoderNotImplemented	= -19,
	kMessengerReplyExternalPassportDecodingFailed			= -20,
	kMessengerReplyWrongExternalPassport					= -21,

	kMessengerReplyServerFailed								= -99,
};

enum
{
	kErrorNo_Success			= 0,

	kErrorNo_Unknown			= 0xFFFFFFFF,
};

enum
{
	// Messenger Service Support Flag
	kSupportFlag_ChatSession				= 1 << 0,
	kSupportFlag_FileUpDnSession			= 1 << 1,
	kSupportFlag_WBSession					= 1 << 2,
	kSupportFlag_AddFriendConfirm			= 1 << 3,
	kSupportFlag_ServerMessage				= 1 << 4,
	kSupportFlag_GeneralNoteMsg				= 1 << 5,
	kSupportFlag_GameInviteNoteMsg			= 1 << 6,
	kSupportFlag_ItemGiftNoteMsg			= 1 << 7,
	kSupportFlag_RequestSession				= 1 << 8,
	kSupportFlag_GuildNoticeNoteMsg			= 1 << 9,
	kSupportFlag_GuildChatRoomInviteNoteMsg	= 1 << 10,
	kSupportFlag_WarningNoteMsg				= 1 << 11,
	kSupportFlag_GuildChatRoom				= 1 << 12,
	kSupportFlag_GuildNote					= 1 << 13,
	kSupportFlag_GuildWarInviteNote			= 1 << 14,
	kSupportFlag_CustomNotify				= 1 << 15,
	kSupportFlag_NexonEvent					= 1 << 16,		//	경품행사 등 각종 이벤트

	kSupportFlag_All						= 0x7FFFFFFF,
	kSupportFlag_FromGameCode				= 0x80000000,
};

enum
{
	kChatRoomFlag_IsDefaultRoom				= 1 << 0,
	kChatRoomFlag_IsOpen					= 1 << 1,
};

enum
{
	//
	//	204
	//		- 쪽지 차단 리스트 제외
	//
	kVersion_UserDataList		= 204,

	//
	//	201
	//		- AppServer 컨피규레이션 추가
	//
	kVersion_ServerConfigData	= 201,
};

#pragma pack( push, 4 )

struct NMVirtualKey
{
	NMGameCode				uGameCode;
	_UInt32_				uVirtualIDCode;
};

bool operator==( const NMVirtualKey& keyLeft, const NMVirtualKey& keyRight );

#define	INITIALIZE_VIRTUALKEY( x )	{ x.uGameCode = 0; x.uVirtualIDCode = 0; }
#define	IS_VIRTUALKEY_NULL( x )		( x.uGameCode == 0 && x.uVirtualIDCode == 0 )
#define	ENCODE_VIRTUALKEY( x )		{ encoder.Encode4( x.uGameCode ); encoder.Encode4( x.uVirtualIDCode ); }
#define	DECODE_VIRTUALKEY( x )		{ x.uGameCode = decoder.Decode4(); x.uVirtualIDCode = decoder.Decode4(); }
#define	VALIDATE_VIRTUALKEY( x )	{ 	VERIFY_GAMECODE( x.uGameCode );\
	if ( x.uVirtualIDCode == 0 ) { x.uVirtualIDCode	= 1; } }

extern	NMVirtualKey const	keyVirtualAll;
extern	NMVirtualKey const	keyNexoncom;
extern	NMVirtualKey const	keyKart;

struct NMFriendCode
{
	NMIDCode				uIDCode;
	NMVirtualKey			keyVirtual;
};

bool operator==( const NMFriendCode& fcLeft, const NMFriendCode& fcRight );

#define	INITIALIZE_FRIENDCODE( x )	{ x.uIDCode = 0; INITIALIZE_VIRTUALKEY( x.keyVirtual ); }
#define ENCODE_FRIENDCODE( x )		{ encoder.Encode8( x.uIDCode ); ENCODE_VIRTUALKEY( x.keyVirtual ); }
#define DECODE_FRIENDCODE( x )		{ x.uIDCode = decoder.Decode8(); DECODE_VIRTUALKEY( x.keyVirtual ); }

struct NMFriendKey
{
	NMIDCode				uIDCode;
	NMVirtualKey			keyOwner;
	NMVirtualKey			keyVirtual;

	operator NMFriendCode() const
	{
		NMFriendCode	fcFriendCode;
		fcFriendCode.uIDCode	= uIDCode;
		fcFriendCode.keyVirtual	= keyVirtual;
		return fcFriendCode;
	}
};

bool operator==( const NMFriendKey& fkLeft, const NMFriendKey& fkRight );

#define	INITIALIZE_FRIENDKEY( x )	{ INITIALIZE_VIRTUALKEY( x.keyOwner ); INITIALIZE_VIRTUALKEY( x.keyVirtual ); }
#define	ENCODE_FRIENDKEY( x )		{ encoder.Encode8( x.uIDCode ); ENCODE_VIRTUALKEY( x.keyOwner ); ENCODE_VIRTUALKEY( x.keyVirtual ); }
#define	DECODE_FRIENDKEY( x )		{ x.uIDCode = decoder.Decode8(); DECODE_VIRTUALKEY( x.keyOwner ); DECODE_VIRTUALKEY( x.keyVirtual ); }

#pragma pack( pop )

// Util Function
void VerifyGameCode( NMGameCode& uGameCode );

class	CNMAvatarItemInfo : public CNMSerializable
{
public:
	enum	NMEMOTION
	{
		kEmotion_NULL		= 0,

		kEmotion_Normal		= 1,
		kEmotion_Smile		= 2,
		kEmotion_Cry		= 3,
		kEmotion_Angry		= 4,
		kEmotion_Nonsense	= 5,
	};

	NMEMOTION				uEmotion;
	_UInt32_				uIconItem;

public:
	CNMAvatarItemInfo( void );

	DECLARE_SERIALIZABLE
};

class	CNMAvatarFullInfo : public CNMAvatarItemInfo
{
public:
	NMSEXTYPE				uSex;
	_UInt16_				uMsgXPos;
	_UInt16_				uMsgYPos;
	_UInt32_				uFullItem[ FULL_LAYER_SIZE ];
	_UInt32_				uFullMaskInfo[ FULL_LAYER_SIZE ];

public:
	CNMAvatarFullInfo( void );
	bool operator==( CNMAvatarFullInfo& rInfo );

	DECLARE_SERIALIZABLE
};

class	CNMDefaultUserInfo : public CNMSerializable
{
public:
	// Permanent Data
	TCHAR					szLoginID[ LOGINID_SIZE ];
	NMIDCode				uIDCode;

	// Changeable Data
	_UInt32_				uFlag;
	TCHAR					szWord[ WORD_SIZE ];

	// Volatile Data
	NMUSERSTATUS			uStatus;
	_UInt32_				uLocalIP;
	_UInt32_				uVirtualIP;
	_UInt16_				uPort;

public:
	CNMDefaultUserInfo( void );

	DECLARE_SERIALIZABLE
};

class CNMRealFriendInfo : public CNMDefaultUserInfo
{
public:
	enum
	{
		kChangeFlag_All					= 0xFFFFFFFF,

		kChangeFlag_FlagChanged			= 1 << 0,
		kChangeFlag_WordChanged			= 1 << 1,
		kChangeFlag_StatusChanged		= 1 << 2,
		kChangeFlag_NetworkInfoChanged	= 1 << 3,
		kChangeFlag_MemoChanged			= 1 << 4,
		kChangeFlag_EmotionChanged		= 1 << 5,
		kChangeFlag_AvatarChanged		= 1 << 6,
		kChangeFlag_BirthdayChanged		= 1 << 7,
	};

public:
	TCHAR					szMemo[ MEMO_SIZE ];
	CNMAvatarItemInfo		aiAvatarItemInfo;

	SYSTEMTIME				stBirthday;
	_UInt8_					uBirthdayType;

public:
	CNMRealFriendInfo( void );

	DECLARE_SERIALIZABLE
};

class CNMVirtualFriendInfo : public CNMSerializable
{
public:
	enum
	{
		kChangeFlag_All					= 0xFFFFFFFF,

		kChangeFlag_FlagChanged			= 1 << 0,
		kChangeFlag_StatusChanged		= 1 << 1,
		kChangeFlag_NickNameChanged		= 1 << 2,
		kChangeFlag_PositionChanged		= 1 << 3,
		kChangeFlag_LevelChanged		= 1 << 4,
		kChangeFlag_LicenseChanged		= 1 << 5,

		kVirtualStatus_Offline			= 0,
		kVirtualStatus_SemiOnline		= 1,
		kVirtualStatus_Online			= 2,
	};

public:
	NMFriendKey				keyFriend;

	TCHAR					szNickName[ NICKNAME_SIZE ];
	TCHAR					szPosition[ POSITION_SIZE ];

	_UInt32_				uVirtualStatus;
	_UInt32_				uFlag;					// kUserFlag_XXX 값이 설정됨.
	_UInt32_				uLevel;

public:
	CNMVirtualFriendInfo( void );

	DECLARE_SERIALIZABLE
};

class	CNMFriendInfo : public CNMRealFriendInfo
{
public:
	NMFriendKey				keyFriend;

	TCHAR					szNickName[ NICKNAME_SIZE ];
	TCHAR					szPosition[ POSITION_SIZE ];

	BOOL					bIsVirtualOnline;
	_UInt32_				uLevel;

public:
	CNMFriendInfo( void );

	DECLARE_SERIALIZABLE

	NMFriendCode	GetFriendCode( void );
};

class	CNMRealUserInfo : public CNMDefaultUserInfo
{
	/* Mask Info Bit
	32		: Reserved
	31		: Reserved
	// Open Type	1 : All, 2 : Friends Only, 3 : Closed
	30, 29	: Mobile Phone
	28, 27	: Phone
	26, 25	: Region
	24, 23	: School
	22, 21	: Birth
	20, 19	: Age
	18, 17	: Sex
	16, 15	: Name
	// Info
	14		: AlertState
	13		: Foreigner Confirmed
	12		: Modify Info
	11		: Temp Password
	10		: Blocked By Admin
	9		: Not Owner Confirmed
	8		: Owner Confirmed
	7		: Parent Auth
	6		: Real Name Confirmed
	5		: Main ID
	4		: Foreigner Temp User 
	3		: Child Temp User
	2		: Blocked By Warning 
	1		: Blocked By Login Fail
	*/

public:
	TCHAR					szName[ NXNAME_SIZE ];
	TCHAR					szNickName[ NICKNAME_SIZE ];
	_UInt32_				uMaskInfo;
	NMSEXTYPE				uSex;
	_UInt32_				uAge;
	_UInt32_				uAreaCode;
	TCHAR					szAreaName[ NXAREA_SIZE ];
	_UInt32_				uSchoolCode;
	TCHAR					szSchoolName[ NXSCHOOL_SIZE ];
	SYSTEMTIME				stBirthday;
	CNMAvatarFullInfo		aiAvatarInfo;

	NMALLOWTYPE				uAllowChatType;
	NMALLOWTYPE				uAllowNoteType;
	NMALLOWTYPE				uAllowGameInviteType;
	NMALLOWTYPE				uAllowRequestFriendType;

	_UInt8_					uBirthdayType;
	TCHAR					szMabiID[ LOGINID_SIZE ];

public:
	CNMRealUserInfo( void );

	DECLARE_SERIALIZABLE
};

class	CNMCategoryInfo;
class	CNMSelfUserInfo : public CNMRealUserInfo
{
public:
	CNMSelfUserInfo( void );

	virtual void	OnHashValueUpdated( _UInt32_ uNewHashValue ) = 0;

	virtual void	OnCateFriendOperationError( LPCTSTR szMsg ) = 0;
	virtual void	OnNoteBoxOperationError( LPCTSTR szMsg ) = 0;
	virtual void	OnRejectedUserListOperationError( LPCTSTR szMsg ) = 0;

	virtual void	OnRealFriendListChanged( void ) = 0;
	virtual void	OnRealFriendInfoDeleted( NMIDCode uIDCode ) = 0;
	virtual void	OnRealFriendInfoChanged( _UInt32_ uChangeFlag, CNMRealFriendInfo* pRealFriendInfo, CNMAvatarFullInfo* pAvatarInfo, BOOL bNeedNotify ) = 0;

	virtual void	OnVirtualFriendListChanged( void ) = 0;
	virtual void	OnVirtualFriendInfoDeleted( NMFriendKey	keyFriend ) = 0;
	virtual void	OnVirtualFriendInfoChanged( _UInt32_ uChangeFlag, CNMVirtualFriendInfo* pVirtualFriendInfo ) = 0;

	virtual void	OnCategoryListChanged( void ) = 0;
	virtual void	OnCategoryInfoChanged( void ) = 0;

	virtual void	OnCateFriendMapChanged( void ) = 0;

	virtual void	OnRejectedUserListChanged( void ) = 0;
};

class	CNMVirtualUserInfo : public CNMSerializable
{
public:
	NMVirtualKey			keyVirtual;
	TCHAR					szNickName[ NICKNAME_SIZE ];
	TCHAR					szPosition[ POSITION_SIZE ];
	BOOL					bIsVirtualOnline;
	BOOL					bIsStealth;
	_UInt32_				uLoginTick;
	_UInt32_				uLevel;

public:
	CNMVirtualUserInfo( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMVirtualUserInfo >		NMVirtualUserList;

typedef std::vector< CNMRealFriendInfo >		NMRealFriendList;
typedef	std::vector< CNMVirtualFriendInfo >		NMVirtualFriendList;
typedef std::vector< CNMFriendInfo >			NMFriendList;
typedef std::vector< NMFriendCode >				NMFriendCodeList;
typedef std::vector< NMFriendKey >				NMFriendKeyList;

class	CNMFriendList : public CNMSerializable
{
public:
	NMFriendList	aMembers;

public:
	CNMFriendList( void );

	DECLARE_SERIALIZABLE
};

class	CNMCategoryInfo : public CNMSerializable
{
public:
	_UInt32_				uCategoryCode;
	NMVirtualKey			keyOwner;
	TCHAR					szCategoryName[ CATEGORY_SIZE ];
	_UInt8_					uProperty;
	_UInt8_					uAllowType;

public:
	CNMCategoryInfo( void );

	DECLARE_SERIALIZABLE
};

class	CNMCateFriendInfo : public CNMCategoryInfo
{
public:
	NMFriendList			aFriends;

public:
	CNMCateFriendInfo( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMCateFriendInfo >	NMCategoryUserList;

//////////////////////////////////////////////////////////////////////////
//	CNMGameCharacter
//////////////////////////////////////////////////////////////////////////

class CNMCharacter : public CNMSerializable
{
public:
	TCHAR			szLoginID[ LOGINID_SIZE ];
	NMVirtualKey	vkey;
	TCHAR			szNickname[ NICKNAME_SIZE ];

public:
	CNMCharacter();
	CNMCharacter(NMGameCode gameCode, LPCTSTR pszNickname, LPCTSTR pszLoginID = NULL, UINT32 uVirtualIDCode = 0);
	CNMCharacter(NMVirtualKey vkey, LPCTSTR pszNickname, LPCTSTR pszLoginID = NULL);

	DECLARE_SERIALIZABLE

public:
	NMGameCode		GetGameCode() const { return this->vkey.uGameCode; }
	void			SetGameCode(NMGameCode gameCode) { this->vkey.uGameCode = gameCode; }
	NMVirtualKey	GetVKey() const { return this->vkey; }

	void			NormalizeVirtualKey()
	{
		if (this->vkey.uGameCode == kGameCode_NULL) this->vkey.uGameCode = kGameCode_nexoncom;
		if (this->vkey.uVirtualIDCode == 0) this->vkey.uVirtualIDCode = 1;
	}

	LPCTSTR			GetName() const { return this->szNickname; }
};

typedef std::vector< CNMCharacter >	NMCharacterList;

//////////////////////////////////////////////////////////////////////////
//	CNMRejectedUser
//////////////////////////////////////////////////////////////////////////

class CNMRejectedUser : public CNMSerializable
{
public:
	CNMCharacter	rejectedCharacter;
	TCHAR			szMemo[ MEMO_SIZE ];

public:
	CNMRejectedUser();
	CNMRejectedUser(CNMCharacter const & rejectedCharacter, LPCTSTR pszMemo = NULL);

	DECLARE_SERIALIZABLE

public:
	const CNMCharacter & GetCharacter() const { return rejectedCharacter; }
	CNMCharacter & GetCharacter() { return rejectedCharacter; }
};

typedef std::vector<CNMRejectedUser> NMRejectedUserList;

//////////////////////////////////////////////////////////////////////////
//	CNMNoteInfo
//////////////////////////////////////////////////////////////////////////

class CNMNoteInfo2;		//	forward declaration

class CNMNoteInfo : public CNMSerializable
{
public:
	enum	NMNOTETYPE
	{
		kNoteType_NULL					= 0,

		kNoteType_GeneralMsg			= 1,
		kNoteType_GameInvite			= 2,
		kNoteType_ItemGift				= 3,
		kNoteType_GuildNotice			= 4,
		kNoteType_GuildChatRoomInvite	= 5,
		kNoteType_Warning				= 6,
		kNoteType_Guild					= 7,
		kNoteType_AdminNotice			= 8,
	};

	enum	NMNOTESUBTYPE
	{
		kNoteSubType_NULL				= 0,

		kNoteSubType_GuildNotice		= 1,
		kNoteSubType_GuildNotify		= 2,
		kNoteSubType_GuildChallenge		= 3,
		kNoteSubType_GuildAlly			= 4,
		kNoteSubType_GuildWarInvite		= 5,

	};

	// Note Info
	_UInt32_				uNoteSerial;
	NMNOTETYPE				uNoteType;
	_UInt32_				uSubType;
	NMVirtualKey			keyVirtual;
	union
	{
		BOOL				bReadFlag;
		BOOL				bSendFlag;
	};
	SYSTEMTIME				stTime;

	// User Info
	TCHAR					szTargetID[ TARGETID_SIZE ];

	TCHAR					szFromLoginID[ LOGINID_SIZE ];
	TCHAR					szFromNickName[ NICKNAME_SIZE ];

	// Contents
	TCHAR					szMessage[ NOTEMSG_SIZE ];
	TCHAR					szArgument[ NOTEARG_SIZE ];
	TCHAR					szCustomData[ NOTECUSTOM_SIZE ];
	CNMAvatarFullInfo		aiAvatarInfo;

public:
	CNMNoteInfo( void );
	CNMNoteInfo( CNMNoteInfo2 & rhs );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMNoteInfo >	NMNoteList;

class	CNMNoteInfoEx : public CNMSerializable
{
public:
	enum	NMNOTEEXTYPE
	{
		kNoteExType_NULL				= 0,

		kNoteExType_GameErrorReport		= 1,
	};

	NMNOTEEXTYPE			uNoteExType;
	TCHAR					szMessage[ NOTEEXMSG_SIZE ];

public:
	CNMNoteInfoEx( void );

	DECLARE_SERIALIZABLE
};

//////////////////////////////////////////////////////////////////////////
//	CNMNoteInfo2
//////////////////////////////////////////////////////////////////////////

class CNMNoteInfo2 : public CNMSerializable
{
	//--------------------------------------------------------------------
	//	enumerators
	//--------------------------------------------------------------------
public:

	enum	NMNOTETYPE
	{
		kNoteType_NULL					= 0,
		kNoteType_GeneralMsg			= 1,
		kNoteType_GameInvite			= 2,
		kNoteType_ItemGift				= 3,
		kNoteType_GuildNotice			= 4,
		kNoteType_GuildChatRoomInvite	= 5,
		kNoteType_Warning				= 6,
		kNoteType_Guild					= 7,
		kNoteType_AdminNotice			= 8,
	};

	enum	NMNOTESUBTYPE
	{
		kNoteSubType_NULL				= 0,
		kNoteSubType_GuildNotice		= 1,
		kNoteSubType_GuildNotify		= 2,
		kNoteSubType_GuildChallenge		= 3,
		kNoteSubType_GuildAlly			= 4,
		kNoteSubType_GuildWarInvite		= 5,
	};

	//--------------------------------------------------------------------
	//	constructor
	//--------------------------------------------------------------------
public:
	CNMNoteInfo2();
	CNMNoteInfo2( CNMNoteInfo & rhs );
	CNMNoteInfo2 & operator = ( CNMNoteInfo & rhs );

	DECLARE_SERIALIZABLE

	//--------------------------------------------------------------------
	//	service functions
	//--------------------------------------------------------------------
public:
	CNMCharacter const & GetSender() const { return this->sender; }
	CNMCharacter const & GetReceiver() const { return (!receiverList.empty()) ? receiverList[0] : defaultReceiver; }
	void SetReceiverVKey( NMVirtualKey keyVirtual )
	{	
		if ( !receiverList.empty() )		
			receiverList[ 0 ].vkey	= keyVirtual;	
	};
	void SetSender( CNMCharacter const & sender ) { this->sender = sender; }
	void SetReceiver( CNMCharacter const & receiver );

	//--------------------------------------------------------------------
	//	data members
	//--------------------------------------------------------------------
public:

	//	note info
	UINT32				uNoteSerial;
	NMNOTETYPE			uNoteType;
	NMNOTESUBTYPE		uNoteSubType;
	SYSTEMTIME			stTime;
	BOOL				bReadFlag;		//	read or not read
	BOOL				bSendFlag;		//	send or received

	//	contents
	TCHAR				szMessage[ NOTEMSG_SIZE ];
	TCHAR				szArgument[ NOTEARG_SIZE ];
	TCHAR				szCustomData[ NOTECUSTOM_SIZE ];

	//	sender
	CNMCharacter		sender;

	//	receivers
	NMCharacterList		receiverList;

private:
	//	used when there is no receiver in the receiver list
	static const CNMCharacter	defaultReceiver;
};

typedef std::vector< CNMNoteInfo2 > NMNoteInfoList;
typedef std::vector< CNMNoteInfo2 >	NMNoteList2;

//////////////////////////////////////////////////////////////////////////
//	ID list <-> Character list
//////////////////////////////////////////////////////////////////////////

namespace CNMNoteInfoHelper
{
	BOOL CharacterListToString( NMCharacterList const & characterList, LPTSTR pszBuffer, size_t nBufferSize );
	BOOL StringToCharacterList( LPCTSTR pszBuffer, NMCharacterList & characterList, NMGameCode gameCode );
};

//////////////////////////////////////////////////////////////////////////
//	CNMReportInfo
//////////////////////////////////////////////////////////////////////////

class	CNMReportInfo : public CNMSerializable
{
public:
	enum	NMREPORTTYPE
	{
		kReportType_NULL	= 0,
		kReportType_Board,
		kReportType_Guild,
		kReportType_Game,
		kReportType_Note,
		kReportType_Chat,
	};

	enum	NMREPORTSUBTYPE
	{
		kReportSubType_NULL							= 0,

		kReportSubType_AbusiveOrLewdWords			= 1,		//	욕설/음란
		kReportSubType_IllegalData					= 2,		//	불법자료
		kReportSubType_CommercialArticle			= 3,		//	상업적인 글
		kReportSubType_CopyrightViolation			= 9,		//	저작권 위반
		kReportSubType_PretendedToAdmin				= 11,		//	운영자 사칭
		kReportSubType_SpamOrAdvertisement			= 16,		//	스팸/광고
		kReportSubType_Disturbance					= 17,		//	상대방 비방
		kReportSubType_MoneyTrade					= 18,		//	현금거래
		kReportSubType_RequestedPrivateInfo			= 19,		//	개인 정보 요구
		
		kReportSubType_Etc							= 255,		//	기타
	};

	NMREPORTTYPE			uType;
	NMREPORTSUBTYPE			uSubType;
	NMGameCode				uGameCode;
	NMGameCode				uAppliedGameCode;
	TCHAR					szTargetId[ LOGINID_SIZE ];
	TCHAR					szReason[ 4000 ];
	TCHAR					szEvidence[ 20000 ];

public:
	CNMReportInfo( void );

	DECLARE_SERIALIZABLE
};

class	CNMChatMessage : public CNMSerializable
{
public:
	NMVirtualKey			keyVirtual;
	CNMAvatarItemInfo		aiAvatarItemInfo;

	TCHAR					szSenderName[ CHATSENDER_SIZE ];
	TCHAR					szChatHeader[ CHATHDR_SIZE ];
	TCHAR					szFontHeader[ CHATHDR_SIZE ];
	TCHAR					szChatMessage[ CHATMSG_SIZE ];

public:
	CNMChatMessage( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMChatMessage >	NMChatMessageList;

class	CNMFileEvent : public CNMSerializable
{
public:
	enum	NMFILESTATUS
	{
		kFileStatus_Unknown			= 0,

		kFileStatus_WaitSend		= 1,
		kFileStatus_SendFile		= 2,
		kFileStatus_SendComplete	= 3,
		kFileStatus_WaitConfirm		= 4,
		kFileStatus_GetFile			= 5,
		kFileStatus_GetComplete		= 6,

		kFileStatus_FileOpenError	= 100,
		kFileStatus_FileReadError	= 101,
		kFileStatus_FileWriteError	= 102,
		kFileStatus_NetworkError	= 103,
		kFileStatus_SessionError	= 104,
		kFileStatus_CantSupport		= 105,

		kFileStatus_UserAbort		= 200,
	};

	_UInt32_				uEventId;
	TCHAR					szFileName[ FILENAME_SIZE ];
	NMFILESTATUS			uStatus;
	_UInt32_				uParam1;
	_UInt32_				uParam2;
	BOOL					bSendFlag;

public:
	CNMFileEvent( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMFileEvent >		NMFileEventList;

class	CNMFilePath : public CNMSerializable
{
public:
	TCHAR	szValue[ MAX_PATH ];

public:
	CNMFilePath( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMFilePath >		NMFilePathList;

class	CNMMyGameInfo : public CNMSerializable
{
public:
	NMGameCode				aGameCodes[ MYGAME_COUNT ];

public:
	CNMMyGameInfo( void );

	DECLARE_SERIALIZABLE
};

#ifndef	_NMSERVER
class	CNMGuildInfo : public CNMSerializable
{
public:
	enum		NMGUILDTYPE
	{
		kGuildType_Unknown			= 0,

		kGuildType_Club				= 1,
		kGuildType_WebGameGuild		= 2,
		kGuildType_RPGGameGuild		= 3,
		kGuildType_KartGuild		= 4,
	};

	_UInt32_				uSerialNo;
	TCHAR					szName[ GUILDNAME_SIZE ];
	TCHAR					szId[ GUILDID_SIZE ];
	TCHAR					szHomeUrl[ GUILDHOME_SIZE ];
	NMGameCode				uGameCode;
	_UInt32_				uLevel;
	_UInt32_				uMemberCount;
	NMGUILDTYPE				uType;
	BOOL					bIsMaster;
	BOOL					bHasNewContent;

public:
	CNMGuildInfo( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMGuildInfo >		NMGuildInfoList;

class	CNMGuildListInfo : public CNMSerializable
{
public:
	NMGuildInfoList			aGuilds;

public:
	CNMGuildListInfo( void );

	DECLARE_SERIALIZABLE
};
#endif	// _NMSERVER

class	CNMGuildInfoEx : public CNMSerializable
{
public:
	NMGuildKey				uGuildKey;

	TCHAR					szName[ GUILDNAME_SIZE ];
	TCHAR					szId[ GUILDID_SIZE ];
	NMGameCode				uGameCode;

	TCHAR					szHomeUrl[ GUILDHOME_SIZE ];
	_UInt32_				uLevel;
	_UInt32_				uMemberCount;
	BOOL					bHasNewContent;

	BOOL					bIsMaster;

public:
	CNMGuildInfoEx( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMGuildInfoEx >		NMGuildInfoExList;

class	CNMGuildListExInfo : public CNMSerializable
{
public:
	NMGuildInfoExList			aGuilds;

public:
	CNMGuildListExInfo( void );

	DECLARE_SERIALIZABLE
};

class	CNMFindUserRequestInfo : public CNMSerializable
{
public:
	_UInt32_				uToken;

	TCHAR					szName[ NXNAME_SIZE ];
	_UInt32_				uAgeMin;
	_UInt32_				uAgeMax;
	NMSEXTYPE				uSex;
	_UInt32_				uRegion;
	_UInt32_				uSchoolCode;
	BOOL					bOnlineUserOnly;

public:
	CNMFindUserRequestInfo( void );

	DECLARE_SERIALIZABLE
};

class	CNMFindUserInfo : public CNMSerializable
{
public:
	NMIDCode				uIdCode;
	TCHAR					szId[ LOGINID_SIZE ];
	TCHAR					szName[ NXNAME_SIZE ];	// NULL String : closed
	TCHAR					szNickName[ NICKNAME_SIZE ];
	_UInt32_				uAge;					// 0 : closed
	NMSEXTYPE				uSex;					// 0 : closed
	_UInt32_				uAreaCode;				// 0 : closed
	_UInt32_				uSchoolCode;			// 0 : closed
	CNMAvatarItemInfo		aiAvatarInfo;
	NMUSERSTATUS			uStatus;		

public:
	CNMFindUserInfo( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMFindUserInfo >	NMFindUserInfoList;

class	CNMGuildMemberInfo : public CNMSerializable
{
public:
	NMIDCode				uIdCode;
	TCHAR					szLoginId[ LOGINID_SIZE ];
	TCHAR					szNickName[ NICKNAME_SIZE ];
	CNMAvatarItemInfo		aiAvatarInfo;
	BOOL					bIsGameOnline;
	_UInt8_					uLevel;
	_UInt32_				uGameLevel;
	_UInt32_				uVirtualIdCode;

public:
	CNMGuildMemberInfo( void );

	DECLARE_SERIALIZABLE
};

#ifdef _NMSERVER
typedef std::multimap< _UInt64_, CNMGuildMemberInfo >			NMGuildMemberList;
typedef NMGuildMemberList::iterator								guildmemberlist_iter;
typedef std::pair< guildmemberlist_iter, guildmemberlist_iter >	guildmemberlist_pair;
#else
typedef std::vector< CNMGuildMemberInfo >						NMGuildMemberList;
#endif

class	CNMGuildOnlineInfoEx : public CNMSerializable
{
public:
	NMGuildKey				uGuildKey;
	_UInt32_				uChatRoomSerialNo;
	_UInt32_				uChatRoomMemberCount;
	TCHAR					szChatServerIpAddr[ 32 ];
	_UInt16_				uChatServerPort;
	NMGuildMemberList		aOnlineGuildMembers;

public:
	CNMGuildOnlineInfoEx( void );

	DECLARE_SERIALIZABLE
};

class	CNMGuildOnlineInfo : public CNMSerializable
{
public:
	_UInt32_				uGuildSerialNo;
	_UInt32_				uChatRoomSerialNo;
	_UInt32_				uChatRoomMemberCount;
	TCHAR					szChatServerIpAddr[ 32 ];
	_UInt16_				uChatServerPort;
	NMGuildMemberList		aOnlineGuildMembers;

public:
	CNMGuildOnlineInfo( void );
	CNMGuildOnlineInfo&		operator=( CNMGuildOnlineInfoEx& infoEx );
	operator CNMGuildOnlineInfoEx( void );

	DECLARE_SERIALIZABLE
};

class	CNMSessionInfo : public CNMSerializable
{
public:
	enum
	{
		// session status
		kStatus_ConnectingToServer		= 0,
		kStatus_ConnectionEstablished,
		kStatus_WaitingForInviteUser,
		kStatus_SessionEstablished,
		kStatus_SwitchingServer,
		kStatus_WaitingForOtherUser,
		kStatus_ConnectionClosed,
		kStatus_ConnectionFailed,

		// session mode
		kMode_BiConnMode				= 0,
		kMode_MultiConnMode,

		// invite mode
		kInviteMode_Friend				= 0,
		kInviteMode_GuildMember,
		kInviteMode_Stranger,
	};

public:
	// Global
	NMSESSIONTYPE			uType;				// Never Change
	NMVirtualKey			keyVirtuality;		// Never Change
	BOOL					bPassive;			// Never Change

	_UInt32_				uInviteMode;		// Change One Time
	_UInt32_				uMode;				// Change One Time
	_UInt64_				uId;				// Change One Time
	NMFriendCode			fcAssocFriend;		// Change One Time

	_UInt32_				uStatus;			// Frequently Change
	_UInt32_				uServingProcessID;

public:
	CNMSessionInfo( void );

	DECLARE_SERIALIZABLE
};

class	CNMSessionMemberInfo : public CNMSerializable
{
public:
	NMIDCode				uIDCode;
	TCHAR					szLoginID[ LOGINID_SIZE ];
	TCHAR					szWord[ WORD_SIZE ];

	NMVirtualKey			keyVirtual;
	TCHAR					szNickName[ NICKNAME_SIZE ];
	_UInt32_				uStatus;
	CNMAvatarFullInfo		aiAvatarInfo;

public:
	CNMSessionMemberInfo( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMSessionMemberInfo >	NMSessionMemberList;

class	CNMSessionMemberList : public CNMSerializable
{
public:
	NMSessionMemberList		aMembers;

public:
	CNMSessionMemberList( void );

	DECLARE_SERIALIZABLE
};

class	CNMChatRoomMemberInfo : public CNMRealUserInfo
{
public:
	TCHAR					szTel[ NXTEL_SIZE ];
	NMVirtualKey			keyVirtual;
	_UInt32_				uLevel;
	TCHAR					szLevelName[ GUILDLEVEL_SIZE ];
	_UInt32_				uFlag;
	_UInt32_				uIpAddr;

public:
	CNMChatRoomMemberInfo( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMChatRoomMemberInfo >	NMChatRoomMemberList;

class	CNMChatRoomMemberList : public CNMSerializable
{
public:
	NMChatRoomMemberList	aMemberList;

public:
	CNMChatRoomMemberList( void );

	DECLARE_SERIALIZABLE
};

class CNMGuildMemberLevelInfo : public CNMSerializable
{
public:
	_UInt8_	uLevel;
	TCHAR	szName[ GUILDLEVEL_SIZE ];

public:
	CNMGuildMemberLevelInfo( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMGuildMemberLevelInfo >	NMGuildMemberLevelList;

class	CNMChatRoomInfoEx : public CNMSerializable
{
public:
	enum
	{
		// chat room status
		kStatus_ConnectingToServer		= 0,
		kStatus_ConnectionEstablished,
		kStatus_SessionEstablished,
		kStatus_ConnectionClosed,
		kStatus_ConnectionFailed,
	};

public:
	// Room Info

	_UInt32_				uRoomType;
	NMGuildKey				uGuildKey;
	_UInt32_				uRoomSerialNo;

	NMGameCode				uGameCode;
	TCHAR					szGuildTitle[ GUILDNAME_SIZE ];
	TCHAR					szRoomTitle[ ROOMTITLE_SIZE ];
	_UInt32_				uLimitLevel;
	_UInt32_				uMaxSize;
	_UInt32_				uFlag;
	TCHAR					szPassword[ ROOMPWD_SIZE ];

	NMIDCode				uOwnerIdCode;

	NMGuildMemberLevelList	aLevelInfo;

	_UInt32_				uStatus;
	_UInt32_				uRequestProcId;

public:
	CNMChatRoomInfoEx( void );

	DECLARE_SERIALIZABLE
};

#ifndef	_NMSERVER
class	CNMChatRoomInfo : public CNMSerializable
{
public:
	enum
	{
		// chat room status
		kStatus_ConnectingToServer		= 0,
		kStatus_ConnectionEstablished,
		kStatus_SessionEstablished,
		kStatus_ConnectionClosed,
		kStatus_ConnectionFailed,
	};

public:
	// Room Info
	_UInt32_				uRoomType;
	_UInt32_				uClubSerialNo;
	_UInt32_				uRoomSerialNo;

	NMGameCode				uGameCode;
	TCHAR					szClubTitle[ GUILDNAME_SIZE ];
	TCHAR					szRoomTitle[ ROOMTITLE_SIZE ];
	_UInt32_				uLimitLevel;
	_UInt32_				uMaxSize;
	_UInt32_				uFlag;
	TCHAR					szPassword[ ROOMPWD_SIZE ];

	NMIDCode				uOwnerIdCode;

	TCHAR					szLevel1Name[ GUILDLEVEL_SIZE ];
	TCHAR					szLevel2Name[ GUILDLEVEL_SIZE ];
	TCHAR					szLevel3Name[ GUILDLEVEL_SIZE ];
	TCHAR					szLevel4Name[ GUILDLEVEL_SIZE ];
	TCHAR					szLevel5Name[ GUILDLEVEL_SIZE ];

	_UInt32_				uStatus;
	_UInt32_				uRequestProcId;

public:
	CNMChatRoomInfo( void );
	CNMChatRoomInfo&		operator=( CNMChatRoomInfoEx& infoEx );
	operator CNMChatRoomInfoEx( void );
	DECLARE_SERIALIZABLE
};
#endif	// _NMSERVER

class CNMMessageData : public CNMSerializable
{
public:
	_SInt32_				nCode;
	TCHAR					szMessage[ SERVER_MSG_SIZE ];

public:
	CNMMessageData( void );
	CNMMessageData( INT32 nCode );

	DECLARE_SERIALIZABLE
};

// CallFunc	:	szRequestID, keyToVirtual, keyFromVirtual, uInsertCategoryCode(ignored), szMessage, bAddToNxFriend
// OnEvent	:	uSerialNo, keyToVirtual, keyFromVirtual, szFromLoginID, szFromNickName, szMessage

class CNMRequestNewFriendData : public CNMSerializable
{
public:
	_UInt32_				uSerialNo;

	TCHAR					szRequestID[ LOGINID_SIZE ];
	NMGameCode				uRequesteeGameCode;
	NMVirtualKey			keyToVirtual;

	TCHAR					szFromLoginID[ LOGINID_SIZE ];
	NMVirtualKey			keyFromVirtual;
	TCHAR					szFromNickName[ NICKNAME_SIZE ];

	_UInt32_				uInsertCategoryCode;
	TCHAR					szMessage[ MSG_SIZE ];

	BOOL					bAddToNxFriend;

public:
	CNMRequestNewFriendData( void );

	DECLARE_SERIALIZABLE
};

class CNMRequestSessionData : public CNMSerializable
{
public:
	_UInt32_				uDataKey;
	_UInt32_				uSessionType;
	TCHAR					szLoginId[ LOGINID_SIZE ];
	TCHAR					szNickName[ NICKNAME_SIZE ];

public:
	CNMRequestSessionData( void );

	DECLARE_SERIALIZABLE
};

class CNMGameInfo : public CNMSerializable
{
public:
	enum
	{
		kMask_CanExecute			= ( 0x1 << 0 ),
		kMask_Visible				= ( 0x1 << 1 ),
		kMask_MainID				= ( 0x1 << 2 ),
		kMask_New					= ( 0x1 << 3 ),
		kMask_Hot					= ( 0x1 << 4 ),
		kMask_RecommandedOver19		= ( 0x1 << 5 ),
		kMask_RecommandedUnder19	= ( 0x1 << 6 ),
		kMask_NotMainIDNotTemp		= ( 0x1 << 7 ),
		kMask_GetUserInstallPath	= ( 0x1 << 8 ),
	};

public:
	// id info
	NMGameCode				uGameCode;
	NMGameCode				uGameCodePrerequired;
	TCHAR					szID[ 32 ];
	TCHAR					szName[ 64 ];
	TCHAR					szCustomID[ 32 ];

	// executable info
	TCHAR					szRegistryRoot[ 1024 ];
	TCHAR					szRegistryExePath[ 1024 ];
	TCHAR					szSetupFileUrl[ 1024 ];

	// property info
	_UInt8_					uAgeLimit;
	_UInt32_				uMask;

	// aditional info
	TCHAR					szIconUrl[ 1024 ];
	TCHAR					szHomeUrl[ 1024 ];

	// for compatibility
	NMGameCode				uOldGameCode;

public:
	CNMGameInfo( void );

	// mask
	BOOL	CanExecute( void )				{ return ( this->uMask & kMask_CanExecute ) ? TRUE : FALSE; }
	BOOL	IsVisable( void )				{ return ( this->uMask & kMask_Visible ) ? TRUE : FALSE; }
	BOOL	NeedMainID( void )				{ return ( this->uMask & kMask_MainID ) ? TRUE : FALSE; }
	BOOL	IsNew( void )					{ return ( this->uMask & kMask_New ) ? TRUE : FALSE; }
	BOOL	IsHot( void )					{ return ( this->uMask & kMask_Hot ) ? TRUE : FALSE; }
	BOOL	IsRecommendedOver19( void )		{ return ( this->uMask & kMask_RecommandedOver19 ) ? TRUE : FALSE; }
	BOOL	IsRecommandedUnder19( void )	{ return ( this->uMask & kMask_RecommandedUnder19 ) ? TRUE : FALSE; }
	BOOL	IsNotMainIDNotTemp( void )		{ return ( this->uMask & kMask_NotMainIDNotTemp ) ? TRUE : FALSE; }
	BOOL	GetUserInstallPath( void )		{ return ( this->uMask & kMask_GetUserInstallPath ) ? TRUE : FALSE; }

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMGameInfo >			NMGameInfoList;

class CNMGameListData : public CNMSerializable
{
public:
	NMGameInfoList			aGameInfo;

public:
	CNMGameListData( void );

	DECLARE_SERIALIZABLE
};

class CNMCustomNotifyData : public CNMSerializable
{
public:
	enum
	{
		//	Custom Notify
		kCustomNotify_NULL			= 0,

		kCustomNotify_LevelChanged	= 4,
		kCustomNotify_ObtainLicence	= 5,
		kCustomNotify_ObtainItem	= 6,
		kCustomNotify_StageClear	= 7,	
	};

	enum
	{
		//	Custom notify policy
		kCustomNotifyPolicy_Default		= 0,

		kCustomNotifyPolicy_NonVolatile	= 1 << 1,		//	로그오프 한 사람이 나중에 로그온 할 때 볼 수 있음
		kCustomNotifyPolicy_Multiple	= 1 << 2,		//	같은 플래그에 대해서 여러개의 알림이 올 수 있음
	};

	UINT32			uType;

	NMIDCode		uToIDCode;
	NMVirtualKey	keyToVirtual;

	NMIDCode		uFromIDCode;
	NMVirtualKey	keyFromVirtual;
	TCHAR			szFromLoginID[ LOGINID_SIZE ];
	TCHAR			szFromNickName[ NICKNAME_SIZE ];

	TCHAR			szArgument[ MSG_SIZE ];
	SYSTEMTIME		stDateTime;

	UINT32			uPolicyFlag;						//	알림 정책 플래그

public:
	CNMCustomNotifyData( void );

	DECLARE_SERIALIZABLE
};

class CNMGameCode : public CNMSerializable
{
public:
	NMGameCode	uGameCode;

public:
	CNMGameCode( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMGameCode >				NMGameCodeList;

class CNMGameServerCode : public CNMSerializable
{
public:
	NMGameServerCode	uGameServerCode;

public:
	CNMGameServerCode( void );

	DECLARE_SERIALIZABLE
};

typedef std::vector< CNMGameServerCode >			NMGameServerCodeList;

class CNMConnConfig : public CNMSerializable
{
public:
	TCHAR				szLoginServerIp[ IPADDR_SIZE ];
	UINT16				uLoginServerPort;

	TCHAR				szStatServerIp[ IPADDR_SIZE ];
	UINT16				uStatServerPort;

	BOOL				bAutoPatch;

public:
	CNMConnConfig( void );
	CNMConnConfig(
		LPCTSTR	szLoginServerIp,
		UINT16	uLoginServerPort,
		LPCTSTR	szStatServerIp,
		UINT16	uStatServerPort,
		BOOL	bAutoPatch = TRUE );

	DECLARE_SERIALIZABLE
};

//	TEMPORARY
//	카트라이더 휴면 계정 이벤트용 데이터 오브젝트
class CNMKartDormant : public CNMSerializable
{
public:
	UINT32				uOIDUser;
	BOOL				bDormant;

public:
	CNMKartDormant();
	CNMKartDormant( UINT32 uOIDUser, BOOL bDormant );

	DECLARE_SERIALIZABLE
};

typedef std::vector<CNMKartDormant> NMKartDormantList;

class CNMKartDormantList : public CNMSerializable
{
public:
	NMKartDormantList	aKartDormant;

public:
	CNMKartDormantList();

	DECLARE_SERIALIZABLE
};

//
//	클라이언트에서 접속할 APP 서버 정보
//
class CAppServerInfo : public CNMSerializable
{
public:
	UINT32						uID;
	TCHAR						szIPAddress[ IPADDR_SIZE ];
	UINT16						uPort;

public:
	CAppServerInfo();
	CAppServerInfo(UINT32 uID, LPCTSTR pszIPAddress, UINT16 uPort);

	DECLARE_SERIALIZABLE
};

typedef std::vector<CAppServerInfo> CAppServerInfoList;

class CAppServerInfoListEx : public CNMSerializable
{
public:
	CAppServerInfoList			aAppServerInfo;

public:
	CAppServerInfoListEx();

	DECLARE_SERIALIZABLE
};

#ifdef _NMSERVER
#define IS_SERVER_LOCALE_KOR		( CNxDBManager::GetInstance().GetServerDBLocale() == 0 )
#define IS_SERVER_LOCALE_CHN		( CNxDBManager::GetInstance().GetServerDBLocale() == 1 )
#define IS_SERVER_LOCALE_TPE		( CNxDBManager::GetInstance().GetServerDBLocale() == 2 )
#define IS_SERVER_LOCALE_JPN		( CNxDBManager::GetInstance().GetServerDBLocale() == 3 )
#define IS_SERVER_LOCALE_USA		( CNxDBManager::GetInstance().GetServerDBLocale() == 4 )
#endif	// #ifdef _NMSERVER

#endif	//	#ifndef __NMDATAOBJECT_H_51C2349E_4DF4_4898_AEBD_E86B4E28CA3F__

#endif