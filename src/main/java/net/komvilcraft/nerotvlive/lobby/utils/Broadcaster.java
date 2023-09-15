package net.komvilcraft.nerotvlive.lobby.utils;

import net.komvilcraft.nerotvlive.core.paper.Core;
import net.komvilcraft.nerotvlive.core.utils.Strings;
import net.komvilcraft.nerotvlive.lobby.Lobbysystem;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import net.komvilcraft.nerotvlive.lobby.jumper.Jumper;
import net.komvilcraft.nerotvlive.lobby.jumper.JumperAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Bukkit.getServer;

public class Broadcaster {

    static ArrayList<String> Messages = new ArrayList<>();

    private static void saveDefaultConfig() {
        LobbyAPI.config.checkEntry("Core.Settings.Broadcasts.Enable",false);
        LobbyAPI.config.checkEntry("Core.Settings.Broadcasts.SecondInterval",10);
        LobbyAPI.config.checkEntry("Core.Strings.Broadcasts",Messages);
        Messages = (ArrayList<String>)LobbyAPI.config.getCFG().getList("Core.Strings.Broadcasts");
        LobbyAPI.config.checkEntry("Core.Actionbar.Message","test");
        LobbyAPI.config.saveConfig();
        LobbyAPI.config.reloadConfig();
    }

    public static void send() {
        saveDefaultConfig();
        sendActionbar(getServer().getScheduler());
        sendScoreboard(getServer().getScheduler());
        autoRenew(getServer().getScheduler());
        if(LobbyAPI.config.getCFG().getBoolean("Core.Settings.Broadcasts.Enable")) {
            startBroadcastTimer(getServer().getScheduler());
        }
    }

    private static void sendActionbar(BukkitScheduler scheduler) {
        int scheduleId = scheduler.scheduleSyncDelayedTask(Lobbysystem.getInstance(), () -> {
            for(Player p : Bukkit.getOnlinePlayers()) {
                LobbyUser u = LobbyAPI.getLobbyUser(p);
                if(JumperAPI.Jumpers.containsKey(p)) {
                    Jumper j = JumperAPI.Jumpers.get(p);
                    if (j.getHighscore() >= j.getStreak()) {
                        p.sendActionBar("§aJumper§8-§aStreak§8: §f" + j.getStreak() + "§7 §8(§7Highscore§8:§7 " + j.getHighscore() + "§8)");
                    } else {
                        p.sendActionBar("§aJumper§8-§aStreak§8: §f" + j.getStreak() + "§7 §8(§7Highscore§8:§7 " + j.getStreak() + "§8)");
                    }
                } else if(u.isPVPMode()) {
                    p.sendActionBar("§8/pvp um den PVP-Mode zu verlassen!");
                } else {
                    p.sendActionBar("§cCLOSED ALPHA");
                }
            }
            sendActionbar(scheduler);
        },10);
    }

    private static void startBroadcastTimer(BukkitScheduler scheduler) {
        int scheduleId = scheduler.scheduleSyncDelayedTask(Lobbysystem.getInstance(), () -> {
            Integer size = Messages.size();
            Integer random = ThreadLocalRandom.current().nextInt(0,size);
            Bukkit.broadcastMessage(Strings.prefix()+Messages.get(random).replace("&","§"));
            startBroadcastTimer(scheduler);
        }, LobbyAPI.config.getCFG().getLong("Core.Settings.Broadcasts.SecondInterval")*20);
    }

    private static void sendScoreboard(BukkitScheduler scheduler) {
        int scheduleId = scheduler.scheduleSyncDelayedTask(Lobbysystem.getInstance(), () -> {
            for(Player all : Bukkit.getOnlinePlayers()) {
                LobbyAPI.setScoreboard(all);
            }
            sendScoreboard(scheduler);
        },15);
    }

    private static void autoRenew(BukkitScheduler scheduler) {
        int scheduleId = scheduler.scheduleSyncDelayedTask(Lobbysystem.getInstance(), () -> {
            if(!LobbyAPI.date.equals(Core.getAPI().getTime())) {
                LobbyAPI.date = Core.getAPI().getTime();
                for(Jumper j : JumperAPI.Jumpers.values()) {
                    if(j.getStreak() > j.getHighscore()) {
                        JumperAPI.setHighscore(j.getPlayer().getUniqueId(), j.getStreak());
                    }
                }
                for (Player all : Bukkit.getOnlinePlayers()) {
                    LobbyAPI.renewScoreboard(all);
                }
            }
            autoRenew(scheduler);
        },15*20);
    }

    public static void rotate(BukkitScheduler scheduler, Entity e) {
        int scheduleId = scheduler.scheduleSyncDelayedTask(Lobbysystem.getInstance(), () -> {
            e.setRotation(e.getLocation().getYaw() - 1, e.getLocation().getPitch());
            rotate(scheduler, e);
        }, 1);
    }
}