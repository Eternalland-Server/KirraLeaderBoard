package net.sakuragame.eternal.kirraleaderboard.compat.dragoncore

import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI
import org.bukkit.Sound
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.chat.colored

object ScreenCategory {

    const val id = "rank_category"

    lateinit var screenGUI: ScreenUI
    lateinit var screenYaml: YamlConfiguration

    @Awake(LifeCycle.ACTIVE)
    fun i() {
        screenGUI = ScreenUI(id)
            .addComponent(TextureComp("category", "0,0,0,0").apply {
                x = "body.x+9.75"
                y = "body.y+42"
            })
            .addComponent(TextureComp("c0", "(global.ranking_category == 0) ? 'ui/ranking/select.png' : 'ui/ranking/unselect.png'").apply {
                text = "&f战斗力榜".colored()
                x = "category.x"
                y = "category.y"
                width = "103.5"
                height = "26.25"
                addAction(ActionType.Left_Click, Sound.UI_BUTTON_CLICK)
                addAction(ActionType.Left_Click, "global.ranking_category = 0;")
            })
            .addComponent(TextureComp("c1", "(global.ranking_category == 1) ? 'ui/ranking/select.png' : 'ui/ranking/unselect.png'").apply {
                text = "&f金币富豪榜".colored()
                x = "c0.x"
                y = "c0.y+30"
                width = "103.5"
                height = "26.25"
                addAction(ActionType.Left_Click, Sound.UI_BUTTON_CLICK)
                addAction(ActionType.Left_Click, "global.ranking_category = 1;")
            })
            .addComponent(TextureComp("c2", "(global.ranking_category == 2) ? 'ui/ranking/select.png' : 'ui/ranking/unselect.png'").apply {
                text = "&f点券富豪榜".colored()
                x = "c1.x"
                y = "c1.y+30"
                width = "103.5"
                height = "26.25"
                addAction(ActionType.Left_Click, Sound.UI_BUTTON_CLICK)
                addAction(ActionType.Left_Click, "global.ranking_category = 2;")
            })
        screenYaml = screenGUI.build(null)
    }
}