


def read_input(hint, chooses, errMessage="Error input."):
    answer = input(hint).strip()
    if chooses is None:
        return answer
    while not answer in chooses:
        print(errMessage)
        answer = input(hint).strip()
    return answer