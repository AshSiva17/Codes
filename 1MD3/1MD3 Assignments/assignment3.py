from typing import List

def insert(data, s: str)-> None:
    if s == "":
        return
    if len(s) == 1:
        if s in data:
            data[s][1] = True
        else:
            data[s] = [{}, True]
    if s[0] in data:
        insert(data[s[0]][0], s[1:])
    else:
        data[s[0]]= [{}, False]
        insert(data[s[0]][0], s[1:])


def count_words(data)->int:
    """
    Returns the number of words encoded in data. You may assume
    data is a valid trie.

    >>> data = {}
    >>> insert(data, "test")
    >>> insert(data, "testing")
    >>> insert(data, "doc")
    >>> insert(data, "docs")
    >>> insert(data, "document")
    >>> insert(data, "documenting")

    >>> count_words(data)
    6
    """
    '''
    count = 0
    for i in data.values():
        
        if i[1] == True:
            count += 1
        count += count_words(i[0])
    '''
        
    return len(get_words(data))
        
            
            
    #return count


def contains(data, s: str)-> bool:
    """
    Returns True if and only if s is encoded within data. You may
    assume data is a valid trie.

    >>> data = {}
    >>> insert(data, "tree")
    >>> insert(data, "trie")
    >>> insert(data, "try")
    >>> insert(data, "trying")
    
    >>> contains(data, "try")
    True
    >>> contains(data, "trying")
    True
    >>> contains(data, "the")
    False
    """
    if s == '':
        return False
    
    words = get_words(data)
    return s in words

def get_words(data) -> list:
    words = []
    for char in data:
        if data[char][1]:
            words.append(char + rest(data[char][0], char))
        else:
            for word in rest(data[char][0], char):  
                words.append(word)
    return words

def rest(data, prefix):
    words = []
    for char in data:
        if data[char][1]:
            words.append(prefix + char)
        for word in rest(data[char][0], prefix + char):  
            words.append(word)
    return words
    
    
    

def height(data)->int:
    """
    Returns the length of longest word encoded in data. You may
    assume that data is a valid trie.

    >>> data = {}
    >>> insert(data, "test")
    >>> insert(data, "testing")
    >>> insert(data, "doc")
    >>> insert(data, "docs")
    >>> insert(data, "document")
    >>> insert(data, "documenting")

    >>> height(data)
    11
    """
    
    
    l = get_words(data)
    most = 0
    for i in l:
        if len(i) > most:
            most = len(i)
    
            
            
    return most
 

def count_from_prefix(data, prefix: str)-> int:
    """
    Returns the number of words in data which starts with the string
    prefix, but is not equal to prefix. You may assume data is a valid
    trie.

    data = {}
    >>> insert(data, "python")
    >>> insert(data, "pro")
    >>> insert(data, "professionnal")
    >>> insert(data, "program")
    >>> insert(data, "programming")
    >>> insert(data, "programmer")
    >>> insert(data, "programmers")

    >>> count_from_prefix(data, 'pro')
    5
    """
    if prefix == '':
        return 0
    
    l = get_words(data)
    a = len(prefix)
    count = 0
    for i in l:
        if i[:a] == prefix and len(i) > a:
            count += 1
    
        
    return count
    

def get_suggestions(data, prefix:str)-> List[str]:
    """
    Returns a list of words which are encoded in data, and starts with
    prefix, but is not equal to prefix. You may assume data is a valid
    trie.

    data = {}
    >>> insert(data, "python")
    >>> insert(data, "pro")
    >>> insert(data, "professionnal")
    >>> insert(data, "program")
    >>> insert(data, "programming")
    >>> insert(data, "programmer")
    >>> insert(data, "programmers")

    >>> get_suggestions(data, "progr")
    ['program', 'programming', 'programmer', 'programmers']
    """
    if prefix == '':
        return []
    
    
    l = get_words(data)
    suggestions = []
    a = len(prefix)
    for i in l:
        if i[:a] == prefix and len(i) > a:
            suggestions.append(i)
            
    return suggestions

    




    

    
