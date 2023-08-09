package noesis.jmeter.main;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Export this file to a JAR file and fill the "Secondary Data" field in NTX
			// with the path to the jar

//			boolean passed = true;
			String sessionId = args[0];
			String url = args[1];
			String xpath = args [2]; //Xpath received from NTX, if you don't select an object in NTX the "xpath" variable will be "xxxxx"

			String inputData = args[3]; // Input Data received from NTX, if you don't fill the "Input Data" field in NTX
										// the "inputData" variable will be "xxxxx"
			String tertiaryData = args[4]; // Tertiary Data received from NTX, if you don't fill the "Tertiary Data"
											// field in NTX the "tertiaryData" variable will be "xxxxx"
			String jmx = tertiaryData;
			String cookie = inputData;

			new ExternalKeyword().testPerformance(cookie, jmx);

			System.out.println("Passed");
			return;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

	}
}
