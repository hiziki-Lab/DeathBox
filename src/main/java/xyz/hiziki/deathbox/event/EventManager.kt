package xyz.hiziki.deathbox.event

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.world.WorldSaveEvent
import org.bukkit.plugin.java.JavaPlugin
import xyz.hiziki.deathbox.event.events.PlayerDeath
import xyz.hiziki.deathbox.event.events.WorldSave

class EventManager(plugin: JavaPlugin) : Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {
        PlayerDeath(e)
    }

    @EventHandler
    fun onWorldSave(e: WorldSaveEvent) {
        WorldSave()
    }
}