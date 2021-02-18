package dev.warriorrr.earthmcalerts;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ChatUtil;

public class EarthMCAlerts implements ModInitializer {
    public static ModConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    public static boolean isOverfishingMessage(String string) {
        return string.matches("You sense that there might not be many fish left in this area. Try fishing at least 3 blocks away.");
    }

    public static boolean isAfk(String string) {
        return string.matches("You are now AFK.");
    }

    public static void handleChat(GameMessageS2CPacket packet) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (isOverfishingMessage(ChatUtil.stripTextFormat(packet.getMessage().getString())) && config.enableOverfishingSound) {
            for (int i = 0; i < 3; i++)
                client.player.playSound(SoundEvents.ITEM_BUCKET_EMPTY_FISH, 100.0F, 1.0F);
        } else if (isAfk(ChatUtil.stripTextFormat(packet.getMessage().getString())) && config.enableAfkSound) {
            for (int i = 0; i < 3; i++)
                client.player.playSound(SoundEvents.BLOCK_BELL_USE, 1.100F, 1.0F);
        }
    }
}