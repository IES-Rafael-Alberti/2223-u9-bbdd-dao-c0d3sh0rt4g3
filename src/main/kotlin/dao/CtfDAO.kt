package dao

//Data Access Object that acts like an intermediary between the program and the Ctf Data Source
class CtfDAO(private val programActionsOnCtfs: CtfDAOInterface): CtfDAOInterface {
    override fun addCompetitor(command: String) {
        return programActionsOnCtfs.addCompetitor(command)
    }

    override fun deleteCompetitor(command: String) {
        return programActionsOnCtfs.deleteCompetitor(command)
    }
}