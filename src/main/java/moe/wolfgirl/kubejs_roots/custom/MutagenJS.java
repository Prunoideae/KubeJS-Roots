package moe.wolfgirl.kubejs_roots.custom;

import dev.latvian.mods.kubejs.level.BlockContainerJS;
import elucent.rootsclassic.mutation.MutagenRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class MutagenJS extends MutagenRecipe {
    private final Builder builder;

    public MutagenJS(ResourceLocation name, BlockState state, BlockState resultState, Builder builder) {
        super(name, state, resultState);
        this.builder = builder;

        for (ItemStack input : this.builder.inputs) {
            this.addIngredient(input);
        }
    }

    @Override
    public void onCrafted(Level levelAccessor, BlockPos pos, Player player) {
        builder.onCrafted.accept(levelAccessor.kjs$getBlock(pos), player);
    }

    @Override
    public boolean matches(List<ItemStack> items, Level levelAccessor, BlockPos pos, Player player) {
        if (!super.matches(items, levelAccessor, pos, player)) {
            return false;
        } else {
            return builder.matches.matches(items, levelAccessor.kjs$getBlock(pos), player);
        }
    }

    @FunctionalInterface
    public interface MutagenMatches {
        boolean matches(List<ItemStack> items, BlockContainerJS block, Player player);
    }

    public static class Builder {
        private final List<ItemStack> inputs = new ArrayList<>();
        private BiConsumer<BlockContainerJS, Player> onCrafted = (b, p) -> {
        };
        private MutagenMatches matches = (i, b, p) -> true;

        public Builder inputs(ItemStack... inputs) {
            this.inputs.addAll(Arrays.asList(inputs));
            return this;
        }

        public Builder onCrafted(BiConsumer<BlockContainerJS, Player> onCrafted) {
            this.onCrafted = onCrafted;
            return this;
        }

        public Builder matches(MutagenMatches matches) {
            this.matches = matches;
            return this;
        }
    }
}
