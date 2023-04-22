#include "Global.h"
#include "detours.h"
#include <stdio.h>

#define JMP		0xE9
#define NOP		0x90
#define RET		0xC3

#define relative_address(frm, to) (int)(((int)to - (int)frm) - 5)

void Log(const char* format, ...)
{
	char buf[1024] = { 0 };

	va_list args;
	va_start(args, format);
	vsprintf_s(buf, format, args);

	OutputDebugStringA(buf);

	va_end(args);
}

void PatchJmp(DWORD dwAddress, DWORD dwDest)
{

	*(BYTE*)dwAddress = JMP;
	*(DWORD*)(dwAddress + 1) = relative_address(dwAddress, dwDest);
}
void PatchRetZero(DWORD dwAddress)
{
	*(BYTE*)(dwAddress + 0) = 0x33;
	*(BYTE*)(dwAddress + 1) = 0xC0;
	*(BYTE*)(dwAddress + 2) = RET;
}

void PatchFJ(DWORD dwAddress)
{
	*(BYTE*)(dwAddress + 0) = 0xEB;
}


void PatchChat(DWORD dwAddress)
{
	*(BYTE*)(dwAddress + 0) = 0xEB;
}

void PatchNoBreath(DWORD dwAddress)
{
	*(BYTE*)(dwAddress + 0) = 0x78;
}

void PatchUI(DWORD dwAddress)
{
	*(BYTE*)(dwAddress + 0) = 0x00;
}

void PatchUnlimitedAttack(DWORD dwAddress)
{
	*(BYTE*)(dwAddress + 0) = 0xEB;
}

void PatchDrop(DWORD dwAddress)
{
	*(BYTE*)(dwAddress + 0) = 0x5E;
}


void PatchNoFade(DWORD dwAddress)
{
	*(BYTE*)(dwAddress + 0) = 0x90;
	*(BYTE*)(dwAddress + 1) = 0xE9;
}

void PatchNop(DWORD dwAddress, DWORD dwCount)
{
	for (DWORD i = 0; i < dwCount; i++)
	{
		*(BYTE*)(dwAddress + i) = NOP;
	}
}

DWORD GetFuncAddress(LPCSTR lpModule, LPCSTR lpFunc)
{
	auto mod = LoadLibraryA(lpModule);

	if (!mod)
	{
		return 0;
	}

	auto address = (DWORD)GetProcAddress(mod, lpFunc);

	if (!address)
	{
		return 0;
	}

#ifdef _DEBUG
	Log(__FUNCTION__ " [%s] %s @ %8X", lpModule, lpFunc, address);
#endif

	return address;
}

BOOL SetHook(BOOL bInstall, PVOID* ppvTarget, PVOID pvDetour)
{
	if (DetourTransactionBegin() != NO_ERROR)
	{
		return FALSE;
	}

	auto tid = GetCurrentThread();

	if (DetourUpdateThread(tid) == NO_ERROR)
	{
		auto func = bInstall ? DetourAttach : DetourDetach;

		if (func(ppvTarget, pvDetour) == NO_ERROR)
		{
			if (DetourTransactionCommit() == NO_ERROR)
			{
				return TRUE;
			}
		}
	}

	DetourTransactionAbort();
	return FALSE;
}
