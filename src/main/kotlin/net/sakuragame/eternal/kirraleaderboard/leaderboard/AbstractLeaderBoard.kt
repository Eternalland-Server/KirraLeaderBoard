package net.sakuragame.eternal.kirraleaderboard.leaderboard

import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType.BIG_FIRST
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType.SMALL_FIRST

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

    fun getFirst(): Pair<Int, T>? {
        return sortedMap
            .toList()
            .firstOrNull()
    }

    fun getSecond(): Pair<Int, T>? {
        return sortedMap
            .toList()
            .getOrNull(1)
    }

    fun getThird(): Pair<Int, T>? {
        return sortedMap
            .toList()
            .getOrNull(2)
    }

    fun getLast(): Pair<Int, T>? {
        return sortedMap
            .toList()
            .lastOrNull()
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