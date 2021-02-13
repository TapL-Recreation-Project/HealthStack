package me.swipez.healthstack;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public final class HealthStack extends JavaPlugin {
    public boolean gamestarted = false;
    HashMap<UUID, Double> items = new HashMap<>();

    @Override
    public void onEnable() {
        BukkitTask task = new Task(this).runTaskTimer(this, 2, 2);
        getCommand("healthstack").setExecutor(new StartCommand(this));
        getCommand("healthstack").setTabCompleter(new CommandComplete());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
