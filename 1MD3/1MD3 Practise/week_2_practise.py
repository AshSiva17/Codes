
'''
******** Section A ******** 

Assume the following two functions have be run in the Python
shell/interpreter:
'''
def math1(x,y,z): # 2 , 1 ,1 
	z = a + 1 # z = 2
	c = x + 1 # c = 3
	y = c + 1 # y = 4
	return x + y + z # 8

def math2(a,b,c):
	a = a + c # 4
	b = math1(a,b,c) #12
	c = x + 1 # 3
	return a + b + c # 19
'''
For each of the following examples write what will be outputted to the screen
in place of each "???", if anything at all. If at any point you think Python will
error, state why.

a)
'''
#>>> math1(1,1,1)
#error no a 
'''
b)

>>> a = 2
>>> c = 4
>>> b = math1(a,a,a)
>>> a
2
>>> b
7
>>> c
4
>>> z
error becuase not defined globally 

c) 

>>> a = 1
>>> x = 2
>>> math2(2,1,2)
19
>>> x
2
>>> a
1

Now consider adding the following two functions to the shell/interpreter
(note they are both very similar to math2).
'''

def math3(a,b,c): #(1,1,1)
	a = a + c # a = 2 
	b = math1(a,b,c) # b = 8
	x = 5 # x = 5
	c = x + 1 # c = 6
	return a + b + c # 16

def math4(a,b,c):#(1,1,1)
	a = a + c # a = 2
	b = math1(a,b,c) #8
	c = x + 1# c = 2
	x = 5
	return a + b + c # 12
'''
d)

>>> a = 1
>>> x = 1
>>> math3(x,a,x)
16
>>> x
1

e)
>>> a = 1
>>> x = 1
>>> math4(x,a,x)
error because theres a local x declared after c = x + 1
>>> x
1

2. Write a function print_circle_info(radius) which computes the area and
circumference of a circle and prints it to the screen.

For example
>>> radius = 5
>>> print_circle_info(radius)
The area of the circle is: 78.54
The circumference of the circle is: 31.42
'''

def print_circle_info(radius):
        area = str(3.14 * radius**2)
        circumference = str(2 * 3.14 * radius)

        print (" The area of the circle is:" + area)
        print(' The circumference of the circle is:' + circumference )
        

'''

3. Write a function called in_range that takes an integer parameter and
returns true if the value of the parameter is an integer from 1 to 3 inclusive.
The only operators you are allowed to use in your code is
arithmetic/comparison/Boolean.You are not allowed to use "if" statement or
other concepts not covered in class.
'''
def in_range(value):
        return value == 1 or value == 2 or value == 3

'''

Examples of use:
>>> in_range(1)
Trueprint_circle_info(12)
>>> in_range(2)
True
>>> in_range(3)
True
>>> in_range(0)
False
>>> in_range(3.4)
False
>>> in_range(2.5)
False
>>> in_range(1.0)
True

4. If a dependent child is a person under 18 years of age who does not earn
$10,000 or more a year, which expression would define a dependent child?
A. age < 18 and salary < 10000 # this one 
B. age < 18 or salary < 10000
C. age <= 18 and salary <= 10000
D. age <= 18 or salary <= 10000


This is not a trick question, it is meant to aid in translating English to logic.
It was actually taken from a past exam.

5. Write a function that takes as input three lengths and returns True only
when there is a right-triangle with these sides. Do not use an if-statement.

def exists_triangle(x : float, y : float, z : float) -> bool:
	"""
	Returns True iff there exists a right-triangle with side lengths x, y, z.
	>>> exists_triangle(3,4,5)
	True
	>>> exists_triangle(5,3,4)
	True
	>>> exists_triangle(1,0,10)
	False
	"""
'''

def exists_triangle(a,b,c):
        return a**2 + b**2 == c**2 or c**2+a**2 == b**2 or c**2+b**2 == a**2

