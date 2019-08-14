
public class Boardpiece {
	String Value = " ";
	int row;
	int col;
	
	public Boardpiece(int r, int c) {
		row = r;
		col = c;
	}
	
	public Boardpiece(int r, int c, String v) {
		row = r;
		col = c;
		Value = v;
	}
	
	int getRow() { return row; }
	int getCol() { return col; }
	
	void setRow(int r) { row=r; }
	void setCol(int c) { col=c; }
	
	void updateLocation(int r, int c){
		row = r;
		col = c;
	}
	
	
	String getVal(){
		return Value;
	}
	protected void setVal(String v){
		Value = v;
	}
}