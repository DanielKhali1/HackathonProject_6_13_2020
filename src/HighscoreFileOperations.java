import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class HighscoreFileOperations 
{
	static public String PATH = "scores.txt";
	
	static public void saveToScores(String name, int score) throws Exception
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(PATH), true));
		bw.append(name + "," + score+"\n");
		bw.close();
	}

	 static public ArrayList<Record> grabHighScores() throws Exception
	 {
		 

		 
		 ArrayList<Record> arlist = new ArrayList<Record>();
		 BufferedReader br = new BufferedReader(new FileReader(new File(PATH)));
		 
		 String line;
		 
		 while( ( line = br.readLine()) != null)
		 {
			 arlist.add(new Record(line.split(",")[0], Integer.parseInt(line.split(",")[1])));
		 }
		 br.close();
		 System.out.println(arlist);
		 Collections.sort(arlist, Collections.reverseOrder());
		 System.out.println(arlist);
		 return arlist;
	 }
	 
	 static public void clearFile() throws Exception
	 {
		 BufferedWriter bw = new BufferedWriter(new FileWriter(new File(PATH), true));bw.write("");bw.close();
	 }
	 
//	 public static void main(String[] args) throws Exception
//	 {
//		 
//		 HighscoreFileOperations.saveToScores("Danny", 123213);
//		 HighscoreFileOperations.saveToScores("Luke", 123213);
//		 HighscoreFileOperations.saveToScores("Vivian", 123213);
//		 HighscoreFileOperations.saveToScores("Felisha", 123213);
//		 
//		 ArrayList<Record> s = grabHighScores();
//		 
//		 for(int i = 0; i < s.size(); ++i)
//		 {
//			 System.out.println(s.get(i).name + " " + s.get(i).score);
//		 }
//		 
//	 }
}


class Record implements Comparable <Record>
{
	public String name;
	public int score;
	
	public Record(String name, int score)
	{
		this.name = name;
		this.score = score;
	}

	@Override
	public int compareTo(Record o) 
	{
		return this.score- o.score;
	}
	
	public String toString()
	{
		return name + " " + score;
	}
}