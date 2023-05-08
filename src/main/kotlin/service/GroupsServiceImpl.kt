package service

import Ctf
import Groups
import i
import java.sql.DriverManager
import java.sql.SQLException

class GroupsServiceImpl: GrupsService {
    override fun showCompetitor(command: String): List<Groups> {
        TODO("Not yet implemented")
    }

    override fun calcBestCtfsById(): MutableMap<Int, Pair<Int, Ctf>> {
        TODO("Not yet implemented")
    }
}