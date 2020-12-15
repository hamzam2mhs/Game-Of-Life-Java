// CLASS: ArtificialIntelligence
//
// Author: Muhammad Hamza, 7859309
//
// REMARKS: What is the purpose of this class?
// This is the most important class, as it
// handles all the methods needed to implement
// our artificial intelligence.
// FEATURES OF THIS CLASS IS:
// 1) I have made AI player to be more defensive,
// 2) AI puts the block with a genius that it is
//    offensive and tries to put the block in a
//    place where it makes it impossible for the
//    player to win.
// 3) This is not the hardest AI as I have designed
//    it in a way that it is not too hard for the
//    human player to win.
//----------------------------------------------------
public class ArtificialIntelligence implements Player
{
    GameLogic logic; //object of the game logic created to call the methods from that class.
    private Status [][] gameBoard; //game board for the AI created.
    private int size = 0; //set the size to 0 initially.

    //-----------------------------------------------------------
    // lastMove
    //
    // PURPOSE: This method keeps the track of the last move.
    // PARAMETERS:
    //     This method accepts an int parameter which
    //     is the number of the last column.
    //-----------------------------------------------------------
    public void lastMove(int lastCol)
    {
        //if the last column is not -1
        if(lastCol != -1)
        {
            //then human can insert in the last col.
            insertByHuman(lastCol);
        }//if

        //int variable created to check the moves in the gameboard.
        int temp = checkMoves(gameBoard);

        //while the column at temp is not valid ..
        while(!checkValidColumn(temp))
        {
            //set the temp to the int which is returned by the checkMoves method.
            temp = checkMoves(gameBoard);
        }//while

        //otherwise AI will insert.
        insertByAI(temp);
        //modify the game board after inserting.
        logic.setAnswer(temp);
    }//lastMove

    //-----------------------------------------------------------
    // checkValidColumn
    //
    // PURPOSE: This method keeps the track of the last move.
    // PARAMETERS:
    //     This method accepts an int parameter which
    //     is the number of the last column.
    //-----------------------------------------------------------
    public boolean checkValidColumn(int col)
    {
        //set the result to false initially.
        boolean result = false;
        //if the col is less the size and col is greater than 0, then..
        if(col < this.size && col > 0)
        {
            //set the result to true.
            result = true;
        }
        //return the result accordingly.
        return result;
    }//checkValidColumn

    //-----------------------------------------------------------
    // gameOver
    //
    // PURPOSE: This method does nothing in this class
    // I'm just calling the method here because this class
    // implements the Player class and we have to use the
    // methods in the interface otherwise it won't work..
    // PARAMETERS:
    //     This method accepts an status object named winner
    //     as a parameter which is doing nothing in this class.
    //-----------------------------------------------------------
    public void gameOver(Status winner)
    {
        //THIS METHOD DOES NOTHING IN THIS CLASS,
        // READ THE PURPOSE OF THIS METHOD ABOVE.
    }//gameOver

    //-----------------------------------------------------------
    // setInfo
    //
    // PURPOSE: This method sets the info and passed the
    // game logic.
    // PARAMETERS:
    //     This method accepts two parameter which
    //     is an int and the object of the gameLogic
    //-----------------------------------------------------------
    public void setInfo(int size, GameLogic gl)
    {
        this.size = size; //set the size to the size passed in the parameter.
        this.logic = gl; //set the logic to the gamelogic object passed in the parameter.
        gameBoard = new Status[size][size]; //initialize the game board of size.
        initializeBoard(); //initialize the board.
    }//setInfo

    //-----------------------------------------------------------
    // initializeBoard
    //
    // PURPOSE: This method initialized the new board.
    // PARAMETERS:
    //     This method accepts no parameter.
    //-----------------------------------------------------------
    public void initializeBoard()
    {
        //looping through the rows.
        for(int i=0; i<gameBoard.length; i++)
        {
            //looping through the columns.
            for (int j=0; j<gameBoard[i].length; j++)
            {
                //set the game board at i an j to NEITHER
                //in the beginning.
                gameBoard[i][j] = Status.NEITHER;
            }//for
        }//for
    }//initializeBoard

    //-----------------------------------------------------------
    // getRow
    //
    // PURPOSE: This method gets the row number by passing
    // the column number.
    // PARAMETERS:
    //     This method accepts an int parameter name col.
    //-----------------------------------------------------------
    public int getRow(int col)
    {
        int position = 0; //set the position to 0 initially.
        //while the position is less than the length of gameboard and the gameboard at
        //position and col is NEITHER, then..
        while(position < gameBoard.length && gameBoard[position][col] == Status.NEITHER)
        {
            position++; //increment the position.
        }//while

        //return the position-1.
        return position-1;
    }//getRow

