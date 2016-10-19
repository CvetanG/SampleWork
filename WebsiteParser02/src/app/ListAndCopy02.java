package app;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class ListAndCopy02 {

	public static void main(String[] args) throws IOException {

//		String url = "http://jcip.net/listings.html";
		String url = "https://7777.bg/loto_games/big_jackpot/#numbers";
		
//		Document doc = renderPage(url);
//		getAllLinks(url);
//		buckets(url);
		
		
		Elements links = list(url);
//		myCopy(links);
	}

	public static Elements list(String url) throws IOException {
		print("Fetching %s...", url);
		
		WebDriver driver = new HtmlUnitDriver();
		
		driver.get(url);
		
//		WebElement element = (WebElement) ((JavascriptExecutor)driver).executeScript("changePage(this,'3');");
		
		
		Document doc = (Document) ((JavascriptExecutor)driver).executeScript("changePage(this,'3');");
		
//		Document doc = Jsoup.connect(url).get();
		
//		Elements links = doc.select("a[href$=.java]");
		Elements links = doc.select("div.padding-box > div.draw-balls li");

//		print("\nLinks: (%d)", links.size());
//		for (Element link : links) {
//			print("%s", link.attr("abs:href"));
//		}
//		return links;
		print("\nLinks: (%d)", links.size());
		for (Element link : links) {
			print("%s", link.text());
		}
		return links;
	}
	
	public static Elements list(Document doc) throws IOException {
//		print("Fetching %s...", doc);
		
//		Document doc = Jsoup.connect(url).get();
		
//		Elements links = doc.select("a[href$=.java]");
		Elements links = doc.select("div.padding-box > div.draw-balls li");
		
//		print("\nLinks: (%d)", links.size());
//		for (Element link : links) {
//			print("%s", link.attr("abs:href"));
//		}
//		return links;
		print("\nLinks: (%d)", links.size());
		for (Element link : links) {
			print("%s", link.text());
		}
		return links;
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
	
	public static Document renderPage(String url) {
		DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);  
        caps.setCapability("takesScreenshot", true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/home/cvetan/Downloads/phantomjs-2.1.1-linux-x86_64/bin/phantomjs");
        
        WebDriver ghostDriver = new PhantomJSDriver(caps);
        try {
            ghostDriver.get(url);
            return Jsoup.parse(ghostDriver.getPageSource());
        } finally {
            ghostDriver.quit();
        }
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
	
	public static String getAllLinks(String url) throws IOException {
		String RedirectedUrl=null;
		Document page = Jsoup.connect(url).get();
	    Elements meta = page.select("html head meta");
	    if (meta.attr("http-equiv").contains("REFRESH")) {
	        RedirectedUrl = meta.attr("content").split("=")[1];
	    } else {
	        if (page.toString().contains("window.location.href")) {
	            meta = page.select("script");
	            for (Element script : meta) {
	                String s = script.data();
	                if (!s.isEmpty() && s.startsWith("window.location.href")) {
	                    int start = s.indexOf("=");
	                    int end = s.indexOf(";");
	                    if (start>0 && end >start) {
	                        s = s.substring(start+1,end);
	                        s =s.replace("'", "").replace("\"", "");        
	                        RedirectedUrl = s.trim();
	                        break;
	                    }
	                }
	            }
	        }
	    }
	    return RedirectedUrl;
	}
	
//	public static void buckets(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
////        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
//        WebClient webClient = new WebClient();
//        System.out.println("Loading page now: "+url);
//        HtmlPage page = webClient.getPage(url);
//        webClient.waitForBackgroundJavaScript(30 * 1000); /* will wait JavaScript to execute up to 30s */
//
//        String pageAsXml = page.asXml();
//        System.out.println("Contains bucket? --> " + pageAsXml.contains("bucket"));
//
//        //get divs which have a 'class' attribute of 'bucket'
//        List<> buckets = page.getByXPath("//div[@class='bucket']");
//        System.out.println("Found " + buckets.size() + " 'bucket' divs.");
//
//        System.out.println("#FULL source after JavaScript execution:\n " + pageAsXml);
//    }

}
