package app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

public class PicLoader {

	// array of supported extensions (use a List if you prefer)
	static final String[] EXTENSIONS = new String[]{
			"gif", "png", "bmp" // and other formats you need
	};

	// filter to identify images based on their extensions
	static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

		@Override
		public boolean accept(File dir,  String name) {
			for ( String ext : EXTENSIONS) {
				if (name.toLowerCase().endsWith("." + ext)) {
					return (true);
				}
			}
			return (false);
		}
	};

	public static Set<File> getPictures(String myFolder) {

		File dir = new File(myFolder);

		File[] myFilesArray = dir.listFiles(IMAGE_FILTER);

		List<File> myFilesList = Arrays.asList(myFilesArray);

		Set<File> myFileSet = new TreeSet<File>(myFilesList);

		return myFileSet;

	}

	public static void printList(Set<File> myFileSet) {
		if (myFileSet.isEmpty()) {
			System.out.println("No File Found");
		} else {
			for (File file : myFileSet) {
				BufferedImage img = null;

				try {
					img = ImageIO.read(file);

					//				System.out.println("image: " + file.getName());
					System.out.println(file.getName());
					System.out.println(" width : " + img.getWidth());
					System.out.println(" height: " + img.getHeight());
					//                    System.out.println(" size  : " + file.length());
				} catch (final IOException e) {
					// handle errors here
				}
			}
		}
	}
	
	public static void forMain(String folder) {
		Set<File> filesFromDir = getPictures(folder);
		printList(filesFromDir);
	}

	public static void main(String[] args) {
		System.out.println("===== START =====");
		
//		String myFolder01 = "/home/cvetan/Documents/Bugs/07.ITF-11706 Default Poster";
//		forMain(myFolder01);

//		String myFolder02 = "/home/cvetan/Perforce/cgeorgiev/iTvManager/ML/Server/Admin/client_addon/clientConf/images_NuBlu10.0";
//		forMain(myFolder02);
		
		String myFolder03 = "/home/cvetan/Perforce/cgeorgiev/Design/Minerva_10/STB/Resources";
		forMain(myFolder03);
		
		System.out.println("===== FINISH =====");

	}
}