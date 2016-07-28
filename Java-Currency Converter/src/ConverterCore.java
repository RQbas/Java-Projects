
public class ConverterCore{
	SourceXML SourceObject;
	int CurrencyN;
	
	public ConverterCore(){
		getSource();
	}
	
public void getSource(){
	SourceObject = new SourceXML();
	CurrencyN = SourceObject.CurrencyNumber;
}
public String DisplayLeftSide(int i){
	String Result=SourceObject.CurrencyArray[i].getConverter()+" "+SourceObject.CurrencyArray[i].getCode();
	return Result;
}
public String DisplayRightSide(int i){
	double CurrencyValue;
	String Result;
	CurrencyValue=SourceObject.CurrencyArray[i].getRate();
	Result=Double.toString(round(CurrencyValue, 4));
	Result+=" PLN";
	return Result;
}
public String getCode(int i){
	String result;
	result=SourceObject.CurrencyArray[i].getCode();
	return result;
}
public String getName(int i){
	String result;
	result=SourceObject.CurrencyArray[i].getName();
	return result;
}
public double getRate(int i){
	double result;
	result=SourceObject.CurrencyArray[i].getRate();
	return result;
}
public double getConverter(int i){
	double result;
	result=Double.parseDouble(SourceObject.CurrencyArray[i].getConverter()); 
	return result;
}
public Object[] ConverterCombination(){
	Object[] Array= new Object[5];
	return Array;
}

public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}
public double[][] setRatioTable(double[][] RatioTable){
		RatioTable= new double[CurrencyN+1][CurrencyN+1];
		for(int i=1; i<CurrencyN+1; i++){
			for(int j=1; j<CurrencyN+1; j++){
				RatioTable[i][j]=(getRate(i-1)/getConverter(i-1))/(getRate(j-1)/getConverter(j-1));
			}
		}
		return RatioTable;
}

	
}
