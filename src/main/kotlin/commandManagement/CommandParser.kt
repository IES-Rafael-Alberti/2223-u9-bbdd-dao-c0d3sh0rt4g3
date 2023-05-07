package commandManagement

class CommandParser(){
    fun parse(commandToParse: String):List<String>{
        val whiteSpaceRegex = "\\s+".toRegex()
        val groupData = commandToParse.split(whiteSpaceRegex)
        return groupData
    }
}