package dao

import Groups

//Data Access Object that acts like an intermediary between the program and the Groups Data Source
class GroupsDAO(private val programActionsOnGroups: GroupsDAOInterface): GroupsDAOInterface{
    override fun showCompetitor(command: String): List<Groups> {
        return programActionsOnGroups.showCompetitor(command)
    }
}