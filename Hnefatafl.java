//Name: Ashwin Sivabalan
//Date: Jan 22 2024
//Purpose: Hnefatafl
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.io.*;
public class Hnefatafl extends Applet implements ActionListener
{
    //For screens
    Panel p_card;
    Panel card1, card2, card3, card4, card5;
    CardLayout cdLayout = new CardLayout ();
    JTextField txtName, txtName2;
    String picname = "name";
    //Game screen
    JLabel turnPic;
    int last = -1;
    int turn = 2;
    //grid
    int row = 11;
    int col = 11;
    //determines how many times players 1 and 2 have moved, needed for the superpowers
    int moves1 = 0;
    int moves2 = 0;
    //the amount of times a player has to move to unlock a power up is determined by these variables.
    int p1 = 7;
    int p2 = 7;
    //variable for powerups, 0 is nothing, 1 is diagonal movement, 2 is instant kill, 3 is wall
    int power = 0;
    //variable for counting how many rounds the wall has been on the board
    int wallcounter = 0;
    JButton a[] = new JButton [row * col];

    //Formatting
    Dimension boardSquare = new Dimension (50, 50);

    //3=corner, 0=nothing
    int base[] [] = {{3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}};

    //0=nothing, 1=attacker, 2=defenders, 9=wall
    int people[] [] = {{0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
	    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1},
	    {1, 0, 0, 0, 2, 2, 2, 0, 0, 0, 1},
	    {1, 1, 0, 2, 2, 2, 2, 2, 0, 1, 1},
	    {1, 0, 0, 0, 2, 2, 2, 0, 0, 0, 1},
	    {1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
	    {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}};
    // 4=viking, 5=king, x=nothing, 8=viking with instant kill
    int what[] [] = {{0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0},
	    {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4},
	    {4, 0, 0, 0, 4, 4, 4, 0, 0, 0, 4},
	    {4, 4, 0, 4, 4, 5, 4, 4, 0, 4, 4},
	    {4, 0, 0, 0, 4, 4, 4, 0, 0, 0, 4},
	    {4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0},
	    {0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0}};

    //6=unselected, 7=selected
    int select[] [] = {{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
	    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
	    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
	    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
	    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
	    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
	    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
	    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
	    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
	    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
	    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}};

    //arrays for reseting the game
    int based[] [] = {{3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}};
    int peopled[] [] = {{0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1},
	    {1, 0, 0, 0, 2, 2, 2, 0, 0, 0, 1}, {1, 1, 0, 2, 2, 2, 2, 2, 0, 1, 1}, {1, 0, 0, 0, 2, 2, 2, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}};
    int whatd[] [] = {{0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4},
	    {4, 0, 0, 0, 4, 4, 4, 0, 0, 0, 4}, {4, 4, 0, 4, 4, 5, 4, 4, 0, 4, 4}, {4, 0, 0, 0, 4, 4, 4, 0, 0, 0, 4}, {4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0}, {0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0}};

    public void init ()
    {
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	//screens
	opening ();
	instructions ();
	instructions2 ();
	gameScreen ();
	resize (660, 760);
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    }


    public void opening ()
    { //displays opening screen
	card1 = new Panel ();
	JButton title = new JButton (createImageIcon ("open.png"));
	title.setBorderPainted (false);
	title.setActionCommand ("card2");
	title.addActionListener (this);
	card1.add (title);
	p_card.add ("1", card1);
    }


    public void instructions ()
    { //displays game first instructions screen
	card2 = new Panel ();
	JButton gameScreen = new JButton (createImageIcon ("instruct.png"));
	gameScreen.setActionCommand ("card5");
	gameScreen.addActionListener (this);
	gameScreen.setPreferredSize (new Dimension (660, 760));
	gameScreen.setBorderPainted (false);
	card2.add (gameScreen);
	p_card.add ("2", card2);
    }


    public void instructions2 ()
    { //displays game second instructions screen
	card5 = new Panel ();
	JButton gameScreen = new JButton (createImageIcon ("instruct2.png"));
	gameScreen.setActionCommand ("card4");
	gameScreen.addActionListener (this);
	gameScreen.setPreferredSize (new Dimension (660, 760));
	gameScreen.setBorderPainted (false);
	card5.add (gameScreen);
	p_card.add ("5", card5);
    }



    public void gameScreen ()
    { //Displays game
	card4 = new Panel ();
	JLabel title = new JLabel (createImageIcon ("gametitle.png"));
	card4.setBackground (new Color (0, 74, 173));
	Panel p = new Panel ();
	//displays whos turn it is
	JLabel curturn = new JLabel ("Current Turn:");
	curturn.setFont (new Font ("Arial", Font.PLAIN, 30));
	curturn.setForeground (Color.white);
	turnPic = new JLabel (createImageIcon ("0246.png"));
	p.add (curturn);
	p.add (turnPic);

	//Set up grid
	Panel p2 = new Panel (new GridLayout (row, col));
	int m = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {

		setpics (m);
		a [m].setPreferredSize (boardSquare);
		a [m].addActionListener (this);
		a [m].setBorder (null);
		a [m].setActionCommand ("" + m);
		p2.add (a [m]);
		m++;
	    }
	}

	//Game buttons
	Panel p3 = new Panel ();
	JButton save = new JButton ("Save");
	save.addActionListener (this);
	save.setActionCommand ("Save");
	save.setBackground (new Color (153, 217, 234));
	p3.add (save);
	JButton open = new JButton ("Open");
	open.addActionListener (this);
	open.setActionCommand ("open");
	open.setBackground (new Color (153, 217, 234));
	p3.add (open);
	JButton instruct = new JButton ("Instructions");
	instruct.addActionListener (this);
	instruct.setActionCommand ("card2");
	instruct.setBackground (new Color (153, 217, 234));
	p3.add (instruct);
	JButton reset = new JButton ("Reset");
	reset.addActionListener (this);
	reset.setActionCommand ("reset");
	reset.setBackground (new Color (153, 217, 234));
	p3.add (reset);

	card4.add (title);
	card4.add (p);
	card4.add (p2);
	card4.add (p3);
	p_card.add ("4", card4);
    }



