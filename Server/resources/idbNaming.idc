// IDC script for identifying functions and such
#include <idc.idc>

#define RenameFunction(a, b) Message("Found %s at %a\r\n", b, a); MakeName(a, b)
#define START_ADDR 0x00400000

static main() {
    auto addr = BADADDR;
    
    FindStringAndRenameFirstXrefFromData("Delivered", "?Decode@CharacterData@@QAE_KAAVCInPacket@@H@Z");
    FindStringAndRenameFirstXrefFromData("CharacterName(%s), WorldID(%d), Ch(%d)", "CWvsApp::ZtlExceptionHandler");
    FindStringAndRenameFirstXrefFromData("Unknown error 0x%0lX", "com_error::ErrorMessage");
    FindStringAndRenameFirstXrefFromData("%d (MAX)", "CUIToolTip::SetToolTip_Equip");
    FindStringAndRenameFirstXrefFromData("GM can not transfer worlds.", "CCashShop::CheckTransferWorldPossible");
    FindStringAndRenameFirstXrefFromData("Please visit the website to charge your account.", "CCashShop::OnStatusCharge");
    FindStringAndRenameFirstXrefFromData("SOFTWARE\\Microsoft\\Windows\\CurrentVersion", "?Init@CSystemInfo@@QAEXXZ");
    FindStringAndRenameFirstXrefFromData("DBGHELP.DLL", "ZExceptionHandler::InitDbgHelpFunctions");
    FindStringAndRenameFirstXrefFromData("battleFieldTeam", "Field::BattlefieldTeamCond::Parse");
    FindStringAndRenameFirstXrefFromData("jobCategory", "Field::JobCategoryCond::Parse");
    FindStringAndRenameFirstXrefFromData("http://maplestory.nexon.net", "CClientSocket::GetGuestIDRegistrationURL");
    FindStringAndRenameFirstXrefFromData("SeDebugPrivilege", "GetSEPrivilege");
    FindStringAndRenameFirstXrefFromData("Congrats! You have gained upgrade potion by playing an hour!", "CFloatNotice::CreateEffEvolRing");
    FindStringAndRenameFirstXrefFromData("Etc/SetItemInfo.img", "CItemInfo::RegisterSetItemInfo");
    FindStringAndRenameFirstXrefFromData("Can be equipped on #cone-handed sword or two-handed sword.#", "CItemInfo::GetItemDesc");
    FindStringAndRenameFirstXrefFromData("epicItem", "CItemInfo::RegisterEquipItemInfo");
    FindStringAndRenameFirstXrefFromData("%02X%02X%02X%02X%02X%02X_%02X%02X%02X%02X", "CItemInfo::RegisterEquipItemInfo");
    FindStringAndRenameFirstXrefFromData("Please select the World you would like to play in.", "CUIWorldSelect::MakeAdvice");
    FindStringAndRenameFirstXrefFromData("Play!", "StartUpWndProc");
    FindStringAndRenameFirstXrefFromData("%d/%02d/%02d %02d:%02d", "CUIGuildBBS::FormatDate");
    FindStringAndRenameFirstXrefFromData("QuestID : %d", "CUIQuestInfo::LoadData");
    FindStringAndRenameFirstXrefFromData("UI/UIWindow2.img/Reset/AP/stat%d/%d", "GetStatCanvas");
    FindStringAndRenameFirstXrefFromData("[W:%2d]", "CUIStatusBar::ChatLogDraw");
    FindStringAndRenameFirstXrefFromData("itemLEV", "CUIToolTip::CUIToolTip");
    FindStringAndRenameFirstXrefFromData("criticaldamageMin", "CUserLocal::SetPassiveSkillDataForced");
    FindStringAndRenameFirstXrefFromData("Map/MapHelper.img/weather/squib/squib%d", "CUserPreview::ShowFireCrack");
    FindStringAndRenameFirstXrefFromData("R6025 %d %d %d %d", "CVecCtrlMob::CtrlUpdateActiveMove");
    FindStringAndRenameFirstXrefFromData("%s\HShield", "SendHSLog");
    FindStringAndRenameFirstXrefFromData("Something went wrong !!!!!", "CWvsContext::ShowNewYearCard");
    FindStringAndRenameFirstXrefFromData("Are you sure you want to use it?", "CWvsContext::SendConsumeCashItemUseRequest");
    FindStringAndRenameFirstXrefFromData("HACK", "CWvsContext::OnFakeGMNotice");
    FindStringAndRenameFirstXrefFromData("SOFTWARE\\NEXON\\eTracer\\", "?Start_eTracer@@YAXKK@Z");
	FindStringAndRenameFirstXrefFromData("Your account was locked after five invalid password or pin code attempts.", "CLogin::OnAccountInfoResult");
	FindStringAndRenameFirstXrefFromData("///////////////////////////////", "CWvsContext::OnAvatarPackTest");
    FindStringAndRenameFirstXrefFromData("Unlock request Failed", "CWvsContext::SomeFunctionUnk");
    FindStringAndRenameFirstXrefFromData("SOFTWARE\\Microsoft\\Windows\\CurrentVersion", "CSystemInfo::Init");
	FindStringAndRenameFirstXrefFromData("hyper", "?OnResultInstanceTable@CWvsContext@@IAEXAAVCInPacket@@@Z");
	
	FindStringAndRenameFirstXrefFromData("IM_NOT_DOING_BUSINESS_AT_THIS_TIME_DONT_I_DESERVE_SOME_REST_TOO", "?OnPacket@CAdminShopDlg@@SAXJAAVCInPacket@@@Z");

	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingBox2DAttack", "?TryDoingBox2DAttack@CUserLocal@@QAEHJJPAVb2Body@@HK@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingMeleeAttack", "?TryDoingMeleeAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JPAJJKJPAVCGrenade@@JHJJJ_NJK@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingShootAttack", "?TryDoingShootAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JJHJIJJ_N@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingMagicAttack", "?TryDoingMagicAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JJJ_NJJKJ1I@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingBodyAttack", "?TryDoingBodyAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JPAVCMob@@UtagPOINT@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingShootAttackObject","?TryDoingShootObjectAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JPAJJKJPAVCGrenade@@JHJJJ_NJK@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::OnDoActivePsychicArea", "?OnDoActivePsychicArea@CUserLocal@@QAEXAAVCInPacket@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingPsychicLockAttack","?TryDoingPsychicLockAttack@CUserLocal@@QAEXJJJPAV?$list@JV?$allocator@J@std@@@std@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingLightningUnionSubAttack","?TryDoingLightningUnionSubAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JJHJIJJ_N@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingForceAtomAttack","?TryDoingForceAtomAttack@CUserLocal@@QAEHABV?$ZArray@V?$ZRef@VCForceAtom@@@@@@PBUSKILLENTRY@@JK@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingMonsterMagnet","?TryDoingMonsterMagnet@CUserLocal@@IAEHPBUSKILLENTRY@@JJ@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingRandAreaAttack","?TryDoingRandAreaAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JKUtagPOINT@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingForceAtomAttack_NonTarget","?TryDoingForceAtomAttack_NonTarget@CUserLocal@@QAEHAAV?$ZRef@VCForceAtom@@@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingBonusAttack","?TryDoingBonusAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JABV?$ZArray@K@@JABV?$ZArray@J@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingSmoothingMovingShootAttack","?TryDoingSmoothingMovingShootAttack@CUserLocal@@IAEXXZ");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingFlameBall","?TryDoingFlameBall@CUserLocal@@IAEHPBUSKILLENTRY@@JAAUFLAMEBALL_INFO@@V?$ZRef@VCFlameBallObject@@@@UtagPOINT@@W4ECharacterAction@@_N@Z");

    FindStringAndRenameFirstXrefFromData("CSummoned::TryDoingAttack","?TryDoingAttack@CSummoned@@QAEXJJ@Z");
    FindStringAndRenameFirstXrefFromData("CSummoned::TryDoingAttackStorm","?TryDoingAttackStorm@CSummoned@@QAEXJJ@Z");
    FindStringAndRenameFirstXrefFromData("CSummoned::TryDoingAttackManual","?TryDoingAttackManual@CSummoned@@QAEXJH@Z");
    FindStringAndRenameFirstXrefFromData("[DEBUG MESSAGE]SID:%d, RET:%d", "CWvsContext::OnLinkSkillResult");
	
	
	FindStringAndRenameFirstXrefFromData("Success Create File RewardTestGachapon.txt", "CWvsContext::OnGachaponTestResult");
	FindStringAndRenameFirstXrefFromData("Success Create File RewardTestMasterPiece.csv", "CWvsContext::OnMasterPieceTestResult");
	FindStringAndRenameFirstXrefFromData("Success Create File RewardTestRoyalStyle.csv", "CWvsContext::OnRoyalStyleTestResult");
	FindStringAndRenameFirstXrefFromData("Success Create File RewardTestBeautyCoupon.csv", "CWvsContext::OnBeautyCouponTestResult");
	FindStringAndRenameFirstXrefFromData("Success Create File RewardTestLunaCrystal.csv", "CWvsContext::OnLunaCrystalTestResult");
	FindStringAndRenameFirstXrefFromData("Effect/PvPEff.img/CoreUpGrade%d", "CWvsContext::OnBattleStatCoreAck");
	FindStringAndRenameFirstXrefFromData("(DWORD)%u, (LONG)%d", "CWvsContext::OnGetServerTime");
	FindStringAndRenameFirstXrefFromData("[Memo_Load] [BlacklistNotLoading]", "CWvsContext::OnMemoResult");
	FindStringAndRenameFirstXrefFromData("[BlackListLoadDone] [BlackListType:%d] [BlackSize:%d] [sNames:%s]", "CWvsContext::OnBlackList");


	FindStringAndRenameFirstXrefFromData("Effect/DebuffObjEff.img/%s/start", "CField::OnDebuffObjON");
	
	
	
	FindStringAndRenameFirstXrefFromData("DEFAULT : %lld/%lld", "CUserLocal::OnPinkBeanDecrease");
	FindStringAndRenameFirstXrefFromData("The Cash Shop is not available for the Guest ID Users.", "CUserLocal::DisplaySetCashShopError");
	FindStringAndRenameFirstXrefFromData("nStep %d, nGiftID %d", "CUserLocal::OnUnk203_1044");
	FindStringAndRenameFirstXrefFromData("nStep %d, nOldCoin %d, nNewCoin %d", "CUserLocal::OnGuardiansBlessingResult");
	FindStringAndRenameFirstXrefFromData("CHARACTERDATA.LOG FILE Save Done.  Check MapleStory Folder.", "CUserLocal::OnCharacterDataLog");
	FindStringAndRenameFirstXrefFromData("AdminMob", "CUserLocal::OnFamiliarAddResult");
	FindStringAndRenameFirstXrefFromData("UI/UIWindow.img/FloatNotice/%d/DrawOrigin/icon", "CUserLocal::OnEffect");
	FindStringAndRenameFirstXrefFromData("UI/UIWindow2.img/EvolvingEventCheck", "CUserLocal::OpenUI");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingBox2DAttack", "?TryDoingBox2DAttack@CUserLocal@@QAEHJJPAVb2Body@@HK@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingMeleeAttack", "?TryDoingMeleeAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JPAJJKJPAVCGrenade@@JHJJJ_NJK@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingShootAttack", "?TryDoingShootAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JJHJIJJ_N@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingMagicAttack", "?TryDoingMagicAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JJJ_NJJKJ1I@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingBodyAttack", "?TryDoingBodyAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JPAVCMob@@UtagPOINT@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingShootAttackObject", "?TryDoingShootObjectAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JPAJJKJPAVCGrenade@@JHJJJ_NJK@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::OnDoActivePsychicArea", "?OnDoActivePsychicArea@CUserLocal@@QAEXAAVCInPacket@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingPsychicLockAttack", "?TryDoingPsychicLockAttack@CUserLocal@@QAEXJJJPAV?$list@JV?$allocator@J@std@@@std@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingForceAtomAttack", "?TryDoingForceAtomAttack@CUserLocal@@QAEHABV?$ZArray@V?$ZRef@VCForceAtom@@@@@@PBUSKILLENTRY@@JK@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingMonsterMagnet", "?TryDoingMonsterMagnet@CUserLocal@@IAEHPBUSKILLENTRY@@JJ@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingRandAreaAttack", "?TryDoingRandAreaAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JKUtagPOINT@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingForceAtomAttack_NonTarget", "?TryDoingForceAtomAttack_NonTarget@CUserLocal@@QAEHAAV?$ZRef@VCForceAtom@@@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingBonusAttack", "?TryDoingBonusAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JABV?$ZArray@K@@JABV?$ZArray@J@@@Z");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingSmoothingMovingShootAttack", "?TryDoingSmoothingMovingShootAttack@CUserLocal@@IAEXXZ");
	FindStringAndRenameFirstXrefFromData("CUserLocal::TryDoingFlameBall", "?TryDoingFlameBall@CUserLocal@@IAEHPBUSKILLENTRY@@JAAUFLAMEBALL_INFO@@V?$ZRef@VCFlameBallObject@@@@UtagPOINT@@W4ECharacterAction@@_N@Z");


	RenameFirstXrefFromData(FindStringAndRenameFirstXrefFromData("UI/UIWindow2.img/Shop2/TabBuy/disabled", "?SetShopDlg@CShopDlg@@QAEXAAVCInPacket@@@Z"), "?OnPacket@CShopDlg@@SAXJAAVCInPacket@@@Z"); // CShopDlg::SetShopDlg
	// (see line above, which combines this + // CShopDlg::SetShopDlg) FirstRef(SetShopDlg, "?OnPacket@CShopDlg@@SAXJAAVCInPacket@@@Z"); // CShopDlg::OnPacket
	
    // CRC stuff
    addr = FindBinary(START_ADDR, SEARCH_DOWN, "00 00 00 00 B7 1D C1 04  6E 3B 82 09 D9 26 43 0D");
    if (addr != BADADDR) {
        RenameFunction(addr, "?ms_adwCrc32Table@CCrc32@@0PAKA");
        RenameFirstXrefFromData(addr, "?GetCrc32@CCrc32@@SAKPBEKKHPAKH@Z");
    }
    
   // FindCInPacketDecodeFunction("83 F8 01", "?Decode1@CInPacket@@QAEEXZ");
   // FindCInPacketDecodeFunction("83 F8 02", "?Decode2@CInPacket@@QAEEXZ");
   // FindCInPacketDecodeFunction("83 F8 04", "?Decode4@CInPacket@@QAEEXZ");
   // FindCInPacketDecodeFunction("83 F8 08", "?Decode8@CInPacket@@QAEEXZ");
  // FindCInPacketDecodeFunction("3B C7", "?DecodeBuffer@CInPacket@@QAEEXZ");
    
  //  FindCOutPacketEncodeFunction("41 3B C8", "?Encode1@COutPacket@@QAEEXZ");
  //  FindCOutPacketEncodeFunction("83 C1 02", "?Encode2@COutPacket@@QAEEXZ");
  //  FindCOutPacketEncodeFunction("83 C1 04", "?Encode4@COutPacket@@QAEEXZ");
  //  FindCOutPacketEncodeFunction("83 C1 08", "?Encode8@COutPacket@@QAEEXZ");
  //  FindCOutPacketEncodeFunction("03 CB", "?EncodeBuffer@COutPacket@@QAEEXZ");

    
    FindAoBAndRename("83 FA 02 73 17 68", "CIOBufferManipulator::DecodeStr");
    FindAoBAndRename("6A 0D 50 8B CF E8 ? ? ? FF 8B CF 85 DB 74 1C", "GW_CharacterStat::Decode");
    FindAoBAndRename("85 ? 74 ? 68 00 00 10 00 56 6a ff 50 6a 00 6a 00 ff ? ? ? ? ? 56 8d ? ? ? ? ? e8 ? ? ? ?", "CClientPacket::OnLoginInfo");
	FindAoBAndRename("E8 ? ? ? ? 84 ? 74 ? 81 ? D5 00 00 00 74 ? 8D ? ?", "CUserLocal::OpenUI");
	FindAoBAndRename("3D 80 EE 36 00 7E ? 6A 00 6A 01 6A 00", "CBattleRecordMan::OnSomeSubPacket");
	FindAoBAndRename("83 ?? 66 74 ? 83 ? 69", "CAuction::OnSomeAuctionType");
	FindAoBAndRename("55 8B EC 8B 45 08 05 ? ? ? ? 83 F8 22 0F 87 ? ? ? ?", "CCashShop::OnPacket");
	FindAoBAndRename("55 8B EC 8B 55 08 8B C2 2D ? ? ? ? 74 32", "CAffectedAreaPool::OnPacket");
	FindAoBAndRename("55 8B EC 8B 45 08 2D ? ? ? ? 74 14 83 E8 01 75 1A FF 75 0C", "CAuction::OnPacket");
	FindAoBAndRename("55 8B EC 8B 45 08 2D ? ? ? ? 74 11 83 E8 02", "CDropPool::OnPacket");
	
	
	
	addr = FindAoBAndRename("83 7D 10 01", "CInPacket::Decode1");
    RenameFirstXrefFromCode(addr, "?Decode1@CInPacket@@QAEEXZ");
	
	addr = FindAoBAndRename("83 7D 10 02", "CInPacket::Decode2");
    RenameFirstXrefFromCode(addr, "?Decode2@CInPacket@@QAEGXZ");
	
	addr = FindAoBAndRename("83 7D 10 04 72", "CInPacket::Decode4");
    RenameFirstXrefFromCode(addr, "?Decode4@CInPacket@@QAEKXZ");
	
	addr = FindAoBAndRename("83 7D 10 08", "CInPacket::Decode8");
    RenameFirstXrefFromCode(addr, "?Decode8@CInPacket@@QAE_JXZ_0");
	
	addr = FindAoBAndRename("55 8B EC 8B 4D 10 83 EC 08 83 F9 02 72 22 8B 55 0C", "CInPacket::DecodeStr");
    RenameFirstXrefFromCode(addr, "?DecodeStr@CInPacket@@QAE?AV?$ZXString@D@@XZ");
	
	addr = FindAoBAndRename("E8 ? ? ? ? 83 27 1F", "CInPacket::DecodeBuffer");
    RenameFirstXrefFromCode(addr, "?DecodeBuffer@CInPacket@@QAE?AV?$ZXString@D@@XZ");
	
	
	
	//  CField::OnPacket  ---------------------------------------------------------------------------------------------------
	
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 81 EC ? ? ? ? A1 ? ? ? ? 33 C5 89 45 F0 53 56 57 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 89 7D DC 8B 5D 08", "?OnFieldEffect@CField@@IAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 10 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 4D 08 E8 ? ? ? ? 0F B6 D8", "?OnDesc@CField@@IAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 83 EC 10 53 57 8B 7D 08 8B CF", "?OnSetQuestTime@CField@@IAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 81 EC ? ? ? ? A1 ? ? ? ? 33 C5 89 45 F0 53 56 57 50 8D 45 F4 64 A3 ? ? ? ? 8B D9 8B 75 08 8D 8D ? ? ? ? E8 ? ? ? ?", "?OnFootHoldMove@CField@@IAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 20 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 75 08 8B CE E8 ? ? ? ? 8B CE 89 45 F0", "?OnSmartMobNotice@CField@@IAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 81 EC ? ? ? ? A1 ? ? ? ? 33 C5 89 45 F0 56 57 50 8D 45 F4 64 A3 ? ? ? ? 89 8D ? ? ? ? 8B 7D 08 C7 85 ? ? ? ? ? ? ? ?", "CField::OnCreateForceAtom");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 56 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 83 BE ? ? ? ? ? 0F 85 ? ? ? ? 68 ? ? ? ? B9 ? ? ? ? E8 ? ? ? ? "
                               "89 45 F0 C7 45 ? ? ? ? ? 85 C0 74 0B 8B C8 E8 ? ? ? ? 8B C8 EB 02 33 C9 8D 41 08 C7 45 ? ? ? ? ? F7 D9 1B C9 23 C8 74 0B 83 C1 F8 74 08 F0 FF 41 0C EB 02 33 C9 8B 86 ? "
                               "? ? ? 89 8E ? ? ? ? 89 45 F0 85 C0 74 0A 6A 00 8D 4D EC E8 ? ? ? ? 8B 8E ? ? ? ? 6A 01", "?OnSetAchieveRate@CField@@AAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 53 8B 5D 08 8B CB 56 E8 ? ? ? ?", "?OnFootHoldOnOff@CField@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 83 EC 14 8B 4D 08 8D 45 EC", "Unk585");
	FindAoBAndRename("73 0A 68 ? ? ? ? E8 ? ? ? ? FF 47 04 89 53 04 8B 42 04 89 10 E9 ? ? ? ?", "CField::OnAddWreckage");
	FindAoBAndRename("55 8B EC 83 EC 10 53 56 8B 75 08 89 4D F0 8B CE 57 E8 ? ? ? ? 8B CE 89 45 F4 E8 ? ? ? ? 8B CE", "?OnCreateMirrorImage@CField@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 56 57 8B F9 8B 4D 08 E8 ? ? ? ? 8B CF 8B F0", "?OnFuntionFootholdMan@CField@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8D 45 F0 68 ? ? ? ? 50 E8 ? ? ? ? 8B C8 E8 ? ? ? ? 6A 00", "CField::OnFunctionTempBlock");
	
	FindStringAndRenameFirstXrefFromData("Effect/BasicEff.img/ObtacleAtomCreate/%d", "CField::OnCreateObstacle");
	FindStringAndRenameFirstXrefFromData("Effect/DebuffObjEff.img/%s/start", "CField::OnDebuffObjON");
	FindStringAndRenameFirstXrefFromData("chaserEndTime", "CField::OnChaserMove");
	
	
	
	// StringPools
	
	
	
	FindAoBAndRename("E8 ? ? ? ? 6A 04 B9 ? ? ? ?", "StringPool::GetString");
	FindAoBAndRename("E8 ? ? ? ? 8D 7E 60", "StringPool::GetStringW");
	FindAoBAndRename("64 ? ? ? ? ? 8b ? ? ? ? ? 6a 01 68 ? ? ? ? ff ? 85 ?", "StringPool::GetInstance");
	FindAoBAndRename("6a 04 b9 ? ? ? ? e8 ? ? ? ? 8b f8 89 7d e4", "StringPool::GetBSTR");
	
	
	// Excl Request
	
	FindAoBAndRename("6A 00 E8 ?? ?? F6 FF C2 04 00", "CWvsContext::SetExclRequestSent");
	
	//  CUserLocal::OnPacket  -----------------------------------------------------------------------------------------------

	FindAoBAndRename("E8 ? ? ? ? 83 C4 0C 8B CE E8 ? ? ? ? 0F B6 C0", "CUserLocal::OnFamiliarAddResult");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 20 A1 ? ? ? ? 33 C5 89 45 F0 53 56 57 50 8D 45 F4 64 A3 ? ? ? ? 8B 75 08 8B CE", "CUserLocal::OnPush");
	FindAoBAndRename("3D 1C CA 31 01 74 ? 3D 1B CA 31 01 74 ?", "CUserLocal::OnIncLarknessResponse");
	FindAoBAndRename("E8 ? ? ? ? 8D ? 18 6B AF FF", "CUserLocal::OnRandomEmotion");
	FindAoBAndRename("6a 00 6a 00 6a 00 6a 00 6a ff 6a 1d", "CWvsContext::OnEnterField");
	FindAoBAndRename("E8 ? ? ? ? EB 33 85 D2", "CLegion::Decode");
	FindAoBAndRename("57 E8 ? ? ? ? 83 C4 08 8B CF", "?RawDecode@GW_ItemSlotEquip@@MAEXAAVCInPacket@@@Z");
	FindAoBAndRename("64 ? ? ? ? ? 8b ? ? ? ? ? 6a 01 68 ? ? ? ? ff ? 85 ?", "StringPool::GetInstance");
	FindAoBAndRename("83 f9 64 72 ? 3d f8 2a 00 00 74 ? 33 c9 eb ?", "CFuncKeyMappedMan::OnInit");
	FindAoBAndRename("83 FF 13 74 ? 83 FF 4B 74 ? 83 FF 6C", "?OnMovePacket@CMovePath@@QAEXAAVCInPacket@@HJ@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 51 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 75 08 8B CE E8 ? ? ? ? 8B CE", "CUserLocal::OnZeroLevelUpAlarm");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 14 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 89 4D F0 8B 4D 08", "CUserLocal::OnIsUniverse");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 24 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B 4D 08 E8 ? ? ? ? 33 F6", "CUserLocal::OnGetNpcCurrentAction");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 18 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B 7D 08 8B CF E8 ? ? ? ? 8D 4D EC", "?OnOpenBattlePvPChampSelectUI@CUserLocal@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 83 EC 08 53 8B D9 56 57 83 BB ? ? ? ? ? 0F 85 ? ? ? ?", "?OnFieldScoreUpdate@CUserLocal@@AAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 83 EC 08 53 8B 5D 08 8B CB", "?OnSkillCooltimeSet@CUserLocal@@IAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 38 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 87 ? ? ? ? 8D 8F ? ? ? ?", "CUserLocal::OnUserExtraAttackRequest");
	
	
	
	
	
	
	// CMobPool::OnPacket  -------------------------------------------------------------------------------------------------

	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 20 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B D9 8B 75 08 8B CE", "CMobPool::OnMobEnterField");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 18 56 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 8B 4D 08 E8 ? ? ? ? 89 86 ? ? ? ?", "CMobPool::OnCrcKeyChanged");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 58 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 89 7D EC 8B 5D 08", "CMob::OnMove");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 18 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 5D 08 8B CB E8 ? ? ? ? 8B CB 89 45 EC", "CMob::OnEscortStopSay");
	FindAoBAndRename("55 8B EC 83 EC 10 53 8B D9 89 5D FC E8 ? ? ? ?", "CMob::OnMobTeleportRequest");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 28 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 33 DB 33 FF", "?OnAttackPriority@CMob@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 44 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B D9 89 5D EC 8B 75 08 8B CE", "?OnCastingBarSkill@CMob@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 75 08 8B CE E8 ? ? ? ? 8B CE 8B D8", "?OnLaserControl@CMob@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 56 8B F1 8B 4D 08 E8 ? ? ? ? 39 86 ? ? ? ? 74 61", "?OnScale@CMob@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 56 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 8B 4D 08 E8 ? ? ? ? 84 C0 74 60", "?ForceChase@CMob@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 83 EC 08 53 8B 5D 08 57 8B F9 8B CB E8 ? ? ? ? 89 45 F8", "CMob::OnMobBurnedInfoRemove");
	
	
	FindStringAndRenameFirstXrefFromData("DemianSword%d", "?OnDemianDelayedAttackCreate@CMob@@QAEXAAVCInPacket@@@Z");
	
	
	//  CWvsContext::OnPacket  ----------------------------------------------------------------------------------------------

	FindAoBAndRename("6a 00 6a 00 6a 00 6a 00 6a ff 6a 1d", "CWvsContext::OnEnterField");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 A1 ? ? ? ? 33 C5 89 45 F0 53 56 57 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 5D 08", "?OnWebBoardAuthkeyUpdate@CWvsContext@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 89 4D EC 8B 7D 08 8D 45 F0", "?OnWeatherEffectNotice@CWvsContext@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 51 53 56 8B 75 08 8B D9 57 8B CE E8 ? ? ? ? 0F B7 F8", "?OnUpdateUIEventListInfo@CWvsContext@@QAEXAAVCInPacket@@@Z");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 56 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 8D 45 EC", "CWvsContext::OnDressUpInfoModified");
	FindAoBAndRename("55 8B EC 83 EC 08 53 56 8B D9 8B 4D 08 57 6A 08", "CWvsContext::OnUpdateMapleTVShowTime");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B D9 8B 75 08 8B CE E8 ? ? ? ? 8B F8", "CWvsContext::OnSetMazeArea");
	FindAoBAndRename("55 8B EC 83 EC 1C A1 ? ? ? ? 33 C5 89 45 FC 53 56 8B 75 08 57", "CWvsContext::OnInappropriateBanMsg");
	FindAoBAndRename("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 0C 56 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 83 BE ? ? ? ? ? 75 58", "CWvsContext::OnUnionAssignResult");
	
	RenameFirstXrefFromCode(FindStringAndRenameFirstXrefFromData("///////////////////////////////", "CWvsContext::OnAvatarPackTest"), "CWvsContext::OnPacket");
	
	
	FindStringAndRenameFirstXrefFromData("%s : %s Create MiniRoom", "CUser::OnMiniRoomBalloon");
	FindStringAndRenameFirstXrefFromData("%s : %s Create MiniRoom", "CUser::OnMiniRoomBalloon");
	
	

    FindAoBAndRename("81 FB B4 05 00 00", "CInPacket::DecryptData");
    FindAoBAndRename("81 FF B4 05 00 00", "COutPacket::MakeBufferList");

    FindAoBAndRename("E8 ? ? ? ? 8D 4B 39", "GW_ItemSlotEquip::RawEncode");
    FindAoBAndRename("55 8B EC 53 56 57 FF 75 0C 8B 7D 08 8B D9 57 E8 ? ? ? ? FF 73 35", "GW_ItemSlotBundle::RawEncode");
    FindAoBAndRename("55 8B EC 51 53 56 57 FF 75 0C 8B 7D 08 8B D9 57 89 5D FC E8 ? ? ? ? 6A 0D", "GW_ItemSlotPet::RawEncode");


	// UserLocal Stuff
	FindAoBAndRename("68 3B 24 D8 17","?OnGiantShadowSpearAttack@CUserLocal@@QAEXAAVCInPacket@@@Z");


	// Summon Stuff
    FindAoBAndRename("68 4B 24 14 00","?OnBeholderRevengeAttack@CSummoned@@QAEXAAVCInPacket@@@Z");
	
	
	
	
	//   Miscellaneous  ------------------------------------------------------------------------------------------------------
	
	
	FindStringAndRenameFirstXrefFromData("Delivered", "?Decode@CharacterData@@QAE_KAAVCInPacket@@H@Z");
	FindStringAndRenameFirstXrefFromData("SOFTWARE\\Microsoft\\Windows\\CurrentVersion", "?Init@CSystemInfo@@QAEXXZ");
	FindStringAndRenameFirstXrefFromData("DBGHELP.DLL", "ZExceptionHandler::InitDbgHelpFunctions");
	FindStringAndRenameFirstXrefFromData("battleFieldTeam", "Field::BattlefieldTeamCond::Parse");
	FindStringAndRenameFirstXrefFromData("jobCategory", "Field::JobCategoryCond::Parse");
	FindStringAndRenameFirstXrefFromData("CharacterName(%s), WorldID(%d), Ch(%d)", "CWvsApp::ZtlExceptionHandler");
	FindStringAndRenameFirstXrefFromData("Unknown error 0x%0lX", "com_error::ErrorMessage");
	FindStringAndRenameFirstXrefFromData("%d (MAX)", "CUIToolTip::SetToolTip_Equip");
	FindStringAndRenameFirstXrefFromData("http://maplestory.nexon.net", "CClientSocket::GetGuestIDRegistrationURL");
	FindStringAndRenameFirstXrefFromData("SeDebugPrivilege", "GetSEPrivilege");
	FindStringAndRenameFirstXrefFromData("Congrats! You have gained upgrade potion by playing an hour!", "CFloatNotice::CreateEffEvolRing");
	FindStringAndRenameFirstXrefFromData("Etc/SetItemInfo.img", "CItemInfo::RegisterSetItemInfo");
	FindStringAndRenameFirstXrefFromData("Can be equipped on #cone-handed sword or two-handed sword.#", "CItemInfo::GetItemDesc");
	FindStringAndRenameFirstXrefFromData("epicItem", "CItemInfo::RegisterEquipItemInfo");
	FindStringAndRenameFirstXrefFromData("%02X%02X%02X%02X%02X%02X_%02X%02X%02X%02X", "CItemInfo::RegisterEquipItemInfo");
	FindStringAndRenameFirstXrefFromData("Please select the World you would like to play in.", "CUIWorldSelect::MakeAdvice");
	FindStringAndRenameFirstXrefFromData("Play!", "StartUpWndProc");
	FindStringAndRenameFirstXrefFromData("%d/%02d/%02d %02d:%02d", "CUIGuildBBS::FormatDate");
	FindStringAndRenameFirstXrefFromData("QuestID : %d", "CUIQuestInfo::LoadData");
	FindStringAndRenameFirstXrefFromData("UI/UIWindow2.img/Reset/AP/stat%d/%d", "GetStatCanvas");
	FindStringAndRenameFirstXrefFromData("[W:%2d]", "CUIStatusBar::ChatLogDraw");
	FindStringAndRenameFirstXrefFromData("itemLEV", "CUIToolTip::CUIToolTip");
	FindStringAndRenameFirstXrefFromData("Map/MapHelper.img/weather/squib/squib%d", "CUserPreview::ShowFireCrack");
	FindStringAndRenameFirstXrefFromData("R6025 %d %d %d %d", "CVecCtrlMob::CtrlUpdateActiveMove");
	FindStringAndRenameFirstXrefFromData("%s\HShield", "SendHSLog");
	FindStringAndRenameFirstXrefFromData("SOFTWARE\\NEXON\\eTracer\\", "?Start_eTracer@@YAXKK@Z");
	FindStringAndRenameFirstXrefFromData("Answer %u %u %u", "CUIHandler::OnDisguise");
	FindStringAndRenameFirstXrefFromData("ZMemoryStream::SetPosition(): negative seek", "?SetPosition@ZMemoryStream@@UAEXHI@Z");
	
	
	



    auto names = object();
    names[0] = "?RawDecode@GW_ItemSlotPet@@MAEXAAVCInPacket@@@Z";
    names[1] = "?RawDecode@GW_ItemSlotEquip@@MAEXAAVCInPacket@@@Z";
    names[2] = "?Decode@GW_CharacterStat@@QAEXAAVCInPacket@@H@Z";
    
    //FindFunctionsByAoB("0F B7 C8 8D 56 ? E8 ? ? ? FF 8B CF 89 46 ?", names, 3);
    
    
    addr = FindAoBAndRename("68 FF FF FF 7F   8B CE C7 44 24 1C 00 00 00 00   E8 ? ? FF FF", "COutPacket::COutPacket");
    if (addr != BADADDR) {
        // Quickly refind
        addr = FindBinary(addr, SEARCH_DOWN, "68 FF FF FF 7F   8B CE C7 44 24 1C 00 00 00 00   E8 ? ? FF FF");
        Message(" %a\r\n", addr);
        addr = GetCallAddress(addr); // address of COutPacket::Init_1
        Message(" %a\r\n", addr);
        //RenameFunction(addr, "COutPacket::Init_1");
        addr = GetCallAddress(addr); // First call = COutPacket__Init
        Message(" %a\r\n", addr);
        //RenameFunction(addr, "COutPacket::Init");
        /*
        auto initByte = FindBinary(addr, SEARCH_DOWN, "E8");
        auto encode2 = FindBinary(initByte + 2, SEARCH_DOWN | SEARCH_NEXT, "E8");
        if (initByte != BADADDR) RenameFunction(initByte, "COutPacket::InitByte");
        if (encode2 != BADADDR) RenameFunction(initByte, "COutPacket::Encode2");
        */
    }
    
    FindAoBAndRename("68 FF FF FF 7F 8B CE C7 44 24 1C 00 00 00 00 E8 ? ? FF FF", "COutPacket::COutPacket");
    
    // Packet handlers
    
    FindAoBAndRename("68 0A 00 00 22", "CClientSocket::OnCheckCrcResult");
    addr = FindAoBAndRename("68 04 00 00 21", "CClientSocket::OnAuthenCodeChanged");
    RenameFirstXrefFromCode(addr, "CClientSocket::ProcessPacket");
    FindAoBAndRename("68 04 00 00 21", "CClientSocket::OnAuthenCodeChanged");
    RenameFirstXrefFromCode(FindStringAndRenameFirstXrefFromData("\\ It may be wating long time", "CField::OnShowQuestLoadingPacket"), "CField::OnPacket");
}

