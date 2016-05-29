import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
public class Store
{
	public static void main(String[] args)
	{
		
		try
		{
		File file = new File("people.txt");
		
			if (!file.exists()) 
				{
				file.createNewFile();
				}

		
			FileWriter wr = new FileWriter(file.getAbsoluteFile(), true); //Convenience class for writing character files (not overwriting since second boolean is true)
			BufferedWriter bwr= new BufferedWriter(wr); //Writes text to a character-output stream
			
			BufferedReader br = new BufferedReader(new FileReader(file)); //Reads text from a character-input stream
			StringTokenizer st;  //tokenizer

			
			
		System.out.println("Welcome to primitive database\n");
		System.out.println("[Name] [Surname] [Sex] [Age] [Height] [Weight]\n");
		boolean loop_var=true; //boolean for loop conditions (menu)
		boolean loop_rec=true; //repetition for input
		boolean implementation=true; //condition for creating objects from file only once
		
		Scanner scan = new Scanner(System.in); // can parse primitive types and strings using regular expressions.
		String input="r"; //we start with read mode in menu
		
		String input_name = null, input_surname=null, input_sex=null, line; //declaring variable used for constructor
		int input_age=0, arrsize, cnt=1, guard=1;
		float input_weight=0, input_height=0;
		
		
		
		ArrayList<Person> people = new ArrayList<Person>(); //array list of objects of class Person
		
		while(loop_var) //MENU LOOP
		{
			if(input.equals("e") || input.equals("r")) //ENTERING DATA OR READING
			{
					if(input.equals("e")) //ENTERING CASE
						{
							while(loop_rec) //LOOP FOR INPUT REPETITION
							{
							System.out.println("Enter name\n"); //USER fills data for new record
								input_name=scan.next();
							System.out.println("Enter surname\n");
								input_surname=scan.next();
							System.out.println("Enter age\n");
								input_age=scan.nextInt();
							System.out.println("Enter sex\n");
								input_sex=scan.next();
							System.out.println("Enter weight\n");
								input_weight=scan.nextFloat();
							System.out.println("Enter height\n");
								input_height=scan.nextFloat();
							
							people.add(new Person(input_name, input_surname, input_sex, input_age, input_weight, input_height)); //add new object to heap
							arrsize=(people.size()-1);
							people.get(arrsize).fill(file, bwr); //add new-created object to file
							
				
							System.out.println("Enter another one? (y/n) \n");
								input=scan.next();
							
							
								if(input.equals("y")) //IF WE WANT ANOTHER ONE (COMING BACK BEFORE CONDITION
								{
									input="e";
								}
								else //WE DONT WANT ANOTHER ONE
								{
									loop_rec=false; //BREAKING REPETITION LOOP
									System.out.println("Enter record (e) or Read record (r) or Exit (x)");
									input=scan.next();
									if(input.equals("x"))
									{
										bwr.close();
										System.exit(0);
									}
									if(input.equals("e") && input.equals("x")) //going back to inserting records
										loop_rec=true; //make repetition loop working again
								}	
							}
						}
						else //read mode
						{
							while((line=br.readLine())!=null) //not end of file
							{
								st=new StringTokenizer(line); //divide into parts obtained line
								while (st.hasMoreTokens())
								{
								if(implementation) //if it's first calling of reading mode
								{
									switch(cnt) //filling variables with tokens
									{case 1:
										input_name=st.nextToken();
										break;
									case 2:
										input_surname=st.nextToken();
										break;
									case 3:
										input_sex=st.nextToken();
										break;
									case 4:
										input_age=Integer.parseInt(st.nextToken());
										break;
									case 5:
										input_height=Float.parseFloat(st.nextToken());
										break;
									case 6:
										input_weight=Float.parseFloat(st.nextToken());
										break; 
									}
								}
									cnt++;
								}
								
								if(guard%2==1)
									people.add(new Person(input_name, input_surname, input_sex, input_age, input_weight, input_height)); //create object using data from file
										cnt=1; //cleaning counter
										guard++; //guard used for not duplicating objects	
							}	
						arrsize=people.size(); 
						for(int i=0; i<arrsize; i++)
							people.get(i).show(); //show each person from file
						
						System.out.println("Enter additional record? (y/n) or Calculations display (c) or Delete (d) \n");
						input=scan.next();
							if(input.equals("y")) //IF WE WANT ANOTHER ONE (COMING BACK BEFORE CONDITION)
							{
								input="e"; //enter records mode
								loop_rec=true; //enter records mode
								implementation=false; //to prevent overwriting already existing objects
							}	
						else  //If we dont want to add new record
							{
								if(input.equals("c")) //User chooses calculations
								{
									System.out.println("Average age: "+Store.AvgAge(people)+"\n"); //methods processing given data
									System.out.println("Average height: "+Store.AvgHeight(people)+"\n");
									System.out.println("Average weight: "+Store.AvgWeight(people)+"\n");
									Store.BMI(people);
									System.out.println("Highest BMI: "+ people.get(Store.HighestBMI(people)).name+" "+people.get(Store.HighestBMI(people)).surname);
									System.out.println("Highest Person: "+ people.get(Store.HighestP(people)).name+" "+people.get(Store.HighestP(people)).surname);
									System.out.println("Heaviest Person: "+ people.get(Store.HeaviestP(people)).name+" "+people.get(Store.HeaviestP(people)).surname);
									System.out.println("Lightest Person: "+ people.get(Store.LightestP(people)).name+" "+people.get(Store.LightestP(people)).surname);
									bwr.close(); //close stream
									System.exit(0); //leave program
								}
								if(input.equals("d"))
								{
									bwr.flush();
									System.out.println("Which one? (Name)");
										input_name=scan.next();
									System.out.println("(Surname)");
										input_surname=scan.next();
										System.out.println(input_name +" "+input_surname);
									for(int i=0; i<people.size(); i++)
									{
										if((input_name.equals(people.get(i).name)) && (input_surname.equals(people.get(i).surname)))
										{
											people.remove(i);
										}
									}
									PrintWriter pw= new PrintWriter(file);
									pw.close();
									Store.fillwhole(file, bwr, people);
									input="r"; 
									loop_rec=true; 
									implementation=false;
									
								}
								if(input.equals("n")) //if it's not calculation mode -> it was "no" -> we leave program
								{	
									bwr.close();
									System.exit(0);
								}
							}	
						}
			}
			else //User selected neither entering mode nor read mode
			{
		System.out.println("Unknown command\n");
		System.out.println("Enter record (e) or Read record (r)"); //Another chance for user
			input=scan.next();
			}
		
		}
		}
		catch(IOException x) //Exception if there's something wrong with file
		{
			x.printStackTrace();
		}
	
	}
static float AvgAge(ArrayList<Person> tmp)
	{
		float avg=0;
		for(int i=0; i<tmp.size(); i++)
			avg+=tmp.get(i).age;
			return (avg/tmp.size());
		}
static float AvgHeight(ArrayList<Person> tmp)
{
	float avg=0;
	for(int i=0; i<tmp.size(); i++)
		avg+=tmp.get(i).height;
		return (avg/tmp.size());
}
static float AvgWeight(ArrayList<Person> tmp)
{
	float avg=0;
	for(int i=0; i<tmp.size(); i++)
		avg+=tmp.get(i).weight;
		return (avg/tmp.size());
}
static void BMI(ArrayList<Person> tmp)
{float h, w;
	
	for(int i=0; i<tmp.size(); i++)
	{
		w=tmp.get(i).weight;
		h=tmp.get(i).height/100;
	System.out.println(tmp.get(i).name+" "+tmp.get(i).surname+" "+w/(h*h));
	}
	System.out.println("\n");
}

static int HighestBMI(ArrayList<Person> tmp)
{
	float h, w, current, highest=0;
	int cnt=0;
	for(int i=0; i<tmp.size(); i++)
	{
	w=tmp.get(i).weight;
	h=tmp.get(i).height/100;
		current=w/(h*h);
		if(current>highest)
			{highest=current;
			cnt=i;
			}
		}
	return cnt;
}
static int HighestP(ArrayList<Person> tmp)
{
	float current, highest=0;
	int cnt=0;
	for(int i=0; i<tmp.size(); i++)
	{
	current=tmp.get(i).height;
		if(current>highest)
			{highest=current;
			cnt=i;
			}
		}
	return cnt;
}
static int HeaviestP(ArrayList<Person> tmp)
{
	float current, highest=0;
	int cnt=0;
	for(int i=0; i<tmp.size(); i++)
	{
	current=tmp.get(i).weight;
		if(current>highest)
			{highest=current;
			cnt=i;
			}
		}
	return cnt;
}

static int LightestP(ArrayList<Person> tmp)
{
	float current, lowest=tmp.get(0).weight;
	int cnt=0;
	for(int i=1; i<tmp.size(); i++)
	{
		current=tmp.get(i).weight;
		if(current<lowest)
			{lowest=current;
			cnt=i;
			}
		}
	return cnt;
}

static void fillwhole(File file, BufferedWriter bwr, ArrayList<Person> tmp)
{
	try
	{
		for(int i=0; i<tmp.size(); i++ )
		{
	bwr.write(tmp.get(i).name+"  "+tmp.get(i).surname+"  "+tmp.get(i).sex+"  "+String.valueOf(tmp.get(i).age) +"  "+String.valueOf(tmp.get(i).height)+"  "+String.valueOf(tmp.get(i).weight)+"\n");
	bwr.newLine();
		}
	}
	catch(IOException x)
	{
		x.printStackTrace();
	}
}

}
