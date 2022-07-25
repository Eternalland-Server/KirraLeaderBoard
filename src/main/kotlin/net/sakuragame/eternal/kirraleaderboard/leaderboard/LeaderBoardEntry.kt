package net.sakuragame.eternal.kirraleaderboard.leaderboard

import net.sakuragame.eternal.kirraleaderboard.UnitConvert
import net.sakuragame.serversystems.manage.client.api.ClientManagerAPI

data class LeaderBoardEntry(val index: Int, val playerName: String, val value: Double) {

    constructor(index: Int, playerId: Int, value: Double) : this(index, ClientManagerAPI.getUserName(playerId) ?: error("获取的用户名为空。"), value)

    fun convert(unit: UnitConvert): String {
        return UnitConvert.formatCN(unit, value.toString().toDouble())
    }
}