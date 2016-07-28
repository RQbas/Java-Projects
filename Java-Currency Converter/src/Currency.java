
public class Currency {
	private String name;
	private double converter;
	private String code;
	private double rate;
	
	public void setName(String DOMname){
		name=DOMname;
	}
	
	public void setConverter(String DOMconverter){
	converter=Double.parseDouble(DOMconverter);
	}
	
	public void setCode(String DOMcode){
	code=DOMcode;
}
	
	public void setRate(String DOMrate){
		String ReplaceToDot;
	ReplaceToDot=DOMrate.replaceAll(",", ".");
	rate=Double.parseDouble(ReplaceToDot);
	}	
	
	public String getName(){
		return name;
	}
	public String getCode(){
		return code;
	}
	public double getRate(){
		return rate;
	}
	public String getConverter(){

		return Integer.toString((int)converter);
	}
	public void display(){
		System.out.println("Name: "+name+" Converter: "+converter+" Code: "+code+" Rate: "+rate);
	}
}