static FindNextCommand(pCommandHex, pFrom) {
    return FindBinary(pFrom, SEARCH_DOWN, pCommandHex);
}

static GetCallAddress(pFrom) {
    auto addr = FindNextCommand("E8", pFrom);
    if (addr == BADADDR) return BADADDR;
    Message(" %a\r\n", addr);
    return pFrom + Dword(addr + 1);
}

static FindFirstText(pWhat, pMin) {
    auto result = FindText(pMin, SEARCH_DOWN, 0, 0, pWhat);
    if (result == BADADDR)
        return BADADDR;
    return Rfirst(result);
}

static FindAoBAndRename(AoB, name) {
    auto addr = FindBinary(START_ADDR, SEARCH_DOWN, AoB);
    if (addr == BADADDR)
        return BADADDR;
    
    auto functionBase = GetFunctionAttr(addr, FUNCATTR_START);
    RenameFunction(functionBase, name);
    
    return functionBase;
}

static FindStringAndRenameFirstXrefFromData(text, name) {
    return FindStringAndRenameXrefSteps(text, name, 1);
}

static FindString(addr, text) {
    return FindBinary(addr, SEARCH_DOWN, sprintf("\"%s\"", text));
}

static FindStringAndRenameXrefSteps(text, name, steps) {
    auto addr = FindString(START_ADDR, text);
    if (addr == BADADDR)
        return BADADDR;
    
    addr = DfirstB(addr);
    if (addr == BADADDR) {
        return BADADDR;
    }
    auto i;
    for (i = 1; i < steps; i++) {
        addr = GetFunctionAttr(addr, FUNCATTR_START);
        
        auto callingFunction = RfirstB(addr); // Get xref to function
        if (callingFunction == BADADDR)
            return BADADDR;

        addr = callingFunction;
    }
    
    addr = GetFunctionAttr(addr, FUNCATTR_START);
    if (addr != BADADDR) {
        RenameFunction(addr, name);
    }
    return addr;
}

