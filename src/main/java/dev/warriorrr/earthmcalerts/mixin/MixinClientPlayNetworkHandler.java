package dev.warriorrr.earthmcalerts.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.warriorrr.earthmcalerts.EarthMCAlerts;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {

	@Shadow private MinecraftClient client;

	@Inject(method = "onGameMessage", at = @At("HEAD"))
	public void onChatMessage(GameMessageS2CPacket packet, CallbackInfo info) {
		if (client.isOnThread()) {
			EarthMCAlerts.handleChat(packet);
		}
	}
}
