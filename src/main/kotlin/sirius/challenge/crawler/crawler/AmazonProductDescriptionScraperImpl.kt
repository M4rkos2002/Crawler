package sirius.challenge.crawler.crawler

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.By
import org.openqa.selenium.Cookie
import java.nio.file.Paths
import kotlin.random.Random

class AmazonProductDescriptionScraperImpl : Scraper {
    override fun visit(url: String): String? {
        val classLoader = Thread.currentThread().contextClassLoader
        val resource = classLoader.getResource("chromedriver")
        val chromedriverPath = Paths.get(resource.toURI()).toFile().absolutePath // just to show where is chromedriver

        System.setProperty("webdriver.chrome.driver", chromedriverPath)

        val options = ChromeOptions()
        options.addArguments("--headless")
        options.addArguments("--disable-gpu")
        options.addArguments("--no-sandbox")
        options.addArguments("--disable-dev-shm-usage")
        options.addArguments("user-agent=Mozilla/5.0 (Wcd ..indows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--disable-software-rasterizer");

        val driver: WebDriver = ChromeDriver(options)

        return try {
            driver.get("https://www.amazon.com") //visit main page
            Thread.sleep(Random.nextLong(1000, 3000)) //fake human

            driver.manage().addCookie(Cookie("session-id", "your-session-id")) //cookies to fake human
            driver.manage().addCookie(Cookie("ubid-main", "your-ubid-main"))

            driver.get(url) //visit the url
            Thread.sleep(Random.nextLong(2000, 4000)) //fake human

            val description = driver.findElement(By.id("productDescription")) //scrap data
            println("")
            println("------------------------------------------------------------------")
            println(description.text)
            println("------------------------------------------------------------------")
            println("")
            val paragraph = description.findElement(By.tagName("p")) // scrap paragraph
            paragraph.text

        } catch (e: Exception) {
            println("")
            println("------------------------------------------------------------------")
            println("Not found productDescription")
            println("------------------------------------------------------------------")
            println("")
            null
        } finally { //kill driver connection
            driver.quit()
        }
    }
}
