'''
**************************** Section A ****************************

1. Implement the following function.
'''
def make_angry(s: str)-> str:
        
        b= ''
        for char in s:
                if char == '.':
                        char = '!'
                
                c = char.upper()
                b += c


        return b

        
"""
	Creates and returns a new string equivalent to s, except all
	lowercase letters are now uppercase, and all periods are 
	replaced with exclamation points.

	>>> make_angry("I want some coffee.")
	"I WANT SOME COFFEE!"
	>>> make_angry("I'm not sure. Let's wait...")
	"I'M NOTE SURE! LET'S WAIT!!!"
"""

        
                
'''
2. Implement the following function.
'''
def exp(s):
        b = ''  
        sentence = ''
        count = 0
        for char in s:
                if char in '!?':
                        b += '.'
                else:
                        b += char
        return b

def count_sentences(s):
        b = ''  
        sentence = ''
        count = 0
        for char in s:
                if char in '!?':
                        b += '.'
                
                else:
                        b += char
        for char in b:
                if char.upper() == char and '.' in b and char.isalpha():
                       # sentence = b[b.find(char):b.find('.')+ 1]
                        b = b[b.find('.')+1:]
                        count += 1
                
                
        return count 
                
                        
'''  
                        
	"""
	Returns the number of sentences which occur in a string s. Assume a sentence is any substring
	begins with a capital letter and completes the first time one of ".", "!", or "?" occurs after said capital letter.

	>>> count_sentences("I like dogs. You like cats? I hate snakes!")
	3
	>>> count_sentences("i type like child!")
	0
	>>> count_sentences("Hmm, maybe...")
	1
	>>> count_sentences("this is a weIrd case?")
	1
	"""

3. Implement the following function.

'''
def symbol_count(s: str)-> int:
        b = ''
        for char in s:
                if char not in ' !@#$%^&*()_+=[]?/':
                        b += ''
                else:
                        b += char
        news = ''
        long = 0
        for char in b:
                news = b[:b.find(' ')]
                b = b[b.find(' ')+ 1:-1]
                if len(news) >len(b):
                        long = len(news)
        return long
                

'''
	"""
	Return the largest number of consecutive "special symbols" in the string s. A sepcial
	character is one of: !@#$%^&*()_+=[]?/

	>>> symbol_count('c0mput3r')
	0
	>>> symbol_count('H! [here')
	1
	>>> symbol_count('h3!!&o world@#')
	3
	"""

4. A "binary string" is a string composed only of the binary digits 0 and
1. Each character in the string represents a single "bit."
This question asks you to write a function that implements a binary
operation -- bitwise exclusive or (XOR) -- on two binary strings. For
two binary strings, the bitwise XOR is computed by performing XOR on
each pair of bits that have the same index. The XOR of two bits is
defined as follows:
XOR(0, 0) = 0
XOR(0, 1) = 1
XOR(1, 0) = 1
XOR(1, 1) = 0
'''
def string_xor(s1: str, s2: str)-> str:
        b = ''

        if s1 == '' or s2 == '':
                return ''
        count = 0
        
        for digit in s1:
                digit2 = s2[count]
                count += 1
                
                if (digit == '1' or  digit2 == '1') and not (digit == digit2):
                        b+= '1'
                else:
                        b += '0'
                print(digit)
                print(digit2)
                        
      
        return b


'''	
	Return the bitwise XOR of two binary strings.
	Assumption: len(s1) == len(s2)

	>>> string_xor("", "")
	''
	>>> string_xor("0", "0")
	'0'
	>>> string_xor("1", "0")
	'1'
	>>> string_xor("1011", "0010")
	'1001'


5. To speed up texting your friends, you are thinking of leaving out all vowels.
Write a Python function shorten that takes in an arbitrary string and return
your shortened string. Assume that your input will only ever be english letters
and removes all uppercase and lowercase vowels.

shorten('The quick brown fox jumps over the lazy dog')
>>> 'Th qck brwn fx jmps vr th lzy dg'

'''
def shorten (s:str) -> str:
        a = 'AEIOUaeiou'
        new = '' 

        for char in s :
                if char not in a:
                        new += char
                
        return new
        
         
'''

**************************** Section B ****************************
1) Implement the following functions
'''
def capitalize_alternate_words(string: str) -> str:
       
        new = ''
        count = 2
        
        for char in string:
             if count %2 == 0:
                     new += char.lower()
             else:
                     new += char.upper()
             if char == ' ':
                     count += 1

        return new
             
                
                
                
                
        
'''  
    Given a string, transform the string such that:
    every letter in a given word is the same capitilzation (aaa or AAA)
    the first word is lowercase, the second is uppercase, the third is lower etc. (a B c D e ...)

    You can ignore the captializations of non-letter characters.

    >>> capitalize_alternate_words("hi my name is alex.")
    'hi MY name IS alex.'
    >>> capitalize_alternate_words("apPLes Are MY favouRite Food.")
    'apples ARE my FAVOURITE food.'
    
    return

'''
def reverse_sentence(s: str) -> str:
        temp = ''
        new = ''
        s = s[::-1]
        for char in s:
                if ' ' in s:
                        temp = s[:s.find(' ')][::-1]
                        s = s[s.find(' ') + 1: ]
                        new += temp +' '
        return new[:] + s[::-1]
                
                
        
'''
    """
    Returns a verion of s but with the order of words reversed. Assume s is English text.

    >>> reverse_sentence("Hello world! This is a test.")
    "test. a is This world! Hello"
    
    """

'''
