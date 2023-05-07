package commandManagement

import dao.CtfDAOInterface
import service.CtfServiceImpl

class CommandExecutor {
    fun execution(commandGiven: String){
        val command = CommandParser().parse(commandGiven)
        when(command[0]){
            "-a" -> CtfServiceImpl().checkDB()//Calls function to add new competitor.
            "-d" -> TODO() //Calls function to delete a competitor.
            "-l" -> TODO() //Calls function to show group info.
        }
    }
}