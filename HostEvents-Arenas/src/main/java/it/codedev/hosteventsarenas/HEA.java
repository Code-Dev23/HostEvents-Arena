package it.codedev.hosteventsarenas;

import it.codedev.hosteventsarenas.commands.admin.SetLobbyCMD;
import it.codedev.hosteventsarenas.commands.admin.SetupArenaCMD;
import it.codedev.hosteventsarenas.commands.host.HostCMD;
import it.codedev.hosteventsarenas.commands.host.LeaveCMD;
import it.codedev.hosteventsarenas.listeners.GameListeners;
import it.codedev.hosteventsarenas.listeners.PlayerListeners;
import it.codedev.hosteventsarenas.managers.ArenaManager;
import it.codedev.hosteventsarenas.utilities.HostUtils;
import it.codedev.hosteventsarenas.utilities.YamlFile;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class HEA extends JavaPlugin {

    @Getter private static HEA instance;
    @Getter private HostUtils hostUtils;
    @Getter private ArenaManager arenaManager;
    @Getter private YamlFile yamlFile;

    @Override
    public void onEnable() {
        instance = this;
        yamlFile = new YamlFile(instance);
        hostUtils = new HostUtils();
        arenaManager = new ArenaManager();
        arenaManager.loadArenas();
        System.out.println(arenaManager.getArenas().toString());

        loadCommands();
        loadListeners();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void loadCommands() {
        new SetLobbyCMD();
        new SetupArenaCMD();
        new LeaveCMD();
        new HostCMD();
    }

    public void loadListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new GameListeners(), this);
        pm.registerEvents(new PlayerListeners(), this);
    }

    public static void saveConfigurations() {
        HEA.getInstance().getYamlFile().saveFile(HEA.getInstance().getYamlFile().getConfig(), new File(HEA.getInstance().getDataFolder(), "config.yml"));
    }
}