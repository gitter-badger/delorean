package delorean
package commands

import delorean.FileOps._
import delorean.commands.OutputFormat.OutputFormat

/**
  * Class for the command 'show-timeline'.
  *
  */
class ShowTimeLine(val outputFormat: OutputFormat = OutputFormat.SHORT) {
    val currentIndicatorFileLines: List[String] = getLinesOfFile(CURRENT_INDICATOR)

    if (currentIndicatorFileLines.isEmpty) {
        println("On timeline - default timeline ")
        println("No pitstops found in the repository.\nFor more information: delorean --help")
    }
    else {
        var currentPitstop: String = currentIndicatorFileLines.head
        if (outputFormat == OutputFormat.SHORT) {
            printShort(currentPitstop)
            var parentPitstop = parent(currentPitstop)
            while (parentPitstop nonEmpty) {
                printShort(parentPitstop)
                parentPitstop = parent(parentPitstop)
            }
        } else {
            printLong(currentPitstop)
            var parentPitstop = parent(currentPitstop)
            while (parentPitstop nonEmpty) {
                printLong(parentPitstop)
                parentPitstop = parent(parentPitstop)
            }
        }
    }

    def parent(pitstop: String): String = {
        val parent: String = getLinesOfFile(METADATA_FOLDER + pitstop).filter(_.contains("Parent")).head.split(":", 2)(1)
        // if (parent.nonEmpty) println("* " + parent.take(6))
        parent
    }

    def printShort(pitstop: String): Unit = {
        val metadata = Metadata(pitstop)
        println("* " + pitstop.take(6) + " " + metadata.riderLog.take(60))
    }

    def printLong(pitstop: String): Unit = {
        val metadata = Metadata(pitstop)
        println(
            s"""
               |pitstop ${pitstop.take(25)}
               |Rider: ${metadata.rider}
               |Time:  ${metadata.time}
               |
               |Rider log: ${metadata.riderLog}
                """.stripMargin)
    }
}

object OutputFormat extends Enumeration {
    type OutputFormat = Value
    val SHORT = Value(0)
    val LONG = Value(1)
}