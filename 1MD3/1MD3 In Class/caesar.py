def encrypt_letter(letter: str, key: int) -> str:
    """
    Encrypt letter by shifting it key places to the right and returning
    that value. Assume len(letter) == 1.

    >>> encrypt_letter("a")
    "d"
    >>> encrypt_letter("m")
    "p"
    """
    return chr (ord (letter) + int) 


def decrypt_letter(letter: str) -> str:
    #TODO
    return chr (ord (letter) - 3)


def encrypt_message(message: str, key: int) -> str:
    """
    Return the given message encrypted using encrypt_letter on each
    alphabetic character in the word.

    >>> encrypt_message("cat", 3)
    "fdw"
    >>> encrypt_message("cat", 0)
    "cat"
    >>> encrypt_message("", 3)
    ""
    """
    
    for char in message:
        #print(char)
        new_message = new_message + encrypt_letter(char, key)
        
    #TODO
    return new_message
        
    

def decrypt_message(message: str, key: int) -> str:
    #TODO
    new_message = new_message + decrypt_letter(char, key)
    return


def get_most_common(s: str)-> str:
    """
    Returns the char that occurs the most in s. If there is a tie,
    the tie will be broken by which character occured first.

    >>> get_most_common("abcda")
    "a"
    >>> get_most_common("10010")
    "0"
    """
    '''
    current_max = s[0]
    for char in s:
        if s.count(char > s.(current_max):
                   current_max = char
    return current_max
    


def remove_spaces(s:str) -> str:
for char in s:
    if char not in ' \n\t':
        new_string += char

return

prev_char = chr(ord('a') -1) 
def in_alphaorder (s:str) ->  bool

for char in s:
    if char < prev_char
    return False
prev = char

return True
'''
i=0
empty =  " "
def remove_nth_occurence(s: str, c: str, n: int) -> str:
    for char in s:
        if char == c:
            i +=1
            if i!=n:
                empty += char
        
        else:
            empty += char

    return empty 
        




def guess_key(common, guess):
    return ord(common) - ord(guess)

def is_good_password(s:str) -> bool:
    
    return cond1 (s) and cond2 (s) and cond3 (s) and cond4 (s) and cond5 (s)


def cond1(s):

    for char in s:
        if char in '!@#$%&*()':
            return True 
    
    return False 

def cond2(s):
    
    return len(s) >= 8

def cond3(s):
    
    return s.lower() != s

def cond4(s):

    for char in s:
        if char.isnumeric:
            return True
    
    return False 

def cond5(s):
    return s.upper() != s

secret = "ᘇᘛᘘᗓᘔᘡᘦᘪᘘᘥᗓᘧᘢᗓᘧᘛᘘᗓᘙᘜᘥᘦᘧᗓᘤᘨᘘᘦᘧᘜᘢᘡᗓᘢᘡᗓᘧᘛᘘᗓᘠᘜᘗᘧᘘᘥᘠᗓᘜᘦᗭᗓᘫᗓᗰᗓᗬ"

























