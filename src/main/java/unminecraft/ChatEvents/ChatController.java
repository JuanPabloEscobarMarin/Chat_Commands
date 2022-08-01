package unminecraft.ChatEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import unminecraft.chatcommands.ChatCommands;

public class ChatController implements Listener {
    private final ChatCommands plugin;

    public ChatController(ChatCommands plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void modifyGeneralChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();

        // Si no se trata de un comando, el mensaje debera ser enviado como un tweet (para mantener el rol)
        if (message.charAt(0) != '/'){
            event.setCancelled(true);

            new BukkitRunnable() {
                @Override
                public void run() {
                    twitterCommandTrigger(player, message);
                }
            }.runTask(plugin);
        }
    }

    private void twitterCommandTrigger(Player mainPlayer, String args){
        mainPlayer.chat("/" + "twt" + " " + args);
    }
}
