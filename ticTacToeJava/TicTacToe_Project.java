//Name: Ashwin Sivabalan
//Date: October 5th 2023
//Purpose: Quick Tic Tac Toe
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
public class TicTacToe_Project extends Applet implements ActionListener
{
    //Game screen
    JLabel turnPic;
    JButton a, b, c, d, ee, f, g, h, i;
    char board[] [] = {{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}};
    char turn = 'g';
    JTextField choice, name, first;
    Panel p_card;  //to hold all of the screens
    Panel card1, card2, card3, card4; //the  screens
    CardLayout cdLayout = new CardLayout ();
    //Formatting
    Color buttonColour = (new Color (252, 161, 65));
    Dimension boardSquare = new Dimension (96, 96);
    int plax = 0; //variable for integer. when this reaches 9, the game has been won
    int placement = 1; //determines if this is the users first mark
    int prehor = 5; //variables for the users first mark
    int prever = 5;
    JTextField u1; //Textfields for the users to input their names
    JTextField u2;

    public void init ()
    {
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	screen1 ();
	screen2 ();
	screen3 ();
	screen4 ();
	resize (420, 540);
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    }


    public void screen1 ()
    { //splash screen is set up.
	card1 = new Panel ();
	JButton next = new JButton (createImageIcon ("start.png"));
	next.setBorderPainted (false);
	next.setActionCommand ("s2");
	next.addActionListener (this);
	card1.add (next);
	p_card.add ("1", card1);
    }


    public void screen2 ()
    { //Istructions screen is set up.
	card2 = new Panel ();
	JButton next = new JButton (createImageIcon ("instruc.png"));
	next.setBorderPainted (false);
	next.setActionCommand ("s3");
	next.addActionListener (this);
	card2.add (next);
	p_card.add ("2", card2);
    }


    //Users can add their names here
    public void screen3 ()
    { //name screen is set up.
	card3 = new Panel ();
	card3.setBackground (new Color (147, 223, 243));
	JLabel ti = new JLabel ("Enter the your name(s) next to");
	ti.setFont (new Font ("Arial", Font.PLAIN, 25));
	JLabel ti2 = new JLabel (" the character(s) you wish to use");
	ti2.setFont (new Font ("Arial", Font.PLAIN, 25));
	JLabel inv = new JLabel ("                  ");
	inv.setPreferredSize (new Dimension (250, 50));
	JLabel redn = new JLabel (createImageIcon ("dno.png"));
	u1 = new JTextField (25);
	JLabel pign = new JLabel (createImageIcon ("gno.png"));
	u2 = new JTextField (25);
	JLabel bird = new JLabel (createImageIcon ("bird.png"));
	JButton next = new JButton ("Next");
	next.setPreferredSize (new Dimension (100, 30));
	next.setBackground (buttonColour);
	next.setBorderPainted (false);
	next.setActionCommand ("s4");
	next.addActionListener (this);
	card3.add (ti);
	card3.add (ti2);
	card3.add (redn);
	card3.add (u1);
	card3.add (pign);
	card3.add (u2);
	card3.add (bird);
	card3.add (inv);
	card3.add (next);
	p_card.add ("3", card3);
    }


