package fr.toss.magiccrusade.common.classes.spell.champion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;

public class SpellEarthShield implements ISpell
{

	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.EARTH_SHIELD);
	}

	@Override
	public void animate(Entity caster, Entity target)
	{
		
	}

	@Override
	public void do_spell(Entity caster, Entity target)
	{
		EntityLivingBase	entity;
		
		entity = (EntityLivingBase)caster;
		entity.addPotionEffect(new PotionEffect(Potion.absorption.id, 40, 9));
	}

	@Override
	public int get_target_id(ClientPlayer client)
	{
		return (-1);
	}

}
