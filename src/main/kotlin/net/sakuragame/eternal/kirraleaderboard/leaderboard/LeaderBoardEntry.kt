package net.sakuragame.eternal.kirraleaderboard.leaderboard

import net.sakuragame.eternal.kirraleaderboard.UnitConvert

data class LeaderBoardEntry(val index: Int, val playerName: String, val value: Double) {

    constructor(index: Int, playerId: Int, value: Double) : this(index, "测试 $playerId 号", value)

//    constructor(index: Int, playerId: Int, value: T) : this(index, ClientManagerAPI.getUserName(playerId) ?: error("获取的用户名为空。"), value)

    fun convert(unit: UnitConvert): String {
        return UnitConvert.formatCN(unit, value.toString().toDouble())
    }
}