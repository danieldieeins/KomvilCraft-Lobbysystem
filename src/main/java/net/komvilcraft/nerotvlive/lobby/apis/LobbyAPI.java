package net.komvilcraft.nerotvlive.lobby.apis;

import net.komvilcraft.nerotvlive.core.paper.Core;
import net.komvilcraft.nerotvlive.core.paper.configuration.Config;
import net.komvilcraft.nerotvlive.core.paper.utils.user.rank.RankManager;
import net.komvilcraft.nerotvlive.core.utils.Strings;
import net.komvilcraft.nerotvlive.lobby.Lobbysystem;
import net.komvilcraft.nerotvlive.lobby.jumper.JumperAPI;
import net.komvilcraft.nerotvlive.lobby.utils.LobbyUser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class LobbyAPI {

    public static HashMap<UUID, LobbyUser> onlinePlayers = new HashMap<>();
    public static String needPlayer = Strings.needPlayer();
    public static String noPerms = Strings.noPerms();
    public static Config config = new Config("plugins/Lobbysystem/config.yml");
    public static String date;
    public static boolean maintenance;
    public static ArrayList<String> maintenanceList = new ArrayList<>();

    public static LobbyUser getLobbyUser(Player player) {
        return getLobbyUser(player.getUniqueId());
    }

    public static LobbyUser getLobbyUser(UUID uuid) {
        if(onlinePlayers.containsKey(uuid)) {
            return onlinePlayers.get(uuid);
        } else {
            onlinePlayers.put(uuid,new LobbyUser(uuid));
            return getLobbyUser(uuid);
        }
    }

    public static void initConfig() {
        //MYSQL
        config.checkEntry("Core.Settings.MySQL.host","host");
        config.checkEntry("Core.Settings.MySQL.port","port");
        config.checkEntry("Core.Settings.MySQL.database","datenbank");
        config.checkEntry("Core.Settings.MySQL.username","benutzer");
        config.checkEntry("Core.Settings.MySQL.password","passwort");

        //SETTINGS
        config.checkEntry("Core.Settings.Maintenance.Enable",true);
        config.checkEntry("Core.Settings.Maintenance.BypassList",maintenanceList);

        //SAVE-RELOAD
        config.saveConfig();
        config.reloadConfig();

        //SYNC
        maintenance = config.getCFG().getBoolean("Core.Settings.Maintenance.Enable");
        maintenanceList = (ArrayList<String>)config.getCFG().getList("Core.Settings.Maintenance.BypassList");
    }

    public static void initListenerClass(Listener listener) {
        Lobbysystem.getPluginManager().registerEvents(listener, Lobbysystem.getInstance());
    }

    public static void initCommand(String commandName, CommandExecutor commandExecutor) {
        Lobbysystem.getInstance().getCommand(commandName).setExecutor(commandExecutor);
    }

    public static void sendMessage(CommandSender receiver, String message) {
        if(receiver instanceof Player) {
            LobbyUser u = getLobbyUser(((Player) receiver).getUniqueId());
            u.sendMessage(message);
        } else {
            receiver.sendMessage(Strings.prefix()+message.replace("&", "§"));
        }
    }

    public static void sendErrorMessage(CommandSender receiver, String message) {
        if(receiver instanceof Player) {
            LobbyUser u = getLobbyUser(((Player) receiver).getUniqueId());
            u.sendErrorMessage(message);
        } else {
            receiver.sendMessage("§c" + message);
        }
    }

    public static void setScoreboard(Player player) {
        LobbyUser u = getLobbyUser(player.getUniqueId());
        Scoreboard board = player.getScoreboard();
        Objective boardContent = board.getObjective("komvilcraft");
        board.resetScores("komvilcraft");
        Score komvilcraft = boardContent.getScore("§8» §e§lServer§8-§e§lIP§8:");
        boardContent.setDisplayName("§a§lKomvil§2§lCraft§8§l.§a§lnet");
        Score komvilcraftContent = boardContent.getScore("§8» §fKomvilCraft.net");
        Score placeholder0 = boardContent.getScore("§0");
        Score placeholder1 = boardContent.getScore("§1");
        Score placeholder2 = boardContent.getScore("§2");
        Score placeholder3 = boardContent.getScore("§3");
        Score placeholder4 = boardContent.getScore("§4");
        Score project = boardContent.getScore("§8» §e§lCoins§8:");
        Score projectContent = boardContent.getScore("§8» §f" + Core.getCoinsystem().getCoins(u.getUUID()));
        Score rank = boardContent.getScore("§8» §e§lRang§8:");
        Score rankContent = boardContent.getScore("§8» §f" + u.getRank().getName());
        Score time = boardContent.getScore("§8» §e§lZeit§8:");
        Score timeContent = boardContent.getScore("§8» §f" + date);
        Score jumperCount = boardContent.getScore("§8» §e§lJumper§8-§e§lScore§8:");
        Score jumperContent = boardContent.getScore("§8» §f" + JumperAPI.getHighscore(player.getUniqueId()));
        placeholder4.setScore(15);
        jumperCount.setScore(14);
        jumperContent.setScore(13);
        placeholder0.setScore(12);
        time.setScore(11);
        timeContent.setScore(10);
        placeholder1.setScore(9);
        project.setScore(8);
        projectContent.setScore(7);
        placeholder2.setScore(6);
        rank.setScore(5);
        rankContent.setScore(4);
        placeholder3.setScore(3);
        komvilcraft.setScore(2);
        komvilcraftContent.setScore(1);
        RankManager.setRank(player);
    }

    public static void renewScoreboard(Player player) {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        player.setScoreboard(sm.getNewScoreboard());
        Scoreboard board = player.getScoreboard();
        if (board.getObjective("komvilcraft") == null) {
            board.registerNewObjective("komvilcraft", "komvilcraft");
        }
        Objective boardContent = board.getObjective("komvilcraft");
        boardContent.setDisplaySlot(DisplaySlot.SIDEBAR);
        setScoreboard(player);
    }

    public static Location spawn;
    public static Location getSpawn() {
        Location location;
        Config loconfig = new Config("plugins/Lobbysystem/Locations.yml");
        if(loconfig.getCFG().contains("Spawn.World")&&loconfig.getCFG().contains("Spawn.X")&&loconfig.getCFG().contains("Spawn.Y")&&loconfig.getCFG().contains("Spawn.Z")|loconfig.getCFG().contains("Spawn.y")&&loconfig.getCFG().contains("Spawn.p")) {
            World world = Bukkit.getWorld(loconfig.getCFG().getString("Spawn.World"));
            location = new Location(world,loconfig.getCFG().getDouble("Spawn.X"),loconfig.getCFG().getDouble("Spawn.Y"),loconfig.getCFG().getDouble("Spawn.Z"),(float)loconfig.getCFG().getDouble("Spawn.y"),(float)loconfig.getCFG().getDouble("Spawn.p"));
        } else {
            location = Bukkit.getWorlds().get(0).getSpawnLocation();
        }
        spawn = location;
        return location;
    }

    public static void setSpawn(Location location) {
        Config loconfig = new Config("plugins/Lobbysystem/Locations.yml");
        loconfig.set("Spawn.World",location.getWorld().getName());
        loconfig.set("Spawn.X",location.getX());
        loconfig.set("Spawn.Y",location.getY());
        loconfig.set("Spawn.Z",location.getZ());
        loconfig.set("Spawn.y",location.getYaw());
        loconfig.set("Spawn.p",location.getPitch());
        loconfig.saveConfig();
        loconfig.reloadConfig();
        getSpawn();
    }
}