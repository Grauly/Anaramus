package grauly.anaramus;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.List;

public class ModItemDefinitions {

    public static ItemStack getBadOmenPotion() {
        var stack = new ItemStack(Items.POTION);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                new StatusEffectInstance(StatusEffects.BAD_OMEN, 20 * 60 * 20),
                new StatusEffectInstance(StatusEffects.NAUSEA, 30*20),
                new StatusEffectInstance(StatusEffects.WEAKNESS, 2*60*20)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.bad_omen").setStyle(textStyle);
        stack.setCustomName(nameText);
        return stack;
    }

    public static ItemStack getMinersFervorPotion() {
        var stack = new ItemStack(Items.POTION);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.HASTE, 20 * 60 * 20,4),
                        new StatusEffectInstance(StatusEffects.SPEED, 20*60*20),
                        new StatusEffectInstance(StatusEffects.LUCK, 2*60*20)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.miners_fervor").setStyle(textStyle);
        stack.setCustomName(nameText);
        return stack;
    }

    public static ItemStack getGreaterPoisonPotion() {
        var stack = new ItemStack(Items.POTION);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.POISON, 60* 20,5),
                        new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1,4),
                        new StatusEffectInstance(StatusEffects.WITHER, 30*20)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.greater_poison").setStyle(textStyle);
        stack.setCustomName(nameText);
        return stack;
    }

    public static ItemStack getFelixFelicitas() {
        var stack = new ItemStack(Items.POTION);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.LUCK, 20*60*20,100)
                )
        );
        var textStyle = Style.EMPTY.withItalic(false);
        var nameText = Text.translatable("potions.anaramus.felix_felicitas").setStyle(textStyle);
        stack.setCustomName(nameText);
        return stack;
    }

    public static ItemStack getLuckPotion(int i) {
        var stack = new ItemStack(Items.POTION);
        PotionUtil.setCustomPotionEffects(stack, List.of(
                        new StatusEffectInstance(StatusEffects.LUCK, 8*60*20,i)
                )
        );
        return stack;
    }
}
