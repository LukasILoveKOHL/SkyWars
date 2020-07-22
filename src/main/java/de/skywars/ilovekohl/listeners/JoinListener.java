package de.skywars.ilovekohl.listeners;

import de.skywars.ilovekohl.main.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class JoinListener implements Listener {
    public JoinListener(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    public static int xp;
    public static int sched;
    public static int allplayer;

    public static void count() {
        xp = 31;
        allplayer = Bukkit.getOnlinePlayers().size();
        for (Player all : Bukkit.getOnlinePlayers()) {
            System.out.println(allplayer);
            if (allplayer >= 1) {
                sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                    public void run() {

                        xp--;
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.setLevel(xp);
                        }

                        if (xp == 30) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.sendMessage(Main.p + "Das Spiel startet in §6" + xp + " §aSekunden");

                            }
                        } else if (xp == 10) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.sendMessage(Main.p + "Das Spiel startet in §6" + xp + " §aSekunden");

                            }
                        } else if (xp == 5) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.sendMessage(Main.p + "Das Spiel startet in §6" + xp + " §aSekunden");

                            }
                        } else if (xp == 4) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.sendMessage(Main.p + "Das Spiel startet in §6" + xp + " §aSekunden");
                            }
                        } else if (xp == 3) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.sendMessage(Main.p + "Das Spiel startet in §6" + xp + " §aSekunden");
                            }
                        } else if (xp == 2) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.sendMessage(Main.p + "Das Spiel startet in §6" + xp + " §aSekunden");
                            }
                        } else if (xp == 1) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.getInventory().clear();
                                all.sendMessage(Main.p + "Das Spiel startet in §6" + xp + " §aSekunden");
                            }
                        }

                        if (xp == 0) {
                            if (Bukkit.getOnlinePlayers().size() >= 1) {

                                //Spiel Starten
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    if (TeamChoose.teams.get(all.getName()).equals("red")) {

                                        double x = Main.instance.getConfig().getDouble("cores.spawn.red.X");
                                        double y = Main.instance.getConfig().getDouble("cores.spawn.red.Y");
                                        double z = Main.instance.getConfig().getDouble("cores.spawn.red.Z");
                                        String w = Main.instance.getConfig().getString("cores.spawn.red.World");
                                        float yaw = (float) Main.instance.getConfig().getDouble("cores.spawn.red.Yaw");
                                        float pitch = (float) Main.instance.getConfig().getDouble("cores.spawn.red.Pitch");
                                        World world = Bukkit.getWorld(w);
                                        Location loc = new Location(world, x, y, z, yaw, pitch);
                                        all.teleport(loc);
                                        TeamChoose.teamred.add(all);

                                    } else {
                                        double x = (double) Main.getInstance().getConfig().getDouble("cores.spawn.blue.X");
                                        double y = (double) Main.getInstance().getConfig().getDouble("cores.spawn.blue.Y");
                                        double z = (double) Main.getInstance().getConfig().getDouble("cores.spawn.blue.Z");
                                        String w = (String) Main.getInstance().getConfig().getString("cores.spawn.blue.World");
                                        float yaw = (float) Main.getInstance().getConfig().getDouble("cores.spawn.blue.Yaw");
                                        float pitch = (float) Main.getInstance().getConfig().getDouble("cores.spawn.blue.Pitch");
                                        World world = Bukkit.getWorld(w);
                                        Location loc = new Location(world, x, y, z, yaw, pitch);
                                        all.teleport(loc);
                                        TeamChoose.teamblue.add(all);
                                    }

                                    all.getInventory().clear();

                                    all.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD));
                                    all.getInventory().setItem(1, new ItemStack(Material.STONE_PICKAXE));
                                    all.getInventory().setItem(2, new ItemStack(Material.BOW));
                                    all.getInventory().setItem(3, new ItemStack(Material.STONE_AXE));
                                    all.getInventory().setItem(4, new ItemStack(Material.WOOD, 64));
                                    all.getInventory().setItem(8, new ItemStack(Material.GOLDEN_APPLE, 16));
                                    all.getInventory().setItem(22, new ItemStack(Material.ARROW, 12));


                                    if (TeamChoose.teams.get(all.getName()).equals("red")) {

                                        all.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                                        all.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                                        all.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                                        all.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));

                                    } else {
                                        all.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                                        all.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                                        all.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                                        all.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
                                    }

                                    damagecancelled = false;
                                    damageblockcancelled = false;
                                    respawn = false;
                                    gamestart = false;


                                    all.setHealth(20);
                                    all.setFoodLevel(20);

                                    Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                                        @Override
                                        public void run() {

                                            for (Player all : Bukkit.getOnlinePlayers()) {

                                                Location PlayerLocation = all.getLocation();

                                                if (TeamChoose.teams.get(all.getName()).equalsIgnoreCase("red")) {

                                                    double x = Main.getInstance().getConfig().getDouble("cores.core." + "blue" + "." + 1 + ".X");
                                                    double y = Main.getInstance().getConfig().getDouble("cores.core." + "blue" + "." + 1 + ".Y");
                                                    double z = Main.getInstance().getConfig().getDouble("cores.core." + "blue" + "." + 1 + ".Z");
                                                    String w = Main.getInstance().getConfig().getString("cores.core." + "blue" + "." + 1 + ".World");
                                                    World world = Bukkit.getWorld(w);
                                                    Location coreB1 = new Location(world, x, y, z);

                                                    double x2 = Main.getInstance().getConfig().getDouble("cores.core." + "blue" + "." + 1 + ".X");
                                                    double y2 = Main.getInstance().getConfig().getDouble("cores.core." + "blue" + "." + 1 + ".Y");
                                                    double z2 = Main.getInstance().getConfig().getDouble("cores.core." + "blue" + "." + 1 + ".Z");
                                                    String w2 = Main.getInstance().getConfig().getString("cores.core." + "blue" + "." + 1 + ".World");
                                                    World world2 = Bukkit.getWorld(w2);
                                                    Location coreB2 = new Location(world2, x2, y2, z2);

                                                    if (PlayerLocation.distance(coreB1) <= 5 && Main.B1isdestroyed == false) {
                                                        Main.B1isaLive = false;
                                                        Main.B1isattacked = true;
                                                        for (Player blue : TeamChoose.teamblue) {
                                                            blue.playSound(blue.getLocation(), Sound.ENDERDRAGON_WINGS, 15, 15);
                                                            if (bi < 10) {
                                                                bi++;
                                                                if (bi == 1) {
                                                                    blue.sendMessage(Main.p + "Ein Spieler nähert sich deinem Core!");
                                                                }

                                                            }
                                                        }
                                                    } else {
                                                        Main.B1isaLive = true;
                                                        Main.B1isattacked = false;
                                                    }
                                                    if (PlayerLocation.distance(coreB2) <= 5 && Main.B2isdestroyed == false) {
                                                        Main.B2isaLive = false;
                                                        Main.B2isattacked = true;
                                                        for (Player blue : TeamChoose.teamblue) {
                                                            blue.playSound(blue.getLocation(), Sound.ENDERDRAGON_WINGS, 15, 15);
                                                            if (bi < 10) {
                                                                bi++;
                                                                if (bi == 1) {
                                                                    blue.sendMessage(Main.p + "Ein Spieler nähert sich deinem Core!");
                                                                }

                                                            }
                                                        }
                                                    } else {
                                                        Main.B2isaLive = true;
                                                        Main.B2isattacked = false;
                                                    }


                                                } else {


                                                    double x = Main.getInstance().getConfig().getDouble("cores.core." + "red" + "." + 1 + ".X");
                                                    double y = Main.getInstance().getConfig().getDouble("cores.core." + "red" + "." + 1 + ".Y");
                                                    double z = Main.getInstance().getConfig().getDouble("cores.core." + "red" + "." + 1 + ".Z");
                                                    String w = Main.getInstance().getConfig().getString("cores.core." + "red" + "." + 1 + ".World");
                                                    World world = Bukkit.getWorld(w);
                                                    Location coreR1 = new Location(world, x, y, z);

                                                    double x2 = Main.getInstance().getConfig().getDouble("cores.core." + "red" + "." + 2 + ".X");
                                                    double y2 = Main.getInstance().getConfig().getDouble("cores.core." + "red" + "." + 2 + ".Y");
                                                    double z2 = Main.getInstance().getConfig().getDouble("cores.core." + "red" + "." + 2 + ".Z");
                                                    String w2 = Main.getInstance().getConfig().getString("cores.core." + "red" + "." + 2 + ".World");
                                                    World world2 = Bukkit.getWorld(w2);
                                                    Location coreR2 = new Location(world2, x2, y2, z2);

                                                    if (PlayerLocation.distance(coreR1) <= 5 && Main.R1isdestroyed == true) {
                                                        Main.R1isaLive = false;
                                                        Main.R1isattacked = true;
                                                        for (Player red : TeamChoose.teamred) {
                                                            red.playSound(red.getLocation(), Sound.ENDERDRAGON_WINGS, 15, 15);
                                                            if (ri < 10) {
                                                                ri++;
                                                                if (ri == 1) {
                                                                    red.sendMessage(Main.p + "Ein Spieler nähert sich deinem Core!");
                                                                }

                                                            }
                                                        }
                                                    } else {
                                                        Main.R1isaLive = true;
                                                        Main.R1isattacked = false;
                                                    }
                                                    if (PlayerLocation.distance(coreR2) <= 5 && Main.R2isdestroyed == true) {
                                                        Main.R2isaLive = false;
                                                        Main.R2isattacked = true;
                                                        for (Player red : TeamChoose.teamred) {
                                                            red.playSound(red.getLocation(), Sound.ENDERDRAGON_WINGS, 15, 15);
                                                            if (ri < 10) {
                                                                ri++;
                                                                if (ri == 1) {
                                                                    red.sendMessage(Main.p + "Ein Spieler nähert sich deinem Core!");
                                                                }

                                                            }
                                                        }
                                                    } else {
                                                        Main.R2isaLive = true;
                                                        Main.R2isattacked = false;
                                                    }

                                                }


                                            }

                                        }
                                    }, 0, 40);


                                }

                            } else {
                                Bukkit.getScheduler().cancelTask(sched);
                                Bukkit.getScheduler().cancelTasks(Main.instance);
                                count();
                            }
                        }

                    }
                }, 0, 20);
            } else {
                Bukkit.getScheduler().cancelTask(sched);
                Bukkit.getScheduler().cancelTasks(Main.instance);
            }
        }
    }

}
