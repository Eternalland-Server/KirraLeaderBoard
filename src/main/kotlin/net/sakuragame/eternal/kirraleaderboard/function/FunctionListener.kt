package net.sakuragame.eternal.kirraleaderboard.function

import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import net.sakuragame.serversystems.manage.client.api.event.NewHourEvent
import taboolib.common.platform.event.SubscribeEvent

object FunctionListener {

    @SubscribeEvent
    fun e(e: NewHourEvent) {
        KirraLeaderBoardAPI.refreshAll()
    }
}