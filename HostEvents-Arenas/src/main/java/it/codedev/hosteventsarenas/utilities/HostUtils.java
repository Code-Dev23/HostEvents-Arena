package it.codedev.hosteventsarenas.utilities;

import it.codedev.hosteventsarenas.HEA;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class HostUtils {
    private final FileConfiguration config = HEA.getInstance().getYamlFile().getConfig();

    public int getRequiredPlayers() {
        return config.getInt("required-players");
    }
    public int getCountdownSeconds() {
        return config.getInt("countdown-seconds");
    }
    public Location getArenaLocation(String arenaId) {

        if (config == null) {
            Bukkit.getLogger().severe("Arena not found! " + arenaId);
            return null;
        }

        World world = Bukkit.getWorld(config.getString("arenas." + arenaId + ".world"));
        double x = config.getDouble("arenas." + arenaId + ".x");
        double y = config.getDouble("arenas." + arenaId + ".y");
        double z = config.getDouble("arenas." + arenaId + ".z");
        float pitch = (float) config.getDouble("arenas." + arenaId + ".pitch");
        float yaw = (float) config.getDouble("arenas." + arenaId + ".yaw");

        return new Location(world, x, y, z, yaw, pitch);
    }

    public Location getLobby() {
        World world = Bukkit.getWorld(config.getString("lobby-spawn.world"));
        double x = config.getDouble("lobby-spawn.x");
        double y = config.getDouble("lobby-spawn.y");
        double z = config.getDouble("lobby-spawn.z");
        float pitch = (float) config.getDouble("lobby-spawn.pitch");
        float yaw = (float) config.getDouble("lobby-spawn.yaw");
        Location loc = new Location(world, x, y, z, yaw, pitch);
        if(loc == null) {
            Bukkit.getLogger().severe("Lobby location not found!");
            return null;
        }
        return loc;
    }
}