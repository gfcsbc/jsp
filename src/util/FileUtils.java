package util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileUtils {

	public static void saveBufferToFile(String filename, byte[] fileCont) {
		BufferedOutputStream bos=null;
		try {
			bos = new BufferedOutputStream(
					new FileOutputStream(filename));
			bos.write(fileCont);
			System.out.println("FileUtils: save file success:"+filename);
		} catch (Exception e) {
			System.out.println("FileUtils: save file error:"+filename);
			e.printStackTrace();
		}finally{
			if(bos !=null){
				try {
					bos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void saveBufferToFile(String filename, String fileCont) {
		saveBufferToFile(filename,fileCont.getBytes());
	}
	
	public static String getUserHomePath(){
		String path = System.getProperty("user.home");
		if(! path.endsWith(File.separator)){
			path += File.separator;
		}
		return path;
	}
	
}
