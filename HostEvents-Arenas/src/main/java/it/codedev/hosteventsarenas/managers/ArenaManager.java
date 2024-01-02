package it.codedev.hosteventsarenas.managers;

import com.google.common.collect.Lists;
import it.codedev.hosteventsarenas.HEA;
import it.codedev.hosteventsarenas.models.arena.Arena;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;

public class ArenaManager {
    @Getter public final List<Arena> arenas = Lists.newArrayList();

    public void loadArenas() {
        for(String s : HEA.getInstance().getYamlFile().getConfig().getConfigurationSection("arenas").getKeys(false)) {
            System.out.println("id: " + s);
            System.out.println("loc: " + HEA.getInstance().getHostUtils().getArenaLocation(s).toString());
            arenas.add(new Arena(s, HEA.getInstance().getHostUtils().getArenaLocation(s)));
        }
    }

    public Arena getArena(Player player) {
        return arenas.stream()
                .filter(arena -> arena.getPlayers().contains(player.getUniqueId()))
                .findFirst()
                .orElse(null);
    }
    public Arena getArena(String id) {
        return arenas.stream()
                .filter(arena -> arena.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean isInArena(Player player) {
        return getArena(player) != null;
    }

    public boolean isValidArena(String arenaId) {
        return getArena(arenaId) != null;
    }
}