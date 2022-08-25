package net.sakuragame.eternal.kirraleaderboard.compat

import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import org.bukkit.entity.Player
import taboolib.platform.compat.PlaceholderExpansion

@Suppress("SpellCheckingInspection")
object PlaceholderAPI : PlaceholderExpansion {

    override val identifier = "KirraLeaderBoard"

    override fun onPlaceholderRequest(player: Player?, args: String): String {
        val leaderBoard = KirraLeaderBoardAPI.leaderBoards.getOrNull(KirraLeaderBoardAPI.modelIndex) ?: return "__"
        return when (args) {
            "category" -> leaderBoard.category.displayName
            "top_1" -> leaderBoard.getFirst()?.playerName ?: "无"
            "top_2" -> leaderBoard.getSecond()?.playerName ?: "无"
            "top_3" -> leaderBoard.getThird()?.playerName ?: "无"
            else -> "__"
        }
    }
}