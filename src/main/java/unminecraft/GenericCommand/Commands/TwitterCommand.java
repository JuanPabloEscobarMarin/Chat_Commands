package unminecraft.GenericCommand.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unminecraft.GenericCommand.GenericCommand;
import unminecraft.chatcommands.ChatCommands;

public class TwitterCommand extends GenericCommand {
    public TwitterCommand(ChatCommands plugin){
        super(plugin);
        super.channelName = ChatColor.WHITE + "(" + ChatColor.BLUE + "Twitter" + ChatColor.WHITE + ")";
    }

    @Override
    protected void renderMessage(String username, String message){
        String twitterUser = ChatColor.BLUE + "@" + username + ": ";
        Bukkit.broadcastMessage(channelName + twitterUser + ChatColor.WHITE + message);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!isPlayerInstance(sender)){
            consoleCommandError();
            return false;
        }
        Player player = (Player) sender;

        if (args.length > 0) {
            String message = String.join(" ", args);
            renderMessage(player.getName(), message);
        }
        else {
            String errorMessage = channelName + ChatColor.BLUE + "TWITTER_ERROR: " + ChatColor.WHITE + "Debes incluir un mensaje";
            player.sendMessage(errorMessage);
        }

        return true;
    }
}
