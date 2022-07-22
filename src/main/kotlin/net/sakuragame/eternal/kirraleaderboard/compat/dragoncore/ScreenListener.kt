package net.sakuragame.eternal.kirraleaderboard.compat.dragoncore

import com.google.common.collect.Lists
import com.taylorswiftcn.megumi.uifactory.event.comp.UIFCompSubmitEvent
import com.taylorswiftcn.megumi.uifactory.generate.function.SubmitParams
import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.eternal.kirraleaderboard.compat.dragoncore.ScreenListener.ActionPage.*
import org.bukkit.entity.Player
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.broadcast

object ScreenListener {

    enum class ActionPage {
        UP, NEXT;
    }

    @SubscribeEvent
    fun e(e: UIFCompSubmitEvent) {
        val player = e.player

        when (e.screenID) {
            "rank_category_change" -> handleCategoryChange()
            "rank_page_up" -> handlePage(player, UP, e.params, e.compID.toDoubleOrNull()?.toInt() ?: return)
            "rank_page_next" -> {
                e.compID.broadcast()
                handlePage(player, NEXT, e.params, e.compID.toDoubleOrNull()?.toInt() ?: return)
            }
            else -> return
        }
    }

    private fun handleCategoryChange() {
        "category changed".broadcast()
    }

    private fun handlePage(player: Player, action: ActionPage, params: SubmitParams, categoryIndex: Int) {
        val page = params.getParamI(0)
        val board = KirraLeaderBoardAPI.leaderBoards.firstOrNull { it.category.index == categoryIndex } ?: return
        val entryTotal = board.getAll()
        val partition = Lists.partition(entryTotal, 10)
        val toOpenedPage = when (action) {
            UP -> {
                if (page > partition.size) {
                    return
                }
                page - 1
            }
            NEXT -> {
                if (page < 1) {
                    return
                }
                page + 1
            }
        }
        ScreenSender.doSyncVariable(player, board, toOpenedPage)
        ScreenSender.openScreen(player)
    }
}