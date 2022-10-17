package xyz.hiziki.deathbox.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import xyz.hiziki.deathbox.Main;
import xyz.hiziki.deathbox.util.Prefix;
import xyz.hiziki.deathbox.util.SaveFile;

public class PlayerDeath implements Listener
{
    private final YamlConfiguration boxes = Main.boxes;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity();

        setBox(p);

        if (p.getLocation().clone().add(0, 0, 1).getBlock().getType() == Material.AIR)
        {
            //死亡位置からz-1が空気だった場合
            Block rightSide = p.getLocation().getBlock();
            Block leftSide = p.getLocation().clone().add(0, 0, 1).getBlock(); //南に向かってチェストを設置
            rightSide.setType(Material.CHEST);
            leftSide.setType(Material.CHEST);
            rightSide.setBlockData(Material.CHEST.createBlockData("[facing=west,type=right]"));
            leftSide.setBlockData(Material.CHEST.createBlockData("[facing=west,type=left]"));
            createChest(p);
            e.getDrops().clear();
        }
        else if (p.getLocation().clone().add(0, 0, -1).getBlock().getType() == Material.AIR)
        {
            //死亡位置からz+1が空気だった場合
            Block rightSide = p.getLocation().getBlock();
            Block leftSide = p.getLocation().clone().add(0, 0, -1).getBlock(); //北に向かってチェストを設置
            rightSide.setType(Material.CHEST);
            leftSide.setType(Material.CHEST);
            rightSide.setBlockData(Material.CHEST.createBlockData("[facing=east,type=right]"));
            leftSide.setBlockData(Material.CHEST.createBlockData("[facing=east,type=left]"));
            createChest(p);
            e.getDrops().clear();
        }
        else if (p.getLocation().clone().add(1, 0, 0).getBlock().getType() == Material.AIR)
        {
            //死亡位置からx+1が空気だった場合
            Block rightSide = p.getLocation().getBlock();
            Block leftSide = p.getLocation().clone().add(1, 0, 0).getBlock(); //西に向かってチェストを設置
            rightSide.setType(Material.CHEST);
            leftSide.setType(Material.CHEST);
            rightSide.setBlockData(Material.CHEST.createBlockData("[facing=north,type=left]"));
            leftSide.setBlockData(Material.CHEST.createBlockData("[facing=north,type=right]"));
            createChest(p);
            e.getDrops().clear();
        }
        else if (p.getLocation().clone().add(-1, 0, 0).getBlock().getType() == Material.AIR)
        {
            //死亡位置からx-1が空気だった場合
            Block rightSide = p.getLocation().getBlock();
            Block leftSide = p.getLocation().clone().add(-1, 0, 0).getBlock(); //東に向かってチェストを設置
            rightSide.setType(Material.CHEST);
            leftSide.setType(Material.CHEST);
            rightSide.setBlockData(Material.CHEST.createBlockData("[facing=north,type=right]"));
            leftSide.setBlockData(Material.CHEST.createBlockData("[facing=north,type=left]"));
            createChest(p);
            e.getDrops().clear();
        }
        else
        {
            new Prefix(p, ChatColor.RED + "周りにブロックが設置されていたためアイテムをBox内に入れる事ができませんでした。");
        }
    }

    private void createChest(Player p)
    {
        Chest chest = (Chest) p.getLocation().getBlock().getState();

        for (ItemStack item : p.getInventory())
        {
            if (item != null)
            {
                chest.getInventory().addItem(item);
            }
        }
    }

    private void setBox(Player p) //ボックス設定
    {
        boxes.set("Boxes." + p.getUniqueId() + ".Name", p.getName()); //プレイヤーの名前
        boxes.set("Boxes." + p.getUniqueId() + ".Location", p.getLocation()); //プレイヤーの死んだロケーション

        new SaveFile(); //ファイルを保存
    }
}
