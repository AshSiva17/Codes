from typing import List


def get_bandnames(file_name: str) -> List[str]:
    '''Return a list of all of the bandnames in a band file.
    '''
    file = open(file_name)
    names = []
    file.readline()
    
    for line in file:
        data = line.split(',')
        names.append(data[0])
        
    file.close()
    return names

def average_rating(file_name: str) -> float:
    '''Return the average rating for all bands in band_file.
    '''
    file = open(file_name)
    file.readline()
    total = 0
    num = 0
    
    
    for line in file:
        data = line.split(',')
        total += int(data[1])
        num += 1
        
    file.close()
    return total/num

def average_plays(file_name: str) -> float:
    '''Return the average rating for all bands in band_file.
    '''
    file = open(file_name)
    file.readline()
    total = 0
    num = 0
    
    
    for line in file:
        data = line.split(',')
        total += int(data[2])
        num += 1
        
    file.close()
    return total/num

def create_playlist(file_name:str, playlist_name:str, threshold:int):
    """
    Writes a file named play_listname with bands having a rating greater
    than of equal to threshold
    """
    #Step 1: Read in data
    file = open(file_name, 'r')

    band_info = []
    header = file.readline()
    for line in file:
        band_info.append(line)
    
    file.close()

    #Step 2: write to new file
    file = open(playlist_name, 'w')
    file.write(header)
    
    for band_line in band_info:
        data = band_line.split(',')
        if int(data[1]) >= threshold:
            file.write (band_line)

    
    file.close()
    

def get_guilty_pleasures(band_file: str, new_file:str):
    
    avg_rating = average_rating(band_file)
    avg_plays = average_plays(band_file)


    #Step 1: Read in data
    file = open(file_name, 'r')

    band_info = []
    header = file.readline()
    for line in file:
        band_info.append(line)
    
    file.close()

    #Step 2: write to new file
    file = open(playlist_name, 'w')
    file.write(header)
    
 
    for band_line in band_info:
        data = band_line.split(',')
        if int(data[1]) <=  avg_rating and int(data[2]) >= avg_plays:
            file.write(band_line)

    file.close()
     
   
    

    



















        

