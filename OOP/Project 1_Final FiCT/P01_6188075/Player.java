import java.util.Arrays;

//THANYANIT JONGJITRAGAN 6188075

public class Player {

	public enum PlayerType {Healer, Tank, Samurai, BlackMage, Phoenix, Cherry};
	
	private PlayerType type; 	//Type of this player. Can be one of either Healer, Tank, Samurai, BlackMage, or Phoenix
	private double maxHP;		//Max HP of this player
	private double currentHP;	//Current HP of this player 
	private double atk;			//Attack power of this player
	
	private int numSpecialTurns; //AKA. max base cooldown
	 int cooldown;
	
	
	private boolean isSleep = false;
	private boolean isCursed = false;
	private boolean isTaunting = false;
	private boolean isAlive;
	
	private int row, pos;
	private String team;
	
	private String SpAbility,SpA_action;
	
	/**
	 * Constructor of class Player, which initializes this player's type, maxHP, atk, numSpecialTurns, 
	 * as specified in the given table. It also reset the internal turn count of this player. 
	 * @param _type
	 */
	public Player(PlayerType _type)
	{	
		type = _type;
		switch(_type)
		{
			case Healer: 	{maxHP = 4790; atk = 238; numSpecialTurns = 4; SpAbility="Heal"; SpA_action="Heals";}	break;
			case Tank:  	{maxHP = 5340; atk = 255; numSpecialTurns = 4; SpAbility="Taunt"; SpA_action="is Taunting";}	break;
			case Samurai:	{maxHP = 4005; atk = 368; numSpecialTurns = 3; SpAbility="Double-Slash"; SpA_action="Double-Slashes";}	break;
			case BlackMage:	{maxHP = 4175; atk = 303; numSpecialTurns = 4; SpAbility="Curse"; SpA_action="Curses";} break;
			case Phoenix: 	{maxHP = 4175; atk = 209; numSpecialTurns = 8; SpAbility="Revive"; SpA_action="Revives";}	break;
			case Cherry: 	{maxHP = 3560; atk = 198; numSpecialTurns = 4; SpAbility="Fortune-Cookies"; SpA_action="Feeds a Fortune Cookie to";}	break;
			
		}
		cooldown = numSpecialTurns;
		currentHP = maxHP;
		
		isSleep = false;
		isCursed = false;
		isTaunting = false;
		isAlive = true;
		
		row=-1;
		pos=-1;
		team=" ";
	}
	
	/**
	 * Returns the current HP of this player
	 * @return
	 */
	public double getCurrentHP()
	{
		return this.currentHP;
	}
	
	/**
	 * Returns type of this player
	 * @return
	 */
	public Player.PlayerType getType()
	{
		return this.type;
	}
	
	/**
	 * Row Pos Team Getter/Setter
	 * @return
	 */
	public int getRow() 		{return row;}
	public void setRow(int row)	{this.row = row;}
	public int getPos() 		{return pos;}
	public void setPos(int pos)	{this.pos = pos;}
	public String getTeam() 		{return team;}
	public void setTeam(String team)	{this.team = team;}

	/**
	 * Returns max HP of this player. 
	 * @return
	 */
	public double getMaxHP()	{return this.maxHP;}
	
	/**
	 * Returns whether this player is sleeping.
	 * @return
	 */
	public boolean isSleeping()
	{
		return this.isSleep;
	}
	
	/**
	 * Returns whether this player is being cursed.
	 * @return
	 */
	public boolean isCursed()
	{
		return this.isCursed;
	}
	
	/**
	 * Returns whether this player is alive (i.e. current HP > 0).
	 * @return
	 */
	public boolean isAlive()
	{
		return this.isAlive;
	}
	
	/**
	 * Returns whether this player is taunting the other team.
	 * @return
	 */
	public boolean isTaunting()
	{
		return this.isTaunting;
	}
	
	/**
	 * Set the player died.
	 * @param player
	 */
	void died(Player player)
	{
		player.currentHP=0;
		player.isAlive=false;
		player.isTaunting=false;
		player.isSleep=false;
		player.isCursed=false;
	}
	
	/**
	 * To attack and check died
	 * @param target
	 */
	public void attack(Player target)
	{
		displayAction(this,"Attacks",target);
		target.currentHP -= this.atk;
		if(target.currentHP < 0)
		{
			died(target);
		}
	}
	
