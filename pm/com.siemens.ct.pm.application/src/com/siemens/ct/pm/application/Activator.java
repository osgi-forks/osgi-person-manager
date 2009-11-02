package com.siemens.ct.pm.application;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator {

	private final Logger logger = LoggerFactory.getLogger(Activator.class);

	@Override
	public void start(BundleContext context) throws Exception {
		// The look & feel is set in the file
		// PersonManagerApplication.properties

		String laf = null;
		try {
			// try {
			// Runnable lnfSetter = new Runnable() {
			// public void run() {
			// // SubstanceLookAndFeel
			// // .setSkin("org.jvnet.substance.skin.RavenGraphiteGlassSkin");
			// try {
			// UIManager
			// .setLookAndFeel(new SubstanceMagmaLookAndFeel());
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
			// };
			// SwingUtilities.invokeAndWait(lnfSetter);
			//
			// } catch (Throwable t) {
			// }

			if (laf == null) {
				for (LookAndFeelInfo info : UIManager
						.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						laf = info.getClassName();
						break;
					}
				}
			}
			if (laf == null) {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} else {
				UIManager.setLookAndFeel(laf);
			}

		} catch (Exception e) {
			logger.warn(e.getMessage());
		}

	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}

}
