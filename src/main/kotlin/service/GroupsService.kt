package service

import Ctf
import Groups

interface GroupsService {
    //Shows the info of a specified group, if not found, shows info from all groups.
    fun showCompetitor(command: MutableList<String>): List<Groups>
    //Calcs the best group for each Ctf, returns a mutable map with all of them.
    fun calcBestCtfsById(): MutableMap<Int, Pair<Int, Ctf>>
}