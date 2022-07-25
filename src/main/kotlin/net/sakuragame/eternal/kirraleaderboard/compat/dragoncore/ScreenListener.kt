package net.sakuragame.eternal.kirraleaderboard.compat.dragoncore

import com.google.common.collect.Lists
import com.taylorswiftcn.megumi.uifactory.event.comp.UIFCompSubmitEvent
import com.taylorswiftcn.megumi.uifactory.generate.function.SubmitParams
import net.sakuragame.eternal.dragoncore.network.PacketSender
import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.eternal.kirraleaderboard.compat.dragoncore.ScreenListener.ActionPage.NEXT
import net.sakuragame.eternal.kirraleaderboard.compat.dragoncore.ScreenListener.ActionPage.UP
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.event.SubscribeEvent

object ScreenListener {

    enum class ActionPage {
        UP, NEXT;
    }

    @SubscribeEvent
    fun e(e: UIFCompSubmitEvent) {
        val player = e.player
        val categoryIndex = e.compID.toDoubleOrNull()?.toInt() ?: return
        when (e.screenID) {
            "rank_category_change" -> handleCategoryChange(player, categoryIndex)
            "rank_page_up" -> handlePage(player, UP, e.params, categoryIndex)
            "rank_page_next" -> handlePage(player, NEXT, e.params, categoryIndex)
            else -> return
        }
    }

    private fun handleCategoryChange(player: Player, categoryIndex: Int) {
        Bukkit.broadcastMessage("category changed: ${player.name}, index: $categoryIndex")
        val board = KirraLeaderBoardAPI.leaderBoards.firstOrNull { it.category.index == categoryIndex } ?: return
        Bukkit.broadcastMessage("reached 1")
        PacketSender.sendRunFunction(player, "rank_main", "global.ranking_page = 1;", true)
        Bukkit.broadcastMessage("reached 2")
        ScreenSender.doSyncVariable(player, board, 1)
        Bukkit.broadcastMessage("reached 3")
    }

    private fun handlePage(player: Player, action: ActionPage, params: SubmitParams, categoryIndex: Int) {
        val page = params.getParamI(0)
        val board = KirraLeaderBoardAPI.leaderBoards.firstOrNull { it.category.index == categoryIndex } ?: return
        val entryTotal = board.getAll()
        val partition = Lists.partition(entryTotal, 10)
        val toOpenedPage = when (action) {
            UP -> {
                if (page <= 1) {
                    return
                }
                page - 1
            }

            NEXT -> {
                if (page >= partition.size) {
                    return
                }
                page + 1
            }
        }
        PacketSender.sendRunFunction(player, "rank_main", "global.ranking_page = $toOpenedPage;", true)
        ScreenSender.doSyncVariable(player, board, toOpenedPage)
    }
}