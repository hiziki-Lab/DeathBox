package xyz.hiziki.deathbox.util;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.hiziki.deathbox.Main;

import java.io.File;
import java.io.IOException;

public class SaveFile
{
    private final File boxFile = Main.getBoxFile();

    private final YamlConfiguration boxes = Main.getBoxes();

    public SaveFile()
    {
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
