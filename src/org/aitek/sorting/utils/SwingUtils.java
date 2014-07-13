package org.aitek.sorting.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

import org.aitek.sorting.gui.ErrorForm;

public class SwingUtils {

	public static void centerFrame(JDialog jd) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = jd.getSize();
		jd.setLocation((screenSize.width / 2) - (frameSize.width / 2), (screenSize.height / 2) - (frameSize.height / 2));
	}

	/**
	 * inits the error form with the specified exceptio and shows it
	 * 
	 * @param ex
	 */
	public static void showFormError(Exception ex) {

		// creates the window with the error message
		ErrorForm ef = new ErrorForm(ex);

		// and shows it
		ef.setVisible(true);
	}

}
