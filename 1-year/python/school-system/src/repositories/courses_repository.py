repository = []


def create(data):
    repository.append(data)


def findById(id):
    result = None

    for course in repository:
        if (course.id == id):
            result = course
            break

    return result


def findByName(name):
    result = None

    for course in repository:
        if (course.name == name):
            result = course
            break

    return result


def findAll():
    return repository


def updateById(id, data):
    global repository

    filtered = list(filter(lambda course: course.id != id, repository))
    filtered.append(data)

    repository = filtered


def delById(id):
    global repository

    filtered = list(filter(lambda course: course.id != id, repository))

    repository = filtered
