package parser;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageGrabber extends JScrollPane {
	Elements images;
	Document doc;
	JPanel panel;

	ImageGrabber(Document doc) {
		this.doc = doc;
		images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		panel = new JPanel(new GridLayout(images.size(), 1));
		grabImages();
		setViewportView(panel);
		setPreferredSize(new Dimension(350, 350));
	}

	private void grabImages() {
		// TODO Auto-generated method stub
		for (Element image : images) {
			String l = image.attr("src");
			if (l.length() > 0) {
				if (l.length() < 4) {
					l = doc.baseUri() + l.substring(1);
				} else if (!l.substring(0, 4).equals("http")) {
					l = doc.baseUri() + l.substring(1);
				}
				SwingLink label = new SwingLink(l,l);
				panel.add(label);
			}
		}
	}
}
