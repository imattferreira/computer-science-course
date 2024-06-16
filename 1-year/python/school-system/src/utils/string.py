import time
from uuid import uuid4
from string import ascii_letters
from random import randint, choice


def genId():
    return str(uuid4())


def genRa():
    return f'{choice(ascii_letters)}{choice(ascii_letters)}{randint(10_000, 99_999)}'


def sleep(seconds=0):
    if seconds == 0:
        time.sleep(randint(1, 5))
    else:
        time.sleep(seconds)


def printDivider():
    print('=' * 28)
