package System;


import java.io.File;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class StudentGetter {

DBGetter adder;
	
	public StudentGetter(DBGetter adder)
	{
		this.adder = adder;
	}
	
	public void run () 
	{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = null;
		try 
		{
			xmlFile = new File("data/studentDB.xml");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to read the xml file.");
		}
		
		try
		{
			Document document = (Document) builder.build(xmlFile);
			Element root = document.getRootElement();
			Element studentData = root.getChild("Students");
			
			
		   List studentList = studentData.getChildren("Student");
			
			for(int i=0; i < studentList.size(); i++)
			{
				Element student = (Element) studentList.get(i);
				String name = student.getChildText("name");
				String surname = student.getChildText("surname");
				String username = student.getChildText("username");
				String password = student.getChildText("password");
					
				Student stud = new Student(name,surname,username,password);
				
				adder.addStudent(stud);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Unable to parse student data.");
		}
	}
}
