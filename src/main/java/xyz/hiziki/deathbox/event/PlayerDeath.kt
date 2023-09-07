package xyz.hiziki.deathbox.event

import org.bukkit.ChatColor
import org.bukkit.Location
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

class PlayerDeath : Listener {
    private val boxes: YamlConfiguration = Main.boxes ?: error("Boxデータが読み込まれていません")

    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {
        val player = e.entity
        val location = setBox(player)

        if (isAirBlock(location)) {
            createChest(player, location)
            e.drops.clear()
            Prefix(player, "${ChatColor.AQUA}Boxは正常に作成されました。")
        } else {
            Prefix(
                    player,
                    "${ChatColor.RED}周りにブロックが設置されていたためアイテムをBox内に入れる事ができませんでした。"
            )
        }
    }

    private fun setBox(player: Player): Location {
        val uniqueId = player.uniqueId.toString()
        val location = player.location

        boxes["Boxes.$uniqueId.Name"] = player.name
        boxes["Boxes.$uniqueId.Location"] = location

        SaveFile()

        return location
    }

    private fun isAirBlock(location: Location): Boolean {
        return location.clone().add(0.0, 0.0, 0.0).block.type == Material.AIR
    }

    private fun createChest(player: Player, location: Location) {
        val chest = getChest(location)

        player.inventory.contents.filterNotNull().forEach { item ->
            chest.inventory.addItem(item)
        }
    }

    private fun getChest(location: Location): Chest {
        val block = location.block
        val facing = when {
            location.x < 0 -> "east"
            location.x > 0 -> "west"
            location.z < 0 -> "south"
            else -> "north"
        }
        val chest = block.state as Chest
        val chestData =
                "[facing=$facing,type=${if (facing == "north" || facing == "south") "left" else "right"}]"
        chest.blockData = Material.CHEST.createBlockData(chestData)
        return chest
    }
}
