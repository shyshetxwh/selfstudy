#include "Printf3.h"
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

JNIEXPORT void JNICALL Java_Printf3_fprint(JNIEnv* env, jclass cl, jobject out,jstring format,jdouble x)
{
	const char* cformat;
	char* fmt;
	jstring str;
	jclass class_PrintWriter;
	jmethodID id_print;
	
	cformat=(*env)->GetStringUTFChars(env,format,NULL);
	fmt=find_format(cformat);
	if(fmt==NULL)
		str=format;
	else
	{
		char* cstr;
		int width=atoi(fmt);
		if(width==0)width=DBL_DIG+10;
		cstr=(char*)malloc(strlen(cformat)+width);
		sprintf(cstr,cformat,x);
		str=(*env)->NewStringUTF(env,cstr);
		free(cstr);
	}
	(*env)->ReleaseStringUTFChars(env,format,cformat);
	
	/*get the class*/
	class_PrintWriter=(*env)->GetObjectClass(env,out);
	/*get the method ID*/
	id_print=(*env)->GetMethodID(env,class_PrintWriter,"print","(Ljava/lang/String;)V");
	/*call the method*/
	(*env)->CallVoidMethod(env,out,id_print,str);
}