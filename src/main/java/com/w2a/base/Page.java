package com.w2a.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.w2a.utilities.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Page {

    public static WebDriver driver;
    public static TopMenu menu ;
    public static Properties config = new Properties();
    public static Properties or = new Properties();
    public static FileInputStream fis ;
    public static Logger log = Logger.getLogger(Page.class);
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"/src/test/resources/excel/TestData.xlsx");
    public static WebDriverWait wait;
    public ExtentReports rep = ExtentManager.getInstance();
    public static ExtentTest test ;


    public Page(){

        if (driver == null) {


            try {
                fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/Config.properties");
                config.load(fis);
                log.debug("Config File loaded");

                fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/OR.properties");
                or.load(fis);
                log.debug("OR file loaded");

            } catch (IOException e) {
                e.printStackTrace();
            }

            Map<String,Object> prefs = new HashMap<String,Object>();
            prefs.put("credentials_enable_service",false);
            prefs.put("profile.password_manager_enabled",false);
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("excludeSwitches", new String[]{"enable-automation"});

            if (config.getProperty("browser").equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--disable-extensions");
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                log.debug("Chrome driver loaded");
            }
            else if (config.getProperty("browser").equals("ie")) {
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/src/test/resources/executables/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                log.debug("IE driver loaded");

            }

            else if (config.getProperty("browser").equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/test/resources/executables/geckodriver.exe");
                driver = new FirefoxDriver();
                log.debug("gecko driver loaded");

            }

            driver.get(config.getProperty("testURL"));
            log.debug("launching testURL");
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
            wait = new WebDriverWait(driver,5);
            log.debug("Timeout has been set");
        }

    }


    public boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return  true;
        }
        catch (NoSuchElementException e){
            return false;
        }

    }

    public By byLocator(String locator){
        if (locator.endsWith("_CSS")) {
            return By.cssSelector(or.getProperty(locator));
        }
        else if (locator.endsWith("_XPATH")){
            return By.xpath(or.getProperty(locator));
        }
        else if(locator.endsWith("_ID")){
            return By.id(or.getProperty(locator));
        }
        return By.id(locator);
    }


    public void click(String locator){

        driver.findElement(byLocator(locator)).click();
        test.log(LogStatus.INFO,"Clicked on "+locator);
    }

    public void type(String locator,String value){

        driver.findElement(byLocator(locator)).sendKeys(value);
        test.log(LogStatus.INFO,"Typed on locator "+locator+" and value "+value);
    }

    public void selectValue(String locator , String value){

        WebElement dropDown = driver.findElement(byLocator(locator));
        Select select = new Select(dropDown);
        select.selectByVisibleText(value);

        test.log(LogStatus.INFO,"Selected from dropdown locator "+locator+" and value "+value);
    }

    public  static void quit(){
        driver.quit();
    }


}
