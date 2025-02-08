from typing import Dict, List


def combine1(d1: Dict[int, List[int]], d2: Dict[int, List[int]]) -> Dict[int, int]:
    """
    Return the dictionary where each key is a key
    that is either in d1 or d2.

    The value associated with each key in the new
    dictionary is a list containing and only contain values
    assoicated with the key in the original dictionary.

    >>> d1 = {1 : [2], 3: [4], 5: [6, 7]}
    >>> d2 = {3: [4, 10], 5: [1]}
    >>> d3 = combine1(d1, d2)
    >>> d3[1]
    [2]
    >>> d3[3]
    [4, 4, 10]
    >>> d3[5]
    [6, 7, 1]
    """
    return


def combine2(d1: Dict[int, List[int]], d2: Dict[int, List[int]]) -> Dict[int, int]:
    """
    Return the dictionary where each key is a key
    that is either in d1 or d2.

    The value associated with each key in the new
    dictionary is the sum of all the integers associated
    with that key in d1 and d2.

    >>> combine({1:[2], 4:[5, 6]}, {4:[8]})
    {1: 2, 4: 19}
    """
    d = {}
    for key in d1:
        d[key] = sum (d1[key])
    for key in d2:
        if key in d:
            d[key] +=  sum(d2[key])
        else:
            d[key] = sum(d2[key])
    return d

def invert(d: Dict[int, int])-> Dict[int, List[int]]:
    inverted = {}
    for key in d:
         if d[key] in inverted:
             inverted(d[key]).append(key)
         else:
             inverted(d[key]) = [key]
    
    return inverted 

def strip_punctuation(s: str)-> str:
    new = ""
    for char in s:
        if char.isalpha() or char == " ":
            new += char
    return new

def get_word_counts(s: str)-> Dict[str, int]:
    """
    >>> s = "Hello, this is an English sentence. This is a second English sentence!"
    >>> get_word_counts(s)
    {'Hello': 1, 'this': 1, 'is': 2, 'an': 1, 'English': 2, 'sentence': 2,
     'This': 1, 'a': 1, 'second': 1}
    """
    return










    


