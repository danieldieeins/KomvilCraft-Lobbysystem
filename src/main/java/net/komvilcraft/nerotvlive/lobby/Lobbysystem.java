package net.komvilcraft.nerotvlive.lobby;

import net.komvilcraft.nerotvlive.core.paper.Core;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.cosmetics.CosmeticInventoryClick;
import net.komvilcraft.nerotvlive.lobby.cosmetics.CosmeticInventoryManager;
import net.komvilcraft.nerotvlive.lobby.jumper.JumperAPI;
import net.komvilcraft.nerotvlive.lobby.jumper.JumperMoveEvent;
import net.komvilcraft.nerotvlive.lobby.managers.ChatManager;
import net.komvilcraft.nerotvlive.lobby.managers.ItemManager;
import net.komvilcraft.nerotvlive.lobby.utils.Broadcaster;
import net.komvilcraft.nerotvlive.lobby.utils.Receiver;
import net.komvilcraft.nerotvlive.lobby.commands.*;
import net.komvilcraft.nerotvlive.lobby.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lobbysystem extends JavaPlugin {

    private static PluginManager pm;
    private static Lobbysystem instance;
    public static PluginManager getPluginManager() {
        return pm;
    }
    public static Lobbysystem getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        pm = Bukkit.getPluginManager();
        LobbyAPI.date = Core.getAPI().getTime();
        Broadcaster.send();
        LobbyAPI.initConfig();
        JumperAPI.checkTable();
        getServer().getMessenger().registerIncomingPluginChannel(instance,"komvil:bungee",new Receiver());
        getServer().getMessenger().registerOutgoingPluginChannel(instance,"komvil:spigot");
        getServer().getMessenger().registerOutgoingPluginChannel(instance, "BungeeCord");
        CosmeticInventoryManager.initSkullInventory();
        initCommands();
        initListeners();
        LobbyAPI.getSpawn();
        for(Player all : Bukkit.getOnlinePlayers()) {
            ItemManager.giveItems(all);
        }
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterIncomingPluginChannel(instance,"komvil:bungee",new Receiver());
        getServer().getMessenger().unregisterOutgoingPluginChannel(instance,"komvil:spigot");
        getServer().getMessenger().unregisterOutgoingPluginChannel(instance,"BungeeCord");
        for(Player all : Bukkit.getOnlinePlayers()) {
            if(JumperAPI.Jumpers.containsKey(all)) {
                JumperAPI.Jumpers.get(all).fail();
            }
        }
        pm = null;
        instance = null;
    }

    private void initCommands() {
        LobbyAPI.initCommand("Broadcast", new BroadcastCommand());
        LobbyAPI.initCommand("Build", new BuildCommand());
        LobbyAPI.initCommand("Clearchat", new ClearchatCommand());
        LobbyAPI.initCommand("Day", new DayCommand());
        LobbyAPI.initCommand("Gamemode", new GamemodeCommand());
        LobbyAPI.initCommand("Jumper", new JumperCommand());
        LobbyAPI.initCommand("Maintenance", new MaintenanceCommand());
        LobbyAPI.initCommand("Night", new NightCommand());
        LobbyAPI.initCommand("Ping", new PingCommand());
        LobbyAPI.initCommand("pvp", new PVPCommand());
        LobbyAPI.initCommand("Rain", new RainCommand());
        LobbyAPI.initCommand("Spawn", new SpawnCommand());
        LobbyAPI.initCommand("Speed", new SpeedCommand());
        LobbyAPI.initCommand("SRL", new StopCommand());
        LobbyAPI.initCommand("Sun", new SunCommand());
        LobbyAPI.initCommand("Teleport", new TeleportCommand());
        LobbyAPI.initCommand("Tell", new TellCommand());
        LobbyAPI.initCommand("Test", new TestCommand());
        LobbyAPI.initCommand("Thunder", new ThunderCommand());
        LobbyAPI.initCommand("Warp",new WarpCommand());
    }

    private void initListeners() {
        LobbyAPI.initListenerClass(new ChatManager());
        LobbyAPI.initListenerClass(new CosmeticInventoryClick());
        LobbyAPI.initListenerClass(new JumperMoveEvent());
        LobbyAPI.initListenerClass(new CoinChange());
        LobbyAPI.initListenerClass(new LobbyProtection());
        LobbyAPI.initListenerClass(new PlayerInteract());
        LobbyAPI.initListenerClass(new PlayerInventory());
        LobbyAPI.initListenerClass(new PlayerJoin());
        LobbyAPI.initListenerClass(new PlayerMove());
        LobbyAPI.initListenerClass(new PlayerQuit());
    }
}
