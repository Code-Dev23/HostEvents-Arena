package it.codedev.hosteventsarenas.commands.admin;

import it.codedev.hosteventsarenas.HEA;
import it.codedev.hosteventsarenas.commands.Command;
import it.codedev.hosteventsarenas.models.arena.Arena;
import it.codedev.hosteventsarenas.utilities.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetupArenaCMD extends Command {
    public SetupArenaCMD() {
        super("setuparena", "hostevents.setuparena", new String[]{}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(args.length == 0) {
            CC.send(player, "&cUtilizza: /setuparena <arenaId>");
            return;
        }

        HEA.getInstance().getYamlFile().getConfig().set("arenas." + args[0], player.getLocation());
        HEA.saveConfigurations();

        HEA.getInstance().getArenaManager().getArenas().add(new Arena(args[0], player.getLocation()));

        CC.send(player, "&aLo spawn dell'arena &7" + args[0] + " &ae' stato configurato con successo!");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
