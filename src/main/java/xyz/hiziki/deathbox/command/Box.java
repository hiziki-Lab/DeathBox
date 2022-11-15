package xyz.hiziki.deathbox.command;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.deathbox.Main;
import xyz.hiziki.deathbox.util.Prefix;

public class Box implements CommandExecutor
{
    private final JavaPlugin plugin = Main.getPlugin();

    private final YamlConfiguration boxes = Main.getBoxes();

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player p))
        {
            if (sender instanceof ConsoleCommandSender)
            {
                plugin.getLogger().info("コマンドを実行できるのはプレイヤーのみです。");
            }
            else
            {
                sender.sendMessage("コマンドを実行できるのはプレイヤーのみです。");
            }
        }
        else
        {
            if (boxes.getString("Boxes." + p.getUniqueId() + ".Location") == null)
            {
                new Prefix(p, ChatColor.RED + "Boxデータが見つかりません");
            }
            else
            {
                Location loc = boxes.getLocation("Boxes." + p.getUniqueId() + ".Location");

                if (loc.getBlock().getType() == Material.CHEST)
                {
                    new Prefix(p,
                            ChatColor.AQUA + "あなたのBoxは" + loc.getWorld().getName() + "の"
                                    + "X:" + loc.getBlockX() + ", "
                                    + "Y:" + loc.getBlockY() + ", "
                                    + "Z:" + loc.getBlockZ() + "にあります。");
                }
                else
                {
                    new Prefix(p, ChatColor.RED + "あなたのBoxは何者かによって破壊されています。");
                }
            }
            return true;
        }
        return false;
    }
}
