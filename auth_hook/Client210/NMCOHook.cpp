#include "Global.h"
#include "NMCO\NMGeneral.h"
#include "NMCO\NMSerializable.h"
#include "NMCO\NMFunctionObject.h"
#include <iostream>
#include <string>
#include <shlwapi.h>

#define BUF_SIZE 2048

typedef BOOL(__cdecl* NMCO_CallNMFunc_t)(int uFuncCode, BYTE* pCallingData, BYTE** ppReturnData, UINT32& uReturnDataLen);
NMCO_CallNMFunc_t _NMCO_CallNMFunc;

typedef BOOL(__cdecl* pNMCO_CallNMFunction_t)(int uFuncCode, BYTE* pCallingData, char pUnk, BYTE** ppReturnData, UINT32& uReturnDataLen);
pNMCO_CallNMFunction_t _NMCO_CallNMFunction;

CHAR* g_pUserName = new CHAR[1024];
BYTE* g_pReturnData = new BYTE[BUF_SIZE];

BOOL SetResponse(CNMSimpleStream& s, BYTE** ppReturnData, UINT32& uReturnDataLen)
{
	uReturnDataLen = s.GetBufferSize();

	if (uReturnDataLen > BUF_SIZE)
		return FALSE;

	memset(g_pReturnData, 0, BUF_SIZE);
	memcpy_s(g_pReturnData, BUF_SIZE, s.GetBufferPtr(), uReturnDataLen);

	*ppReturnData = g_pReturnData;

	return TRUE;
}

namespace Hooks
{
	BOOL __cdecl CallNMFunc(int uFuncCode, BYTE* pCallingData, BYTE** ppReturnData, UINT32& uReturnDataLen)
	{
		int nEBP = 0;
		__asm mov nEBP, ebp
		Log(__FUNCTION__);
		if (uFuncCode == kNMFuncCode_LoginAuth)
		{
			Log("nEBP: %X8", nEBP);

			char* szNexonID = reinterpret_cast<LPSTR>(nEBP + 156); //677 or 64 
			Log("szNexonID: %X8", *szNexonID);
			Log("szNexonID: %X8", szNexonID);
			memcpy_s(g_pUserName, LOGINID_SIZE, szNexonID, LOGINID_SIZE);
			Log("g_pUserName: %s", g_pUserName);
			Sleep(0x7FFFFFFF);
			Log("LoginAuth: %s", szNexonID);

			CNMLoginAuthFunc retFunc;
			retFunc.bSuccess = true;
			retFunc.nErrorCode = kLoginAuth_OK;
			retFunc.SetReturn();

			CNMSimpleStream retStream;

			if (!retFunc.Serialize(retStream))
				return FALSE;

			return SetResponse(retStream, ppReturnData, uReturnDataLen);
		}
		else if (uFuncCode == kNMFuncCode_GetNexonPassport)
		{
			CNMGetNexonPassportFunc retFunc;
			retFunc.bSuccess = true;
			retFunc.SetReturn();

			strcpy_s(retFunc.szNexonPassport, g_pUserName);

			CNMSimpleStream retStream;
			if (!retFunc.Serialize(retStream))
				return FALSE;

			return SetResponse(retStream, ppReturnData, uReturnDataLen);
		}
		else if (uFuncCode == kNMFuncCode_LogoutAuth)
		{
			CNMLogoutAuthFunc retFunc;
			retFunc.bSuccess = true;
			retFunc.SetReturn();

			CNMSimpleStream retStream;

			if (!retFunc.Serialize(retStream))
				return FALSE;

			return SetResponse(retStream, ppReturnData, uReturnDataLen);
		}

		return _NMCO_CallNMFunc(uFuncCode, pCallingData, ppReturnData, uReturnDataLen);
	}

	BOOL _cdecl CallNMFunction(int uFuncCode, BYTE* pCallingData, char pUnk, BYTE** ppReturnData, UINT32& uReturnDataLen) {
		Log(__FUNCTION__);
		if (uFuncCode == kNMFuncCode_LoginAuth)
		{
			std::string commandLineString = PathGetArgsA(GetCommandLineA());


			char* cmdString = strcpy(new char[commandLineString.length() + 1], commandLineString.c_str());
			char* end = cmdString + strlen(cmdString);
			while (*end != ' ')
				end--;

			//Log("UserID: %s", (end + 1));

			char* szNexonID = reinterpret_cast<LPSTR>(end + 1);

			//	char* szNexonID = reinterpret_cast<LPSTR>(GetCommandLineA() + 10);

			memset(g_pUserName, 1024, 1);
			strcpy(g_pUserName, szNexonID);

			memcpy_s(g_pUserName, LOGINID_SIZE, szNexonID, LOGINID_SIZE);


			CNMLoginAuthFunc retFunc;
			retFunc.bSuccess = true;
			retFunc.nErrorCode = kLoginAuth_OK;
			retFunc.SetReturn();

			CNMSimpleStream retStream;

			if (!retFunc.Serialize(retStream))
				return FALSE;

			return SetResponse(retStream, ppReturnData, uReturnDataLen);

		}
		else if (uFuncCode == kNMFuncCode_GetNexonPassport)
		{
			CNMGetNexonPassportFunc retFunc;
			retFunc.bSuccess = true;
			retFunc.SetReturn();

			strcpy_s(retFunc.szNexonPassport, g_pUserName);

			CNMSimpleStream retStream;
			if (!retFunc.Serialize(retStream))
				return FALSE;

			return SetResponse(retStream, ppReturnData, uReturnDataLen);
		}
		else if (uFuncCode == kNMFuncCode_LogoutAuth)
		{

			CNMLogoutAuthFunc retFunc;
			retFunc.bSuccess = true;
			retFunc.SetReturn();

			CNMSimpleStream retStream;

			if (!retFunc.Serialize(retStream))
				return FALSE;

			return SetResponse(retStream, ppReturnData, uReturnDataLen);
		}

		return _NMCO_CallNMFunction(uFuncCode, pCallingData, pUnk, ppReturnData, uReturnDataLen);
	}

}

bool HookNMCO()
{
	_NMCO_CallNMFunc = reinterpret_cast<NMCO_CallNMFunc_t>(GetFuncAddress("NMCONEW", "NMCO_CallNMFunc"));
	_NMCO_CallNMFunction = reinterpret_cast<pNMCO_CallNMFunction_t>(GetFuncAddress("NMCOGAME", "NMCO_CallNMFunction"));
	return SetHook(TRUE, reinterpret_cast<void**>(&_NMCO_CallNMFunc), Hooks::CallNMFunc) && SetHook(TRUE, reinterpret_cast<void**>(&_NMCO_CallNMFunction), Hooks::CallNMFunction);
}