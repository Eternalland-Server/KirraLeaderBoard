package net.sakuragame.eternal.kirraleaderboard

import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry
import org.bukkit.Bukkit
import org.bukkit.Location

fun Pair<Int, Double>.toLeaderBoardEntry(index: Int): LeaderBoardEntry {
    return LeaderBoardEntry(index, first, second)
}

fun Location.parseToString(): String {
    return "${world.name}@$x@$y@$z@$yaw@$pitch"
}

fun String.parseToLoc(): Location? {
    val split = split("@")
    if (split.size != 6) {
        return null
    }
    val world = Bukkit.getWorld(split[0]) ?: return null
    val x = split[1].toDoubleOrNull() ?: return null
    val y = split[2].toDoubleOrNull() ?: return null
    val z = split[3].toDoubleOrNull() ?: return null
    val yaw = split[4].toFloatOrNull() ?: return null
    val pitch = split[5].toFloatOrNull() ?: return null
    return Location(world, x, y, z, yaw, pitch)
}

fun <T> List<T>.safeSubList(fromIndex: Int, toIndex: Int): List<T> {
    return subList(fromIndex.coerceAtLeast(0), toIndex.coerceAtMost(this.size))
}