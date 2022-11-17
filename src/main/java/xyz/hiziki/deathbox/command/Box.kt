package xyz.hiziki.deathbox.command

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import xyz.hiziki.deathbox.Main
import xyz.hiziki.deathbox.util.Prefix

class Box : CommandExecutor
{
    private val boxes : YamlConfiguration = Main.boxes!!

    override fun onCommand(sender : CommandSender, command : Command, label : String, args : Array<String>) : Boolean
    {
        if (sender !is Player)
        {
            sender.sendMessage("コマンドを実行できるのはプレイヤーのみです。")
        }
        else
        {
            if (boxes.getString("Boxes." + sender.uniqueId + ".Location") == null)
            {
                Prefix(sender, ChatColor.RED.toString() + "Boxデータが見つかりません")
            }
            else
            {
                val loc = boxes.getLocation("Boxes." + sender.uniqueId + ".Location")!!

                if (loc.block.type == Material.CHEST)
                {
                    Prefix(sender, ChatColor.AQUA.toString() + "あなたのBoxは" + loc.world!!.name + "の" +
                            "X:" + loc.blockX + ", " + "Y:" + loc.blockY + ", " + "Z:" + loc.blockZ + "にあります。")
                }
                else
                {
                    Prefix(sender, ChatColor.RED.toString() + "あなたのBoxは何者かによって破壊されています。")
                }
            }
            return true
        }
        return false
    }
}