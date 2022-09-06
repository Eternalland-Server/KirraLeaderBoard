package net.sakuragame.eternal.kirraleaderboard.compat

import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import org.bukkit.entity.Player
import taboolib.platform.compat.PlaceholderExpansion

@Suppress("SpellCheckingInspection")
object PlaceholderAPI : PlaceholderExpansion {

    override val identifier = "KirraLeaderBoard"

    override fun onPlaceholderRequest(player: Player?, args: String): String {
        val leaderBoard = KirraLeaderBoardAPI.leaderBoards.getOrNull(KirraLeaderBoardAPI.modelIndex) ?: return "__"
        val entries = leaderBoard.getAll()
        return when (args) {
            "category" -> leaderBoard.category.displayName
            "top_1" -> entries.firstOrNull()?.playerName ?: "无"
            "top_2" -> entries.getOrNull(1)?.playerName ?: "无"
            "top_3" -> entries.getOrNull(2)?.playerName ?: "无"
            "top_4" -> entries.getOrNull(3)?.playerName ?: "无"
            "top_5" -> entries.getOrNull(4)?.playerName ?: "无"
            else -> "__"
        }
    }
}