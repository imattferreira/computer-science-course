import domain.students.controllers as controllers
import domain.students.use_cases as use_cases
from repositories import students_repository, courses_repository, lessons_repository


def create():
    return controllers.create(use_case=use_cases.create(students_repository, courses_repository))


def find():
    return controllers.find(use_case=use_cases.find(students_repository, courses_repository, lessons_repository))


def getAll():
    return controllers.getAll(use_case=use_cases.getAll(students_repository))


def update():
    return controllers.update(use_case=use_cases.edit(students_repository, courses_repository))


def delete():
    return controllers.delete(use_case=use_cases.delete(students_repository))
