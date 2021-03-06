/*
 * Developer: Swaroop <durgaswaroop@gmail.com>
 * Date: March 2017
 */

package delorean.commands

import java.io.File
import java.nio.file.{Files, Paths}

import delorean.{CURRENT_INDICATOR, TIME_MACHINE}
import delorean.FileOps._
import org.apache.commons.io.FileUtils
import org.junit.Assert._
import org.junit.{AfterClass, BeforeClass, Test}

import scala.util.Try

class CreateTimelineTest {

  /**
    * Checks timeline creation flow by creating a new timeline and  checking if the
    * current indicator is pointing to the new timeline
    */
  @Test
  def createTimelineTest(): Unit = {
    CreateTimeLine("master")
    assertEquals("master", getLinesOfFile(CURRENT_INDICATOR).head)

    CreateTimeLine("develop")
    assertEquals("develop", getLinesOfFile(CURRENT_INDICATOR).head)
  }
}

object CreateTimelineTest {
  @BeforeClass
  def callToRide(): Unit = {
    // This will make sure it creates all the required files for the test. We are checking for CURRENT_INDICATOR
    // instead of TIME_MACHINE because .tm could be created by config test
    if (!Files.exists(Paths.get(CURRENT_INDICATOR))) new delorean.commands.Ride
  }

  @AfterClass
  def tearDown(): Unit = {
    println("Tearing Down '.tm/' directory created for StageTest")
    Try(FileUtils.deleteDirectory(new File(TIME_MACHINE)))
  }
}
