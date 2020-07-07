//I have not given nor recieved any unauthorized aid on this assignment
import java.util.ArrayList;

public class Word implements Comparable<Word>{
	
	private String text = "";
	private ArrayList<String> fileList = new ArrayList<String>();
	
	private Boolean directoryToggle = false;//Boolean that stores whether Word is the name of a directory
	private Boolean fileToggle = false;//Boolean that stores whether Word is the name of a file

	public void setText(String Word)
	{
		text = Word;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setFileList(ArrayList<String> list)
	{
		fileList = list;
	}
	
	public ArrayList<String> getFileList()
	{
		return fileList;
	}
	
	public ArrayList<String> add(String f)
	{
		 fileList.add(f);
		 return fileList;
	}
	
	
	public void toggleFile()//used when identifying a file name
	{
		fileToggle = true;
	}
	
	public void toggleDirectory()//used when identifying a directory name
	{
		directoryToggle = true;
	}
	
	public boolean getFileBool()
	{
		return fileToggle;
	}
	
	public boolean getDirectoryBool()
	{
		return directoryToggle;
	}

	
	@Override
	public int compareTo(Word anotherWord) 
	{
		return this.getText().compareTo(anotherWord.getText());//compares Word objects according to the text stored
	}
}

