package sirius.challenge.crawler.wordColud

import com.kennycason.kumo.CollisionMode
import com.kennycason.kumo.WordCloud
import com.kennycason.kumo.bg.RectangleBackground
import com.kennycason.kumo.font.scale.LinearFontScalar
import com.kennycason.kumo.nlp.FrequencyAnalyzer
import com.kennycason.kumo.palette.ColorPalette
import java.awt.Color
import java.awt.Dimension

class RectangleWordCloudGenerator: WordCloudGenerator {

    override fun generateImage(filePath: String) {
        val frequencyAnalyzer = FrequencyAnalyzer()
        val wordFrequencies = frequencyAnalyzer.load(filePath)
        val dimension = Dimension(600, 600)
        val wordCloud = WordCloud(dimension, CollisionMode.RECTANGLE)
        wordCloud.setPadding(0)
        wordCloud.setBackground(RectangleBackground(dimension))
        wordCloud.setColorPalette(ColorPalette(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE))
        wordCloud.setFontScalar(LinearFontScalar(10, 40))
        wordCloud.build(wordFrequencies)
        wordCloud.writeToFile("src/main/resources/wordcloud_rectangle.png")
    }
}
