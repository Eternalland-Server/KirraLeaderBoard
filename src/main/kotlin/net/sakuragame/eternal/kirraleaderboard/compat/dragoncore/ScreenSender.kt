package net.sakuragame.eternal.kirraleaderboard.compat.dragoncore

import net.sakuragame.eternal.dragoncore.api.event.YamlSendFinishedEvent
import net.sakuragame.eternal.dragoncore.config.FolderType
import net.sakuragame.eternal.dragoncore.network.PacketSender
import taboolib.common.platform.event.SubscribeEvent

object ScreenSender {

    @SubscribeEvent
    fun e(e: YamlSendFinishedEvent) {
        val player = e.player
        PacketSender.sendYaml(player, FolderType.Gui, ScreenCategory.id, ScreenCategory.screenYaml)
    }
}