package net.sakuragame.eternal.kirraleaderboard.leaderboard

import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType.BIG_FIRST
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType.SMALL_FIRST
import net.sakuragame.eternal.kirraleaderboard.toLeaderBoardEntry

@Suppress("LeakingThis", "MemberVisibilityCanBePrivate")
abstract class AbstractLeaderBoard<T : Comparable<T>> {

    init {
        KirraLeaderBoardAPI.leaderBoards += this
    }

    abstract val category: Category

    abstract var sortedMap: MutableMap<Int, T>

    abstract val type: SortType

    abstract fun refreshInput()

    fun getFirst(): LeaderBoardEntry<T>? {
        return sortedMap
            .toList()
            .firstOrNull()
            ?.toLeaderBoardEntry(1)
    }

    fun getSecond(): LeaderBoardEntry<T>? {
        return sortedMap
            .toList()
            .getOrNull(1)
            ?.toLeaderBoardEntry(2)
    }

    fun getThird(): LeaderBoardEntry<T>? {
        return sortedMap
            .toList()
            .getOrNull(2)
            ?.toLeaderBoardEntry(3)
    }

    fun getLast(): LeaderBoardEntry<T>? {
        return sortedMap
            .toList()
            .lastOrNull()
            ?.toLeaderBoardEntry(sortedMap.size)
    }

    fun getAll(): MutableList<LeaderBoardEntry<T>> {
        val toReturn = mutableListOf<LeaderBoardEntry<T>>()
        sortedMap.onEachIndexed { index, entry ->
            toReturn += entry.toPair().toLeaderBoardEntry(index)
        }
        return toReturn
    }

    fun getByPlayerName(name: String): LeaderBoardEntry<T>? {
        return getAll().firstOrNull { it.playerName == name }
    }

    fun doInternalSort(inputMap: MutableMap<Int, T>) {
        sortedMap = when (type) {
            SMALL_FIRST -> inputMap
                .toList()
                .sortedByDescending { (_, value) -> value }
                .toMap()
                .toMutableMap()

            BIG_FIRST -> inputMap
                .toList()
                .sortedBy { (_, value) -> value }
                .toMap()
                .toMutableMap()
        }
    }
}