package dev.warriorrr.earthmcalerts;

import static dev.warriorrr.earthmcalerts.EarthMCAlerts.config;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.text.LiteralText;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.ConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.ConfigManager;

public class ConfigUtils implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> getConfigBuilder().build();
    }

    public static void serializeConfig(ModConfig config) {
        try {
            ((ConfigManager<ModConfig>) AutoConfig.getConfigHolder(ModConfig.class)).getSerializer().serialize(config);
        } catch (ConfigSerializer.SerializationException serializeException) {
            serializeException.printStackTrace();
        }
    }

    public static ConfigBuilder getConfigBuilder() {
        ConfigBuilder configBuilder = ConfigBuilder.create().setTitle(new LiteralText("EarthMCAlerts")).setTransparentBackground(true);

        ConfigCategory main = configBuilder.getOrCreateCategory(new LiteralText("main"));
        main.addEntry(configBuilder.entryBuilder().startBooleanToggle(new LiteralText("Enable Mod"), config.enabled)
            .setDefaultValue(true)
            .setSaveConsumer(val -> config.enabled = val)
            .build());

        main.addEntry(configBuilder.entryBuilder().startBooleanToggle(new LiteralText("Enable Overfishing Sound"), config.enableOverfishingSound)
            .setDefaultValue(true)
            .setSaveConsumer(val -> config.enableOverfishingSound = val)
            .build());

        main.addEntry(configBuilder.entryBuilder().startBooleanToggle(new LiteralText("Enable AFK Sound"), config.enableAfkSound)
            .setDefaultValue(true)
            .setSaveConsumer(val -> config.enableAfkSound = val)
            .build());

        configBuilder.setSavingRunnable(() -> serializeConfig(config));

        return configBuilder;
    }
}