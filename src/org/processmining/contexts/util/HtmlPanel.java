package org.processmining.contexts.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.processmining.contexts.uitopia.annotations.Visualizer;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginLevel;
import org.processmining.framework.util.HTMLToString;

public class HtmlPanel extends javax.swing.JPanel implements HyperlinkListener {

	@Plugin(name = "Visualize HTML text", //
	level = PluginLevel.PeerReviewed,
	parameterLabels = { "HTML text" }, //
	returnLabels = { "HTML panel" }, //
	returnTypes = { HtmlPanel.class }, //
	userAccessible = true, //
	help = "Visualizes HTML text.", mostSignificantResult = 1)
	@Visualizer
	public static HtmlPanel visualizeHTML(PluginContext context, HTMLToString htmlText) {
		return new HtmlPanel(htmlText.toHTMLString(true));
	}

	/**
	 * Opens hyperlinks in standard browser (see
	 * http://forums.sun.com/thread.jspa?threadID=5403426)
	 */
	public void hyperlinkUpdate(HyperlinkEvent event) {
		if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			try {
				Desktop.getDesktop().browse(event.getURL().toURI());
			} catch (IOException e) {
			} catch (URISyntaxException e) {
			}
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8692121995363561628L;

	/** Creates new form HtmlReport */
	public HtmlPanel(String diagnosis) {
		initComponents(diagnosis);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents(String diagnosis) {

		jScrollPane1 = new javax.swing.JScrollPane();
		jTextPane1 = new javax.swing.JEditorPane();
		jTextPane1.setEditable(false);
		jTextPane1.addHyperlinkListener(this);

		jTextPane1.setContentType("text/html");
		jTextPane1.setText(diagnosis);
		jScrollPane1.setViewportView(jTextPane1);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE,
						641, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE,
						3860, Short.MAX_VALUE).addContainerGap()));
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JScrollPane jScrollPane1;
	private JEditorPane jTextPane1;
	// End of variables declaration
}
