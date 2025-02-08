def most_birthdays(d: dict[str, list[int]]) -> str:
    most = 0 
    max_month = ''
    for month in d:
        if len(d[month]) >= most:
            most = len(d[month])
            max_month = month

    return max_month
