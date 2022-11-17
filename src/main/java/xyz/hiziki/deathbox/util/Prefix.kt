package xyz.hiziki.deathbox.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Prefix
{
    public Prefix(Player p, String msg)
    {
        p.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "Boxプラグイン" + ChatColor.AQUA + "]" +
                ChatColor.RESET + msg);
    }
}
