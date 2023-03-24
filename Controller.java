package application;

import java.io.IOException;
import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

@SuppressWarnings("serial")
public class Controller implements Serializable{
	int turn=1;
	Integer xIteration=0;
	Integer oIteration=0;
	@FXML
	Label CommentLabel;
	@FXML
	Label turnLabel;
	@FXML
	Button button00;
	@FXML
	Button button01;
	@FXML
	Button button02;
	@FXML
	Button button10;
	@FXML
	Button button11;
	@FXML
	Button button12;
	@FXML
	Button button20;
	@FXML
	Button button21;
	@FXML
	Button button22;
	@FXML
	Button playAgain;
	@FXML
	Button exit;
	@FXML
	Label Xscore;
	@FXML
	Label Oscore;
	Button x=null;
	
	public boolean checkDraw ()
	{
		Button[] buttons = {button00,button01,button02,button10,button11,button12,button20,button21,button22};
		boolean state = true;
		for (int i=0;i<buttons.length;i++)
		{
			if (buttons[i].isDisabled()==false)
				{state=false;
				break;}
		}
		return state;
	}
	
	public String XorO ()
	{
		String result=null;
		if 		((button00.getText() == "O" && button01.getText()=="O" && button02.getText()=="O") ||
				(button10.getText() == "O" && button11.getText()=="O" && button12.getText()=="O")||
				(button20.getText() == "O" && button21.getText()=="O" && button22.getText()=="O")||
				(button00.getText() == "O" && button10.getText()=="O" && button20.getText()=="O")||
				(button01.getText() == "O" && button11.getText()=="O" && button21.getText()=="O")||
				(button02.getText() == "O" && button12.getText()=="O" && button22.getText()=="O")||
				(button00.getText() == "O" && button11.getText()=="O" && button22.getText()=="O")||
				(button02.getText() == "O" && button11.getText()=="O" && button20.getText()=="O"))
			result=("O");
		else if ((button00.getText() == "X" && button01.getText()=="X" && button02.getText()=="X") ||
				(button10.getText() == "X" && button11.getText()=="X" && button12.getText()=="X")||
				(button20.getText() == "X" && button21.getText()=="X" && button22.getText()=="X")||
				(button00.getText() == "X" && button10.getText()=="X" && button20.getText()=="X")||
				(button01.getText() == "X" && button11.getText()=="X" && button21.getText()=="X")||
				(button02.getText() == "X" && button12.getText()=="X" && button22.getText()=="X")||
				(button00.getText() == "X" && button11.getText()=="X" && button22.getText()=="X")||
				(button02.getText() == "X" && button11.getText()=="X" && button20.getText()=="X"))
			result=("X");
		return result;
	}
	
	public void checkWin (ActionEvent event) throws IOException, InterruptedException
	{
		Button[] buttons = {button00,button01,button02,button10,button11,button12,button20,button21,button22};
		String win = null;
		win=(XorO());
		if (win==("O"))
		{
			turnLabel.setText("O wins!");
			oIteration++;
			Oscore.setText(oIteration.toString());
			for (int i=0;i<buttons.length;i++)
			{
				buttons[i].setDisable(true);
				buttons[i].setOpacity(1);
			}
			
		}
		else if (win==("X"))
		{
			turnLabel.setText("X wins!");
			xIteration++;
			Xscore.setText(xIteration.toString());
			for (int i=0;i<buttons.length;i++)
			{
				buttons[i].setDisable(true);
				buttons[i].setOpacity(1);
			}
		}
	}
	
	public void XTurn ()
	{
		if (checkDraw()==false)
			turnLabel.setText("X to play");
		else
			turnLabel.setText("It's a draw!");
	}
	public void OTurn()
	{
		if (checkDraw()==false)
			turnLabel.setText("O to play");
		else
			turnLabel.setText("It's a draw");
	}
	
	private void comment()
	{
		int number = (int)Math.floor(Math.random() * (5) + 1);
		if (number==1)
		CommentLabel.setText("Good move!");
		else if (number==2)
		CommentLabel.setText("Super!");
		else if (number==3)
			CommentLabel.setText("Well played!");
		else if (number==4)
			CommentLabel.setText("Great!");
		else if (number==5)
			CommentLabel.setText("Amazing!");
	}
	
	public void Action00 (ActionEvent e)throws IOException, InterruptedException
	{
		x=(Button)e.getSource();
		comment();
		if (turn %2 == 1) {
		x.setText("X");
		x.setTextFill(Color.RED);
		x.setDisable(true);
		OTurn ();
		checkWin(e);
		turn++;}
		else {
		x.setText("O");
		x.setTextFill(Color.BLUE);
		x.setDisable(true);
		XTurn ();
		checkWin(e);
		turn++;
		}
		x.setOpacity(1);
	}

	public void exit (ActionEvent event) throws IOException
	{
		javafx.application.Platform.exit();
	}
	
	public void restartGame (ActionEvent event) throws IOException
	{
		Button[] buttons = {button00,button01,button02,button10,button11,button12,button20,button21,button22};
		for (int i=0;i<buttons.length;i++)
		{
			buttons[i].setText("");
			buttons[i].setDisable(false);
		}
		turnLabel.setText("X to play");
		CommentLabel.setText("");
		turn=1;
		
	}
}
