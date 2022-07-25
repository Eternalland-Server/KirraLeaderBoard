package net.sakuragame.eternal.kirraleaderboard.leaderboard

@Suppress("unused")
enum class Category(val index: Int, val displayName: String) {

    LEVEL(0, "等级"),
    COMBAT(1, "战斗力"),
    COINS(2, "金币"),
    POINTS(3, "点券");
}