package unminecraft.GenericCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unminecraft.chatcommands.ChatCommands;

public class GenericCommand implements CommandExecutor {
    protected final ChatCommands plugin;
    protected String channelName;

    public GenericCommand(ChatCommands plugin){
        this.plugin = plugin;
        this.channelName = "General";
    }

    protected boolean isPlayerInstance(CommandSender sender){
        return (sender instanceof Player);
    }

    protected void consoleCommandError(){
        String errorMessage = ChatColor.RED + " No puedes ejecutar comandos desde la consola";
        Bukkit.getConsoleSender().sendMessage(this.plugin.name + errorMessage);
    }

    protected void renderMessage(String username, String message){
        Bukkit.broadcastMessage(ChatColor.WHITE + username + ": " + message);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        return true;
    }

}
