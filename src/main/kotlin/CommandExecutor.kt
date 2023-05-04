class CommandExecutor {
    fun execution(commandToParse: String){
        val command = CommandParser().parse(commandToParse)
        when(command[0]){
            "-a" -> TODO() //Call function to add new competitor
            "-d" -> TODO() //Call function to delete a competitor
            "-l" -> TODO() //Call function to show group info
        }
    }
}