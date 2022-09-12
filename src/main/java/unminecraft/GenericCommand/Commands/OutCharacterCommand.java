package unminecraft.GenericCommand.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unminecraft.GenericCommand.GenericCommand;
import unminecraft.chatcommands.ChatCommands;

public class OutCharacterCommand extends GenericCommand {

    public OutCharacterCommand(ChatCommands plugin) {
        super(plugin);
        super.channelName = ChatColor.DARK_GRAY + "(OCC)";
    }

    @Override
    protected void renderMessage(String username, String message){
        Bukkit.broadcastMessage(channelName + username + ": " + ChatColor.GRAY + message);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!isPlayerInstance(sender)){
            consoleCommandError();
            return false;
        }
        Player player = (Player) sender;

        if (args.length > 0) {
            String message = String.join(" ", args);
            renderMessage(player.getName(), message);
        } else {
            String errorMessage = channelName + ChatColor.GRAY + "OCC_ERROR: " + ChatColor.LIGHT_PURPLE + "Debes incluir un mensaje";
            player.sendMessage(errorMessage);
        }

        return true;
    }
}
