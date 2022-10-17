package xyz.hiziki.deathbox.event;

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
import xyz.hiziki.deathbox.util.SaveFile;

public class PlayerDeath implements Listener
{
    private final YamlConfiguration boxes = Main.boxes;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity();

        setBox(p);

        Block leftSide = p.getLocation().getBlock();
        Block rightSide = p.getLocation().clone().add(0, 0, -1).getBlock();

        leftSide.setType(Material.CHEST);
        rightSide.setType(Material.CHEST);

        leftSide.setBlockData(Material.CHEST.createBlockData("[facing=east,type=right]"));
        rightSide.setBlockData(Material.CHEST.createBlockData("[facing=east,type=left]"));

        Chest chest = (Chest) p.getLocation().getBlock().getState();

        for (ItemStack item : p.getInventory())
        {
            if (item != null)
            {
                chest.getInventory().addItem(item);
            }
        }
        p.getInventory().clear();
    }

    private void setBox(Player p) //ボックス設定
    {
        boxes.set("Boxes." + p.getUniqueId() + ".Name", p.getName()); //プレイヤーの名前
        boxes.set("Boxes." + p.getUniqueId() + ".Location", p.getLocation()); //プレイヤーの死んだロケーション

        new SaveFile(); //ファイルを保存
    }
}
