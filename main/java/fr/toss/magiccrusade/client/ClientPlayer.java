package fr.toss.magiccrusade.client;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import api.player.client.ClientPlayerAPI;
import api.player.client.ClientPlayerBase;
import fr.toss.magiccrusade.client.gui.ChatColor;
import fr.toss.magiccrusade.client.gui.GuiIngameOverlay;
import fr.toss.magiccrusade.client.gui.GuiString;
import fr.toss.magiccrusade.common.classes.ClasseChampion;
import fr.toss.magiccrusade.common.classes.EnumClasse;
import fr.toss.magiccrusade.common.classes.IClasse;

public class ClientPlayer extends ClientPlayerBase
{
	private static ClientPlayer	instance;
	
	private IClasse classe;
	
	/** amount of experience the player has */
	private int	experience;
	private int	experience_to_receive;

	/** player experience to achieve next level */
	private int	experience_to_next_level;
	
	/** player current level */
	private int	level;


	public ClientPlayer(ClientPlayerAPI playerapi)
	{
		super(playerapi);
		ClientPlayer.instance = this;
		this.classe = new ClasseChampion();
		this.level = 1;
		this.experience_to_next_level = this.calcul_next_level_experience();
		this.experience = 0;
		this.experience_to_receive = 0;
	}
	
	public static ClientPlayer	instance()
	{
		return (instance);
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		this.classe.update();
		if (this.experience_to_receive > 0)
		{
			this.experience += 8;
			this.experience_to_receive -= 8;
			if (this.experience >= this.experience_to_next_level)
			{
				this.on_level_up();
			}
		}
	}
	
	private void	on_level_up()
	{
		this.level++;
		this.experience = 0;
		this.experience_to_next_level = this.calcul_next_level_experience();
		GuiIngameOverlay.add_message("You have reached level " + this.level, GuiString.TIMER_NORMAL, GuiString.GREEN_SMOOTH);
	}
	
	private int	calcul_next_level_experience()
	{
		return (this.level * 20 * (this.level + 1));
	}

	/** return player max health */
	public float	getMaxHealth()
	{
		return (this.player.getMaxHealth());
	}

	/** return player health */
	public float	getHealth()
	{
		return (this.player.getHealth());
	}
	
	 @Override
	public void readEntityFromNBT(net.minecraft.nbt.NBTTagCompound nbt)
	{
		 super.readEntityFromNBT(nbt);
		 this.level			= nbt.getInteger("level"); 
		 this.experience	= nbt.getInteger("experience");
		 this.classe		= EnumClasse.load_classe_from_id(nbt.getInteger("classe_id"));
		 this.classe.read_from_nbt(nbt);
	}
		
	 @Override
	 public void writeEntityToNBT(net.minecraft.nbt.NBTTagCompound nbt)
	 {
		 super.writeEntityToNBT(nbt);
		 nbt.setInteger("level", this.level); 
		 nbt.setInteger("experience", this.experience);
		 this.classe.write_to_nbt(nbt);
	 }
	
	/** add a messege to player chat */
	public void		add_chat_message(String str)
	{
		this.player.addChatMessage(new ChatComponentText(str));
	}

	public IClasse	get_classe()
	{
		return (this.classe);
	}
	
	/** return player current level */
	public int get_level()
	{
		return (this.level);
	}
	
	/** get entity player client side */
	public EntityPlayerSP	get_player()
	{
		return (this.player);
	}

	/** add experience to the player */
	public void add_experience(int exp)
	{
		this.experience_to_receive += exp;		
	}

	/** set player level */
	public void set_level(int lvl)
	{
		this.level = lvl;
	}

	/** return experience to achieve next level */
	public int	get_total_experience()
	{
		return (this.experience_to_next_level);
	}

	public float get_experience()
	{
		return (this.experience);
	}
}
