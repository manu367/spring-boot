package com.example.demo;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.*;
import java.time.Duration;
import java.util.*;

public class InstagramFollowerRemover {

    private static final String INSTAGRAM_URL = "https://www.instagram.com/";
    private static final String USERNAME = "manu.life01";
    private static final String PASSWORD = "anil@123";
    private static WebDriver driver;

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        loginToInstagram();

        // Open your own profile
        driver.get(INSTAGRAM_URL + USERNAME + "/");
        Thread.sleep(3000);

        // Click on followers
        WebElement followersLink = driver.findElement(By.partialLinkText("followers"));
        followersLink.click();
        Thread.sleep(3000);

        List<String> usernames = readUsernames("D:\\Projects-Stages\\Working Projects\\Tutorial-Point\\bakend\\demo\\src\\main\\java\\com\\example\\demo\\usernames.txt");
        removeFollowers(usernames);

        driver.quit();
    }

    private static void loginToInstagram() throws InterruptedException {
        driver.get(INSTAGRAM_URL);
        Thread.sleep(2000);
        driver.findElement(By.name("username")).sendKeys(USERNAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(5000);

        // Dismiss popups if present
        try {
            driver.findElement(By.xpath("//button[contains(text(),'Not Now')]")).click();
            Thread.sleep(2000);
        } catch (Exception ignored) {}
        try {
            driver.findElement(By.xpath("//button[contains(text(),'Not Now')]")).click();
            Thread.sleep(2000);
        } catch (Exception ignored) {}
    }

    private static List<String> readUsernames(String filePath) {
        List<String> usernames = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                usernames.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("⚠️ Couldn't read file: " + e.getMessage());
        }
        return usernames;
    }

    private static void removeFollowers(List<String> usernames) throws InterruptedException {
        for (String targetUsername : usernames) {
            try {
                WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search']"));
                searchBox.clear();
                searchBox.sendKeys(targetUsername);
                Thread.sleep(200);

                // Click on the user
                WebElement user = driver.findElement(By.xpath("//span[contains(text(),'" + targetUsername + "')]/ancestor::div[@role='button']"));
                user.click();
                Thread.sleep(2000);

                // Click remove button
                WebElement removeBtn = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
                removeBtn.click();
                Thread.sleep(1000);

                WebElement confirmRemove = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
                confirmRemove.click();

                System.out.println("✅ Removed: " + targetUsername);
                Thread.sleep(3000);

            } catch (Exception e) {
                System.out.println("❌ Couldn't remove: " + targetUsername + " => " + e.getMessage());
            }
        }
    }
}