	public void useSpecialAbility(Player[][] myTeam, Player[][] theirTeam)
	{	
		//int target_row, target_pos;
		
		switch(this.getType())
		{
			case Healer:{
				Player target_front,target_back,target=null;
				
				target_front=Arena.findLowestHP(myTeam, Arena.Row.Front, false);
				target_back=Arena.findLowestHP(myTeam, Arena.Row.Back, false);
				
				if(target_front==null) target=target_back;
				else if(target_back==null) target=target_front;
				else if(target_front.getCurrentHP()/target_front.getMaxHP()<=target_back.getCurrentHP()/target_back.getMaxHP()) target=target_front;
				else target=target_back;
					
				if(target!=null)
				{
					if(target.isCursed()==false)
					{
						displayAction(this,this.SpA_action,target);
						//System.out.println("F:"+target_front.getType()+target_front.currentHP +" B:"+target_back.getType()+target_back.currentHP +" T:"+target.getType());
						target.currentHP += 0.25*(target.getMaxHP());
						if(target.currentHP > target.getMaxHP())
							target.currentHP = target.getMaxHP(); 
					}
				}
					
			}break;
			
			case Tank:{
				this.isTaunting = true;
				displayAction(this,this.SpA_action,null);
			}break;
			
			case Samurai:{
				
				Player target;
				target = Arena.findLowestHP(theirTeam,Arena.Row.Front,true);
				if(target==null)
					target = Arena.findLowestHP(theirTeam,Arena.Row.Back,true);
				
				//If find target, do Action
				if(target!=null)
				{
					//Player target = theirTeam[target_row][target_pos];
					displayAction(this,this.SpA_action,target);
					for(int atkCount=2; atkCount>0; atkCount--)
					{
						target.currentHP -= this.atk;
						if(target.currentHP < 0)
						{
							died(target);
						}
					}
				}
			}break;
			case BlackMage:{
				
				Player target;
				target = Arena.findLowestHP(theirTeam,Arena.Row.Front,true);
				if(target==null)
					target = Arena.findLowestHP(theirTeam,Arena.Row.Back,true);
				
				//If find target, do Action
				if(target!=null) {
					displayAction(this,this.SpA_action,target);
					target.isCursed = true;
				}
			}break;
			case Phoenix:{
				//Find Dead teammate, then rez immediately
				boolean endThis=false;
				for(int row=0; row<2; row++)
				{
					for(int pos=0; pos<myTeam[row].length; pos++)
					{
						if(myTeam[row][pos].isAlive()==false && endThis==false)
						{
							displayAction(this,this.SpA_action,myTeam[row][pos]);
							myTeam[row][pos].isAlive = true;
							myTeam[row][pos].currentHP = 0.3*myTeam[row][pos].maxHP;
							myTeam[row][pos].cooldown = myTeam[row][pos].numSpecialTurns;
							myTeam[row][pos].isSleep = false;
							myTeam[row][pos].isCursed = false;
							myTeam[row][pos].isTaunting = false;
							
							endThis=true;
							break;
						}
					}
				}
			}break;
			case Cherry:{
				for(int row=0; row<2; row++)
				{
					for(int pos=0; pos<theirTeam[row].length; pos++)
					{
						if(theirTeam[row][pos].isAlive() /*&& theirTeam[row][pos].isSleep()==false*/)
						{
							displayAction(this,this.SpA_action,theirTeam[row][pos]);
							theirTeam[row][pos].isSleep = true;
						}
					}
				}
			}break;
			
		}
	}
	
	public void liftCurse(Player[][] team)
	{
		for(int row=0;row<2;row++)
		{
			for(int pos=0;pos<team.length;pos++)
			{
				if(team[row][pos].isCursed()==true)
					team[row][pos].isCursed=false;
			}
		}
	}
	
	/**
	 * This method is called by Arena when it is this player's turn to take an action. 
	 * By default, the player simply just "attack(target)". However, once this player has 
	 * fought for "numSpecialTurns" rounds, this player must perform "useSpecialAbility(myTeam, theirTeam)"
	 * where each player type performs his own special move. 
	 * @param arena
	 */
	public void takeAction(Arena arena)
	{	
		String turnOf=arena.turnOf; //Turn of which team.
		Player performer, target;
		
		
		//Team A's turn
		if(turnOf.equals("A")) {
			performer=arena.getTeamA()[arena.row][arena.pos];
			target=arena.findLowestHP(arena.getTeamB(),Arena.Row.Front,true);
			if(target==null)
				target=arena.findLowestHP(arena.getTeamB(),Arena.Row.Back,true);
		}
		//Team's B turn
		else {
			performer=arena.getTeamB()[arena.row][arena.pos];
			target=arena.findLowestHP(arena.getTeamA(),Arena.Row.Front,true);
			if(target==null)
				target=arena.findLowestHP(arena.getTeamA(),Arena.Row.Back,true);
		}
		
		//Check Taunt,Curse
		if(turnOf.equals("A")) liftCurse(arena.getTeamB());
		else liftCurse(arena.getTeamA());
		
		if(performer.isTaunting()==true) performer.isTaunting=false;
		
		//Check Sleep, Alive
		//Perform action
		if(/*target!=null && /**/performer.isAlive && performer.isSleep==false) {
			performer.cooldown--;
			if(performer.cooldown==0)
			{
				performer.useSpecialAbility((turnOf.equals("A"))? arena.getTeamA():arena.getTeamB(),(turnOf.equals("A"))? arena.getTeamB():arena.getTeamA());
				performer.cooldown=performer.numSpecialTurns;
			}
			else
			{
				if(target!=null)
				performer.attack(target);
			}
		}
		else if(performer.isSleep)
			performer.isSleep=false;/**/
	}
	
	void displayAction(Player performer,String action, Player target){
		System.out.print("# "+performer.team+"["+((performer.row==0)?Arena.Row.Front.toString():Arena.Row.Back.toString())+"]["+(performer.pos+1)+"] {"+performer.getType()+"} ");
		System.out.print(action);
		if(target!=null)System.out.println(" "+target.team+"["+((target.row==0)?Arena.Row.Front.toString():Arena.Row.Back.toString())+"]["+(target.pos+1)+"] {"+target.getType()+"}");
		else System.out.println();
	}
	
	/**
	 * This method overrides the default Object's toString() and is already implemented for you. 
	 */
	@Override
	public String toString()
	{
		return "["+this.type.toString()+" HP:"+this.currentHP+"/"+this.maxHP+" ATK:"+this.atk+"]["
				+((this.isCursed())?"C":"")
				+((this.isTaunting())?"T":"")
				+((this.isSleeping())?"S":"")
				+"]";
	}
	
	
}
