package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.eternal.kirraleaderboard.compat.dragoncore.ScreenSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper

@CommandHeader(name = "leaderboard")
object Commands {

    @CommandBody
    val main = subCommand {
        createHelper()
    }

    @CommandBody
    val open = subCommand {
        execute<Player> { player, _, _ ->
            ScreenSender.openScreen(player, init = true)
        }
    }
}