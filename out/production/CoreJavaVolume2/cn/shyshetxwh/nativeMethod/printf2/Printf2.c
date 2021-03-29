#include "Printf2.h"
#include <string.h>
#include <stdlib.h>
#include <float.h>

char* find_format(const char format[])
{
	char* p;
	char* q;
	
	p=strchr(format,'%');
	while(p!=NULL&&*(p+1)=='%')/*skip %% */
		p=strchr(p+2,'%');
	if(p==null) return NULL;
	/*now check that % is unique*/
	p++;
	q=strchr(p,'%');
	while(q!=NULL&&*(q+1)=='%')/*skip %% */
		q=strchr(q+2,'%');
	if(q!=NULL)return NULL;/*not unique*/
	q=p+strspn(p,"-0+#");/*skip past flags*/
	q+=strspn(q,"0123456789");/*skip past field width*/
	if(*q=='.')/*skip past precision*/
	{
		q++;
		q+=strspn(q,"0123456789");
	}
	if(strchr("eEfFgG",*q)==NULL)return NULL;/*not a floating-point format*/
	return p;
}

JNIEXPORT jstring JNICALL Java_Printf2_sprintf(JNIEnv* env, jclass cl, jstring format,jdouble x)
{
	const char* cformat;
	char* fmt;
	jstring ret;
	
	cformat=(*env)->GetStringUTFChars(env,format,NULL);
	fmt=find_format(cformat);
	if(fmt==NULL)
		ret=format;
	else
	{
		char* cret;
		int width=atoi(fmt);
		if(width==0)width=DBL_DIG+10;
		cret=(char*)malloc(strlen(cformat)+width);
		sprintf(cret,cformat,x);
		ret=(*env)->NewStringUTF(env,cret);
		free(cret);
	}
	(*env)->ReleaseStringUTFChars(env,format,cformat);
	return ret;
}