import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;

public class getAveragefromFile {
	public static void main(String[] args) throws IOException {
		String[] foldersToInclude = { "562940", "562870", "563740", "561780", "561960", "573060", "572450", "572370",
				"571440", "571340", "571270", "573480", "948050", "110350", "716310", "73540", "103840", "476620",
				"466960", "483270", "62750", "722196", "10300" };
		
		
		
		
		File[] directories = new File("/samba/weerstation_share/").listFiles(File::isDirectory);
		for (int i = 0; i < directories.length; i++) {
			File tempdir = directories[i];

			if (Arrays.asList(foldersToInclude).contains(tempdir.getName()) == true &&  tempdir.getName() != "users"  &&  tempdir.getName() !="Stations" &&  tempdir.getName() != "postprocessing") {

				File folder = new File("/samba/weerstation_share/" + tempdir.getName());

				File logfolder = new File(
						"/samba/weerstation_share/" + tempdir.getName() + "/log/");
				if (!logfolder.exists()) {

					logfolder.mkdir();
				}

				File log = new File(
						"/samba/weerstation_share/" + tempdir.getName() + "/log/log.txt");
				File[] listOfFiles = folder.listFiles();

				for (File file : listOfFiles) {
					if (file.isFile()
							&& Files.probeContentType(Paths.get(file.getPath())).contentEquals("application/gzip")==true) {

						File temp = new File(
								"/samba/weerstation_share/" + tempdir.getName() + "/temp");
						temp.createNewFile();

						decompress(file, temp);

						FileWriter fr = new FileWriter(log, true);
						fr.write(avg(temp) + "\n");
						fr.close();

						deletefile(temp);

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

	public static String avg(File input) throws FileNotFoundException, IOException {

		String STN = "", DATE = "", TIME = "";
		float TEMP = 0, DEWP = 0, STP = 0, SLP = 0, VISIB = 0, WDSP = 0, PRCP = 0, SNDP = 0, FRSHTT = 0, CLDC = 0,
				WNDDIR = 0;
		int count = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(input))) {
			String line;
			while ((line = br.readLine()) != null) {

				String[] linearr = line.split(",");
				if (linearr.length == 14) {
					count++;
					STN = linearr[0];
					DATE = linearr[1];
					TIME = "-";
					TEMP += Float.parseFloat(linearr[3]);
					DEWP += Float.parseFloat(linearr[4]);
					STP += Float.parseFloat(linearr[5]);
					SLP += Float.parseFloat(linearr[6]);
					VISIB += Float.parseFloat(linearr[7]);
					WDSP += Float.parseFloat(linearr[8]);
					PRCP += Float.parseFloat(linearr[9]);
					SNDP += Float.parseFloat(linearr[10]);
					FRSHTT += Float.parseFloat(linearr[11]);
					CLDC += Float.parseFloat(linearr[12]);
					WNDDIR += Float.parseFloat(linearr[13]);
				}
			}
			br.close();

		}

		String avgtemp = Float.toString(TEMP / count);
		String avgdewp = Float.toString(DEWP / count);
		String avgstp = Float.toString(STP / count);
		String avgslp = Float.toString(SLP / count);
		String avgvisib = Float.toString(VISIB / count);
		String avgwdsp = Float.toString(WDSP / count);
		String avgprsp = Float.toString(PRCP / count);
		String avgsndp = Float.toString(SNDP / count);
		String avgdrshtt = Float.toString(FRSHTT / count);
		String avgcldc = Float.toString(CLDC / count);
		String avgwnddir = Float.toString(WNDDIR / count);

		return STN + "," + DATE + "," + TIME + "," + avgtemp + "," + avgdewp + "," + avgstp + "," + avgslp + ","
				+ avgvisib + "," + avgwdsp + "," + avgprsp + "," + avgsndp + "," + avgdrshtt + "," + avgcldc + ","
				+ avgwnddir;

	}

	public static void decompress(File source, File destination) throws IOException {
		byte[] buffer = new byte[1024];
		FileInputStream fInput = new FileInputStream(source);

		GZIPInputStream gZipInput = new GZIPInputStream(fInput);

		FileOutputStream fOutput = new FileOutputStream(destination);
		int read;
		while ((read = gZipInput.read(buffer)) != -1) {
			fOutput.write(buffer, 0, read);

		}
		gZipInput.close();
		gZipInput.close();
		fOutput.close();
		deletefile(source);

	}
}
