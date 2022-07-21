package net.sakuragame.eternal.kirraleaderboard.compat.dragoncore

import com.taylorswiftcn.megumi.uifactory.generate.type.FunctionType
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.EntityViewComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.LabelComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI
import taboolib.module.chat.colored

object ScreenMain {

    private const val id = "rank_main"

    fun send2Player() {
        ScreenUI(id).apply {
            addImmutableComponents()


        }
            .addComponent(TextureComp("t1", "0,0,0,0").apply {
                x = "body.x+134.25"
                y = "body.y+45"
                width = "59.25"
                height = "21"
            })
            .addComponent(TextureComp("t2", "0,0,0,0").apply {
                x = "t1.x+71"
                y = "t1.y"
                width = "66.75"
                height = "21"
            })
            .addComponent(TextureComp("t3", "0,0,0,0").apply {
                x = "t2.x+78"
                y = "t2.y"
                width = "59.25"
                height = "21"
            })
            .addComponent(TextureComp("page", "0,0,0,0").apply {
                x = "body.x+(body.width-page.width)/2"
                y = "body.y+322"
                width = "30"
                height = "14"
            })
            .addComponent(TextureComp("up", "ui/quest/l.png").apply {
                x = "page.x-10"
                y = "page.y+2"
                width = "7"
                height = "10"
            })
            .addComponent(TextureComp("next", "ui/quest/r.png").apply {
                x = "page.x+33"
                y = "up.y"
                width = "7"
                height = "10"
            })
            .addComponent(TextureComp("area", "0,0,0,0").apply {
                x = "body.x+128"
                y = "body.y+82.5"
                width = ""
                height = ""
            })
            .addComponent(TextureComp("l1", "ui/ranking/line.png").apply {
                x = "area.x"
                y = "area.y"
                width = "219"
                height = "20.25"
            })
            .addComponent(TextureComp("l2", "ui/ranking/line.png").apply {
                x = "l1.x"
                y = "l1.y+24"
                width = "219"
                height = "20.25"
            })
            .addComponent(TextureComp("l3", "ui/ranking/line.png").apply {
                x = "l2.x"
                y = "l2.y+24"
                width = "219"
                height = "20.25"
            })
            .addComponent(TextureComp("l4", "ui/ranking/line.png").apply {
                x = "l3.x"
                y = "l3.y+24"
                width = "219"
                height = "20.25"
            })
            .addComponent(TextureComp("l5", "ui/ranking/line.png").apply {
                x = "l4.x"
                y = "l4.y+24"
                width = "219"
                height = "20.25"
            })
            .addComponent(TextureComp("l6", "ui/ranking/line.png").apply {
                x = "l5.x"
                y = "l5.y+24"
                width = "219"
                height = "20.25"
            })
            .addComponent(TextureComp("l7", "ui/ranking/line.png").apply {
                x = "l6.x"
                y = "l6.y+24"
                width = "219"
                height = "20.25"
            })
            .addComponent(TextureComp("l8", "ui/ranking/line.png").apply {
                x = "l7.x"
                y = "l7.y+24"
                width = "219"
                height = "20.25"
            })
            .addComponent(TextureComp("l9", "ui/ranking/line.png").apply {
                x = "l8.x"
                y = "l8.y+24"
                width = "219"
                height = "20.25"
            })
            .addComponent(TextureComp("l10", "ui/ranking/line.png").apply {
                x = "l9.x"
                y = "l9.y+24"
                width = "219"
                height = "20.25"
            })
            .addComponent(TextureComp("l_1_1", "0,0,0,0").apply {
                x = "l1.x"
                y = "l1.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_1_2", "0,0,0,0").apply {
                x = "l1.x+71.25"
                y = "l1.y"
                width = "77.25"
                height = "20.25"
            })
            .addComponent(TextureComp("l_1_3", "0,0,0,0").apply {
                x = "l_1_2.x+78"
                y = "l1.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_2_1", "0,0,0,0").apply {
                x = "l2.x"
                y = "l2.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_2_2", "0,0,0,0").apply {
                x = "l2.x+71.25"
                y = "l2.y"
                width = "77.25"
                height = "20.25"
            })
            .addComponent(TextureComp("l_2_3", "0,0,0,0").apply {
                x = "l_2_2.x+78"
                y = "l2.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_3_1", "0,0,0,0").apply {
                x = "l3.x"
                y = "l3.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_3_2", "0,0,0,0").apply {
                x = "l3.x+71.25"
                y = "l3.y"
                width = "77.25"
                height = "20.25"
            })
            .addComponent(TextureComp("l_3_3", "0,0,0,0").apply {
                x = "l_3_2.x+78"
                y = "l3.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_4_1", "0,0,0,0").apply {
                x = "l4.x"
                y = "l4.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_4_2", "0,0,0,0").apply {
                x = "l4.x+71.25"
                y = "l4.y"
                width = "77.25"
                height = "20.25"
            })
            .addComponent(TextureComp("l_4_3", "0,0,0,0").apply {
                x = "l_4_2.x+78"
                y = "l4.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_5_1", "0,0,0,0").apply {
                x = "l5.x"
                y = "l5.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_5_2", "0,0,0,0").apply {
                x = "l5.x+71.25"
                y = "l5.y"
                width = "77.25"
                height = "20.25"
            })
            .addComponent(TextureComp("l_5_3", "0,0,0,0").apply {
                x = "l_5_2.x+78"
                y = "l5.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_6_1", "0,0,0,0").apply {
                x = "l6.x"
                y = "l6.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_6_2", "0,0,0,0").apply {
                x = "l6.x+71.25"
                y = "l6.y"
                width = "77.25"
                height = "20.25"
            })
            .addComponent(TextureComp("l_6_3", "0,0,0,0").apply {
                x = "l_6_2.x+78"
                y = "l6.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_7_1", "0,0,0,0").apply {
                x = "l7.x"
                y = "l7.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_7_2", "0,0,0,0").apply {
                x = "l7.x+71.25"
                y = "l7.y"
                width = "77.25"
                height = "20.25"
            })
            .addComponent(TextureComp("l_7_3", "0,0,0,0").apply {
                x = "l_7_2.x+78"
                y = "l7.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_8_1", "0,0,0,0").apply {
                x = "l8.x"
                y = "l8.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_8_2", "0,0,0,0").apply {
                x = "l8.x+71.25"
                y = "l8.y"
                width = "77.25"
                height = "20.25"
            })
            .addComponent(TextureComp("l_8_3", "0,0,0,0").apply {
                x = "l_8_2.x+78"
                y = "l8.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_9_1", "0,0,0,0").apply {
                x = "l9.x"
                y = "l9.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_9_2", "0,0,0,0").apply {
                x = "l9.x+71.25"
                y = "l9.y"
                width = "77.25"
                height = "20.25"
            })
            .addComponent(TextureComp("l_9_3", "0,0,0,0").apply {
                x = "l_9_2.x+78"
                y = "l9.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_10_1", "0,0,0,0").apply {
                x = "l10.x"
                y = "l10.y"
                width = "70.5"
                height = "20.25"
            })
            .addComponent(TextureComp("l_10_2", "0,0,0,0").apply {
                x = "l10.x+71.25"
                y = "l10.y"
                width = "77.25"
                height = "20.25"
            })
            .addComponent(TextureComp("l_10_3", "0,0,0,0").apply {
                x = "l_10_2.x+78"
                y = "l10.y"
                width = "70.5"
                height = "20.25"
            })
    }

