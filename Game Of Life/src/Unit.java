
public class Unit {
	private Boolean Alive;
	private Boolean Dead;
			Boolean CheckState;
		
	void UnitDie(){
		this.Dead=true;
		this.Alive=false;
	}
	void UnitAlive(){
		this.Alive=true;
		this.Dead=false;
	}
	
	Boolean isAlive(){
		if(Alive==true)
			CheckState=true;
		else
			CheckState=false;
		return CheckState;
	}
	
	public Unit() {
	UnitDie();
	}
	


}
