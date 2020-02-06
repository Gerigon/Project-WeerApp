import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class deleteFiles {
	
	public static void main(String[] args) throws IOException {
		String[] foldersToExclude = { "562940", "562870", "563740", "561780", "561960", "573060", "572450", "572370",
				"571440", "571340", "571270", "573480", "948050", "110350", "716310", "73540", "103840", "476620",
				"466960", "483270", "62750", "722196", "users", "Stations", "postprocessing" };
		;
		File[] directories = new File("/samba/weerstation_share/").listFiles(File::isDirectory);

		for (int i = 0; i < directories.length; i++) {
			File tempdir = directories[i];

			if (Arrays.asList(foldersToExclude).contains(tempdir.getName()) == false) {
			

				File folder = new File("/samba/weerstation_share/" + tempdir.getName());
				File[] listOfFiles = folder.listFiles();

				for (File file : listOfFiles) {
					if (file.isFile()) {
						deletefile(file);

					}

				}
			}
		}

	}
	
	
    public static void deletefile(File file) {
    	try {
            if (file.exists()) {
                file.delete();
            }
            } catch (Exception e) {
            e.printStackTrace();
        }   
       }
}
