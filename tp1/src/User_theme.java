import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class User_theme {
	
	/*protected ArrayList<String> themes;
	protected ArrayList<String> users;
	protected int[][] matrice;
	
	public User_theme(){
		this.themes = new ArrayList<String> ();
		this.users = new ArrayList<String> ();
	}*/
	
	public static void gerer_fichier(String nom) throws IOException{
		ArrayList<String> themes;
		ArrayList<String> users;
		themes = new ArrayList<String> ();
		users = new ArrayList<String> ();
		File f = new File(nom);
		BufferedReader text = new BufferedReader(new FileReader(f));
		
		try {
			while (true){
				String ligne = text.readLine();
				try {
					String[] log = ligne.split(";");
					if (log.length > 2){ 
						if (!users.contains(log[1]) ){
							users.add(log[1]);
						}
						if (!themes.contains(log[2]) ){
							themes.add(log[2]);
						}
					}
				}catch (NullPointerException e ) {throw new EOFException();}
				
				
				
			}
		} catch ( EOFException e){
			text.close();
		}
		/*int[][] matrice = new int[users.size()][themes.size()];
		for ( int i = 0 ; i < users.size();i++){
			for ( int j = 0 ; j < themes.size();j++){
				
			}
		}*/
		Map<String,Map<String,Integer>> res = new HashMap<String,Map<String,Integer>>();
		for ( int i = 0; i < users.size();i++){
			res.put(users.get(i),new HashMap<String,Integer>());
			for ( int j = 0; j<themes.size();j++){
				res.get(users.get(i)).put(themes.get(j), 0);
			}
		}
		text = new BufferedReader(new FileReader(f));
		
		try {
			while (true){
				String ligne = text.readLine();
				try {
					
					String[] log = ligne.split(";");
					if (log.length > 2){ 
						res.get(log[1]).put(log[2], res.get(log[1]).get(log[2])+1);	
					}
				}catch (NullPointerException e ) {throw new EOFException();}
			}
		} catch ( EOFException e){
			text.close();
		}
		File ftheme = new File("themes.txt");
		BufferedWriter the= new BufferedWriter(new FileWriter(ftheme));
		File fuser = new File("users.txt");
		BufferedWriter use= new BufferedWriter(new FileWriter(fuser));
		for ( int i = 0; i < themes.size();i ++){
			the.write(themes.get(i)+"\n");
		}
		for ( int i = 0; i < users.size();i ++){
			use.write(users.get(i)+"\n");
		}
		the.close();
		use.close();
		File fmat = new File("matrice.txt");
		BufferedWriter mat= new BufferedWriter(new FileWriter(fmat));
		for ( int i = 0; i < users.size();i++){
			for ( int j = 0; j<themes.size();j++){
				mat.write(res.get(users.get(i)).get(themes.get(j))+" ");
			}
			mat.write("\n");
		}
		mat.close();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		gerer_fichier(args[0]);

	}

}
