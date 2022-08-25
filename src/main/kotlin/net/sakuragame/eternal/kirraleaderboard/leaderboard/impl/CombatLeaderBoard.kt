package net.sakuragame.eternal.kirraleaderboard.leaderboard.impl

import com.sakuragame.eternal.justattribute.JustAttribute
import net.sakuragame.eternal.kirraleaderboard.UnitConvert
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType
import net.sakuragame.eternal.kirraleaderboard.safeSubList

object CombatLeaderBoard : AbstractLeaderBoard() {

    override val category = Category.COMBAT

    override var sortedMap = mutableMapOf<Int, Double>()

    override val type = SortType.BIG_FIRST

    override fun printEntryPretty(entry: LeaderBoardEntry): String {
        return UnitConvert.doCommonInputInference(entry.value)
    }

    override fun refreshInput() {
        val inputMap = JustAttribute.getStorageManager().allPlayerCombatPower
            .mapValues { it.value.toDouble() }
            .toMutableMap()
        doInternalSort(inputMap)
    }
}