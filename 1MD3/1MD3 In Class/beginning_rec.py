from typing import Dict, List

"""
All functions in this file are intended to be implemented recursively
"""




def fact(n:int)->int:
    """
    Returns n factorial, i.e., n!
    Assume n >= 0.
    >>> fact(0)
    1
    >>> fact(1)
    1
    >>> fact(5)
    120
    """
    if n <= 1:
        return 1
    return n * fact(n-1)



def fib(n:int)->int:
    """
    Returns nth Fibonacci number
    Assume n >= 0.
    >>> fib(0)
    0
    >>> fib(1)
    1
    >>> fib(5)
    5
    >>> fib(10)
    55
    """
    if n < 2:
        return n
    return fib(n-1) + fib(n-2)





def get_length(s: str)-> int:
    """
    Returns the length of s. Do not use len or a loop.
    """
    return


def count_char(s: str, char: str)-> int:
    """
    Returns the number of times char appears in s.
    Do not use len or a loop.
    """
    return



    
def is_palindrome(s: str)-> bool:
    """
    Returns True iff s is a palindrome.
    Do not use rev or a loop.
    """
    return



def reverse(s: str) -> str:
    """
    Returns the reverse of s.
    """
    return


def remove_first(s: str, char: str)-> str:
    """
    Returns a version of s with the first occurence of char removed.

    >>> remove_first()
    """
    return


def replace_all(s:str, old: str, new: str)->str:
    return
    

def replace_at(s:str, i: int, char: str)->str:
    """
    >>> replace_at("abcd", 2, "z")
    "abzd"
    >>> "a" + replace_at("bcd", 1, "z")
    >>> "a" + "b" + replace_at("cd", 0, "z")
    """
    return


def delete_at(s:str, i: int)->str:
    return

def delete_chunk(s: str, start: int, end: int):
    """
    >>> delete_chunk("0123456789", 3, 7)
    "01289"
    """
    return
    
        

def is_near_palindrome(s: str)-> bool:
    """
    Returns True iff s can be turned into a palindrome by removing
    up to 1 character.

    >>> is_near_palindrome("tacocamt")
    True
    >>> is_near_palindrome("11111111101111")
    True
    >>> is_near_palindrome("111111111011110")
    False
    """
    return
    

def is_k_near_palindrome(s: str, k: int)-> bool:
    """
    Returns True iff s can be turned into a palindrome by removing
    up to k characters.
    """
    return
    



def power_set(L: List[int]) -> List[List[int]]:
    """
    Returns the powerset of L.
    """
    pwerset = []
    if L == []:
        return [[]]
    return power_set(L[1:0]) + add_to_each(power_set(L[1:0],L[0]))


    


def add_to_each(sets, x):
    L = sets.copy()
    for sub_list in L:
        sub_list.append(x)
    return L

def hanoi (source, target,spare,n):
    if n == 1:
        print("Move a disk from peg",source, "to peg",target)
    else:
        hanoi(source,spare,target,n-1)
        print ("Move a disk from peg",source,"to peg",target)
        hanoi(source,spare,target,n-1)

    
        
        
        
def num_bitstrings(n):
    if n <= 3:
        return 2**n
    return num_bitstrings(n-1)+num_bitstrings(n-2)+num_bitstrings(n-3)+num_bitstrings(n-4)
#not best implmentation, slow

    


    
    
'''
[4,1,5,6]

h = [4]
t = [1,5,6]

a = [1]
b = [4,5,6]

f(a) = [1]

f(b)= 


b = [4]
'''