package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.CombatPowerLeaderBoard
import taboolib.common.platform.Awake
import taboolib.common.platform.function.submit

@Suppress("SpellCheckingInspection")
object KirraLeaderBoardAPI {

    val leaderBoards = mutableListOf<AbstractLeaderBoard>()

    @Awake
    fun i() {
        leaderBoards += CombatPowerLeaderBoard
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