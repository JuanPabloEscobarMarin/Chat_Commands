package unminecraft.GenericCommand.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unminecraft.GenericCommand.GenericCommand;
import unminecraft.chatcommands.ChatCommands;

import java.util.HashMap;

public class DeepwebCommand extends GenericCommand {
    private static final String namePath = "DeepWeb";

    private static HashMap<String, String> IpMap = new HashMap<String, String>();

    public static void ClearHashMap() {
        IpMap.clear();
    }

    public DeepwebCommand(ChatCommands plugin){
        super(plugin, namePath);
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

    protected String getUsernameIp(String username){
        String ip = "";

        if (IpMap.get(username) == null){
            ip = randomIpGenerator();
            IpMap.put(username, ip);
        } else {
            ip = IpMap.get(username);
        }

        return ip;
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
            String newUsername = this.getUsernameIp(player.getName());
            renderMessage(newUsername, message);
        }
        else {
            super.messageError();
        }

        return true;
    }
}