    public void screen4 ()
    {

	//game screen is set up
	card4 = new Panel ();
	JLabel title = new JLabel (createImageIcon ("title.png"));
	title.setPreferredSize (new Dimension (500, 50));
	Panel p = new Panel ();
	JLabel curturn = new JLabel (createImageIcon ("curt.png"));
	turnPic = new JLabel (createImageIcon ("dno.png"));
	turnPic.setPreferredSize (new Dimension (75, 75));
	p.add (curturn);
	p.add (turnPic);
	Panel p2 = new Panel (new GridLayout (3, 3));
	card4.setBackground (new Color (147, 223, 243));
	//Tic Tac Toe grid
	a = new JButton (createImageIcon ("b.png"));
	a.setActionCommand ("a");
	a.setPreferredSize (boardSquare);
	a.addActionListener (this);
	p2.add (a);
	b = new JButton (createImageIcon ("b.png"));
	b.setActionCommand ("b");
	b.setPreferredSize (boardSquare);
	b.addActionListener (this);
	p2.add (b);
	c = new JButton (createImageIcon ("b.png"));
	c.setActionCommand ("c");
	c.setPreferredSize (boardSquare);
	c.addActionListener (this);
	p2.add (c);
	d = new JButton (createImageIcon ("b.png"));
	d.setActionCommand ("d");
	d.setPreferredSize (boardSquare);
	d.addActionListener (this);
	p2.add (d);
	ee = new JButton (createImageIcon ("b.png"));
	ee.setActionCommand ("ee");
	ee.setPreferredSize (boardSquare);
	ee.addActionListener (this);
	p2.add (ee);
	f = new JButton (createImageIcon ("b.png"));
	f.setActionCommand ("f");
	f.setPreferredSize (boardSquare);
	f.addActionListener (this);
	p2.add (f);
	g = new JButton (createImageIcon ("b.png"));
	g.setActionCommand ("g");
	g.setPreferredSize (boardSquare);
	g.addActionListener (this);
	p2.add (g);
	h = new JButton (createImageIcon ("b.png"));
	h.setActionCommand ("h");
	h.setPreferredSize (boardSquare);
	h.addActionListener (this);
	p2.add (h);
	i = new JButton (createImageIcon ("b.png"));
	i.setActionCommand ("i");
	i.setPreferredSize (boardSquare);
	i.addActionListener (this);
	p2.add (i);
	JButton done = new JButton ("Done");
	done.setActionCommand ("done");
	done.setBackground (buttonColour);
	done.addActionListener (this);
	done.setPreferredSize (new Dimension (125, 30));
	done.setBackground (buttonColour);
	JLabel inv = new JLabel (" ");
	inv.setPreferredSize (new Dimension (20, 30));
	JLabel inv2 = new JLabel (" ");
	inv2.setPreferredSize (new Dimension (20, 30));
	//Game screen buttons
	Panel p3 = new Panel ();
	JButton reset = new JButton ("Again");
	reset.addActionListener (this);
	reset.setActionCommand ("reset");
	reset.setPreferredSize (new Dimension (100, 30));
	reset.setBackground (buttonColour);
	p3.add (reset);
	JButton instruct = new JButton ("Instructions");
	instruct.addActionListener (this);
	instruct.setActionCommand ("s2");
	instruct.setPreferredSize (new Dimension (120, 30));
	instruct.setBackground (buttonColour);
	p3.add (instruct);
	JButton creator = new JButton ("Creator");
	creator.addActionListener (this);
	creator.setActionCommand ("creator");
	creator.setPreferredSize (new Dimension (100, 30));
	creator.setBackground (buttonColour);
	p3.add (creator);
	card4.add (title);
	card4.add (inv2);
	card4.add (p);
	card4.add (inv);
	card4.add (done);
	card4.add (p2);
	card4.add (p3);
	p_card.add ("4", card4);
    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("reset"))
	{
	    reset (); //resets game
	}
	else if (e.getActionCommand ().equals ("done"))
	    doneturn (); //switches turns

	//method used for switching screens
	else if (e.getActionCommand ().equals ("s2"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("s3"))
	    cdLayout.show (p_card, "3");
	else if (e.getActionCommand ().equals ("s4"))
	    cdLayout.show (p_card, "4");
	else if (e.getActionCommand ().equals ("creator"))
	{
	    JOptionPane.showMessageDialog (null, "Created by Ashwin Sivabaln in 2023", "Creator", JOptionPane.INFORMATION_MESSAGE);
	}
	//updates the squares
	else
	{
	    if (e.getActionCommand ().equals ("a"))
		det (0, 0, a); //sends variables into to a method that determines if the user is placing for the first second or third time in a row
	    else if (e.getActionCommand ().equals ("b"))
		det (0, 1, b);
	    else if (e.getActionCommand ().equals ("c"))
		det (0, 2, c);
	    else if (e.getActionCommand ().equals ("d"))
		det (1, 0, d);
	    else if (e.getActionCommand ().equals ("ee"))
		det (1, 1, ee);
	    else if (e.getActionCommand ().equals ("f"))
		det (1, 2, f);
	    else if (e.getActionCommand ().equals ("g"))
		det (2, 0, g);
	    else if (e.getActionCommand ().equals ("h"))
		det (2, 1, h);
	    else if (e.getActionCommand ().equals ("i"))
		det (2, 2, i);
	}
    }


    // resets the grid and important ints
    public void reset ()
    {
	board [0] [0] = 'b';
	a.setIcon (createImageIcon ("b.png"));
	board [0] [1] = 'b';
	b.setIcon (createImageIcon ("b.png"));
	board [0] [2] = 'b';
	c.setIcon (createImageIcon ("b.png"));
	board [1] [0] = 'b';
	d.setIcon (createImageIcon ("b.png"));
	board [1] [1] = 'b';
	ee.setIcon (createImageIcon ("b.png"));
	board [1] [2] = 'b';
	f.setIcon (createImageIcon ("b.png"));
	board [2] [0] = 'b';
	g.setIcon (createImageIcon ("b.png"));
	board [2] [1] = 'b';
	h.setIcon (createImageIcon ("b.png"));
	board [2] [2] = 'b';
	i.setIcon (createImageIcon ("b.png"));
	doneturn (); //switches turn
	plax = 0; //resets global variables
	placement = 1;
    }


    //determines if the user is placing for the first time or second/third time
    public void det (int hor, int ver, JButton square)
    {

	if (placement == 1)
	    upDateSquare1 (hor, ver, square);

	else if (placement > 1)
	    upDateSquare2 (hor, ver, square);

    }


    // method for when the user places for the first time. Allows them to place in any space that is not already filled
    public void upDateSquare1 (int hor, int ver, JButton square)
    {


	if (board [hor] [ver] == 'b')
	{
	    prehor = hor;
	    prever = ver;
	    nexTo (square);
	    board [hor] [ver] = turn;
	    square.setIcon (createImageIcon (turn + ".png"));
	    plax++;
	    placement++;
	    if (turn == 'g')
		win (1);
	    else
		win (2);

	}
	else
	{
	    JOptionPane.showMessageDialog (null, "Space is already full. Pick another.", "Can't go here!", JOptionPane.ERROR_MESSAGE);
	}


    }


