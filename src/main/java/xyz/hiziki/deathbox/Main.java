package xyz.hiziki.deathbox;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.deathbox.command.Box;
import xyz.hiziki.deathbox.event.PlayerDeath;
import xyz.hiziki.deathbox.util.SaveFile;

import java.io.File;

public final class Main extends JavaPlugin
{
    private static JavaPlugin plugin;

    private static File boxFile;

    private static YamlConfiguration boxes;

    @Override
    public void onEnable()
    {
        // プラグイン起動時の処理

        super.onEnable();

        plugin = this;

        boxFile = new File(getDataFolder(), "Boxes.yml");

        boxes = YamlConfiguration.loadConfiguration(boxFile);

        getCommand("box").setExecutor(new Box());

        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);

        getLogger().info("プラグインは正常に起動しました。");
    }

    @Override
    public void onDisable()
    {
        // プラグイン停止時の処理

        super.onDisable();

        new SaveFile();

        getLogger().info("プラグインは正常に停止しました。");
    }

    public static JavaPlugin getPlugin()
    {
        return plugin;
    }

    public static File getBoxFile()
    {
        return boxFile;
    }

    public static YamlConfiguration getBoxes()
    {
        return boxes;
    }
}
