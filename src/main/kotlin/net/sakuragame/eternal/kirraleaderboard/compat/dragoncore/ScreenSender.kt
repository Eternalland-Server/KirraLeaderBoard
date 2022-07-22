package net.sakuragame.eternal.kirraleaderboard.compat.dragoncore

import com.google.common.collect.Lists
import net.sakuragame.eternal.dragoncore.network.PacketSender
import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry
import org.bukkit.entity.Player

@Suppress("SameParameterValue")
object ScreenSender {

    const val screenId = "rank_main"

    fun openScreen(player: Player, init: Boolean = false) {
        if (init) {
            val board = KirraLeaderBoardAPI.leaderBoards[0]
            doSyncVariable(player, board, 1)
        }
        PacketSender.sendOpenGui(player, screenId)
    }

    fun doSyncVariable(player: Player, board: AbstractLeaderBoard, page: Int) {
        val entryTotal = board.getAll()
        val entryPlayer = entryTotal.firstOrNull { it.playerName == player.name } ?: return
        syncPlayer(player, entryPlayer)
        syncLeaderBoard(player, board, page)
    }

    private fun syncPlayer(player: Player, entry: LeaderBoardEntry) {
        PacketSender.sendSyncPlaceholder(player, mutableMapOf<String, String>().apply {
            put("my_rank", entry.index.toString())
            put("my_data", entry.value.toString())
        })
    }

    private fun syncLeaderBoard(player: Player, board: AbstractLeaderBoard, page: Int) {
        val entryTotal = board.getAll()
        val partition = Lists.partition(entryTotal, 10)
        if (page > partition.size) {
            return
        }
        val currentEntries = partition[page - 1]
        for (index in 1..10) {
            val entry = currentEntries.getOrNull(index - 1) ?: continue
            PacketSender.sendSyncPlaceholder(player, mutableMapOf<String, String>().apply {
                put("ranking_pos_$index", entry.index.toString())
                put("ranking_name_$index", entry.playerName)
                put("ranking_data_$index", entry.value.toString())
            })
        }
    }
}