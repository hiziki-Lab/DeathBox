package xyz.hiziki.deathbox

import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import xyz.hiziki.deathbox.command.Box
import xyz.hiziki.deathbox.event.PlayerDeath
import xyz.hiziki.deathbox.util.SaveFile
import java.io.File

class Main : JavaPlugin()
{
    override fun onEnable()
    {
        // プラグイン起動時の処理
        super.onEnable()
        boxFile = File(dataFolder, "Boxes.yml")
        boxes = YamlConfiguration.loadConfiguration(boxFile!!)
        getCommand("box")!!.setExecutor(Box())
        Bukkit.getPluginManager().registerEvents(PlayerDeath(), this)
        logger.info("プラグインは正常に起動しました。")
    }

    override fun onDisable()
    {
        // プラグイン停止時の処理
        super.onDisable()
        SaveFile()
        logger.info("プラグインは正常に停止しました。")
    }

    companion object
    {
        var boxFile : File? = null
            private set
        var boxes : YamlConfiguration? = null
            private set
    }
}