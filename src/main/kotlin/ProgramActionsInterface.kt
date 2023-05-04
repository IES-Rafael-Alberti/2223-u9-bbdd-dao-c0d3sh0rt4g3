interface ProgramActionsInterface {
    //Adds a competitor with ctf ID, a group ID and a score on the CTF table.
    fun addCompetitor(command: String)

    //Deletes specified competitor from the specified CTF on the GROUPS table.
    fun deleteCompetitor(command: String)
    //Shows the info of a specified group, if not found, shows info from all groups.
    fun showCompetitor(command: String): List<Group>
}