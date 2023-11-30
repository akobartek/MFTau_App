package pl.mftau.mftau.leaders.domain.usecase

import kotlinx.datetime.Clock
import pl.mftau.mftau.leaders.data.DbEmausRepository
import pl.mftau.mftau.leaders.domain.model.Emaus
import pl.mftau.mftau.leaders.domain.model.Person
import kotlin.random.Random

class DrawNewEmausesUseCase(val repository: DbEmausRepository) {
    companion object {
        private const val MAX_TRIES = 40
        private const val MAX_SINGLE_DRAWS = 20
    }

    suspend operator fun invoke(people: List<Person>): Boolean {
        val peopleIds = people.map { it.id }
        val drawsMap: HashMap<String, MutableSet<String>> = hashMapOf()
        peopleIds.forEach { drawsMap[it] = mutableSetOf(it) }
        repository.getAllDraws().forEach { emaus ->
            drawsMap[emaus.person1]?.add(emaus.person2)
            drawsMap[emaus.person2]?.add(emaus.person1)
        }

        // return false if any person was drawn with every other
        if (peopleIds.any { drawsMap[it]?.containsAll(peopleIds) == true })
            return false

        try {
            var numberOfTry = 1
            while (numberOfTry <= MAX_TRIES) {
                val notDrawnPeopleIds = peopleIds.toMutableList()
                val listOfPairs = arrayListOf<Pair<String, String>>()

                draw@ while (notDrawnPeopleIds.size >= 2) {
                    var successDraw: Pair<String, String>? = null
                    run singleDraw@{
                        repeat(MAX_SINGLE_DRAWS) {
                            val draw: Pair<String, String> = singleDraw(notDrawnPeopleIds)
                            if (drawsMap[draw.first]?.contains(draw.second) == false
                                && drawsMap[draw.second]?.contains(draw.first) == false
                            ) {
                                successDraw = draw
                                return@singleDraw
                            }
                        }
                    }
                    if (successDraw == null) break@draw

                    notDrawnPeopleIds.apply { remove(successDraw!!.first); remove(successDraw!!.second) }
                    listOfPairs.add(successDraw!!)
                }

                if (notDrawnPeopleIds.size >= 2) ++numberOfTry
                else {
                    val drawTime = Clock.System.now().toEpochMilliseconds()
                    listOfPairs.map {pair ->
                        Emaus(
                            person1 = pair.first,
                            person2 = pair.second,
                            drawTime = drawTime
                        )
                    }.let { emausList ->
                        repository.insertDraws(emausList)
                        return true
                    }
                }
            }
            return false
        } catch (exc: Exception) {
            println("Przykra sprawa $exc")
            return false
        }
    }

    private fun singleDraw(listOfIds: MutableList<String>): Pair<String, String> {
        val firstID = listOfIds[Random.nextInt(listOfIds.size)]
        val secondID = listOfIds[Random.nextInt(listOfIds.size)]
        if (firstID == secondID)
            return singleDraw(listOfIds)

        return Pair(firstID, secondID)
    }
}