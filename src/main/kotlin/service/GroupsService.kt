package service

import Ctf
import Groups

interface GroupsService {
    //Shows the info of a specified group, if not found, shows info from all groups.
    fun listCompetitor(command: MutableList<String>)
    //Calcs the best group for each Ctf, returns a mutable map with all of them.
    fun calcBestCtfsById(participants: List<Ctf>): List<Pair<Int, Ctf>>
    //Updates the best CTFS
    fun addBestCtfs()
}