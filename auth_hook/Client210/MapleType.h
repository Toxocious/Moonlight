#pragma once

template <typename T>
struct ZXString
{
    T* m_pStr;

    ZXString()
    {
        m_pStr = 0;
    }
};

template <typename T>
struct ZRef
{
	char aPad[4];
	T* p;
};

# pragma pack (1)
struct GW_CharacterStat
{
    unsigned int dwCharacterID;
    unsigned int dwCharacterIDForLog;
    unsigned int dwWorldIDForLog;
    char sCharacterName[13];

    char nGender;
    char nSkin;
    int nFace;
    int nHair;

    char nUnk1;
    char nUnk2;
    char nUnk3;

    int _ZtlSecureTear_nLevel[2];
    unsigned int _ZtlSecureTear_nLevel_CS;
};
# pragma pack (0)

struct CharacterData
{
	GW_CharacterStat characterStat;
};
