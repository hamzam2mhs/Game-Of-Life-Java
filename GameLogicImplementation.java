// CLASS: GameLogicImplementation
//
// Author: Muhammad Hamza, 7859309
//
// REMARKS: What is the purpose of this class?
// This is one of the  important class as this
// implements the basic game logic which is
// required to run the game in a manner.
// This class does the following things:
// 1) It creates a new board 2D array.
// 2) It randomly sets the size of the board.
// 3) It creates a player objects and assigns
//    turns to the human player and the AI
//    one by one.
// 4) This class holds an importance as it
//    ends the program when the conditions
//    are met.
//----------------------------------------------------

//Importing Java random library.
import java.util.Random;

//GameLogicImplementation which is based on the interface GameLogic
public class GameLogicImplementation implements GameLogic
{
    private int size; //to store the random size that is generated.
    private Player currentPlayer; //current player object to store if the turn is of player one or player two.
    private Player AI; //Artificial Intelligence (Player two) object.
    private Player HumanPlayer; //object of the human player which is the user.
    private Status [][] gameBoard; //Status 2D array object for creating our game board.
    private int currentMove  = -1; //current move changes, initially it is set to -1.

    //-------------------------------------------------------
    // Constructor
    //
    // PURPOSE: This is basically a default constructor for
    // the GameLogicImplementation class, Whenever we create
    // a GameLogicImplementation object, it randomly generates
    // the size of the board, initializes the board then creates
    // a new artificial intelligence and the human player object.
    // and then sets the info of the artificial intelligence and
    // the human player.
    // PARAMETERS:
    //     The constructor takes no parameters.
    //-------------------------------------------------------
    public GameLogicImplementation()
    {
        size = getRandomGenerator(); //sets the size to random.
        gameBoard = new Status[size][size]; //initialized the game board with the random size.
        initializeBoard(); // initializes the empty board, sets all rows and cols to NEITHER.
        AI = new ArtificialIntelligence(); //initializes the artificial intelligence object.
        HumanPlayer = new HumanImplementation(); ////initializes the Human Player object.
        getStartingPlayer(); //assigns the starting player randomly.
        AI.setInfo(size,this); //sets the info for the artificial intelligence.
        HumanPlayer.setInfo(size,this); // sets the info for the human player.
    }//GameLogicImplementation

    //-----------------------------------------------------------
    // setAnswer
    //
    // PURPOSE: This method sets the current move to the col number
    // that is passed and sets the status to Game over status of
    // the player and AI to NEITHER if the its the end of the board.
    // If the condition of winning is satisfied then it sets the
    // game over status of player and AI to the playerStatus and
    // AI status accordingly.
    // PARAMETERS:
    //     This method accepts an int parameter which
    //     is the column number.
    //-----------------------------------------------------------
    public void setAnswer(int col)
    {
        //set the current move to the col.
        currentMove = col;
        //insert the currentmove.
        insert(currentMove);

        //if the end is reached, then.
        if(checkEnd())
        {
            //set the game over status of humanPlayer to neither.
            HumanPlayer.gameOver(Status.NEITHER);
            //set the game over status of AI to neither.
            AI.gameOver(Status.NEITHER);
        }//if

        //if the winning condition is satisfied, then.
        if(gameOverStatus())
        {
            //set the game over status of humanPlayer
            //to the current status of the player.
            HumanPlayer.gameOver(getPlayerStatus());
            //set the game over status of AI
            //to the current status of the AI.
            AI.gameOver(getPlayerStatus());
        }//if

        //change the player
        changePlayer();
        //after changing the player set the last move
        // of the current player to the current move.
        currentPlayer.lastMove(currentMove);
    }//setAnswer

    //-----------------------------------------------------------
    // getRow
    //
    // PURPOSE: This method gets the row at a position and returns it.
    // PARAMETERS:
    //     This method accepts an int parameter named col.
    //-----------------------------------------------------------
    public int getRow(int col)
    {
        //int position variable created.
        int position = 0; //set the position to 0 initially.

        //while the position is less than the length of the game board
        // and game board at the position and the col is empty, then ..
        while(position < gameBoard.length && gameBoard[position][col] == Status.NEITHER)
        {
            position++; //increment the position.
        }//while
        //return the position-1 then.
        return position-1;
    }//getRow

    //-----------------------------------------------------------
    // insert
    //
    // PURPOSE: This method inserts a row by getting the row number
    // from the getRow method, it also determines if the player or
    // the AI is inserting.
    // PARAMETERS:
    //     This method accepts an int parameter which is
    //     the col number.
    //-----------------------------------------------------------
    public void insert(int col)
    {
        //set the row by getting a number
        //from the getRow method.
        int row = getRow(col);

        //check if the current player is AI, then..
        if(currentPlayer.equals(AI))
        {
            //set the gameBoard at row and col to TWO.
            gameBoard[row][col] = Status.TWO;
        }//if

        //check if the current player is HumanPlayer, then..
        else
        {
            //set the gameBoard at row and col to ONE.
            gameBoard[row][col] = Status.ONE;
        }//else
    }//insert

