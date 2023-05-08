package commandManagement

import dao.CtfDAO
import dao.GroupsDAO
import i

class CommandExecutor {
    fun execution(commandGiven: String){
        val command = CommandParser().parse(commandGiven).toMutableList()
        i("commandManagement.CommandExecutor.execution", "Command that starts with ${command[0]} received.")
        when(command[0]){
            //Calls function to add new competitor.
            "-a" -> CtfDAO().addCompetitor(command)
            //Calls function to delete a competitor.
            "-d" -> CtfDAO().deleteCompetitor(command)
            //Calls function to show group info.
            "-l" -> GroupsDAO().showCompetitor(command)
            else -> throw Exception("The command introduced doesn't exists")
        }
    }
}