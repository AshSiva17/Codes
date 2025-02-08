//Name:Ashwin
//Date:6/11/2023
//Purpose:Min Mystersy CardGame

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class CardGame extends Applet implements ActionListener
{
    Panel p_card;  //to hold all of the screens
    Panel card1, card2, card3, card4, card5;
    CardLayout cdLayout = new CardLayout ();

    JLabel picture1; //Suspect pictures
    JLabel picture2;
    JLabel picture3;
    JLabel picture4;
    JButton Clue1; //Clues and Culprit card
    JButton Clue2;
    JButton Clue3;
    JButton Clue4;
    JLabel Culprit;
    Dimension ClueSquare = new Dimension (80, 80); //Dimensions for clues and culprit card
    Dimension SusSquare = new Dimension (150, 150); //Dimensions for Suspects
    //Variables for game functions
    String crim = "someone"; //answer string
    int bl = 1; //blue clue
    int gr = 1; //green clue
    int pp = 1; //purple clue
    int re = 1; //red clue
    int num = 0; //cards in stack
    int ne = 0; //Determines if the game has started
    String turn = "Player 1"; //determines which players turn it is
    String ans = "Click new game first"; //displays the answer
    int sc1 = 0; //Scores
    int sc2 = 0;
    JLabel score1;
    JLabel score2;
    DeckStack culps = new DeckStack ();
    public void init ()
    {
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	screen1 (); //Screens
	screen2 ();
	screen3 ();
	resize (430, 720);
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    }


    public void screen1 ()
    { //Splash Screen is set up.
	card1 = new Panel ();
	JButton next = new JButton (createImageIcon ("Title.png"));
	next.setBorderPainted (false);
	next.setActionCommand ("s2");
	next.addActionListener (this);
	card1.add (next);
	p_card.add ("1", card1);
    }


    public void screen2 ()
    { //Instructions screen is set up.
	card2 = new Panel ();
	card2.setBackground (Color.white);
	JButton next = new JButton (createImageIcon ("Instructions.png"));
	next.setBorderPainted (false);
	next.setActionCommand ("s3");
	next.addActionListener (this);
	card2.add (next);
	p_card.add ("2", card2);
    }


    public void screen3 ()
    { //Game Screen is set up.
	card3 = new Panel ();
	card3.setBackground (Color.white);
	card3.setBackground (new Color (47, 37, 38));
	JLabel title = new JLabel ("Minute Mystery");
	title.setFont (new Font ("Arial", Font.BOLD, 40));
	title.setForeground (Color.white);
	JButton reset = new JButton ("New Game"); //reset button
	reset.addActionListener (this);
	reset.setActionCommand ("shuffle");
	reset.setBackground (new Color (143, 84, 123));

	Panel bad = new Panel (new GridLayout (2, 4)); //Panel for culprits
	picture1 = new JLabel (createImageIcon ("Q1.png"));
	picture1.setPreferredSize (SusSquare);
	picture2 = new JLabel (createImageIcon ("Q1.png"));
	picture2.setPreferredSize (SusSquare);
	picture3 = new JLabel (createImageIcon ("Q1.png"));
	picture3.setPreferredSize (SusSquare);
	picture4 = new JLabel (createImageIcon ("Q1.png"));
	picture4.setPreferredSize (SusSquare);

	Panel p2 = new Panel (new GridLayout (3, 3)); //Clues
	Clue1 = new JButton (createImageIcon ("Glasses.png"));
	Clue1.setActionCommand ("Clue1");
	Clue1.addActionListener (this);
	Clue1.setPreferredSize (ClueSquare);
	Clue2 = new JButton (createImageIcon ("Umbrella.png"));
	Clue2.setActionCommand ("Clue2");
	Clue2.addActionListener (this);
	Clue2.setPreferredSize (ClueSquare);
	Clue3 = new JButton (createImageIcon ("Fur.png"));
	Clue3.setActionCommand ("Clue3");
	Clue3.addActionListener (this);
	Clue3.setPreferredSize (ClueSquare);
	Clue4 = new JButton (createImageIcon ("Scarf.png"));
	Clue4.setActionCommand ("Clue4");
	Clue4.addActionListener (this);
	Clue4.setPreferredSize (ClueSquare);
	JLabel Clue5 = new JLabel (createImageIcon ("Blank.png"));
	JLabel Clue6 = new JLabel (createImageIcon ("Blank.png"));
	JLabel Clue7 = new JLabel (createImageIcon ("Blank.png"));

	Culprit = new JLabel (createImageIcon ("q.png")); //Culprit cards
	Culprit.setPreferredSize (ClueSquare);
	//Game Buttons
	JButton Instructions = new JButton ("Instructions");
	Instructions.setActionCommand ("s2");
	Instructions.addActionListener (this);
	Instructions.setBackground (new Color (143, 84, 123));
	JButton NewGame = new JButton ("Next Round"); //Note:Make this does nothing until the users click new game
	NewGame.addActionListener (this);
	NewGame.setActionCommand ("Next");
	NewGame.setBackground (new Color (143, 84, 123));
	JButton Choose = new JButton ("Choose");
	Choose.addActionListener (this);
	Choose.setActionCommand ("answer");
	Choose.setBackground (new Color (143, 84, 123));
	JButton end = new JButton ("End");
	end.addActionListener (this);
	end.setActionCommand ("s6");
	end.setBackground (new Color (143, 84, 123));
	JButton p1score = new JButton ("Player 1 score");
	p1score.addActionListener (this);
	p1score.setActionCommand ("player1score");
	p1score.setBackground (new Color (143, 84, 123));
	JButton p2score = new JButton ("Player 2 Score");
	p2score.addActionListener (this);
	p2score.setActionCommand ("player2score");
	p2score.setBackground (new Color (143, 84, 123));
	JLabel bla = new JLabel ("        ");
	score1 = new JLabel ("Player one's score:" + sc1); //Scores
	score1.setFont (new Font ("Arial", Font.BOLD, 15));
	score1.setForeground (Color.white);
	score2 = new JLabel ("Player two's score:" + sc2);
	score2.setFont (new Font ("Arial", Font.BOLD, 15));
	score2.setForeground (Color.white);

	Panel p = new Panel ();
	card3.add (title);
	bad.add (picture1);
	bad.add (picture2);
	bad.add (picture3);
	bad.add (picture4);
	p.add (reset);
	p.add (Instructions);
	p.add (NewGame);
	p.add (Choose);
	p.add (end);
	Panel p3 = new Panel ();
	p3.add (p1score);
	p3.add (p2score);
	Panel p4 = new Panel ();
	p4.add (score1);
	p4.add (bla);
	p4.add (score2);

	p2.add (Clue7);
	p2.add (Clue3);
	p2.add (Clue5);
	p2.add (Clue4);
	p2.add (Culprit);
	p2.add (Clue1);
	p2.add (Clue6);
	p2.add (Clue2);
	card3.add (bad);
	card3.add (p);
	card3.add (p2);
	card3.add (p3);
	card3.add (p4);

	p_card.add ("3", card3);
    }


    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = CardGame.class.getResource (path);
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



    public void actionPerformed (ActionEvent e)
    { //moves between the screens
	if (e.getActionCommand ().equals ("s1"))
	    cdLayout.show (p_card, "1");
	else if (e.getActionCommand ().equals ("s2"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("s3"))
	    cdLayout.show (p_card, "3");
	else if (e.getActionCommand ().equals ("s6"))
	    System.exit (0);
	//Changes the clues
	else if (e.getActionCommand ().equals ("Clue1"))
	    changeBlue (Clue1);
	else if (e.getActionCommand ().equals ("Clue2"))
	    changeGreen (Clue2);
	else if (e.getActionCommand ().equals ("Clue3"))
	    changePurple (Clue3);
	else if (e.getActionCommand ().equals ("Clue4"))
	    changeRed (Clue4);
	else if (e.getActionCommand ().equals ("answer"))
	    answer ();
	else if (e.getActionCommand ().equals ("player1score"))
	    p1s ();
	else if (e.getActionCommand ().equals ("player2score"))
	    p2s ();
	else if (e.getActionCommand ().equals ("Next"))
	    nextcards ();
	else if (e.getActionCommand ().equals ("shuffle"))
	    shuff ();
    }


    public void p2s ()  //Updates the score for player 2
    {
	sc2++;
	score2.setText ("Player two's score:" + sc2);
    }


    public void p1s ()  //Updates the score for player 1
    {
	sc1++;
	score1.setText ("Player one's score:" + sc1);
    }


    public void nextcards ()  //pops a new set of cards
    {
	//if the culprit stack isn't empty
	//This game can only have 4 rounds until there are no more cards
	if (!culps.isEmpty ())
	{
	    ne = 1;
	    //pop a card from the culps, save it
	    if (turn.equals ("Player 1"))
		turn = "Player 2";
	    else
		turn = "Player 1";
	    Card d = culps.pop ();
	    Card b = culps.pop ();
	    Card r = culps.pop ();
	    Card f = culps.pop ();
	    //subtract one from num
	    num--;
	    //call showcard, pass in the 4 criminal cards
	    showCard (d, b, r, f);

	}
	else if (ne == 0) //tells the user that they need to begin the game first
	    JOptionPane.showMessageDialog (null, "Click New Game first", "Start the game", JOptionPane.INFORMATION_MESSAGE);
	else
	{
	    ne = 0; //resets the variable that determines if the user has already started the game or not
	    if (sc1 > sc2) //checks if the winner
		JOptionPane.showMessageDialog (null, "The game is over. Player 1 has won. Well done!", "Winner", JOptionPane.INFORMATION_MESSAGE);
	    else if (sc2 > sc1)
		JOptionPane.showMessageDialog (null, "The game is over. Player 2 has won. Well done!", "Winner", JOptionPane.INFORMATION_MESSAGE);
	    else
		JOptionPane.showMessageDialog (null, "The game is over. It was a tie.", "Tie", JOptionPane.INFORMATION_MESSAGE);
	}
    }


    public void shuff ()  //creates a new random set of cards. Also acts as reset button
    {
	//reset game variables
	reset ();
	//clear culps
	culps.clear ();
	//shuffles
	culps.shuffle ();
	//set num to size of the culps
	num = culps.size ();
	//pop a card for each stack, store it in a variable
	Card d = culps.pop ();
	Card h = culps.pop ();
	Card r = culps.pop ();
	Card f = culps.pop ();
	//calls the showcard, passes in the 4 cards
	showCard (d, h, r, f);
    }


    public void changeBlue (JButton b)  //Changes the blue clue
    {
	if (bl == 1)
	{
	    b.setIcon (createImageIcon ("Monocle.png"));
	    bl = 2;

	}
	else if (bl == 2)
	{
	    b.setIcon (createImageIcon ("Fan.png"));
	    bl = 3;
	}
	else if (bl == 3)
	{
	    b.setIcon (createImageIcon ("Gloves.png"));
	    bl = 4;
	}
	else
	{
	    b.setIcon (createImageIcon ("glasses.png"));
	    bl = 1;
	}
    }


    public void changeGreen (JButton g)  //Changes the green clue
    {
	if (gr == 1)
	{
	    g.setIcon (createImageIcon ("Newspaper.png"));
	    gr = 2;
	}
	else if (gr == 2)
	{
	    g.setIcon (createImageIcon ("Purse.png"));
	    gr = 3;
	}
	else if (gr == 3)
	{
	    g.setIcon (createImageIcon ("Pocketwatch.png"));
	    gr = 4;
	}
	else
	{
	    g.setIcon (createImageIcon ("Umbrella.png"));
	    gr = 1;
	}
    }


    public void changePurple (JButton p)  //Changes the purple clue
    {
	if (pp == 1)
	{
	    p.setIcon (createImageIcon ("Skin.png"));
	    pp = 2;
	}
	else if (pp == 2)
	{
	    p.setIcon (createImageIcon ("Scales.png"));
	    pp = 3;
	}
	else if (pp == 3)
	{
	    p.setIcon (createImageIcon ("Feathers.png"));
	    pp = 4;
	}
	else
	{
	    p.setIcon (createImageIcon ("Fur.png"));
	    pp = 1;
	}
    }


    public void changeRed (JButton r)  //Changes the red clue
    {
	if (re == 1)
	{
	    r.setIcon (createImageIcon ("Hat.png"));
	    re = 2;
	}
	else if (re == 2)
	{
	    r.setIcon (createImageIcon ("Necklace.png"));
	    re = 3;
	}
	else if (re == 3)
	{
	    r.setIcon (createImageIcon ("Flower.png"));
	    re = 4;
	}
	else
	{
	    r.setIcon (createImageIcon ("Scarf.png"));
	    re = 1;
	}
    }


    public void answer ()  //alows the users to input an answer
    {
	String input = JOptionPane.showInputDialog ("Who is the culprit?");
	if (input.equalsIgnoreCase (crim)) //tells the user if their answer is correct or incorrect
	    JOptionPane.showMessageDialog (null, turn + " caught the criminal! +1 point to them.", "Correct!", JOptionPane.INFORMATION_MESSAGE);
	else
	    JOptionPane.showMessageDialog (null, ans, "Incorrect!", JOptionPane.INFORMATION_MESSAGE);
    }


    public void reset ()  //resets the score variables
    {
	sc1 = 0;
	sc2 = 0;
	score1.setText ("Player one's score:" + sc1);
	score2.setText ("Player two's score:" + sc2);
    }


    public void showCard (Card d, Card h, Card r, Card f)  //Changes the card
    {
	picture1.setIcon (createImageIcon (d.getName () + ".png")); //updates the pictures
	picture2.setIcon (createImageIcon (h.getName () + ".png"));
	picture3.setIcon (createImageIcon (r.getName () + ".png"));
	picture4.setIcon (createImageIcon (f.getName () + ".png"));
	int cu = (int) (Math.random () * 4);
	if (cu == 1) //picks a random suspect to be the criminal
	{
	    crim = d.getName (); //sets the crim to the name of the random criminal, answer method to check if the input is correct
	    ans = d.toString (); //sets the ans string to the toSring. This displays the real criminal and its features
	    Culprit.setIcon (createImageIcon (d.getPicnum () + ".png"));
	}
	else if (cu == 2)
	{
	    crim = h.getName ();
	    ans = h.toString ();
	    Culprit.setIcon (createImageIcon (h.getPicnum () + ".png"));
	}
	else if (cu == 3)
	{
	    crim = r.getName ();
	    ans = r.toString ();
	    Culprit.setIcon (createImageIcon (r.getPicnum () + ".png"));
	}
	else
	{
	    crim = f.getName ();
	    ans = f.toString ();
	    Culprit.setIcon (createImageIcon (f.getPicnum () + ".png"));
	}
    }
}


