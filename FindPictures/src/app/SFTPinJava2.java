package app;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @author http://kodehelp.com
 *
 */
public class SFTPinJava2 {

	public static final String SFTPHOST = "172.31.10.117";
	public static final int    SFTPPORT = 22;
	public static final String SFTPUSER = "root";
	public static final String SFTPPASS = "mn2111td";

	public static final String SFTPWORKINGDIR = "/";
	public static final String ext = "stripe_default_large_unfocused.png";


	public static ChannelSftp myConnection() {

		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;

		try{
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp)channel;

		}catch(Exception ex){
			ex.printStackTrace();
		}
//		session.disconnect();
		return channelSftp;
	}

	public static void getAllEntries(ChannelSftp channelSftp, String myDir) throws SftpException {
		channelSftp.cd(myDir);

		Vector<ChannelSftp.LsEntry> list = channelSftp.ls(myDir);
		for(ChannelSftp.LsEntry entry : list) {
			System.out.println(entry.getFilename());
		}
	}

	public static void getFiles(ChannelSftp channelSftp, String myDir) throws SftpException {
		channelSftp.cd(myDir);

		Vector<ChannelSftp.LsEntry> list = channelSftp.ls(myDir);
		for(ChannelSftp.LsEntry entry : list) {
			if (entry.getFilename().toString().endsWith(ext)) {
				System.out.println(entry.getFilename());

			}
		}
	}

	public static List<String> myStrings = new ArrayList<String>();

	public static List<String> getFilesRecursivly(ChannelSftp channelSftp, String myDir) throws SftpException, IOException {
		channelSftp.cd(myDir);

		Vector<ChannelSftp.LsEntry> list = channelSftp.ls(myDir);
		

		for(ChannelSftp.LsEntry entry : list) {
			if (entry.getAttrs().isDir()) {
				if (entry.getFilename().toString().endsWith("..") ||
						entry.getFilename().toString().endsWith(".") ||
						entry.getFilename().toString().startsWith("~") ||
						entry.getFilename().toString().startsWith("u0") ||
						entry.getFilename().toString().startsWith("proc") ||
						entry.getFilename().toString().startsWith("oracle") ||
						entry.getFilename().toString().startsWith("dev") ||
						entry.getFilename().toString().startsWith("kernels") ||
						entry.getFilename().toString().startsWith("php56")) {
					//					System.out.println(myDir + entry.getFilename());
				} else {
					//					System.out.println(myDir + "/"  + entry.getFilename() + " is a Folder");
					String tempString = null;
					if (myDir.equals("/") ) {
						tempString = myDir + entry.getFilename().toString();
					} else {
						tempString = myDir + "/" + entry.getFilename().toString();
					}
					getFilesRecursivly(channelSftp, tempString);
				}
			} else {
				if (entry.getFilename().endsWith(ext)) {
					String myFile = myDir + "/" + entry.getFilename();
					System.out.println(myFile);
					myStrings.add(myFile);
					
					
				}
			}
		}
		return myStrings;
	}
	
	public static  void printProperties(ChannelSftp channelSftp, List<String> myStrings) throws SftpException, IOException {

		System.out.println();
		if (myStrings.isEmpty()) {
			System.out.println("No Files Found");
		} else {
			for (String file : myStrings) {

				System.out.println(file);

				BufferedImage img = null;


				try (InputStream is = channelSftp.get(file)) {
					img = ImageIO.read(is);

					System.out.println(" width : " + img.getWidth());
					System.out.println(" height: " + img.getHeight());
					
				} finally {
				}
			}

		}
	}
	

	public static void main(String[] args) throws SftpException, IOException, JSchException {
		System.out.println("===== START =====");

		ChannelSftp channelSftp = myConnection();
		//		getAllEntries(channelSftp, SFTPWORKINGDIR);
		//		System.out.println("=============================================");
		//		getFiles(channelSftp, SFTPWORKINGDIR);
		//		System.out.println("=============================================");

		List<String> myStrings = getFilesRecursivly(channelSftp, SFTPWORKINGDIR);
		printProperties(channelSftp, myStrings);


		System.out.println("===== FINISH =====");
		channelSftp.getSession().disconnect();
		channelSftp.disconnect();
	}
}