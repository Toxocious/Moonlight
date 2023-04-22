//---------------------------------------------------------------------------------------------
// v203.4 Localhost Enabler - Rajan
//---------------------------------------------------------------------------------------------

#include "Global.h"

extern "C" {
#include "Discord.h"
}

Application app;
DiscordActivity activity;

VOID WINAPI HeaderProc(DWORD dwPID)
{
	Log("------------------------------------------------");
	Log("[Rajan] [%s] [v%s] [PID: %d]", OPT_APPNAME, OPT_APPVERSION, dwPID);
	Log("[Rajan]       \\    /\\  ");
	Log("[Rajan]        )  ( ')   ");
	Log("[Rajan]       (  /  )    ");
	Log("[Rajan] cat    \\(__)|   ");
	Log("[Rajan] [Built: %s]", __TIMESTAMP__);
	Log("------------------------------------------------");
}

void UpdateActivityCallback(void* data, EDiscordResult result)
{
	if (activity.state[0]) {
		app.activities->update_activity(app.activities, &activity, &app, UpdateActivityCallback);
	}
}

DWORD WINAPI MainProc(PVOID)
{
	DWORD dwCurProcId = GetCurrentProcessId();
	HeaderProc(dwCurProcId);

	bool bInit = true;

	bInit &= HookSockApi();
	bInit &= HookWinApi();
	bInit &= HookNMCO();

	int discordStopSignal = 0;
	if (bInit)
	{
		DiscordStart((void*)&discordStopSignal);
	}
	else
	{
		Log("[AuthHook] Initialization Failed!!!");
	}

	return 0;
}

BOOL WINAPI DllMain(HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpvReserved)
{
	if (fdwReason == DLL_PROCESS_ATTACH)
	{
		DisableThreadLibraryCalls(hinstDLL);
		CreateThread(NULL, NULL, (LPTHREAD_START_ROUTINE)&MainProc, NULL, NULL, NULL);
	}

	return TRUE;
}
