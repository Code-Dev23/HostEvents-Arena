package it.codedev.hosteventsarenas.game;

import com.google.common.collect.Maps;
import it.codedev.hosteventsarenas.models.arena.Arena;
import it.codedev.hosteventsarenas.models.game.GameState;
import it.codedev.hosteventsarenas.utilities.CC;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {
    private Arena arena;
    private HashMap<UUID, Integer> points;

    public Game(Arena arena) {
        this.arena = arena;
        this.points = Maps.newHashMap();
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendTitle("&6&lHOST INIZIATO", "&fRompi 20 blocchi per vincere.");

        arena.getPlayers().forEach(uuid -> {
            points.put(uuid, 0);
        });
    }

    public void addPoint(Player player) {
        int playerPoints = points.get(player.getUniqueId()) + 1;
        if(playerPoints == 20) {
            player.sendTitle(CC.color("&6&lVITTORIA!"), CC.color("&fHai vinto l'evento!"));
            arena.sendTitle(CC.color("&c&lHAI PERSO!"), CC.color("&fHai perso l'evento!"));
            return;
        }
        points.replace(player.getUniqueId(), playerPoints);
        CC.send(player, "&6&l+1");
    }
}