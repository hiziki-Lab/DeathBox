package xyz.hiziki.deathbox.command

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import xyz.hiziki.deathbox.Main
import xyz.hiziki.deathbox.Util

class BoxCommandExecutor : CommandExecutor {
    private val boxes : YamlConfiguration = Main.boxes ?: error("Boxデータが読み込まれていません")

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("コマンドを実行できるのはプレイヤーのみです。")
        } else {
            val uniqueId = sender.uniqueId.toString()
            val location = boxes.getLocation("Boxes.$uniqueId.Location")

            if (location == null) {
                sender.sendMessage(Util().prefix() + "${ChatColor.RED}Boxデータが見つかりません")
            } else {
                val block = location.block

                if (block.type == Material.CHEST) {
                    sender.sendMessage(Util().prefix() + "${ChatColor.AQUA}あなたのBoxは${location.world?.name}の" +
                            "X:${block.x}, Y:${block.y}, Z:${block.z}にあります。")
                } else {
                    sender.sendMessage(Util().prefix() + "${ChatColor.RED}あなたのBoxは何者かによって破壊されています。")
                }
            }
        }
        return true
    }
}