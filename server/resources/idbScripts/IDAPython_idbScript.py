from idautils import *
from idaapi import *
from idc import *
from struct import *
from re import *
from swordie_ida_plugin import *
import os


print "=====  Starting IDAPython idb Script   ====="
nSel = AskYN(0, "Running Swordie IDAPython Naming Script\n"
                "Create/Overwrite a text file (" + txtFileName + ") in " + path +
                " containing OutHeaders with their op value? \n"
                "[Note] The values are not guaranteed to be correct.")
  # nSel = False





#  Essentials  ---------------------------------------------------------------------------------------------------------

    #  COutPacket::COutPacket
coutpacket_ea = get_called_addr(find_first_addr_by_aob("E8 ? ? ? ? 8A 5D 0C"))
rename_func(coutpacket_ea, "COutPacket::COutPacket")


    #  StringPool::GetStrings
getString_EA =      0x82D730
getStringW_EA =     0x86B1D0
getStringBSTR_EA =  0xC656A0


    #  Decodes
decode1_EA = find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 18 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 89 65 F0 8B F9 89 7D E8 8B 57 18 8B 77 08 8B 47 0C 2B C2", "CInPacket::Decode1")
decode2_EA = find_first_aob_and_rename_func("C7 45 FC 00 00 00 00 83 F8 02 73 43 ? ? ? ? ? 8D 45 D8 C7 45 D8 26 00 00 00", "CInPacket::Decode2")
decode4_EA = find_first_aob_and_rename_func("C7 45 FC 00 00 00 00 83 F8 04 73 43 ? ? ? ? ? 8D 45 D8 C7 45 D8 26 00 00 00", "CInPacket::Decode4")
decode8_EA = find_first_aob_and_rename_func("C7 45 FC 00 00 00 00 83 F8 08 73 43 ? ? ? ? ? 8D 45 D8 C7 45 D8 26 00 00 00", "CInPacket::Decode8")
decodeString_EA = find_first_aob_and_rename_func("C7 45 D4 26 00 00 00 50 E8", "CInPacket::DecodeString")
decodeBuffer_EA = get_called_addr(find_first_addr_by_aob("E8 ? ? ? ? 83 27 1F"))
rename_func(decodeBuffer_EA, "?DecodeBuffer@CInPacket@@QAEXPAXI@Z")


    #  StringPools
getString_EA = get_first_xref_and_rename_func(get_called_addr(find_first_addr_by_aob("E8 ? ? ? ? 6A 04 B9 ? ? ? ?")), "StringPool::GetString")

getStringW_EA = get_called_addr(find_first_addr_by_aob("E8 ? ? ? ? 8D 7E 60"))
rename_func(getStringW_EA, "StringPool::GetStringW")

find_first_aob_and_rename_func("64 ? ? ? ? ? 8b ? ? ? ? ? 6a 01 68 ? ? ? ? ff ? 85 ?", "StringPool::GetInstance")
getStringBSTR_EA = find_first_aob_and_rename_func("6a 04 b9 ? ? ? ? e8 ? ? ? ? 8b f8 89 7d e4", "StringPool::GetBSTR")


    #  Others
CWvsContext_SetExclRequestSent_EA = get_called_addr(find_first_addr_by_aob("E8 ? ? ? ? 4E 83 FE 10"))
rename_func(CWvsContext_SetExclRequestSent_EA, "CWvsContext::SetExclRequestSent")

#  Essentials  ---------------------------------------------------------------------------------------------------------




















#  CField::OnPacket  ---------------------------------------------------------------------------------------------------

find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 81 EC ? ? ? ? A1 ? ? ? ? 33 C5 89 45 F0 53 56 57 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 89 7D DC 8B 5D 08", "?OnFieldEffect@CField@@IAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 10 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 4D 08 E8 ? ? ? ? 0F B6 D8", "?OnDesc@CField@@IAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 83 EC 10 53 57 8B 7D 08 8B CF", "?OnSetQuestTime@CField@@IAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 81 EC ? ? ? ? A1 ? ? ? ? 33 C5 89 45 F0 53 56 57 50 8D 45 F4 64 A3 ? ? ? ? 8B D9 8B 75 08 8D 8D ? ? ? ? E8 ? ? ? ?", "?OnFootHoldMove@CField@@IAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 20 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 75 08 8B CE E8 ? ? ? ? 8B CE 89 45 F0", "?OnSmartMobNotice@CField@@IAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 81 EC ? ? ? ? A1 ? ? ? ? 33 C5 89 45 F0 56 57 50 8D 45 F4 64 A3 ? ? ? ? 89 8D ? ? ? ? 8B 7D 08 C7 85 ? ? ? ? ? ? ? ?", "CField::OnCreateForceAtom")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 56 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 83 BE ? ? ? ? ? 0F 85 ? ? ? ? 68 ? ? ? ? B9 ? ? ? ? E8 ? ? ? ? "
                               "89 45 F0 C7 45 ? ? ? ? ? 85 C0 74 0B 8B C8 E8 ? ? ? ? 8B C8 EB 02 33 C9 8D 41 08 C7 45 ? ? ? ? ? F7 D9 1B C9 23 C8 74 0B 83 C1 F8 74 08 F0 FF 41 0C EB 02 33 C9 8B 86 ? "
                               "? ? ? 89 8E ? ? ? ? 89 45 F0 85 C0 74 0A 6A 00 8D 4D EC E8 ? ? ? ? 8B 8E ? ? ? ? 6A 01", "?OnSetAchieveRate@CField@@AAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 53 8B 5D 08 8B CB 56 E8 ? ? ? ?", "?OnFootHoldOnOff@CField@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 83 EC 14 8B 4D 08 8D 45 EC", "Unk585")
find_first_aob_and_rename_func("73 0A 68 ? ? ? ? E8 ? ? ? ? FF 47 04 89 53 04 8B 42 04 89 10 E9 ? ? ? ?", "CField::OnAddWreckage")
find_first_aob_and_rename_func("55 8B EC 83 EC 10 53 56 8B 75 08 89 4D F0 8B CE 57 E8 ? ? ? ? 8B CE 89 45 F4 E8 ? ? ? ? 8B CE", "?OnCreateMirrorImage@CField@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 56 57 8B F9 8B 4D 08 E8 ? ? ? ? 8B CF 8B F0", "?OnFuntionFootholdMan@CField@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8D 45 F0 68 ? ? ? ? 50 E8 ? ? ? ? 8B C8 E8 ? ? ? ? 6A 00", "CField::OnFunctionTempBlock")


find_first_str_and_rename_first_xref_func("Effect/BasicEff.img/ObtacleAtomCreate/%d", "CField::OnCreateObstacle")
find_first_str_and_rename_first_xref_func("Effect/DebuffObjEff.img/%s/start", "CField::OnDebuffObjON")
find_first_str_and_rename_first_xref_func("chaserEndTime", "CField::OnChaserMove")


sp_str_list = ["THE_PORTAL_IS_CLOSED_FOR_NOW",
          "THIS_MAP_CAN_ONLY_BE_ENTERED_BY_PARTY_MEMBERS",
          "ONLY_THE_PART_LEADER_CAN_MAKE_THE_REQUEST_TO_ENTER",
          "PLEASE_ENTER_ONCE_YOUR_ENTIRE_PARTY_HAS_GATHERED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CField::OnTransferFieldReqIgnored")

