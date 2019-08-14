import java.util.Scanner;
public class Player extends Monster{
	Scanner scan = new Scanner(System.in);

	public Player(int x, int y) {
		super(x, y);
		setVal("P");
		health = 10;
	}
	
	public Player(int x, int y, int h) {
		super(x, y);
		setVal("P");
		health = h;
	}
	
	public int takeDamage(int damage){
		health -= damage;
		return health;
	}
	
	public int heal(){
		health+=3;
		return health;
	}
	
	public int move(Board b) {
		System.out.println("Enter move: ");
		String s = scan.next();
		
		int newRow=row;
		int newCol=col;
		
		if(s=="W"){
			newRow--;
		}
		else if(s=="A"){
			newCol--;
		}
		else if(s=="S"){
			newRow++;
		}
		else if(s=="D"){
			newCol++;
		}
		else{
			System.out.println("Invalid input");
			return move(b);
		}
		
		int cond = moveSub(newRow, newCol, b);
		return cond;
	}
	
	public int moveSub(int r, int c, Board b){
		if(r<0 || r >= b.getRows() || c<0 || c >= b.getColumns()){
			System.out.println("Out of Bounds");
			return move(b);
		}
		if(b.isOpen(r, c)){
			return 1;
		}
		else if(b.getValAt(r, c).equals("T")){
			System.out.println("Ouch you hit a trap");
			return 0;
		}
		else if(b.getValAt(r, c).equals("M")){
			System.out.println("You attacked the Monster");
			return -1;
		}
		else if(b.getValAt(r, c).equals("G")){
			System.out.println("You WIN!");
			return 5;
		}
		
		return -1;
		
	}
	

}
