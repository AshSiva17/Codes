'''

**************************** Section A *******************************

1) Re-implement the function below using a while-loop instead of a for-loop:
'''
def count_vowels(s):
        
	num = 0
	for char in s:
		if char in "aeiouAEIOU":
			num += 1
	return num
'''
        num = 0
        track = 0 
        while num < len(s):
                if s[num] in 'aeiouAEIOU':
                        track += 1
                num += 1
        
        return track
        
'''
'''
2) Consider the list L = [1,2,3,4,5,6,7,8,9,10], using slicing notation, i.e.
L[1:3], L[-1], L[2:8:2], etc., write an expression which would yield:
a) [4,5,6]
b) [1,2,3,4,5,6,7,8,9,10]
c) [1,3,5,7,9]
d) [6,5,4]
e) [10,1]

3) Consider the code below:
'''
def do():
        for i in range(9,4,-2):
                print(i)
'''
What is written to the shell when the code is run with the following inserted
into the "blank" part of the code:
a) range(5)
        0 1 2 3 4
b) range(4,8)
        4 5 6 7
c) range(8,8)

d) range(-1)
        
e) range(5,10,2)
        5 7 9 
f) range(5,11,2)
        5 7 9
g) range(4,9,-1)
        
        
h) range(4,9,-2)

i) range(9,4,-1)
        9 8 7 6 5 
j) range(9,4,-2)
        9 7 5

4) Implement the function below in two different ways, the first using a forloop,
the second using a while-loop.
'''
def get_front_list(L:list[int], target:int) -> list[int]:
        
        """
	Takes in a list of positive non-zero integers L, where the sum of all
	numbers of L is greater than or equal to target. Returns a sub-list of the
	first n numbers of L, such that these numbers sum to something greater than
	or equal to target, but the first n-1 elements would sum to something less
	than target.

	Examples:
	>>> get_front_list([3,7,5,2,10,4,7,1],16)
	[3,7,5,2]
	>>> get_front_list([3,7,5,2,10,4,7,1],31)
	[3,7,5,2,10,4]
	>>> get_front_list([100,1,2,3,4],10)
	[100]
        
        b = []
        
        total = 0
        for num in L:
                if total < target:
                        total += int(num)
                        b.append(num)
                
        return b
        """
        num = 0
        b = []
        total = 0
        while num < len(L):
                if total < target:
                        total += int(L[num])
                        b.append(L[num])
                num += 1

        return b
                
                

'''
5) For those of you who like challenges, try writing this function (if you don't get
it, do not worry about it, it's a bit tricky.)
'''
def longest_palindrome(s: str) -> int:
        
	"""
	Return the length of the longest palindrome in s.
	>>> longest_palindrome('')
	0
	>>> longest_palindrome('a')
	1
	>>> longest_palindrome('ab')
	1
	>>> longest_palindrome('banana')
	5
	"""
	return
'''
6) This is way out of scope for this week, but think about this question, given a string s and and number k, can I turn s into a
palindrome by removing up to k characters.


**************************** Section B *******************************
# Example 1
# Alex is a SPENDER. He has x dollars and wants to buy the most expensive thing he can.
# You are given a list of products subtotals (WITHOUT TAX) (tax is 13%)
# Return the subtotal of the most expensive product Alex can buy
# Ex. given products = [30,33,46,51,100,42], x = 50
# Alex can buy the $42 product as it comes to $47.46 with tax. The $46 product comes to $51.98 which is too expensive (over $50).
# So the function should return 42
# If he can't buy anything, return -1
'''
def spender(products, x):
        current = 0
        for product in products:
                if product*1.13 <= 50 and product > current:
                        current = product
                        print(current)
        return current
                        
'''
# assert spender([30,42,46,51,100,24], 50) == 42
# assert spender([30,42,46,51,100,24], 10) == -1

# Example 2
# Given a list, return the median number
# Remember: Median is the value in the middle of a sorted data set
# Try to solve this with AND without loops
# (Hint: my_list.sort() sorts my_list)

def median(arr):
        mid = len(arr)//2
        return arr[mid-1]
  pass

# assert median([1,2,4,6,7,7,7,8,9]) == 7
# assert median([14,12,2,3,20,18,9,1,14,13,3,6,22]) == 12


# Example 3
# Given an integer, return the sum of all digits in the integer
'''
def sum_da_digits(num):
        
        s = str(num)
        total = 0
        for digit in s:
                  total += int(digit)
        return total 

  
'''
assert sum_da_digits(2637816241689) == 63
assert sum_da_digits(12345) == 15


**************************** Section C *******************************

# Example 1
# Loop over a list to check if it is sorted
# Try doing this with for loops and while loops
# Extra: how would you do it with a for loop, but 
# using list slicing instead of indexing?
'''
def is_sorted(L: list[int]) -> bool:
        previous = L[0]
        for num in L:
                if num < previous:
                        return False
                else:
                        previous = num
        return True 
                

'''
# Example 2
# Use loops to calculate the factorial for a number.
'''
def factorial(n: int) -> int:
        num = n
        total = 1
        while n > 0:
                total *= n
                n += -1
        return total 
                
        
        

'''
# Example 3
# This function requires use of python's range() function.

# Write a function that prints the string representation 
# of all numbers from 1 to n based on the following rules:

# If it's a multiple of 3, print it as "fizz".
# If it's a multiple of 5, print it as "buzz".
# If it's a multiple of both 3 and 5, represent it as "fizzbuzz".
# If it's neither, just return the number itself.
'''
def fizzbuzz(n: int):
        for number in range(1,n+1):
                if number % 5 == 0 and number % 3 == 0:
                        print("fizzbuzz")
                elif number % 5 == 0:
                        print("buzz")
                elif number % 3 == 0:
                        print ("fizz")
                else:
                        print(str(number))
                

