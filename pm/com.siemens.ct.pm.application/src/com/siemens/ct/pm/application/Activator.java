package com.siemens.ct.pm.application;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Activator.start()XXXXXXXXXXXXXXXXXXXXx");
		String laf = null;
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					laf = info.getClassName();
					break;
				}
			}
			if (laf == null) {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} else {
				UIManager.setLookAndFeel(laf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}

}
