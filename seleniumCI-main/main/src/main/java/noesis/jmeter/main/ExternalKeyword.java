package noesis.jmeter.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import us.abstracta.jmeter.javadsl.core.DslTestPlan;

public class ExternalKeyword {
	public static String COOKIE_JAVA_VALUE = "COOKIE_JAVA_VALUE";
	public static String FILENAMETEMP_DIR = "JMETER_TEMP.jmx";

	public void testPerformance(String cookie, String fileContent) throws IOException {
		fileContent = fileContent.replaceAll(COOKIE_JAVA_VALUE, cookie);
		FileWriter fw = new FileWriter(new File(FILENAMETEMP_DIR));
		fw.append(fileContent);
		fw.close();
		final DslTestPlan jmx = DslTestPlan.fromJmx(FILENAMETEMP_DIR);
		jmx.run();
	}

	public void testPerformanceByDir(String cookie, String fileDir) throws IOException {
		String fileContent = getFileContent(fileDir).replaceAll(COOKIE_JAVA_VALUE, cookie);
		testPerformance(cookie, fileContent);

	}

	public static String getFileContent(String fileDir) throws IOException {
		return new String(Files.readAllBytes(new File(fileDir).toPath()));

	}

//	public static void main(String[] args) throws IOException {
//		System.out.println(getFileContent("sitenoesis.jmx"));
//	}
}