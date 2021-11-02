package com.github.discordbotdev2.protcolus.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.github.discordbotdev2.protcolus.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;


public class boomCommand implements CommandExecutor {
    private Main plugin;
    public boomCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("boom").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ProtocolManager manager = ProtocolLibrary.getProtocolManager();

            player.getLineOfSight(null,50).stream()
                    .filter(block -> block.getType() != Material.AIR)
                    .forEach(block -> {

                        Location blockLocation = block.getLocation();

                        PacketContainer packet = manager.createPacket(PacketType.Play.Server.EXPLOSION);
                        packet.getDoubles().write(0, blockLocation.getX());
                        packet.getDoubles().write(1,blockLocation.getY());
                        packet.getDoubles().write(2,blockLocation.getZ());
                        packet.getFloat().write(0,3.0f);

                        try {
                            manager.sendServerPacket(player, packet);
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }

                    });
        }
        return true;
    }
}
