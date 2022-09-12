package unminecraft.GenericCommand.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unminecraft.GenericCommand.GenericCommand;
import unminecraft.chatcommands.ChatCommands;

public class OutCharacterCommand extends GenericCommand {
    private static final String namePath = "OCC";

    public OutCharacterCommand(ChatCommands plugin) {
<<<<<<< HEAD:src/main/java/unminecraft/GenericCommand/OutCharacterCommand.java
        super(plugin);
        super.channelName = ChatColor.GOLD + "/OCC ";
    }

    @Override
    protected void renderMessage(String username, String message){
        Bukkit.broadcastMessage(channelName + username + ": " + ChatColor.WHITE + message);
=======
        super(plugin, namePath);
>>>>>>> develop:src/main/java/unminecraft/GenericCommand/Commands/OutCharacterCommand.java
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
<<<<<<< HEAD:src/main/java/unminecraft/GenericCommand/OutCharacterCommand.java
            String errorMessage = channelName + ChatColor.GOLD + "OCC_ERROR: " + ChatColor.LIGHT_PURPLE + "Debes incluir un mensaje";
            player.sendMessage(errorMessage);
=======
            super.messageError(player);
>>>>>>> develop:src/main/java/unminecraft/GenericCommand/Commands/OutCharacterCommand.java
        }

        return true;
    }
}
