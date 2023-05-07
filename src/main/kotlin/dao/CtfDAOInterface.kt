package dao

interface CtfDAOInterface {
    //Adds a competitor with ctf ID, a group ID and a score on the CTF table.
    fun addCompetitor(command: String)
    //Deletes specified competitor from the specified CTF on the GROUPS table.
    fun deleteCompetitor(command: String)
}