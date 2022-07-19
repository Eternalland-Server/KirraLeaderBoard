package net.sakuragame.eternal.kirraleaderboard.leaderboard

import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI

@Suppress("LeakingThis")
abstract class AbstractLeaderBoard<T : Number>(inputMap: Map<Int, T>) {

    init {
        refresh(inputMap)
        KirraLeaderBoardAPI.leaderBoards += this
    }

    abstract val name: String

    abstract val sortedMap: MutableMap<Int, T>

    abstract val sortType: SortType

    abstract fun refresh(inputMap: Map<Int, T>)

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
}