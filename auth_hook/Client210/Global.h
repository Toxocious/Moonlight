#include <Windows.h>
#include <assert.h>
#include <stdio.h>
#include <time.h>
#include "MapleType.h"
#include "discord_game_sdk.h"

#pragma comment(lib, "ws2_32.lib")
#pragma comment(lib, "detours.lib")
#pragma comment(lib, "discord_game_sdk.dll.lib")

#define DISCORD_REQUIRE(x) assert(x == DiscordResult_Ok)

//---------------------------------------------------------------------------------------------

#define OPT_APPNAME			"v214"
#define OPT_APPVERSION		"1.0"
#define OPT_ADDR_HOSTNAME	"127.0.0.1"

#define OPT_ADDR_SEARCH		"8.31.99."
#define OPT_ADDR_NEXON		"175.207.3.211"

#define OPT_PORT_LOW		8484
#define OPT_PORT_HIGH		8989

#define CLIENT_ID	0

//---------------------------------------------------------------------------------------------

void FuckMaple();

bool HookNMCO();
bool HookWinApi();
bool HookSockApi();
//bool HookCRC();

//---------------------------------------------------------------------------------------------

void Log(const char* format, ...);

void PatchJmp(DWORD dwAddress, DWORD dwDest);
void PatchRetZero(DWORD dwAddress);
void PatchNop(DWORD dwAddress, DWORD dwCount);
void PatchFJ(DWORD dwAddress);
void PatchChat(DWORD dwAddress);
void PatchNoBreath(DWORD dwAddress);
void PatchNoFade(DWORD dwAddress);
void PatchUI(DWORD dwAddress);
void PatchDrop(DWORD dwAddress);
void PatchUnlimitedAttack(DWORD dwAddress);

DWORD GetFuncAddress(LPCSTR lpModule, LPCSTR lpFunc);

BOOL SetHook(BOOL bInstall, PVOID* ppvTarget, PVOID pvDetour);

//---------------------------------------------------------------------------------------------

struct Application
{
    struct IDiscordCore* core;
    struct IDiscordUserManager* users;
    struct IDiscordAchievementManager* achievements;
    struct IDiscordActivityManager* activities;
    struct IDiscordRelationshipManager* relationships;
    struct IDiscordApplicationManager* application;
    struct IDiscordLobbyManager* lobbies;
    DiscordUserId user_id;
};

void UpdateActivityCallback(void* data, EDiscordResult result);