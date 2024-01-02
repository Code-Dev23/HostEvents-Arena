package it.codedev.hosteventsarenas.listeners;

import it.codedev.hosteventsarenas.HEA;
import it.codedev.hosteventsarenas.models.arena.Arena;
import it.codedev.hosteventsarenas.models.game.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GameListeners implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Arena arena = HEA.getInstance().getArenaManager().getArena(player);
        if(!HEA.getInstance().getArenaManager().isInArena(player) || !arena.getState().equals(GameState.LIVE)) return;
        arena.getGame().addPoint(player);
    }
}
