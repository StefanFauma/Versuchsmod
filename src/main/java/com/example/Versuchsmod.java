package com.example;

import com.mojang.brigadier.Command;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import javax.swing.text.html.parser.Entity;
import java.util.concurrent.TimeUnit;

public class Versuchsmod implements ModInitializer
{
	public static final String MOD_ID = "versuchsmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ServerLivingEntityEvents.ALLOW_DAMAGE.register((livingEntity, damageSource, v) -> {
			if (damageSource.isOf(DamageTypes.FALL) && livingEntity instanceof ServerPlayerEntity player) {
				player.changeGameMode(GameMode.SPECTATOR);
				player.getServer().getCommandManager().executeWithPrefix(
						player.getServer().getCommandSource(),
						"title @a title {\"text\": \"You died!\", \"color\": \"red\"}"
				);
			}
			return true;
		});

	}
}