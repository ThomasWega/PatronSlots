package me.wega;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    /* TODO
    - ADD MORE PERMS OPTIONS AND LIMIT TO THEM
    - ADD PLAYER MESSAGE AFTER HE JOINS, THAT HE BYPASSED THE SLOT LIMIT
     */

    public Listeners listeners;

    @Override
    public void onEnable() {
        listeners = new Listeners(this);

        getServer().getPluginManager().registerEvents(new Listeners(this), this);

        getConfig().addDefault("permission", "wega.patronslots");

        List<String> KickMessageList = new ArrayList<>();
        KickMessageList.add("&cBuy VIP to access full server!");
        KickMessageList.add("");
        KickMessageList.add("&8store.ratethisplugin.com");
        getConfig().addDefault("kick-message", KickMessageList);


        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}