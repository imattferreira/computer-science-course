from presentations.student_presentation import student_presentation
from presentations.course_presentation import course_presentation
from presentations.lesson_presentation import lesson_presentation
from entities.student import Student
from utils.validate import isCpfValid, isRaValid, isIdValid


def create(students_repository, courses_repository):
    def execute(name, cpf, course_id):

        if (len(name) < 4):
            return 'invalid name length'

        if (not (isCpfValid(cpf))):
            return 'invalid cpf'

        if (not (isIdValid(course_id))):
            return 'course id invalid'

        cpf_already_exists = students_repository.findByCpf(cpf)

        if (cpf_already_exists):
            return 'cpf already registered'

        course_exists = courses_repository.findById(course_id)

        if (not (course_exists)):
            return 'couse not found'

        student = Student(id=None, name=name, cpf=cpf,
                          ra=None, course_id=course_id)

        students_repository.create(student)

    return execute


def find(students_repository, courses_repository, lessons_repository):
    def execute(ra):
        if (not (isRaValid(ra))):
            return 'student RA invalid'

        student = students_repository.findByRa(ra)

        if (not (student)):
            return 'student not found'

        course = courses_repository.findById(student.course_id)
        lessons = lessons_repository.findAllByCourseId(course.id)

        return {'student': student_presentation(student), 'course': course_presentation(course), 'lessons': map(lesson_presentation, lessons)}

    return execute


def getAll(students_repository):
    def execute():
        students = students_repository.findAll()

        return map(student_presentation, students)

    return execute


def edit(students_repository, courses_repository):
    def execute(ra, name, cpf, course_id):
        if (not (isRaValid(ra))):
            return 'student id invalid'

        student = students_repository.findByRa(ra)

        if (not (student)):
            return 'student not found'

        if (len(name) < 4):
            return 'invalid name length'

        if (not (isIdValid(course_id))):
            return 'course id invalid'

        course_exists = courses_repository.findById(course_id)

        if (not (course_exists)):
            return 'couse not found'

        if (not (isCpfValid(cpf))):
            return 'invalid cpf'

        cpf_already_exists = students_repository.findByCpf(cpf)

        if (cpf_already_exists and cpf_already_exists.id != student.id):
            return 'cpf already registered'

        student = Student(id=student.id, name=name, cpf=cpf,
                          ra=student.ra, course_id=course_id)

        students_repository.updateByRa(ra, student)

    return execute


def delete(students_repository):
    def execute(id):
        if (not (isIdValid(id))):
            return 'student id invalid'

        students_repository.delById(id)

    return execute
