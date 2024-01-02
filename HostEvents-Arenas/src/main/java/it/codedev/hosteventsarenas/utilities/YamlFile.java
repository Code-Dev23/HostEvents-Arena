package it.codedev.hosteventsarenas.utilities;

import it.codedev.hosteventsarenas.HEA;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YamlFile {
    @Getter
    private final FileConfiguration config;

    private HEA main;

    public YamlFile(HEA main) {
        this.main = main;
        this.config = saveConfig("config.yml");
    }

    public void saveFile(FileConfiguration configuration, File file) {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileConfiguration saveConfig(String configName) {
        File file = new File(main.getDataFolder(), configName);

        if (!file.exists()) {
            main.saveResource(configName, false);
        }

        return loadConfig(file);
    }

    public FileConfiguration loadConfig(File file) {
        YamlConfiguration configuration = new YamlConfiguration();

        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }

        return configuration;
    }
}