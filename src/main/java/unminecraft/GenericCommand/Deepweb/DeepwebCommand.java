package unminecraft.GenericCommand.Deepweb;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unminecraft.GenericCommand.GenericCommand;
import unminecraft.chatcommands.ChatCommands;

public class DeepwebCommand extends GenericCommand {
    public DeepwebCommand(ChatCommands plugin){
        super(plugin);
        super.channelName = ChatColor.BLACK + "(" + ChatColor.DARK_GREEN + "Deepweb" + ChatColor.BLACK + ") ";
    }

    private String randomIpGenerator(){
        int MIN = 0;
        int MAX = 256;

        StringBuilder ip = new StringBuilder();

        for (int i=0; i<4; i++){
            int num = (int) (Math.random() * (MAX - MIN + 1) + MIN);
            ip.append(num);

            if (i < 3) ip.append(".");
        }

        ip.append(": ");
        return ip.toString();
    }

    private boolean isValidPlayer(Player player) {
        String NAME_PERMISSION_ROLE = "Criminal";
        return (player.hasPermission(NAME_PERMISSION_ROLE));
    }

    @Override
    protected void renderMessage(String username, String message){
        String userIp = ChatColor.GREEN + randomIpGenerator();
        Bukkit.broadcastMessage(channelName + userIp + ChatColor.GRAY + message);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!isPlayerInstance(sender)){
            consoleCommandError();
            return false;
        }

        Player player = (Player) sender;

        if (!isValidPlayer(player)){
            player.sendMessage("Aun no estas preparado para acceder al bajo mundo, te falta calle");
            return false;
        }

        if (args.length > 0) {
            String message = String.join(" ", args);
            renderMessage("", message);
        }
        else {
            String errorMessage = channelName + ChatColor.DARK_GREEN + "UNKNOWN_ERROR: " + ChatColor.GRAY + "Debes incluir un mensaje";
            player.sendMessage(errorMessage);
        }

        return true;
    }
}
