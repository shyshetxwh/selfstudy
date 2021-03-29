class Printf2
{
	public static native String sprintf(String format,double x);
	
	static 
	{
		System.loadLibrary("Printf2");
	}
}