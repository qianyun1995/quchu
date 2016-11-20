
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class WriteByteStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("D:/test/"+"test.txt");
		try {
			FileOutputStream fos=new FileOutputStream(file);
		    String outStirng="\r\nnihao";
		    byte output[]=outStirng.getBytes("utf-8");
		    fos.write(output);
		    fos.close();
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
