import uuid


def isRaValid(ra):
    return len(ra) == 7


def isIdValid(string):
    try:
        uuid.UUID(str(string))
        return True
    except:
        return False


def isCpfValid(string):
    def getSumOfMultiplied(multiplied):
        result = 0

        for num in multiplied:
            result += num

        return result

    def getDigit(divider_rest):
        if (divider_rest < 2):
            return 0

        return 11 - divider_rest

    def getFirstDigit(cpf_without_digits):
        multiplied_digits = []

        for multiplier in range(10, 1, -1):
            index = (multiplier - 10) * -1

            multiplied_digits.append(
                int(cpf_without_digits[index]) * multiplier)

        divider_rest = getSumOfMultiplied(multiplied=multiplied_digits) % 11

        return getDigit(divider_rest)

    def getSecondDigit(cpf_without_last_digit):
        multiplied_digits = []

        for multiplier in range(11, 1, -1):
            index = (multiplier - 11) * -1

            multiplied_digits.append(
                int(cpf_without_last_digit[index]) * multiplier)

        divider_rest = getSumOfMultiplied(multiplied=multiplied_digits) % 11

        return getDigit(divider_rest)

    cpf_without_digits = string[0:9]
    first_digit = getFirstDigit(cpf_without_digits)
    second_digit = getSecondDigit(cpf_without_last_digit=string[0:10])

    return string == f'{cpf_without_digits}{first_digit}{second_digit}'
