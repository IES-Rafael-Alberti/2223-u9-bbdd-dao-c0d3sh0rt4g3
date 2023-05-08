package service

import i
import java.sql.DriverManager
import java.sql.SQLException

class ManageDBs {
    private val connection = DriverManager.getConnection("jdbc:h2:./default", "user", "user")
    private val statement = connection.createStatement()
    private var table = ""
    private var sql = ""

    fun checkGroups(){
        i("service.ManageDBs.checkGroups" ,"Selecting all data from groups table")
        table = "GRUPOS"
        try{
            sql = "SELECT * FROM $table"
            val statement = connection.createStatement()
            val execution = statement.executeQuery(sql)

            execution.close()
            statement.close()
            connection.close()

            i("service.ManageDBs.checkGroups" ,"The groups table already exists")
        }catch (e: SQLException){
            ManageDBs().createGroups()
        }
    }

    fun checkCTFs(){
        i("service.ManageDBs.checkCTFs" ,"Selecting all data from groups table")
        table = "CTFS"
        try{
            sql = "SELECT * FROM $table"
            val statement = connection.createStatement()
            val execution = statement.executeQuery(sql)

            execution.close()
            statement.close()
            connection.close()

            i("service.ManageDBs.checkCTFs" ,"The groups table already exists")
        }catch (e: SQLException){
            ManageDBs().createCtfs()
        }
    }

    private fun createGroups(){
        i("service.ManageDBs.createGroups" ,"The groups table doesn't exists")

        sql = "CREATE TABLE GRUPOS(grupoid INT NOT NULL AUTO_INCREMENT," +
                "grupodesc VARCHAR(100) NOT NULL," +
                "mejorposCTFid INT," +
                "PRIMARY KEY (grupoid));"

        statement.executeUpdate(sql)

        i("service.ManageDBs.createGroups" ,"Groups table created")

        insertIntoGroups()
        createCtfs()
        alterGroups()
    }

    private fun insertIntoGroups(){
        sql = "insert into grupos(grupoid, grupodesc) values(1, '1DAM-G1');" +
                "insert into grupos(grupoid, grupodesc) values(2, '1DAM-G2');" +
                "insert into grupos(grupoid, grupodesc) values(3, '1DAM-G3');" +
                "insert into grupos(grupoid, grupodesc) values(4, '1DAW-G1');" +
                "insert into grupos(grupoid, grupodesc) values(5, '1DAW-G2');" +
                "insert into grupos(grupoid, grupodesc) values(6, '1DAW-G3');"

        statement.executeUpdate(sql)
        statement.close()
        connection.close()

        i("service.ManageDBs.insertIntoGroups" ,"Values inserted into Groups table.")
    }

    private fun createCtfs(){
        i("service.ManageDBs.createCtfs" ,"The CTFs table doesn't exists")

        val connection = DriverManager.getConnection("jdbc:h2:./default", "user", "user")
        val statement = connection.createStatement()
        sql = "CREATE TABLE CTFS(" +
                "CTFid INT NOT NULL," +
                "grupoid INT NOT NULL," +
                "puntuacion INT NOT NULL," +
                "PRIMARY KEY (CTFid, grupoid));"

        statement.executeUpdate(sql)
        statement.close()
        connection.close()

        i("service.ManageDBs.createCtfs" ,"CTFs table created")
    }

    private fun alterGroups(){
        val connection = DriverManager.getConnection("jdbc:h2:./default", "user", "user")
        val statement = connection.createStatement()
        sql = "ALTER TABLE GRUPOS" +
                " ADD FOREIGN KEY (mejorposCTFid, grupoid)" +
                " REFERENCES CTFS(CTFid,grupoid);"

        statement.executeUpdate(sql)
        statement.close()
        connection.close()

        i("service.ManageDBs.alterGroups" ,"Groups table altered")
    }
}