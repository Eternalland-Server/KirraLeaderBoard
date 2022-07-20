package net.sakuragame.eternal.kirraleaderboard.compat

import org.bukkit.entity.Player
import taboolib.platform.compat.PlaceholderExpansion

@Suppress("SpellCheckingInspection")
object PlaceholderAPI : PlaceholderExpansion {

    override val identifier = "KirraLeaderBoard"

    override fun onPlaceholderRequest(player: Player?, args: String): String {
        return ""
    }
}