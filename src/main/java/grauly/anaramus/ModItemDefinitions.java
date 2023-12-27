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
}
