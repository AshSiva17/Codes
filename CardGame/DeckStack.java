//Name:Ashwin
//Date:6/11/2023
//Purpose:Min Mystersy Stack Class

public class DeckStack
{
    private int count;
    private Card data[] = new Card [50];
    public DeckStack ()
    {
	count = 0;
    }


    public void shuffle ()  //gets a random place card in the array
    {
	int picnums[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}; //culprit card number array

	String blues[] = {"Glasses", "Monocle", "Glasses", "Gloves", "Monocle", "Glasses", "Gloves", "Fan", "Fan", "Gloves", "Glasses", "Glasses",
	    "Fan", "Gloves", "Glasses", "Glasses"}; //blue clues


	String greens[] = {"Umbrella", "PocketWatch", "Umbrella", "Newspaper", "Pocketwatch",
	    "Purse", "Purse", "Umbrella", "Hat", "Purse", "Newspaper", "Pocketwatch", "Umbrella", "Newspaper", "Pocketwatch", "Purse"}; //green clues

	String purples[] = {"Fur", "Scales", "Skin", "Skin", "Skin", "Feathers", "Scales",
	    "Feathers", "Feathers", "Fur", "Fur", "Scales", "Scales", "Scales", "Scales", "Skin"}; //purple clues

	String reds[] = {"Scarf", "Necklace", "Flower", "Necklace", "Necklace",
	    "Flower", "Hat", "Necklace", "Newspaper", "Necklace", "Hat", "Scarf",
	    "Hat", "Flowers", "Flowers", "Hat"}; //red clues

	String names[] = {"Quentin", "Herbert", "Braxton", "Maurice", "George",
	    "Abigail", "Debbie", "Francine", "Ophelia", "Isabella", "Colton", "Nancy",
	    "Karla", "Eric", "Lily", "Lovecraft"}; //suspect names

	//Randomize the order of the arrays
	for (int i = 0 ; i < 100 ; i++)
	{
	    int r1 = (int) (Math.random () * names.length);
	    int r2 = (int) (Math.random () * names.length);
	    //swap picnum array
	    int temp = picnums [r1];
	    picnums [r1] = picnums [r2];
	    picnums [r2] = temp;
	    //swap blue array
	    String temp1 = blues [r1];
	    blues [r1] = blues [r2];
	    blues [r2] = temp1;
	    //swap green array
	    String temp2 = greens [r1];
	    greens [r1] = greens [r2];
	    greens [r2] = temp2;
	    //swap purple array
	    String temp3 = purples [r1];
	    purples [r1] = purples [r2];
	    purples [r2] = temp3;
	    //swap red array
	    String temp4 = reds [r1];
	    reds [r1] = reds [r2];
	    reds [r2] = temp4;
	    //swap names array
	    String temp5 = names [r1];
	    names [r1] = names [r2];
	    names [r2] = temp5;
	}
	count = 0;
	for (int i = 0 ; i < names.length ; i++)
	{
	    Card d = new Card (picnums [i], blues [i], greens [i], purples [i], reds [i], names [i]);
	    push (d);
	}

    }


    //push a card
    public void push (Card addMe)
    {
	data [count] = addMe;
	count++;
    }

    
    public int size ()
    {
	return count;
    }

    //checks if the stack is full
    public boolean isFull ()
    {
	return (count == 50);
    }

    //pops a card
    public Card pop ()
    {
	count--;
	return data [count];
    }

    //peeks at the stack
    public Card peek ()
    {
	return data [count--];
    }

    //checks if the stack is empty 
    public boolean isEmpty ()
    {
	return count == 0;
    }

    //clears
    public void clear ()
    {
	count = 0;
    }



}

