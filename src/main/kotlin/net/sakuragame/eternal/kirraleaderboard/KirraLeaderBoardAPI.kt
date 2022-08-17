package net.sakuragame.eternal.kirraleaderboard

import ink.ptms.adyeshach.api.AdyeshachAPI
import ink.ptms.adyeshach.common.entity.EntityTypes
import ink.ptms.adyeshach.common.entity.type.AdyHuman
import net.sakuragame.eternal.dragoncore.api.ArmourAPI
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.CoinsLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.CombatLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.LevelLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.impl.PointsLeaderBoard
import net.sakuragame.serversystems.manage.client.api.ClientManagerAPI
import org.bukkit.Bukkit
import org.bukkit.Location
import taboolib.common.platform.Awake
import taboolib.common.platform.Schedule
import taboolib.common.platform.function.submit
import java.util.*

@Suppress("SpellCheckingInspection")
object KirraLeaderBoardAPI {

    private var modelIndex = 0

    private val modelLocations: MutableMap<Int, Location>
        get() = mutableMapOf<Int, Location>().apply {
            this[1] = KirraLeaderBoard.conf.getString("settings.locations.a")?.parseToLoc() ?: return@apply
            this[2] = KirraLeaderBoard.conf.getString("settings.locations.b")?.parseToLoc() ?: return@apply
            this[3] = KirraLeaderBoard.conf.getString("settings.locations.c")?.parseToLoc() ?: return@apply
        }

    private val uuids = mutableListOf<UUID>()

    val leaderBoards = mutableListOf<AbstractLeaderBoard>()

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
        val leaderBoard = leaderBoards.getOrNull(modelIndex) ?: kotlin.run {
            modelIndex = 0
            leaderBoards.first()
        }
        uuids += createNPC(leaderBoard.getFirst(), modelLocations[1]!!)
        uuids += createNPC(leaderBoard.getSecond(), modelLocations[2]!!)
        uuids += createNPC(leaderBoard.getThird(), modelLocations[3]!!)
        modelIndex++
    }

    private fun createNPC(entry: LeaderBoardEntry?, loc: Location): UUID {
        val entityInstance = AdyeshachAPI.getEntityManagerPublicTemporary().create(EntityTypes.PLAYER, loc) as AdyHuman
        entityInstance.apply {
            if (entry == null) {
                setName("无")
                return@apply
            }
            setName(entry.playerName)
            val skins = ArmourAPI.getSkinsFormDB(ClientManagerAPI.getUserID(entry.playerName))
            submit(async = true, delay = 20) {
                ArmourAPI.setEntitySkin(normalizeUniqueId, skins)
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