#pragma once
#include <Windows.h>
#include "MapleType.h"

class CWvsContext
{
private:
	char aPad1[0x2264];
	ZRef<CharacterData> m_pCharacterData;

public:

	CharacterData* GetCharacterData()
	{
		if (this->m_pCharacterData.p)
			return this->m_pCharacterData.p;

		return nullptr;
	}

	static CWvsContext* GetInstance()
	{
		return *reinterpret_cast<CWvsContext**>(0x0370E9CC);
	}
	static bool IsInitialized()
	{
		return (GetInstance() != nullptr);
	}
};
