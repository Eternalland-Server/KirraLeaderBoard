package net.sakuragame.eternal.kirraleaderboard.leaderboard.impl

import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType

object CombatPowerLeaderBoard : AbstractLeaderBoard<Int>() {

    override val category = Category.COMBAT

    override var sortedMap = mutableMapOf<Int, Int>()

    override val type = SortType.BIG_FIRST

    override fun refreshInput() {

    }
}