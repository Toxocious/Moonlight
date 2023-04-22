#define _XDEF_NEXONLOGIN 1
#ifdef _XDEF_NEXONLOGIN

#ifndef __NMGENERAL_H_2EFA9787_0C6D_48e0_A447_FAC5A9D35A11__
#define __NMGENERAL_H_2EFA9787_0C6D_48e0_A447_FAC5A9D35A11__

#include <Windows.h>

// singleton macro

#define DECLARE_NMSINGLETON( className )			\
	public:											\
		static className& GetInstance( void )		\
		{											\
			static className* spInstance = NULL;	\
			if ( spInstance == NULL )				\
				spInstance = new className;			\
			return ( *spInstance );					\
		}


// simple lock implementation

class CNMCSHolder
{
protected:
	LPCRITICAL_SECTION		m_pCriticalSection;

public:
	CNMCSHolder( LPCRITICAL_SECTION pCriticalSection )
	{
		this->m_pCriticalSection = pCriticalSection;
		::EnterCriticalSection( this->m_pCriticalSection );
	}

	~CNMCSHolder()
	{
		::LeaveCriticalSection( this->m_pCriticalSection );
	}

	operator bool()	{ return true; }
};
#define NMCSLOCK( x )		if ( CNMCSHolder	csholder = CNMCSHolder( &x ) )

// utility macro

#define NMSAFE_STRCOPY( dst, src, len )		\
	{ ::_tcsncpy( dst, src, len );			\
	dst[ len - 1 ] = NULL; }

#define NMSAFE_STRCOPYW( dst, src, len )	\
	{ ::wcsncpy( dst, src, len );			\
	dst[ len - 1 ] = NULL; }

#define NMSAFE_STRCOPYA( dst, src, len )	\
	{ ::strncpy( dst, src, len );			\
	dst[ len - 1 ] = NULL; }

#endif	//	#ifndef __NMGENERAL_H_2EFA9787_0C6D_48e0_A447_FAC5A9D35A11__

#endif