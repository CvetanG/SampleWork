package app;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
public class SFTPManager {

	public static final String SFTP_HOST = "172.31.10.117";
	public static final int    SFTP_PORT = 22;
	public static final String SFTP_USER = "root";
	public static final String SFTP_PASS = "mn2111td";

	public static final String SFTP_WORKINGDIR = "/";
	public static final String ext = "image-scene_locked_default_small.png";
	
	public static final String[] EXCL_START = new String[]{
			"~", "u0", "proc", "oracle", "dev", "kernels", "php56" // and other formats you need
	};
	
	public static final String[] EXCL_END = new String[]{
			"..", "." // and other formats you need
	};
	
	public static boolean isContain(String tobeCheck) {
		for (String myString : EXCL_START) {
			if (tobeCheck.startsWith(myString)) {
				return true;
			}
		} 
		for (String myString : EXCL_END) {
			if (tobeCheck.endsWith(myString)) {
				return true;
			}
		} 
		return false;
	}
	

	public static ChannelSftp myConnection() {

		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;

		try{
			JSch jsch = new JSch();
			session = jsch.getSession(SFTP_USER,SFTP_HOST,SFTP_PORT);
			session.setPassword(SFTP_PASS);
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
	
	// List all entries from myDir  
	public static void getAllEntries(ChannelSftp channelSftp, String myDir) throws SftpException {
		channelSftp.cd(myDir);
		Vector<ChannelSftp.LsEntry> list = channelSftp.ls(myDir);
		for(ChannelSftp.LsEntry entry : list) {
			System.out.println(entry.getFilename());
		}
	}
	
	// List only entries from myDir which refer to "ext"
	public static void getFiles(ChannelSftp channelSftp, String myDir) throws SftpException {
		channelSftp.cd(myDir);
		Vector<ChannelSftp.LsEntry> list = channelSftp.ls(myDir);
		for(ChannelSftp.LsEntry entry : list) {
			if (entry.getFilename().toString().endsWith(ext)) {
				System.out.println(entry.getFilename());

			}
		}
	}
	
	// List only entries from myDir recursively which refer to "ext"
	public static List<String> myStrings = new ArrayList<String>();

	public static List<String> getFilesRecursivly(ChannelSftp channelSftp, String myDir) throws SftpException, IOException {
		channelSftp.cd(myDir);

		Vector<ChannelSftp.LsEntry> list = channelSftp.ls(myDir);
		
		for(ChannelSftp.LsEntry entry : list) {
			if (entry.getAttrs().isDir()) {
				if(isContain(entry.getFilename())) {
					continue;
					// System.out.println(myDir + entry.getFilename() + " is stop by filter");
				} else {
					// System.out.println(myDir + "/"  + entry.getFilename() + " is a Folder");
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
	
	public static  void printProperties(ChannelSftp channelSftp, List<String> myStrings) {
		System.out.println();
		if (myStrings.isEmpty()) {
			System.out.println("No Files Found");
		} else {
			for (String file : myStrings) {
				System.out.println(file);
				BufferedImage img = null;
				try {
					InputStream is = channelSftp.get(file); 
					img = ImageIO.read(is);
					System.out.println(" width : " + img.getWidth());
					System.out.println(" height: " + img.getHeight());
					is.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}

	public static void close(ChannelSftp channelSftp) throws JSchException {
		if (channelSftp.getSession() != null) {
			channelSftp.getSession().disconnect();
		}
		if (channelSftp != null) {
			channelSftp.disconnect();
		}
	}
	
	public static void duration(long startTime, long endTime) {
		long totalTime = endTime - startTime;
		
		int seconds = (int) (totalTime / 1000) % 60 ;
		int minutes = (int) ((totalTime / (1000*60)) % 60);
		int milisec = (int) (totalTime - ((seconds * 1000) + (minutes * 60 * 1000)));
		
		StringBuilder sb = new StringBuilder(64);
		sb.append("Elapsed time: ");
        sb.append(minutes);
        sb.append(" min, ");
        sb.append(seconds);
        sb.append(" sec. ");
        sb.append(milisec);
        sb.append(" milsec.");
        
		System.err.println(sb.toString());
	}
	
	
	
	public static void main(String[] args) throws SftpException, IOException, JSchException {
		System.out.println("===== START =====");
		long startTime = System.currentTimeMillis();
		
		ChannelSftp channelSftp = myConnection();
		//		getAllEntries(channelSftp, SFTPWORKINGDIR);
		//		System.out.println("=============================================");
		//		getFiles(channelSftp, SFTPWORKINGDIR);
		//		System.out.println("=============================================");

		List<String> myStrings = getFilesRecursivly(channelSftp, SFTP_WORKINGDIR);
		printProperties(channelSftp, myStrings);

		System.out.println("===== FINISH =====");
		close(channelSftp);
		
		long endTime   = System.currentTimeMillis();
		duration(startTime, endTime);
		
	}

}