package xyz.hiziki.deathbox.event.events

import org.bukkit.configuration.file.YamlConfiguration
import xyz.hiziki.deathbox.Main
import java.io.File
import java.io.IOException

class WorldSave {
    init {
        val boxFile : File = Main.boxFile!!
        val boxes : YamlConfiguration = Main.boxes!!

        try {
            boxes.save(boxFile)
        } catch (ex : IOException) {
            ex.printStackTrace()
        }
    }
}