    //-----------------------------------------------------------
    // insertByAI
    //
    // PURPOSE: This method lets the AI to insert in the
    // passed column number.
    // PARAMETERS:
    //     This method accepts an int parameter name col.
    //-----------------------------------------------------------
    public void insertByAI(int col)
    {
        int row = getRow(col); //set the row from the number which we get from getRow method.
        gameBoard[row][col] = Status.TWO; //set the game board at that row and col to the TWO.
    }//insertByAI

    //-----------------------------------------------------------
    // insertByHuman
    //
    // PURPOSE: This method lets the HumanPlayer to insert
    // in the passed column number.
    // PARAMETERS:
    //     This method accepts an int parameter name col.
    //-----------------------------------------------------------
    public void insertByHuman(int col)
    {
        int row = getRow(col); //set the row from the number which we get from getRow method.
        gameBoard[row][col] = Status.ONE; //set the game board at that row and col to the ONE.
    }//insertByHuman

    //-----------------------------------------------------------
    // checkMoves
    //
    // PURPOSE: This method determines where the AI should be
    // inserting after the player inserts.
    // PARAMETERS:
    //     This method accepts a 2D array object of type Status.
    //------------------------------------------------------------
    public int checkMoves(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;

        //if the result is equal to 0, then.
        if(result == 0)
        {
            //set the result to the number that we get from the checkHorizontalHighest method.
            result = checkHorizontalHighest(array);

            //if the result is again equal to 0, then.
            if(result == 0) {
                //set the result to the number that we get from the checkVerticalHighest method.
                result = checkVerticalHighest(array);

                //if the result is still equal to 0, then.
                if (result == 0) {
                    //set the result to the number that we get from the checkLeftHighest method.
                    result = checkLeftHighest(array);

                    //if the result is still equal to 0, then.
                    if (result == 0) {
                        //set the result to the number that we get from the checkDiagonal1Highest method.
                        result = checkDiagonal1Highest(array);

                        //if the result is still equal to 0, then.
                        if (result == 0) {
                            //set the result to the number that we get from the checkDiagonal2Highest method.
                            result = checkDiagonal2Highest(array);

                            //if the result is still equal to 0, then.
                            if (result == 0) {
                                //set the result to the number that we get from the checkHorizontal1Highest method.
                                result = checkHorizontal1Highest(array);

                                //if the result is still equal to 0, then.
                                if (result == 0) {
                                    //set the result to the number that we get from the checkVertical1Highest method.
                                    result = checkVertical1Highest(array);

                                    //if the result is still equal to 0, then.
                                    if (result == 0) {
                                        //set the result to the number that we get from the checkDiagonal11Highest method.
                                        result = checkDiagonal11Highest(array);

                                        //if the result is still equal to 0, then.
                                        if (result == 0) {
                                            //set the result to the number that we get from the checkDiagonal21Highest method.
                                            result = checkDiagonal21Highest(array);

                                            //if the result is still equal to 0, then.
                                            if (result == 0) {
                                                //set the result to the number that we get from the checkHorizontal2Highest method.
                                                result = checkHorizontal2Highest(array);

                                                //if the result is still equal to 0, then.
                                                if (result == 0) {
                                                    //set the result to the number that we get from the checkVertical2Highest method.
                                                    result = checkVertical2Highest(array);

                                                    //if the result is still equal to 0, then.
                                                    if (result == 0) {
                                                        //set the result to the number that we get from the checkDiagonal12Highest method.
                                                        result = checkDiagonal12Highest(array);

                                                        //if the result is still equal to 0, then.
                                                        if (result == 0) {
                                                            //set the result to the number that we get from the checkDiagonal22Highest method.
                                                            result = checkDiagonal22Highest(array);

                                                            //if the result is still equal to 0, then.
                                                            if (result == 0) {
                                                                //set the result to the number that we get from the getRandomGenerator method.
                                                                result = getRandomGenerator();
                                                            }//if
                                                        }//if
                                                    }//if
                                                }//if
                                            }//if
                                        }//if
                                    }//if
                                }//if
                            }//if
                        }//if
                    }//if
                }//if
            }//if
        }//if
        //return the result number.
        return result;
    }//checkMoves

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
    // checkHorizontalHighest
    //
    // PURPOSE: This method checks if the player has inserted
    // three matching colours or not horizontally.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkHorizontalHighest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows.
        for(int row = 0; row < array.length; row++)
        {
            //looping through the cols.
            for (int col = 0; col < array[0].length - 3; col++)
            {
                //if the array at row, col and array at row, col+1 and array at row, col+2 is equal to ONE and array at row, col+3 is equal to NEITHER..
                if(array[row][col] == Status.ONE && array[row][col+1] == Status.ONE && array[row][col+2] == Status.ONE && array[row][col+3] == Status.NEITHER)
                {
                    //set the result to the col+3.
                    result = col + 3;
                }//if
            }//for
        }//for
        //return the result.
        return result;
    }//checkHorizontalHighest

    //-----------------------------------------------------------
    // checkLeftHighest
    //
    // PURPOSE: This method is especially for the left side,
    // and it checks if the player has 3 inserted three matching
    // colours in a row on the first row.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkLeftHighest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 0; row < array.length; row++)
        {
            //looping through the cols.
            for (int col = 2; col < array[0].length; col++)
            {
                //if the array at row, col and array at row, col-1 and array at row, col-2 is equal to ONE and array at row, col-3 is equal to NEITHER..
                if(array[row][col] == Status.ONE && array[row][col-1] == Status.ONE && array[row][col-2] == Status.ONE && array[row][col-3] == Status.NEITHER)
                {
                    //set the result to the col-3.
                    result = col - 3;
                }//if
            }//for
        }//for
        //return the result.
        return result;
    }//checkLeftHighest

    //-----------------------------------------------------------
    // checkHorizontal1Highest
    //
    // PURPOSE: This method checks if the player has inserted
    // two matching colours by checking at col and col+1
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkHorizontal1Highest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 0; row < array.length; row++)
        {
            //looping through the cols.
            for (int col = 0; col < array[0].length - 2; col++)
            {
                //if the array at row, col and array at row, col+1 is equal to ONE and array at row, col+2 is equal to NEITHER..
                if(array[row][col] == Status.ONE && array[row][col+1] == Status.ONE && array[row][col+2] == Status.NEITHER)
                {
                    //set the result to the col+2.
                    result = col + 2;
                }//if
            }//for
        }//for
        //return the result.
        return result;
    }//checkHorizontal1Highest

    //-----------------------------------------------------------
    // checkHorizontal2Highest
    //
    // PURPOSE: This method checks if the player has inserted
    // one matching colours by checking at col
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkHorizontal2Highest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 0; row < array.length; row++)
        {
            //looping through the cols
            for (int col = 0; col < array[0].length - 1; col++)
            {
                //if the array at row, col is equal to ONE and array at row, col+1 is equal to NEITHER..
                if(array[row][col] == Status.ONE && array[row][col+1] == Status.NEITHER)
                {
                    //set the result to col+1.
                    result = col + 1;
                }//if
            }//for
        }//for
        //return the result.
        return result;
    }//checkHorizontal2Highest

    //-----------------------------------------------------------
    // checkVerticalHighest
    //
    // PURPOSE: This method checks if the player has inserted
    // three matching colours or not vertically.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkVerticalHighest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 1; row < array.length-2; row++)
        {
            //looping through the cols
            for (int col = 0; col < array[0].length ; col++)
            {
                //if the array at row, col and array at row+1, col and array at row+2, col is equal to ONE and array at row-1, col is equal to NEITHER..
                if(array[row][col] == Status.ONE && array[row+1][col] == Status.ONE && array[row+2][col] == Status.ONE && array[row-1][col] == Status.NEITHER)
                {
                    //set the result to col.
                    result = col;
                }//if
            }//for
        }//for
        //return the result.
        return result;
    }//checkVerticalHighest

    //-----------------------------------------------------------
    // checkVertical1Highest
    //
    // PURPOSE: This method checks if the player has inserted
    // two matching colours or not vertically.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkVertical1Highest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows.
        for(int row = 1; row < array.length-1; row++)
        {
            //looping through the cols
            for (int col = 0; col < array[0].length ; col++)
            {
                //if the array at row, col and array at row+1, col and array at row-1, col is equal to NEITHER..
                if(array[row][col] == Status.ONE && array[row+1][col] == Status.ONE && array[row-1][col] == Status.NEITHER)
                {
                    //set the result to col.
                    result = col;
                }//if
            }//for
        }//for
        //return the result.
        return result;
    }//checkVertical1Highest

    //-----------------------------------------------------------
    // checkVertical2Highest
    //
    // PURPOSE: This method checks if the player inserted
    // vertically or not.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkVertical2Highest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 1; row < array.length; row++)
        {
            //looping through the cols
            for (int col = 0; col < array[0].length ; col++)
            {
                //if the array at row, col is equal to ONE and array at row-1, col is equal to NEITHER, then..
                if( array[row][col] == Status.ONE && array[row-1][col] == Status.NEITHER )
                {
                    //set the result to the col.
                    result = col;
                }//if
            }//for
        }//for
        //return the result accordingly.
        return result;
    }//checkVertical2Highest

    //-----------------------------------------------------------
    // checkDiagonal1Highest
    //
    // PURPOSE: This method checks if the player has inserted
    // three matching colours or not diagonally.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkDiagonal1Highest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 0; row < array.length-3; row++)
        {
            //looping through the cols
            for (int col = 0; col < array[0].length - 3 ; col++)
            {
                //if the array at row, col and array at row+1, col+1 and array at row+2, col+2 is equal to ONE and array at row+3, col+3 is equal to NEITHER..
                if(array[row][col] == Status.ONE && array[row+1][col+1] == Status.ONE && array[row+2][col+2] == Status.ONE && array[row+3][col+3] == Status.NEITHER)
                {
                    //set the result to col+3.
                    result = col + 3;
                }//if
            }//for
        }//for
        //return the result accordingly.
        return result;
    }//checkDiagonal1Highest

    //-----------------------------------------------------------
    // checkDiagonal11Highest
    //
    // PURPOSE: This method checks if the player has inserted
    // two matching colours or not Diagonally.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkDiagonal11Highest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 0; row < array.length-2; row++)
        {
            //looping through the cols
            for (int col = 0; col < array[0].length - 2; col++)
            {
                //if the array at row, col and array at row+1, col+1 and array at row+2, col+2 is equal to NEITHER..
                if(array[row][col] == Status.ONE && array[row+1][col+1] == Status.ONE && array[row+2][col+2] == Status.NEITHER)
                {
                    //set the result to col+2
                    result = col + 2;
                }//if
            }//for
        }//for
        return result;
    }//checkDiagonal11Highest

    //-----------------------------------------------------------
    // checkDiagonal12Highest
    //
    // PURPOSE: This method checks if the player inserted
    // diagonally or not.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkDiagonal12Highest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 0; row < array.length-1; row++)
        {
            //looping through the cols
            for (int col = 0; col < array[0].length - 1; col++)
            {
                //if the array at row, col is equal to ONE and array at row+1, col+1 is equal to NEITHER, then..
                if( array[row][col] == Status.ONE && array[row+1][col+1] == Status.NEITHER )
                {
                    //set the result to col+1
                    result = col + 1;
                }//if
            }//for
        }//for
        //return the result accordingly.
        return result;
    }//checkDiagonal12Highest

    //-----------------------------------------------------------
    // checkDiagonal2Highest
    //
    // PURPOSE: This method checks if the player has inserted
    // three matching colours or not diagonally.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkDiagonal2Highest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 3; row < array.length; row++)
        {
            //looping through the cols
            for (int col = 3; col < array[0].length ; col++)
            {
                //if the array at row, col and array at row-1, col-1 and array at row-2, col-2 is equal to ONE and array at row-3, col-3 is equal to NEITHER..
                if(array[row][col] == Status.ONE && array[row-1][col-1] == Status.ONE && array[row-2][col-2] == Status.ONE && array[row-3][col-3] == Status.NEITHER)
                {
                    //set the result to col-3.
                    result = col - 3;
                }//if
            }//for
        }//for
        return result;
    }//checkDiagonal2Highest

    //-----------------------------------------------------------
    // checkDiagonal21Highest
    //
    // PURPOSE: This method checks if the player has inserted
    // two matching colours or not Diagonally.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkDiagonal21Highest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 2; row < array.length; row++)
        {
            //looping through the cols
            for (int col = 2; col < array[0].length ; col++)
            {
                //if the array at row, col and array at row-1, col-1 and array at row-2, col-2 is equal to NEITHER..
                if(array[row][col] == Status.ONE && array[row-1][col-1] == Status.ONE && array[row-2][col-2] == Status.NEITHER)
                {
                    //set the result to col-2
                    result = col - 2;
                }//if
            }//for
        }//for
        //return the result accordingly.
        return result;
    }//checkDiagonal21Highest

    //-----------------------------------------------------------
    // checkDiagonal22Highest
    //
    // PURPOSE: This method checks if the player inserted
    // Diagonally or not.
    // PARAMETERS:
    //     This method accepts one 2D array of type status.
    //-----------------------------------------------------------
    public int checkDiagonal22Highest(Status [][] array)
    {
        //set the result to 0 initially.
        int result = 0;
        //looping through the rows
        for(int row = 1; row < array.length; row++)
        {
            //looping through the cols
            for (int col = 1; col < array[0].length ; col++)
            {
                //if the array at row, col is equal to ONE and array at row-1, col-1 is equal to NEITHER, then..
                if(array[row][col] == Status.ONE && array[row-1][col-1] == Status.NEITHER)
                {
                    //set the result to col-1
                    result = col - 1;
                }//if
            }//for
        }//for
        //return the result accordingly.
        return result;
    }//checkDiagonal22Highest

}//ArtificialIntelligence

//////////////////////END OF ARTIFICIAL INTELLIGENCE CLASS//////////////////////
