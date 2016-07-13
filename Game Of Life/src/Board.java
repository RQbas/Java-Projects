import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class Board {

	
	Unit[][] GameBoard; //Used to check conditions for each cell
	Unit[][] NextTurnBoard; //Used to keep new scheme of units
	int BoardLength;
	int BoardWidth;
	
	
	public Board(int length, int width) {
		SetBoardSize(length, width);
		CreateGameBoard();
		SetGameBoard();
	}
	
void SetBoardSize(int x, int y){
	this.BoardLength=x;
	this.BoardWidth=y;
}

void CreateGameBoard(){
	GameBoard=new Unit[BoardLength][BoardWidth];
	NextTurnBoard=new Unit[BoardLength][BoardWidth];
}

void SetGameBoard(){
	for(int i=0; i<BoardLength; i++){
		for(int j=0; j<BoardWidth; j++){
			GameBoard[i][j]=new Unit();
			NextTurnBoard[i][j]=new Unit();
			}
		}
}
int HowManyNeighbors(int x, int y){
	int NeighborNumber=0;
	//Row above actual cell
		if(GameBoard[x-1][y-1].isAlive())
			NeighborNumber++;
		if(GameBoard[x-1][y].isAlive())
			NeighborNumber++;
		if(GameBoard[x-1][y+1].isAlive())
			NeighborNumber++;
		
		//Row of actual cell
		if(GameBoard[x][y-1].isAlive())
			NeighborNumber++;
		if(GameBoard[x][y+1].isAlive())
			NeighborNumber++;
		
		//Row below actual cell
		if(GameBoard[x+1][y-1].isAlive())
			NeighborNumber++;
		if(GameBoard[x+1][y].isAlive())
			NeighborNumber++;
		if(GameBoard[x+1][y+1].isAlive())
			NeighborNumber++;
		
		return NeighborNumber;
}



Boolean CheckConditions(int x, int y){
	
	int NeighborNumber;
	
	NeighborNumber=HowManyNeighbors(x, y);
	Boolean isAliveLater=false;
	Boolean WasAlive=GameBoard[x][y].isAlive();
	
	

	//RULES
	if(NeighborNumber==2 || NeighborNumber==3)
	{	
		if(NeighborNumber==3 && !WasAlive)
		isAliveLater=true;	
		if(WasAlive)
		isAliveLater=true;
	}
	else
		isAliveLater=false;
	
	//Return State
	return isAliveLater;
}		
 void NextTurn(){
	 //Set living units on new board
	 for(int i=1; i<BoardLength-1; i++){
			for(int j=1; j<BoardWidth-1; j++){
				if(CheckConditions(i,j))
					NextTurnBoard[i][j].UnitAlive();
				else
					NextTurnBoard[i][j].UnitDie();
			}
		}
	 //Copy new board to the previous one
	 for(int i=0; i<BoardLength; i++){
			for(int j=0; j<BoardWidth; j++){
			if(NextTurnBoard[i][j].isAlive())
				GameBoard[i][j].UnitAlive();
			else
				GameBoard[i][j].UnitDie();
			}
			}
 }
 
}