sp_str_list = ["YOUR_ACCOUNT_HAS_BEEN_INACTIVE_FOR_A_WHILE_R_NSO_THE_CASH_SHOP_HAS_BEEN_LOCKED_R",
          "YOU_CANNOT_GO_INTO_THE_CASH_SHOP_PLEASE_TRY_AGAIN_LATER",
          "YOU_CANNOT_ACCESS_THE_AUCTION_HOUSE_AT_THIS_TIME_PLEASE_TRY_AGAIN_LATER",
          "THE_ITEM_TRADING_SHOP_IS_CURRENTLY_UNAVAILABLE_PLEASE_TRY_AGAIN_LATER",
          "YOU_DO_NOT_MEET_THE_MINIMUM_LEVEL_REQUIREMENT_TO_ACCESS_THE_TRADE_SHOP"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnTransferChannelReqIgnored@CField@@IAEXAAVCInPacket@@@Z")

sp_str_list = ["YOU_CANNOT_JOIN_BATTLE_MODE_AT_THIS_TIME_PLEASE_TRY_AGAIN_LATER",
          "FAILED_TO_RECEIVE_PARTY_MEMBERS_INFORMATION_FROM_CHANNEL",
          "ONLY_THE_PARTY_LEADER_CAN_DO_THIS",
          "PARTY_MEMBER_HAS_NOT_RESURRECTED_YET",
          "CAN_BE_USED_ONLY_IN_THE_BATTLE_MODE_SERVER"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnTransferPvpReqIgnored@CField@@IAEXAAVCInPacket@@@Z")

sp_str_list = ["S_IS_AT_THE_CASH_SHOP",
          "S_HAS_NOT_JOINED_MONSTER_LIFE",
          "STREETNAME",
          "S_IS_CURRENTLY_AT_S",
          "S_IS_AT_THE_EVENT_MAP"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnWhisper@CField@@IAEXAAVCInPacket@@@Z")

find_first_func_and_rename_by_a_sp_and_string("YOU_CANT_USE_IT_HERE_IN_THIS_MAP", getString_EA, "?OnSummonItemInavailable@CField@@IAEXAAVCInPacket@@@Z")

sp_str_list = ["BGM36IMG_HAPPYTIMESHORT",
          "MAP_MAP_MAP9_924050000IMG_BACK",
          "EFFECT_ELITEMOBEFFIMG_ELITEBONUSSTAGE"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnEliteState@CField@@QAEXAAVCInPacket@@@Z")

sp_str_list = ["THERE_ARE_NO_REMAINING_SEATS_ON_THE_GROUP_CHAIR",
          "UNABLE_TO_FIND_GROUP_CHAIR",
          "INVITED_PLAYER_TO_SIT_ON_GROUP_CHAIR",
          "THE_GROUP_CHAIR_INVITATION_WAS_DECLINED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnGroupChairInvitationResult@CField@@QAEXAAVCInPacket@@@Z")

sp_str_list = ["THE_ZAKUM_SHRINE_WILL_CLOSE_IN_D_MINUTES",
          "THE_ZAKUM_SHRINE_WILL_CLOSE_IF_YOU_DO_NOT_SUMMON_ZAKUM_IN_D_MINUTES",
          "THE_ZAKUM_SHRINE_HAS_CLOSED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnZakumTimer@CField@@IAEXAAVCInPacket@@@Z")

sp_str_list = ["S_EXPEDITION_CHALLENGE_HAS_ENDED",
          "D_MIN_LEFT_UNTIL_THE_S__EXPEDITION_CHALLEGNE_ENDS",
          "IF_THE_EXPEDITION_CHALLENGE_DOESNT_TAKE_PLACE_D_MIN_AFTER_ENTERING_THE_CHALLENGE"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnSummonBossTimer@CField@@IAEXAAVCInPacket@@@Z")

sp_str_list = ["YOU_ARE_NOT_MARRIED_OR_YOUR_SPOUSE_IS_CURRENTLY_OFFLINE", "S__S"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CField::OnWhisperSpouse")

rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.contains_sp_str("SHAPE2DVECTOR2D")),
                get_all_functions_containing_aob("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 6C 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 89 4D D8 C7 45 ? ? ? ? ?"))),
    "?OnCreateFallingCatcher@CField@@QAEXAAVCInPacket@@@Z")

#  CField::OnPacket  ---------------------------------------------------------------------------------------------------










#  CUserPool::OnUserCommonPacket  --------------------------------------------------------------------------------------


    #  CUser::OnChat -> CUserPool::OnCommonPacket
CUser_OnChat_EA = find_first_str_and_rename_first_xref_func("act2", "CUser::OnChat")
CUserPool_OnCommonPacket_EA = get_first_xref_and_rename_func(CUser_OnChat_EA, "CUserPool::OnCommonPacket")

    #  CUser::OnMiniRoomBalloon  ->  CUserPool::OnPacket  ->  CField::OnPacket
CUserPool_OnMiniRoomBalloon = find_first_str_and_rename_first_xref_func("%s : %s Create MiniRoom", "CUser::OnMiniRoomBalloon")
CUserPool_OnUserCommonPacket = get_first_xref_and_rename_func(CUserPool_OnMiniRoomBalloon, "CUserPool::OnCommonPacket")
CUserPool_OnPacket = get_first_xref_and_rename_func(CUserPool_OnUserCommonPacket, "?OnPacket@CUserPool@@QAEXJAAVCInPacket@@@Z")
CField_OnPacketEA_3 = get_first_xref_and_rename_func(CUserPool_OnPacket, "?OnPacket@CField@@UAEXJAAVCInPacket@@@Z")  # CField::OnPacket

    #  CPet::OnActionCommand  ->  CUser::OnPetPacket  ->  CUserPool::OnUserCommonPacket
CUser_OnPet_OnActionCommand_EA = find_first_func_and_rename_by_a_sp_and_string("YUM_YUM_S_XD_LEFT", getString_EA, "?OnActionCommand@CPet@@QAEXAAVCInPacket@@@Z")
CUser_OnPetPacket_EA = get_first_xref_and_rename_func(CUser_OnPet_OnActionCommand_EA, "CUser::OnPetPacket")
CUser_OnUserCommonPacket_EA = get_first_xref_and_rename_func(CUser_OnPetPacket_EA, "?OnUserCommonPacket@CUserPool@@IAEXJAAVCInPacket@@@Z")



sp_str_list = ["ENCHANTSUCCESS", "ENCHANTFAILURE",
          "THE_TOTEMS_DURATION_HAS_BEEN_EXTENDED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CUser::ShowItemUpgradeEffect")

sp_str_list = ["ENCHANTSUCCESS", "ENCHANTFAILURE",
          "SUCCESSFULLY_CONVERTED_TO_A_SOUL_WEAPON",
          "FAILED_TO_CONVERT_TO_A_SOUL_WEAPON"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CUser::ShowItemSkillSocketUpgradeEffect")

sp_str_list = ["ENCHANTSUCCESS", "ENCHANTFAILURE",
          "_THE_EFFECT_OF_THE_SOUL_HAS_BEEN_APPLIED",
          "FAILED_TO_EQUIP_SOUL",
          "THE_ITEM_WAS_DESTROYED_WHILE_EQUIPPING_THE_SOUL",
          "SUCCESSFULLY_EQUIPPED_SOUL"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CUser::ShowItemSkillOptionUpgradeEffect")

sp_str_list = ["MAGNIFIERSUCCESS",
          "BONUS_POTENTIAL_REVEALED",
          "POTENTIAL_REVEALED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CUser::ShowItemReleaseEffect")

sp_str_list = ["ENCHANTSUCCESS",
          "FAILED_TO_EXPAND_POTENTIAL_SLOTS",
          "RESETTING_POTENTIAL_HAS_FAILED_DUE_TO_INSUFFICIENT_SPACE_IN_THE_USE_ITEM",
          "POTENTIAL_RESET",
          "POTENTIAL_HAS_BEEN_RESET_R_NYOUVE_OBTAINED_S",
          "SUCCESSFULLY_EXPANDED_POTENTIAL_SLOTS",
          "USED_S_TO_APPRAISE_THE_POTENTIAL_OF_S"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CUser::ShowItemUnreleaseEffect")

sp_str_list = ["ENCHANTSUCCESS", "ENCHANTFAILURE",
          "FAILED_TO_USE_THE_LUCKY_ITEM_SCROLL",
          "SUCCESSFULLY_USED_THE_LUCKY_ITEM_SCROLL_THIS_EQUIPMENT_WILL_BE_INCLUDED_IN_S",
          "SET"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CUser::ShowItemLuckyItemEffect")

sp_str_list = ["ITEM_CASH_0506IMG_08D_EFFECT_DEFAULT",
          "FIREWORKS_ITEMS_CANNOT_BE_USED",
          "A_FIREWORK_ITEM_HAS_BEEN_USED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CUser::ShowItemFireWorksEffect")

sp_str_list = ["ENCHANTFAILURE", "ENCHANTSUCCESS",
          "THE_S_LIGHTS_UP_DRAWING_ENERGY_FROM_S_TO_S",
          "THE_S_LIGHTS_UP_BUT_NOTHING_HAPPENS"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CUser::ShowItemOptionChangeEffect")

sp_str_list = ["EFFECT_CHARACTEREFFIMG_MEISTEREFF_EQUIPMENT",
          "EFFECT_CHARACTEREFFIMG_MEISTEREFF_ACCESSORY",
          "EFFECT_CHARACTEREFFIMG_MEISTEREFF_ALCHEMY"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "?SetMakingMeisterSkillEff@CUser@@QAEXJ@Z")

sp_str_list = ["YOUR_REQUEST_TO_BE_ON_THE_WAIT_LIST__R_NWILL_BE_CANCELED_BECAUSE_YOU_ENTERED_A_F",
          "ARE_YOU_READY_TO_GO_R_NPRESS_THE_OK_BUTTON_TO_PROCEED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUser::OnWaitQueueResponse")

#  CUserPool::OnUserCommonPacket  --------------------------------------------------------------------------------------










#  CUserRemote::OnPacket  ----------------------------------------------------------------------------------------------

aob = "81 ? 58 D1 06 06"
aobList = ["81 ? 58 D1 06 06",
           "81 ? 10 AB D7 17",
           "81 ? 46 20 D8 17",
           "81 ? 23 FD D7 17",
           "81 ? 2C AB D7 17"]
CUserRemote_OnAttackEA = find_first_func_by_multiple_aob_and_rename(aob, aobList, "?OnAttack@CUserRemote@@QAEXJAAVCInPacket@@@Z")
CUserRemote_OnPacketEA = get_first_xref_and_rename_func(CUserRemote_OnAttackEA, "CUserRemote::OnPacket")

#  CUserRemote::OnPacket  ----------------------------------------------------------------------------------------------










#  CUserLocal::OnPacket  -----------------------------------------------------------------------------------------------

find_first_aob_and_rename_func("E8 ? ? ? ? 83 C4 0C 8B CE E8 ? ? ? ? 0F B6 C0", "CUserLocal::OnFamiliarAddResult")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 20 A1 ? ? ? ? 33 C5 89 45 F0 53 56 57 50 8D 45 F4 64 A3 ? ? ? ? 8B 75 08 8B CE", "CUserLocal::OnPush")
find_first_aob_and_rename_func("3D 1C CA 31 01 74 ? 3D 1B CA 31 01 74 ?", "CUserLocal::OnIncLarknessResponse")
find_first_aob_and_rename_func("E8 ? ? ? ? 8D ? 18 6B AF FF", "CUserLocal::OnRandomEmotion")
find_first_aob_and_rename_func("6a 00 6a 00 6a 00 6a 00 6a ff 6a 1d", "CWvsContext::OnEnterField")
find_first_aob_and_rename_func("E8 ? ? ? ? EB 33 85 D2", "CLegion::Decode")
find_first_aob_and_rename_func("57 E8 ? ? ? ? 83 C4 08 8B CF", "?RawDecode@GW_ItemSlotEquip@@MAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("64 ? ? ? ? ? 8b ? ? ? ? ? 6a 01 68 ? ? ? ? ff ? 85 ?", "StringPool::GetInstance")
find_first_aob_and_rename_func("6a 04 b9 ? ? ? ? e8 ? ? ? ? 8b f8 89 7d e4", "StringPool::GetBSTR")
find_first_aob_and_rename_func("83 f9 64 72 ? 3d f8 2a 00 00 74 ? 33 c9 eb ?", "CFuncKeyMappedMan::OnInit")
find_first_aob_and_rename_func("83 FF 13 74 ? 83 FF 4B 74 ? 83 FF 6C", "?OnMovePacket@CMovePath@@QAEXAAVCInPacket@@HJ@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 51 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 75 08 8B CE E8 ? ? ? ? 8B CE", "CUserLocal::OnZeroLevelUpAlarm")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 14 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 89 4D F0 8B 4D 08", "CUserLocal::OnIsUniverse")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 24 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B 4D 08 E8 ? ? ? ? 33 F6", "CUserLocal::OnGetNpcCurrentAction")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 18 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B 7D 08 8B CF E8 ? ? ? ? 8D 4D EC", "?OnOpenBattlePvPChampSelectUI@CUserLocal@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 83 EC 08 53 8B D9 56 57 83 BB ? ? ? ? ? 0F 85 ? ? ? ?", "?OnFieldScoreUpdate@CUserLocal@@AAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 83 EC 08 53 8B 5D 08 8B CB", "?OnSkillCooltimeSet@CUserLocal@@IAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 38 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 87 ? ? ? ? 8D 8F ? ? ? ?", "CUserLocal::OnUserExtraAttackRequest")
rename_func(get_called_addr(find_first_addr_by_aob("E8 ? ? ? ? 5E 5D C2 08 00 FF 75 0C E8 ? ? ? ? 5E 5D C2 08 00 FF 75 0C E8 ? ? ? ? 5E 5D C2 08 00 83 EC 1C")), "?OnPlantPotClickResult@CUser@@QAEXAAVCInPacket@@@Z")


find_first_str_and_rename_first_xref_func("DEFAULT : %lld/%lld", "CUserLocal::OnPinkBeanDecrease")
find_first_str_and_rename_first_xref_func("The Cash Shop is not available for the Guest ID Users.", "CUserLocal::DisplaySetCashShopError")
find_first_str_and_rename_first_xref_func("nStep %d, nGiftID %d", "CUserLocal::OnUnk203_1044")
find_first_str_and_rename_first_xref_func("nStep %d, nOldCoin %d, nNewCoin %d", "CUserLocal::OnGuardiansBlessingResult")
find_first_str_and_rename_first_xref_func("CHARACTERDATA.LOG FILE Save Done.  Check MapleStory Folder.", "CUserLocal::OnCharacterDataLog")
find_first_str_and_rename_first_xref_func("AdminMob", "CUserLocal::OnFamiliarAddResult")
find_first_str_and_rename_first_xref_func("UI/UIWindow.img/FloatNotice/%d/DrawOrigin/icon", "CUserLocal::OnEffect")
find_first_str_and_rename_first_xref_func("UI/UIWindow2.img/EvolvingEventCheck", "CUserLocal::OpenUI")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingBox2DAttack", "?TryDoingBox2DAttack@CUserLocal@@QAEHJJPAVb2Body@@HK@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingMeleeAttack", "?TryDoingMeleeAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JPAJJKJPAVCGrenade@@JHJJJ_NJK@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingShootAttack", "?TryDoingShootAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JJHJIJJ_N@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingMagicAttack", "?TryDoingMagicAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JJJ_NJJKJ1I@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingBodyAttack", "?TryDoingBodyAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JPAVCMob@@UtagPOINT@@@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingShootAttackObject", "?TryDoingShootObjectAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JPAJJKJPAVCGrenade@@JHJJJ_NJK@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::OnDoActivePsychicArea", "?OnDoActivePsychicArea@CUserLocal@@QAEXAAVCInPacket@@@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingPsychicLockAttack", "?TryDoingPsychicLockAttack@CUserLocal@@QAEXJJJPAV?$list@JV?$allocator@J@std@@@std@@@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingForceAtomAttack", "?TryDoingForceAtomAttack@CUserLocal@@QAEHABV?$ZArray@V?$ZRef@VCForceAtom@@@@@@PBUSKILLENTRY@@JK@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingMonsterMagnet", "?TryDoingMonsterMagnet@CUserLocal@@IAEHPBUSKILLENTRY@@JJ@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingRandAreaAttack", "?TryDoingRandAreaAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JKUtagPOINT@@@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingForceAtomAttack_NonTarget", "?TryDoingForceAtomAttack_NonTarget@CUserLocal@@QAEHAAV?$ZRef@VCForceAtom@@@@@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingBonusAttack", "?TryDoingBonusAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JABV?$ZArray@K@@JABV?$ZArray@J@@@Z")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingSmoothingMovingShootAttack", "?TryDoingSmoothingMovingShootAttack@CUserLocal@@IAEXXZ")
find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingFlameBall", "?TryDoingFlameBall@CUserLocal@@IAEHPBUSKILLENTRY@@JAAUFLAMEBALL_INFO@@V?$ZRef@VCFlameBallObject@@@@UtagPOINT@@W4ECharacterAction@@_N@Z")



    #  GiantShadowSpear  ->  CUserLocal::OnPacket  ->  CUserPool::OnPacket
OnGiantShadowSpearAttack_EA = find_first_aob_and_rename_func("68 3B 24 D8 17", "?OnGiantShadowSpearAttack@CUserLocal@@QAEXAAVCInPacket@@@Z")
CUserLocal_1_OnPacket_EA = get_first_xref_and_rename_func(OnGiantShadowSpearAttack_EA, "?OnPacket_1@CUserLocal@@QAEXJAAVCInPacket@@@Z_0")
CUserLocal_OnPacket_EA = get_first_xref_and_rename_func(CUserLocal_1_OnPacket_EA, "?OnPacket@CUserLocal@@QAEXJAAVCInPacket@@@Z")
CUserPool_OnPacket_EA = get_first_xref_and_rename_func(CUserLocal_OnPacket_EA, "?OnPacket@CUserPool@@QAEXJAAVCInPacket@@@Z")
CField_OnPacket_EA_2 = get_first_xref_and_rename_func(CUserPool_OnPacket_EA, "?OnPacket@CField@@UAEXJAAVCInPacket@@@Z")  # CField::OnPacket

    #  CUserLocal::ShowMonadResult  ->  CUserLocal::OnShowMonadResult
CUserLocal_ShowMonadResult_EA = find_first_str_and_rename_first_xref_func("UI/UIWindowPL.img/MainStream_ScenarioReward/","CUserLocal::ShowMonadResult")
CUserLocal_OnShowMonadResult_EA = get_first_xref_and_rename_func(CUserLocal_ShowMonadResult_EA, "CUserLocal::OnShowMonadResult")

    #  CUserLocal::TryDoingLightningUnionSubAttack  ->  CUserLocal::OnLightningUnionSubAttack
CUserLocal_TryDoingLightningSubUnionAttack_EA = find_first_str_and_rename_first_xref_func("CUserLocal::TryDoingLightningUnionSubAttack","?TryDoingLightningUnionSubAttack@CUserLocal@@IAEHPBUSKILLENTRY@@JJHJIJJ_N@Z")
get_first_xref_and_rename_func(CUserLocal_TryDoingLightningSubUnionAttack_EA, "CUserLocal::OnLightningUnionSubAttack")



aob = "3D 75 B1 A9 00 75"
aobList = ["68 75 B1 A9 00",
           "3D BD A1 B8 00 75",
           "68 BD A1 B8 00"]
find_first_func_by_multiple_aob_and_rename(aob, aobList, "CUserLocal::OnExplosionAttack")

aob = "81 ? A8 0E 70 01"
aobList = ["81 ? A8 0E 70 01",
           "81 ? 3E 3D 42 00",
           "81 ? 30 FD D7 17",
           "81 ? F0 FF AC 06"]
CUserLocal_TryRegFAByReq_EA = find_first_func_by_multiple_aob_and_rename(aob, aobList, "CUserLocal::TryRegisterFinalAttackBySvrRequest")
get_first_xref_and_rename_func(CUserLocal_TryRegFAByReq_EA, "CUserLocal::OnFinalAttackRequest")

aob = "81 ? 0E 88 D7 17"
aobList = ["81 ? 46 24 D8 17",
           "81 ? DF 68 D2 03"]
find_first_func_by_multiple_aob_and_rename(aob, aobList, "CUserLocal::OnUserBonusAttackRequest")

aob = "68 44 24 D8 17"
aobList = ["68 44 24 D8 17"]
find_first_func_by_multiple_aob_and_rename(aob, aobList, "?OnGreaterDarkServantSwapResult@CUserLocal@@QAEXAAVCInPacket@@@Z")

aob = "81 ? 6C F5 3E 09"
aobList = ["81 ? 48 20 D8 17",
           "81 ? 32 F9 D7 17",
           "81 ? 51 4B D8 17"]
find_first_func_by_multiple_aob_and_rename(aob, aobList, "?OnCreateAreaDoTInfo@CUserLocal@@QAEXAAVCInPacket@@@Z")



find_first_func_and_rename_by_a_sp_and_string("YOU_HAVE_FAILED_TO_USE_THE_MESO_SACK", getString_EA, "CUserLocal::OnGiveMesoFailed")

sp_str_list = ["IN_PREPARATION_FOR_THE_MAPLE_RISING_STAR_CONTEST_IF_YOU_CAN_HELP_ME_COMPLETE_MY_",
          "QUEST_SAYIMG_D_D_YES",
          "QUEST_SAYIMG_D_D_YESEX",
          "QUEST_ACTIMG_D_D_ITEM",
          "COUNT"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnQuestResult")

sp_str_list = ["YOU_ARE_LACKING_THE_HP_POTION_THAT_YOUR_PET_IS_SUPPOSED_TO_USE",
          "YOUR_PET_IS_RUNNING_LOW_ON_THE_ALLOTTED_HP_POTIONS",
          "YOU_CANNOT_USE_THAT_ITEM_BECAUSE_OF_A_STRANGE_ENERGY_THAT_SURROUNDS_YOU"]
CUserLocal_TryConsumePetHP_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::TryConsumePetHP")

sp_str_list = ["THE_ITEM_PICK_UP_SKILL_HAS_BEEN_S_TO_SS_LIST_OF_SKILLS",
          "THE_EXPAND_RANGE_OF_MOTION_SKILL_HAS_BEEN_S_TO_SS_LIST_OF_SKILLS",
          "THE_AUTO_PICKUP_SKILL_HAS_BEEN_S_TO_SS_LIST_OF_SKILLS",
          "THE_IGNORE_SELECT_ITEMS_SKILL_HAS_BEEN_S_TO_SS_LIST_OF_SKILLS",
          "THE_PICK_UP_UNALLOTTED_ITEMS__MESOS_SKILL_HAS_BEEN_S_TO_SS_LIST_OF_SKILLS",
          "THE_HP_RECHARGE_SKILL_HAS_BEEN_S_TO_SS_LIST_OF_SKILLS",
          "THE_MP_RECHARGE_SKILL_HAS_BEEN_S_TO_SS_LIST_OF_SKILLS"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnUserPetSkillChanged")

sp_str_list = ["UI_LOGINIMG_WORLDNOTICE_BALLOON",
          "SLARROW",
          "ARIAL",
          "BA"]
CUserLocal_BalloonMsgInit_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringBSTR_EA, "CUserLocal::CBalloonMsg::Init")
CUserLocal_ShowBalloonMsg_EA = get_first_xref_and_rename_func(CUserLocal_BalloonMsgInit_EA, "CUserLocal::ShowBalloonMsg")
CUserLocal_OnBalloonMsg_EA = get_first_xref_and_rename_func(CUserLocal_ShowBalloonMsg_EA, "CUserLocal::OnShowBalloonMsg")

sp_str_list = ["YOU_HAVE_GAINED_ITEMS_IN_THE_S_TAB_S_D",
          "YOU_HAVE_LOST_ITEMS_S_D",
          "YOU_HAVE_LOST_MESOS_D",
          "YOU_HAVE_LOST_AN_ITEM_S"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnMakerResult")

sp_str_list = ["UI_TUTORIALIMG_D",
          "HEIGHT"]
CTutor_OnMsg_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CTutor::OnMessage")
get_first_xref_and_rename_func(CTutor_OnMsg_EA, "CUserLocal::OnTutorMsg")

sp_str_list = ["_S_S_BROADCASTING_WILL_BEGIN_PLEASE_TURN_UP_THE_VOLUME",
          "SOUND_RADIOIMG_S"]
CRadioManager_Play_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringBSTR_EA, "CRadioManager::Play")
get_first_xref_and_rename_func(CRadioManager_Play_EA, "CUserLocal::OnRadioSchedule")

sp_str_list = ["YOUR_TARGET_IS_ALREADY_FOLLOWING_S",
          "FOLLOW_TARGET_CANNOT_ACCEPT_THE_REQUEST_AT_THIS_TIME",
          "YOU_CANNOT_SEND_A_FOLLOW_REQUEST_WHILE_YOU_ARE_ALREADY_FOLLOWING_SOMEONE",
          "THE_FOLLOW_REQUEST_HAS_NOT_BEEN_ACCEPTED",
          "YOU_ARE_TOO_FAR_AWAY",
          "FOLLOW_CANNOT_BE_USED_HERE",
          "THE_FOLLOW_REQUEST_COULD_NOT_BE_EXECUTED_DUE_TO_AN_UNKNOWN_ERROR"]
CUserLocal_FollowCharFailedMsg_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::FollowCharacterFailedMsg")
get_first_xref_and_rename_func(CUserLocal_FollowCharFailedMsg_EA, "CuserLocal::OnFollowCharacterFailed")

sp_str_list = ["A_MEDAL_HAS_BEEN_RE_ISSUED_TO_YOU_S",
          "THIS_MEDAL_CANNOT_BE_RE_ISSUED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnMedalReissueResult")

find_first_func_and_rename_by_a_sp_and_string("YOU_USED_ONE_S_TO_PROTECT_YOUR_BUFFS", getString_EA, "UserLocal::OnSetBuffProtector")

Context_SetBtCandy_TradeEnable_EA = find_first_func_and_rename_by_a_sp_and_string("YOU_CANNOT_RECEIVE_ANYMORE_CANDY_TODAY", getString_EA, "CUIContextMenu::SetBtCandyTradeEnable")
get_first_xref_and_rename_func(Context_SetBtCandy_TradeEnable_EA, "CUserLocal::OnTrickOrTreatResult")

find_first_func_and_rename_by_a_sp_and_string("THERE_IS_NO_BUFF_EFFECT_TO_BE_PRESERVED", getString_EA, "CUserLocal::OnOpenUIDead")

sp_str_list = ["THE_ITEM_CANNOT_BE_USED_AS_R_NTHE_EVENT_PERIOD_HAS_ENDED",
          "YOU_CANT_USE_THE_KALEIDO_SPINNER_ANY_MORE_TODAY_R_NYOU_CAN_USE_THE_BOARD_D_TIMES",
          "ITEM_ONLY_VALID_UNTIL_YEAR_D_MONTH_D_DAY_D__R_NAFTER_THAT_THE_ITEM_WILL_DISAPPEA"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnExpiredNotice")

sp_str_list = ["PLEASE_HAVE_YOUR_PARTY_LEADER_REQUEST_ENTRY",
          "A_MEMBER_OF_YOUR_PARTY_IS_SOMEWHERE_ELSE_ALL_PARTY_MEMBERS_MUST_BE_GATHERED_TO_E",
          "YOU_CANNOT_ENTER_S_HAS_NOT_BEGUN_THE_SCENARIO",
          "YOU_CANNOT_ENTER_S_HAS_REACHED_THEIR_CHALLENGE_LIMIT_FOR_THE_DAY"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnMirrorDungeonEnterFailed")

CWvsContext_UpdateMirDunClearState_EA = find_first_func_and_rename_by_a_sp_and_string("EFFECT_BASICEFFIMG_MIRRORDUNGEONCLEAR_GO", getStringBSTR_EA, "CWvsContext::UpdateMirrorDungeonClearState")
get_first_xref_and_rename_func(CWvsContext_UpdateMirDunClearState_EA, "CUserLocal::OnMirrorDungeonUnitCleared")

sp_str_list = ["YOUVE_OBTAINED_LLD_EXP_AND_D_WP",
          "YOUR_CLEAR_EXP_WILL_BE_GREATLY_REDUCED_IF_YOUR_LEVEL_IS_HIGHER_THAN_D"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnMirrorDungeonRecord")

sp_str_list = ["THE_EFFECT_IS_TAKING_PLACE_RIGHT_NOW_R_NPLEASE_TRY_IT_LATER",
          "NO_CURRENT_BROADCASTS"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnOpenURL")

find_first_func_and_rename_by_a_sp_and_string("COMMON_DISAPPEAR", getStringW_EA, "CUserLocal::OnResetRuneStoneAction")

sp_str_list = ["YOU_ARE_NO_LONGER_ON_THE_WAIT_LIST_CLOSE_ANY_OTHER_WINDOWS_SUCH_AS_NPC_SHOPS_DEL",
          "MAKE_SURE_YOURE_NOT_ON_A_MAP_THAT_RESTRICTS_WAIL_LISTING_OR_ENTRY"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnMoveToContentsCannotMigrate")

sp_str_list = ["YOU_WILL_BE_LOGGED_OUT_R_NYOUR_NEW_NAME_WILL_TAKE_EFFECT_ON_YOUR_NEXT_LOGIN",
          "INVALID_REQUEST",
          "NOT_ENOUGH_MAPLE_POINTS",
          "YOU_NEED_A_NAME_CHANGE_COUPON_TO_DO_THAT"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnUserRenameResult")

sp_str_list = ["UI_LOGIN_COMMON_BURNING_BURNINGCHARACTER_ITEM",
          "YOU_HAVE_DESIGNATED_THIS_CHARACTER_AS_YOUR_BURNING_CHARACTER",
          "AN_ERROR_OCCURRED_UNABLE_TO_DESIGNATE_CHARACTER_FOR_BURNING",
          "YOU_CANNOT_DESIGNATE_THIS_CHARACTER_FOR_BURNING"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CUserLocal::OnBurningResult")

sp_str_list = ["YOU_NEED_A_TARGET_TO_USE_THE_MAGIC_DEBRIS",
               "THIS_SKILL_CANNOT_BE_USED_WITH_THE_EQUIPPED_ITEM",
               "THERE_IS_NO_AVAILABLE_MAGIC_DEBRIS"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?DoActiveSkill_MagicWreckage@CUserLocal@@IAEHPBUSKILLENTRY@@J@Z")

sp_str_list = ["THIS_SKILL_CANNOT_BE_USED_WITH_THE_EQUIPPED_ITEM", "THIS_SKILL_CANNOT_BE_USED",
               "YOU_CANNOT_USE_THAT_SKILL_ON_THIS_MAP", "YOU_MUST_EQUIP_D_OR_MORE_SET_ITEMS_THAT_ARE_A_PART_OF_S",
               "YOU_CANNOT_USE_THAT_SKILL_BECAUSE_YOU_DONT_HAVE_AN_EVIL_EYE_SUMMONED",
               "THAT_CAN_ONLY_BE_USED_WHILE_EVIL_EYE_OF_DOMINATION_IS_ACTIVE",
               "YOU_CANNOT_USE_THAT_SKILL_ON_THIS_MAP", "YOU_DO_NOT_HAVE_ENOUGH_THROWING_STARS_TO_USE_THIS_SKILL"
               "YOU_DO_NOT_HAVE_ENOUGH_BULLETS_TO_USE_THIS_SKILL"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?DoActiveSkill_StatChange@CUserLocal@@IAEHPBUSKILLENTRY@@JKK@Z")

find_first_func_and_rename_by_a_sp_and_string("YOU_OBTAINED_THE_S_MEDAL", getString_EA, "CUserLocal::OnMedalObtained")

sp_str_list = ["UI_UIWINDOW6IMG_DISCOVERYSKYSKILL_GAUGE",
          "SKILL_03DIMG_SKILL_07D_SPECIAL2"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CUserLocal::OnDiscoverySkillGauge")

find_first_func_and_rename_by_a_sp_and_string("YOU_OBTAINED_D_MESOS_FROM_THE_RANDOM_MESO_SACK", getString_EA, "CUserLocal::OnRandomMesoSackSucceed")

find_first_func_and_rename_by_a_sp_and_string("THERE_WERE_NO_MESOS_IN_THE_SACK", getString_EA, "CUserLocal::OnRandomMesoSackFail")

find_first_func_and_rename_by_a_sp_and_string("CONGRATULATIONS_YOU_HAVE_REACHED_LV30_50_70_DURING_THE_EVENT_PERIOD_AND_HAVE_BEE", getString_EA, "CUserLocal::OnEventLevelUpCheckPoint")

sp_str_list = ["UI_UIWINDOW2IMG_QUESTMESSENGERTHOMAS_BACKGRND",
          "UI_UIWINDOW2IMG_QUESTMESSENGERALICE_BACKGRND"]
CUserLocal_ShowQuestMessenger_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CUserLocal::ShowQuestMessenger")
get_first_xref_and_rename_func(CUserLocal_ShowQuestMessenger_EA, "CUserLocal::OnShowQuestMessenger")

find_first_func_and_rename_by_a_sp_and_string("EFFECT_CHARACTEREFFIMG_FORCEATOM_49_ATOM_1_PARENTATOM", getStringW_EA, "CUserLocal::OnUnk203_1083")

find_first_func_and_rename_by_a_sp_and_string("INCREASED_AVAILABLE_UPGRADE_BY_1", getString_EA, "CUIGoldHammer::OnItemUpgradeResult")

rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and CUserLocal_1_OnPacket_EA in get_all_distinct_func_start_addr_from_ea_list(x.get_xrefs())),
                get_all_functions_containing_sp_str("EFFECT_MAPEFFIMG_S"))),
    "CUserLocal::OnUnk203_1024")

aob_list = ["? 0C B5 C4 04", "? 91 D1 F0 08"]
rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.makes_call_to(decode4_EA)),
                get_all_functions_containing_aob_list(aob_list))),
    "CUserLocal::OnTideOfBattle")

#  CUserLocal::OnPacket  -----------------------------------------------------------------------------------------------










#  CSummonedPool::OnPacket  --------------------------------------------------------------------------------------------

CSummoned_OnHit_EA = find_first_aob_and_rename_func("8B F9 83 BF ? ? ? ? ? 8B 75 08 74 07", "?OnHit@CSummoned@@QAEXAAVCInPacket@@@Z")
CUser_OnSummonedHit_EA = get_first_xref_and_rename_func(CSummoned_OnHit_EA, "?OnSummonedHit@CUser@@QAEXAAVCInPacket@@@Z")
get_first_xref_and_rename_func(CUser_OnSummonedHit_EA, "?OnHit@CSummonedPool@@IAEXKAAVCInPacket@@@Z")

CSummoned_OnEffect_case4_EA = find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 14 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 C7 45 ? ? ? ? ? 8B 4D 08", "CSummoned::OnEffect_Case4")
CSummoned_OnEffect_EA = get_first_xref_and_rename_func(CSummoned_OnEffect_case4_EA, "CSummoned::OnEffect")
CUser_OnSummonedEffect_EA = get_first_xref_and_rename_func(CSummoned_OnEffect_EA, "CUser::OnSummonedEffect")
get_first_xref_and_rename_func(CUser_OnSummonedEffect_EA, "CSummonedPool::OnEffect")


find_first_str_and_rename_first_xref_func("CSummoned::TryDoingAttack", "?TryDoingAttack@CSummoned@@QAEXJJ@Z")
find_first_str_and_rename_first_xref_func("CSummoned::TryDoingAttackStorm", "?TryDoingAttackStorm@CSummoned@@QAEXJJ@Z")
find_first_str_and_rename_first_xref_func("CSummoned::TryDoingAttackManual", "?TryDoingAttackManual@CSummoned@@QAEXJH@Z")


    #  BeholderRevengeAttack  ->  CField::OnPacket
CSummoned_Beholder_EA = find_first_aob_and_rename_func("68 4B 24 14 00", "?OnBeholderRevengeAttack@CSummoned@@QAEXAAVCInPacket@@@Z")
CUser_Beholder_EA = get_first_xref_and_rename_func(CSummoned_Beholder_EA, "?OnSummonBeholderRevengeAttack@CUser@@QAEXAAVCInPacket@@@Z")
CSummonedPool_Beholder_EA = get_first_xref_and_rename_func(CUser_Beholder_EA, "?OnSummonBeholderRevengeAttack@CSummonedPool@@IAEXKAAVCInPacket@@@Z")
CSummonedPool_OnPacket_EA = get_first_xref_and_rename_func(CSummonedPool_Beholder_EA, "CSummonedPool::OnPacket")
CField_OnPacket_EA = get_first_xref_and_rename_func(CSummonedPool_OnPacket_EA, "?OnPacket@CField@@UAEXJAAVCInPacket@@@Z")  # CField::OnPacket



aob = "81 ? 88 E0 10 09"
aobList = ["39 AF D7 17",
           "5E 4B D8 17",
           "3D 51 D6 D7 17",
           "3D 4D D6 D7 17",
           "3D 15 DA 13 00",
           "3D 44 24 D8 17"]
find_first_func_by_multiple_aob_and_rename(aob, aobList, "?OnCreated@CSummonedPool@@IAEXKAAVCInPacket@@@Z")

aob = "81 ? ? ? ? ? 51 D6 D7 17"
aobList = ["81 ? 43 4B D8 17",
           "3D 37 D6 D7 17",
           "3D 49 24 D8 17",
           "3D 47 D6 D7 17"]
CSummoned_OnSkillEA = find_first_func_by_multiple_aob_and_rename(aob, aobList, "?OnSkill@CSummoned@@QAEXAAVCInPacket@@@Z")
CUser_OnSummonedSkillEA = get_first_xref_and_rename_func(CSummoned_OnSkillEA, "?OnSummonedSkill@CUser@@QAEXAAVCInPacket@@@Z")
CSummonedPool_OnSkillEA = get_first_xref_and_rename_func(CUser_OnSummonedSkillEA, "?OnSkill@CSummonedPool@@IAEXKAAVCInPacket@@@Z")

aob = "81 ? ? ? ? ? 2B 31 51 00"
aobList = ["3D F5 87 D7 17",
           "3D 15 DA 13 00 ",
           "3D 88 E0 10 09",
           "3D 15 AF D7 17"]
CSummoned_OnAttackEA = find_first_func_by_multiple_aob_and_rename(aob, aobList, "?OnAttack@CSummoned@@QAEXAAVCInPacket@@@Z")
CUser_OnSummonedAttackEA = get_first_xref_and_rename_func(CSummoned_OnAttackEA, "?OnSummonedAttack@CUser@@QAEXAAVCInPacket@@@Z")
CSummonedPool_OnAttackEA = get_first_xref_and_rename_func(CUser_OnSummonedAttackEA, "?OnAttack@CSummonedPool@@IAEXKAAVCInPacket@@@Z")

aob = "46 4B D8 17"
aobList = ["5D 78 2F 00", "FD FE 30 00",
           "60 C0 17 02", "3F D6 D7 17",
           "40 D6 D7 17", "41 D6 D7 17",
           "42 D6 D7 17", "43 D6 D7 17",
           "60 AF D7 17", "FE AE D7 17",
           "0B 88 D7 17", "FB 87 D7 17"]
CSummoned_LoadAttackInfo = find_first_func_by_multiple_aob_and_rename(aob, aobList, "CSummonedBase::LoadAttackInfo")

aob = "3D DB EB CE 07"
aobList = ["3D 08 88 D7 17",
           "81 ? 46 AF D7 17",
           "81 ? 46 FE CE 07",
           "81 ? 5E FA CE 07",
           "81 ? 76 F6 CE 07",
           "81 ? 98 86 AE 06"]
CSummoned_OnRemovedEA = find_first_func_by_multiple_aob_and_rename(aob, aobList, "?OnRemoved@CSummoned@@QAEXAAVCInPacket@@@Z")
CSummonedPool_OnRemovedEA = get_first_xref_and_rename_func(CSummoned_OnRemovedEA, "?OnRemoved@CSummonedPool@@IAEXKAAVCInPacket@@@Z")



aob_list = ["54 4B D8 17", "55 4B D8 17", "56 4B D8 17", "57 4B D8 17", "58 4B D8 17"]
rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.makes_call_to(decode4_EA)
            and CSummonedPool_OnPacket_EA in get_all_distinct_func_start_addr_from_ea_list(x.get_xrefs())),
                get_all_functions_containing_aob_list(aob_list))),
    "CSummonedPool::OnJettSummon")

#  CSummonedPool::OnPacket  --------------------------------------------------------------------------------------------










#  CMobPool::OnPacket  -------------------------------------------------------------------------------------------------

find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 20 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B D9 8B 75 08 8B CE", "CMobPool::OnMobEnterField")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 18 56 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 8B 4D 08 E8 ? ? ? ? 89 86 ? ? ? ?", "CMobPool::OnCrcKeyChanged")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 58 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 89 7D EC 8B 5D 08", "CMob::OnMove")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 18 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 5D 08 8B CB E8 ? ? ? ? 8B CB 89 45 EC", "CMob::OnEscortStopSay")
find_first_aob_and_rename_func("55 8B EC 83 EC 10 53 8B D9 89 5D FC E8 ? ? ? ?", "CMob::OnMobTeleportRequest")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 28 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 33 DB 33 FF", "?OnAttackPriority@CMob@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 44 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B D9 89 5D EC 8B 75 08 8B CE", "?OnCastingBarSkill@CMob@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 75 08 8B CE E8 ? ? ? ? 8B CE 8B D8", "?OnLaserControl@CMob@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 56 8B F1 8B 4D 08 E8 ? ? ? ? 39 86 ? ? ? ? 74 61", "?OnScale@CMob@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 56 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 8B 4D 08 E8 ? ? ? ? 84 C0 74 60", "?ForceChase@CMob@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 83 EC 08 53 8B 5D 08 57 8B F9 8B CB E8 ? ? ? ? 89 45 F8", "CMob::OnMobBurnedInfoRemove")


find_first_str_and_rename_first_xref_func("DemianSword%d", "?OnDemianDelayedAttackCreate@CMob@@QAEXAAVCInPacket@@@Z")


find_first_func_and_rename_by_a_sp_and_string("SKILL_420IMG_SKILL_4201004_AFFECTED", getStringBSTR_EA, "?OnStealEffect@CMob@@QAEXAAVCInPacket@@@Z")


    #  CMobPool::Block
CMobPool_BlockAttack_EA = find_first_str_and_rename_first_xref_func("[Using Only Attack Idx: %d]", "CMobPool::BlockAttack")
CMobPool_OnBlockAttack_EA = get_first_xref_and_rename_func(CMobPool_BlockAttack_EA, "CMobPool::OnBlockAttack")
CMobPool_OnPacket_EA = get_first_xref_and_rename_func(CMobPool_OnBlockAttack_EA, "?OnPacket@CMobPool@@QAEXJAAVCInPacket@@@Z")


aob = "81 ? D0 B8 21 00"
aobList = ["81 ? 11 FB 30 00",
           "81 ? 71 74 2F 00",
           "81 ? 2C 98 78 08"]
CMobPool_OnMobSpecialEffectBySkillEA = find_first_func_by_multiple_aob_and_rename(aob, aobList, "?OnSpecialEffectBySkill@CMob@@QAEXAAVCInPacket@@@Z")
CMobPool_OnMobPacketEA = get_first_xref_and_rename_func(CMobPool_OnMobSpecialEffectBySkillEA, "CMobPool::OnMobPacket")
CMobPool_OnPacketEA = get_first_xref_and_rename_func(CMobPool_OnMobPacketEA, "CMobPool::OnPacket")

#  CMobPool::OnPacket  -------------------------------------------------------------------------------------------------










#  CNpcPool::OnPacket  -------------------------------------------------------------------------------------------------

sp_str_list = ["NPC_07DIMG", "EFFECT"]
CNpcPool_OnMove_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CNpcPool::OnMove")
CNpcPool_OnNpcPacket_EA = get_first_xref_and_rename_func(CNpcPool_OnMove_EA, "CNpcPool::OnNpcPacket")
CNpcPool_OnPacket_EA = get_first_xref_and_rename_func(CNpcPool_OnNpcPacket_EA, "CNpcPool::OnPacket")

#  CNpcPool::OnPacket  -------------------------------------------------------------------------------------------------










#  CDropPool::OnPacket  ------------------------------------------------------------------------------------------------

#  CDropPool::OnPacket  ------------------------------------------------------------------------------------------------










#  CAffectedAreaPool::OnPacket  ----------------------------------------------------------------------------------------

aob = "? D5 2D 11 09"
aobList = ["? D5 2D 11 09",
           "? 32 AF D7 17",
           "? 33 AF D7 17"]
CAAPool_OnCreatedEA = find_first_func_by_multiple_aob_and_rename(aob, aobList, "?OnAffectedAreaCreated@CAffectedAreaPool@@IAEXAAVCInPacket@@@Z")
CAAPool_OnPacketEA = get_first_xref_and_rename_func(CAAPool_OnCreatedEA, "CAffectedAreaPool::OnPacket")

#  CAffectedAreaPool::OnPacket  ----------------------------------------------------------------------------------------










#  CTownPortalPool::OnPacket  ------------------------------------------------------------------------------------------

aob_list = ["A2 AD AE 06", "30 75 00 00"]
sp_str_list = ["SKILL_11211IMG_SKILL_112111010_MDOOR",
               "SKILL_11211IMG_SKILL_112111010_FRAME",
               "SKILL_11211IMG_SKILL_112111010_CDOOR",
               "SKILL_231IMG_SKILL_2311002_MDOOR",
               "SKILL_231IMG_SKILL_2311002_FRAME",
               "SKILL_231IMG_SKILL_2311002_CDOOR"]

rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.contains_aob_list(aob_list)
            and x.makes_call_to(decode4_EA)),
                get_all_functions_containing_sp_str_list(sp_str_list))),
    "CTownPortalPool::OnTownPortalCreated")


