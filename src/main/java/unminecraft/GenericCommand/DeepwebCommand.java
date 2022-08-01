package unminecraft.GenericCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unminecraft.chatcommands.ChatCommands;

import java.util.HashMap;

public class DeepwebCommand extends GenericCommand {

    private static HashMap<String, String> IpMap = new HashMap<String, String>();

    public static void ClearHashMap() {
        IpMap.clear();
    }

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
        return ip.toString();
    }

    @Override
    protected void renderMessage(String username, String message){
        String ip = "";

        if (IpMap.get(username) == null){
            ip = randomIpGenerator();
            IpMap.put(username, ip);
        } else {
            ip = IpMap.get(username);
        }


        String userIp = ChatColor.GREEN + ip + ": ";
        Bukkit.broadcastMessage(channelName + userIp + ChatColor.GRAY + message);
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
            String errorMessage = channelName + ChatColor.DARK_GREEN + "UNKNOWN_ERROR: " + ChatColor.GRAY + "Debes incluir un mensaje";
            player.sendMessage(errorMessage);
        }

        return true;
    }
}
