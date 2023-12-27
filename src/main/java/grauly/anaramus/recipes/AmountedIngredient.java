package grauly.anaramus.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.dynamic.Codecs;

import java.util.ArrayList;
import java.util.List;

public record AmountedIngredient(Ingredient ingredient, int amount) {
    public static final Codec<AmountedIngredient> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(AmountedIngredient::ingredient),
            Codecs.POSITIVE_INT.fieldOf("amount").forGetter(AmountedIngredient::amount)
    ).apply(instance, AmountedIngredient::new));

    public static AmountedIngredient of(Item item, int amount) {
        return new AmountedIngredient(Ingredient.ofItems(item), amount);
    }

    public static AmountedIngredient of(Item item) {
        return new AmountedIngredient(Ingredient.ofItems(item), 1);
    }

    public static List<AmountedIngredient> ofMany(int amount, Item... items) {
        ArrayList<AmountedIngredient> list = new ArrayList<>();
        for (Item item : items) {
            list.add(new AmountedIngredient(Ingredient.ofItems(item), amount));
        }
        return list;
    }

    public static List<AmountedIngredient> ofMany(Item... items) {
        return ofMany(1, items);
    }

    public static List<AmountedIngredient> concat(Object... ingredients) {
        ArrayList<AmountedIngredient> list = new ArrayList<>();
        for (Object ingredient : ingredients) {
            if (ingredient instanceof AmountedIngredient innerIngredient) {
                list.add(innerIngredient);
            } else if (ingredient instanceof List innerList) {
                list.addAll(concat(innerList));
            } else {
                throw new IllegalArgumentException("AmountedIngredient#concat can only take AmountedIngredient or List<AmountedIngredient>, not " + ingredient.getClass().getName());
            }
        }
        return list;
    }

}
