package net.sakuragame.eternal.kirraleaderboard.leaderboard

import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType.BIG_FIRST
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType.SMALL_FIRST
import net.sakuragame.eternal.kirraleaderboard.toLeaderBoardEntry

@Suppress("MemberVisibilityCanBePrivate")
abstract class AbstractLeaderBoard {

    abstract val category: Category

    abstract var sortedMap: MutableMap<Int, Double>

    abstract val type: SortType

    abstract fun refreshInput()

    fun getFirst(): LeaderBoardEntry? {
        return sortedMap
            .toList()
            .firstOrNull()
            ?.toLeaderBoardEntry(1)
    }

    fun getSecond(): LeaderBoardEntry? {
        return sortedMap
            .toList()
            .getOrNull(1)
            ?.toLeaderBoardEntry(2)
    }

    fun getThird(): LeaderBoardEntry? {
        return sortedMap
            .toList()
            .getOrNull(2)
            ?.toLeaderBoardEntry(3)
    }

    fun getLast(): LeaderBoardEntry? {
        return sortedMap
            .toList()
            .lastOrNull()
            ?.toLeaderBoardEntry(sortedMap.size)
    }

    fun getAll(): MutableList<LeaderBoardEntry> {
        val toReturn = mutableListOf<LeaderBoardEntry>()
        sortedMap.onEachIndexed { index, entry ->
            toReturn += entry.toPair().toLeaderBoardEntry(index + 1)
        }
        return toReturn
    }

    fun getByPlayerName(name: String): LeaderBoardEntry? {
        return getAll().firstOrNull { it.playerName == name }
    }

    fun doInternalSort(inputMap: MutableMap<Int, Double>) {
        sortedMap = when (type) {
            BIG_FIRST -> inputMap
                .toList()
                .sortedByDescending { (_, value) -> value }
                .toMap()
                .toMutableMap()

            SMALL_FIRST -> inputMap
                .toList()
                .sortedBy { (_, value) -> value }
                .toMap()
                .toMutableMap()
        }
    }
}