    //-----------------------------------------------------------
    // changePlayer
    //
    // PURPOSE: This method changes the player after every turn
    // is completed.
    // PARAMETERS:
    //     This method accepts no parameter.
    //-----------------------------------------------------------
    public void changePlayer()
    {
        //if the current player is equal to the human player, then..
        if(currentPlayer.equals(HumanPlayer))
        {
            //change the current player to AI.
            currentPlayer = AI;
        }//if

        //if the current player is equal to the AI, then..
        else
        {
            //change the current player to human player.
            currentPlayer = HumanPlayer;
        }//else
    }//changePlayer

    //-----------------------------------------------------------
    // getStartingPlayer
    //
    // PURPOSE: This method randomly assigns the first turn to the
    // player or the AI.
    // PARAMETERS:
    //     This method accepts no parameter.
    //-----------------------------------------------------------
    private void getStartingPlayer()
    {
        //array object created which has the status one and status two.
        Status[] participators={Status.ONE,Status.TWO};
        //int variable randomIndex decides whose turn it should be.
        int randomIndex = new Random().nextInt(participators.length);

        //if the participator at random index has status ONE, then..
        if( participators[randomIndex] == Status.ONE )
        {
            //set the current player to the human player.
            currentPlayer = HumanPlayer ;
        }//if

        //else if the participator at random index has status TWO, then..
        else
        {
            //set the current player to the artifical intelligence.
            currentPlayer = AI;
        }//else
    }//getStartingPlayer

    //-----------------------------------------------------------
    // getRandomGenerator
    //
    // PURPOSE: This method generates a random number
    // between 6 to 12.
    // PARAMETERS:
    //     This method accepts no parameter.
    //-----------------------------------------------------------
    public int getRandomGenerator()
    {
        int max = 12; //maximum rows and cols can be be 12.
        int min = 6; //minimum rows and cols can be be 6.
        //int value which generates random value which is
        //above or equal to 6 and below or equal to 12.
        int value = (int)(Math.random()*((max-min)+1))+min;
        //return the value.
        return value;
    }//getRandomGenerator

    //-----------------------------------------------------------
    // initializeBoard
    //
    // PURPOSE: This method initializes the board and sets all
    // the rows and columns to NEITHER initially.
    // PARAMETERS:
    //     This method accepts no parameter.
    //-----------------------------------------------------------
    public void initializeBoard()
    {
        //looping through the rows
        for(int i=0; i<gameBoard.length; i++)
        {
            //looping through the columns
            for (int j=0; j<gameBoard[i].length; j++)
            {
                //set the rows and columns at i and j
                //to NEITHER.
                gameBoard[i][j] = Status.NEITHER;
            }//for
        }//for
    }//initializeBoard

    //-----------------------------------------------------------
    // checkValidCol
    //
    // PURPOSE: This method checks if the column is valid..
    // PARAMETERS:
    //     This method accepts a int variable which is a col.
    //-----------------------------------------------------------
    public boolean checkValidCol(int col)
    {
        //set the result to false.
        boolean result = false;
        //if the col is greater than or equal to 0 and the col is less then the length
        // of the col and the game board at first row and the col is neither (empty), then ..
        if(col >= 0 && col < gameBoard[0].length && gameBoard[0][col] == Status.NEITHER)
        {
            result = true; //set the result to true.
        }//if
        //return the result accordingly.
        return result;
    }//checkValidCol

    //-----------------------------------------------------------
    // checkEnd
    //
    // PURPOSE: This method checks the end of the board.
    // PARAMETERS:
    //     This method accepts no parameter.
    //-----------------------------------------------------------
    public boolean checkEnd()
    {
        //set the result to false initially.
        boolean result = false;
        //set the track to 0 initially.
        int track = 0;

        //looping through.
        for(int i=0 ; i<gameBoard.length; i++)
        {
            //if the col is not valid, then..
            if(!checkValidCol(i))
            {
                //increment the track.
                track++;
            }//if
        }//for

        //if track is equals to the length of the game board.
        if(track == gameBoard.length)
        {
            //set the result to true.
            result = true;
        }//if

        //return the result accordingly.
        return result;
    }//checkEnd

    //------------------------------------------------------------------
    // checkVertically
    //
    // PURPOSE: This method checks if the colors are same vertically.
    // PARAMETERS:
    //     This method accepts no parameter.
    //------------------------------------------------------------------
    public boolean checkVertically()
    {
        //set the result to false initially.
        boolean result = false;
        for(int i=0; i<gameBoard.length-3;i++)
        {
            for (int j=0; j<gameBoard[0].length;j++)
            {
                //if the game board at i,j and i+1,j and i+2,j and i+3,j equals the human player, then..
                if(gameBoard[i][j].equals(getPlayerStatus()) && gameBoard[i+1][j].equals(getPlayerStatus())
                     && gameBoard[i+2][j].equals(getPlayerStatus()) && gameBoard[i+3][j].equals(getPlayerStatus()))
                {
                    result = true; //set the result to true
                }//if
            }//for
        }//for
        //return the result accordingly.
        return result;
    }//checkVertically

