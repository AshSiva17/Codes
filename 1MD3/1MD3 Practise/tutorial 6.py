
'''
tutorial 6 ,  week 7 
'''


def upper_triangle_sum (m: list[list[int]]) -> int:
    '''
    Return the sum of the elements in the upper triangle of matrix m.

    >>> upper_triangle_sum([[1,2,3], [4,5,6], [7,8,9]])
    26

    '''

    total = 0

    for i in range(len(m)):
        for j in range (i, len(m[i])):
            total += m[i][j]

        return total 
# not working




def border_sum(m: list[list[int]]) -> int:
    '''
    Return the sum of the elements on the border of matrx m.

    >>> border_sum ([1,2,3] , [4,5,6] , [7,8,9]])
    40

    >>> border_sum ([[5,1], [2,8]])
    16
    '''
    total = 0

       for i in range(len(m)):
        for j in range (len(m[i])):
            # Check if the element is on the border
            if i == 0 or i == len(m) - 1 or j == 0 or j == len(m[i]) -1:
                total += m[i][j]

        return total 
