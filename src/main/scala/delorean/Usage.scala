package delorean

/**
  * Prints usage information for all the Delorean commands.
  */
object Usage {
    def apply(command: String): Unit = command match {
        case "full" ⇒ fullUsage()
        case "ride" ⇒ rideUsage()
        case "add" ⇒ addUsage()
        case "pitstop" ⇒ pitstopUsage()
        case "config" ⇒ configUsage()
        case "version" ⇒ versionUsage()
        case "status" ⇒ statusUsage()
        case "show-timeline" ⇒ showTimeLineUsage()
        case _ ⇒ println(s"Usage information for the command $command is not available.")
    }

    def fullUsage(): Unit = {
        print(
            """usage: delorean <command> [<args>] [--version] [--help]
              |
              | These are the common Delorean commands:
              |
              | ride          Tell Delorean to initialize a new repo
              | add           Add files to repository
              | pitstop       Record the changes to the repo
              | config        Configuration options
              | show-timeline Shows all the pitstops in the current timeline
            """.stripMargin)
    }

    def rideUsage(): Unit = {
        print(
            """
              |Usage: delorean ride
              |                - Initializes a new Delorean Repository
              |
              |For more: delorean --help
            """.stripMargin)
    }

    def addUsage(): Unit = {
        print(
            """
              |Usage: delorean add <file1> <file2> ..
              |
              |For more: delorean --help
            """.stripMargin)
    }

    def pitstopUsage(): Unit = {
        print(
            """
              |Usage: delorean pitstop -rl "<Rider Log>"
              |                - Creates a new pitstop with the given Rider log.
              |
              |For more: delorean --help
            """.stripMargin)
    }

    def configUsage(): Unit = {
        print(
            """
              |Usage: delorean config <property name> <property value>
              |                - Adds/Updates delorean configuration
              |
              |For more: delorean --help
            """.stripMargin)
    }

    def versionUsage(): Unit = {
        print(
            """
              |Usage: delorean ([--version] [-V] [-v])
              |                - Prints the current version of delorean
            """.stripMargin)
    }

    def statusUsage(): Unit = {
        print(
            """
              |Usage: delorean status
              |                - Displays the current status of the repository
              |       delorean status <filename>
              |                - Displays the status of the file
              |                - Only one file is supported now
            """.stripMargin)
    }

    def showTimeLineUsage(): Unit = {
        print(
            """
              |Usage: delorean show-timeline [--short | -s] [--long | -l]
              |                - Displays all the pitstops in the current timeline. Default shows short format output
              |
              |       Output Format options:
              |       short - Displays a short pitstop hash and the rider log
              |       long  - Displays full information of the pitstop including time, rider name etc.
            """.stripMargin)
    }
}
