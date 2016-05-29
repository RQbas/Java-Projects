import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
public class Person 
{
String name;
String surname;
String sex;
int age;
float weight;
float height;

Person(String input_name, String input_srname, String input_sex, int input_age, float input_weight, float input_height )
{
	this.name=input_name;
	this.surname=input_srname;
	this.sex=input_sex;
	this.age=input_age;
	this.weight=input_weight;
	this.height=input_height;
}

void show()
{
	System.out.println(name+"  "+surname+"  "+sex+"  "+age+"  "+height+"  "+weight+"\n");
}
void fill(File file, BufferedWriter bwr)
{
	try
	{
	bwr.write(name+"  "+surname+"  "+sex+"  "+String.valueOf(age)+"  "+String.valueOf(height)+"  "+String.valueOf(weight)+"\n");
	bwr.newLine();
	}
	catch(IOException x)
	{
		x.printStackTrace();
	}
}




}