    //------------------------------------------------------------------
    // checkHorizontally
    //
    // PURPOSE: This method checks if the colors are same horizontally.
    // PARAMETERS:
    //     This method accepts no parameter.
    //------------------------------------------------------------------
    public boolean checkHorizontally()
    {
        //set the result to false initially.
        boolean result = false;
        //loop through the rows.
        for(int i=0; i<gameBoard.length;i++)
        {
            //loop through the columns.
            for (int j=0; j<gameBoard[0].length-3;j++)
            {
                //if the game board at i,j and i,j+1 and i,j+2 and i,j+3 equals the human player, then..
                if(gameBoard[i][j].equals(getPlayerStatus()) && gameBoard[i][j+1].equals(getPlayerStatus())
                        && gameBoard[i][j+2].equals(getPlayerStatus()) && gameBoard[i][j+3].equals(getPlayerStatus()))
                {
                    result = true; //set the result to true.
                }//if
            }//for
        }//for
        //return the result accordingly.
        return result;
    }//checkHorizontally

    //-----------------------------------------------------------
    // checkDiagonally
    //
    // PURPOSE: This method checks if the colors are same diagonally.
    // PARAMETERS:
    //     This method accepts no parameter.
    //-----------------------------------------------------------
    public boolean checkDiagonally()
    {
        //boolean result set to false initially.
        boolean result = false;

        //loop through the rows.
        for(int i=3; i<gameBoard.length-3;i++)
        {
            //loop through the columns.
            for (int j=3; j<gameBoard[0].length-3;j++)
            {
                //if the game board at the row i, j and i+1, j+1 and i+2, j+2 and i+3, j+3  is equals to the human player, then..
                if(gameBoard[i][j].equals(getPlayerStatus()) && gameBoard[i+1][j+1].equals(getPlayerStatus())
                        && gameBoard[i+2][j+2].equals(getPlayerStatus()) && gameBoard[i+3][j+3].equals(getPlayerStatus()))
                {
                    result = true; //set the result to true.
                }//if

                //else //if the game board at the row i, j and i+1, j-1 and i+2, j-2 and i+3, j-3  is equals to the human player, then..
                else if(gameBoard[i][j].equals(getPlayerStatus()) && gameBoard[i+1][j-1].equals(getPlayerStatus())
                        && gameBoard[i+2][j-2].equals(getPlayerStatus()) && gameBoard[i+3][j-3].equals(getPlayerStatus()))
                {
                    result = true; //set the result to true.
                }//else if
            }//for
        }//for
        //return the result accordingly.
        return result;
    }//checkDiagonally

    //-----------------------------------------------------------
    // gameOverStatus
    //
    // PURPOSE: This method checks if the condition for winning is
    // satisfied, if the four pieces of same colour are in the line
    // horizontally, vertically or diagonally..
    // PARAMETERS:
    //     This method accepts no parameter,
    //-----------------------------------------------------------
    public boolean gameOverStatus()
    {
        //boolean flag which is set to false initially.
        boolean flag = false;

        //check if the colors match diagonally, then
        if(checkDiagonally())
        {
            //set the flag to true.
            flag = true;
        }//if

        //check if the colors match horizontally, then
        else if(checkHorizontally())
        {
            //set the flag to true.
            flag = true;
        }//else if

        //check if the colors match vertically, then
        else if(checkVertically())
        {
            //set the flag to true.
            flag = true;
        }//else if

        //return the flag accordingly.
        return flag;
    }//gameOverStatus

    //-----------------------------------------------------------
    // getPlayerStatus
    //
    // PURPOSE: This method returns status one if the current
    // player is human player otherwise return status two.
    // PARAMETERS:
    //     This method accepts no parameter.
    //-----------------------------------------------------------
    public Status getPlayerStatus()
    {
        //if the current player is human player, then
        if(currentPlayer.equals(HumanPlayer))
        {
            //return status one.
            return Status.ONE;
        }//if

        //otherwise return status two.
        return Status.TWO;
    }//getPlayerStatus

    //-----------------------------------------------------------
    // getCurrentPlayer
    //
    // PURPOSE: This method returns the current player.
    // PARAMETERS:
    //     This method accepts no parameter.
    //-----------------------------------------------------------
    public Player getCurrentPlayer()
    {
        //return the current player.
        return this.currentPlayer;
    }//getCurrentPlayer
}//GameLogicImplementation

//////////////////////END OF GAMELOGIC CLASS//////////////////////
