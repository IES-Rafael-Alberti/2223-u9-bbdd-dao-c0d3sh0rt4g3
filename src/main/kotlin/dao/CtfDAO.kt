package dao

import service.CtfServiceImpl

//Data Access Object that acts like an intermediary between the program and the Ctf Data Source
class CtfDAO: CtfDAOInterface {
    private val programActionsOnCtfs = CtfServiceImpl()
    override fun addCompetitor(command: MutableList<String>) {
        return programActionsOnCtfs.addCompetitor(command)
    }

    override fun deleteCompetitor(command: MutableList<String>) {
        return programActionsOnCtfs.deleteCompetitor(command)
    }
}