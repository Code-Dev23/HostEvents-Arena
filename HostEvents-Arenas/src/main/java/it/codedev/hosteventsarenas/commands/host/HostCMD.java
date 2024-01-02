package it.codedev.hosteventsarenas.commands.host;

import it.codedev.hosteventsarenas.HEA;
import it.codedev.hosteventsarenas.commands.Command;
import it.codedev.hosteventsarenas.models.arena.Arena;
import it.codedev.hosteventsarenas.utilities.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HostCMD extends Command {
    public HostCMD() {
        super("host", "hostevents.host", new String[]{"arena"}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
            CC.send(player, "&6&l• &fLista delle arene disponibili:");
            for(Arena a : HEA.getInstance().getArenaManager().getArenas()) {
                CC.send(player, "&e&l- " + a.getId() + " &7| &f" + a.getState().name());
            }
        } else if(args.length == 2 && args[0].equalsIgnoreCase("join")) {
            if(HEA.getInstance().getArenaManager().isInArena(player)) {
                CC.send(player, "&cSei gia' in un'evento!");
                return;
            }
            if(HEA.getInstance().getArenaManager().isValidArena(args[1])) {
                CC.send(player, "&cL'evento non e' stato trovato!");
                return;
            }
            Arena arenaToPlay = HEA.getInstance().getArenaManager().getArena(args[1]);
            arenaToPlay.addPlayer(player);
            arenaToPlay.sendMessage("&6%player% &ee' entrato.".replace("%player%", player.getName()));
        } else {
            CC.send(player, "&6&l• &fLista dei comandi:");
            CC.send(player, "&e&l- &f/arena list");
            CC.send(player, "&e&l- &f/arena join <id>");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
