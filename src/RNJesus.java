import java.util.Random;
public class RNJesus {
	Random rnd = new Random();
	
	int scalex;
	int scaley;
	
	int LB=0; 
	int MB;
	int RB = scaley;
	
	public RNJesus() {
		scalex=100;
		scaley=100;
	}
	
	int leftBound(int diff){
		LB = (diff * -1) + 80;
		if(LB < 0){ LB = 0; }
		return LB;
	}
	
	int middleBound(int diff){
		MB = (int)(-(0.1*(diff-50))*(0.1*(diff-50)) + 40);
		if (MB<0){ MB = 0; }
		MB += LB;
		if(MB>scaley){ MB = scaley; } // Keep the value from going over the threshold
		return MB;
	}
	
	int rightBound(int diff){
		return RB;
	}
	
	//this returns the number 1 through 3 -- 1 == easy -- 2 == medium -- 3 == hard
	public int getNum(int diff){
		leftBound(diff);
		middleBound(diff);
		rightBound(diff);
		
		int RNG = rnd.nextInt(101);
		
		if(RNG < LB){
			return 1;
		}
		
		else if(RNG < MB){
			return 2;
		}
		
		else {
			return 3;
		}
	}
}
