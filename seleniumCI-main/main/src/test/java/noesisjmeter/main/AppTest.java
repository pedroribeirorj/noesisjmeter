package noesisjmeter.main;

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

public class AppTest {
	public static final String URL = "http://timntx.brazilsouth.cloudapp.azure.com:8084/NTX/ntxLogin";
	public static final String CHROMEDRIVER_DIR = "C:\\Users\\Usuario\\Downloads\\seleniumCI-main\\seleniumCI-main\\chromedriver.exe";
	public static String cookie = "";
	public static ChromeDriver driver;
	
	//VARS 
	public static String FILENAME_DIR="ntxLogin.jmx";
	public static String FILENAMETEMP_DIR="JMETER_TEMP.jmx";
	public static String COOKIE_JAVA_VALUE="COOKIE_JAVA_VALUE";
	public static String ENCODING = "UTF-8";

	@BeforeAll
	public static void config() {
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_DIR);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@AfterAll
	public static void close() {
		driver.close();
	}

	public static String getCookies() {
		// Retorna apenas o primeiro cookie encontrado
		Set<Cookie> c = driver.manage().getCookies();
		if (c.isEmpty())
			return "";
		return c.iterator().next().getValue();
	}

	public void testCase() {
		// Simulando a execução de um caso de teste que irá chamar o getCookies
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys("admin");
		WebElement we = driver.findElement(By.id("password"));
		we.sendKeys("admin");
		we.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id='dash-date-start-input']"));
		cookie = getCookies();

		System.out.println("Cookie depois do login: " + getCookies());
	}

	@Test
	public void testPerformance() throws IOException {
		testCase();

		String dados = FileUtils.readFileToString(new File(FILENAME_DIR), ENCODING);
		dados = dados.replaceAll(COOKIE_JAVA_VALUE, cookie);

		FileWriter fw = new FileWriter(new File(FILENAMETEMP_DIR));
		fw.write(dados);
		fw.close();
		
		final DslTestPlan jmx = DslTestPlan.fromJmx(FILENAMETEMP_DIR);
		jmx.run();
//		File f = new File(FILENAMETEMP_DIR);
//		f.delete();
//		f = null;
		
		System.out.println("Cookie utilizado: " + getCookies());
	}
	/**
	 * ENTRADA: IMPLEMENTAR KW GETCOOKIES ARMAZENAR GETCOOKIES EM VARIAVEL NTX
	 * CHAMAR A KW EXECUTEWITHCOOKIES(COOKIE, DIRETÓRIO DO ARQUIVO JMX COM COOKIE
	 * SETADO COMO "COOKIE_JAVA_VALUE")
	 * 
	 */
}