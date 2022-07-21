package net.sakuragame.eternal.kirraleaderboard.leaderboard

import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType.BIG_FIRST
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType.SMALL_FIRST
import net.sakuragame.eternal.kirraleaderboard.toLeaderBoardEntry

@Suppress("LeakingThis")
abstract class AbstractLeaderBoard<T : Comparable<T>> {

    init {
        refreshInput()
        KirraLeaderBoardAPI.leaderBoards += this
    }

    abstract val name: String

    abstract var sortedMap: MutableMap<Int, T>

    abstract val type: SortType

    abstract fun refreshInput()

    fun getFirst(): LeaderBoardEntry<T>? {
        return sortedMap
            .toList()
            .firstOrNull()
            ?.toLeaderBoardEntry()
    }

    fun getSecond(): LeaderBoardEntry<T>? {
        return sortedMap
            .toList()
            .getOrNull(1)
            ?.toLeaderBoardEntry()
    }

    fun getThird(): LeaderBoardEntry<T>? {
        return sortedMap
            .toList()
            .getOrNull(2)
            ?.toLeaderBoardEntry()
    }

    fun getLast(): LeaderBoardEntry<T>? {
        return sortedMap
            .toList()
            .lastOrNull()
            ?.toLeaderBoardEntry()
    }

    fun getByPlayerId(id: Int): LeaderBoardEntry<T>? {
        val result = sortedMap[id] ?: return null
        return LeaderBoardEntry(id, result)
    }

    private fun doInternalSort(inputMap: MutableMap<Int, T>) {
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