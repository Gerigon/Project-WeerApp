package domparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class Decompress{
	public static void decompress(File source, File destination) throws IOException{
		byte[] buffer = new byte[1024];
		FileInputStream fInput = new FileInputStream(source);
		
		GZIPInputStream gZipInput = new GZIPInputStream(fInput);
		
		FileOutputStream fOutput = new FileOutputStream(destination);
		int read;
		while((read = gZipInput.read(buffer)) != -1) {
			fOutput.write(buffer,0,read);
			
		}
		gZipInput.close();
		gZipInput.close();
		fOutput.close();
		
			
	}
	
	public static void main(String[] args) {
		File source = new File("C:\\Users\\ludew\\eclipse-workspace\\leertaak3\\src\\domparser\\cprsd.txt");
		File destination = new File("C:\\Users\\ludew\\eclipse-workspace\\leertaak3\\src\\domparser\\decprsd.txt");
	    try {
	    	decompress(source, destination);
	    }
	    
	    catch(IOException e) {
	    	System.out.print("no");
	    	System.out.print(e);
	    }
	}
	
}