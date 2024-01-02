package it.codedev.hosteventsarenas.utilities;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

@UtilityClass
public class CC {
    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public String[] color(String... text) {
        return Arrays.stream(text).map(CC::color).toArray(String[]::new);
    }
    public void send(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }
    public void send(CommandSender sender, String... messages) {
        Arrays.stream(messages).forEach(message -> send(sender, message));
    }
}
