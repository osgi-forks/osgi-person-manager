package com.siemens.ct.osgi.a4ds.test.pm;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AnnotationTest {
	public static void main(String[] args) {
		String a4dsProjectLocation = "C:/java/RCP/A4DS/com.siemens.ct.osgi.a4ds";
		String pmProjectLocation = "C:/java/RCP/PM/com.siemens.ct.pm.ui.views.treeview.a4ds";
		String commandLine = "javac -cp \"" + a4dsProjectLocation + "/bin;"
				+ a4dsProjectLocation + "/lib/jdom.jar;" + pmProjectLocation
				+ "/bin\" -processor com.siemens.ct.osgi.a4ds.Processor "
				+ pmProjectLocation
				+ "/src/com/siemens/ct/pm/ui/views/treeview/a4ds/TreeView.java";

		System.out.println("Command: " + commandLine);

		Process process;
		try {
			process = Runtime.getRuntime().exec(commandLine);
			InputStream inputStream = process.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
