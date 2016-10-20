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
public class SFTPManager_Locate {
	
	public static final String SFTP_HOST = "172.31.10.117";
	public static final int    SFTP_PORT = 22;
	public static final String SFTP_USER = "root";
	public static final String SFTP_PASS = "mn2111td";
	
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
	
	public static void close(ChannelSftp channelSftp) throws JSchException {
		if (channelSftp.getSession() != null) {
			channelSftp.getSession().disconnect();
		}
		if (channelSftp != null) {
			channelSftp.disconnect();
		}
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
	public static void addZero(int time, StringBuilder sb) {
		if (time < 10) {
			sb.append(0);
		}
	}
	public static void duration(long startTime, long endTime) {
		long totalTime = endTime - startTime;
		
		int seconds = (int) (totalTime / 1000) % 60 ;
		int minutes = (int) ((totalTime / (1000*60)) % 60);
		int milisec = (int) (totalTime - ((seconds * 1000) + (minutes * 60 * 1000)));
		
		StringBuilder sb = new StringBuilder(64);
		sb.append("Elapsed time: ");
		addZero(minutes, sb);
        sb.append(minutes);
        sb.append(":");
        addZero(seconds, sb);
        sb.append(seconds);
        sb.append(".");
        addZero(milisec, sb);
        if (milisec < 100 && milisec > 10) {
        	sb.append(0);
        }
        sb.append(milisec);
        sb.append(" [mm:ss.SSS]");
        
		System.err.println(sb.toString());
	}
	
	
	
	public static void main(String[] args) throws SftpException, IOException, JSchException {
		System.out.println("===== START =====");
		long startTime = System.currentTimeMillis();
		
		ChannelSftp channelSftp = myConnection();
		
		// run "locate" in terminal and add results to String[]
		String[] strs = { 
				"/var/www/html/xtv-ws-client/webclient/images/cva/image-scene_locked_default_small.png",
				"/var/www/html/xtv-ws-client/webclient/images/standard/image-scene_locked_default_small.png",
				"/var/www/vstb/web/images/cva/image-scene_locked_default_small.png",
				"/var/www/webclient/html/src/stb/images/standard/image-scene_locked_default_small.png",
				"/var/www/webclient/html/src/web/skin/standard/blue/img/image-scene_locked_default_small.png",
				"/var/www/webclient/html/src/web/skin/standard/cva/img/image-scene_locked_default_small.png"
				};

		List<String> myStrings = new ArrayList<String>();
			for(int i =  0; i < strs.length; i++){
				myStrings.add(strs[i]);  //something like this?
			}
		
		printProperties(channelSftp, myStrings);

		System.out.println("===== FINISH =====");
		close(channelSftp);
		
		long endTime   = System.currentTimeMillis();
		duration(startTime, endTime);
		
	}

}