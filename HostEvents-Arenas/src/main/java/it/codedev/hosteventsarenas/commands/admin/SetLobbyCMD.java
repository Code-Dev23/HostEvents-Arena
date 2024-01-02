package it.codedev.hosteventsarenas.commands.admin;

import it.codedev.hosteventsarenas.HEA;
import it.codedev.hosteventsarenas.commands.Command;
import it.codedev.hosteventsarenas.utilities.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetLobbyCMD extends Command {
    public SetLobbyCMD() {
        super("setlobby", "hostevents.setlobby", new String[]{}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        HEA.getInstance().getConfig().set("lobby-spawn", player.getLocation());
        HEA.getInstance().saveConfig();

        CC.send(player, "&aLobby configurata con successo.");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
