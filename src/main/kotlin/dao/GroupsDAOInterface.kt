package dao
import Groups

interface GroupsDAOInterface {
    //Shows the info of a specified group, if not found, shows info from all groups.
    fun showCompetitor(command: MutableList<String>): List<Groups>
}