    public void setpics (int pos)
    { //sets starting game screen
	int x = pos / col;
	int y = pos % col;
	picname = base [x] [y] + "" + people [x] [y] + "" + what [x] [y] + "" + select [x] [y];
	a [pos] = new JButton (createImageIcon (picname + ".png"));

    }


    //movements
    public void move (int pos)
    { //variables for position
	int x = pos / col;
	int y = pos % col;
	int lastx = last / col;
	int lasty = last % col;
	//variable to display power up
	int powcounter = 0;
	if (power == 3)
	{
	    barrier (x, y);

	}
	//selects all the possible moves
	else if (last == -1 && ValidClick1 (x, y))
	{
	    //Click 1, your turn
	    last = pos;
	    select [x] [y] = 7;
	    //all possible moves if the user has a diagonal power up
	    if (power == 1 && what [x] [y] != 5)
		selectDiagonal (x, y);
	    //all possible moves if the user moves normally
	    else
		selectRook (x, y);
	    cornersInvalid (x, y); //prevents the vikings from going to the corner

	}
	else if (last == -1)
	{
	    JOptionPane.showMessageDialog (null, "It is not this side's turn", "Wrong turn", JOptionPane.INFORMATION_MESSAGE);
	}
	else if (ValidClick2 (x, y, lastx, lasty))
	{
	    //Click 2, valid move
	    //if the instant kill super power is active, remove the pink highlighting to show the super power is no longer active
	    if (power == 2 && what [lastx] [lasty] != 5)
		what [lastx] [lasty] = 4;
	    //change positions
	    people [x] [y] = people [lastx] [lasty];
	    what [x] [y] = what [lastx] [lasty];
	    people [lastx] [lasty] = 0;
	    what [lastx] [lasty] = 0;
	    kill (x, y);
	    unselect ();
	    last = -1;
	    //Updates Turns
	    turn ();
	    wallcount (); //check for walls
	    //variable that determines if the power method must be called, used to display power ups after redraw
	    powcounter = 1;
	}


	else
	{
	    //Click 2, not a valid move
	    last = -1;
	    unselect ();
	    JOptionPane.showMessageDialog (null, "This is not a valid move. Try again!", "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
	    if (power == 2 && what [lastx] [lasty] != 5)
		what [lastx] [lasty] = 4;
	}

	redraw ();
	wincheck ();
	//if a valid move was made, a method to check for powerups is called
	if (powcounter == 1) //if statement to check for powerups after the redraw
	    powercheck ();

    }


    //method that checks if there is a wall after every move

    public void wallcount ()
    {
	//if there is a wall, the wall counter will increase after each move
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (people [i] [j] == 9)
		    wallcounter++;
		//once the wall counter reaches five the wall will be removed
		if (wallcounter == 5)
		{
		    people [i] [j] = 0;
		    wallcounter = 0;
		    JOptionPane.showMessageDialog (null, "The wall has expired!", "Wall", JOptionPane.INFORMATION_MESSAGE);
		}


	    }
	}

    }


    //changes turns
    public void turn ()
    {
	if (turn == 1)
	{
	    turn = 2;
	    turnPic.setIcon (createImageIcon ("0246.png"));
	    moves1++; //increases moves for attackers, used for power ups
	}


	else
	{
	    turn = 1;
	    turnPic.setIcon (createImageIcon ("0146.png"));
	    moves2++; //increases moves for defenders
	}
	power = 0;
    }


    //checks to see if a user got a power up
    public void powercheck ()
    {
	//creates a new number moves that the users must reach to get a power up
	if (moves2 == 1)
	    p2 = (int) (Math.random () * 6) + 2;

	if (moves1 == 1)
	    p1 = (int) (Math.random () * 6) + 2;
	//checks if the users have reached the target number of moves and gives them a power up
	if (turn == 2 & moves2 == p2)
	{
	    JOptionPane.showMessageDialog (null, "The Defenders got a power up!", "Power Up unlocked", JOptionPane.INFORMATION_MESSAGE);
	    power ();
	    moves2 = 0;

	}
	if (turn == 1 && moves1 == p1)
	{
	    JOptionPane.showMessageDialog (null, "The Attackers got a power up!", "Power Up unlocked", JOptionPane.INFORMATION_MESSAGE);
	    power ();
	    moves1 = 0;

	}

    }


    //checks if there is a wall and returns true or false
    public boolean wallcheck ()
    {
	int b = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (people [i] [j] == 9)
		    b = 1;
	    }
	}
	if (b == 1)
	    return false;
	else
	    return true;

    }


    //changes images
    public void redraw ()
    {
	int m = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		String picname = base [i] [j] + "" + people [i] [j] + "" + what [i] [j] + "" + select [i] [j];
		a [m].setIcon (createImageIcon (picname + ".png"));
		m++;
	    }
	}
    }


    //checks if the turn is correct
    public boolean ValidClick1 (int x, int y)
    {
	if (turn == people [x] [y])
	    return true;
	else
	    return false;
    }


    //checks if the attempted placement is valid
    public boolean ValidClick2 (int x, int y, int lastx, int lasty)
    {
	if (select [x] [y] == 6)
	    return false;
	else if (x == lastx && y == lasty)
	    return false;
	else
	    return true;
    }


    //selects valid placements
    public void selectRook (int x, int y)
    {
	int notme = 1;
	if (turn == 1)
	    notme = 2;
	//highlights all the valid spots
	//up
	int cx1 = x - 1;
	while (cx1 >= 0 && people [cx1] [y] == 0)
	{
	    select [cx1] [y] = 7;
	    //checks if there are two enemies on either side of the spot, if there are then its an invalid spot, used in the next 3 loops
	    if (y - 1 >= 0 && y + 1 < col && people [cx1] [y - 1] == notme && people [cx1] [y + 1] == notme)
		select [cx1] [y] = 6;
	    cx1--;


	}
	//down
	cx1 = x + 1;
	while (cx1 < row && people [cx1] [y] == 0)
	{
	    select [cx1] [y] = 7;
	    if (y - 1 >= 0 && y + 1 < col && people [cx1] [y - 1] == notme && people [cx1] [y + 1] == notme)
		select [cx1] [y] = 6;
	    cx1++;
	}
	//left
	int cy1 = y - 1;
	while (cy1 >= 0 && people [x] [cy1] == 0)
	{
	    select [x] [cy1] = 7;
	    if (x + 1 < row && x - 1 >= 0 && people [x + 1] [cy1] == notme && people [x - 1] [cy1] == notme)
		select [x] [cy1] = 6;
	    cy1--;

	}
	//right
	cy1 = y + 1;
	while (cy1 < col && people [x] [cy1] == 0)
	{
	    select [x] [cy1] = 7;
	    if (x + 1 < row && x - 1 >= 0 && people [x + 1] [cy1] == notme && people [x - 1] [cy1] == notme)
		select [x] [cy1] = 6;
	    cy1++;

	}
	if (power == 2)
	    hyper (x, y);

    }


    //selects valid placements for diagonal movement
    public void selectDiagonal (int x, int y)
    {
	int notme = 1;
	if (turn == 1)
	    notme = 2;
	// up left conditons
	int cy1 = y - 1;
	int cx1 = x - 1;
	//highlights all the valid spots
	while (cy1 >= 0 && cx1 >= 0 && people [cx1] [cy1] == 0)
	{
	    select [cx1] [cy1] = 7;
	    //checks if there are two enemies on either side of the spot, if there are then its an invalid spot, used in the next three loops
	    if (cy1 - 1 >= 0 && cy1 + 1 < col && people [cx1] [cy1 - 1] == notme && people [cx1] [cy1 + 1] == notme)
		select [cx1] [cy1] = 6;
	    if (cx1 + 1 < row && cx1 - 1 >= 0 && people [cx1 + 1] [cy1] == notme && people [cx1 - 1] [cy1] == notme)
		select [cx1] [cy1] = 6;
	    cx1--;
	    cy1--;
	}

	// up right conditions
	cy1 = y + 1;
	cx1 = x - 1;

	while (cy1 < col && cx1 >= 0 && people [cx1] [cy1] == 0)
	{
	    select [cx1] [cy1] = 7;
	    if (cy1 - 1 >= 0 && cy1 + 1 < col && people [cx1] [cy1 - 1] == notme && people [cx1] [cy1 + 1] == notme)
		select [cx1] [cy1] = 6;
	    if (cx1 + 1 < row && cx1 - 1 >= 0 && people [cx1 + 1] [cy1] == notme && people [cx1 - 1] [cy1] == notme)
		select [cx1] [cy1] = 6;
	    cx1--;
	    cy1++;
	}

	// down left conditions
	cy1 = y - 1;
	cx1 = x + 1;

	while (cy1 >= 0 && cx1 < row && people [cx1] [cy1] == 0)
	{
	    select [cx1] [cy1] = 7;
	    if (cy1 - 1 >= 0 && cy1 + 1 < col && people [cx1] [cy1 - 1] == notme && people [cx1] [cy1 + 1] == notme)
		select [cx1] [cy1] = 6;
	    if (cx1 + 1 < row && cx1 - 1 >= 0 && people [cx1 + 1] [cy1] == notme && people [cx1 - 1] [cy1] == notme)
		select [cx1] [cy1] = 6;
	    cx1++;
	    cy1--;
	}

	// down right conditions
	cy1 = y + 1;
	cx1 = x + 1;

	while (cy1 < col && cx1 < row && people [cx1] [cy1] == 0)
	{
	    select [cx1] [cy1] = 7;
	    if (cy1 - 1 >= 0 && cy1 + 1 < col && people [cx1] [cy1 - 1] == notme && people [cx1] [cy1 + 1] == notme)
		select [cx1] [cy1] = 6;
	    if (cx1 + 1 < row && cx1 - 1 >= 0 && people [cx1 + 1] [cy1] == notme && people [cx1 - 1] [cy1] == notme)
		select [cx1] [cy1] = 6;
	    cx1++;
	    cy1++;
	}
    }


    //prevents the vikings from going to the corners
    public void cornersInvalid (int x, int y)
    {
	if (what [x] [y] != 5)
	{
	    select [0] [0] = 6;
	    select [10] [0] = 6;
	    select [0] [10] = 6;
	    select [10] [10] = 6;
	}
    }


    //method for placing a wall
    public void barrier (int x, int y)
    { //places a wall on a valid spot
	if (people [x] [y] == 0)
	{
	    people [x] [y] = 9;
	    unselect ();
	    turn ();
	}
	//tells the user that they can't place a wall in an invalid spot
	else
	    JOptionPane.showMessageDialog (null, "Click a blank spot to place a wall", "Invalid placement!", JOptionPane.INFORMATION_MESSAGE);

    }


    //highlights the character in pink, indicating that they can kill anyone without sandwiching
    public void hyper (int x, int y)
    {
	if (what [x] [y] != 5)
	    what [x] [y] = 8;
    }


    //method for capturing enemies
    public void kill (int x, int y)
    {
	int notme = 1;
	if (turn == 1)
	    notme = 2;
	//if a user has the instant kill power up, the instatn kill method is called
	if (power == 2)
	    instkill (x, y, notme);
	//Up
	if (x - 2 >= 0 && people [x - 2] [y] == turn && people [x - 1] [y] == notme)
	{
	    people [x - 1] [y] = 0;
	    what [x - 1] [y] = 0;

	}
	//down
	if (x + 2 < row && people [x + 2] [y] == turn && people [x + 1] [y] == notme)
	{
	    people [x + 1] [y] = 0;
	    what [x + 1] [y] = 0;
	}

	//left
	if (y - 2 >= 0 && people [x] [y - 2] == turn && people [x] [y - 1] == notme)
	{
	    people [x] [y - 1] = 0;
	    what [x] [y - 1] = 0;
	}
	//right
	if (y + 2 < col && people [x] [y + 2] == turn && people [x] [y + 1] == notme)
	{
	    people [x] [y + 1] = 0;
	    what [x] [y + 1] = 0;
	}
    }


    //method for capturing without a sandwich, cannot be used to kill the king
    public void instkill (int x, int y, int n)
    {
	if (x - 1 >= 0 && people [x - 1] [y] == n && what [x - 1] [y] != 5)
	{
	    people [x - 1] [y] = 0;
	    what [x - 1] [y] = 0;

	}
	//down
	else if (x + 1 < row && people [x + 1] [y] == n && what [x + 1] [y] != 5)
	{
	    people [x + 1] [y] = 0;
	    what [x + 1] [y] = 0;
	}

	//left
	else if (y - 1 >= 0 && people [x] [y - 1] == n && what [x] [y - 1] != 5)
	{
	    people [x] [y - 1] = 0;
	    what [x] [y - 1] = 0;
	}
	//right
	else if (y + 1 < col && people [x] [y + 1] == n && what [x] [y + 1] != 5)
	{
	    people [x] [y + 1] = 0;
	    what [x] [y + 1] = 0;
	}

    }


    //checks if the game has been won
    public void wincheck ()
    {
	int b = 1;
	//loops through array to see if the king is still on the board
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (what [i] [j] == 5) //if king is still on the board, b=2, if there is no king b=1
		    b = 2;

	    }
	}
	if (b == 1)
	{
	    JOptionPane.showMessageDialog (null, "The king has been killed. Attackers win!", "Winner!", JOptionPane.INFORMATION_MESSAGE);
	    moves1 = 0; //resets the moves so that the power ups do not display after the game has ended
	    moves2 = 0;
	}
	//checks if the king is in a corner
	if (what [0] [0] == 5 || what [10] [0] == 5 || what [0] [10] == 5 || what [10] [10] == 5)
	{
	    JOptionPane.showMessageDialog (null, "The king has made it to a corner. Defenders win!", "Winner!", JOptionPane.INFORMATION_MESSAGE);
	    moves1 = 0;
	    moves2 = 0;
	}
    }


    //unselects the valid placements
    public void unselect ()
    {
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		select [i] [j] = 6;
	    }
	}
    }


    //selects every spot that a wall can be placed on
    public void selectblanks ()
    {
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (what [i] [j] == 0)
		    select [i] [j] = 7;
	    }
	}


	redraw ();
    }


    //method for determing the powerup
    public void power ()
    {
	power = (int) (Math.random () * 4);
	if (power == 1)
	    JOptionPane.showMessageDialog (null, "You got diagonal movement!", "Power Up", JOptionPane.INFORMATION_MESSAGE);
	else if (power == 2)
	    JOptionPane.showMessageDialog (null, "You got instant kill!", "Power Up", JOptionPane.INFORMATION_MESSAGE);
	else if (power == 3 && wallcheck ()) //checks to make sure there is not already a wall on the board
	{
	    selectblanks ();
	    JOptionPane.showMessageDialog (null, "You got wall!", "Power Up", JOptionPane.INFORMATION_MESSAGE);
	}
	else
	{
	    JOptionPane.showMessageDialog (null, "You got... Nothing!", "Power Up", JOptionPane.INFORMATION_MESSAGE);
	    power = 0; //if there is already a wall, this changes the power to be nothing
	}
    }


    //saves the arrays to a file
    public void saved (String filename)
    {
	PrintWriter out;
	try
	{
	    out = new PrintWriter (new FileWriter (filename));
	    //saves all the arrays
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println (base [i] [j]);
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println (people [i] [j]);
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println (what [i] [j]);
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println (select [i] [j]);
		}
	    }
	    //saves all the global variables
	    out.println (power);
	    out.println (moves1);
	    out.println (moves2);
	    out.println (p1);
	    out.println (p2);
	    out.println (wallcounter);
	    out.println (turn);
	    out.println (last);
	    out.close ();
	}


	catch (IOException e)
	{
	    System.out.println ("Error opening file" + e);
	}
    }


    //updates the screen with the saved arrays
    public void opened (String filename)
    {
	//opens all the saved arrays
	BufferedReader in;
	try
	{
	    in = new BufferedReader (new FileReader (filename));
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    base [i] [j] = Integer.parseInt (in.readLine ());

		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    people [i] [j] = Integer.parseInt (in.readLine ());
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    what [i] [j] = Integer.parseInt (in.readLine ());
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    select [i] [j] = Integer.parseInt (in.readLine ());
		}
	    }
	    //opens all the saved global variables
	    power = Integer.parseInt (in.readLine ());
	    moves1 = Integer.parseInt (in.readLine ());
	    moves2 = Integer.parseInt (in.readLine ());
	    p1 = Integer.parseInt (in.readLine ());
	    p2 = Integer.parseInt (in.readLine ());
	    wallcounter = Integer.parseInt (in.readLine ());
	    turn = Integer.parseInt (in.readLine ());
	    last = Integer.parseInt (in.readLine ());
	    in.close ();
	}


	catch (IOException e)
	{
	    System.out.println ("Error opening file" + e);
	}

	if (turn == 1)
	    turnPic.setIcon (createImageIcon ("0146.png"));
	else
	    turnPic.setIcon (createImageIcon ("0246.png"));
	redraw ();

    }


    //resets game
    public void reset ()
    { //copys every element of based into base
	//that sets it back to the original
	unselect ();
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		base [i] [j] = based [i] [j];
		people [i] [j] = peopled [i] [j];
		what [i] [j] = whatd [i] [j];
	    }
	}

	wallcounter = 0;
	turn = 2;
	power = 0;
	turnPic.setIcon (createImageIcon ("0246.png"));
	redraw ();
    }


    //Action performed
    public void actionPerformed (ActionEvent e)
    { //Changes the screen
	if (e.getActionCommand ().equals ("card1"))
	    cdLayout.show (p_card, "1");
	else if (e.getActionCommand ().equals ("card2"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("card3"))
	    cdLayout.show (p_card, "3");
	else if (e.getActionCommand ().equals ("card4"))
	    cdLayout.show (p_card, "4");
	else if (e.getActionCommand ().equals ("card5"))
	    cdLayout.show (p_card, "5");
	//Game buttons
	else if (e.getActionCommand ().equals ("reset"))
	{
	    reset ();
	}

	else if (e.getActionCommand ().equals ("Save"))
	{
	    saved ("game.txt");
	}


	else if (e.getActionCommand ().equals ("open"))
	{
	    opened ("game.txt");
	}

	//used for moves
	else
	{
	    int n = Integer.parseInt (e.getActionCommand ());
	    int x = n / col;
	    int y = n % col;

	    move (n);
	    redraw ();


	}
    }


    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = Hnefatafl.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}


	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }
}


