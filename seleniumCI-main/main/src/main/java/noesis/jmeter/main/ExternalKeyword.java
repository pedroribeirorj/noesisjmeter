package noesis.jmeter.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import us.abstracta.jmeter.javadsl.core.DslTestPlan;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

public class ExternalKeyword {
	private static final String ENCODING = "UTF-8";
	public static String COOKIE_JAVA_VALUE="COOKIE_JAVA_VALUE";
	public static String FILENAMETEMP_DIR="JMETER_TEMP.jmx";

	public void testPerformance(String cookie, String fileContent) throws IOException {
		fileContent = fileContent.replaceAll(COOKIE_JAVA_VALUE, cookie);
		FileWriter fw = new FileWriter(new File(FILENAMETEMP_DIR));
		fw.append(fileContent);
		fw.close();
		final DslTestPlan jmx = DslTestPlan.fromJmx(FILENAMETEMP_DIR);
		jmx.run();
	}

}