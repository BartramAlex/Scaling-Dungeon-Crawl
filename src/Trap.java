
public class Trap extends Boardpiece{
	int health;

	public Trap(int x, int y) {
		super(x, y, "T");
		health = 1;
	}
	
	public Trap(int x, int y, int damage) {
		super(x, y, "T");
		health = damage;
	}
	
	int health(){
		return health;
	}
}
