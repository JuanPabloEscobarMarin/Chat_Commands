package unminecraft.chatcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import unminecraft.GenericCommand.Deepweb.DeepwebCommand;
import unminecraft.GenericCommand.OCC.OutCharacterCommand;
import unminecraft.GenericCommand.Twitter.TwitterCommand;

public final class ChatCommands extends JavaPlugin {
    PluginDescriptionFile pdfile = getDescription();
    public String version = ChatColor.GREEN + pdfile.getVersion();
    public String name = ChatColor.YELLOW + "[" + ChatColor.GREEN + pdfile.getName() + ChatColor.YELLOW + "]";

    @Override
    public void onEnable() {
        String initPluginMessage = name + ChatColor.WHITE + " has been enabled in the version: " + version;
        Bukkit.getConsoleSender().sendMessage(initPluginMessage);

        // Reset hashmap - user ip
        DeepwebCommand.ClearHashMap();

        commandRegister();
    }

    @Override
    public void onDisable() {
        // Reset hashmap - user ip
        DeepwebCommand.ClearHashMap();

        String endPluginMessage = name + ChatColor.RED + " has been disabled";
        Bukkit.getConsoleSender().sendMessage(endPluginMessage);
    }

    public void commandRegister(){
        this.getCommand("twt").setExecutor(new TwitterCommand(this));
        this.getCommand("deepweb").setExecutor(new DeepwebCommand(this));
        this.getCommand("occ").setExecutor(new OutCharacterCommand(this));
    }
}
