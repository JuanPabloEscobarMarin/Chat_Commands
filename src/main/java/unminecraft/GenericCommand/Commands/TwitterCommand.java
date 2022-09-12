package unminecraft.GenericCommand.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unminecraft.GenericCommand.GenericCommand;
import unminecraft.chatcommands.ChatCommands;

public class TwitterCommand extends GenericCommand {
    private static final String namePath = "Twitter";
    public TwitterCommand(ChatCommands plugin){
        super(plugin, namePath);
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
            super.messageError();
        }

        return true;
    }
}
