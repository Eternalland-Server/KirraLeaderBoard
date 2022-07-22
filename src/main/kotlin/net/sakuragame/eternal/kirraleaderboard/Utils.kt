package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry

fun Pair<Int, Double>.toLeaderBoardEntry(index: Int): LeaderBoardEntry {
    return LeaderBoardEntry(index, first, second)
}