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
		System.out.println("\r<br>ִ�к�ʱ : "+(System.currentTimeMillis()-a)/1000f+" �� ");
	}

	public static void startTest(String testFileDir) throws Exception {
		File[] testFile = new File(testFileDir).listFiles();
		for(int i=0; i<testFile.length; i++)
		{
			if(testFile[i].isFile())
			{
				//�ļ��������...
				try {       			   
					//�ļ�������
					InputStream in = new FileInputStream(testFile[i]);
					byte[] buffer = new byte[(int) testFile[i].length()];
					in.read(buffer, 0, buffer.length);
					//�������ʽ
					//String code=CharsetUtil.getEncoding(testFile[i]);	
					String str=new String(buffer,"utf-8");
					//ʵ������ϴ��
					HTMLSpirit qc=new HTMLSpirit();
					//��ȡkeywords����������ϴ
					String jkeystr=HTMLSpirit.jkey(str);
					String jkey=HTMLSpirit.jkeystripHtml(jkeystr);					
					//��ϴ�ı�
					String temp= HTMLSpirit.delHTMLTag(str);
					String content=HTMLSpirit.stripHtml(temp);					
					String judge=content;
					//���ı���keywords�ϲ�
					content+="\r\n"+jkey;					
					//System.out.println(content);
					//���ݿ�����ʱ������ļ���
					int uid=i+1;
					//String filename=Integer.toString(uid);
					//��������ı��ļ����ļ���
					String filename=testFile[i].getName();
					//�������·��
					File file2=new File("D:/test/7.22/"+filename);
					//File file2=new File("D:/test/test/"+uid+".txt");
					//���뵽���ݿ���
					/*Clean clean=new Clean(uid,content);
					ConnectDB jdbc=new ConnectDB();
					jdbc.insert(clean); */

					//���뵽ָ��·���µ��ı���
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
					System.out.println("�ļ���������");
				}
		}
	}
}
