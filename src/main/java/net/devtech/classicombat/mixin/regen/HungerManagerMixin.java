package net.devtech.classicombat.mixin.regen;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import net.minecraft.entity.player.HungerManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
	@Redirect(method = "update", at =@At(value = "FIELD", target = "Lnet/minecraft/entity/player/HungerManager;foodSaturationLevel:F", opcode = Opcodes.GETFIELD))
	private float min(HungerManager manager) {
		return 0;
	}

	@ModifyConstant(method = "update", constant = @Constant(floatValue = 1.0F), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;canFoodHeal()Z", ordinal = 1), to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;addExhaustion(F)V", ordinal = 1)))
	private float update(float original) {
		return 1.3f;
	}
}
