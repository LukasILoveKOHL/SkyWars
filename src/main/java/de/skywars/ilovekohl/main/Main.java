package de.skywars.ilovekohl.main;

import de.skywars.ilovekohl.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class Main extends JavaPlugin {

    public static String p = "§7[§aHimmels§fKampf§7] ";


    public static Main instance;
    public static Main getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        System.out.println("SkyWars on");

        instance = this;
        new JoinListener(this);



    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
        Player p = (Player) s;

        if (cmd.getName().equalsIgnoreCase("cores")){
            if (p.hasPermission("cores.*")){
                if (args.length == 0) {
                    p.sendMessage(Main.p + "Bitte benutze /cores setspawn lobby | rot | blau");
                } else if (args.length >= 1) {
                    if (args[0].equalsIgnoreCase("setspawn")) {
                        if (args[1].equalsIgnoreCase("red") || args[1].equalsIgnoreCase("blue") || args[1].equalsIgnoreCase("lobby") || args[1].equalsIgnoreCase("spec")) {
                            double x = p.getLocation().getX();
                            double y = p.getLocation().getY();
                            double z = p.getLocation().getZ();
                            String w = p.getWorld().getName();

                            float yaw = p.getLocation().getYaw();
                            float pitch = p.getLocation().getPitch();

                            getConfig().set("cores.spawn." + args[1] + ".X", x);
                            getConfig().set("cores.spawn." + args[1] + ".Y", y);
                            getConfig().set("cores.spawn." + args[1] + ".Z", z);
                            getConfig().set("cores.spawn." + args[1] + ".World", w);
                            getConfig().set("cores.spawn." + args[1] + ".Yaw", yaw);
                            getConfig().set("cores.spawn." + args[1] + ".Pitch", pitch);
                            saveConfig();

                            p.sendMessage(Main.p + "Der Spawnpunkt für §6" + args[1] + " §awurde hinzu gefügt!");

                        } else {
                            p.sendMessage(Main.p + "/cores lobby | blue | red");
                        }
                    } else if (args[0].equalsIgnoreCase("start")) {
                        if (p.hasPermission("cores.start")) {
                            if (JoinListener.xp >= 6) {
                                JoinListener.xp = 6;
                                p.sendMessage(Main.p + "Du hast das Spiel erfolgreich gestartet!");
                            }else {
                                p.sendMessage(Main.p + "§4Das Spielt startet bereits!");
                            }

                        } else {
                            p.sendMessage(Main.p + "§4Dazu hast du keine Rechte!");
                        }
                    }else if (args[0].equalsIgnoreCase("build")){
                        if (p.hasPermission("cores.build")){
                            if (build == false){
                                build = true;
                                p.sendMessage(Main.p + "Du kannst nun bauen!");
                            }else {
                                build = false;
                                p.sendMessage(Main.p + "Du kannst nun nicht mehr bauen!");
                            }
                        }else {
                            p.sendMessage(Main.p + "§4Dazu hast du keine Rechte!");
                        }
                    }else if (args[0].equalsIgnoreCase("setcore")){
                        if (args[1].equalsIgnoreCase("red") || args[1].equalsIgnoreCase("blue")){
                            int i = Integer.parseInt(args[2]);

                            if (i == 1 || i == 2){

                                Block b = p.getLocation().subtract(0.00,1.00,0.00).getBlock();

                                double x = b.getX();
                                double y = b.getY();
                                double z = b.getZ();
                                String w = p.getWorld().getName();


                                getConfig().set("cores.core." + args[1] + "." + i + ".X", x);
                                getConfig().set("cores.core." + args[1] + "." + i + ".Y", y);
                                getConfig().set("cores.core." + args[1] + "." + i + ".Z", z);
                                getConfig().set("cores.core." + args[1] + "." + i + ".World", w);
                                saveConfig();



                                p.sendMessage(Main.p + "Du hast erfolgreich den §6" + args[2] + " §aCore für das Team §6" + args[1] + " §agesetzt!");

                            }else {
                                p.sendMessage(Main.p + "Die Zahl §6 " + args[2] + " §aist kein Güliger Wert!");
                            }
                        }else {
                            p.sendMessage(Main.p + "Dieses Team existiert nicht!");
                        }


                    }else if (args[0].equalsIgnoreCase("dev")){
                        JoinListener.xp = 100000;
                        for (Player all : Bukkit.getOnlinePlayers()){
                            Bukkit.getServer().setWhitelist(true);
                            all.kickPlayer("Der Admin-Modus wurde aktiviert bitte joine neu!");
                        }

                        p.sendMessage(Main.p + "Der Admin Modus ist nun Aktiv alle Spieler wurden vom Server gekickt!");

                    }
                }
            } else {
                p.sendMessage(Main.p + "§4Dazu hast du keine Rechte!");

            }
        }else if (cmd.getName().equalsIgnoreCase("start")){

            if(args.length >= 1){
                if (args[0].equalsIgnoreCase("admin")){
                    if (p.hasPermission("cores.*")){
                        if (JoinListener.xp >= 1) {
                            JoinListener.xp = 1;
                            p.sendMessage(Main.p + "Du hast das Spiel erfolgreich gestartet!");
                        }else {
                            p.sendMessage(Main.p + "§4Das Spielt startet bereits!");
                        }
                    }else {
                        p.sendMessage(Main.p + "§4Dazu hast du keine Rechte!");
                    }


                }
            }else {
                if (p.hasPermission("cores.start")){
                    if (JoinListener.xp >= 6) {
                        JoinListener.xp = 6;
                        p.sendMessage(Main.p + "Du hast das Spiel erfolgreich gestartet!");
                    }else {
                        p.sendMessage(Main.p + "§4Das Spielt startet bereits!");
                    }
                }else {
                    p.sendMessage(Main.p + "§4Dazu hast du keine Rechte!");
                }
            }


        }
        return true;
    }


    @Override
    public void onDisable() {
        System.out.println("SkyWars off");
    }
}
