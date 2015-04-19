package fr.toss.magiccrusade.common.classes.spell.rogue;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.client.entity.EntityFX_Colored;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellDodge implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.DODGE);
	}
	
	@Override
	public void animate(Entity caster, Entity target)
	{
		World 			world;
		EntityFX		particles;
		double			velx;
		double			velz;
		
		this.do_spell(caster, target, null);
		world = Minecraft.getMinecraft().theWorld;
		for (int i = 0; i < 100; i++)
        {
			velx = world.rand.nextFloat();
			velz = world.rand.nextFloat();
			if (world.rand.nextInt(2) == 0)
			{
				velx = -velx;
			}
			else if (world.rand.nextInt(2) == 0)
			{
				velz = -velz;
			}
    		particles = new EntityFX_Colored(world, caster.posX, caster.posY, caster.posZ, velx, 0, velz, 2.5f, 0, 1.0f, 5.0f);
    		Minecraft.getMinecraft().effectRenderer.addEffect(particles);
        }
	}
	
	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		return (-1);
	}

	@Override
	public void do_spell(Entity caster, Entity target, Stats stat)
	{
		Vec3	vec;
		
		vec = caster.getLookVec();
		caster.motionX = vec.xCoord * -1.5d;
		caster.motionY += 0.5d;
		caster.motionZ = vec.zCoord * -1.5d;
	}
}
