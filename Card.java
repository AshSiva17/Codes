//Name:Ashwin
//Date:6/11/2023
//Purpose:Min Mystersy CardGame

public class Card
{
    //instance variables for culprit number, culprit name, blue, green, purple, and red clues
    private int picnum;
    private String blue;
    private String green;
    private String purple;
    private String red;
    private String name;


    public Card ()
    {
	//array for culprit numbers
	int picnums[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
	//array for blue clues
	String blues[] = {"Glasses", "Monocle", "Glasses", "Gloves", "Monocle", "Glasses", "Gloves", "Fan", "Fan", "Gloves", "Glasses", "Glasses",
	    "Fan", "Gloves", "Glasses", "Glasses"};
	//array for green clues
	String greens[] = {"Umbrella", "Pocketwatch", "Umbrella", "Newspaper", "Pocketwatch",
	    "Purse", "Purse", "Umbrella", "Hat", "Purse", "Newspaper", "Pocketwatch", "Umbrella", "Newspaper", "Pocketwatch", "Purse"};
	//array for purple clues
	String purples[] = {"Fur", "Scales", "Skin", "Skin", "Skin", "Feathers", "Scales",
	    "Feathers", "Feathers", "Fur", "Fur", "Scales", "Scales", "Scales", "Scales", "Skin"};
	//array for red clues
	String reds[] = {"Scarf", "Necklace", "Flower", "Necklace", "Necklace",
	    "Flower", "Hat", "Necklace", "Newspaper", "Necklace", "Hat", "Scarf",
	    "Hat", "Flower", "Flower", "Hat"};
	//array for culprit names
	String names[] = {"Quentin", "Herbert", "Braxton", "Maurice", "George", "Abigail", "Debbie", "Francine", "Ophelia",
	    "Isabella", "Colton", "Nancy", "Karla", "Eric", "Lily", "Lovecraft"};

	int rand = (int) (Math.random () * names.length); //sets the instance variable to random array index
	picnum = picnums [rand];
	blue = blues [rand];
	green = greens [rand];
	purple = purples [rand];
	red = reds [rand];
	name = names [rand];
    }


    //custom constructor
    public Card (int p, String b, String g, String pu, String r, String n)
    {
	picnum = p;
	blue = b;
	purple = pu;
	green = g;
	red = r;
	name = n;
    }


    //changes culprit number
    public void setPicnum (int p)
    {
	picnum = p;
    }


    //changes blue clue
    public void setBlue (String b)
    {
	blue = b;
    }


    //changes green clue
    public void setGreen (String g)
    {
	green = g;
    }


    //changes purple clue
    public void setPurple (String pu)
    {
	purple = pu;
    }


    //changes red clue
    public void setRed (String r)
    {
	red = r;
    }


    //changes set culprit name
    public void setName (String n)
    {
	name = n;
    }


    //returns culprit num
    public int getPicnum ()
    {
	return picnum;
    }


    //returns blue clue
    public String getBlue ()
    {
	return blue;
    }


    //returns green clue
    public String getGreen ()
    {
	return green;
    }


    //returns purple clue
    public String getPurple ()
    {
	return purple;
    }


    //returns red clue
    public String getRed ()
    {
	return red;
    }


    //returns culprit name
    public String getName ()
    {
	return name;
    }


    //to string that returns the answer and clues associated with it
    public String toString ()
    {
	return "The actual culprit was " + getName () + " who has " + getPurple () + ", " + getRed () + ", " + getGreen () + ", and " + getBlue () + ". No Points!";
    }


    //Equals
    public boolean equals (Card c)
    {

	if (c.getName () == name && c.getBlue () == blue && c.getPurple () == purple && c.getGreen () == green && c.getRed () == red && c.getPicnum () == picnum)
	    return true;
	else
	    return false;

    }


    //compareTo method
    public int compareTo (Card c)
    {
	if (c.getPicnum () == picnum && c.getBlue ().equals (blue) && c.getGreen ().equals (green) && c.getName ().equals (name))
	    return 0;
	else if (c.getPicnum () > picnum && c.getBlue ().compareTo (blue) > 0 && c.getGreen ().compareTo (green)>0 && c.getName ().compareTo (name)>0)
	    return 1;
	else
	    return -1;
    }



}

