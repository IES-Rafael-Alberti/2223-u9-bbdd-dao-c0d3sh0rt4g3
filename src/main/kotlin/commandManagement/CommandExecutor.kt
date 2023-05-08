package commandManagement

import i
import service.GroupsServiceImpl
import service.ManageDBs

class CommandExecutor {
    fun execution(commandGiven: String){
        val command = CommandParser().parse(commandGiven)
        i("commandManagement.CommandExecutor.execution", "Command that starts with ${command[0]} received.")
        when(command[0]){
            "-a" -> ManageDBs().checkGroups() //Calls function to add new competitor.
            "-d" -> TODO() //Calls function to delete a competitor.
            "-l" -> TODO() //Calls function to show group info.
        }
    }
}