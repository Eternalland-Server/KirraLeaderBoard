package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.eternal.kirraleaderboard.compat.dragoncore.ScreenSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper

@CommandHeader(name = "leaderboard")
object Commands {

    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody
    val open = subCommand {
        execute<Player> { player, _, _ ->
            ScreenSender.openScreen(player, init = true)
        }
    }

    @CommandBody
    val updateModel = subCommand {
        execute<Player> { player, context, _ ->
            KirraLeaderBoardAPI.updateModel()
        }
    }

    @CommandBody
    val setPos = subCommand {
        dynamic(commit = "id") {
            execute<Player> { player, context, _ ->
                when (val pos = context.get(1)) {
                    "a", "b", "c" -> setPos(player, pos)
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