#  CTownPortalPool::OnPacket  ------------------------------------------------------------------------------------------










#  CRandomPortalPool::OnPacket  ----------------------------------------------------------------------------------------

#  CRandomPortalPool::OnPacket  ----------------------------------------------------------------------------------------










#  COpenGatePool::OnPacket  --------------------------------------------------------------------------------------------

sp_str_list = ["SKILL_3510IMG_SKILL_35101005_EDOOR",
          "THE_PORTAL_THAT_WAS_INSTALLED_EARLIER_HAS_DISAPPEARED",
          "SKILL_3510IMG_SKILL_35101005_SDOOR"]
COpenGate_OnOpenGateRemoved_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "?OnOpenGateRemoved@COpenGatePool@@IAEXAAVCInPacket@@@Z")
COpenGate_OnPacket_EA = get_first_xref_and_rename_func(COpenGate_OnOpenGateRemoved_EA, "?OnPacket@COpenGatePool@@QAEXJAAVCInPacket@@@Z")

#  COpenGatePool::OnPacket  --------------------------------------------------------------------------------------------










#  CReactorPool::OnPacket  ---------------------------------------------------------------------------------------------


    #  CReactorPool::OnUnk200_1259 -> CReactorPool::OnPacket
CReactorPool_OnUnk200_1259_EA = find_first_str_and_rename_first_xref_func("Reactor/%d/%d/event/%d/arrow", "?OnUnk200_1259@CReactorPool@@QAEXJAAVCInPacket@@@Z")
CReactorPool_OnPacket_EA = get_first_xref_and_rename_func(CReactorPool_OnUnk200_1259_EA, "?OnPacket@CReactorPool@@QAEXJAAVCInPacket@@@Z")

