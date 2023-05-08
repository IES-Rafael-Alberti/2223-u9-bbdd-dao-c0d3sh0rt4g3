package service

import Ctf
import Groups
import java.sql.DriverManager
import java.sql.SQLException

class GroupsServiceImpl: GroupsService {
    override fun listCompetitor(command: MutableList<String>){
        ManageDBs().checkCTFs()
        command.removeAt(0)

        val commandtoInt = command.map { it.toInt() }
        val groupIdGiven = commandtoInt[0]
        val connection = DriverManager.getConnection("jdbc:h2:./default", "user", "user")
        val sql = "SELECT * FROM GRUPOS WHERE GRUPOID = $groupIdGiven"

        try {
            val statement = connection.prepareStatement(sql)
            val groupInfo = statement.executeQuery()
            groupInfo.next()
            val groupDesc= groupInfo.getString("GRUPODESC")
            val bestCtf = groupInfo.getObject("MEJORPOSCTFID")
            println("Processed: Group $groupDesc participation info:")
            println("GROUP: $groupIdGiven $groupDesc BEST CTF: $bestCtf")

            addBestCtfs()
            statement.close()
            connection.close()
        }catch (e: SQLException){
            val sql = "SELECT * FROM GRUPOS;"
            val statement = connection.prepareStatement(sql)
            val groupInfo = statement.executeQuery()

            while (groupInfo.next()){
                val groupId = groupInfo.getInt("GRUPOID")
                val groupDesc= groupInfo.getString("GRUPODESC")
                val bestCtf = groupInfo.getObject("MEJORPOSCTFID")

                println("Processed: Group $groupDesc participation info:")
                println("GROUP: $groupId $groupDesc BEST CTF: $bestCtf\n")

                addBestCtfs()
            }
            statement.close()
            connection.close()
        }

    }

    override fun calcBestCtfsById(participants: List<Ctf>): List<Pair<Int, Ctf>> {
        val participantsByCTFId = participants.groupBy { it.ctfId }
        val participantsByGroupId = participants.groupBy { it.groupId }
        val mejoresCtfByGroupId = mutableMapOf<Int, Pair<Int, Ctf>>()
        participantsByCTFId.values.forEach { ctfs ->
            val ctfsOrderByScore = ctfs.sortedBy { it.score }.reversed()
            participantsByGroupId.keys.forEach { groupId ->
                val posicionNueva = ctfsOrderByScore.indexOfFirst { it.groupId == groupId }
                if (posicionNueva >= 0) {
                    val posicionMejor = mejoresCtfByGroupId.getOrDefault(groupId, null)
                    if (posicionMejor != null) {
                        if (posicionNueva < posicionMejor.first)
                            mejoresCtfByGroupId[groupId] = Pair(posicionNueva, ctfsOrderByScore[posicionNueva])
                    } else
                        mejoresCtfByGroupId[groupId] = Pair(posicionNueva, ctfsOrderByScore[posicionNueva])
                }
            }
        }
        return mejoresCtfByGroupId.mapNotNull { (groupId, pair) ->
            pair.second.let { Pair(groupId, it) }
        }
    }


    override fun addBestCtfs() {
        val connection = DriverManager.getConnection("jdbc:h2:./default", "user", "user")
        val participants = CtfServiceImpl().listOfCTFs()
        val mejoresResultados = calcBestCtfsById(participants)
        mejoresResultados.forEach { (groupId, pair) ->
            val ctfId = pair.ctfId
            val sql = "UPDATE GRUPOS SET mejorposCTFid = $ctfId WHERE grupoid = $groupId"
            val statement = connection.prepareStatement(sql)

            statement.executeUpdate()
        }

    }
}