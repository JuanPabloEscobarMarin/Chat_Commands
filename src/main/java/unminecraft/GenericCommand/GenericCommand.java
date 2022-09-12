package unminecraft.GenericCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import unminecraft.chatcommands.ChatCommands;

public class GenericCommand implements CommandExecutor {
    protected final ChatCommands plugin;
    protected final FileConfiguration config;

    protected final String generalPath;

    protected String channelName;

    public GenericCommand(ChatCommands plugin, String namePath){
        this.plugin = plugin;
        this.config = plugin.getConfig();

        this.generalPath = "Config." + namePath;
        String baseTemplate = config.getString(generalPath + ".channelName");

        if (baseTemplate == null) baseTemplate = "&cERROR_NAME_NOT_LOAD";

        this.channelName = ChatColor.translateAlternateColorCodes('&', baseTemplate);
    }

    protected boolean isPlayerInstance(CommandSender sender){
        return (sender instanceof Player);
    }

    protected void consoleCommandError(){
        String errorMessage = ChatColor.RED + " No puedes ejecutar comandos desde la consola";
        Bukkit.getConsoleSender().sendMessage(this.plugin.name + errorMessage);
    }

    protected void messageError(){
        String stringTemplate = this.config.getString(this.generalPath + ".errorMessage");

        if (stringTemplate == null) return;

        String message = stringTemplate.replaceAll("%channel_name%", this.channelName);
        Bukkit.getConsoleSender().sendMessage(message);
    }

    protected void renderMessage(String username, String message){
        String stringTemplate = config.getString(this.generalPath + ".messageTemplate");

        if (stringTemplate == null) return;


        // Channel Name replace
        String renderMessage = stringTemplate.replaceAll("%channel_name%", this.channelName);

        // Username replace
        renderMessage = renderMessage.replaceAll("%player_username%", username);

        // Message replace
        renderMessage = renderMessage.replaceAll("%message%", message);

        renderMessage = ChatColor.translateAlternateColorCodes('&', renderMessage);

        Bukkit.broadcastMessage(renderMessage);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        return true;
    }

}
