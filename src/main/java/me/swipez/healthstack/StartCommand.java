package me.swipez.healthstack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    HealthStack plugin;

    public StartCommand(HealthStack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (sender.hasPermission("healthstack.toggle")){
                Player p = (Player) sender;
                if (args.length == 1){
                    if (args[0].equals("start")){
                        Bukkit.broadcastMessage(ChatColor.GREEN+"Health stack challenge has started!");
                        plugin.gamestarted = true;
                    }
                    if (args[0].equals("stop")) {
                        if (plugin.gamestarted){
                           for (Player others : Bukkit.getOnlinePlayers()){
                               others.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                           }
                        }
                        Bukkit.broadcastMessage(ChatColor.GREEN+"Health stack challenge has ended!");
                        plugin.gamestarted = false;
                    }
                    if (args[0].equals("reload")) {
                        plugin.reloadConfig();
                        p.sendMessage(ChatColor.GREEN+"Config reloaded!");
                }
                else {
                    p.sendMessage(ChatColor.CYAN+"/healthstack <start/stop>");
                }
            }
            else {
                sender.sendMessage(ChatColor.RED+"You dont have the permission needed to run this command.");
            }
        }
        else {
            sender.sendMessage(ChatColor.RED+"This command is for players only!");
        }
        return true;
    }
}