#  CReactorPool::OnPacket  ---------------------------------------------------------------------------------------------










#  CScriptMan::OnPacket  -----------------------------------------------------------------------------------------------

find_first_func_and_rename_by_a_sp_and_string("IM_NOT_DOING_BUSINESS_AT_THIS_TIME_DONT_I_DESERVE_SOME_REST_TOO", getString_EA, "?OnPacket@CAdminShopDlg@@SAXJAAVCInPacket@@@Z")

#  CScriptMan::OnPacket  -----------------------------------------------------------------------------------------------










#  CFuncKeyMappedMan::OnPacket  ----------------------------------------------------------------------------------------

find_first_aob_and_rename_func("83 f9 64 72 ? 3d f8 2a 00 00 74 ? 33 c9 eb ?", "CFuncKeyMappedMan::OnInit")

#  CFuncKeyMappedMan::OnPacket  ----------------------------------------------------------------------------------------










#  CStoreBankDlg::OnPacket  --------------------------------------------------------------------------------------------

sp_str_list = ["I_DONT_THINK_YOU_HAVE_ANY_ITEMS_OR_MONEY_TO_RETRIEVE_HERE_R_NTHIS_IS_WHERE_YOU_R",
          "YOUR_PERSONAL_STORE_IS_OPEN_BIN_CHANNEL_S_FREE_MARKET_DK_R_NIF_YOU_NEED_ME_THEN_",
          "YOUR_ACCOUNT_HAS_BEEN_INACTIVE_FOR_A_WHILE_R_NSO_THE_ESTORE_BANK_HAS_BEEN_LOCKED",
          "THIS_IS_CURRENTLY_UNAVAILABLE_R_NPLEASE_TRY_AGAIN_LATER"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnPacket@CStoreBankDlg@@SAXJAAVCInPacket@@@Z")

#  CStoreBankDlg::OnPacket  --------------------------------------------------------------------------------------------










#  CField::OnGoldHammerRes  --------------------------------------------------------------------------------------------

#  CField::OnGoldHammerRes  --------------------------------------------------------------------------------------------










#  CBattleRecordMan::OnPacket  -----------------------------------------------------------------------------------------

find_first_aob_and_rename_func("55 8B EC 56 8B F1 8B 4D 08 E8 ? ? ? ? 0F B6 C0 89 46 0C", "?OnServerOnCalcRequestResult@CBattleRecordMan@@AAEXAAVCInPacket@@@Z")
CBattleRecordMan_OnDotDamageInfo_ea = find_first_aob_and_rename_func("55 8B EC 83 EC 10 56 8B F1 83 7E 08 00 0F 84", "CBattleRecordMan::OnDotDamageInfo")
CBattleRecordMan_OnPacket_ea = get_first_xref_and_rename_func(CBattleRecordMan_OnDotDamageInfo_ea, "CBattleRecordMan::OnPacket")

#  CBattleRecordMan::OnPacket  -----------------------------------------------------------------------------------------










#  CMapLoadable::OnPacket  ---------------------------------------------------------------------------------------------

CMapLoadable_SmoothVisible_ea = find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 0C 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 89 4D EC 8B 7D 08", "?OnSetMapTaggedObjectSmoothVisible@CMapLoadable@@IAEXAAVCInPacket@@@Z")
CMapLoadable_OnPacket_ea = get_first_xref_and_rename_func(CMapLoadable_SmoothVisible_ea, "CMapLoadable::OnPacket")

#  CMapLoadable::OnPacket  ---------------------------------------------------------------------------------------------










#  CStage::OnPacket  ---------------------------------------------------------------------------------------------------

sp_str_list = ["ETHANKS_FOR_PICKING_PINK_BEANN_R_NPINK_BEAN_HAS_A_FEW_RESTRICTIONS_WHICH_ARE_DIF",
               "FUI_UIWINDOW2IMG_UTILDLGEX_NOTICE",
               "YOU_CONNECTED_FROM_A_DIFFERENT_IP_THAN_LAST_TIME_R_NFOR_YOUR_CHARACTERS_SECURITY"]
CStage_OnSetField_ea = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CStage::OnSetField")
CStage_OnPacket_ea = get_first_xref_and_rename_func(CStage_OnSetField_ea, "CStage::OnPacket")

#  CStage::OnPacket  ---------------------------------------------------------------------------------------------------










#  CPersonalMapObjectPool::OnPacket  -----------------------------------------------------------------------------------

sp_str_list = ["YOUR_EXTRACTOR_WAS_BROUGHT_DOWN_BECAUSE_YOU_WANDERED_OFF",
          "YOUR_EXTRACTOR_WAS_BROUGHT_DOWN"]
CPersonalMapObj_Removed_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnDecomposerRemoved@CPersonalMapObjectPoolCli@@QAEXAAVCInPacket@@@Z")
CPersonalMapObj_OnPacket_EA = get_first_xref_and_rename_func(CPersonalMapObj_Removed_EA, "?OnPacket@CPersonalMapObjectPoolCli@@QAEXJAAVCInPacket@@@Z")

#  CPersonalMapObjectPool::OnPacket  -----------------------------------------------------------------------------------










#  CFieldAttackObjPool::OnPacket  --------------------------------------------------------------------------------------

find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 28 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 89 4D E8 8B 75 08", "CFieldAttackObjPool::OnObjCreatePacket")
find_first_aob_and_rename_func("55 8B EC 53 8B D9 8B 4D 08 57 E8 ? ? ? ? 8B 4D 08 8B F8 E8 ? ? ? ? 84 C0", "CFieldAttackObj::OnPushAct")


aob = "A8 99 A9 05"
aobList = ["68 65 78 2F 00",
           "68 85 9B 2F 00"]
CFieldAttackObj_SetAttack_EA = find_first_func_by_multiple_aob_and_rename(aob, aobList, "?SetAttack@CFieldAttackObj@@QAE_NH@Z")
CFieldAttackObjPool_OnObjPacket = BADADDR
i = 0
CField_SetAttack_Xrefs = ["?Fire@CAimBox2DMan@@QAEXXZ", "?OnSetAttack@CFieldAttackObj@@QAEXAAVCInPacket@@@Z"]
for xref in get_xrefs_addr(CFieldAttackObj_SetAttack_EA):
    rename_func(xref, CField_SetAttack_Xrefs[i])
    if "CFieldAttackObj" in CField_SetAttack_Xrefs[i]:
        CFieldAttackObjPool_OnObjPacket = get_first_xref_and_rename_func(get_func_start_addr_by_addr(xref), "?OnObjPacket@CFieldAttackObjPool@@QAEXJAAVCInPacket@@@Z")
    i += 1

#  CFieldAttackObjPool::OnPacket  --------------------------------------------------------------------------------------










#  CWvsContext::OnPacket  ----------------------------------------------------------------------------------------------

find_first_aob_and_rename_func("6a 00 6a 00 6a 00 6a 00 6a ff 6a 1d", "CWvsContext::OnEnterField")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 A1 ? ? ? ? 33 C5 89 45 F0 53 56 57 50 8D 45 F4 64 A3 ? ? ? ? 8B F9 8B 5D 08", "?OnWebBoardAuthkeyUpdate@CWvsContext@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 89 4D EC 8B 7D 08 8D 45 F0", "?OnWeatherEffectNotice@CWvsContext@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 51 53 56 8B 75 08 8B D9 57 8B CE E8 ? ? ? ? 0F B7 F8", "?OnUpdateUIEventListInfo@CWvsContext@@QAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 56 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 8D 45 EC", "CWvsContext::OnDressUpInfoModified")
find_first_aob_and_rename_func("55 8B EC 83 EC 08 53 56 8B D9 8B 4D 08 57 6A 08", "CWvsContext::OnUpdateMapleTVShowTime")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 08 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B D9 8B 75 08 8B CE E8 ? ? ? ? 8B F8", "CWvsContext::OnSetMazeArea")
find_first_aob_and_rename_func("55 8B EC 83 EC 1C A1 ? ? ? ? 33 C5 89 45 FC 53 56 8B 75 08 57", "CWvsContext::OnInappropriateBanMsg")
find_first_aob_and_rename_func("55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 0C 56 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B F1 83 BE ? ? ? ? ? 75 58", "CWvsContext::OnUnionAssignResult")


find_first_str_and_rename_first_xref_func("Something went wrong !!!!!", "CWvsContext::ShowNewYearCard")
find_first_str_and_rename_first_xref_func("Are you sure you want to use it?", "CWvsContext::SendConsumeCashItemUseRequest")
find_first_str_and_rename_first_xref_func("HACK", "CWvsContext::OnFakeGMNotice")
find_first_str_and_rename_first_xref_func("Unlock request Failed", "CWvsContext::SomeFunctionUnk")
find_first_str_and_rename_first_xref_func("hyper", "?OnResultInstanceTable@CWvsContext@@IAEXAAVCInPacket@@@Z")
find_first_str_and_rename_first_xref_func("[DEBUG MESSAGE]SID:%d, RET:%d", "CWvsContext::OnLinkSkillResult")
find_first_str_and_rename_first_xref_func("Success Create File RewardTestGachapon.txt", "CWvsContext::OnGachaponTestResult")
find_first_str_and_rename_first_xref_func("Success Create File RewardTestMasterPiece.csv", "CWvsContext::OnMasterPieceTestResult")
find_first_str_and_rename_first_xref_func("Success Create File RewardTestRoyalStyle.csv", "CWvsContext::OnRoyalStyleTestResult")
find_first_str_and_rename_first_xref_func("Success Create File RewardTestBeautyCoupon.csv", "CWvsContext::OnBeautyCouponTestResult")
find_first_str_and_rename_first_xref_func("Success Create File RewardTestLunaCrystal.csv", "CWvsContext::OnLunaCrystalTestResult")
find_first_str_and_rename_first_xref_func("Effect/PvPEff.img/CoreUpGrade%d", "CWvsContext::OnBattleStatCoreAck")
find_first_str_and_rename_first_xref_func("(DWORD)%u, (LONG)%d", "CWvsContext::OnGetServerTime")
find_first_str_and_rename_first_xref_func("[Memo_Load] [BlacklistNotLoading]", "CWvsContext::OnMemoResult")
find_first_str_and_rename_first_xref_func("[BlackListLoadDone] [BlackListType:%d] [BlackSize:%d] [sNames:%s]", "CWvsContext::OnBlackList")



    #  CWvsContext::OnAvatarPackTest -> CWvsContext::OnPacket
CWvsContext_OnAvatarPackTestEA = find_first_str_and_rename_first_xref_func("///////////////////////////////", "CWvsContext::OnAvatarPackTest")
CWvsContext_OnPacketEA = get_first_xref_and_rename_func(CWvsContext_OnAvatarPackTestEA, "CWvsContext::OnPacket")



sp_str_list = ["S_TRACE_ONLY_ALLOWS_TRANSFERS_ONTO_IDENTICAL_EQUIPMENT",
          "RARE",
          "EPIC",
          "UNIQUE",
          "LEGENDARY",
          "OBTAINED_S"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnInventoryOperation")

sp_str_list = ["SOUND_GAMEIMG_",
          "LEVELUP",
          "EFFECT_BASICEFFIMG_LEVELUP",
          "IF_YOU_DO_NOT_USE_YOUR_AP_WHEN_YOU_LEVEL_UP__R_NTHEN_YOU_WONT_BE_ABLE_TO_RECEIVE"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getStringW_EA, "CWvsContext::OnStatChanged")

sp_str_list = ["YOU_CANT_RAISE_OR_DROP_A_LEVEL_OF_FAME_ANYMORE_FOR_TODAY",
          "USERS_UNDER_LEVEL_D_ARE_UNABLE_TO_TOGGLE_WITH_FAME",
          "YOU_HAVE_RAISED_SS_LEVEL_OF_FAME",
          "YOU_HAVE_DROPPED_SS_LEVEL_OF_FAME",
          "YOU_CANT_RAISE_OR_DROP_A_LEVEL_OF_FAME_OF_THAT_CHARACTER_ANYMORE_FOR_THIS_MONTH"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnGivePopularityResult")

sp_str_list = ["THE_NEW_CORE_HAS_BEEN_MOVED_INTO_YOUR_CORE_STORAGE",
          "CORE_STORAGE_IS_FULL_MAKE_SOME_ROOM_AND_TRY_AGAIN",
          "FAILED_TO_ACQUIRE_CORE_DUE_TO_AN_UNKNOWN_ERROR",
          "CORE_WILL_BE_CONSUMED_S",
          "CORE_WILL_BE_CONSUMED_S_X_D"]
CoreMessage_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::CoreMessage")
CWvsContext_OnMessage_EA = get_first_xref_and_rename_func(CoreMessage_EA, "CWvsContext::OnMessage")

sp_str_list = ["S_IS_CURRENTLY_INCAPACITATED_ALL_PARTY_MEMBERS_MUST_BE_ALIVE_TO_ENTER",
          "S_HAS_NOT_COMPLETED_THE_A_WORLD_OF_MY_MAKING_QUEST_AND_CANNOT_ENTER",
          "S_NEEDS_TO_COME_TO_THE_ENTRY_FIELD_BEFORE_YOUR_PARTY_CAN_ENTER"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::EvolutionMessage")

sp_str_list = ["S_IS_CURRENTLY_DIFFICULT_TO_LOCATE_SO_R_NTHE_TELEPORT_WILL_NOT_TAKE_PLACE",
          "YOU_CANT_TELEPORT_BECAUSE__R_NTHE_TARGET_CANT_BE_FOUND",
          "THIS_MAP_IS_NOT_AVAILABLE_TO_ENTER_FOR_THE_LIST",
          "USERS_BELOW_LEVEL_7_ARE_NOT_ALLOWED__R_NTO_GO_OUT_FROM_MAPLE_ISLAND",
          "YOU_NEED_TO_MEET_THE_REQUIREMENTS_TO_ENTER_THIS_AREA_R_NTHIS_AREA_IS_CURRENTLY_N"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnMapTransferResult")

sp_str_list = ["YOUR_STORAGE_ROOM_SLOTS_HAVE_ALREADY_BEEN_EXPANDED",
          "YOUVE_INCREASED_YOUR_STORAGE_ROOM_SLOTS",
          "FAILED_TO_EXPAND_YOUR_STORAGE_ROOM_SLOTS"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnTrunkSlotIncResult")

sp_str_list = ["S_HAS_APPEARED_IN_S",
          "APPEARING_IN_S_S_HAS_BEEN_ELIMINATED",
          "MAPNAME"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnEliteMobWMI")

sp_str_list = ["THE_RANDOM_PORTAL_THAT_APPEARED_IN_S_HAS_DISAPPEARED_AGAIN",
          "A_RANDOM_PORTAL_HAS_BEEN_SPOTTED_IN_S_GET_IN_THERE_BEFORE_IT_DISAPPEARS",
          "MAPNAME"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnRandomPortalNotice")

sp_str_list = ["THERE_ARE_NO_HAIRSTYLES_SAVED_ON_THE_MANNEQUIN_R_NPLEASE_SAVE_ONE_ON_THE_MANNEQU",
          "THE_MANNEQUINS_HAIR_IS_THE_SAME_AS_YOUR_CURRENT_HAIR_R_NPLEASE_TRY_AGAIN",
          "YOU_CANNOT_APPLY_HAIR_ON_THE_OPPOSITE_GENDER_R_NPLEASE_CHECK_AND_TRY_AGAIN",
          "THIS_HAIR_IS_ALREADY_ON_THE_MANNEQUIN_R_NPLEASE_CHECK_AND_TRY_AGAIN",
          "PLEASE_CHECK_TO_SEE_IF_YOU_ARE_AN_ANGELIC_BUSTER_AND_TRY_AGAIN",
          "THE_REQUEST_WAS_DENIED_DUE_TO_AN_UNKNOWN_ERROR"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnMannequinResult")

sp_str_list = ["USING_BOMB_LIE_DETECTOR_ON_FIELD_S_IN_ALL_CHANNELS",
          "USING_BOMB_LIE_DETECTOR_ON_FIELD_S_IN_CHANNEL__2D_",
          "YOU_CANT_USE_THE_BOMB_LIE_DETECTOR_ON_FIELD_S",
          "MAPNAME"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnAntiMacroBombResult")

sp_str_list = ["YOU_HAVE_SUCCESSFULLY_REGISTERED",
          "THE_REPORT_HAS_BEEN_SUCCESSFULLY_MADE_R_NYOU_MAY_NOT_REPORT_FOR_THE_REST_OF_THE_",
          "REPORT_SUCCESSFUL__R_NYOU_CAN_MAKE_D_MORE_REPORTS_TODAY",
          "YOU_MAY_ONLY_REPORT_FROM_D_TO_D"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnClaimResult")

sp_str_list = ["YOUR_STORE_IS_CURRENTLY_OPEN_R_NIN_CHANNEL_S_FREE_MARKET_D_R_NPLEASE_USE_THIS_AF",
          "RENAMING_SUCCESSFUL",
          "RENAMING_FAILED___CANT_FIND_THE_HIRED_MERCHANT",
          "THE_STORE_IS_OPEN_AT_CHANNEL_S_R_NWOULD_YOU_LIKE_TO_CHANGE_TO_THAT_CHANNEL"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnEntrustedShopCheckResult")

sp_str_list = ["THE_BOOK_OF_MASTERY_GLOWS_BRIGHTLY_AND_THE_CURRENT_SKILLS_HAVE_GONE_THROUGH_AN_U",
          "THE_SKILL_BOOK_GLOWS_BRIGHTLY_AND_NEW_SKILLS_HAVE_NOW_BEEN_ADDED",
          "DESPITE_USING_S_THE_EFFECT_WAS_NOWHERE_TO_BE_FOUND",
          "MASTERY_BOOK",
          "SKILL_BOOK",
          "YOU_CANNOT_USE_S"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnSkillLearnItemResult@CWvsContext@@QAEXAAVCInPacket@@@Z")

sp_str_list = ["THE_SP_RESET_SCROLL_HAS_RESET_YOUR_SP",
          "SP_RESET_SCROLL_FAILED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnSkillResetItemResult")

sp_str_list = ["FAILED_TO_USE_AP_RESET_SCROLL",
          "YOUVE_USED_THE_AP_RESET_SCROLL_TO_R_NRESET_ALL_OF_YOUR_AP"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnAbilityResetItemResult")

sp_str_list = ["YOU_DONT_HAVE_ENOUGH_SLOTS",
          "ITEM_WAS_DISTRIBUTED_SUCCESSFULLY"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnExpItemGetResult")

sp_str_list = ["YOUVE_INCREASED_YOUR_NUMBER_OF_CHARACTER_SLOTS",
          "ITEMS_THAT_EXPAND_THE_NUMBER_OF_CHARACTER_SLOTS_CANNOT_BE_USED_REPEATEDLY_R_NTRY",
          "YOUR_CHARACTERS_SLOTS_HAVE_ALREADY_BEEN_EXPANDED",
          "FAILED_TO_EXPAND_CHARACTER_SLOTS",
          "CHARACTER_SLOT_COUPONS_CANNOT_BE_USED_IMMEDIATELY_FOLLOWING_ONE_ANOTHER_PLEASE_W"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnCharSlotIncResult")

sp_str_list = ["A_BEGINNER_CANT_CREATE_A_PARTY",
          "ALREADY_HAVE_JOINED_A_PARTY",
          "YOU_HAVE_CREATED_A_NEW_DUO_PARTY",
          "YOU_HAVE_CREATED_A_NEW_PARTY",
          "YOU_HAVE_BEEN_EXPELLED_FROM_THE_PARTY",
          "YOU_HAVE_LEFT_THE_PARTY",
          "YOU_HAVE_QUIT_AS_THE_LEADER_OF_THE_PARTY_THE_PARTY_HAS_BEEN_DISBANDED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnPartyResult")

sp_str_list = ["YOUR_QUEUE_REQUEST_HAS_EXPIRED_PLEASE_MAKE_ANOTHER_REQUEST",
          "YOUR_REQUEST_FOR_THE_URSUS_RAID_IS_BEING_PROCESSED",
          "YOU_HAVE_BEEN_KICKED_FROM_THE_URSUS_RAID",
          "S_HAS_BEEN_INVITED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnUrsusPartyResult")

sp_str_list = ["YOU_CANNOT_ADD_S_AS_A_FRIEND_NOW_BECAUSE_S_IS_CURRENTLY_DISCONNECTED_FROM_STAR_P",
          "BUDDY_REQUEST_SUCCESSFULLY_SENT_TO_S",
          "_FRIEND__S_HAS_LOGGED_IN",
          "S_HAS_DECLINED_THE_FRIEND_REQUEST",
          "S_IS_CURRENTLY_NOT_ACCEPTING_ANY_FRIENDS",
          "S_HAS_DECLINED_THE_BESTIE_REQUEST",
          "YOU_CANNOT_ADD_S_AS_A_FRIEND_NOW_BECAUSE_S_IS_CURRENTLY_DISCONNECTED_FROM_STAR_P"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnFriendResult")

sp_str_list = ["S_IS_REQUESTING_HELP__R_NWILL_YOU_ACCEPT",
          "S_USED_SUMMON_GUILD_MEMBERS__R_NWILL_YOU_ACCEPT"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnGuildRequest")

sp_str_list = ["CONGRATULATION__S_GUILD_HAS_BEEN_REGISTERED_TO_OUR_GUILD_OFFICE__I_WISH_THE_BEST",
          "ALREADY_JOINED_THE_GUILD",
          "SOMEBODY_HAS_DISAGREED_TO_FORM_A_GUILD__PLEASE_COME_BACK_TO_ME_WHEN_YOU_MEET_WIT",
          "THE_PROBLEM_HAS_HAPPENED_DURING_THE_PROCESS_OF_FORMING_THE_GUILD_PLEASE_TRY_AGAI",
          "THE_GUILD_YOU_ARE_TRYING_TO_JOIN_HAS_ALREADY_REACHED_THE_MAX_NUMBER_OF_USERS",
          "THE_GUILD_YOU_ARE_TRYING_TO_JOIN_HAS_ALREADY_REACHED_THE_MAX_NUMBER_OF_REQUESTS",
          "UNABLE_TO_REQUEST_TO_JOIN_A_GUILD_YOU_CAN_DO_SO_AFTER_THE_RESTRICTION_PERIOD_END",
          "YOU_CANCELED_YOUR_GUILD_REQUEST__R_N_YOU_CANNOT_REQUEST_TO_JOIN_A_NEW_GUILD_FOR_"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnGuildResult")

sp_str_list = ["S_ALLIANCE_HAS_FORMED",
          "YOU_HAVE_BEEN_KICKED_FROM_THE_ALLIANCE",
          "YOU_HAVE_LEFT_THE_ALLIANCE",
          "UNABLE_TO_LEAVE_THE_ALLIANCE_PLEASE_CHECK_WHO_THE_ALLIANCE_LEADER_IS"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnAllianceResult")

sp_str_list = ["YOU_HAVE_GAINED_AN_ITEM_IN_THE_S_TAB_S",
          "YOU_HAVE_GAINED_ITEMS_IN_THE_S_TAB_S_D",
          "YOUR_INVENTORY_IS_FULL_R_NPLEASE_TRY_AGAIN_LATER"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnIncubatorResult")

sp_str_list = ["NO_ITEMS_MEET_YOUR_STAT_SEARCH_PARAMETERS_THE_RESULTS_ARE_THE_SAME_AS_THE_OWL_OF",
          "UNABLE_TO_FIND_THE_ITEM_R_NYOU_HAVE_ENTERED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnShopScannerResult")

find_first_func_and_rename_by_a_sp_and_string("S_HAS_REQUESTED_ENGAGEMENT_R_NWILL_YOU_ACCEPT_THIS_PROPOSAL", getString_EA, "CWvsContext::OnMarriageRequest")

sp_str_list = ["YOUR_ENGAGEMENT_HAS_BEEN_BROKEN",
          "YOU_ARE_NOW_MARRIED",
          "YOU_ARE_NO_LONGER_MARRIED",
          "YOU_ARE_NOW_ENGAGED",
          "CONGRATULATIONS_R_NYOUR_RESERVATION_WAS_SUCCESSFULLY_MADE",
          "UNFORTUNATELY_THE_MAN_WHO_PROPOSED_TO_YOU_HAS_WITHDRAWN_HIS_REQUEST_FOR_AN_ENGAG"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnMarriageResult")

sp_str_list = ["YOUR_PET_CANNOT_CONSUME_THIS_FOOD_R_NPLEASE_CHECK_AGAIN",
          "EATTA",
          "EAT"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnCashPetFoodResult")

sp_str_list = ["ELEMENT_ROCK_CANNOT_BE_USED_RIGHT_AFTER_USAGE",
          "UNABLE_TO_CAPTURE_THE_MONSTER_THE_MONSTER_IS_TOO_STRONG"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnBridleMobCatchFail")

sp_str_list = ["A_NEW_TAB_IS_AVAILABLE_IN_THE_CRUSADER_CODEX",
          "SKULL_TAB_IS_OPEN",
          "_S__HAS_BEEN_SUCCESSFULLY_RECORDED_ON_THE_MONSTER_BOOK",
          "YOU_CANNOT_OBTAIN_THIS_ITEM_THE_CARD_IS_ALREADY_IN_YOUR_CRUSADER_CODEX"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnMonsterBookSetChanged")

sp_str_list = ["AN_EQUIPPED_ITEM_GRANTING_YOU_AN_CEXP_BONUS_EFFECT_HAS_BEEN_EQUIPPED_FOR_D_HRS_Y",
          "AN_EQUIPPED_ITEM_IS_GRANTING_YOU_AN_CEXP_BONUS_EFFECT_AND_YOU_HAVE_RECEIVED_AN_A"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnBonusExpRateChanged")

sp_str_list = ["S_S_HAS_REACHED_LV_D",
          "FAMILY",
          "GUILD"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnNotifyLevelUp")

sp_str_list = ["SS_IS_NOW_MARRIED_PLEASE_CONGRATULATE_THEM",
          "FAMILY",
          "GUILD"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnNotifyWedding")

sp_str_list = ["S_S_HAS_ADVANCED_TO_S",
          "FAMILY",
          "GUILD"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnNotifyJobChange")

find_first_func_and_rename_by_a_sp_and_string("S_HAS_REQUESTED_TO_FOLLOW_YOU_R_NWOULD_YOU_LIKE_TO_ACCEPT", getString_EA, "CWvsContext::OnSetPassengerRequest")

find_first_func_and_rename_by_a_sp_and_string("_S__INCREASED_CLOSENESS_WITHOUT_USING_ANY_CLOSENESS_INCREASE_COUNT__REMAINING_CO", getString_EA, "CWvsContext::OnItemPotChange")

sp_str_list = ["S_ALREADY_RECEIVED_FROM_S_THE_S_SKILL",
          "S_RECEIVED_S",
          "THIS_REQUEST_HAS_FAILED_DUE_TO_AN_UNKNOWN_ERROR",
          "S_CANT_LEARN_ANY_MORE_SKILLS",
          "FAILED_TO_LINK_SKILL_PLEASE_TRY_AGAIN_LATER"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnSetSonOfLinkedSkillResult")

sp_str_list = ["THE_S_COULD_NOT_FIT_IN_YOUR_USE_TAB_CLICK_ON_THE_QUEST_NOTIFIER_TO_RECEIVE_IT_YO",
          "S_XD_RECEIVED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnCharacterHonorGift")

sp_str_list = ["YOU_CANNOT_PURCHASE_AN_ITEM_AT_THE_MOMENT",
          "THE_REQUESTED_ITEM_PURCHASE_HAS_BEEN_COMPLETED",
          "YOU_DO_NOT_HAVE_ENOUGH_SPACE_IN_YOUR_INVENTORY",
          "YOU_CAN_NO_LONGER_PURCHASE_THIS_ITEM",
          "YOU_NEED_MORE_ITEMS"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnCrossHunterShopResult")

sp_str_list = ["YOU_NEED_AN_AUTO_PILOT_PERMIT_TO_DO_THAT",
          "THIS_MOUNT_DOESNT_SUPPORT_AUTO_PILOT",
          "YOU_ARE_NOT_RIDING_A_MOUNT",
          "FAILED_TO_PAY_THE_AUTO_PILOT_COSTS_DUE_TO__R_NAN_ERROR_CHECK_YOUR_MESOS_AND_TRY_",
          "AUTO_PILOT_FAILED_FOR_AN_UNKNOWN_REASON__R_NPLEASE_TRY_AGAIN_LATER"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnNaviFlyingResult")

sp_str_list = ["YOU_CANNOT_PARTICIPATE_IN_THE_EVENT",
          "THE_EVENT_IS_NOT_RUNNING_RIGHT_NOW"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnBingoResult")

sp_str_list = ["YOUR_HYPER_SP_HAS_FAILED_TO_RESET",
          "YOUR_HYPER_SP_HAS_BEEN_RESET"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnHyperSkillResetResult")

EvolvingCoreFailed_EA = find_first_func_and_rename_by_a_sp_and_string("FAILED_TO_MOVE_CORE_PLEASE_TRY_AGAIN", getString_EA, "CWvsContext::EvolvingResultCoreFailed")
CWvsContext_OnEvolvingResult_EA = get_first_xref_and_rename_func(EvolvingCoreFailed_EA, "CWvsContext::OnEvolvingResult")

sp_str_list = ["YOU_CAN_USE_YOUR_MENTORING_TELEPORT_EVERY_D_MINUTES_TRY_AGAIN_AFTER_D_MINUTES",
          "YOU_CANT_TELEPORT_THERE_BECAUSE_YOU_DONT_MEET_THE_LEVEL_REQUIREMENTS",
          "YOU_COULD_NOT_TELEPORT_TO_THE_SELECTED_AREA_YOU_HAVE_NOT_COMPLETED_THE_RELEVANT_"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnMentoring")

sp_str_list = ["THE_WAITING_LINE_IS_LONGER_THAN_15_SECONDS__R_NPLEASE_TRY_USING_IT_AT_A_LATER_TI",
          "THIS_MEGAPHONE_IS_ONLY_AVAILABLE_FOR__R_NCHARACTERS_THAT_ARE_OVER_LEVEL_10"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnAvatarMegaphoneRes")

sp_str_list = ["MULTIPLE_AUTOGRAPHS_FROM_CHARACTERS_ON_THE_SAME_ACCOUNT_IS_NOT_ALLOWED",
          "YOU_CANT_GET_AUTOGRAPHS_FROM_PLAYERS_WHO_DONT_HAVE_THEIR_MAPLE_FRIEND_INFO_LISTE",
          "S_HAS_GIVEN_AS_MANY_AUTOGRAPHS_AS_THEY_CAN_FOR_THE_DAY",
          "YOUVE_REACHED_YOUR_DAILY_AUTOGRAPH_LIMIT"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnSignErrorAck")

sp_str_list = ["MULTIPLE_GREETING_EXCHANGES_WITH_CHARACTERS_ON_THE_SAME_ACCOUNT_IS_NOT_ALLOWED",
          "GREETINGS_CANNOT_BE_EXCHANGED_BETWEEN_USERS_WITHOUT_FAMILY_NAME_TAGS",
          "YOUVE_REACHED_YOUR_DAILY_GREETING_LIMIT",
          "INVALID_GREETING",
          "YOU_CAN_ONLY_GREET_CHARACTERS_THAT_COMPLETED_THE_S_QUEST"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnAskAfterErrorAck")

sp_str_list = ["YOU_HAVE_GAINED_AN_ITEM_IN_THE_S_TAB_S",
          "WERE_PASSING_OUT_EQUIPMENT_BOXES_FOR_OPEN_JOB_ADVANCEMENTS_R_N_BUT_YOU_DONT_HAVE",
          "OPEN_JOB_ADVANCEMENT_IS_AVAILABLE__R_NONLY_ONCE_PER_DAY_R_N_PLEASE_COME_BACK_AFT",
          "REMOVED_FROM_SLOT_BECAUSE_SAME_JOB_EXISTS_R_NON_THE_CHARACTER_CARD"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnJobFreeChangeResult")

sp_str_list = ["WRONG_ITEM_PLEASE_CHECK_THE_BASE_AND_INGREDIENT_ITEMS",
          "IDENTICAL_ITEMS_CANNOT_BE_FUSED",
          "MAKE_SURE_YOU_HAVE_1_OR_MORE_OPEN_SLOTS_IN_YOUR_EQUIP_AND_CASH_TABS",
          "YOU_DO_NOT_HAVE_ENOUGH_EQUIP_OR_CASH_SLOTS_OPEN_TRY_AGAIN_AFTER_CLEARING_1_SLOT_",
          "YOU_CANT_USE_THE_LUNA_CRYSTAL_RIGHT_NOW_PLEASE_TRY_AGAIN_LATER",
          "YOU_CANNOT_USE_THE_MASTERPIECE_AT_THIS_TIME_PLEASE_USE_IT_AGAIN_LATER"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnMasterPieceReward")

find_first_func_and_rename_by_a_sp_and_string("BOSS_ARENA_MATCHMAKING_COMPLETE_YOU_WILL_BE_MOVED_IN_5_SECONDS", getString_EA, "CWvsContext::OnBossArenaMatchSuccess")

find_first_func_and_rename_by_a_sp_and_string("BOSS_ARENA_MATCHMAKING_FAILED_TRY_AGAIN", getString_EA, "CWvsContext::OnBossArenaMatchFail")

sp_str_list = ["YOU_HAVE_BEEN_PLACED_IN_THE_BOSS_ARENA_QUEUE_BOSS_ARENA_MATCHMAKING_WILL_BE_CANC",
          "BOSS_ARENA_MATCHMAKING_HAS_BEEN_CANCELED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnBossArenaMatchRequestDone")

sp_str_list = ["ONLY_THE_ACCOUNT_OWNER_CAN_REQUEST_THE_WORLD_TRANSFER",
          "THE_PASSAGEWAY_BETWEEN_WORLDS_HAS_CLOSED_YOU_CANNOT_TRANSFER_WORLDS_FOR_NOW",
          "YOU_DO_NOT_HAVE_ENOUGH_CHARACTER_SLOTS_IN_THE_WORLD_YOU_ARE_TRYING_TO_TRANSFER_T",
          "UNABLE_TO_TRANSFER_TO_THIS_WORLD_PLEASE_TRY_TRANSFERRING_TO_A_DIFFERENT_WORLD",
          "YOU_CAN_TRANSFER_WORLDS_WHEN_YOUR_CHARACTERS_LEVEL_IS_33_OR_ABOVE_PLEASE_TRY_AGA"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnWorldTransferResult")

sp_str_list = ["HYPER_STAT_RESET_FAILED_TRY_AGAIN_A_LITTLE_LATER",
          "THE_HYPER_STAT_HAS_BEEN_RESET"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnReceiveHyperStatSkillResetResult")

sp_str_list = ["YOU_HAVE_OBTAINED_A_COLLECTION_COMPLETION_REWARD_R_N_PLEASE_CHECK_YOUR_INVENTORY",
          "UNABLE_TO_CLAIM_THE_REWARD__R_NYOU_MUST_COMPLETE_YOUR_COLLECTION_FIRST",
          "AN_ERROR_OCCURRED_UNABLE_TO_CLAIM_REWARDS_PLEASE_TRY_AGAIN",
          "YOU_HAVE_ALREADY_CLAIMED_THIS_REWARD",
          "UNABLE_TO_CLAIM_THE_REWARD__R_NYOU_MUST_COMPLETE_YOUR_COLLECTION_FIRST",
          "UNABLE_TO_CLAIM_REWARD__R_NPLEASE_TRY_AGAIN_IN_A_MOMENT",
          "AN_EXPLORATION_BOX_HAS_BEEN_AWARDED_R_N_PLEASE_CHECK_YOUR_INVENTORY"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnMonsterCollectionResult")

sp_str_list = ["TRY_AGAIN_LATER",
          "THE_ITEM_HAS_BEEN_RECYCLED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnRecycleItem")

sp_str_list = ["THE_EFFECT_IS_TAKING_PLACE_RIGHT_NOW_R_NPLEASE_TRY_IT_LATER",
          "PLEASE_EMPTY_D_SLOTS_IN_YOUR_CASH_TAB_AND_TRY_AGAIN",
          "AN_ERROR_OCCURRED_PLEASE_TRY_AGAIN_LATER"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::Unk203_430")

sp_str_list = ["THE_S_SKILL_NODE_HAS_LOST_THE_POWER_OF_ERDA_AND_VANISHED",
          "THE_S_BOOST_NODE_HAS_LOST_THE_POWER_OF_ERDA_AND_VANISHED",
          "THE_S_SPECIAL_NODE_HAS_LOST_THE_POWER_OF_ERDA_AND_VANISHED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnNodeVanished")

find_first_func_and_rename_by_a_sp_and_string("COMBAT_POWER_S", getString_EA, "CWvsContext::OnCombatPowerMsg")

sp_str_list = ["EXPIRATION_DATE_OF_YOUR_POTION_POT_HAS_BEEN_EXTENDED_TO_S",
          "THE_POTION_POT_HAS_D_OF_HP_SAVED_IN_IT",
          "THE_POTION_POT_HAS_D_OF_MP_SAVED_IN_IT",
          "YOUR_POTION_POTS_MAXIMUM_CAPACITY_HAS_BEEN_INCREASED_TO_D",
          "YOUR_POTION_POTS_CAPACITY_HAS_ALREADY_REACHED_ITS_MAX_LIMIT"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnPotionPotResult")

sp_str_list = ["YOU_MAY_ONLY_REPORT_USERS_10_TIMES_A_DAY",
          "YOU_HAVE_SUCCESSFULLY_REPORTED_THE_USER",
          "UNABLE_TO_LOCATE_THE_USER",
          "YOU_HAVE_BEEN_REPORTED_TO_THE_GMS_BY_A_USER",
          "YOUR_REQUEST_DID_NOT_GO_THROUGH_FOR_UNKNOWN_REASONS_PLEASE_TRY_AGAIN_LATER"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnUserReportResult")

find_first_func_and_rename_by_a_sp_and_string("YOU_DONT_HAVE_SPACE_FOR_A_MEDAL_CLEAR_AT_LEAST_1_SLOT_IN_YOUR_EQUIPMENT_INVENTOR", getString_EA, "CWvsContext::OnCrusaderMedalFail")

find_first_func_and_rename_by_a_sp_and_string("FOR_EQUIPPING_S_POTIONS_WILL_BE_DISCOUNTED_D_WHEN_YOU_VISIT_STORE", getString_EA, "CWvsContext::OnReducePotionsPriceByEquip")

sp_str_list = ["YOUR_SHOP_WAS_CLOSED_AT_THE_DISCRETION_OF_A_GM_PLEASE_SEE_FRED_TO_RECOVER_YOUR_I", "AN_UNKNOWN_ERROR_HAS_OCCURED"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnPersonalShopShutdownByGMMsg")

sp_str_list = ["YOU_HAVE_BEEN_BLOCKED_BY_BPOLICE_S_FOR_THE_S_REASONK", "YOU_HAVE_BEEN_DISCONNECTED_BY_BPOLICE_S_FOR_THE_S_REASONK"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnHackBlockMsg")

sp_str_list = ["SUCCESSFULLY_RECEIVED_A_NEW_YEAR_CARD", "SUCCESSFULLY_DELETED_A_NEW_YEAR_CARD", "THE_REQUEST_IS_DENIED_FOR_AN_UNKNOWN_REASON",
          "SUCCESSFULLY_SENT_A_NEW_YEAR_CARD_R_N_TO_S", "YOU_CANNOT_SEND_A_CARD_TO_YOURSELF_", "YOU_HAVE_NO_FREE_SLOT_TO_STORE_CARD_R_NTRY_LATER_ON_PLEASE",
          "YOU_HAVE_NO_CARD_TO_SEND", "WRONG_INVENTORY_INFORMATION_", "CANNOT_FIND_SUCH_CHARACTER_",
          "INCOHERENT_DATA_", "AN_ERROR_OCCURED_DURING_DB_OPERATION", "AN_UNKNOWN_ERROR_OCCURED_"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnNewYearCardSendResult")

sp_str_list = ["THIS_IS_NOT_A_BLESSING_OF_GREATNESS_ITEM",
          "THIS_IS_NOT_A_SYMBOL_OF_ADORATION_ITEM",
          "YOU_SENT_A_BLESSING_OF_GREATNESS__R_NTO_YOUR_FRIEND",
          "THE_REQUEST_IS_DENIED_FOR_AN_UNKNOWN_REASON",
          "YOU_CANNOT_SEND_A_BLESSING_OF_GREATNESS_TO_THE_SAME_FRIEND"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnGiftBlessingResult")

sp_str_list = ["S_IS_CURRENTLY__R_NNOT_ACCEPTING_YOUR_CREW_INVITATION_MESSAGE",
          "S_IS_TAKING_CARE_OF_ANOTHER_INVITATION",
          "S_HAS_DENIED__R_NYOUR_CREW_INVITATION",
          "YOUR_S_ITEM_INVENTORY_IS_FULL"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnCrewResult")

sp_str_list = ["YOU_CAN_USE_RANDOM_TRANSFORM_POTION_ONLY_IN_THE_TOWN",
          "FAILED_TO_FIND_USER_S"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnTransformPotionResult")

sp_str_list = ["THE_REQUESTER_IS_NOW_IN_A_PLACE_WHERE_THEY_CANNOT_BE_SUMMONED",
          "FAILED_TO_USE_THE_SKILL_DUE_TO_AN_UNKNOWN_REASON",
          "SOME_PARTY_MEMBERS_ARE_UNABLE_TO_RESPOND_TO_THE_SUMMONING",
          "THE_SUMMONING_SKILL_CANNOT_BE_USED_HERE",
          "FAILED_TO_USE_THE_SKILL_DUE_TO_AN_UNKNOWN_REASON",
          "FROM_S_S_HAS_REQUESTED_A_SUMMONS_WOULD_YOU_LIKE_TO_TRANSPORT"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnSummonSkillResult")

sp_str_list = ["YOU_CANNOT_MOVE_WHILE_MAGIC_WHEEL_WINDOW_IS_OPEN",
          "FAILED_TO_RECEIVE_MAGIC_GACHAPON_WHEEL_ITEM",
          "FAILED_TO_DELETE_MAGIC_GACHAPON_WHEEL_ITEM"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnMagicGachaponWheelMsg")

sp_str_list = ["YOU_HAVE_RECEIVED_THE_MAPLE_POINTS_R_N_D_MAPLE_POINT_",
          "YOU_HAVE_RECEIVED_THE_MESOS_R_N_D_MESO_",
          "YOU_HAVE_RECEIVED_THE_EXP_R_N_D_EXP_"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnSomeReward")

sp_str_list = ["YOUR_PARTNER_IS_LOGGED_OUT__R_NOR_IS_ON_A_DIFFERENT_CHANNEL",
          "YOU_MAY_NOT_USE_THE_SKILL_YET",
          "CANNOT_BE_USED_HERE",
          "YOU_CANNOT_USE_IT_IN_YOUR_CURRENT_STATE",
          "AN_UNKNOWN_ERROR_HAS_OCCURRED_R_NPLEASE_TRY_AGAIN_LATER"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnUnk203_500")

sp_str_list = ["YOU_CANNOT_RECEIVE_THE_ITEM_BECAUSE_YOU_DO_NOT_HAVE_ENOUGH_SPACE_IN_YOUR_S_TAB_P",
               "WE_COULDNT_GIVE_YOU_ALL_THE_MESOS_YOU_WERE_OWED__R_NYOU_MAY_HAVE_TOO_MANY_MESOS_"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnShowSlotMessage")

sp_str_list = ["YOU_COULD_NOT_TELEPORT_TO_THE_SELECTED_AREA_YOU_HAVE_NOT_COMPLETED_THE_RELEVANT_",
               "YOU_CANT_TELEPORT_THERE_BECAUSE_YOU_DONT_MEET_THE_LEVEL_REQUIREMENTS",
               "YOU_CAN_USE_YOUR_MENTORING_TELEPORT_EVERY_D_MINUTES_TRY_AGAIN_AFTER_D_MINUTES",
               "S_IS_ALREADY_ON_THE_SAME_MAP", "PROTEGE", "MENTOR"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "CWvsContext::OnMentor")



sp_str_list = ["YOU_CAN_ENTER_UP_TO_24_CHARACTERS", "ALSO_CAN_BE_ENTERED_WITH_THE__R_NCOMMAND__ELECTRONIC_DISPLAY"]
rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.makes_call_to(decode4_EA)),
                get_all_functions_containing_sp_str_list(sp_str_list))),
    "CWvsContext::OnTextEquipUIOpen")

sp_str_list = ["THERE_ARENT_ANY_TARGETS_TO_STEAL_FROM",
               "FAILED_TO_STEAL_DUE_TO_AN_UNKNOWN_REASON"]
rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.makes_call_to(decode4_EA)),
                get_all_functions_containing_sp_str_list(sp_str_list))),
    "?OnChangeStealMemoryResult@CWvsContext@@QAEXAAVCInPacket@@@Z")

sp_str = "PLAYERS_THAT_ARE_LEVEL_15_AND_BELOW__R_NMAY_ONLY_TRADE_1_MILLION_MESOS_PER_DAY__"
rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.makes_call_to(decode1_EA)
            and x.makes_call_to(CWvsContext_SetExclRequestSent_EA)),
                get_all_functions_containing_sp_str(sp_str))),
    "CWvsContext::OnTradeMesoLimitReached")

#  CWvsContext::OnPacket  ----------------------------------------------------------------------------------------------










#  CUIHandler::OnPacket  -----------------------------------------------------------------------------------------------

#  CUIHandler::OnPacket  -----------------------------------------------------------------------------------------------










#  CCashShop::OnPacket  ------------------------------------------------------------------------------------------------

find_first_str_and_rename_first_xref_func("GM can not transfer worlds.", "CCashShop::CheckTransferWorldPossible")
find_first_str_and_rename_first_xref_func("Please visit the website to charge your account.", "CCashShop::OnStatusCharge")



CCashShop_OnUnk_EA = find_first_func_and_rename_by_a_sp_and_string("YOU_GOT_D_REWARD_POINTS", getString_EA, "CCashShop::OnUnk")
CCashShop_OnPacket_EA = get_first_xref_and_rename_func(CCashShop_OnUnk_EA, "?OnPacket@CCashShop@@UAEXJAAVCInPacket@@@Z")

CCashShop_OnUnk2_EA = find_first_func_and_rename_by_a_sp_and_string("YOU_SENT_S_THE_FOLLOWING_GIFT_S", getString_EA, "?OnUnk2@CCashShop@@IAEXAAVCInPacket@@@Z")
CCashShop_OnPacket_EA_2 = get_first_xref_and_rename_func(CCashShop_OnUnk2_EA, "?OnPacket@CCashShop@@UAEXJAAVCInPacket@@@Z")

#  CCashShop::OnPacket  ------------------------------------------------------------------------------------------------










#  CLogin::OnPacket  ---------------------------------------------------------------------------------------------------

CLogin_OnCheckPWResult_EA = find_first_str_and_rename_first_xref_func("Your account was locked after five invalid password or pin code attempts.\r\n%s", "CLogin::OnCheckPasswordResult")
CLogin_OnPacket_EA = get_first_xref_and_rename_func(CLogin_OnCheckPWResult_EA, "?OnPacket@CLogin@@UAEXJAAVCInPacket@@@Z")

#  CLogin::OnPacket  ---------------------------------------------------------------------------------------------------










#  CFieldGiantBoss::OnPacket  ------------------------------------------------------------------------------------------


    #  CFieldGiantBoss::OnChangePortalState -> OnOpenPortal -> OnPacket
CField_GiantBoss_ChangePortalState = find_first_str_and_rename_first_xref_func("Map/DynamicObjectStateEffect.img", "CField_GiantBoss::ChangePortalState")
CField_GiantBoss_OnPortalOpen = get_first_xref_and_rename_func(CField_GiantBoss_ChangePortalState, "CField_GiantBoss::OnPortalOpen")
CField_GiantBoss_OnPacket = get_first_xref_and_rename_func(CField_GiantBoss_OnPortalOpen, "CField_GiantBoss::OnPacket")

#  CFieldGiantBoss::OnPacket  ------------------------------------------------------------------------------------------










#  CBossLucid::OnPacket1 & 2  ------------------------------------------------------------------------------------------


    # CBossLucid::LaserRainAction -> CBossLucid::OnSkill -> CBossLucid::OnPacket_1
CBossLucid_LaserRainAction_EA = find_first_str_and_rename_first_xref_func("Etc/BossLucid.img/LaserRain/action", "CBossLucid::LaserRainAction")
CBossLucid_OnSkill_EA = get_first_xref_and_rename_func(CBossLucid_LaserRainAction_EA, "CBossLucid::OnDoSkill")

CBossLucid_CreateDragon_EA = find_first_str_and_rename_first_xref_func("Etc/BossLucid.img/Dragon/phase%d", "CBossLucid::CreateDragon")
CBossLucid_DragonCreated_EA = get_first_xref_and_rename_func(CBossLucid_CreateDragon_EA, "CBossLucid::OnDragonCreated")
CBossLucid_OnPacket1 = get_xrefs_addr(CBossLucid_DragonCreated_EA)[0]
rename_func(CBossLucid_OnPacket1, "CBossLucid::OnPacket1")
CBossLucid_OnPacket2 = get_xrefs_addr(CBossLucid_DragonCreated_EA)[1]
rename_func(CBossLucid_OnPacket2, "CBossLucid::OnPacket2")

#  CBossLucid::OnPacket1 & 2  ------------------------------------------------------------------------------------------









#  CBossWill::OnPacket  ------------------------------------------------------------------------------------------------


    #  CBossWill::TriggerBlock -> CBossWill::OnPacket
CBossWill_TriggerBlockEA = find_first_str_and_rename_first_xref_func("UI/UIWindow7.img/boss_will_hp/triggerBlock/phase1", "CBossWill::TriggerBlock")
CBossWill_TriggerBlock_1EA = get_first_xref_and_rename_func(CBossWill_TriggerBlockEA, "CBossWill::TriggerBlock_1")
CBossWill_OnTriggerBlockEA = get_first_xref_and_rename_func(CBossWill_TriggerBlock_1EA, "CBossWill::OnTriggerBlock")
CBossWill_OnPacket1EA = get_first_xref_and_rename_func(CBossWill_OnTriggerBlockEA, "CBossWill::OnPacket1")

#  CBossWill::OnPacket  ------------------------------------------------------------------------------------------------










#  Miscellaneous  ------------------------------------------------------------------------------------------------------

find_first_aob_and_rename_func("E8 ? ? ? ? EB 33 85 D2", "CLegion::Decode")
find_first_aob_and_rename_func("57 E8 ? ? ? ? 83 C4 08 8B CF", "?RawDecode@GW_ItemSlotEquip@@MAEXAAVCInPacket@@@Z")
find_first_aob_and_rename_func("83 FF 13 74 ? 83 FF 4B 74 ? 83 FF 6C", "?OnMovePacket@CMovePath@@QAEXAAVCInPacket@@HJ@Z")
find_first_aob_and_rename_func("FF 73 4C 8D 7B 48", "?Encode@CMovePath@@QAEXAAVCOutPacket@@PAH_N@Z")

find_first_str_and_rename_first_xref_func("Delivered", "?Decode@CharacterData@@QAE_KAAVCInPacket@@H@Z")
find_first_str_and_rename_first_xref_func("SOFTWARE\\Microsoft\\Windows\\CurrentVersion", "?Init@CSystemInfo@@QAEXXZ")
find_first_str_and_rename_first_xref_func("DBGHELP.DLL", "ZExceptionHandler::InitDbgHelpFunctions")
find_first_str_and_rename_first_xref_func("battleFieldTeam", "Field::BattlefieldTeamCond::Parse")
find_first_str_and_rename_first_xref_func("jobCategory", "Field::JobCategoryCond::Parse")
find_first_str_and_rename_first_xref_func("CharacterName(%s), WorldID(%d), Ch(%d)", "CWvsApp::ZtlExceptionHandler")
find_first_str_and_rename_first_xref_func("Unknown error 0x%0lX", "com_error::ErrorMessage")
find_first_str_and_rename_first_xref_func("%d (MAX)", "CUIToolTip::SetToolTip_Equip")
find_first_str_and_rename_first_xref_func("http://maplestory.nexon.net", "CClientSocket::GetGuestIDRegistrationURL")
find_first_str_and_rename_first_xref_func("SeDebugPrivilege", "GetSEPrivilege")
find_first_str_and_rename_first_xref_func("Congrats! You have gained upgrade potion by playing an hour!", "CFloatNotice::CreateEffEvolRing")
find_first_str_and_rename_first_xref_func("Etc/SetItemInfo.img", "CItemInfo::RegisterSetItemInfo")
find_first_str_and_rename_first_xref_func("Can be equipped on #cone-handed sword or two-handed sword.#", "CItemInfo::GetItemDesc")
find_first_str_and_rename_first_xref_func("epicItem", "CItemInfo::RegisterEquipItemInfo")
find_first_str_and_rename_first_xref_func("%02X%02X%02X%02X%02X%02X_%02X%02X%02X%02X", "CItemInfo::RegisterEquipItemInfo")
find_first_str_and_rename_first_xref_func("Please select the World you would like to play in.", "CUIWorldSelect::MakeAdvice")
find_first_str_and_rename_first_xref_func("Play!", "StartUpWndProc")
find_first_str_and_rename_first_xref_func("%d/%02d/%02d %02d:%02d", "CUIGuildBBS::FormatDate")
find_first_str_and_rename_first_xref_func("QuestID : %d", "CUIQuestInfo::LoadData")
find_first_str_and_rename_first_xref_func("UI/UIWindow2.img/Reset/AP/stat%d/%d", "GetStatCanvas")
find_first_str_and_rename_first_xref_func("[W:%2d]", "CUIStatusBar::ChatLogDraw")
find_first_str_and_rename_first_xref_func("itemLEV", "CUIToolTip::CUIToolTip")
find_first_str_and_rename_first_xref_func("Map/MapHelper.img/weather/squib/squib%d", "CUserPreview::ShowFireCrack")
find_first_str_and_rename_first_xref_func("R6025 %d %d %d %d", "CVecCtrlMob::CtrlUpdateActiveMove")
find_first_str_and_rename_first_xref_func("%s\HShield", "SendHSLog")
find_first_str_and_rename_first_xref_func("SOFTWARE\\NEXON\\eTracer\\", "?Start_eTracer@@YAXKK@Z")
find_first_str_and_rename_first_xref_func("Answer %u %u %u", "CUIHandler::OnDisguise")
find_first_str_and_rename_first_xref_func("ZMemoryStream::SetPosition(): negative seek", "?SetPosition@ZMemoryStream@@UAEXHI@Z")



sp_str_list = ["THE_MONSTER_SEEMS_CONTENT",
          "EFFECTPLAY",
          "_R_NPLAYED_D___D"]
CMonsterUnk_OnUnk_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnUnk@CMonsterUnk@@IAEXAAVCInPacket@@@Z")
CMonsterUnk_OnPacket_EA = get_first_xref_and_rename_func(CMonsterUnk_OnUnk_EA, "?OnPacket@CMonsterUnk@@IAEXAAVCInPacket@@@Z")

sp_str_list = ["YOUVE_SUCCESSFULLY_NURTURED_THE_MONSTER_R_NIT_SEEMS_CONTENT",
          "EFFECTCARE"]
CMonsterUnk_OnUnk2_EA = find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "?OnUnk2@CMonsterUnk@@IAEXAAVCInPacket@@@Z")
CMonsterUnk_OnPacket_EA_2 = get_first_xref_and_rename_func(CMonsterUnk_OnUnk2_EA, "?OnPacket@CMonsterUnk@@IAEXAAVCInPacket@@@Z")

CMonsterLife_OnUnk3_EA = find_first_func_and_rename_by_a_sp_and_string("YESTERDAYS_SCORE_IS_BEING_CALCULATED_R_NCALCULATION_TIME_LEFT_LESS_THAN_30_MINUT", getString_EA, "?OnUnk3@CMonsterLife@@IAEXAAVCInPacket@@@Z")
CMonsterLife_OnPacket_EA = get_first_xref_and_rename_func(CMonsterLife_OnUnk3_EA, "?OnPacket@CMonsterLife@@IAEXAAVCInPacket@@@Z")

CMonsterLife_OnLevelUpFarm_EA = find_first_func_and_rename_by_a_sp_and_string("YOUR_FARM_HAS_LEVELED_UP", getString_EA, "?OnFarmLevelUp@CMonsterLife@@IAEXAAVCInPacket@@@Z")
CMonsterLife_OnPacket_EA_2 = get_first_xref_and_rename_func(CMonsterLife_OnLevelUpFarm_EA, "?OnPacket@CMonsterLife@@IAEXAAVCInPacket@@@Z")

sp_str_list = ["ROCK_PAPER_SCISSORS_HAS_BEEN_CANCELED",
          "THE_FACE_OFF_HAS_BEEN_CANCELED_SINCE_ONE_OR_MORE_PARTICIPANTS_HAS_EXITED_THE_ROO"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "RPSPacket")

sp_str_list = ["YOU_HAVE_GAINED_FAME_D",
          "YOU_HAVE_LOST_FAME_D"]
find_first_func_and_rename_by_multiple_sp_and_string(sp_str_list, getString_EA, "FameMessagePacket")

UnkPacket_EA = find_first_func_and_rename_by_a_sp_and_string("SELECTED", getString_EA, "Unk_Packets::OnUnkPacket")
UnkPacket_OnPacket_EA = get_first_xref_and_rename_func(UnkPacket_EA, "Unk_Packets::OnPacket")

#  Miscellaneous  ------------------------------------------------------------------------------------------------------





















# COutPackets ----------------------------------------------------------------------------------------------------------

find_first_aob_and_rename_func("B9 81 F9 F0 08", "OnSendIncreaseSkillStackRequest")


aob_list = ["? ? 48 4B D8 17",
            "? ? 38 24 D8 17",
            "? ? 39 24 D8 17",
            "? ? 3A 24 D8 17",
            "? ? 11 D6 D7 17",
            "? ? 12 D6 D7 17",
            "? ? 13 D6 D7 17",
            "? ? 56 D6 D7 17",
            "? ? 58 D6 D7 17",
            "? ? 24 D6 D7 17"]
sp_str_list = ["YOU_DONT_HAVE_ANY_ROCKETS_READIED",
               "YOU_NEED_MORE_LIGHTNING_CHARGES_TO_USE_THIS_SKILL",
               "YOU_HAVE_USED_UP_ALL_OF_YOUR_THROWING_STARS",
               "THIS_SKILL_CANNOT_BE_USED_WITH_THE_EQUIPPED_ITEM",
               "YOU_MAY_NOT_USE_THE_SKILL_YET"]
rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.contains_aob_list(aob_list)),
                get_all_functions_containing_sp_str_list(sp_str_list))),
    "OnSendShootObjectCreateRequest")


aob_list = ["FE 01 00 00", "FF 01 00 00", "00 02 00 00",
            "08 02 00 00", "09 02 00 00", "0A 02 00 00",
            "38 4B D8 17"]
sp_str = "SKILL_03DIMG_SKILL_07D_END"
rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.contains_sp_str(sp_str)),
                get_all_functions_containing_aob_list(aob_list))),
    "OnSendSerpentVortexIncrementRequest")


aob_list = ["A6 2A 7F 01", "AD F3 B8 00", "F2 40 36 02", "2F BB C4 04",
            "E4 29 7F 01", "1F 51 7F 01", "53 D2 D7 17", "2F D6 D7 17",
            "24 FD D7 17", "F9 87 D7 17", "4E D2 D7 17", "D1 2E 11 09"]
sp_str_list = ["THIS_SKILL_CANNOT_BE_USED",
               "MOUNT_SKILLS_CANNOT_BE_USED_ON_THIS_MAP",
               "YOU_CANNOT_USE_A_MOUNT_WHILE_IN_YOUR_SPECTER_STATE",
               "CANNOT_BE_USED_ON_THIS_LANDSCAPE"]
rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.contains_aob_list(aob_list)),
                get_all_functions_containing_sp_str_list(sp_str_list))),
    "OnSendSkillUseRequest")


sp_str_list = ["DO_YOU_WISH_FOR_A_RE_ISSUE_R_NRE_ISSUE_COUNT_D___COST_D_MESOS",
               "THIS_MEDAL_CANNOT_BE_RE_ISSUED",
               "YOU_HAVE_INSUFFICIENT_SLOTS__R_NIN_YOUR_INVENTORY_EQUIP_TAB",
               "YOU_DO_NOT_HAVE_ENOUGH_MESOS"]
rename_first_in_list(get_all_functions_containing_sp_str_list(sp_str_list), "OnSendUserMedalReissueRequest")


aob_list = ["FA 87 D7 17", "1B FD D7 17", "1C FD D7 17", "3C FD D7 17"]
sp_str_list = ["IT_CANNOT_BE_USED_HERE", "YOU_CANT_USE_IT_IN_THE_AIR"]
rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.contains_sp_str_list(sp_str_list)),
                get_all_functions_containing_aob_list(aob_list))),
    "OnSendUserThrowGrenade")


sp_str = "YOU_CAN_ONLY_REJECT_A_MAXIMUM_OF_100_REQUESTS_AT_ONE_TIME"
rename_first_in_list(
    list(filter(lambda x: (
            is_function(x)
            and x.makes_call_to(coutpacket_ea)),
                get_all_functions_containing_sp_str(sp_str))),
    "OnSendGuildJoinAccept_Reject")  # Both COutPacket for GUILD_JOIN_ACCEPT and GUILD_JOIN_REJECT are inside this func






# SwitchCase Txt Making ------------------------------------------------------------------------------------------------

if nSel == 1:
    onPacketList = [
                    CField_OnPacketEA_3,
                    CUserPool_OnPacket,
                    CUserRemote_OnPacketEA,
                    CUserLocal_1_OnPacket_EA,
                    CUserLocal_OnPacket_EA,
                    CUserPool_OnPacket_EA,
                    CSummonedPool_OnPacket_EA,
                    CMobPool_OnPacket_EA,
                    CMobPool_OnMobPacketEA,
                    CNpcPool_OnNpcPacket_EA,
                    CNpcPool_OnPacket_EA,
                    CAAPool_OnPacketEA,
                    COpenGate_OnPacket_EA,
                    CReactorPool_OnPacket_EA,
                    CBattleRecordMan_OnPacket_ea,
                    CMapLoadable_OnPacket_ea,
                    CStage_OnPacket_ea,
                    CPersonalMapObj_OnPacket_EA,
                    CWvsContext_OnPacketEA,
                    CCashShop_OnPacket_EA,
                    CLogin_OnPacket_EA,
                    CField_GiantBoss_OnPacket,
                    CBossLucid_OnPacket1,
                    CBossLucid_OnPacket2,
                    CBossWill_OnPacket1EA,

                    CMonsterUnk_OnPacket_EA,
                    CMonsterLife_OnPacket_EA,
                    UnkPacket_OnPacket_EA,
                    ]

    # OnPacket Functions
    for ea in onPacketList:
        list = decompile_func(ea)
        if list == False:
            print "[OP Parser] Failed to decompile this function %s %s" % (get_function_name(ea), hex(ea))
            continue
        parseSwitchCasesInFunc(get_function_name(ea), list)

    # CField xrefs
    for ea in get_xrefs_addr(CField_OnPacket_EA):
        list = decompile_func(ea)
        if list == False:
            print "[OP Parser] Failed to decompile this function %s %s" % (get_function_name(ea), hex(ea))
            continue
        parseSwitchCasesInFunc(get_function_name(ea), list)

    file = open(completePath, "w+")
    file.write("Created by SwordieDevs\n\n\n")
    file.write("[INFO] OP Values for GMS v%s-%s\n\n" % (idbVersion, idbSubVersion))
    writeSwitchCases(file)
    print "Succesfully created %s in %s" % (txtFileName, path)

print "=====  End of IDAPython idb Script     ====="