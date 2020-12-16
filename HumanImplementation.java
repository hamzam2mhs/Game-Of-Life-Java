// CLASS: HumanImplementation
//
// REMARKS: What is the purpose of this class?
// This is the implementation of the Human and
// Player interface.
//----------------------------------------------------
public class HumanImplementation implements Human, Player
{
    private GameLogic gameLogic; //GameLogic object created.
    private UI graphics; //UI object.
    private int size; //size variable.

    //-------------------------------------------------------
    // Constructor
    //
    // PURPOSE: This is basically a default constructor for
    // the HumanImplementation class, which initializes the
    // graphic object.
    // PARAMETERS:
    //     The constructor takes no parameters.
    //-------------------------------------------------------
    public  HumanImplementation()
    {
        graphics = new SwingGUI(); //initializes the SwingGUI
    }//HumanImplementation

    //-----------------------------------------------------------
    // setAnswer
    //
    // PURPOSE: This method calls the gamelogic set answer method
    // by passing the col.
    // PARAMETERS:
    //     This method accepts an int parameter which
    //     is the column number.
    //-----------------------------------------------------------
    public void setAnswer(int col)
    {
        //calling the setanswer method of the gamelogic class.
        gameLogic.setAnswer(col);
    }//setAnswer

    //-----------------------------------------------------------
    // lastMove
    //
    // PURPOSE: This method calls the UI lastMove
    // by passing the lastCol.
    // PARAMETERS:
    //     This method accepts an int parameter which
    //     is the last column number.
    //-----------------------------------------------------------
    public void lastMove(int lastCol)
    {
        //calling the UI method of the graphics class.
        graphics.lastMove(lastCol);
    }//lastMove

    //-----------------------------------------------------------
    // gameOver
    //
    // PURPOSE: This method calls the UI gameOver
    // by passing the winner.
    // PARAMETERS:
    //     This method accepts a parameter named winner
    //     of the type status.
    //-----------------------------------------------------------
    public void gameOver(Status winner)
    {
        //UI gameOver method.
        graphics.gameOver(winner);
        //when the game is over, exit.
        System.exit(0);
    }//gameOver

    //-----------------------------------------------------------
    // setInfo
    //
    // PURPOSE: This method calls the UI gameOver
    // by passing the winner.
    // PARAMETERS:
    //     This method accepts a parameter named winner
    //     of the type status.
    //-----------------------------------------------------------
    public void setInfo(int size, GameLogic gl)
    {
        this.size = size; //set the size to the passed size.
        gameLogic = gl; //set the gameLogic to the passed gameLogic object.
        //call the UI setInfo method by passing the human and the size.
        graphics.setInfo(this,this.size);
    }//setInfo
}//HumanImplementation

//////////////////////END OF HumanImplementation CLASS//////////////////////


