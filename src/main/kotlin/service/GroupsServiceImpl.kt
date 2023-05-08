package service

import Ctf
import Groups
import i
import java.sql.DriverManager
import java.sql.SQLException

class GroupsServiceImpl: GrupsService {
    override fun checkDB() {
        val connection = DriverManager.getConnection("jdbc:h2:./default", "user", "user")
        var sql = "SELECT * FROM GRUPOS"
        i("service.GroupsServiceImpl.checkDB" ,"Selecting all data from groups table")
        try{
            val statement = connection.createStatement()
            val execution = statement.executeQuery(sql)
            execution.close()
            statement.close()
            connection.close()
            i("service.GroupsServiceImpl.checkDB" ,"The groups table already exists")
        }catch (e: SQLException){
            val statement = connection.createStatement()
            i("service.GroupsServiceImpl.checkDB" ,"The groups table doesn't exists")
            sql = "CREATE TABLE GRUPOS(grupoid INT NOT NULL AUTO_INCREMENT," +
                    "grupodesc VARCHAR(100) NOT NULL," +
                    "mejorposCTFid INT," +
                    "PRIMARY KEY (grupoid));"

            statement.executeUpdate(sql)
            i("service.GroupsServiceImpl.checkDB" ,"Groups table created.")

            sql = "insert into grupos(grupoid, grupodesc) values(1, '1DAM-G1');" +
                    "insert into grupos(grupoid, grupodesc) values(2, '1DAM-G2');" +
                    "insert into grupos(grupoid, grupodesc) values(3, '1DAM-G3');" +
                    "insert into grupos(grupoid, grupodesc) values(4, '1DAW-G1');" +
                    "insert into grupos(grupoid, grupodesc) values(5, '1DAW-G2');" +
                    "insert into grupos(grupoid, grupodesc) values(6, '1DAW-G3');"

            statement.executeUpdate(sql)
            statement.close()
            connection.close()

            i("service.GroupsServiceImpl.checkDB" ,"Values inserted into Groups table.")
        }
    }

    override fun showCompetitor(command: String): List<Groups> {
        TODO("Not yet implemented")
    }

    override fun calcBestCtfsById(): MutableMap<Int, Pair<Int, Ctf>> {
        TODO("Not yet implemented")
    }
}