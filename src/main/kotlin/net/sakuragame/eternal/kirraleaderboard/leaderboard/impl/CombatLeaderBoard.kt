package net.sakuragame.eternal.kirraleaderboard.leaderboard.impl

import com.sakuragame.eternal.justattribute.api.JustAttributeAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.eternal.kirraleaderboard.leaderboard.SortType
import net.sakuragame.serversystems.manage.client.api.ClientManagerAPI

object CombatLeaderBoard : AbstractLeaderBoard() {

    override val category = Category.COMBAT

    override var sortedMap = mutableMapOf<Int, Double>()

    override val type = SortType.BIG_FIRST

    override fun refreshInput() {
        val inputMap = mutableMapOf<Int, Double>()
        JustAttributeAPI.getAllRole().forEach {
            inputMap[ClientManagerAPI.getUserID(it.uuid)] = it.combatPower.value.toDouble()
        }
        doInternalSort(inputMap)
    }
}