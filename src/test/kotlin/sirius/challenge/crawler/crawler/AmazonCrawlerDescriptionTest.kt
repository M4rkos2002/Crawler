package sirius.challenge.crawler.crawler

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AmazonCrawlerDescriptionTest {

    private val crawler = AmazonProductDescriptionCrawlerImpl()
    private val url = "https://www.amazon.com/gp/product/B00VVOCSOU"


    @Test
    fun test001_ExtractionIsNotNull(){
        val text = crawler.visit(url)
        assertNotNull(text)
    }

    @Test
    fun test002_ExtractsProperTextFormURL(){
        val text = crawler.visit(url)
        assertEquals(text, "Enjoy The Creative Life with the TCL 40\" 1080p direct LED HDTV. It delivers premium picture quality and tremendous value in a sophisticated slim frame design perfect for bringing entertainment to any space. This flat screen LED HDTV features High Definition 1080p resolution for a sharper image and TCL True Color Technology for brilliant color and contrast. With direct LED backlighting, view darker blacks and luminous brightness while maintaining the best standards in energy efficiency.")
    }

    @Test
    fun test003_ExtractsInvalidTestFromNotAmazonUrl(){
        val text = crawler.visit("https://www.youtube.com/watch?v=dQw4w9WgXcQ")  //this url has no id: productDescription
        assertNull(text)
    }


}