package net.sakuragame.eternal.kirraleaderboard.compat.dragoncore

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI
import net.sakuragame.eternal.kirraleaderboard.KirraLeaderBoardAPI
import org.bukkit.Sound
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

object ScreenCategory {

    const val id = "rank_category"

    lateinit var screenGUI: ScreenUI
    lateinit var screenYaml: YamlConfiguration

    @Awake(LifeCycle.ACTIVE)
    fun i() {
        screenGUI = ScreenUI(id).apply {
            addComponent(TextureComp("category", "0,0,0,0").apply {
                x = "body.x+9.75"
                y = "body.y+42"
            })
            addCategoryComponents()
        }
        screenYaml = screenGUI.build(null)
    }

    private fun ScreenUI.addCategoryComponents() {
        var increasedY = 0
        KirraLeaderBoardAPI.leaderBoards.forEach { board ->
            val index = board.category.index
            addComponent(TextureComp("category_$index", "(global.ranking_category == $index) ? 'ui/ranking/select.png' : 'ui/ranking/unselect.png'").apply {
                text = board.category.displayName
                x = "body.x + 9.75"
                y = "body.y + 42 + $increasedY"
                width = "103.5"
                height = "26.25"
                addAction(ActionType.Left_Click, Sound.UI_BUTTON_CLICK)
                addAction(ActionType.Left_Click, "global.ranking_category = $index;")
            })
            increasedY += 30
        }
    }
}