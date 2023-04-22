#include "Global.h"
#include "CWvsContext.h"
#include <intrin.h>
#include <map>
#include <string>

extern Application app;
std::map<int, std::string>    g_mStringPool;

typedef ZXString<char>* (__fastcall* StringPool__GetString_t)(void* ecx, void* edx, ZXString<char>* result, unsigned int nIdx, char formal);
auto StringPool__GetString = reinterpret_cast<StringPool__GetString_t>(0x00CDDFE0);// v213 0x00CB14E0 // 55 8B EC 6A FF 68 ? ? ? ? 64 A1 ? ? ? ? 50 83 EC 14 53 56 57 A1 ? ? ? ? 33 C5 50 8D 45 F4 64 A3 ? ? ? ? 8B D9 89 5D F0 8D 73 0C C7 45 ? ? ? ? ? 8B CE 89 75 E4 E8 ? ? ? ? 85 C0 74 20 8B CE E8 ? ? ? ? 
typedef void(__fastcall* ZXString_char__Assign_t)(void* pThis, void* edx, const char* s, int n);
auto ZXString_char__Assign = reinterpret_cast<ZXString_char__Assign_t>(0x0084EB10);// v213 0x00CB14E0// 55 8B EC 57 8B F9 8B 4D 08 85 C9 75 1D 8B 07 85 C0 74 4C 83 C0 F4 50 E8 ? ? ? ? 83 C4 04 C7 07 ? ? ? ? 5F 5D C2 08 00       ///////////     55 8B EC 57 8B F9 8B 4D 08 85 C9 75 1D 8B 07 85 C0 74 4C 83 C0 F4 50 E8 24 12 CD FF 83 C4 04 C7 07 00 00 00 00 5F 5D C2 08 00 56 8B 75 0C 83 FE


ZXString<char>* __fastcall StringPool__GetString_Hook(void* pThis, void* edx, ZXString<char>* result, unsigned int nIdx, char formal)
{
    if (g_mStringPool.count(nIdx) > 0)
    {
        auto szEntry = g_mStringPool[nIdx];

        result->m_pStr = 0;
        ZXString_char__Assign(result, edx, szEntry.c_str(), -1);

        return result;
    }

    return StringPool__GetString(pThis, edx, result, nIdx, formal);
}

//----------------------------------------------------------------------------------------------------

void InitStringPool()
{
    g_mStringPool[4761] = std::string("v214"); //version
    g_mStringPool[7408] = std::string("Golden Hammer applied successfully!"); // golden hammer
}

void FuckMaple()
{
    InitStringPool();

    PatchRetZero(0x02DC2890); // CRC v214.1
    PatchUI(0x02AC1293); // UI Transparency

    PatchChat(0x01436DE5); // Bypass Chat Spam v214.1
    PatchChat(0x01436EB9); // Bypass Chat Spam v214.1
    PatchNop(0x015060AF, 6); // Drop Cash Items v214.1
    PatchNop(0x015060BB, 6); // Drop Cash Items v214.1

    SetHook(TRUE, reinterpret_cast<void**>(&StringPool__GetString), StringPool__GetString_Hook);

}