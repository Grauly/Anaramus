package grauly.anaramus;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItemDefinitions {

    public static ItemStack getBadOmenPotion(Item item) {
        var stack = new ItemStack(item);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.BAD_OMEN, 20 * 60 * 20),
                        new StatusEffectInstance(StatusEffects.NAUSEA, 30 * 20),
                        new StatusEffectInstance(StatusEffects.WEAKNESS, 2 * 60 * 20)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.bad_omen").setStyle(textStyle);
        stack.setCustomName(nameText);
        stack.getOrCreateNbt().putString(PotionUtil.POTION_KEY, Registries.POTION.getId(Potions.WEAKNESS).toString());
        return stack;
    }

    public static ItemStack getMinersFervorPotion(Item item) {
        var stack = new ItemStack(item);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.HASTE, 20 * 60 * 20, 4),
                        new StatusEffectInstance(StatusEffects.SPEED, 20 * 60 * 20),
                        new StatusEffectInstance(StatusEffects.LUCK, 2 * 60 * 20)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.miners_fervor").setStyle(textStyle);
        stack.setCustomName(nameText);
        stack.getOrCreateNbt().putString(PotionUtil.POTION_KEY, Registries.POTION.getId(Potions.TURTLE_MASTER).toString());
        return stack;
    }

    public static ItemStack getGreaterPoisonPotion(Item item) {
        var stack = new ItemStack(item);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.POISON, 60 * 20, 5),
                        new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 4),
                        new StatusEffectInstance(StatusEffects.WITHER, 30 * 20)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.greater_poison").setStyle(textStyle);
        stack.setCustomName(nameText);
        stack.getOrCreateNbt().putString(PotionUtil.POTION_KEY, Registries.POTION.getId(Potions.POISON).toString());
        return stack;
    }

    public static ItemStack getFelixFelicitas(Item item) {
        var stack = new ItemStack(item);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.LUCK, 20 * 60 * 20, 100)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.felix_felicitas").setStyle(textStyle);
        stack.setCustomName(nameText);
        stack.getOrCreateNbt().putString(PotionUtil.POTION_KEY, Registries.POTION.getId(Potions.LUCK).toString());
        return stack;
    }

    public static ItemStack getLuckPotion(Item item, int i) {
        var stack = new ItemStack(item);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.LUCK, 8 * 60 * 20, i)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.luck" + i).setStyle(textStyle);
        stack.setCustomName(nameText);
        stack.getOrCreateNbt().putString(PotionUtil.POTION_KEY, Registries.POTION.getId(Potions.LUCK).toString());
        return stack;
    }

    public static ItemStack getBlindExertionPotion(Item item) {
        var stack = new ItemStack(item);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.SPEED, 8 * 60 * 20, 6),
                        new StatusEffectInstance(StatusEffects.JUMP_BOOST, 8 * 60 * 20, 3),
                        new StatusEffectInstance(StatusEffects.DARKNESS, 8 * 60 * 20, 1),
                        new StatusEffectInstance(StatusEffects.NAUSEA, 60 * 20, 1)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.blind_exertion").setStyle(textStyle);
        stack.setCustomName(nameText);
        stack.getOrCreateNbt().putString(PotionUtil.POTION_KEY, Registries.POTION.getId(Potions.NIGHT_VISION).toString());
        return stack;
    }

    public static ItemStack getBerserkersRagePotion(Item item) {
        var stack = new ItemStack(item);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.STRENGTH, 4 * 60 * 20, 4),
                        new StatusEffectInstance(StatusEffects.SPEED, 2 * 60 * 20, 2),
                        new StatusEffectInstance(StatusEffects.HASTE, 3 * 60 * 20, 2),
                        new StatusEffectInstance(StatusEffects.DARKNESS, 4 * 60 * 20, 1),
                        new StatusEffectInstance(StatusEffects.WITHER, 30 * 20, 1)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.berserkers_rage").setStyle(textStyle);
        stack.setCustomName(nameText);
        stack.getOrCreateNbt().putString(PotionUtil.POTION_KEY, Registries.POTION.getId(Potions.STRENGTH).toString());
        return stack;
    }

    public static ItemStack getNeptunesWakePotion(Item item) {
        var stack = new ItemStack(item);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                new StatusEffectInstance(StatusEffects.WATER_BREATHING, 8 * 60 * 20, 1),
                new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 8 * 60 * 20, 1),
                new StatusEffectInstance(StatusEffects.NIGHT_VISION, 8 * 60 * 20, 1)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.neptunes_wake").setStyle(textStyle);
        stack.setCustomName(nameText);
        stack.getOrCreateNbt().putString(PotionUtil.POTION_KEY, Registries.POTION.getId(Potions.WATER_BREATHING).toString());
        return stack;
    }

    public static ItemStack getParalysisPotion(Item item) {
        var stack = new ItemStack(item);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.SLOWNESS, 2 * 60 * 20, 10)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.paralysis").setStyle(textStyle);
        stack.setCustomName(nameText);
        stack.getOrCreateNbt().putString(PotionUtil.POTION_KEY, Registries.POTION.getId(Potions.SLOWNESS).toString());
        return stack;
    }


}
