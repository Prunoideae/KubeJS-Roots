package moe.wolfgirl.kubejs_roots.recipes;

import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.nbt.CompoundTag;

public class TagCompoundComponent implements RecipeComponent<CompoundTag> {
    public static final RecipeComponent<CompoundTag> COMPOUND_TAG = new TagCompoundComponent();

    @Override
    public String toString() {
        return "kubejs_roots:tag_compound";
    }

    @Override
    public Codec<CompoundTag> codec() {
        return CompoundTag.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(CompoundTag.class);
    }
}
