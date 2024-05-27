package sirius.challenge.crawler.filter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IrrelevantWordsMessageFilterTest {

    private val filter = IrrelevantWordsMessageFilter()
    private val message = "Enjoy The Creative Life with the TCL 40\" 1080p direct LED HDTV. It delivers premium picture quality and tremendous value in a sophisticated slim frame design perfect for bringing entertainment to any space. This flat screen LED HDTV features High Definition 1080p resolution for a sharper image and TCL True Color Technology for brilliant color and contrast. With direct LED backlighting, view darker blacks and luminous brightness while maintaining the best standards in energy efficiency."
    private val filtered = "Enjoy Creative Life TCL 40\" 1080p direct LED HDTV delivers premium picture quality tremendous value sophisticated slim frame design perfect bringing entertainment space flat screen LED HDTV features High Definition 1080p resolution sharper image TCL True Color Technology brilliant color contrast direct LED backlighting view darker blacks luminous brightness maintaining best standards energy efficiency"

    @Test
    fun test001_GetStopWordsFromNLTK(){
        val words = filter.filter(message)
        println(words)
        assertEquals(filtered, words)
    }
}
