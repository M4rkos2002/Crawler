package sirius.challenge.crawler.crawler

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.By
import org.openqa.selenium.Cookie
import java.nio.file.Paths
import java.util.*
import kotlin.random.Random

class AmazonProductDescriptionCrawlerImpl : Crawler {
    override fun visit(url: String): String? {
        // Obtener la ruta al archivo chromedriver en la carpeta resources
        val classLoader = Thread.currentThread().contextClassLoader
        val resource = classLoader.getResource("chromedriver")
        val chromedriverPath = Paths.get(resource.toURI()).toFile().absolutePath

        // Configurar Selenium con opciones de Chrome
        System.setProperty("webdriver.chrome.driver", chromedriverPath)

        val options = ChromeOptions()
        options.addArguments("--headless")
        options.addArguments("--disable-gpu")
        options.addArguments("--no-sandbox")
        options.addArguments("--disable-dev-shm-usage")
        options.addArguments("user-agent=Mozilla/5.0 (Wcd ..indows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")

        val driver: WebDriver = ChromeDriver(options)

        return try {
            // Visitar la página inicial para obtener cookies y establecer sesión
            driver.get("https://www.amazon.com")
            Thread.sleep(2000 + Random.nextInt(2000).toLong()) // Espera aleatoria entre 2 y 4 segundos

            // Agregar cookies necesarias (si es necesario)
            driver.manage().addCookie(Cookie("session-id", "your-session-id"))
            driver.manage().addCookie(Cookie("ubid-main", "your-ubid-main"))

            // Visitar la página del producto
            driver.get(url)
            Thread.sleep(3000 + Random.nextInt(2000).toLong()) // Espera aleatoria entre 3 y 5 segundos

            // Extracción del contenido de la descripción del producto
            val description = driver.findElement(By.id("productDescription"))
            println(description.text)
            val paragraph = description.findElement(By.tagName("p"))
            paragraph.text

        } catch (e: Exception) {
            println(e.message)
            null
        } finally {
            driver.quit()
        }
    }
}

/*
    Document is a Library which has methods to make querrys easily
 */