    private fun ScreenUI.addImmutableComponents() {
        addImports(ScreenCategory.id)
        addFunctions(FunctionType.Open, "global.ranking_category = 0;")
        addComponent(TextureComp("body", "ui/ranking/body.png").apply {
            x = "(w-body.width)/2"
            y = "(h-body.height)/2"
            width = "485.25"
            height = "344.25"
        })
        addComponent(LabelComp("title", "排行榜".colored()).apply {
            x = "body.x+(body.width-title.width)/2"
            y = "body.y+15"
            scale = "1.6"
        })
        addComponent(TextureComp("s1", "ui/ranking/frame.png").apply {
            x = "body.x+390.75"
            y = "body.y+200"
            width = "56.25"
            height = "16"
            text = "&5&l我的排名".colored()
        })
        addComponent(TextureComp("s2", "ui/ranking/frame.png").apply {
            x = "s1.x"
            y = "s1.y+60"
            width = "56.25"
            height = "16"
            text = "&5&l我的数据".colored()
        })
        addComponent(EntityViewComp("myself", "owner").apply {
            x = "body.x+420"
            y = "body.y+176"
            scale = 2.0.toString()
        })
        addComponent(TextureComp("myrank", "0,0,0,0").apply {
            x = "s1.x"
            y = "s1.y+20"
            width = "24"
            height = "10"
            text = "&e&l9999".colored()
            scale = 2.4.toString()
        })
        addComponent(TextureComp("mydata", "0,0,0,0").apply {
            x = "s2.x"
            y = "s2.y+20"
            width = "24"
            height = "10"
            scale = 2.4.toString()
        })
    }
}