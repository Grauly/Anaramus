package grauly.anaramus;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Block> HEAT_SOURCES = TagKey.of(RegistryKeys.BLOCK,new Identifier(Anaramus.MODID,"heatsources"));
    public static final TagKey<Item> CAULDRON_BLACKLIST = TagKey.of(RegistryKeys.ITEM,new Identifier(Anaramus.MODID, "cauldron_blacklist"));
    public static final TagKey<Item> ALIVE_CORALS = TagKey.of(RegistryKeys.ITEM,new Identifier(Anaramus.MODID,"alive_corals"));
}
