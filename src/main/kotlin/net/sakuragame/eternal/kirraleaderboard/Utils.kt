package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry

fun <T : Comparable<T>> Pair<Int, T>.toLeaderBoardEntry(index: Int): LeaderBoardEntry<T> {
    return LeaderBoardEntry(index, first, second)
}