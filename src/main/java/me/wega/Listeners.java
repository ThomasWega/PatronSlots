package me.wega;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Listeners implements Listener {

    private final Main mainClass;

    public Listeners(Main main) {
        this.mainClass = main;
    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
            if (event.getPlayer().hasPermission(mainClass.getConfig().getString("permission.player"))) {
                if (mainClass.getConfig().getBoolean("limit-slots.enable", true)) {
                    // check if players online are not larger int than max player + max patronslots limit in config
                    if ((mainClass.getServer().getMaxPlayers() + mainClass.getConfig().getInt("limit-slots.max")) > mainClass.getServer().getOnlinePlayers().size()) {
                        event.allow();
                    }
                    // in case of slot limit being disabled, check if limit-slot.enabled isn't true (it can be everything else other than true)
                } else if (!mainClass.getConfig().getBoolean("limit-slots.enable", true)) {
                    event.allow();
                }
                if (mainClass.getConfig().getBoolean("join-message.enable", true)) { // check if join message is enabled
                    // Delay the sendMessage to player using BukkitRunnable
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            // alternate the color codes and join the lines with \n, for the config to work properly
                            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.join("\n", mainClass.getConfig().getStringList("join-message.message"))));
                        }
                        // get the delay time from config in seconds and multiply it by 20, to get it in ticks
                    }.runTaskLater(this.mainClass, mainClass.getConfig().getLong("join-message.delay") * 20L);
                }
            }
            if (mainClass.getConfig().getBoolean("kick-message.enable", true)) {
                // alternate the color codes and join the lines with \n, for the config to work properly
                event.setKickMessage(ChatColor.translateAlternateColorCodes('&', String.join("\n", mainClass.getConfig().getStringList("kick-message.message"))));
            }
        }
    }

    // Listener for staff. Staff can bypass the limit-slots.max, also it doesn't get the join message
    @EventHandler
    public void onStaffLoginEvent(PlayerLoginEvent event) {
        if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
            if (event.getPlayer().hasPermission(mainClass.getConfig().getString("permission.staff"))) {
                event.allow();
            }
        }
    }

}
