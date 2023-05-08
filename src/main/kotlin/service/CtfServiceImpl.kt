package service

import Ctf
import Groups
import i
import java.sql.DriverManager

class CtfServiceImpl: CtfService {
    private val connection = DriverManager.getConnection("jdbc:h2:./default", "user", "user")
    override fun addCompetitor(command: MutableList<String>) {
        ManageDBs().checkCTFs()
        command.removeAt(0)

        val commandtoInt = command.map { it.toInt() }
        val ctfId = commandtoInt[0]
        val groupId = commandtoInt[1]
        val score = commandtoInt[2]

        if(command.count() == 3){
            val ctf = Ctf(ctfId, groupId, score)
            ManageDBs().checkGroupExistence(groupId)
            val groupName = ManageDBs().getGroupName(groupId)
            val sql = "INSERT INTO CTFS(ctfid, grupoid, puntuacion) VALUES (?, ?, ?)"
            val statement = connection.prepareStatement(sql)

            statement.setInt(1, ctf.ctfId)
            statement.setInt(2, ctf.groupId)
            statement.setInt(3, ctf.score)

            statement.executeUpdate()

            statement.close()
            connection.close()

            i("service.CtfServiceImpl.addCompetitor", "Processed: Added participation of the group $groupName" +
                    " on the CTF $ctfId with a score of $score")
            println("Processed: Added participation of the group $groupName on the CTF $ctfId with a score of $score")
            GroupsServiceImpl().addBestCtfs()
        }else{
            println("ERROR: Invalid number of parameters on the given command")
        }
    }

    override fun deleteCompetitor(command: MutableList<String>) {
        ManageDBs().checkCTFs()
        command.removeAt(0)

        val commandtoInt = command.map { it.toInt() }
        val ctfId = commandtoInt[0]
        val groupId = commandtoInt[1]

        if(command.count() == 2){
            ManageDBs().checkGroupExistence(groupId)
            val groupName = ManageDBs().getGroupName(groupId)
            val sql = "DELETE FROM CTFS WHERE CTFID = $ctfId AND GRUPOID = $groupId"
            val statement = connection.prepareStatement(sql)


            statement.executeUpdate()

            statement.close()
            connection.close()

            GroupsServiceImpl().addBestCtfs()
            i("service.CtfServiceImpl.addCompetitor", "Processed: Deleted participation of the group " +
                    "$groupName from the CTF $ctfId")
            println("Processed: Deleted participation of the group $groupName from the CTF $ctfId")
        }else{
            println("ERROR: Invalid number of parameters on the given command")
        }
    }

    override fun listOfCTFs(): MutableList<Ctf> {
        val connection = DriverManager.getConnection("jdbc:h2:./default", "user", "user")
        val statement = connection.createStatement()
        val allCtfsFromTable = statement.executeQuery("SELECT * FROM CTFS")
        val ctfs = mutableListOf<Ctf>()

        while (allCtfsFromTable.next()) {
            val ctfId = allCtfsFromTable.getInt("CTFID")
            val groupId = allCtfsFromTable.getInt("GRUPOID")
            val score = allCtfsFromTable.getInt("PUNTUACION")
            val ctf = Ctf(ctfId, groupId, score)
            ctfs.add(ctf)
        }

        allCtfsFromTable.close()
        statement.close()
        connection.close()

        return ctfs
    }
}