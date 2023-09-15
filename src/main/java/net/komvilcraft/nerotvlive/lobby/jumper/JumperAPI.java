package net.komvilcraft.nerotvlive.lobby.jumper;

import net.komvilcraft.nerotvlive.core.utils.sql.MySQL;
import net.komvilcraft.nerotvlive.lobby.apis.LobbyAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class JumperAPI {

    public static String host = LobbyAPI.config.getCFG().getString("Core.Settings.MySQL.host");
    public static String port = LobbyAPI.config.getCFG().getString("Core.Settings.MySQL.port");
    public static String database = LobbyAPI.config.getCFG().getString("Core.Settings.MySQL.database");
    public static String username = LobbyAPI.config.getCFG().getString("Core.Settings.MySQL.username");
    public static String password = LobbyAPI.config.getCFG().getString("Core.Settings.MySQL.password");
    public static HashMap<Player,Jumper> Jumpers = new HashMap<>();
    private static net.komvilcraft.nerotvlive.core.utils.sql.MySQL MySQL = new MySQL(host,port,database,username,password);

    public static boolean checkTable() {
        if(!MySQL.isConnected()) {
            MySQL.getConnection();
        }
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS jumperhighscores (UUID VARCHAR(100),Highscore DOUBLE(30,2))");
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean hasHighscore(UUID uuid) {
        if(checkTable()) {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Highscore FROM jumperhighscores WHERE UUID = ?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean setHighscore(UUID uuid, int streak) {
        String SID = uuid.toString();
        if(checkTable()) {
            if ((double)streak < 0.0D) {
                return false;
            } else {
                try {
                    if (hasHighscore(uuid)) {
                        PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM jumperhighscores WHERE UUID = ?");
                        ps.setString(1, SID);
                        ps.executeUpdate();
                    }
                    PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO jumperhighscores (UUID,Highscore) VALUES (?,?)");
                    ps.setString(1, SID);
                    ps.setDouble(2, (double)streak);
                    ps.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public static int getHighscore(UUID uuid) {
        if(checkTable()) {
            double data;
            if (hasHighscore(uuid)) {
                try {
                    PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Highscore FROM jumperhighscores WHERE UUID = ?");
                    ps.setString(1, uuid.toString());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        data = rs.getDouble("Highscore");
                    } else {
                        data = 0.0;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    data = 0.0;
                }
            } else {
                data = 0.0;
            }
            return (int)data;
        } else {
            return 0;
        }
    }

    public static Location nextLoc(Location loc) {
        int x = loc.getBlockX(); int y = loc.getBlockY(); int z = loc.getBlockZ();
        if(y < 300) {
            int i = ThreadLocalRandom.current().nextInt(0,4);
            if(i == 2) {
                y = y+1;
            }
        }
        int x0 = ThreadLocalRandom.current().nextInt(0,2);
        int x1;
        if(y != loc.getBlockY()) {
            x1 = ThreadLocalRandom.current().nextInt(2,3);
        } else {
            x1 = ThreadLocalRandom.current().nextInt(2,5);
        }
        if(x0 == 1) {
            x = x-x1;
        } else {
            x = x+x1;
        }
        int z1;
        if(x == 5) {
            z1 = ThreadLocalRandom.current().nextInt(0,3);
        } else {
            z1 = ThreadLocalRandom.current().nextInt(0,4);
        }
        int z0 = ThreadLocalRandom.current().nextInt(0,2);
        if(z0 == 1) {
            z = z-z1;
        } else {
            z = z+z1;
        }
        int switchCo = ThreadLocalRandom.current().nextInt(0,2);
        Location finalLoc = new Location(loc.getWorld(), x, y, z);
        if(finalLoc.getBlock().getType().equals(Material.AIR)) {
            return finalLoc;
        } else {
            return nextLoc(loc);
        }
    }
}