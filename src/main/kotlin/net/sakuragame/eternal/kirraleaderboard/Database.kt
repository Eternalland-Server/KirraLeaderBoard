package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.serversystems.manage.client.api.ClientManagerAPI
import taboolib.module.database.ColumnOptionSQL
import taboolib.module.database.ColumnTypeSQL
import taboolib.module.database.Table
import taboolib.module.database.getHost

@Suppress("SpellCheckingInspection")
object Database {

    private val dataSource by lazy {
        ClientManagerAPI.getDataManager().dataSource
    }

    private const val prefix = "KirraLeaderBoard"

    private val host = KirraLeaderBoard.conf.getHost("settings.database")

    private val tableNumber = Table("${prefix}_combat_power", host) {
        add("uid") {
            type(ColumnTypeSQL.INT) {
                options(ColumnOptionSQL.UNIQUE_KEY, ColumnOptionSQL.NOTNULL)
            }
        }
        add("combat_power") {
            type(ColumnTypeSQL.BIGINT)
        }
    }

    init {
        tableNumber.createTable(dataSource)
    }

    fun getAll(): MutableMap<Int, Int> {
        val toReturn = mutableMapOf<Int, Int>()
        tableNumber.select(dataSource) {}.map {
            toReturn += getInt("uid") to getInt("combat_power")
        }
        return toReturn
    }

    fun setCombatPower(uid: Int, number: Int) {
        if (uid == -1) return
        val isFind = tableNumber.find(dataSource) {
            where { where("uid" eq uid) }
        }
        if (isFind) {
            tableNumber.update(dataSource) {
                where { where("uid" eq uid) }
                set("combat_power", number)
            }
        } else {
            tableNumber.insert(dataSource, "uid", "combat_power") {
                value(uid, number)
            }
        }
    }
}