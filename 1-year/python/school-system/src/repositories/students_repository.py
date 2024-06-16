repository = []


def create(data):
    repository.append(data)


def findById(id):
    result = None

    for student in repository:
        if (student.id == id):
            result = student
            break

    return result


def findByRa(ra):
    result = None

    for student in repository:
        if (student.ra == ra):
            result = student
            break

    return result


def findByCpf(cpf):
    result = None

    for student in repository:
        if (student.cpf == cpf):
            result = student
            break

    return result


def findAll():
    return repository


def updateByRa(ra, data):
    global repository

    filtered = list(filter(lambda student: student.ra != ra, repository))
    filtered.append(data)

    repository = filtered


def delById(id):
    global repository

    filtered = list(filter(lambda student: student.id != id, repository))

    repository = filtered
