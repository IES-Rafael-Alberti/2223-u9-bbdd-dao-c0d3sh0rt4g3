package service

import Groups

interface CtfService {
    //Checks if the DB exists before accessing the data.
    fun checkDB()
    //Adds a competitor with ctf ID, a group ID and a score on the CTF table.
    fun addCompetitor(command: String)
    //Deletes a specified competitor from a CTF on the CTF table.
    fun deleteCompetitor(command: String)
}