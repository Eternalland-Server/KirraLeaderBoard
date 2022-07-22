package net.sakuragame.eternal.kirraleaderboard.compat.dragoncore

import com.google.common.collect.Lists
import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType
import com.taylorswiftcn.megumi.uifactory.generate.type.FunctionType
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.EntityViewComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.LabelComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI
import net.sakuragame.eternal.kirraleaderboard.UnitConvert
import net.sakuragame.eternal.kirraleaderboard.leaderboard.AbstractLeaderBoard
import net.sakuragame.eternal.kirraleaderboard.leaderboard.LeaderBoardEntry
import org.bukkit.Sound
import org.bukkit.entity.Player
import taboolib.module.chat.colored

object ScreenMain {

    private const val id = "rank_main"

    fun send2Player(player: Player, board: AbstractLeaderBoard<out Comparable<*>>, page: Int) {
        val partition = Lists.partition(board.getAll(), 10) ?: return
        ScreenUI(id).apply {
            addBackgroundComponents()
            addBaseInfoComponents(player, board)
            addPageComponents(partition, page)
            addAreaComponents()
            addLinesComponents()
            addRankingComponents()
        }
    }

    private fun ScreenUI.addBackgroundComponents() {
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
    }

    private fun ScreenUI.addBaseInfoComponents(player: Player, board: AbstractLeaderBoard<out Comparable<*>>) {
        val boardEntry = board.getByPlayerName(player.name) ?: return
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
        addComponent(TextureComp("my_rank", "0,0,0,0").apply {
            x = "s1.x"
            y = "s1.y+20"
            width = "24"
            height = "10"
            text = boardEntry.index.toString()
            scale = 2.4.toString()
        })
        addComponent(TextureComp("my_data", "0,0,0,0").apply {
            x = "s2.x"
            y = "s2.y+20"
            width = "24"
            height = "10"
            text = boardEntry.convert(UnitConvert.MILLION)
            scale = 2.4.toString()
        })
        addComponent(TextureComp("t1", "0,0,0,0").apply {
            x = "body.x+134.25"
            y = "body.y+45"
            width = "59.25"
            height = "21"
            text = "&f&l排名".colored()
        })
        addComponent(TextureComp("t2", "0,0,0,0").apply {
            x = "t1.x+71"
            y = "t1.y"
            width = "66.75"
            height = "21"
            text = "&f&l玩家".colored()
        })
        addComponent(TextureComp("t3", "0,0,0,0").apply {
            x = "t2.x+78"
            y = "t2.y"
            width = "59.25"
            height = "21"
            text = "&f&l数据".colored()
        })
    }

    private fun ScreenUI.addPageComponents(partition: MutableList<MutableList<LeaderBoardEntry<out Comparable<*>>>>, page: Int) {
        addComponent(TextureComp("page", "0,0,0,0").apply {
            x = "body.x+(body.width-page.width)/2"
            y = "body.y+322"
            width = "30"
            height = "14"
            text = "$page/${partition.size}"
        })
        addComponent(TextureComp("up", "ui/quest/l.png").apply {
            x = "page.x-10"
            y = "page.y+2"
            width = "7"
            height = "10"
            addAction(ActionType.Left_Click, Sound.UI_BUTTON_CLICK)
            addAction(ActionType.Left_Click, "up.texture = 'ui/quest/l_p.png';")
            addAction(ActionType.Left_Release, "up.texture = 'ui/quest/l.png';")
        })
        addComponent(TextureComp("next", "ui/quest/r.png").apply {
            x = "page.x+33"
            y = "up.y"
            width = "7"
            height = "10"
            addAction(ActionType.Left_Click, Sound.UI_BUTTON_CLICK)
            addAction(ActionType.Left_Click, "up.texture = 'ui/quest/l_p.png';")
            addAction(ActionType.Left_Release, "up.texture = 'ui/quest/l.png';")
        })
    }

    private fun ScreenUI.addAreaComponents() {
        addComponent(TextureComp("area", "0,0,0,0").apply {
            x = "body.x+128"
            y = "body.y+82.5"
        })
    }

    private fun ScreenUI.addLinesComponents() {
        var yIncreased = 0
        for (index in 1..10) {
            addComponent(TextureComp("l$index", "'ui/ranking/line.png'").apply {
                x = "aura.x"
                y = "aura.y + $yIncreased"
                width = "219"
                height = "20.25"
            })
            yIncreased += 24
        }
    }

    private fun ScreenUI.addRankingComponents() {
        for (index in 1..10) {
            addComponent(TextureComp("l${index}_1", "0,0,0,0").apply {
                x = "l$index.x"
                y = "l$index.y"
                width = "70.5"
                height = "20.25"
                text = "pos_1"
            })
            addComponent(TextureComp("l${index}_2", "0,0,0,0").apply {
                x = "l$index.x + 71.25"
                y = "l$index.y"
                width = "70.5"
                height = "20.25"
                text = "name_1"
            })
            addComponent(TextureComp("l${index}_2", "0,0,0,0").apply {
                x = "l${index}_2.x + 78"
                y = "l$index.y"
                width = "70.5"
                height = "20.25"
                text = "data_1"
            })
        }
    }
}