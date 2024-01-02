package it.codedev.hosteventsarenas.commands;

import it.codedev.hosteventsarenas.utilities.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class Command extends BukkitCommand {
    boolean useConsole;

    public Command(String command, String permission, String[] aliases, boolean useConsole) {
        super(command);

        this.setAliases(Arrays.asList(aliases));
        this.setPermission(permission);
        this.setPermissionMessage(CC.color("&cYou don't have permission."));
        this.useConsole = useConsole;

        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getServer());
            commandMap.register(command, this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!useConsole && !(sender instanceof Player)) {
            Bukkit.getLogger().warning("You can't execute this command from console.");
            return true;
        }
        execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return onTabComplete(sender, args);
    }

    public abstract List<String> onTabComplete(CommandSender sender, String[] args);
}