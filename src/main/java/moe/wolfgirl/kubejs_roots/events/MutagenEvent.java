package moe.wolfgirl.kubejs_roots.events;

import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.kubejs.typings.Info;
import elucent.rootsclassic.mutation.MutagenManager;
import moe.wolfgirl.kubejs_roots.custom.MutagenJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class MutagenEvent implements KubeEvent {

    @Info("Removes mutagen recipes registered by Roots or other addons.")
    public void clear() {
        MutagenManager.recipes.clear();
    }

    public void add(ResourceLocation id, BlockState output, BlockState input, Consumer<MutagenJS.Builder> builder) {
        MutagenJS.Builder b = new MutagenJS.Builder();
        builder.accept(b);
        MutagenManager.recipes.add(new MutagenJS(id, input, output, b));
    }
}
