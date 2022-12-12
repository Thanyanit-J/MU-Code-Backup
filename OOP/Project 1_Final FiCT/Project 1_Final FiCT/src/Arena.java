//THANYANIT JONGJITRAGAN 6188075

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Arena {

	public enum Row {Front, Back};	//enum for specifying the front or back row
	public enum Team {A, B};		//enum for specifying team A or B
	
	private Player[][] teamA = null;	//two dimensional array representing the players of Team A
	private Player[][] teamB = null;	//two dimensional array representing the players of Team B
	private int numRowPlayers = 0;		//number of players in each row
	
	public static final int MAXROUNDS = 100;	//Max number of turn
	public static final int MAXEACHTYPE = 3;	//Max number of players of each type, in each team.
	private final Path logFile = Paths.get("battle_log.txt");
	
	private int numRounds = 0;	//keep track of the number of rounds so far
	
	
	private int[] numTeamAPlayers = {0,0},numTeamBPlayers = {0,0};
	
	/**
	 * Constructor. 
	 * @param _numRowPlayers is the number of players in each row.
	 */
	public Arena(int _numRowPlayers)
	{	
		teamA = new Player[Row.values().length][_numRowPlayers];
		teamB = new Player[Row.values().length][_numRowPlayers];
		this.numRowPlayers = _numRowPlayers;
		
		////Keep this block of code. You need it for initialize the log file. 
		////(You will learn how to deal with files later)
		try {
			Files.deleteIfExists(logFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/////////////////////////////////////////
		
	}
	
	/**
	 * Returns true if "player" is a member of "team", false otherwise.
	 * Assumption: team can be either Team.A or Team.B
	 * @param player
	 * @param team
	 * @return
	 */
	public boolean isMemberOf(Player player, Team team)
	{
		switch(team) 
		{
			case A:
			{
				for(int row=0; row<=1; row++)
				{
					for(int pos=0; pos < teamA[row].length; pos++)
					{
						if(teamA[row][pos] == player)
							return true;
					}
				}/**/
			}break;
			case B:
			{
				for(int row=0;row<=1;row++)
				{
					for(int pos=0;pos < teamB[row].length;pos++)
					{
						if(teamB[row][pos] == player)
							return true;
					}
				}
			}break;
		}
		return false;
	}
	
	
	
	/**
	 * This methods receives a player configuration (i.e., team, type, row, and position), 
	 * creates a new player instance, and places him at the specified position.
	 * @param team is either Team.A or Team.B
	 * @param pType is one of the Player.Type  {Healer, Tank, Samurai, BlackMage, Phoenix}
	 * @param row	either Row.Front or Row.Back
	 * @param position is the position of the player in the row. Note that position starts from 1, 2, 3....
	 */
	public void addPlayer(Team team, Player.PlayerType pType, Row row, int position)
	{	
		if(position>numRowPlayers) {
			System.out.println("Excess player.");
			return;
		}/**/
		position--;
		switch(team)
		{
			case A:
			{
				if(row == Row.Front) {
					teamA[0][position] = new Player(pType);
					teamA[0][position].setRow(0);
					teamA[0][position].setPos(position);
					teamA[0][position].setTeam(team.toString());
					numTeamAPlayers[0]++;
				}
				else {
					teamA[1][position] = new Player(pType);
					teamA[1][position].setRow(1);
					teamA[1][position].setPos(position);
					teamA[1][position].setTeam(team.toString());
					numTeamAPlayers[1]++;
				}
			}break;
			case B:
			{
				if(row == Row.Front) {
					teamB[0][position] = new Player(pType);
					teamB[0][position].setRow(0);
					teamB[0][position].setPos(position);
					teamB[0][position].setTeam(team.toString());
					numTeamBPlayers[0]++;
				}
				else {
					teamB[1][position] = new Player(pType);
					teamB[1][position].setRow(1);
					teamB[1][position].setPos(position);
					teamB[1][position].setTeam(team.toString());
					numTeamBPlayers[1]++;
				}
			}break;
		}
	}
	
	
	/**
	 * Validate the players in both Team A and B. Returns true if all of the following conditions hold:
	 * 
	 * 1. All the positions are filled. That is, there each team must have exactly numRow*numRowPlayers players.
	 * 2. There can be at most MAXEACHTYPE players of each type in each team. For example, if MAXEACHTYPE = 3
	 * then each team can have at most 3 Healers, 3 Tanks, 3 Samurais, 3 BlackMages, and 3 Phoenixes.
	 * 
	 * Returns true if all the conditions above are satisfied, false otherwise.
	 * @return
	 */
	public boolean validatePlayers()
	{
		
		int countHealer=0,countTank=0,countSamurai=0,countBlackMage=0,countPhoenix=0,countCherry=0;
		
		for(int row=0; row<=1; row++)
		{
			if(teamA[row].length!=numRowPlayers || teamB[row].length!=numRowPlayers)
				return false;
			
			for(int pos=0; pos < teamA[row].length; pos++)
			{		
				switch(teamA[row][pos].getType()) {
				case Healer: 	{if(countHealer<MAXEACHTYPE)	countHealer++; 		else return false;}	break;
				case Tank:  	{if(countTank<MAXEACHTYPE) 	countTank++; 		else return false;}	break;
				case Samurai:	{if(countSamurai<MAXEACHTYPE) 	countSamurai++; 	else return false;}	break;
				case BlackMage:	{if(countBlackMage<MAXEACHTYPE)countBlackMage++; 	else return false;} break;
				case Phoenix: 	{if(countPhoenix<MAXEACHTYPE)	countPhoenix++; 	else return false;}	break;
				case Cherry: 	{if(countCherry<MAXEACHTYPE)	countCherry++; 		else return false;}	break;
				}
					
			}
		}
		return true;
	}
	
	
	/**
	 * Returns the sum of HP of all the players in the given "team"
	 * @param team
	 * @return
	 */
	public static double getSumHP(Player[][] team)
	{
		double sum=0;
		for(int row=0;row<2;row++)
			for(int pos=0; pos< team[row].length; pos++)
				sum+=team[row][pos].getCurrentHP();
		
		return sum;
	}
	
	/**
	 * Return the team (either teamA or teamB) whose number of alive players is higher than the other. 
	 * 
	 * If the two teams have an equal number of alive players, then the team whose sum of HP of all the
	 * players is higher is returned.
	 * 
	 * If the sums of HP of all the players of both teams are equal, return teamA.
	 * @return
	 */
	public Player[][] getWinningTeam()
	{
		int countTeamAAlive = 0, countTeamBAlive = 0;
		
		for(int row=0;row<2;row++)
		{
			for(int pos=0; pos < numRowPlayers; pos++)
			{
				if(teamA[row][pos].isAlive())
					countTeamAAlive++;
				if(teamB[row][pos].isAlive())
					countTeamBAlive++;
			}
		}
		
		if(countTeamAAlive > countTeamBAlive)
			return teamA;
		else if(countTeamAAlive < countTeamBAlive)
			return teamB;
		else
		{
			if(getSumHP(teamB)>getSumHP(teamA))
				return teamB;
			else return teamA;
		}
	}
	
	///////////////
	public int countAlive(Team team) 
	{
		int alive = 0;
		for(int row=0;row<2;row++)
		{
			for(int pos=0; pos < numRowPlayers; pos++)
			{
				switch(team) {
					case A: {
						if(teamA[row][pos].isAlive())
						alive++;
					} break;
					case B: {
						if(teamB[row][pos].isAlive())
						alive++;
					} break;
				}
				
				
			}
		}
		return alive;
	}
	
	//SETTER
	public Player[][] getTeamA()	{return teamA;}
	public Player[][] getTeamB()	{return teamB;}
	
	/**
	 * Find Lowest HP/Taunt Player of the ROW of the TEAM 
	 * @param theirTeam
	 * @return
	 */
	public static Player findLowestHP(Player[][] team, Row ROW, Boolean returnTaunt)
	{
		double lwst = 0;
		int target_row=-1, target_pos=-1;
		
		int row,pos;
		
		//Return taunt player immediately if found.
		if(returnTaunt)
		{
			for(row=0;row<team.length;row++)
			{
				for(pos=0; pos<team[row].length; pos++)
				{
					if(team[row][pos].isAlive() && team[row][pos].isTaunting())
					{
						return team[row][pos];
					}
				}
			}
		}
		
		//Check Lowest HP
		if(ROW.equals(Row.Front)) row=0;
		else row=1;
		for(pos=0; pos<team[row].length; pos++)
		{
			if(team[row][pos].isAlive())
			{
				
				if(target_row==-1)
				{
					target_row=row;
					target_pos=pos;
					lwst = team[row][pos].getCurrentHP();
					continue;
				}
				
				double x = team[row][pos].getCurrentHP();
				if(x < lwst)
				{
					lwst = x;
					target_row = row;
					target_pos = pos;
				}
			}
				
		}
		if(target_row==-1) return null;
		return team[target_row][target_pos];
	}
	
	/**
	 * This method simulates the battle between teamA and teamB. The method should have a loop that signifies
	 * a round of the battle. In each round, each player in teamA invokes the method takeAction(). The players'
	 * turns are ordered by its position in the team. Once all the players in teamA have invoked takeAction(),
	 * not it is teamB's turn to do the same. 
	 * 
	 * The battle terminates if one of the following two conditions is met:
	 * 
	 * 1. All the players in a team has been eliminated.
	 * 2. The number of rounds exceeds MAXROUNDS
	 * 
	 * After the battle terminates, report the winning team, which is determined by getWinningTeam().
	 */
	int row,pos;
	String turnOf;
	boolean allow = true;
	public void startBattle()
	{
		for(numRounds=1; numRounds<=MAXROUNDS; numRounds++)
		{
			System.out.println("@ Round "+numRounds);
			
			//Team A's Action
			turnOf = Team.A.toString();
			for(row=0; row<teamA.length; row++)
			{
				for(pos=0; pos<numRowPlayers; pos++)
				{
					teamA[row][pos].takeAction(this);
				}
			}
			
			//Team B's Action
			turnOf = Team.B.toString();
			for(row=0; row<teamB.length; row++)
			{
				for(pos=0; pos<numRowPlayers; pos++)
				{
					teamB[row][pos].takeAction(this);
				}
			}
			
			displayArea(this,allow);
			logAfterEachRound();
			if((findLowestHP(teamA,Row.Front,false)==null && findLowestHP(teamA,Row.Back,false)==null )||
				(findLowestHP(teamB,Row.Front,false)==null && findLowestHP(teamB,Row.Back,false)==null))
				break;
			
		}/**/
		
		System.out.println("@@@ Team " + ((getWinningTeam()==teamA)? Team.A.toString():Team.B.toString()) + " won.");
	}
	
	
	
	/**
	 * This method displays the current area state, and is already implemented for you.
	 * In startBattle(), you should call this method once before the battle starts, and 
	 * after each round ends. 
	 * 
	 * @param arena
	 * @param verbose
	 */
	public static void displayArea(Arena arena, boolean verbose)
	{
		StringBuilder str = new StringBuilder();
		if(verbose)
		{
			str.append(String.format("%43s   %40s","Team A","")+"\t\t"+String.format("%-38s%-40s","","Team B")+"\n");
			str.append(String.format("%43s","BACK ROW")+String.format("%43s","FRONT ROW")+"  |  "+String.format("%-43s","FRONT ROW")+"\t"+String.format("%-43s","BACK ROW")+"\n");
			for(int i = 0; i < arena.numRowPlayers; i++)
			{
				str.append(String.format("%43s",arena.teamA[1][i])+String.format("%43s",arena.teamA[0][i])+"  |  "+String.format("%-43s",arena.teamB[0][i])+String.format("%-43s",arena.teamB[1][i])+"\n");
			}
		}
	
		str.append("@ Total HP of Team A = "+getSumHP(arena.teamA)+". @ Total HP of Team B = "+getSumHP(arena.teamB)+"\n\n");
		System.out.print(str.toString());
		
		
	}
	
	/**
	 * This method writes a log (as round number, sum of HP of teamA, and sum of HP of teamB) into the log file.
	 * You are not to modify this method, however, this method must be call by startBattle() after each round.
	 * 
	 * The output file will be tested against the auto-grader, so make sure the output look something like:
	 * 
	 * 1	47415.0	49923.0
	 * 2	44977.0	46990.0
	 * 3	42092.0	43525.0
	 * 4	44408.0	43210.0
	 * 
	 * Where the numbers of the first, second, and third columns specify round numbers, sum of HP of teamA, and sum of HP of teamB respectively. 
	 */
	private void logAfterEachRound()
	{
		try {
			Files.write(logFile, Arrays.asList(new String[]{numRounds+"\t"+getSumHP(teamA)+"\t"+getSumHP(teamB)}), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
