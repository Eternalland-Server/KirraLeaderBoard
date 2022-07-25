package net.sakuragame.eternal.kirraleaderboard.leaderboard.impl

import net.sakuragame.eternal.justlevel.JustLevel
import net.sakuragame.eternal.justlevel.api.JustLevelAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType
import taboolib.common.util.random

object LevelLeaderBoard : AbstractLeaderBoard() {

    override val category = Category.LEVEL

    override var sortedMap = mutableMapOf<Int, Double>()

    override val type = SortType.BIG_FIRST

    override fun refreshInput() {

    }
}