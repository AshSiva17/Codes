from typing import List, Dict, TextIO

import random


def associate_pair(d: Dict[str, List[str]], key: str, value: str):
    '''Add the key-value pair to d. keys may need to be associated with
    multiple values, so values are placed in a list.
    Assumption: key is immutable
    '''
    if key in d:
        d[key].append(value)
    else:
        d[key] = [value]

    


def make_dictionary(file_name: str) -> Dict[str, List[str]]:
    '''Return a dictionary where the keys are words in f and the value
    for a key is the list of words that were found to follow the key in f.
    '''
    d = {}
    file = open(file_name, 'r')
    context = ('','')

    for line in file: 
        words = line.split()
        for word in words:
            associate_pair(d,context,word)
            context = context[1:] +(word,)

    associate_pair(d,context,'')
    file.close
    return d

    


def mimic_text(word_dict: Dict[str, List[str]], num_words: int) -> str:
    """Based on the word patterns in word_dict, return a string that mimics
    that text, and has num_words words.
    """
    story = ''
    context = ('','')
    

    for i in range(num_words):
        words = d[context]
        next_word = words[random.randint(0,len(words) - 1)]
        story += next_word + ' '
        context = context[1:] + (next_word,)


    return story 



def append_dictionary(file_name: str, d: Dict[str, List[str]], k:int) -> None:
    #TODO
    return 




def mimic_text2(word_dict: Dict[str, List[str]], num_words: int, k: int) -> str:
    #TODO
    return










