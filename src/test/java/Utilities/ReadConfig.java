package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;
	String path = "config.properties";

	public ReadConfig() {
		pro = new Properties();

		try {
			FileInputStream fis = new FileInputStream(path);
			pro.load(fis);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public String getBrowser() {
		String browser = pro.getProperty("browser");
		if (browser != null)
			return browser;
		else
			throw new RuntimeException("browser not specified in config file");
	}

	public String getApplicationURL() {
		String url = pro.getProperty("Baseurl");
		if (url != null)
			return url;
		else
			throw new RuntimeException("URL not specified in config file");
	}
	public String getUserName() {
		String username = pro.getProperty("Username");
		if (username != null)
			return username;
		else
			throw new RuntimeException("username not specified in config file");
	}
	public String getPassword() {
		String password = pro.getProperty("Password");
		if (password != null)
			return password;
		else
			throw new RuntimeException("password not specified in config file");
	}

}
