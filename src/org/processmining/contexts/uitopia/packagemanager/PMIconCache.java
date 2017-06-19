package org.processmining.contexts.uitopia.packagemanager;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class PMIconCache {

	/*
	 * Cache the retrieved icons. Prevents unnecessary access over the network.
	 */
	private static Map<String, ImageIcon> iconMap = new HashMap<String, ImageIcon>();
	private static Map<String, ImageIcon> iconPreviewMap = new HashMap<String, ImageIcon>();

	public static ImageIcon getIcon(PMPackage pack) throws MalformedURLException {
		synchronized (iconMap) {
			ImageIcon icon = iconMap.get(pack.getDescriptor().getLogoURL());
			if (icon == null) {
				URL logoURL;
				logoURL = new URL(pack.getDescriptor().getLogoURL());
				icon = new ImageIcon(logoURL);
				iconMap.put(pack.getDescriptor().getLogoURL(), icon);
//				System.out.println("[PMIconCache] Added cached icon for URL " + pack.getDescriptor().getLogoURL());
			} else {
//				System.out.println("[PMIconCache] Found cached icon for URL " + pack.getDescriptor().getLogoURL());
			}
			return icon;
		}
	}

	public static ImageIcon getIconPreview(PMPackage pack) {
		synchronized (iconPreviewMap) {
			ImageIcon icon = iconPreviewMap.get(pack.getDescriptor().getLogoURL());
			Image image = null;
			if (icon == null) {
				image = pack.getPreview(150, 150);
			}
			if (image != null) {
				icon = new ImageIcon(image);
				iconPreviewMap.put(pack.getDescriptor().getLogoURL(), icon);
//				System.out.println("[PMIconCache] Added cached icon preview for URL " + pack.getDescriptor().getLogoURL());
			} else {
//				System.out.println("[PMIconCache] Found cached icon preview for URL " + pack.getDescriptor().getLogoURL());
			}
			return icon;
		}
	}
}
