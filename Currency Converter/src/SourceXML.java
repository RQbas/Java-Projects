import javax.xml.parsers.DocumentBuilder;
import java.net.URL;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import java.io.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

public class SourceXML {

	String link;
	URL url;
	
	DocumentBuilderFactory DBF;
	DocumentBuilder DB;
	Document DOC; 

	String CurrencyName;
	String CurrencyConverter;
	String CurrencyCode;
	String AverageExchangeRate;
	
	Currency[] CurrencyArray;
	
	int CurrencyNumber;
	
	
	public SourceXML(){
		try{	
			
			setAddress();
			setDocument();
			getDocumentElements();
		
			
			
		}catch(Exception e){}
	}
	public void setAddress(){
		this.link = "http://www.nbp.pl/kursy/xml/a137z160718.xml";  
		
		try{
			this.url = new URL(link);
		}catch(Exception e){}
		
		}
	public void setDocument(){
		try{
			 DBF = DocumentBuilderFactory.newInstance();
			 DB = DBF.newDocumentBuilder();
			 DOC = DB.parse(url.openStream());
		}catch(Exception e){}
	}
	public void getDocumentElements(){
		DOC.getDocumentElement().normalize();
		
		NodeList CurrencyList=DOC.getElementsByTagName("pozycja");	//positioning
		CurrencyNumber=CurrencyList.getLength();
		CurrencyArray= new Currency[CurrencyNumber];
		for(int i=0; i<CurrencyNumber; i++){
			CurrencyArray[i]=new Currency();
			Node CurrencyNode = CurrencyList.item(i); //get currency one by one
			
				if(CurrencyNode.getNodeType()==Node.ELEMENT_NODE){
					
					Element CurrencyElement = (Element)CurrencyNode; //create element to distinguish Name

                    
                    NodeList CurrencyNameList = CurrencyElement.getElementsByTagName("nazwa_waluty");
                    Element CurrencyNameElement = (Element)CurrencyNameList.item(0);
                    NodeList textCNList = CurrencyNameElement .getChildNodes();                 
                    CurrencyArray[i].setName(((Node)textCNList.item(0)).getNodeValue().trim());
                    
                    
                    
                    NodeList ConverterList = CurrencyElement.getElementsByTagName("przelicznik");
                    Element ConverterElement = (Element)ConverterList.item(0);
                    NodeList textConvList = ConverterElement.getChildNodes();
                    CurrencyArray[i].setConverter(((Node)textConvList.item(0)).getNodeValue().trim());
                    
                    NodeList CodeList = CurrencyElement.getElementsByTagName("kod_waluty");
                    Element CodeElement = (Element)CodeList.item(0);
                    NodeList textCodeList = CodeElement.getChildNodes();
                    CurrencyArray[i].setCode(((Node)textCodeList.item(0)).getNodeValue().trim());
                    
                    NodeList RateList = CurrencyElement.getElementsByTagName("kurs_sredni");
                    Element RateElement = (Element)RateList.item(0);
                    NodeList textRateList = RateElement.getChildNodes();
                    CurrencyArray[i].setRate(((Node)textRateList.item(0)).getNodeValue().trim());
                   
				}
		}
	}
	public void DisplayAllCurrency(){
		for(int i=0; i<CurrencyNumber; i++)
			CurrencyArray[i].display();
	}

}
