package xyz.hiziki.deathbox.util;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.hiziki.deathbox.Main;

import java.io.File;
import java.io.IOException;

public class SaveFile
{
    public SaveFile()
    {
        File boxFile = Main.getBoxFile();
        YamlConfiguration boxes = Main.getBoxes();

        try
        {
            boxes.save(boxFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
