repository = []


def create(data):
    repository.append(data)


def findById(id):
    result = None

    for lesson in repository:
        if (lesson.id == id):
            result = lesson
            break

    return result


def findAllByCourseId(course_id):
    return list(filter(lambda lesson: lesson.course_id == course_id, repository))


def findAll():
    return repository


def updateById(id, data):
    lesson = None

    for i in range(len(repository)):
        if (repository[i].id == id):
            lesson[i] = data
            break


def updateById(id, data):
    global repository

    filtered = list(filter(lambda lesson: lesson.id != id, repository))
    filtered.append(data)

    repository = filtered


def delById(id):
    global repository

    filtered = list(filter(lambda lesson: lesson.id != id, repository))

    repository = filtered
