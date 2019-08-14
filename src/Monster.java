
public class Monster extends Trap{

	public Monster(int x, int y) {
		super(x, y);
		setVal("M");
		health = 2;
	}
	
	public Monster(int x, int y, int damage) {
		super(x, y);
		setVal("M");
		health = damage;
	}
	
	public int move(Board b) {
		int newR = row;
		int newC = col;
		
		if(b.getPlayerRow() < newR){ newR -=1; }
		if(b.getPlayerRow() > newR){ newR +=1; }
		if(b.getPlayerColumn() < newC){ newC -=1; }
		if(b.getPlayerColumn() > newC){ newC +=1; }
		
		//if empty move towards player
		if(b.isOpen(newR, newC)){
			this.updateLocation(newR, newC);
			return 1; // 1 means successful move
		}
		
		else if(b.getValAt(newR, newC) == "T"){
			return 0; // Kill monster
		}
		
		else if(b.getValAt(newR, newC) == "P"){
			return -1; // Kill monster, Hurt player
		}
		
		else{
			return -2; //Don't move. Moving into Gold and other Monsters case
		}
	}
	

}
