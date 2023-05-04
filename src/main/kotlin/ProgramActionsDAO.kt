//Data Acess Object that acts like an intermediary between the program and the Data Source
class ProgramActionsDAO(private val programActions: ProgramActionsInterface): ProgramActionsInterface {
    override fun addCompetitor(command: String) {
        return programActions.addCompetitor(command)
    }

    override fun deleteCompetitor(command: String) {
        return programActions.deleteCompetitor(command)
    }

    override fun showCompetitor(command: String): List<Group> {
        return programActions.showCompetitor(command)
    }
}