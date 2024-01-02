package it.codedev.hosteventsarenas.tasks;

import it.codedev.hosteventsarenas.HEA;
import it.codedev.hosteventsarenas.models.arena.Arena;
import it.codedev.hosteventsarenas.models.game.GameState;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private Arena arena;
    private int countdownSeconds;

    public Countdown(Arena arena) {
        this.arena = arena;
        this.countdownSeconds = HEA.getInstance().getHostUtils().getCountdownSeconds();
    }

    public void start() {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimerAsynchronously(HEA.getInstance(), 0, 20);
    }

    @Override
    public void run() {
        if(countdownSeconds == 0) {
            cancel();
            arena.start();
            return;
        }

        if(countdownSeconds <= 10 || countdownSeconds % 15 == 0) {
            arena.sendMessage("&eL'evento inizera' tra %seconds% secondi.".replace("%seconds%", String.valueOf(countdownSeconds)));
        }

        arena.sendTitle("&6" + countdownSeconds, "");

        countdownSeconds--;
    }
}
