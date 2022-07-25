package net.sakuragame.eternal.kirraleaderboard.leaderboard.impl

import net.sakuragame.eternal.gemseconomy.GemsEconomy
import net.sakuragame.eternal.gemseconomy.currency.EternalCurrency
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType

object PointsLeaderBoard : AbstractLeaderBoard() {

    override val category = Category.POINTS

    override var sortedMap = mutableMapOf<Int, Double>()

    override val type = SortType.BIG_FIRST

    override fun refreshInput() {
        doInternalSort(GemsEconomy.getInstance().dataStore.getAllBalance(EternalCurrency.Points))
    }
}