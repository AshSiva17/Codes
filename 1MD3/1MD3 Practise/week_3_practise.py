'''
**************** Section A ****************


1. Assume, s = '1234567'
What is the value of each of the following:
a) s[len(s)]#error out of bounds
b) s[len(s) - 2]#6
c) s[-2]#6
d) s[-5:-2]#345
e) s[-2:-5]#''
f) s[-2:-2]#''
g) s[:-3]#1234
h) s[0:7]#1234567
i) s[0:6]#123456
j) s[5:]#67
k) s[7:]#error
'''
s = '1234567'

'''
2. Note that a string can be indexed immediately after being sliced.
>>> x = '0123456789'
>>> x = [3:9][2]
5

a) Write a single computation that first creates a String of all even numbers in x, and then finds the 3rd even number in x.'
'''
x = '0123456789'
x[2::2][2]

'''
Similar to above, multiple slices of a string can be made simultaneously.
>>> x = '0123456789'
>>> x[::3][::-1]
'9630'
>>> x[2:8][::3]
'25'

>>> x[a:b:c][x:y:z]

b) Play around with it, does Python slice a:b:c or x:y:z first? Does order matter?


3. Consider the following function:
'''
def f(x):
	if x > 10:
		if x > 100:
			print("A")#130
		elif x > 200:
			print("B")#never
		else:
			print("C")#11
	elif x < 50:
		if x < 40:
			print("D")#39
		else:
			print("E")#41
		if x < 30:
			print("F")#21
		else:
			print("G")#32
	if x >= 10:
		print("H")#200
	elif x >= 10:
		print("I")#never


'''
a) What will be outputted to the screen in place of each "???", if anything at
all. If at any point you think Python will error, state why.

>>> f(11)#ch
???
>>> f(300)#AH
???
>>> f(5)#D
???

b) For each letter "A" through "I", give a value of x which will result in f(x)
printing out that letter (it doesn't need to just print out that letter). If you
think a specific letter can never be printed, explain why.

c) Repeat parts a) and b), but change the print statements in f,
to return statements.

**************** Section B ****************
Example 1
s = "123456789"
What is the value of each of the following:
a) s[len(s) - 1]#9
b) s[0:len(s) - 1]#12345678
c) s[::-1]#987654321


Assume, s = "Hello World!".
Using string slicing.

d) How could you obtain "Hello"?
s[:5]

e) How could you obtain "World!"?
s[6:]

f) How could you obtain "!dlroW"?
s[6:][::-1]

g) How could you obtain "HloWrd"?
s[::2]


Example 2
x = "987654321"
Given x, use slicing to get the string of all odd numbers in order (i.e. "13579"). Bonus: do it in one line.
x[::2][::-1]

Example 3
s1 = "ABC"
s2 = "XYZ"
What will the following print? If at any point you think Python will error, explain why.
a) s1[0] = "B"
    print(s1)
    error not mutable
b) s1 = "B" + s1[1:len(s1)]BC
    print(s1)
    BBC
c) s3 = s1[0] + s2[::-1][0] + s1[1] + s2[::-1][1] + s1[2] + s2[::-1][2] # A + Z + B + Y + C + X
    print(s3)
#AZBYCX

**************** Section C ****************
# Example 1

# When writing code, it is important to make it simple to read so other
# programmers can understand what you are doing.
# Simplify this function to be more readable (i.e. Try to simplify the nested ifs)
'''
def get_grade(score):
    if score >= 60:
        if score >= 80:
            if score >= 90:
                return "A"
            else:
                return "B"
        
    
        return "F"

# Example 2

# Remove the redundant code 

def math(a,b,c):
    if a + b > 30:
        return "A"
    elif c > 30:
        if a >= b:
            return "B"
       
    elif c > 35:
        return "D"
    else: 
        if a + b > 30:
            return "E"
        return "F"

    return 0
'''

def check_grade(score):
    if score >= 90:
        if score >= 95:
            return "A+"
        else:
            return "A"
   
     if score >= 80:
        if score >= 85:
                return "B+"
        else:
                return "B"

        
    return "C"


# Example 3

# Some aliens have sent us a message, but it seems to have been distorted!
# Our analysts say that the distorted message can be decoded using the following method:
# - Split the string in half
# - Read every third character of the first half
# - Skip five characters of the second half, and then read every second letter in reverse
# - then join the two halves with a space
# What is the message?

'''
msg = "hbce ul tlnxomhn ghhet rla2e"


mid = len(msg)//2

first_half = msg [:mid]
second_half = msg [mid: ]

first_half_decoded = first_half [ : : 3]

second_half_decoded = second_half [ :4 :-2] 

decoded_message = first_half_decoded + " " + second_half_decoded

print (decoded_message)


half = len(msg)//2
first_half = msg[:half]
second_half = msg[half:]

#for first half
fst = first_half[::3]

snd = second_half [ 4:] [::-2]

print(fst +' ' + snd)

'''
**************** Section D ****************

# Example 1: Which conditionals can be ommited? In other words, what branches can never be
# run?
#
# Can you write a simplified version of this code that removes all the unecessary if statements but still behaves the same?

def func3(x, y):
   
    if y == 0:
        print(x)

    else: 
        print(y)


#
#
# Example 2: Strings
#
a = "abc"
b = "def"

c = (a + b)#abcdef[1:5]bcde[0]b
print(c)
#

'''

#Week 4 PRACTISE

vowels = "aeiouAEIOU"

def replace_vowels (s: str) -> str:
        b = ' '
        for char in s:
                if char in vowels:
                        b += "*"
                else:
                        b += char

        return b

def count_alpha (s: str) -> int:
        i = 0
        for char in s:
                if char.isalpha():
                      i += 1
                
        return i

def reverse_no_spaces (s: str) -> str:
        b = s[::-1]
        c = ''
        for char in s:
                if char not in "\n \t  ":
                        c += char
                
        return c


def betterreverse_no_spaces (s: str) -> str:
        b = s[::-1]
        c = ''
        for char in s:
                if char not in "\n \t  ":
                        c += char
                
        return c

def is_palindrome(s:str) -> bool:
        filtered_string = ''
        
        for char in s:
                if char.isalpha():
                        filtered_string += char.lower()

        return filtered_string == filtered_string[::-1]
msg = "hbce ul tlnxomhn ghhet rla2e"


