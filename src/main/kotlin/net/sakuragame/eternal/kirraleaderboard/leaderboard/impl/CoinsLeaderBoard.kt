package net.sakuragame.eternal.kirraleaderboard.leaderboard.impl

import net.sakuragame.eternal.gemseconomy.GemsEconomy
import net.sakuragame.eternal.gemseconomy.currency.EternalCurrency
import net.sakuragame.eternal.kirraleaderboard.UnitConvert
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType

object CoinsLeaderBoard : AbstractLeaderBoard() {

    override val category = Category.COINS

    override var sortedMap = mutableMapOf<Int, Double>()

    override val type = SortType.BIG_FIRST

    override fun printEntryPretty(entry: LeaderBoardEntry): String {
        return UnitConvert.doCommonInputInference(entry.value)
    }

    override fun refreshInput() {
        doInternalSort(GemsEconomy.getInstance().dataStore.getAllBalance(EternalCurrency.Coins))
    }
}