package moe.wolfgirl.kubejs_roots.recipes;

import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.type.TypeInfo;
import elucent.rootsclassic.component.ComponentBase;

public class ComponentComponent implements RecipeComponent<String> {
    public static final RecipeComponent<String> COMPONENT = new ComponentComponent();

    @Override
    public String toString() {
        return "kubejs_roots:component";
    }

    @Override
    public Codec<String> codec() {
        return Codec.STRING;
    }

    @Override
    public String wrap(Context cx, KubeRecipe recipe, Object from) {
        return String.valueOf(from);
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(ComponentBase.class);
    }
}
