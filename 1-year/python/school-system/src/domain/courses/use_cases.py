from entities.course import Course
from presentations.course_presentation import course_presentation
from presentations.lesson_presentation import lesson_presentation
from utils.validate import isIdValid


def create(courses_repository):
    def execute(name, duration):
        if (not (duration.isnumeric())):
            return 'invalid duration value type'

        if (len(name) < 4):
            return 'invalid name length'

        course_already_exists = courses_repository.findByName(name)

        if (course_already_exists):
            return 'course name already exists'

        course = Course(id=None, name=name, duration=int(duration))

        courses_repository.create(course)

    return execute


def find(courses_repository, lessons_repository):
    def execute(id):
        if (not (isIdValid(id))):
            return 'course id invalid'

        course = courses_repository.findById(id)

        if (not (course)):
            return 'course not found'

        lessons = lessons_repository.findAllByCourseId(course.id)

        return {'course': course_presentation(course), 'lessons': map(lesson_presentation, lessons)}

    return execute


def getAll(courses_repository, lessons_repository):
    def execute():
        courses = courses_repository.findAll()

        result = []

        for course in courses:
            lessons = lessons_repository.findAllByCourseId(course.id)
            result.append({'course': course_presentation(course),
                          'lessons': map(lesson_presentation, lessons)})

        return result

    return execute


def edit(courses_repository):
    def execute(id, name, duration):
        if (not (isIdValid(id))):
            return 'course id invalid'

        course = courses_repository.findById(id)

        if (not (course)):
            return 'course not found'

        if (len(name) < 4):
            return 'invalid name length'

        course = Course(id=course.id, name=name, duration=int(duration))

        courses_repository.updateById(id, course)

    return execute


def delete(courses_repository):
    def execute(id):
        if (not (isIdValid(id))):
            return 'course id invalid'

        courses_repository.delById(id)

    return execute
