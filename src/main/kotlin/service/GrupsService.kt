package service

import Ctf
import Groups

interface GrupsService {
    //Checks if the DB exists before accessing the data.
    fun checkDB()
    //Shows the info of a specified group, if not found, shows info from all groups.
    fun showCompetitor(command: String): List<Groups>
    //Calcs the best group for each Ctf, returns a mutable map with all of them.
    fun calcBestCtfsById(): MutableMap<Int, Pair<Int, Ctf>>
}