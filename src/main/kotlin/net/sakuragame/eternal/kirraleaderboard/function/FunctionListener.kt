package net.sakuragame.eternal.kirraleaderboard.function

import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.eternal.kirraleaderboard.compat.dragoncore.ScreenMain
import net.sakuragame.eternal.kirraleaderboard.leaderboard.Category
import net.sakuragame.serversystems.manage.client.api.event.NewHourEvent
import org.bukkit.entity.Player
import taboolib.common.platform.command.command
import taboolib.common.platform.event.SubscribeEvent

object FunctionListener {

    init {
        command("leaderboard") {
            execute<Player> { player, _, _ ->
                ScreenMain.send2Player(player, Category.COMBAT, 1)
            }
        }
    }

    @SubscribeEvent
    fun e(e: NewHourEvent) {
        KirraLeaderBoardAPI.refreshAll()
    }
}