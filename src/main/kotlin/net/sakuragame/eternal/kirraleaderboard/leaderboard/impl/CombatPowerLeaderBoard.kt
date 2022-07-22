package net.sakuragame.eternal.kirraleaderboard.leaderboard.impl

import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType
import taboolib.common.util.random

object CombatPowerLeaderBoard : AbstractLeaderBoard() {

    override val category = Category.COMBAT

    override var sortedMap = mutableMapOf<Int, Double>()

    override val type = SortType.BIG_FIRST

    override fun refreshInput() {
        doInternalSort(mutableMapOf<Int, Double>().apply {
            for (index in 1..100) {
                put(index, random(1.0, 500.0))
            }
        })
    }
}