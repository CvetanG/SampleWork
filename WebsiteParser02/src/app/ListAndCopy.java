package app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ListAndCopy {

	public static void main(String[] args) throws IOException {

		String url = "http://jcip.net/listings.html";
		Elements links = list(url);
		myCopy(links);
	}

	public static Elements list(String url) throws IOException {
		print("Fetching %s...", url);

		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a[href$=.java]");

		print("\nLinks: (%d)", links.size());
		for (Element link : links) {
			print("%s", link.attr("abs:href"));
		}
		return links;
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	public static void myCopy(Elements links) throws IOException {
		System.out.println();

		for (Element link : links) {
			String oneLink = link.attr("abs:href");
			URL website = new URL(oneLink);
			// the folder bellow must be created
			String myFolderOutput = "/home/cvetan/Desktop/Link to Books/JCIP/";

			String[] parts = oneLink.split("/");
			String myFileName = parts[4];

			String myFileOutput = myFolderOutput + myFileName;

			FileOutputStream fos = null;
			try {
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				fos = new FileOutputStream(myFileOutput);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				System.out.println(myFileName + " Copied");

			} catch (FileNotFoundException e) {
				System.err.println("MISSING " + oneLink);
			} finally {
				if (fos != null) {
					fos.close();
				}
			}

		}
		System.out.println("ALL DONE !!!");

	} 

}
