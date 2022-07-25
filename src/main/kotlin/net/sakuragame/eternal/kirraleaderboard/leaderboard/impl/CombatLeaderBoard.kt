package net.sakuragame.eternal.kirraleaderboard.leaderboard.impl

import net.sakuragame.eternal.kirraleaderboard.compat.justattribute.CombatRecorder
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType

object CombatLeaderBoard : AbstractLeaderBoard() {

    override val category = Category.COMBAT

    override var sortedMap = mutableMapOf<Int, Double>()

    override val type = SortType.BIG_FIRST

    override fun refreshInput() {
        val inputMap = CombatRecorder.combatMap
            .mapValues { it.value.toDouble() }
            .toMutableMap()
        doInternalSort(inputMap)
    }
}