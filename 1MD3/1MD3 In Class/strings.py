"""
Let s = "abcdefghijklmnop"

Write some slicing expressions to arrive at the follow
1) "lmnop"
2) "efg"
3) "op"
4) "fjn"
5) "edcb"
"""








def change_at(s:str, char:str, i:int)-> str:
    """
    Creates and returns a new string, such that the new string is
    equivalent to s, with the exeception that at index i, the
    new string has the value char. It is assumed, 0 <= i < len(s),
    and len(char) == 1.

    >>> change_at("abc", "z", 1)
    "azc"
    >>> change_at("test string", "_", 4)
    "test_string"
    """
    #TODO
    return 








def slice_out(s:str, i:int, j:int)-> str:
    """
    Takes in a string s and returns a new string equivalent to s
    but with all characters between i and j (inclusive) removed.

    Examples:
    >>> slice_out("qwerty", 1, 4)
    "qy"
    >>> slice_out("test string", 0, 10)
    ""
    """
    #TODO
    return 










def get_shorter(s1:str, s2:str)-> str:
    '''
    Return the shorter of the two provided strings, s1 and s2.
    
    >>> get_shorter("", "cat")
    ''
    >>> get_shorter("kitten", "cat")
    'cat'
    >>> get_shorter("cat", "dog")
    'dog'
    '''
    #TODO
    return



'''
1) How many bitstring are there of length n: 2^n because there are 2 options for each bit and n bits

2) How many bitstrings are there of length n which do not have 4 or more consecutive 0s 

1: 1(n-1)
2: 01(n-2)
3. 001(n-3)
4. 0001(n-4)

if len == 1
    1
if s[0] == 1
    rec(s[3:])
if s[0:2] == 01
    rec(s[2:])
if s[0:3] == 001
    rec(s[3:])
if s[0:4] == 0001
    rec(s[4:])



3)4 -> k




'''