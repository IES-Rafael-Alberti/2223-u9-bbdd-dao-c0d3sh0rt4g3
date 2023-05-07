package service

import Ctf
import Groups
import java.sql.DriverManager
import java.sql.SQLException

class GroupsServiceImpl: GrupsService {
    override fun checkDB() {
        val connection = DriverManager.getConnection("jdbc:h2:./default", "user", "user")
        var sql = "SELECT * FROM GRUPOS"
        try{
            val statement = connection.createStatement()
            val execution = statement.executeQuery(sql)
            println("yes")
            execution.close()
            statement.close()
            connection.close()
        }catch (e: SQLException){
            sql = "CREATE TABLE GRUPOS(grupoid INT NOT NULL AUTO_INCREMENT," +
                    "grupodesc VARCHAR(100) NOT NULL," +
                    "mejorposCTFid INT," +
                    "PRIMARY KEY (grupoid));" +
                    "insert into grupos(grupoid, grupodesc) values(1, '1DAM-G1');" +
                    "insert into grupos(grupoid, grupodesc) values(2, '1DAM-G2');" +
                    "insert into grupos(grupoid, grupodesc) values(3, '1DAM-G3');" +
                    "insert into grupos(grupoid, grupodesc) values(4, '1DAW-G1');" +
                    "insert into grupos(grupoid, grupodesc) values(5, '1DAW-G2');" +
                    "insert into grupos(grupoid, grupodesc) values(6, '1DAW-G3');"
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("nope")
            statement.close()
            connection.close()
        }
    }

    override fun showCompetitor(command: String): List<Groups> {
        TODO("Not yet implemented")
    }

    override fun calcBestCtfsById(): MutableMap<Int, Pair<Int, Ctf>> {
        TODO("Not yet implemented")
    }
}