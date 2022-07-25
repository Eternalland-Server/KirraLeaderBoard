package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.CoinsLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.CombatLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.LevelLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.PointsLeaderBoard
import taboolib.common.platform.Awake
import taboolib.common.platform.function.submit

@Suppress("SpellCheckingInspection")
object KirraLeaderBoardAPI {

    val leaderBoards = mutableListOf<AbstractLeaderBoard>()

    @Awake
    fun i() {
        leaderBoards.apply {
            this += LevelLeaderBoard
            this += CombatLeaderBoard
            this += CoinsLeaderBoard
            this += PointsLeaderBoard
        }
        refreshAll()
    }

    fun refreshAll() {
        submit(async = true) {
            leaderBoards.forEach {
                it.refreshInput()
            }
        }
    }
}