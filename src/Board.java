import java.util.Random;
public class Board {
	
	static Boardpiece[][] board;
	static int rows=10;
	static int columns=10;
	
	//player object
	static Player p = new Player(0, 0);
	
	//Goal object
	Boardpiece g;
	
	//list of Traps on the board
	Trap[] traps;
	
	//list of Monsters on the board
	Monster[] monsters;
	
	int score=0;
	int difficulty=100;
	int bonusScore = 0;
	
	Random rnd = new Random();
	
	RNJesus J = new RNJesus();
	
	public Board() {
		board = new Boardpiece[10][10];
	}
	
	public Board(int r, int c){
		board = new Boardpiece[r][c];
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return columns;
	}
	
	public int getPlayerRow(){
		return p.getRow();
	}
	
	public int getPlayerColumn(){
		return p.getCol();
	}
	
	public boolean isOpen(int r, int c){
		if(board[r][c] == null ) { return true; }
		else { return false; }
	}
	
	public String getValAt(int r, int c){
		return board[r][c].getVal();
	}
	
	
	
	
	
	public void createBoard(){
		
		p.updateLocation(rnd.nextInt(rows), rnd.nextInt(columns));
		
		board[p.getRow()][p.getCol()] = p;
		
		boolean plantingGold = true;
		while(plantingGold){
			int r = rnd.nextInt(rows);
			int c = rnd.nextInt(rows);
			
			if( board[r][c] == null && Math.abs(r-p.getRow()) >= 4 && Math.abs(c-p.getCol()) >= 4 ){
				g = new Boardpiece(r, c, "G");
				board[r][c] = g;
				plantingGold = false;
			}
		}
		
		
		createTraps();
		createMonsters();
		
		
		
		
	}
	
	void createTraps(){
		createTraps(J.getNum(difficulty));
	}
	
	void createTraps(int n){
		boolean settingTrap;
		
		traps = new Trap[n];
		
		//Iterate through num of traps to place while validating location
		for(int i = 0; i < n; i++){
			settingTrap = true;
			while(settingTrap){
				int r = rnd.nextInt(rows);
				int c = rnd.nextInt(rows);
				if(board[r][c] == null){
					traps[i] = new Trap(r, c);
					board[r][c] = traps[i];
					settingTrap = false;
				}
			}
		}
	}
	
	void createMonsters(){
		createMonsters(J.getNum(difficulty));
	}
	
	void createMonsters(int n){
		boolean settingMonsters;
		
		monsters = new Monster[n];
		
		//Iterate through num of monsters to place while validating location
		for(int i = 0; i < n; i++){
			settingMonsters = true;
			while(settingMonsters){
				int r = rnd.nextInt(rows);
				int c = rnd.nextInt(rows);
				if(board[r][c] == null){
					//missing RNG for monster difficulty
					//no alternate difficulty monsters
					monsters[i] = new Monster(r, c);
					board[r][c] = monsters[i];
					settingMonsters = false;
				}
			}
		}
	}
	
	public void moveMonsters(){
		for(int i=0; i<monsters.length; i++){
			int oldr = monsters[i].getRow();
			int oldc = monsters[i].getCol();
			
			int act = monsters[i].move(this);
			
			if(act==1){
				board[monsters[i].getRow()][monsters[i].getCol()] = monsters[i];
				board[oldr][oldc] = null;
			}
			else if(act==0){
				 System.out.println("The foolish monster fell into the trap");
				 board[oldr][oldc] = null;
				 monsters[i] = null;
			}
			else if(act==-1){
				 System.out.println("You were attacked by the monster");
				 board[oldr][oldc] = null;
				 p.takeDamage(monsters[i].health());
				 monsters[i] = null;
			}
			else if(act==-2){
				System.out.println("A monster is blocked, you got lucky");
			}
		}
		//cleanMonsters();
	}
	
	//implement a class to clean up the monster list to prevent call errors after death
	void cleanMonsters(){
		
	}
	
	public void display(){
		for(int i=0; i<rows; i++){
			for(int j=0; j<columns; j++){
				if(board[i][j] == null){
					System.out.print("*");
				}
				else {
					System.out.print(board[i][j].getVal());
				}
			}
			System.out.println("");
		}
	}
	
public static void main(String[] args) {
		System.out.println("Game Start");
		System.out.println("Welcome to Get the Gold");
		System.out.println("Move the player at the prompt with WASD");
		
		Board b = new Board();
		b.createBoard();
		b.display();
		p.move(b);
		
}
	
}
