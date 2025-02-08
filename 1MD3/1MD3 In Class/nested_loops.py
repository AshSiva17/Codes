from typing import List

L = [[1,2,3],[4,5,6],[7,8,9]]
L1 = [[1,2],[4],[7,8,9,10,11]]

def f(L):
    for sub_list in L:
        print("Beginning of outter loop.")
        for num in sub_list:
            print("Beginning of inner loop.")
            print(num)

def g(L):
    for i in range(len(L)):
        for j in range(len(L[i])):
            print("i, j  = " + str(i) + ", " + str(j))
            print(L[i][j])

def h1(n):
    for i in range(n):
        for j in range(n):
            print("i, j  = " + str(i) + ", " + str(j))

def h2(n):
    for i in range(n):
        for j in range(i):
            print("i, j  = " + str(i) + ", " + str(j))

def f2(L):
    for i in range(len(L)):
        L[i] = 0
    return 99


    


def max_row_sum(m: List[List[int]]) -> int:
    """
    Checks the sum of all the rows in m, and returns the max value.
    Assume all non-empty rows sum to something greater than 0

    >>> max_row_sum([[4, -1, 7], [5, 10, -2], [0, 5, 6]])
    13
    [[4, -1, 7],
     [5, 10, -2],
     [0, 5, 6]]
    >>> max_row_sum([])
    0
    >>> max_row_sum([[-5, 7, 1], []])
    3
    """
    #TODO


def col_sums(m: List[List[int]]) -> List[int]:
    """
    Return a new list that contains the sum of each column in lst. 
    (Consider L to be a matrix where each element is a row.) 
    Assumption: All elements in L are of the same length.

    >>> col_sums([[5, 10, 15], [1, 2, 3]])
    [6, 12, 18]
    """
    #TODO
    

'''
def transpose(m: List[List[int]])-> None:
    """
    Mutates m to be the transpose of itself. Assume m is a
    valid square  matrix.

    >>> L = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    >>> transpose(L)
    >>> L
    [[1, 4, 7], [2, 5, 8], [3, 6, 9]]
    """
    #TODO
    transpose = [] 
    for i in range (len (m)):
           for j in range (len(m[0]):
                           temp = m[i][j]
                           m[i][j] += m[j][i]
                           m[j][i] = temp
                           #or m[i][j],m[j][i] = m[j][i], m[i][j]
        return transpose 
            
'''
def is_valid_groups(groups: List[List[int]], classlist: List[int])-> bool:
    """
    Returns True iff groups are valid. A groups are valid iff the following
    properties are met:

    1) 3-4 people in each group
    2) All students in the class should be in a group
    3) no groups can have repeats
    4) all students in groups are in the course 
    
    """
    def cond1(groups: List [List[int]], classList: List [int]) :
        for group in groups:
                if len(group > 4 or len(group) < 3:
                       return False
        return True 
     

    def cond2(groups: List [List[int]], classList: List [int]) :
        for student in classList:
            for group in groups:
                if  student not in group
                return False
            
        return True 
    
    def cond3(groups: List [List[int]], classList: List [int]) :
        
        for student in classList:
            count = 0
            for group in groups:
                if student in group:
                    count += 1
            if count > 1:
                return false
                
                
        return True
    
    def cond4(groups: List [List[int]], classList: List [int]) :
           for group in groups:
            for student in group:
                if student not in classList
                return False
        return
    

'''
def cond23(groups: List [List[int]], classList: List [int]) :

           for student in classList:
             num_groups = 0 
             for group in groups:
                 if student in group:
                 num_group += 1
            if not num_groups == 1
                return False
        return True



'''
