package net.devtech.classicombat;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class ClassiCombatMixinPlugin implements IMixinConfigPlugin {
	private static final Logger LOGGER = Logger.getLogger("ClassiCombat");
	private final Properties properties = new Properties();

	@Override
	public void onLoad(String mixinPackage) {
		this.properties.setProperty("fixdamage", "true");
		this.properties.setProperty("spamclick", "true");
		this.properties.setProperty("regen", "true");
		this.properties.setProperty("blocking", "true");
		this.properties.setProperty("defense", "true");
		this.properties.setProperty("render", "true");
		File file = new File("config", "classicombat.properties");
		if(file.exists()) {
			try {
				this.properties.load(new FileReader(file));
			} catch (IOException e) {
				LOGGER.severe("Unable to read config!");
				e.printStackTrace();
			}
		} else {
			file.getParentFile().mkdirs();
			try {
				FileWriter writer = new FileWriter(file);
				this.properties.store(writer, "classiconfig");
			} catch (IOException e) {
				LOGGER.severe("Unable to write to config!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getRefMapperConfig() {return null;}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		int clsIndex = mixinClassName.lastIndexOf('.');
		boolean enabled = "true".equals(this.properties.get(mixinClassName.substring(31, clsIndex)));
		if(enabled) System.out.println("applying: "+mixinClassName);
		return enabled;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

	@Override
	public List<String> getMixins() {return null;}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
