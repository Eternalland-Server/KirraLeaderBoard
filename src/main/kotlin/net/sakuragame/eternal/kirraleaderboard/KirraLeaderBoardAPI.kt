package net.sakuragame.eternal.kirraleaderboard

import ink.ptms.adyeshach.api.AdyeshachAPI
import ink.ptms.adyeshach.common.entity.EntityTypes
import ink.ptms.adyeshach.common.entity.ai.expand.ControllerLookAtPlayerAlways
import ink.ptms.adyeshach.common.entity.type.AdyHuman
import net.sakuragame.eternal.dragoncore.api.ArmourAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.CoinsLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.CombatLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.LevelLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.PointsLeaderBoard
import net.sakuragame.serversystems.manage.client.api.ClientManagerAPI
import org.bukkit.Location
import org.bukkit.Material
import taboolib.common.platform.Awake
import taboolib.common.platform.Schedule
import taboolib.common.platform.function.submit
import taboolib.module.chat.colored
import taboolib.platform.util.buildItem
import java.util.*

@Suppress("SpellCheckingInspection")
object KirraLeaderBoardAPI {

    var modelIndex = 0

    private val modelLocations: MutableMap<Int, Location>
        get() = mutableMapOf<Int, Location>().apply {
            this[1] = KirraLeaderBoard.conf.getString("settings.locations.a")?.parseToLoc() ?: return@apply
            this[2] = KirraLeaderBoard.conf.getString("settings.locations.b")?.parseToLoc() ?: return@apply
            this[3] = KirraLeaderBoard.conf.getString("settings.locations.c")?.parseToLoc() ?: return@apply
            this[4] = KirraLeaderBoard.conf.getString("settings.locations.d")?.parseToLoc() ?: return@apply
            this[5] = KirraLeaderBoard.conf.getString("settings.locations.e")?.parseToLoc() ?: return@apply
        }

    val uuids = mutableListOf<UUID>()

    val leaderBoards = mutableListOf<AbstractLeaderBoard>()

    private val skinCache = mutableMapOf<String, MutableMap<String, String>>()

    @Awake
    fun i() {
        leaderBoards.apply {
            this += LevelLeaderBoard
            this += CombatLeaderBoard
            this += CoinsLeaderBoard
            this += PointsLeaderBoard
        }
        refresh()
    }

    fun refresh() {
        submit(async = true) {
            leaderBoards.forEach {
                it.refreshInput()
            }
        }
    }

    @Schedule(async = true, period = 20 * 60L)
    fun updateModel() {
        if (modelLocations.size < 3) {
            return
        }
        recycleModel()
        modelIndex++
        val leaderBoard = leaderBoards.getOrNull(modelIndex) ?: kotlin.run {
            modelIndex = 0
            leaderBoards.first()
        }
        val entries = leaderBoard.getAll()
        uuids += createNPC(entries.firstOrNull(), modelLocations[1]!!)
        uuids += createNPC(entries.getOrNull(1), modelLocations[2]!!)
        uuids += createNPC(entries.getOrNull(2), modelLocations[3]!!)
        uuids += createNPC(entries.getOrNull(3), modelLocations[4]!!)
        uuids += createNPC(entries.getOrNull(4), modelLocations[5]!!)
    }

    private fun createNPC(entry: LeaderBoardEntry?, loc: Location): UUID {
        val entityInstance = AdyeshachAPI.getEntityManagerPublicTemporary().create(EntityTypes.PLAYER, loc) as AdyHuman
        entityInstance.apply {
            registerController(ControllerLookAtPlayerAlways(this))
            if (entry == null) {
                setName("无")
                return@apply
            }
            setName("&f${entry.playerName}".colored())
            if (skinCache[entry.playerName] == null) {
                skinCache[entry.playerName] = ArmourAPI.getSkinsFormDB(ClientManagerAPI.getUserID(entry.playerName))
            }
            val skins = skinCache[entry.playerName]!!
            submit(async = true, delay = 20) {
                when {
                    skins.keys.contains("mainhand_skin") -> setItemInMainHand(buildItem(Material.DIAMOND_SWORD))
                    skins.keys.contains("offhand_skin") -> setItemInOffHand(buildItem(Material.SHIELD))
                }
                ArmourAPI.setEntitySkin(normalizeUniqueId, skins.values.toList())
            }
        }
        return entityInstance.normalizeUniqueId
    }

    private fun recycleModel() {
        AdyeshachAPI.getEntityManagerPublicTemporary().getEntities()
            .filter { uuids.contains(it.normalizeUniqueId) }
            .forEach {
                it.delete()
            }
        uuids.clear()
    }
}