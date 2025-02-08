import random

i = 1
k = 10
print("Before the loop.")
while i < k:
    print("Hello, i = ", i)
    i = i + 1 
print("After the loop.")



def remove_whitespace(s: str) -> str:
    """
    Returns a version of s, but with all white-spaces removed.
    USE A WHILE LOOP TO IMPLEMENT IT.

    >>> remove_spaces("Hello world!")
    "Helloworld!"
    >>> remove_spaces("010101101001")
    "010101101001"
    >>> remove_spaces("\ta \n\t  \tb\n c\t")
    "abc"
    """
    new_message = ""

    for char in s:
        if char not in " \n\t":
            new_message = new_message + char
    return new_message





def guess() -> None:
    '''Engage the user in a guessing game. Repeatedly ask the user to select
    a number between 1 and 10, until they guess the correct (randomly
    selected) number.
    '''
    num = random.randint(1,10000)
    guess = int(input("Guess: "))
    while guess != num:
        if guess > num:
            guess = int(input("To high, Guess Again: "))
        else:
            guess = int(input("Too low, Guess Again: "))
    print ("you got it")

























def is_good_password(s: str) -> bool:
   """
   Return True iff s is a "good password". A password is good if
   following are met:
   1) Must have a number
   2) Must have a special character: !@#$%^&*()
   3) length has to be at least 8
   4) Must have an uppercase
   5) must have a lowercase
   *****
   6) no spaces
   """
   return cond1(s) and cond2(s) and cond3(s) and cond4(s) and cond5(s)

def cond1(s):
   for char in s:
      if char.isnumeric():
         return True
   return False


def cond2(s):
   for char in s:
      if char in "!@#$%^&*()":
         return True
      else:
         return False
   return


def cond3(s):
   return len(s) >= 8


def cond4(s):
   return s.lower() != s
   

def cond5(s):
   return s.upper() != s



"""
answer = random.randint(1, 100)
    guess = input("Guess a number between 1 and 100: ")
    while int(guess) != answer:
        if answer < int(guess):
            guess = input("Sorry, the number is less than " + guess)
        else:
            guess = input("Sorry, the number is greater than " + guess)

    print("Got it!")

"""
