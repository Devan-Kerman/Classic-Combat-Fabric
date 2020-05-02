package net.devtech.classicombat.mixin.fixdamage;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SwordItem.class)
public class SwordItemMixin {
	@Mutable @Shadow @Final private float attackDamage;

	@Inject(method = "<init>", at = @At("RETURN"))
	private void init(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings, CallbackInfo ci) {
		this.attackDamage = 4 + material.getAttackDamage();
	}
}
