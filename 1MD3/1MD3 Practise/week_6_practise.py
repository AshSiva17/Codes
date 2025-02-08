
'''
************************ Section A ************************

1) Consider the list:

L = [[1, 5.6, [5]], [8, True, "False"], [[False], [6, "Six"], 88], "qwerty"]

Write expressions using only slicing and indexing that evaluating to the
following. As an example, the answer to part a) would be: L[1][2][1:3]
a) "al"
b) 5 #L[0][2][0]
c) [True, "False"] # L[1][1:]
d) 88 # L[2][2]
e) [[False], [6, "Six"]]#L[2][:2]
f) "rty" # L[3][3:]
g) 6 # L[2][1][0]


Implement the functions below (you may or may not need to use nested
loops).

Hint: Consider the string methods split and strip.

a)
'''
def list_paragraph(para: str)-> list[list[str]]:
    cut = para.split('.')
    new = []
    for sentence in cut:
        if not (sentence == ''):
            new.append(sentence.split())
        
    return new 
    
    """
	Takes in a paragraph of text para. Each sentence in para ends with a
	".". Returns a nested list of strings, where each
	element of the list is a list of strings which represents a single
	sentence. And each element in a list representing a sentence
	is a single word.

	>>> s = 'This is an example. This is the second sentence; there may
	be some punctuation. This is the third.'
	>>> list_paragraph(s)
	[['This', 'is', 'an', 'example'], ['This', 'is', 'the', 'second',
	'sentence;', 'there', 'may', 'be', 'some', 'punctuation'],
	['This', 'is', 'the', 'third']]
	>>> list_paragraph("")
	[]
    """
	 
'''
b)
'''
def create_paragraph(para_list: list[list[str]])-> str:
    new = ''
    for sentence in para_list:
        for word in sentence:
            new += word + ' '
        
    return new 

