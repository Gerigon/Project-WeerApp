package domparser;
import java.io.*;
import java.util.zip.*;

public class Compress {
	
	public static void compress(File source, File destination) throws IOException {
		byte[] buffer = new byte[1024];
		FileInputStream fInput = new FileInputStream(source);
		FileOutputStream fOutput = new FileOutputStream(destination);
		GZIPOutputStream gZipOutput = new GZIPOutputStream(fOutput);
	int read;
	while((read = fInput.read(buffer))!=-1)
	{
		gZipOutput.write(buffer,0,read);
	}
	gZipOutput.finish();
	gZipOutput.close();
	fOutput.close();
	fInput.close();

}
	

	public static void main(String[] args) {

		File source = new File("C:\\Users\\ludew\\eclipse-workspace\\leertaak3\\src\\domparser\\decompresseddata.txt");
		File destination = new File("C:\\Users\\ludew\\eclipse-workspace\\leertaak3\\src\\domparser\\compresseddata.txt");
		
		try{
			compress(source,destination);

			
		}catch(Exception e){
			System.out.println(e);
		
		}
	}
}
    	
