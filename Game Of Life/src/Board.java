import java.awt.List;
import java.util.ArrayList;

public class Board {

	
	Unit[][] GameBoard;
	Unit[][] NextTurnBoard;
	int BoardLength;
	int BoardWidth;
	
	
	public Board(int length, int width) {
		SetBoardSize(length, width);
		CreateGameBoard();
		SetGameBoard();
		SetInitialUnits();
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
			}
		}
	
	for(int i=0; i<BoardLength; i++){
		for(int j=0; j<BoardWidth; j++){
			NextTurnBoard[i][j]=new Unit();
			}
		}
}
void SetInitialUnits(){
	GameBoard[10][11].UnitAlive();
	GameBoard[10][12].UnitAlive();
	GameBoard[11][10].UnitAlive();
	
	GameBoard[12][13].UnitAlive();
	GameBoard[13][11].UnitAlive();
	GameBoard[13][12].UnitAlive();
	
	
}
Boolean CheckConditions(int x, int y){
	
	int NeighborNumber=0;
	Boolean isAliveLater=false;
	Boolean WasAlive=GameBoard[x][y].isAlive();
	
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
	return isAliveLater;
}		
 void NextTurn(){
	 for(int i=1; i<BoardLength-1; i++){
			for(int j=1; j<BoardWidth-1; j++){
				if(CheckConditions(i,j))
					NextTurnBoard[i][j].UnitAlive();
				else
					NextTurnBoard[i][j].UnitDie();
			}
			}
	 
	 
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
