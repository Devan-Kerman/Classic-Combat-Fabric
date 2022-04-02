package net.devtech.classicombat.mixin.fixdamage;

import net.minecraft.item.*;
import net.minecraft.tag.TagKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin {
	@Mutable @Shadow @Final private float attackDamage;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void init(float attackDamage, float attackSpeed, ToolMaterial material, TagKey effectiveBlocks, Item.Settings settings, CallbackInfo ci) {
		if((Object)this instanceof AxeItem) {
			this.attackDamage = 3 + material.getAttackDamage();
		} else if((Object)this instanceof PickaxeItem) {
			this.attackDamage = 2 + material.getAttackDamage();
		} else if((Object)this instanceof ShovelItem) {
			this.attackDamage = 1 + material.getAttackDamage();
		}
	}
}
