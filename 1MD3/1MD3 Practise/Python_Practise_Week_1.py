
'''
**************** Set A ********************
Practice for: 
-Mathemtical experssions/operations
-Variables
-Booleans

1. For each of the expressions, what would be the output of the interpreter:

a) >>> 66+34 / 2
b) >>> -2*3//5
c) >>> 2**4**(1/(5//2))
d) >>> min(max(min(-3**2, 3**2), 11//-1), -(4**2) + (2*3))


3. For each of the following examples, what are the values of the variable afterwards. t using the interpreter. Use the ie may do a couple of these in lecture, depending on time.
'''
#Example 1
x = 1 + 4 #x = 5
y = x - 1 # y = 4
y = y+1 * x# y = 9
x = y//x# x = 1

#Example 2
x = 1
y = x + x
z = y + y + 1.0 
y = y//x # y = 2
x = z//x # x = 5.0 
z = x + x/4 # z = 6.25
y = y**((y*y)%3) #  y = 2

#Example 3
y = 5
x = -(-y//2)# x = 3 
z = max(min(4, y/2), x) # z = 3 
x = (y-x)**(x**z-y**2)**(y-x) # x =16
'''
4. In lecture we have seen the following boolean operators: and, or, ==, !=, not
a) Imagine you didn't have access to "and" but wanted to write an equivant statement to: a and b. Find a way to write an equivalent statement using only "not" and "or".
b) Do the same thing as in part a), but find an equivalent statement to a or b, using only "and" and "not".
c) In electrical and computer engineering a popular boolean operator to use is "exclusive-or", often spelled xor. The expression a xor b would evaluate to True if and only if one of but not both of the inputs are True. The xor operation does not come up that often in logic theory and some applications of software. Can you think why this may be the case? Hint: could we use a different operator we already have to accomplish the same behaviour?

**************** Set B ********************

#For each of the following examples, what are the values of the variable afterwards:
# Example 1
'''
x = 3
y = 4
z = 5

x = y # x = 4 
y = x**2 # y = 16
w = x+y//z # 4


# Example 2

x = 3
y = 4
z = 5
a = not(x == y)# True 
b = a and not False # True 
c = b or True # True 


# Example 3

x = 9%2# x = 1 
y = 2 % 9 # y = 2 
z = (5 % 9) // 2 # 2

#**************** Set C ********************
#Give the final assignment for all variables:

# Example 1
x = 10
y = 5
is_greater = x > y # True 
is_equal = x == y # False 
is_not_equal = x != y # True 
result = (is_greater and is_equal) or not not is_not_equal # True 

# Example 2
x = 7
y = 2
z = 8
x = max((x / y), (z - y))# x  = 6 
y = (x * z) - (y ** 2)#y= 44
z = min((z % x) + (y // z), z)# 7, 8

# Example 3 (Bonus)
x = 10 * (7) / 2**2 # x = 17.5
y = 5 + 2 + 1.0 # y = 8.0 
z = 7 # z = 7
total = sum([min(x, y, z), max(x, y, z), z]) #14+17.5 = 31.5

#**************** Set D ********************
# Variable Tracing Practice
# What are the values for each of the variables at the end of the program?
# Example 1

b = 3
a = b#a=3 
b = a - b# b = 0

c = a + b#  c = 3 
d = c % 7# d = 0 

# Example 2

a = 3 + 4 # a =7 
b = True
c = 7 == a #c = True 
d = b != c # d = False 

# Example 3

a = not (not (3 == 4)) # a =  False 
b = True and not a # b = True 
c = not False != b # c = False 


#**************** Set E ********************
# Example 1

x = 10
y = 5

z = x # z = 10 
x = y #x =  5
y = z # y = 10 

z =  not (x // y == 0) # z  =  False 


# Example 2

x = 2 ** 3 # x = 8 
y = 3 ** 2 # y = 9 
z = max(x, y) # z = 9 
result = (z % 2) == 1 # result = True 

#**************** Set F ********************
# Example 1 - Is there a difference?
x = 2
y = 6


x = x + y*y # x = 38 
z = x + y**2 # z = 74

# Example 2 - Mod Practice
x = 9
x = x % 12 # x = 9 
x = x + 3 # x = 12
x = x % 12 # x = 0 
x = x + 1 # x = 1

# Example 3 - Floor Division
x = 10 
y = 3
z = 5
a = 1 + x / 2 # a = 6
a = a // y + z # a = 7

# Example 4 - Boolean 
x = True  
y = 10
z = x and y > 5 # z = True 
z = z or x == False # True
z = z == False # False
