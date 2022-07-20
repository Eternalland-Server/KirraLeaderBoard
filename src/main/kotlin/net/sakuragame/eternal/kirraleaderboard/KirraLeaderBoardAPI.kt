package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import taboolib.common.platform.function.submit

@Suppress("SpellCheckingInspection")
object KirraLeaderBoardAPI {

    val leaderBoards = mutableListOf<AbstractLeaderBoard<out Comparable<*>>>()

    fun refreshAll() {
        submit(async = true) {
            leaderBoards.forEach {
                it.refreshInput()
            }
        }
    }
}