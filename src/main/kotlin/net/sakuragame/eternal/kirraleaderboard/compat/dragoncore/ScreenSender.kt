package net.sakuragame.eternal.kirraleaderboard.compat.dragoncore

import com.google.common.collect.Lists
import net.sakuragame.eternal.dragoncore.network.PacketSender
import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry
import org.bukkit.entity.Player

@Suppress("SameParameterValue")
object ScreenSender {

    private const val screenId = "rank_main"

    fun openScreen(player: Player, init: Boolean = false) {
        if (init) {
            val board = KirraLeaderBoardAPI.leaderBoards[0]
            doSyncVariable(player, board, 1)
        }
        PacketSender.sendOpenGui(player, screenId)
    }

    fun doSyncVariable(player: Player, board: AbstractLeaderBoard, page: Int) {
        val entryTotal = board.getAll()
        val entryPlayer = entryTotal.find { it.playerName == player.name } ?: return
        syncPlayer(player, board, entryPlayer)
        syncLeaderBoard(player, board, page)
    }

    private fun syncPlayer(player: Player, board: AbstractLeaderBoard, entry: LeaderBoardEntry) {
        PacketSender.sendSyncPlaceholder(player, mutableMapOf<String, String>().apply {
            put("my_rank", entry.index.toString())
            put("my_data", board.printEntryPretty(entry))
        })
    }

    private fun syncLeaderBoard(player: Player, board: AbstractLeaderBoard, page: Int) {
        val entryTotal = board.getAll()
        val partition = Lists.partition(entryTotal, 10)
        if (page > partition.size) {
            return
        }
        PacketSender.sendSyncPlaceholder(player, mutableMapOf<String, String>().apply {
            put("max_ranking_page", partition.size.toString())
        })
        val currentEntries = partition[page - 1]
        for (index in 1..10) {
            val entry = currentEntries.getOrNull(index - 1)
            if (entry == null) {
                PacketSender.sendSyncPlaceholder(player, mutableMapOf<String, String>().apply {
                    put("ranking_pos_$index", "")
                    put("ranking_name_$index", "")
                    put("ranking_data_$index", "")
                })
                continue
            }
            PacketSender.sendSyncPlaceholder(player, mutableMapOf<String, String>().apply {
                put("ranking_pos_$index", entry.index.toString())
                put("ranking_name_$index", entry.playerName)
                put("ranking_data_$index", board.printEntryPretty(entry))
            })
        }
    }
}