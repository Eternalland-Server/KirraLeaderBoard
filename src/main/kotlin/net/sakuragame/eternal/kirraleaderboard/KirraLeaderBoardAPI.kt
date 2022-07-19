package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard

@Suppress("SpellCheckingInspection")
object KirraLeaderBoardAPI {

    val leaderBoards = mutableListOf<AbstractLeaderBoard<out Number>>()
}