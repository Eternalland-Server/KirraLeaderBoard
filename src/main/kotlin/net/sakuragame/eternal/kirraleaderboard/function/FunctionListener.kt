package net.sakuragame.eternal.kirraleaderboard.function

import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.CombatPowerLeaderBoard
import net.sakuragame.serversystems.manage.client.api.event.NewHourEvent
import org.bukkit.entity.Player
import taboolib.common.platform.command.command
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.broadcast

object FunctionListener {

    init {
        command("leaderboard") {
            execute<Player> { player, _, _ ->
                 KirraLeaderBoardAPI.leaderBoards.toString().broadcast()
            }
        }
    }

    @SubscribeEvent
    fun e(e: NewHourEvent) {
        KirraLeaderBoardAPI.refreshAll()
    }
}