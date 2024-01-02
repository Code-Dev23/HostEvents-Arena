package it.codedev.hosteventsarenas.commands.host;

import it.codedev.hosteventsarenas.HEA;
import it.codedev.hosteventsarenas.commands.Command;
import it.codedev.hosteventsarenas.models.arena.Arena;
import it.codedev.hosteventsarenas.utilities.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class LeaveCMD extends Command {
    public LeaveCMD() {
        super("leave", "hostevents.leave", new String[]{"l"}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Arena arena = HEA.getInstance().getArenaManager().getArena(player);
        if(!HEA.getInstance().getArenaManager().isInArena(player)) {
            CC.send(player, "&cNon sei in nessun evento.");
            return;
        }
        arena.removePlayer(player);
        arena.sendMessage("&c%player% &ee' uscito.".replace("%player%", player.getName()));
        CC.send(player, "&eSei uscito dall'evento con successo.");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