    //Method for when the user places for the second/third time
    public void upDateSquare2 (int hor2, int ver2, JButton square)
    {
	if (board [hor2] [ver2] == 'o')
	{
	    board [hor2] [ver2] = turn;
	    square.setIcon (createImageIcon (turn + ".png"));
	    HorVerCheck (hor2, ver2);
	    plax++;
	    if (turn == 'g')
		win (1);
	    else
		win (2);
	}
	else if (board [hor2] [ver2] != 'o' && board [hor2] [ver2] != 'b')
	{
	    JOptionPane.showMessageDialog (null, "Space is already full. Pick another.", "Can't go here!", JOptionPane.ERROR_MESSAGE);
	}
	else if (board [hor2] [ver2] != 'o')
	{
	    JOptionPane.showMessageDialog (null, "That space is not in the same vertical or horizontal line. Try again", "Can't go here!", JOptionPane.ERROR_MESSAGE);
	}
    }


    // Makes the spots in the same vertical and horizontal rows as the first placement valid placement spots
    public void nexTo (JButton square)
    {
	// can remove unneeded code in the if statements
	//removed the code
	if (prehor + 1 < 3 && board [prehor + 1] [prever] == 'b')
	    board [prehor + 1] [prever] = 'o';
	if (prehor - 1 >= 0 && prehor - 1 < 2 && board [prehor - 1] [prever] == 'b')
	    board [prehor - 1] [prever] = 'o';
	if (prever + 1 < 3 && board [prehor] [prever + 1] == 'b')
	    board [prehor] [prever + 1] = 'o';
	if (prever - 1 >= 0 && prever - 1 < 2 && board [prehor] [prever - 1] == 'b')
	    board [prehor] [prever - 1] = 'o';
	if (prehor == 0 && board [2] [prever] == 'b')
	    board [2] [prever] = 'o';
	if (prehor == 2 && board [0] [prever] == 'b')
	    board [0] [prever] = 'o';
	if (prever == 0 && board [prehor] [2] == 'b')
	    board [prehor] [2] = 'o';
	if (prever == 2 && board [prehor] [0] == 'b')
	    board [prehor] [0] = 'o';
    }


    //Method that is used if the same user places two times in a row. Makes it so the user can only place in the same Horizontal OR Veritcal line for their third turn.
    public void HorVerCheck (int hor2, int ver2)
    {
	if (board [ver2] == board [prever])
	{
	    if (board [prehor] [0] == 'o')
		board [prehor] [0] = 'b';
	    if (board [prehor] [1] == 'o')
		board [prehor] [1] = 'b';
	    if (board [prehor] [2] == 'o')
		board [prehor] [2] = 'b';
	}
	if (board [hor2] == board [prehor])
	{
	    if (board [0] [prever] == 'o')
		board [0] [prever] = 'b';
	    if (board [1] [prever] == 'o')
		board [1] [prever] = 'b';
	    if (board [2] [prever] == 'o')
		board [2] [prever] = 'b';
	}
    }


    //there is an error here, fix it
    //Fixed it
    //Changes all the valid spots that were not used back to invalid spots
    public void changeBack ()
    {
	if (board [0] [0] == 'o')
	    board [0] [0] = 'b';
	if (board [0] [1] == 'o')
	    board [0] [1] = 'b';
	if (board [0] [2] == 'o')
	    board [0] [2] = 'b';

	if (board [1] [0] == 'o')
	    board [1] [0] = 'b';
	if (board [1] [1] == 'o')
	    board [1] [1] = 'b';
	if (board [1] [2] == 'o')
	    board [1] [2] = 'b';

	if (board [2] [0] == 'o')
	    board [2] [0] = 'b';
	if (board [2] [1] == 'o')
	    board [2] [1] = 'b';
	if (board [2] [2] == 'o')
	    board [2] [2] = 'b';
    }



    //win condition
    public void win (int winner)
    {
	String s = u1.getText (); //gets the users' names
	String b = u2.getText ();
	if (plax == 9 && winner == 1) //if the game is won, it is updated with users name.
	    JOptionPane.showMessageDialog (null, s + " has won!! Well done!", "Winner!", JOptionPane.INFORMATION_MESSAGE);
	else if (plax == 9 && winner == 2)
	    JOptionPane.showMessageDialog (null, b + " has won!! Well done!", "Winner!", JOptionPane.INFORMATION_MESSAGE);
    }


    //remove the frame for the turn pic
    // its been removed
    //Flips turn
    public void doneturn ()
    {
	changeBack ();
	placement = 1; //resets the win condition variable
	if (turn == 'g') //changes the current turn picture
	{
	    turn = 'd';
	    turnPic.setIcon (createImageIcon ("gno.png"));
	}
	else
	{
	    turn = 'g';
	    turnPic.setIcon (createImageIcon ("dno.png"));
	}
    }


    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = TicTacToe_Project.class.getResource (path);
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


