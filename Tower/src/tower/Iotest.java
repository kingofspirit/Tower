/*Text t = new Text("aaiaaiaiaiaiaiia");
		editorPane.getChildren().addAll(t);
		editorPane.setOnMouseMoved(e -> {
			t.setX(e.getX());
			t.setY(e.getY());
		});
		*/
		package tower;

import java.io.*;
	import java.util.Scanner;

import javax.swing.JFileChooser;

import javafx.scene.text.Text;
	public class Iotest {
		

		


		public static int inputamount() {
			int a = 10;
			return a;
			
			
		}
		
		public static void main(String[] args) throws FileNotFoundException {
			int input;
			input = inputamount();
			int[][] num = new int[input][input];
			
			int u = 1;
			
			for(int i = 0; i < 10; i++)
				for(int j = 0; j < 10; j++)
					{num[i][j] = u++;
					//System.out.println(num[i][j]);
					}
		
			String s = "e:\\qweq.txt";
			
			ioo(num);
			ooi();
			
		}
		
		public static void ioo(int q[][]) throws FileNotFoundException {
			
			JFileChooser savingFile = new JFileChooser();

			if(savingFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				File f = savingFile.getSelectedFile();
			
			
			PrintWriter w = new PrintWriter(f);
			
			for(int i = 0; i < 10; i++)
				for(int j = 0; j < 10; j++)
					{w.println(q[i][j]);
					
					}
			w.close();
			}
		}
		
		
	public static void ooi() throws FileNotFoundException {
		
		JFileChooser jFileChooser = new JFileChooser();

		if(jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = jFileChooser.getSelectedFile();	
		
		
			
			
			Scanner w = new Scanner(file);
			
			while(w.hasNext())
				System.out.print(w.nextInt());
					
				
			w.close();
		}
		}
	}
	

