package it.codedev.hosteventsarenas.listeners;

import it.codedev.hosteventsarenas.HEA;
import it.codedev.hosteventsarenas.models.arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        player.teleport(HEA.getInstance().getHostUtils().getLobby());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(null);
        if(HEA.getInstance().getArenaManager().isInArena(player)) {
            Arena arena = HEA.getInstance().getArenaManager().getArena(player);
            arena.removePlayer(player);
        }
    }
}
