package net.devtech.classicombat.mixin.defense;

import net.minecraft.entity.DamageUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DamageUtil.class)
public class DamageUtilMixin {
	@Redirect (method = "getDamageLeft", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(FFF)F"))
	private static float damage(float value, float min, float max) {
		return min/.2f;
	}
}
