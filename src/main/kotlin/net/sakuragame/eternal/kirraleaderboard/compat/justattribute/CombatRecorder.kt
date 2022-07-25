package net.sakuragame.eternal.kirraleaderboard.compat.justattribute

import com.sakuragame.eternal.justattribute.JustAttribute
import com.sakuragame.eternal.justattribute.api.event.role.RoleAccountLoadedEvent
import net.sakuragame.eternal.kirraleaderboard.Database
import net.sakuragame.serversystems.manage.client.api.ClientManagerAPI
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Schedule
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submit

object CombatRecorder {

    val combatMap = mutableMapOf<Int, Int>()

    @Awake(LifeCycle.ENABLE)
    fun i() {
        combatMap += Database.getAll()
    }

    @Schedule(async = true, period = 500L)
    fun s() {
        combatMap.forEach { (uid, combatPower) ->
            Database.setCombatPower(uid, combatPower)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun e(e: RoleAccountLoadedEvent) {
        val player = e.player
        val uid = ClientManagerAPI.getUserID(player.uniqueId)
        val character = JustAttribute.getRoleManager().get(player.uniqueId) ?: return
        val combatPower = character.combatPower.value
        combatMap[uid] = combatPower
    }

    @SubscribeEvent
    fun e(e: PlayerQuitEvent) {
        val player = e.player
        val uid = ClientManagerAPI.getUserID(player.uniqueId)
        submit(async = true) {
            Database.setCombatPower(uid, combatMap[uid] ?: return@submit)
        }
    }
}