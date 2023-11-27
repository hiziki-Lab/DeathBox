package xyz.hiziki.deathbox

import org.bukkit.ChatColor

class Util {
    fun prefix(): String {
        return ChatColor.AQUA.toString() + "[" + ChatColor.GREEN + "Boxプラグイン" + ChatColor.AQUA + "]"
    }
}