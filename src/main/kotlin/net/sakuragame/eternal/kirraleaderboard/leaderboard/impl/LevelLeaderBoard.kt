package net.sakuragame.eternal.kirraleaderboard.leaderboard.impl

import net.sakuragame.eternal.justlevel.JustLevel
import net.sakuragame.eternal.justlevel.api.JustLevelAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType

object LevelLeaderBoard : AbstractLeaderBoard() {

    override val category = Category.LEVEL

    override var sortedMap = mutableMapOf<Int, Double>()

    override val type = SortType.BIG_FIRST

    override fun printEntryPretty(entry: LeaderBoardEntry): String {
        val level = entry.value
        val realm = when {
            level < 2000 -> 1
            else -> (level / 2000).toInt()
        }
        val stage = ((level % 2000) / 200).toInt()
        val realmPrefix = JustLevelAPI.getRealPrefix(realm)
        return "$realmPrefix($stage)"
    }

    override fun refreshInput() {
        val inputMap = JustLevel.getStorageManager().allPlayerLevel
            .mapValues { it.value.toDouble() }
            .toMutableMap()
        doInternalSort(inputMap)
    }
}