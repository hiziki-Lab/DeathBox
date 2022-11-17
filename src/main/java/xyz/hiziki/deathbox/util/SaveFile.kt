package xyz.hiziki.deathbox.util

import org.bukkit.configuration.file.YamlConfiguration
import xyz.hiziki.deathbox.Main
import java.io.File
import java.io.IOException

class SaveFile
{
    init
    {
        val boxFile : File = Main.Companion.getBoxFile()
        val boxes : YamlConfiguration = Main.Companion.getBoxes()
        try
        {
            boxes.save(boxFile)
        } catch (e : IOException)
        {
            e.printStackTrace()
        }
    }
}