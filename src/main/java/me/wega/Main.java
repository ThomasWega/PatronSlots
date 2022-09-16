package me.wega;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    /* TODO
    - PUSH THE CHANGES TO GITHUB
     */

    public Listeners listeners;

    @Override
    public void onEnable() {

        listeners = new Listeners(this);

        // register events
        getServer().getPluginManager().registerEvents(new Listeners(this), this);

        // add config defaults for permissions and limits
        getConfig().addDefault("permission.player", "wega.patronslots.player");
        getConfig().addDefault("permission.staff", "wega.patronslots.staff");
        getConfig().addDefault("limit-slots.enable", true);
        getConfig().addDefault("limit-slots.max", 20);

        // add config defaults for kick message
        getConfig().addDefault("kick-message.enable", true);
        List<String> KickMessageList = new ArrayList<>();
        KickMessageList.add("&cBuy VIP to access full server!");
        KickMessageList.add("");
        KickMessageList.add("&8store.ratethisplugin.com");
        getConfig().addDefault("kick-message.message", KickMessageList);

        // add config defaults for join message
        getConfig().addDefault("join-message.enable", true);
        getConfig().addDefault("join-message.delay", 1.5);
        List<String> JoinMessageList = new ArrayList<>();
        JoinMessageList.add("&aYou just successfully bypassed the slot limit, because of your VIP!");
        JoinMessageList.add("&fThanks for supporting us! <3");
        getConfig().addDefault("join-message.message", JoinMessageList);

        // save the config
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}