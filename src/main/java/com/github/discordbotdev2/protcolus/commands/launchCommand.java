package com.github.discordbotdev2.protcolus.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.github.discordbotdev2.protcolus.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class launchCommand implements CommandExecutor {
    private Main plugin;
    public launchCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("launch").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ProtocolManager manager = ProtocolLibrary.getProtocolManager();
            Location playerLoc = player.getLocation();
            PacketContainer packet = manager.createPacket(PacketType.Play.Server.EXPLOSION);
            packet.getDoubles().write(0, playerLoc.getX());
            packet.getDoubles().write(1,playerLoc.getY());
            packet.getDoubles().write(2,playerLoc.getZ());
            packet.getFloat().write(0,0.0f);
            packet.getDoubles().write(3, 5.0);
        }


        return true;
    }
}
