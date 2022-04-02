package net.devtech.classicombat.mixin.test;

import com.google.common.collect.Ordering;
import net.minecraft.client.gui.hud.PlayerListHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(PlayerListHud.class)
public class PlayerListHudMixin {
	@Redirect (method = "render", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Ordering;sortedCopy(Ljava/lang/Iterable;)Ljava/util/List;"), remap = false)
	private <E> List<E> shuffle(Ordering ordering, Iterable<E> elements) {
		List<E> el = new ArrayList<>();
		for (E element : elements) {
			el.add(element);
		}
		Collections.shuffle(el);
		return el;
	}
}
