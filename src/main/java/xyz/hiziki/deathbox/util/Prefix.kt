package xyz.hiziki.deathbox.util

import org.bukkit.ChatColor
import org.bukkit.entity.Player

class Prefix(p : Player, msg : String)
{
    init
    {
        p.sendMessage(
            ChatColor.AQUA.toString() + "[" + ChatColor.GREEN + "Boxプラグイン" + ChatColor.AQUA + "]" + ChatColor.RESET + msg
        )
    }
}