package it.codedev.hosteventsarenas.models.arena;

import com.google.common.collect.Lists;
import it.codedev.hosteventsarenas.HEA;
import it.codedev.hosteventsarenas.game.Game;
import it.codedev.hosteventsarenas.models.game.GameState;
import it.codedev.hosteventsarenas.tasks.Countdown;
import it.codedev.hosteventsarenas.utilities.CC;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@Data
public class Arena {
    private String id;
    private Location spawn;

    private GameState state;
    private List<UUID> players;
    private Countdown countdown;
    private Game game;

    public Arena(String id, Location spawn) {
        this.id = id;
        this.spawn = spawn;
        this.players = Lists.newArrayList();
        this.state = GameState.WAITING;
        this.countdown = new Countdown(this);
        this.game = new Game(this);
    }

    // PLAYER METHODS

    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        if(state == GameState.WAITING && players.size() >= HEA.getInstance().getHostUtils().getRequiredPlayers()) {
            countdown.start();
        }
    }
    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(HEA.getInstance().getHostUtils().getLobby());
        sendTitle("", "");

        if(state == GameState.COUNTDOWN && players.size() < HEA.getInstance().getHostUtils().getRequiredPlayers()) {
            sendMessage("&cNon ci sono abbastanza player per iniziare l'evento!");
            reset(false);
        }

        if(state == GameState.LIVE && players.size() < HEA.getInstance().getHostUtils().getRequiredPlayers()) {
            sendMessage("&cEvento terminato per mancanza di player!");
            reset(true);
        }
    }

    // ARENA UTILS

    public void sendMessage(String message) {
        players.forEach(player -> {
            CC.send(Bukkit.getPlayer(player), message);
        });
    }
    public void sendTitle(String title, String subTitle) {
        players.forEach(player -> {
            Bukkit.getPlayer(player).sendTitle(CC.color(title), CC.color(subTitle));
        });
    }

    // GAME METHODS

    public void start() {
        game.start();
    }
    public void reset(boolean kickPlayers) {
        if(kickPlayers) {
            players.forEach(uuid -> {
                Bukkit.getPlayer(uuid).teleport(spawn);
            });
            players.clear();
        }
        sendTitle("", "");
        state = GameState.WAITING;
        countdown.cancel();
        countdown = new Countdown(this);
    }
}