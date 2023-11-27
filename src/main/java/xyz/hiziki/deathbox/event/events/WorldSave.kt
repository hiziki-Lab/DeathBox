package xyz.hiziki.deathbox.event.events

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.event.world.WorldSaveEvent
import xyz.hiziki.deathbox.Main
import java.io.File
import java.io.IOException

class WorldSave(e: WorldSaveEvent) {
    init {
        val boxFile : File = Main.boxFile!!
        val boxes : YamlConfiguration = Main.boxes!!

        try {
            boxes.save(boxFile)
        } catch (e : IOException) {
            e.printStackTrace()
        }
    }
}