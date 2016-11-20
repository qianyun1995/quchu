package quchu;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import quchu.HTMLSpirit;
public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		long a=System.currentTimeMillis();
		startTest("F:/spri/spider3/");
		//startTest("F:/spri/spt/");
		System.out.println("\r<br>执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
	}

	public static void startTest(String testFileDir) throws Exception {
		File[] testFile = new File(testFileDir).listFiles();
		for(int i=0; i<testFile.length; i++)
		{
			if(testFile[i].isFile())
			{
				//文件处理代码...
				try {       			   
					//文件输入流
					InputStream in = new FileInputStream(testFile[i]);
					byte[] buffer = new byte[(int) testFile[i].length()];
					in.read(buffer, 0, buffer.length);
					//检查编码格式
					//String code=CharsetUtil.getEncoding(testFile[i]);	
					String str=new String(buffer,"utf-8");
					//实例化清洗类
					HTMLSpirit qc=new HTMLSpirit();
					//提取keywords，并进行清洗
					String jkeystr=HTMLSpirit.jkey(str);
					String jkey=HTMLSpirit.jkeystripHtml(jkeystr);					
					//清洗文本
					String temp= HTMLSpirit.delHTMLTag(str);
					String content=HTMLSpirit.stripHtml(temp);					
					String judge=content;
					//将文本与keywords合并
					content+="\r\n"+jkey;					
					//System.out.println(content);
					//数据库连接时存入的文件名
					int uid=i+1;
					//String filename=Integer.toString(uid);
					//获得输入文本文件的文件名
					String filename=testFile[i].getName();
					//设置输出路径
					File file2=new File("D:/test/7.22/"+filename);
					//File file2=new File("D:/test/test/"+uid+".txt");
					//输入到数据库中
					/*Clean clean=new Clean(uid,content);
					ConnectDB jdbc=new ConnectDB();
					jdbc.insert(clean); */

					//输入到指定路径下的文本中
					if(file2.exists()){ 
						file2.delete();
					}else
					{
						file2.createNewFile();
					}

					FileOutputStream fos=new FileOutputStream(file2);
					String outString=content;
					judge=judge.replaceAll("\r\n", "");
					judge=judge.replaceAll("\r", "");
					judge=judge.replaceAll("\n", "");
					judge=judge.replaceAll("\n\r", "");
					judge=judge.replaceAll(" ","");
					//byte output[]=outString.getBytes(code);

					byte output[]=outString.getBytes("utf-8");
					
					fos.write(output);
					fos.close();
					in.close();
					
					if(judge.equals("123456")||judge.equals(""))
					{    
						System.out.println(file2.delete());
					}



				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
