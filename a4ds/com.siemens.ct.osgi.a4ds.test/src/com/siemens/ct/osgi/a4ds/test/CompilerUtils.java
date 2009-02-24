package com.siemens.ct.osgi.a4ds.test;

import java.util.LinkedList;

import javax.annotation.processing.AbstractProcessor;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import com.siemens.ct.osgi.a4ds.Processor;

public class CompilerUtils {
	static String testFileDirectory = "C:/java/RCP/A4DS/com.siemens.ct.osgi.a4ds.test/src/com/siemens/ct/osgi/a4ds/test/data/";

	public static boolean compileAndProcessAnnotations(String filename) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				null, null, null);
		Iterable<? extends JavaFileObject> compilationUnits1 = fileManager
				.getJavaFileObjects(testFileDirectory + filename);
		CompilationTask task = compiler.getTask(null, fileManager, null, null,
				null, compilationUnits1);
		LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();

		Processor processor = new Processor();
		processor.setOutputDir("OSGI-INF");
		processors.add(processor);
		task.setProcessors(processors);
		return task.call();
	}
}
