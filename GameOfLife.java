//----------------------------------------------
// REMARKS: What is the purpose of this program?
// In this assignment I have written a Java code
// to enable a user to play a game of “Connect”,
// where players alternate dropping coloured
// playing pieces into a grid. The goal of the
// game is to get four of your pieces in a line
// (horizontal, diagonal or vertical) before the
// artificial intelligence does.
//-----------------------------------------------

public class GameOfLife
{
    public static void main(String[] args)
    {
        //New Game logic implementation object instantiated.
        GameLogicImplementation game = new GameLogicImplementation();
        //to start the game, call the getCurrentPlayer method and the lastMove method.
        game.getCurrentPlayer().lastMove(-1);
    }//main
}//GameOfLife

////////////////////////END OF CLASS/////////////////////
