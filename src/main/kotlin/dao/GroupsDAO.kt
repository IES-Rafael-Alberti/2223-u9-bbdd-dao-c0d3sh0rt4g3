package dao

import Groups
import service.GroupsServiceImpl

//Data Access Object that acts like an intermediary between the program and the Groups Data Source
class GroupsDAO: GroupsDAOInterface{
    private val programActionsOnGroups = GroupsServiceImpl()
    override fun showCompetitor(command: MutableList<String>): List<Groups> {
        return programActionsOnGroups.showCompetitor(command)
    }
}