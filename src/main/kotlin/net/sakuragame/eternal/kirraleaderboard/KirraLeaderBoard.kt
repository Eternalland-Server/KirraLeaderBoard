package net.sakuragame.eternal.kirraleaderboard

import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import taboolib.platform.BukkitPlugin

@Suppress("SpellCheckingInspection")
object KirraLeaderBoard : Plugin() {

    @Config
    lateinit var conf: Configuration
        private set

    val plugin by lazy {
        BukkitPlugin.getInstance()
    }
}