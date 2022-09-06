package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.eternal.kirraleaderboard.compat.dragoncore.ScreenSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper
import taboolib.module.chat.colored

@CommandHeader(name = "leaderboard")
object Commands {

    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody
    val open = subCommand {
        execute<Player> { sender, _, _ ->
            ScreenSender.openScreen(sender, init = true)
        }
    }

    @CommandBody
    val updateModel = subCommand {
        execute<Player> { sender, _, _ ->
            KirraLeaderBoardAPI.updateModel()
            sender.sendMessage("&c[System] &7Ok!".colored())
        }
    }

    @CommandBody
    val refreshCache = subCommand {
        execute<Player> { sender, _, _ ->
            KirraLeaderBoardAPI.refresh()
            sender.sendMessage("&c[System] &7Ok!".colored())
        }
    }

    @CommandBody
    val setPos = subCommand {
        dynamic(commit = "id") {
            execute<Player> { player, context, _ ->
                when (val pos = context.get(1)) {
                    "a", "b", "c", "d", "e" -> setPos(player, pos)
                    else -> return@execute
                }
            }
        }
    }

    private fun setPos(player: Player, pos: String) {
        KirraLeaderBoard.conf["settings.locations.$pos"] = player.location.parseToString()
        KirraLeaderBoard.conf.saveToFile(KirraLeaderBoard.conf.file)
        KirraLeaderBoard.conf.reload()
    }
}