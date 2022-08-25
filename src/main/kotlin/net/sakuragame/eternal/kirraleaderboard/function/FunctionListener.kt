package net.sakuragame.eternal.kirraleaderboard.function

import ink.ptms.adyeshach.api.event.AdyeshachEntityDamageEvent
import ink.ptms.adyeshach.api.event.AdyeshachEntityInteractEvent
import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.eternal.kirraleaderboard.compat.dragoncore.ScreenSender
import net.sakuragame.serversystems.manage.client.api.event.NewHourEvent
import org.bukkit.entity.Player
import taboolib.common.platform.event.SubscribeEvent
import java.util.*

object FunctionListener {

    @SubscribeEvent
    fun e(e: NewHourEvent) {
        KirraLeaderBoardAPI.refresh()
    }

    @SubscribeEvent
    fun e(e: AdyeshachEntityInteractEvent) {
        handleInteract(e.player, e.entity.normalizeUniqueId)
    }

    @SubscribeEvent
    fun e(e: AdyeshachEntityDamageEvent) {
        handleInteract(e.player, e.entity.normalizeUniqueId)
    }

    private fun handleInteract(player: Player, normalizeUniqueId: UUID) {
        if (KirraLeaderBoardAPI.uuids.contains(normalizeUniqueId)) {
            ScreenSender.openScreen(player, init = true)
        }
    }
}