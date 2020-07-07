//I have not given or received any unauthorized help on this assignment
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FileSearcherExtra {
	
	static BinarySearchTree<Word> tree = new BinarySearchTree<Word>();

	public static void main(String[] args) throws IOException {
		
		File directory = new File(args[0]);
		Scanner scan = new Scanner(System.in);
		int end = 0 ;//integer that changes when user decides to quit
		
		while (end == 0)
		{
			scanFiles(directory);
			
			System.out.println("Please enter a command (a, s, or q)>> ");
			String choice = scan.nextLine();
			
			if (choice.equals("a"))
			{
				choiceA();
			}
			
			if (choice.equals("s"))
			{
				choiceS(scan);
			}
			
			if (choice.equals("q"))
			{
				end = 1;
			}
			
		}
		System.out.print("Bye Bye Birdie!");//reference to my favorite show mad men
		
		scan.close();
		
	}
	
	public static void choiceA()
	{
		while (!tree.isEmpty())
		{
			Word item = tree.findMin();
			
			System.out.println("word: " + item.getText());
			
			if (item.getDirectoryBool() == true)
			{
				System.out.println("directory names containing " + item.getText() + ": " + item.getText());
			}
			if (item.getFileBool() == true)
			{
				System.out.println("file names containing " + item.getText() + ": " + item.getText());
			}
			
			if (!item.getFileList().isEmpty())
			{
				ArrayList<String> fileArr = item.getFileList();
				String fileString = "";
				
				for(int i = 0; i <fileArr.size(); i++)
				{
					fileString += fileArr.get(i) + " ";
				}
				
				System.out.println("text files containing " + item.getText() + ": " + fileString);
				
			}
			tree.remove(item);
			
		}
	}
	
	public static void choiceS(Scanner scan)
	{
		System.out.println("Word to find>> ");
		Word wordToFind = new Word();
		wordToFind.setText(scan.nextLine());
		
		if (tree.contains(wordToFind))
		{
			wordToFind = tree.find(wordToFind);
			if (wordToFind.getDirectoryBool() == true)
			{
				System.out.println("directory names containing " + wordToFind.getText() + ": " + wordToFind.getText());
			}
			
			if (wordToFind.getFileBool() == true)
			{
				System.out.println("file names containing " + wordToFind.getText() + ": " + wordToFind.getText());
			}
			
			if (!wordToFind.getFileList().isEmpty())
			{
				ArrayList<String> fileArr =tree.find(wordToFind).getFileList();
				String fileString = "";
				for(int i = 0; i <fileArr.size(); i++)
				{
					fileString += fileArr.get(i) + " ";
				}
				
				System.out.println("files containing " + wordToFind.getText() + ": " + fileString);
			}
			
		}
		else
		{
			System.out.println(wordToFind.getText() + " is not found");
		}
		tree.makeEmpty();
	}
	
	public static void scanFiles(File stuff) throws IOException
	{
		File[] fileList = stuff.listFiles();
		
		for(int i = 0; i < fileList.length; i++)
		{
			File current = fileList[i] ;
			if(!current.isHidden()&& current.getName().charAt(0)!= '.' && current.getName().charAt(0)!= '_')
			{
				if (current.isDirectory())
				{
					//adds directory name to tree
					Word x = new Word();
					x.setText(current.getName());
					x.toggleDirectory();
					tree.insert(x);
					
					scanFiles(current);
				}
				else
				{
					//adds file name to tree
					Word y = new Word();
					y.setText(current.getName());
					if (tree.contains(y))
					{
						Word a = tree.find(y);
						a.toggleFile();
					}
					else
					{
						y.toggleFile();
						tree.insert(y);
					}
					
					addToTree(current);
				}
			}
		}
	}
	
	public static void addToTree(File current) throws IOException
	{
		Scanner filereader = new Scanner(current);
		while(filereader.hasNext())
		{
			String currentString = "";
			String[] parse = filereader.next().split("[\\s\\p{Punct}]+");
			
			for (int i = 0; i < parse.length; i++)
			{
				currentString += parse[i];
			}
			
			Word currentWord = new Word();
			currentWord.setText(currentString);
			
			if (tree.contains(currentWord))
			{
				Word wordInTree = tree.find(currentWord);
				wordInTree.add(current.getName());
			}
			else
			{
				currentWord.add(current.getName());
				tree.insert(currentWord);
			}
		}
		filereader.close();
	}

}
