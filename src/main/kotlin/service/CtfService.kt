package service

import Ctf

interface CtfService {
    //Adds a competitor with ctf ID, a group ID and a score on the CTF table.
    fun addCompetitor(command: MutableList<String>)
    //Deletes a specified competitor from a CTF on the CTF table.
    fun deleteCompetitor(command: MutableList<String>)
    //Adds each column of CTFS to a list of CTF dataclasses
    fun listOfCTFs(): MutableList<Ctf>
}