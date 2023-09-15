package net.komvilcraft.nerotvlive.lobby.apis;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;

public class WarpAPI {

    public static boolean ifWarpExists(String Warpname) {
        File WarpFile = new File("plugins/Lobbysystem/Warps/"+Warpname+".yml");
        if(WarpFile.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWarpEnabled(String Warpname) {
        if(ifWarpExists(Warpname)) {
            File WarpFile = new File("plugins/Lobbysystem/Warps/"+Warpname+".yml");
            YamlConfiguration WF = YamlConfiguration.loadConfiguration(WarpFile);
            if(WF.getBoolean("Warp.isEnabled")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void setWarp(String Warpname, World w, double x, double y, double z, float yaw, float pitch, boolean enable) {
        File WarpFile = new File("plugins/Lobbysystem/Warps/"+Warpname+".yml");
        YamlConfiguration WF = YamlConfiguration.loadConfiguration(WarpFile);
        WF.set("Warp.World",w.getName());
        WF.set("Warp.X",x);
        WF.set("Warp.Y",y);
        WF.set("Warp.Z",z);
        WF.set("Warp.Yaw",yaw);
        WF.set("Warp.Pitch",pitch);
        if(enable) {
            WF.set("Warp.isEnabled",true);
        } else {
            WF.set("Warp.isEnabled",false);
        }
        saveConfig(WarpFile,WF);
    }

    public static void setWarp(String Warpname, Player p, boolean enable) {
        setWarp(Warpname,p.getWorld(),p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ(),p.getLocation().getYaw(),p.getLocation().getPitch(),enable);
    }

    public static Location getWarp(String Warpname) {
        File WarpFile = new File("plugins/Lobbysystem/Warps/"+Warpname+".yml");
        YamlConfiguration WF = YamlConfiguration.loadConfiguration(WarpFile);
        return new Location(Bukkit.getWorld(WF.getString("Warp.World")),WF.getDouble("Warp.X"),WF.getDouble("Warp.Y"),WF.getDouble("Warp.Z"),(float)WF.getDouble("Warp.Yaw"),(float)WF.getDouble("Warp.Pitch"));
    }

    public static void removeWarp(String Warpname) {
        if(ifWarpExists(Warpname)) {
            File WarpFile = new File("plugins/Lobbysystem/Warps/"+Warpname+".yml");
            WarpFile.delete();
        }
    }

    public static void enableWarp(String Warpname) {
        if(ifWarpExists(Warpname)) {
            File WarpFile = new File("plugins/Lobbysystem/Warps/"+Warpname+".yml");
            YamlConfiguration WF = YamlConfiguration.loadConfiguration(WarpFile);
            WF.set("Warp.isEnabled",true);
            saveConfig(WarpFile,WF);
        }
    }

    public static void disableWarp(String Warpname) {
        if(ifWarpExists(Warpname)) {
            File WarpFile = new File("plugins/Lobbysystem/Warps/"+Warpname+".yml");
            YamlConfiguration WF = YamlConfiguration.loadConfiguration(WarpFile);
            WF.set("Warp.isEnabled",false);
            saveConfig(WarpFile,WF);
        }
    }

    public static void getWarpList(CommandSender s) {
        File folder = new File("plugins/Lobbysystem/Warps/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                s.sendMessage("§a"+listOfFiles[i].getName().replace(".yml",","));
            } else if (listOfFiles[i].isDirectory()) {
                s.sendMessage("§2"+listOfFiles[i].getName().replace(".yml",","));
            }
        }
    }

    public static void saveConfig(File file, YamlConfiguration cfg) {
        try {
            cfg.save(file);
        }
        catch (IOException ef) {
            ef.printStackTrace();
        }
        reloadConfig(file, cfg);
    }

    public static void reloadConfig(File file, YamlConfiguration cfg) {
        cfg = YamlConfiguration.loadConfiguration(file);
    }
}