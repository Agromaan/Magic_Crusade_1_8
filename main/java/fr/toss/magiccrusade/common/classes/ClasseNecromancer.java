package fr.toss.magiccrusade.common.classes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.player.Stats;

public class ClasseNecromancer extends ClasseMana
{
	public ClasseNecromancer()
	{
		super();
		this.spells.add(EnumSpell.DRAIN);
	}
	
	@Override
	public EnumClasse get_enum_classe()
	{
		return (EnumClasse.NECROMANCER);
	}
	@Override
	public void render_energy_bar(Minecraft minecraft, int x, int y)
	{
		String	str;
		
		str = (int) this.mana + "/" + (int)this.get_default_max_energy();
    	GuiUtils.drawTexturedModalRect(x, y + 15, 0, 0, 65, 13, 0);
    	GuiUtils.drawTexturedModalRect(x, y + 15, 0, 98, (int) (65.0f / this.get_default_max_energy() * this.mana), 13, 0);
    	minecraft.fontRendererObj.drawStringWithShadow(str, x + 32 - minecraft.fontRendererObj.getStringWidth(str) / 2, y + 17, 0xffffffff);
	}

	@Override
	public Stats get_default_stats()
	{
		Stats	stats;
		
		stats = new Stats();
		stats.set_endurance(30);
		stats.set_strength(5);
		stats.set_stamina(0);
		stats.set_spirit(50);
		stats.set_clarity(25);
		stats.set_magic(0);
		stats.set_mana(0);
		return (stats);
	}

	@Override
	public Stats get_stats_per_lvl()
	{
		Stats	stats;

		stats = new Stats();
		stats.set_endurance(5);
		stats.set_strength(1);
		stats.set_stamina(0);
		stats.set_spirit(1);
		stats.set_clarity(2);
		stats.set_magic(1);
		stats.set_mana(50);
		return (stats);
	}

	@Override
	public ResourceLocation get_texture()
	{
		return (IClasse.NECROMANCER_RES);
	}
}