'''

Hint: Any three of the inputs could potentially be the hypotenuse. What has
to be true about the hypotenuse length compared to the other two values?

6. Write a function that takes the coefficients from ax^2 + bx + c and 
returns the largest solution to the quadratic equation. You may assume that
input will always have real solutions. 

def solver(a : float, b : float, c: float) -> float:
	"""
	Returns the largest value of x such that ax^2+bx+c=0
	>>> solver(2, -8, 6)
	3
	"""
'''
def solver(a,b,c):
        if b**2-4*a*c <0:
                return "no solutions"
                
        x = (-b + (b**2-4*a*c)**(1/2))/(2*a)
        return x

'''


******** Section B ********

What is the output of these examples:

 

#1 Function Definition & Call:



def sum1(a,b,c):

    return (a + b + c)

    

x = 1

y = 2

z = 3

print (sum1(x,y,z))

#6



#2a Understanding Scope

 

def changeA(a):

    a = 3
    return a

 

def sum1(a,b,c):

    changeA(a)# no return 

    return (a + b + c)

    

x = 1

y = 2

z = 3

print (sum1(x,y,z))
#6


#2b 

 

def changeA(a):

    a = 3

    return a 

    

def sum1(a,b,c):

    a = changeA(a)

    return (a + b + c)

    

x = 1

y = 2

z = 3

print (sum1(x,y,z))
#8


 

#3 Lost in the Sauce

 

def changeA(a):

    a = 3

    return a 

    

def multiply(a,b):

    return (a*b)

def sum1(a,b,c):

    a = changeA(a) # a = 3

    b = multiply(a,a) # b = 9 

    sum1(a,b,c) # error loops forever 

    return (a + b + c)

    

x = 1

y = 2

z = 3

print (sum1(x,y,z))



#******** Section C ********

#Problem 1: Function Tracing

a = 5

def m1(a,b):
    return a % b # 1


def m2(a, b):
    b = b + a # b = 3
    a = m1(a, a) # a = 0
    return b - a # 3

a = 2
print(m2(m1(4,3), 2))


#******** Section D ********

# Example 1
def func1(x):
    x = x**2
    return x




def func2(y):
    x = 8+y # 18
    z = func1(x)
    x *= 3 # 54
    return x

def func3(x, y):
    z = x + y# 6
    z = func2(z)# 42
    return z



# What will these print out?
print(func1(12))#144
print(func1(5))#25

print(func2(5))#39
print(func2(10))#54

print(func3(2, 4))#42


******** Section E ********

For example 1 and 2, write what will be printed to the screen, if anything at all.
If at any point you think Python will error, state why.

Example 1

def outer(x,y,z):
  def inner(x,y,z):
    def evenmoreinner(x,y,z):
      return x+y+z+1#10
    return evenmoreinner(x,y,z)+1#11
  return inner(x,y,z)#11

x = 2
y = 3
z = 4
print(outer(x,y,z))

#Example 2
def outer(x,y,z):
  def inner():
    def evenmoreinner(x,y,z):
      return x+y+z+1#10
    evenmoreinner(x,y,z)
    return x+y+z#9
  return inner()
x = 2
y = 3
z = 4
print(outer(x,y,z))#9

Example 3
I want this program to loop through all numbers from 1 to 20 and print out if a number is a divisor of 2, 3 or 6.
If it isn't a divisor of anything, print "Not a divisor of anything". 
If a number is a divisor of more than one of those, I want it to pick the largest divisor and print that.
What is wrong with this code? Fix it!

Note: a for loop will loop from the first number (inclusive) to the last number (exclusive)

for i in range(1,21):
  if i % 6 == 0:
    print("Divisor of 6: ", i)
  elif i % 3 == 0:
    print("Divisor of 3: ", i)
  elif i % 2 == 0:
    print("Divisor of 2: ", i)
  else:
    print("Not a divisor of anything: ", i)
'''
#******** Section F ********

# What would happen if you executed these code snippets?

# 3
'''
def func1(a):
    def func2(b):
        print(b)
        func1(a)
    func2(a)

func1(10)
#10 #error

# 2

def func1(a):
    def func2(b):
        print(b)
        func1()

    func2(a)

func2(10)
#error
'''
# 3

def func1(x, y, z):
    def func2(z, y, x):
        print(z // y + x)

    func2(x, y, z)

func1(10, 20, 30)#30

