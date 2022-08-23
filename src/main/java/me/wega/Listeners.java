package me.wega;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class Listeners implements Listener {

    private final Main mainClass;

    public Listeners(Main main){
        this.mainClass = main;
    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event){
        if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
             if (event.getPlayer().hasPermission(mainClass.getConfig().getString("permission"))){
                event.allow();
            }else if (!event.getPlayer().hasPermission(mainClass.getConfig().getString("permission"))){
                 event.setKickMessage(ChatColor.translateAlternateColorCodes('&', String.join("\n", mainClass.getConfig().getStringList("kick-message"))));
             }
        }
    }
}