'''
	"""
	Takes in a list of lists of strings which represent a paragraph of
	text as described in part a). The function recreates the paragraph
	and returns it. In general, for any paragraph of text s, s should
	equal create_paragraph(list_paragraph(s)).

	>>> L = [['This', 'is', 'an', 'example'], ['This', 'is', 'the',
	'second', 'sentence;', 'there', 'may', 'be', 'some', 'punctuation'],
	['This', 'is', 'the', 'third']]
	>>> create_paragraph(L)
	'This is an example. This is the second sentence; there may be some
	punctuation. This is the third.'
	"""
	return

c) What if we had a very large piece of text with many paragraphs. How might
we represent that in list format, where if I had such a list L and wanted the
the 3rd paragraph I'd be able to return it with L[2]?

3) Consider the function below:

def annoying(L: List[List[int]]):
	"""
	???
	"""
	total = 0
	for i in range(len(L[0])):
		for num in L[i]:
			total += L[L[i][num]][L[num][i]]
	return total

What is the output of:
>>> L = [[0,1,2],[2,0,1],[1,2,0]]
>>> annoying(L)
???

4) The Cartesian Product is used to generate pairs from two sets:

For example:

{0,1,2} X {a,b} = {(0,a),(0,b),(1,a),(1,b),(2,a),(2,b)}

Implement the function below which mimics the caresian product behaviour.

def cart_prod(A:list, B:list) -> List[List]:
	"""
	>>> cart_prod( [], [1,2,3] )
	[]
	>>> cart_prod( [1,2], [5,6,7])
	[[1, 5], [1, 6], [1, 7], [2, 5], [2, 6], [2, 7]]
	"""
	return

************************ Section B ************************

from typing import List, Tuple

# Question 1 - Working with nested range loops

# A dice is a 6 sided object that when thrown gives a value random 
# between 1 to 6. It is common to roll 2 die at once.

# Using for loops, create a list all possible combinations,
# and then print a table of each possible combination of rolls.

def list_dice_rolls() -> List[List[Tuple(int,int)]]:
	pass

def print_dice_rolls(rolls: List[List[Tuple(int,int)]]):
	pass


# Example output
# (1, 1) (1, 2) (1, 3) (1, 4) (1, 5) (1, 6) 
# (2, 1) (2, 2) (2, 3) (2, 4) (2, 5) (2, 6) 
# (3, 1) (3, 2) (3, 3) (3, 4) (3, 5) (3, 6) 
# (4, 1) (4, 2) (4, 3) (4, 4) (4, 5) (4, 6) 
# (5, 1) (5, 2) (5, 3) (5, 4) (5, 5) (5, 6) 
# (6, 1) (6, 2) (6, 3) (6, 4) (6, 5) (6, 6)


# Question 2 - Working with nested lists and nested loops

# From the result of Q1, we can see that there is only one way to roll 
# the output = 2, with (1,1), but we can roll 5 by having (1,4), (2,3), (3,2) or (4,1).
# Thus, we can say that the probability of rolling 5 is 4/36 (given there are 36 
# different outcomes). Write a program that returns a list of probabilites of rolling.

# For this, first build a 2d list of all possible rolls
# and then reduce that down to a list of probabilites

# The final output should be something like:
# [0.027777777777777776, 0.05555555555555555, 0.08333333333333333, 0.1111111111111111, 0.1388888888888889, 0.16666666666666666, 0.1388888888888889, 0.1111111111111111, 0.08333333333333333, 0.05555555555555555, 0.027777777777777776]

def dice_probs():
	pass

# Exercise 3

# Given a list defining a tic-tac-toe board, output a 3x3 2d list.

def tictactransform(L: List[str]):
	pass


# [" ", "X", "O", "O", "X", "X", "O", "X", " "] 

# turns into

# [' ', 'X', 'O']
# ['O', 'X', 'X']
# ['O', 'X', ' ']


************************ Section C ************************
# Example 1
# Write a program that prints out the 12x12 Multiplication table
# It will look something like this (you don't need the headers if it's easier):
# Hint: Look into .rjust() and print(str, end="") for formatting
"""
        1   2   3   4   5   6   7   8   9  10  11  12
     ------------------------------------------------
 1 |    1   2   3   4   5   6   7   8   9  10  11  12
 2 |    2   4   6   8  10  12  14  16  18  20  22  24
 3 |    3   6   9  12  15  18  21  24  27  30  33  36
 4 |    4   8  12  16  20  24  28  32  36  40  44  48
 5 |    5  10  15  20  25  30  35  40  45  50  55  60
 6 |    6  12  18  24  30  36  42  48  54  60  66  72
 7 |    7  14  21  28  35  42  49  56  63  70  77  84
 8 |    8  16  24  32  40  48  56  64  72  80  88  96
 9 |    9  18  27  36  45  54  63  72  81  90  99 108
10 |   10  20  30  40  50  60  70  80  90 100 110 120
11 |   11  22  33  44  55  66  77  88  99 110 121 132
12 |   12  24  36  48  60  72  84  96 108 120 132 144
"""

def multtable():
  pass

multtable()

# Example 2
# Print out a symmetric matrix with n rows that is empty down the diagonal

"""
symmetry(5):
     1  2  3  4  5
  1     3  4  5  6
  2  3     5  6  7
  3  4  5     7  8
  4  5  6  7     9
  5  6  7  8  9
"""

def symmetry(n):
  pass

symmetry(5)


# Example 3
# Given a list of employees and their department where it is guaranteed 
# only 2 people will work at the same department
# return a list of tuples that contains every Employee and the other employee in their department 
# If no one else works with them, don't add them to the answer

def partners(employees):
  pass

L = [
  ("Mark", "Accounting"),
  ("Sophia", "IT"),
  ("Jeff", "Health"),
  ("Julian", "Accounting"),
  ("Bob", "IT"),
  ("Ivan", "HR"),
  ("Andrea", "HR"),
]

expected = [
  ("Mark", "Julian"), 
  ("Sophia", "Bob"), 
  ("Julian", "Mark"), 
  ("Bob", "Sophia"), 
  ("Ivan", "Andrea"), 
  ("Andrea", "Ivan")
]


************************ Section D ************************

Write a Python program to create a multiplication table that creates a grid of
 the product of numbers 
from a to b (inclusive). You don't have to format the table to look nice, but you can.
Use nested loops to generate this table.

ie, a = 2 and b = 4, we'd have

        X 2 3 4
        2 4 6 8
        3 6 9 12
        4 8 12 16

This is how it looks "graphically", but we want to have it in a grid, or a list of lists.

Thus, it would look more like:

    [
        [4, 6, 8],
        [6, 9, 12],
        [8, 12, 16],
    ]    
    
    (We don't want the 'headers' of the table)



def multiplication_table(a, b):
    pass


# assert multiplication_table(2, 4) == [[4, 6, 8], [6, 9, 12], [8, 12, 16]]
# assert multiplication_table(1, 4) == [[1, 2, 3, 4], [2, 4, 6, 8], [3, 6, 9, 12], [4, 8, 12, 16]]


Write a Python program that return the list of *unique* common elements found in two lists.

That is, given lists list1 and list2, return a list of elements that appears in list1 AND list2. In this new list though,
we should only see a given element one time.

Use two for loops in this solution.



def find_shared(list1, list2):
    pass

# assert find_shared([1, 2, 3], [1]) == [1]
# assert find_shared([8, 12, 5, 8], [8]) == [8]
# assert find_shared([8, 12, 5, 8], [8, 8]) == [8]
# assert find_shared([7, 5, 6, 2], [9]) == []


Write a Python program to count the number of times any character in a list of charachters
appears in a different, given string using a nested for loop. 

The program should take a string and a list of characters (or a different string) as input and 
return the count of occurrences that any of those characters appear in the string.

You can assume that the second string (the 'bank') will always have unique characters, no duplicates.


def find_count_of_chars(string, bank):
    pass


# assert find_count_of_chars("apple", "") == 0
# assert find_count_of_chars("apple", "a") == 1
# assert find_count_of_chars("apple", "p") == 2
# assert find_count_of_chars("apple", "Z") == 0 
# assert find_count_of_chars("apple", "ap") == 3
# assert find_count_of_chars("apple", "aple") == 5
'''
