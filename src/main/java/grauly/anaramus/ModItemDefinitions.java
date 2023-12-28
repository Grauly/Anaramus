package grauly.anaramus;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModItemDefinitions {

    public static ItemStack getBadOmenPotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.BAD_OMEN, 20 * 60 * 20),
                        new StatusEffectInstance(StatusEffects.NAUSEA, 30 * 20),
                        new StatusEffectInstance(StatusEffects.WEAKNESS, 2 * 60 * 20)
                ),
                new Color(0, 0, 0),
                Text.translatable("potions.anaramus.bad_omen")
        );
    }

    public static ItemStack getMinersFervorPotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.HASTE, 20 * 60 * 20, 4),
                        new StatusEffectInstance(StatusEffects.SPEED, 20 * 60 * 20),
                        new StatusEffectInstance(StatusEffects.LUCK, 2 * 60 * 20)
                ),
                new Color(191, 255, 0),
                Text.translatable("potions.anaramus.miners_fervor")
        );
    }

    public static ItemStack getGreaterPoisonPotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.POISON, 60 * 20, 5),
                        new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 4),
                        new StatusEffectInstance(StatusEffects.WITHER, 30 * 20)
                ),
                new Color(196, 255, 77),
                Text.translatable("potions.anaramus.greater_poison")
        );
    }

    public static ItemStack getFelixFelicitas(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.LUCK, 20 * 60 * 20, 100)
                ),
                new Color(0, 153, 51),
                Text.translatable("potions.anaramus.felix_felicitas")
        );
    }

    public static ItemStack getLuckPotion(Item item, int i) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.LUCK, 8 * 60 * 20, i)
                ),
                new Color(51, 153, 51),
                Text.translatable("potions.anaramus.luck" + i)
        );
    }

    public static ItemStack getBlindExertionPotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.SPEED, 8 * 60 * 20, 6),
                        new StatusEffectInstance(StatusEffects.JUMP_BOOST, 8 * 60 * 20, 3),
                        new StatusEffectInstance(StatusEffects.DARKNESS, 8 * 60 * 20, 1),
                        new StatusEffectInstance(StatusEffects.NAUSEA, 60 * 20, 1)
                ),
                new Color(153, 102, 0),
                Text.translatable("potions.anaramus.blind_exertion")
        );
    }

    public static ItemStack getBerserkersRagePotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.STRENGTH, 4 * 60 * 20, 4),
                        new StatusEffectInstance(StatusEffects.SPEED, 2 * 60 * 20, 2),
                        new StatusEffectInstance(StatusEffects.HASTE, 3 * 60 * 20, 2),
                        new StatusEffectInstance(StatusEffects.DARKNESS, 4 * 60 * 20, 1),
                        new StatusEffectInstance(StatusEffects.WITHER, 30 * 20, 1)
                ),
                new Color(179, 36, 0),
                Text.translatable("potions.anaramus.berserkers_rage")
        );
    }

    public static ItemStack getNeptunesWakePotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.WATER_BREATHING, 8 * 60 * 20, 1),
                        new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 8 * 60 * 20, 1),
                        new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 8 * 60 * 20, 1),
                        new StatusEffectInstance(StatusEffects.NIGHT_VISION, 8 * 60 * 20, 1)
                ),
                new Color(153, 153, 255),
                Text.translatable("potions.anaramus.neptunes_wake")
        );

    }

    public static ItemStack getParalysisPotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.SLOWNESS, 2 * 60 * 20, 10)
                ),
                new Color(102, 153, 153),
                Text.translatable("potions.anaramus.paralysis")
        );
    }

    public static ItemStack getVitalizationPotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 10),
                        new StatusEffectInstance(StatusEffects.REGENERATION, 30 * 20, 10)
                ),
                new Color(255, 64, 0),
                Text.translatable("potions.anaramus.vitalization")
        );
    }

    public static ItemStack getGreaterHealingPotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 4)
                ),
                new Color(255, 0, 102),
                Text.translatable("potions.anaramus.greater_healing")
        );
    }

    public static ItemStack getFloatationPotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.LEVITATION, 30 * 20, 1),
                        new StatusEffectInstance(StatusEffects.SLOW_FALLING, 60 * 20, 1)
                ),
                new Color(255, 255, 204),
                Text.translatable("potions.anaramus.floatation")
        );
    }

    public static ItemStack getStoneSkinPotion(Item item) {
        return constructPotion(item,
                List.of(
                        new StatusEffectInstance(StatusEffects.RESISTANCE, 8 * 60 * 20, 4),
                        new StatusEffectInstance(StatusEffects.SLOWNESS, 8 * 60 * 20, 1)
                ),
                new Color(61, 61, 41),
                Text.translatable("potions.anaramus.stoneskin")
        );
    }

    public static ItemStack constructPotion(Item potionVariant, List<StatusEffectInstance> effects, Color color, MutableText name) {
        var stack = new ItemStack(potionVariant);
        PotionUtil.setCustomPotionEffects(stack, effects);
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = name.setStyle(textStyle);
        stack.setCustomName(nameText);
        stack.getOrCreateNbt().putInt(PotionUtil.CUSTOM_POTION_COLOR_KEY, color.getRGB());
        stack.addHideFlag(ItemStack.TooltipSection.ADDITIONAL);
        List<Text> effectDescription = new ArrayList<>();
        PotionUtil.buildTooltip(stack, effectDescription, 1);
        NbtList lore = new NbtList();
        effectDescription.forEach(t -> {
            var at = t.copy();
            at.setStyle(at.getStyle().withItalic(false));
            lore.add(NbtString.of(Text.Serializer.toJson(at)));
        });
        stack.getOrCreateNbt().getCompound(ItemStack.DISPLAY_KEY).put(ItemStack.LORE_KEY, lore);
        return stack;
    }

}
