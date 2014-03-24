package fr.wolf.moteur.states;

public enum GameState
{
	LAUNCH_MENU(), PLAYING(), EXIT(), SETTINGS();
	
	public static GameState gameState;
	
	public static void setStates(GameState state)
	{
		gameState = state;
	}
}