import io.Source
import java.io.File
val fileStream = Source.fromFile("src/main/scala/io/scala.txt").getLines.toStream
