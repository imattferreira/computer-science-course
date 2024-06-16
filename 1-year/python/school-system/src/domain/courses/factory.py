import domain.courses.controllers as controllers
import domain.courses.use_cases as use_cases
from repositories import courses_repository, lessons_repository


def create():
    return controllers.create(use_case=use_cases.create(courses_repository))


def find():
    return controllers.find(use_case=use_cases.find(courses_repository, lessons_repository))


def getAll():
    return controllers.getAll(use_case=use_cases.getAll(courses_repository, lessons_repository))


def update():
    return controllers.update(use_case=use_cases.edit(courses_repository))


def delete():
    return controllers.delete(use_case=use_cases.delete(courses_repository))
