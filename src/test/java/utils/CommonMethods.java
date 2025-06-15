package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class CommonMethods extends PageInitializer{
    /**
     * This is the WebDriver instance that will be used throughout the tests.
     * It is declared as static so that it can be accessed from static methods.
     */
    public static WebDriver driver;
    /**
     * This method opens the browser and launches the application.
     * It reads the browser type from the configuration file and initializes the WebDriver accordingly.
     */

    public void openBrowserAndLaunchApplication() {
        //Declare the instance
        /* String browserName = ConfigReader.read("browser");*/
        switch (ConfigReader.read("browser")){
            case "Chrome":
                /*ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");*/
                driver=new ChromeDriver();//(options);
                break;
            case "FireFox":
                driver = new FirefoxDriver();
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Invalid Browser Name");
        }


        //maximize the window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //Navigate to https://www.syntaxprojects.com/synchronization-waits.php
        //take me to the url

        driver.get(ConfigReader.read("url"));

        // Initialize page objects

        initializerPageObjects();

        //*String url = ConfigReader.read(Constants.CONFIG_FILE_PATH, "url");
        //driver.get(url);
    }

    /**
     * This method closes the browser if it is open.
     * It checks if the driver is not null before attempting to quit the browser.
     */

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    /**
     * This method sends text to a WebElement, optionally clearing it first.
     * @param text the text to send
     * @param element the WebElement to send the text to
     * @param clear whether to clear the element before sending text
     */
    public static void sendText(String text, WebElement element, boolean clear) {
        if (clear) {
            element.clear();
        }
        element.sendKeys(text);
    }
    /**
     * This method sends text to a WebElement, clearing it first.
     * @param text the text to send
     * @param element the WebElement to send the text to
     */

    public static void sendText(String text, WebElement element) {
        element.clear();
        element.sendKeys(text);

    }
    /**
     * This method returns a WebDriverWait instance with the specified timeout.
     * @return WebDriverWait instance
     */

    public static WebDriverWait getWait(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
    }
    /**
     * This method waits for an element to be clickable.
     * @param element the WebElement to wait for
     */


    public static void waitForElementToBeClickable(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));

    }
    /**
     * This method clicks on a WebElement after waiting for it to be clickable.
     * @param element the WebElement to click
     */
    public static void click(WebElement element){
        waitForElementToBeClickable(element);
        element.click();

    }
    /**
     * This method returns a JavascriptExecutor instance for executing JavaScript.
     * @return JavascriptExecutor instance
     */
    public JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }
    /**
     * This method clicks on a WebElement using JavaScript.
     * @param element the WebElement to click
     */
    public void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    public static void selectFromDropDown(String value, WebElement element){
        Select sel=new Select(element);
        sel.selectByValue(value);
    }
    /**
     * This method selects an option from a dropdown by visible text.
     * @param element the WebElement representing the dropdown
     * @param text the visible text of the option to select
     */

    public static void selectFromDropDown( WebElement element,String text){
        Select sel=new Select(element);
        sel.selectByVisibleText(text);
    }
    /**
     * This method selects an option from a dropdown by index.
     * @param element the WebElement representing the dropdown
     * @param index the index of the option to select
     */

    public static void selectFromDropDown( WebElement element,int index){
        Select sel=new Select(element);
        sel.selectByIndex(index);
    }
    /**
     * This method takes a screenshot and saves it to a specified file path.
     * @param fileName the name of the screenshot file
     * @return byte array of the screenshot
     */
    public byte[] takeScreenshot(String fileName){
        //it accepts array of byte in cucumber for the screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picByte = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile,
                    new File(Constants.SCREENSHOT_FILEPATH +
                            fileName+" "+
                            getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picByte;
    }
    /**
     * This method returns the current timestamp formatted according to the specified pattern.
     * @param pattern the date format pattern
     * @return formatted timestamp as a String
     */

    public String getTimeStamp(String pattern){
        //this method will return the timestamp which we will add in ss method
        Date date =new Date();
        //12-01-1992-21-32-34
        //yyyy-mm-dd-hh-mm-ss
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    /**
     * This method generates a unique employee ID based on the current timestamp.
     * The format is MMddHHmmss (MonthDayHourMinuteSecond).
     * @return a unique employee ID as a String
     */


    public static String generateUniqueEmployeeId() {
        // Pattern: MMddHHmmss (MonthDayHourMinuteSecond)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return timestamp; // Example: EMP0508143052
    }
    /**
     * Optional main method to test the unique employee ID generation.
     * This can be used for quick testing without running the entire test suite.
     */

    // Optional main method to test
    public static void main(String[] args) {
        String empId = generateUniqueEmployeeId();
        System.out.println("Generated Employee ID: " + empId);
    }


    //make method to select the checkbox for a specific id

    public void selectEmployeeById(String targetId) {
        List<WebElement> allIDs = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));

        for (int i = 0; i < allIDs.size(); i++) {
            String idText = allIDs.get(i).getText().trim();

            if (idText.equals(targetId)) {
                int rowIndex = i + 1; // XPath rows are 1-based
                WebElement checkBox = driver.findElement(By.xpath("//table/tbody/tr[" + rowIndex + "]/td[1]"));
                checkBox.click();

                WebElement idLink = driver.findElement(By.xpath("//table/tbody/tr[" + rowIndex + "]/td[2]/a"));
                idLink.click();

                break;
            }
        }

    }
    //Method to upload a file


    public static String uploadFile(WebElement fileInput, String filePath) {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                throw new FileNotFoundException("File not found at path: " + filePath);
            }

            fileInput.sendKeys(file.getAbsolutePath());
            System.out.println("File uploaded successfully: " + file.getName());

        } catch (FileNotFoundException e) {
            System.err.println("File upload failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error during file upload: " + e.getMessage());
            e.printStackTrace(); // Optional: print stack trace for debugging
        }


        return filePath;
    }
    //Method to get selected text from dropdown
    public static String getSelectedDropdownText(WebElement dropdownElement, String dropdownName) {
        try {
            Select select = new Select(dropdownElement);
            String text = select.getFirstSelectedOption().getText().trim();
            if (text.isEmpty()) {
                System.err.println("No value selected in dropdown: " + dropdownName);
            }
            return text;
        } catch (Exception e) {
            System.err.println("Error reading dropdown (" + dropdownName + "): " + e.getMessage());
            return null;
        }


    }

    public void checkboxClick(WebElement checkbox) {
        try {
            if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                    System.out.println("Checkbox was not selected, now selected.");
                } else {
                    System.out.println("Checkbox is already selected.");
                }
            } else {
                throw new RuntimeException("Checkbox is not interactable.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to click on the checkbox: " + e.getMessage());
        }
    }





}
