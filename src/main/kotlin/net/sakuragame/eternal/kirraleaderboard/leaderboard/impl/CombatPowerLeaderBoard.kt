package net.sakuragame.eternal.kirraleaderboard.leaderboard.impl

import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType

object CombatPowerLeaderBoard : AbstractLeaderBoard<Int>() {

    override val name = "战斗力"

    override var sortedMap = mutableMapOf<Int, Int>()

    override val type = SortType.BIG_FIRST

    override fun refreshInput() {

    }
}