static RenameFirstXrefFromData(address, name) {
    auto callingFunction = DfirstB(address);
    if (callingFunction == BADADDR)
        return BADADDR;
    
    
    auto functionBase = GetFunctionAttr(callingFunction, FUNCATTR_START);
    RenameFunction(functionBase, name);
    return functionBase;
}

static RenameFirstXrefFromCode(address, name) {
    auto callingFunction = RfirstB(address);
    if (callingFunction == BADADDR)
        return BADADDR;
    
    
    auto functionBase = GetFunctionAttr(callingFunction, FUNCATTR_START);
    RenameFunction(functionBase, name);
    return functionBase;
}

static FindCInPacketDecodeFunction(identifier, name) {
    auto lastResult = 0x00400000;
    while (1) {
        lastResult = FindBinary(lastResult, SEARCH_DOWN, "8D 45 EC 50 C7 45 EC 26 00 00 00");
        if (lastResult == BADADDR)
            return;
        
        
        auto tmp = FindBinary(lastResult, 0x00, identifier);
        
        if (tmp != BADADDR && lastResult - tmp < 0x15) {
            auto functionBase = GetFunctionAttr(lastResult, FUNCATTR_START);
            
            Message("Found %s at %a\r\n", name, functionBase);
                
            RenameFunction(functionBase, name);
            return;
        }
        lastResult = lastResult + 0x50;
    }
}

