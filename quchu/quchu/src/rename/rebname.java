package rename;

import java.io.File;

public class rebname {

	public static void main(String[] args) {
	
		startTest("F:/spri/spider3/");
	}
	public static void startTest(String testFileDir) {
		File[] testFile = new File(testFileDir).listFiles();
		for(int i=0; i<testFile.length; i++)
		{
			if(testFile[i].isFile())
			{
				//文件重命名
				testFile[i].renameTo(new File(testFileDir+i+".txt"));
			}
			else
				if(testFile[i].isDirectory())
				{
					startTest(testFile[i].getPath());
				}
				else
				{
					System.out.println("文件读入有误！");
				}
		}
	}
	

}
