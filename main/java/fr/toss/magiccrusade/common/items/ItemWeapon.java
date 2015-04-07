package fr.toss.magiccrusade.common.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.client.gui.ChatColor;
import fr.toss.magiccrusade.common.player.Stats;

public class ItemWeapon extends ItemSword
{
	protected Stats	stats;
	private boolean hasEffect;

	public ItemWeapon(ToolMaterial material)
	{
		super(material);
		this.stats = new Stats();
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack is)
    {
        return (this.hasEffect || is.isItemEnchanted());
    }
	
	 /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean bool)
    {
    	list.addAll(this.stats.to_string_list());
    }
    
	public ItemWeapon setHasEffect()
	{
		this.hasEffect = true;
		return (this);
	}
	
	public Entity getLookingEntity(EntityPlayer player, double range)
	{
		List	list;
		Entity	entity;
		double	d;
		double	d1;
		Vec3	vec3d;
		Vec3	vec3d1;

		list =  player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(range, range, range));
		if (list != null)
		{
			for (int i = 0; i < list.size(); i++)
			{
				entity = (Entity)list.get(i);

                if (!entity.isDead)
                {
             	   if(entity instanceof EntityLivingBase)
             	   {
             		   vec3d = player.getLook(1.0F).normalize();
             		   vec3d1 = new Vec3(entity.posX - player.posX, (entity.getEntityBoundingBox().minY + (double)(entity.height / 2.0F)) - (player.posY + (double)player.getEyeHeight()), entity.posZ - player.posZ);
             		   d = vec3d1.lengthVector();
             		   vec3d1 = vec3d1.normalize();
             		   d1 = vec3d.dotProduct(vec3d1);
             	       if (d1 > 1.0D - 0.025000000000000001D / d)
             	       {
             	    	   System.out.println("Looking at " + entity);
             	    	   return (entity);
             	       }
             	   }    
				}
			}
	    }
		return (null);
	}

}
