import commandManagement.CommandExecutor

fun main(args: Array<String>) {

    val participants = listOf(
        Ctf(1, 1, 3),
        Ctf(1, 2, 101),
        Ctf(2, 2, 3),
        Ctf(2, 1, 50),
        Ctf(2, 3, 1),
        Ctf(3, 1, 50),
        Ctf(3, 3, 5))
    val mejoresCtfByGroupId = calcBestResults(participants)
    println(mejoresCtfByGroupId)
    println("Command?")
    val command = readln()
    println(CommandExecutor().execution(command))
}

/**
 * TODO
 *
 * @param participants
 * @return devuelve un mutableMapOf<Int, Pair<Int, Ctf>> donde
 *      Key: el groupId del grupo
 *      Pair:
 *          first: Mejor posición
 *          second: Objeto CTF el que mejor ha quedado
 */
private fun calcBestResults(participants: List<Ctf>): MutableMap<Int, Pair<Int, Ctf>> {
    val participantsByCTFId = participants.groupBy { it.ctfId }
    var participantsByGroupId = participants.groupBy { it.groupId }
    val mejoresCtfByGroupId = mutableMapOf<Int, Pair<Int, Ctf>>()
    participantsByCTFId.values.forEach { ctfs ->
        val ctfsOrderByScore = ctfs.sortedBy { it.score }.reversed()
        participantsByGroupId.keys.forEach { groupId ->
            val posicionNueva = ctfsOrderByScore.indexOfFirst { it.groupId == groupId }
            if (posicionNueva >= 0) {
                val posicionMejor = mejoresCtfByGroupId.getOrDefault(groupId, null)
                if (posicionMejor != null) {
                    if (posicionNueva < posicionMejor.first)
                        mejoresCtfByGroupId.set(groupId, Pair(posicionNueva, ctfsOrderByScore.get(posicionNueva)))
                } else
                    mejoresCtfByGroupId.set(groupId, Pair(posicionNueva, ctfsOrderByScore.get(posicionNueva)))

            }
        }
    }
    return mejoresCtfByGroupId
}