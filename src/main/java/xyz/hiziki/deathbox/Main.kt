package xyz.hiziki.deathbox

import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import xyz.hiziki.deathbox.command.BoxCommandExecutor
import xyz.hiziki.deathbox.event.PlayerDeath
import java.io.File

class Main : JavaPlugin() {
    companion object {
        var boxFile : File? = null

        var boxes : YamlConfiguration? = null
    }

    override fun onEnable() {
        // プラグイン起動時の処理
        super.onEnable()

        boxFile = File(dataFolder, "Boxes.yml")
        boxes = YamlConfiguration.loadConfiguration(boxFile!!)

        getCommand("box")!!.setExecutor(BoxCommandExecutor())
        Bukkit.getPluginManager().registerEvents(PlayerDeath(), this)

        logger.info("プラグインは正常に起動しました。")
    }

    override fun onDisable() {
        // プラグイン停止時の処理
        super.onDisable()

        logger.info("プラグインは正常に停止しました。")
    }
}