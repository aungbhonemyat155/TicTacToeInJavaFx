package application;

import java.net.URL;
import java.util.*;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.*;

public class TicTacToeApp extends Application{

	TicTacToe t1;
	
	private int cRow,cCol;
	private int size,row=-1,col=-1;
	private boolean w,cWin;
	private ArrayList<Button> buttons=new ArrayList<>();
	
	private Label label,win;
	private TextField btnSize;
	private Button confirm;
	
	private Label tableErr;
	
	private GridPane mainPane,btnPane;
	
	public void createNodes()
	{
		label=new Label("Enter size of Table");
		win=new Label();
		btnSize=new TextField();
		tableErr=new Label();
		confirm=new Button("Confirm");
		confirm.setOnAction(e->
		{
			confirm();
		});
	}
	
	public void confirm()
	{
		size=Integer.parseInt(btnSize.getText());
		if(size<3)
		{
			tableErr.setText("Table size only can be above 2");
		}
		else
		{
			t1=new TicTacToe(size);
			tableErr.setText("");
			for(int i=0;i<size;i++)
			{
				for(int j=0;j<size;j++)
				{
					Button btn=new Button(" ");
					btn.setPrefHeight(50);
					btn.setPrefWidth(50);
					btn.getStyleClass().add("btn");
					buttons.add(btn);
					btnPane.add(btn, j, i);
					btn.setOnAction(e->
					{
						if(t1.isGameOver())
						{
							win.setText("Game Over");
						}
						row=GridPane.getRowIndex(btn);
						col=GridPane.getColumnIndex(btn);
						btn.setText("X");
						boolean w=t1.filling('X', row, col);
						btn.setDisable(true);
						if(w)
						{
							win.setText("You Win the game");
							disableButtons();
		  				}
						else
						{
							do
							{
								cRow=getRandomNumber(0,t1.arrL());
								cCol=getRandomNumber(0,t1.arrL());
							}while(t1.isAvailable(cRow, cCol)==false);
							cWin=t1.filling('O', cRow, cCol);
							Button targetButton=null;
							for (Node node : btnPane.getChildren()) 
							{
							if (GridPane.getRowIndex(node) == cRow && GridPane.getColumnIndex(node) == cCol) 
							    {
							        if (node instanceof Button) 
							        {
							            targetButton = (Button) node;
							            break;
							        }
							    }
							}
							if(targetButton!=null)
							{
								targetButton.setText("O");
								targetButton.setDisable(true);
							}
							if(cWin)
							{
								win.setText("Computer Win the game");
								disableButtons();
							}
						}
					});
				}
			}
		}
	}
	 public static int getRandomNumber(int min, int max) 
	  {
	        Random random = new Random();
	        return random.nextInt((max - min) + 1) + min;
	  }
	
	public void disableButtons()
	{
		for(Button button:buttons)
		{
			button.setDisable(true);
		}
	}
	
	public void layouts()
	{
		btnPane=new GridPane();
		btnPane.setHgap(15);
		btnPane.setVgap(15);
		mainPane=new GridPane();
		mainPane.setHgap(15);
		mainPane.setVgap(15);
		mainPane.add(label, 0, 0);
		mainPane.add(btnSize, 0, 1);
		mainPane.add(confirm, 1, 1);
		mainPane.add(tableErr, 0, 2);
		mainPane.add(btnPane, 0, 3);
		mainPane.add(win,0,4);
	}
	public void createTable()
	{
		
	}
	
	public void setStyle()
	{
		tableErr.getStyleClass().add("err");
		confirm.getStyleClass().add("confirm");
	}
	
	public void start(Stage stage) throws Exception {
		
		createNodes();
		layouts();
		Scene sc=new Scene(mainPane,500,500);
		URL url=this.getClass().getResource("TicTacToe.css");
		sc.getStylesheets().add(url.toExternalForm());
		setStyle();

		stage.setScene(sc);
		stage.setTitle("Tic Tac Toe");
		stage.show();
	}
	
	public static void main(String args[])
	{
		Application.launch(args);
	}

}
