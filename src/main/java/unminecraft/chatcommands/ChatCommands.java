package unminecraft.chatcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import unminecraft.ChatEvents.ChatController;
import unminecraft.GenericCommand.Commands.DeepwebCommand;
import unminecraft.GenericCommand.Commands.OutCharacterCommand;
import unminecraft.GenericCommand.Commands.TwitterCommand;

import java.io.File;

public final class ChatCommands extends JavaPlugin {
    PluginDescriptionFile pdfile = getDescription();
    public String pathConfig;

    public String version = ChatColor.GREEN + pdfile.getVersion();
    public String name = ChatColor.YELLOW + "[" + ChatColor.GREEN + pdfile.getName() + ChatColor.YELLOW + "]";

    @Override
    public void onEnable() {
        String initPluginMessage = name + ChatColor.WHITE + " has been enabled in the version: " + version;
        Bukkit.getConsoleSender().sendMessage(initPluginMessage);

        this.saveDefaultConfig();

        // Reset hashmap - user ip
        DeepwebCommand.ClearHashMap();

        commandRegister();
        eventsRegister();
        configRegister();
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

    public void eventsRegister(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ChatController(this), this);
    }

    public void configRegister(){
        File config = new File(this.getDataFolder(), "config.yml");
        pathConfig = config.getPath();

        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
}

