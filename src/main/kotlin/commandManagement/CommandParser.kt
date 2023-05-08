package commandManagement

class CommandParser(){
    fun parse(commandToParse: String): MutableList<String>{
        val whiteSpaceRegex = "\\s+".toRegex()
        val commandContent = commandToParse.split(whiteSpaceRegex).toMutableList()
        if (valid(commandContent)){
            return commandContent
        }else{
            throw Exception("Only integers are accepted")
        }
    }

    private fun valid(commandContent:MutableList<String>): Boolean{
        val command = commandContent.toMutableList()
        command.removeAt(0)
        return command.all{it.toIntOrNull() != null}
    }
}