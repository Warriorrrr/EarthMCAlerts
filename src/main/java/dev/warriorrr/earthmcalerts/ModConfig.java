package dev.warriorrr.earthmcalerts;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "EarthMCEssentials")
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public boolean enabled = true;
    public boolean enableOverfishingSound = true;
    public boolean enableAfkSound = true;
}
