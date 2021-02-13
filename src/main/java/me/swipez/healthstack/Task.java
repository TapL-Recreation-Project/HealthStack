package me.swipez.healthstack;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Task extends BukkitRunnable {

    HealthStack plugin;

    public Task(HealthStack plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (plugin.gamestarted) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                ItemStack[] inv = p.getInventory().getContents();
                double newval = 0;
                plugin.items.put(p.getUniqueId(), newval);
                for (int i = 0; i < 41; i++) {
                    if (inv[i] != null) {
                        newval = newval + inv[i].getAmount();
                    }
                }
                plugin.items.put(p.getUniqueId(), newval);
                p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20 + (plugin.items.get(p.getUniqueId()) * 0.2));
            }
        }
    }
}

