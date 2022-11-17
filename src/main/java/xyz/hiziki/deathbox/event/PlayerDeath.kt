package xyz.hiziki.deathbox.event

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.block.Chest
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import xyz.hiziki.deathbox.Main
import xyz.hiziki.deathbox.util.Prefix
import xyz.hiziki.deathbox.util.SaveFile

class PlayerDeath : Listener
{
    private val boxes : YamlConfiguration? = Main.Companion.getBoxes()
    @EventHandler
    fun onPlayerDeath(e : PlayerDeathEvent)
    {
        val p = e.entity
        setBox(p)
        if (p.location.clone().add(0.0, 0.0, 0.0).block.type == Material.AIR) //死んだときのy座標にブロックがなかったら
        {
            if (p.location.clone().add(0.0, 0.0, 1.0).block.type == Material.AIR)
            {
                //死亡位置からz-1が空気だった場合
                val rightSide = p.location.block
                val leftSide = p.location.clone().add(0.0, 0.0, 1.0).block //南に向かってチェストを設置
                rightSide.type = Material.CHEST
                leftSide.type = Material.CHEST
                rightSide.blockData = Material.CHEST.createBlockData("[facing=west,type=right]")
                leftSide.blockData = Material.CHEST.createBlockData("[facing=west,type=left]")
                createChest(p)
                e.drops.clear()
            } else if (p.location.clone().add(0.0, 0.0, -1.0).block.type == Material.AIR)
            {
                //死亡位置からz+1が空気だった場合
                val rightSide = p.location.block
                val leftSide = p.location.clone().add(0.0, 0.0, -1.0).block //北に向かってチェストを設置
                rightSide.type = Material.CHEST
                leftSide.type = Material.CHEST
                rightSide.blockData = Material.CHEST.createBlockData("[facing=east,type=right]")
                leftSide.blockData = Material.CHEST.createBlockData("[facing=east,type=left]")
                createChest(p)
                e.drops.clear()
            } else if (p.location.clone().add(1.0, 0.0, 0.0).block.type == Material.AIR)
            {
                //死亡位置からx+1が空気だった場合
                val rightSide = p.location.block
                val leftSide = p.location.clone().add(1.0, 0.0, 0.0).block //西に向かってチェストを設置
                rightSide.type = Material.CHEST
                leftSide.type = Material.CHEST
                rightSide.blockData = Material.CHEST.createBlockData("[facing=north,type=left]")
                leftSide.blockData = Material.CHEST.createBlockData("[facing=north,type=right]")
                createChest(p)
                e.drops.clear()
            } else if (p.location.clone().add(-1.0, 0.0, 0.0).block.type == Material.AIR)
            {
                //死亡位置からx-1が空気だった場合
                val rightSide = p.location.block
                val leftSide = p.location.clone().add(-1.0, 0.0, 0.0).block //東に向かってチェストを設置
                rightSide.type = Material.CHEST
                leftSide.type = Material.CHEST
                rightSide.blockData = Material.CHEST.createBlockData("[facing=north,type=right]")
                leftSide.blockData = Material.CHEST.createBlockData("[facing=north,type=left]")
                createChest(p)
                e.drops.clear()
            }
        } else if (p.location.clone().add(0.0, 1.0, 0.0).block.type == Material.AIR) //死んだときのy座標の一つ上にブロックがなかったら
        {
            if (p.location.clone().add(0.0, 1.0, 1.0).block.type == Material.AIR)
            {
                //死亡位置からz-1が空気だった場合
                val rightSide = p.location.clone().add(0.0, 1.0, 0.0).block
                val leftSide = p.location.clone().add(0.0, 1.0, 1.0).block //南に向かってチェストを設置
                rightSide.type = Material.CHEST
                leftSide.type = Material.CHEST
                rightSide.blockData = Material.CHEST.createBlockData("[facing=west,type=right]")
                leftSide.blockData = Material.CHEST.createBlockData("[facing=west,type=left]")
                createChest(p)
                e.drops.clear()
            } else if (p.location.clone().add(0.0, 1.0, -1.0).block.type == Material.AIR)
            {
                //死亡位置からz+1が空気だった場合
                val rightSide = p.location.clone().add(0.0, 1.0, 0.0).block
                val leftSide = p.location.clone().add(0.0, 1.0, -1.0).block //北に向かってチェストを設置
                rightSide.type = Material.CHEST
                leftSide.type = Material.CHEST
                rightSide.blockData = Material.CHEST.createBlockData("[facing=east,type=right]")
                leftSide.blockData = Material.CHEST.createBlockData("[facing=east,type=left]")
                createChest(p)
                e.drops.clear()
            } else if (p.location.clone().add(1.0, 0.0, 0.0).block.type == Material.AIR)
            {
                //死亡位置からx+1が空気だった場合
                val rightSide = p.location.clone().add(0.0, 1.0, 0.0).block
                val leftSide = p.location.clone().add(1.0, 1.0, 0.0).block //西に向かってチェストを設置
                rightSide.type = Material.CHEST
                leftSide.type = Material.CHEST
                rightSide.blockData = Material.CHEST.createBlockData("[facing=north,type=left]")
                leftSide.blockData = Material.CHEST.createBlockData("[facing=north,type=right]")
                createChest(p)
                e.drops.clear()
            } else if (p.location.clone().add(-1.0, 0.0, 0.0).block.type == Material.AIR)
            {
                //死亡位置からx-1が空気だった場合
                val rightSide = p.location.clone().add(0.0, 1.0, 0.0).block
                val leftSide = p.location.clone().add(-1.0, 1.0, 0.0).block //東に向かってチェストを設置
                rightSide.type = Material.CHEST
                leftSide.type = Material.CHEST
                rightSide.blockData = Material.CHEST.createBlockData("[facing=north,type=right]")
                leftSide.blockData = Material.CHEST.createBlockData("[facing=north,type=left]")
                createChest(p)
                e.drops.clear()
            } else
            {
                Prefix(p, ChatColor.RED.toString() + "周りにブロックが設置されていたためアイテムをBox内に入れる事ができませんでした。")
                return
            }
            Prefix(p, ChatColor.AQUA.toString() + "Boxは正常に作成されました。")
        }
    }

    private fun createChest(p : Player)
    {
        val chest = p.location.block.state as Chest
        for (item in p.inventory)
        {
            if (item != null)
            {
                chest.inventory.addItem(item)
            }
        }
    }

    private fun setBox(p : Player) //ボックス設定
    {
        boxes!!["Boxes." + p.uniqueId + ".Name"] = p.name //プレイヤーの名前
        boxes["Boxes." + p.uniqueId + ".Location"] = p.location //プレイヤーの死んだロケーション
        SaveFile() //ファイルを保存
    }
}