static FindCOutPacketEncodeFunction(identifier, name) {
    auto lastResult = 0x00400000;
    while (1) {
        lastResult = FindBinary(lastResult, SEARCH_DOWN, "8B F1 8B 46 04 57 8D 7E  04");
        if (lastResult == BADADDR)
            return;
        
        
        auto tmp = FindBinary(lastResult, SEARCH_DOWN, identifier);
        
        if (tmp != BADADDR && tmp - lastResult < 0x15) {
            auto functionBase = GetFunctionAttr(lastResult, FUNCATTR_START);
            
            Message("Found %s at %a\r\n", name, functionBase);
                
            RenameFunction(functionBase, name);
            return;
        }
        lastResult = lastResult + 0x50;
    }
}

static FindFunctionsByAoB(AoB, Names, Amount) {
    auto lastResult = 0x00400000;
    auto lastFunctionResult = -1;
    auto i = 0;
    for (i; i < Amount; i = i) {
        lastResult = FindBinary(lastResult, SEARCH_DOWN, AoB);
        if (lastResult == BADADDR)
            return;
        
        auto functionBase = GetFunctionAttr(lastResult, FUNCATTR_START);
        
        if (functionBase != lastFunctionResult) {
            Message("Found %s at %a\r\n", Names[i], functionBase);
            
            RenameFunction(functionBase, Names[i]);
            
            lastResult = functionBase;
            lastFunctionResult = functionBase;
            i++;
        }
        lastResult = lastResult + 0x50;
    }
}  