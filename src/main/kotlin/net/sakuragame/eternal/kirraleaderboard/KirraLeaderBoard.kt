package net.sakuragame.eternal.kirraleaderboard

import taboolib.common.platform.Plugin
import taboolib.platform.BukkitPlugin

@Suppress("SpellCheckingInspection")
object KirraLeaderBoard : Plugin() {

    val plugin by lazy {
        BukkitPlugin.getInstance()
    }
}