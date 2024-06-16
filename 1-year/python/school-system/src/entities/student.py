from utils.string import genId, genRa


class Student:
    def __init__(self, id, name, cpf, ra, course_id) -> None:
        self._id = id or genId()
        self._name = name
        self._cpf = cpf
        self._ra = ra or genRa()
        self._course_id = course_id
        pass

    @property
    def id(self):
        return self._id

    @property
    def name(self):
        return self._name

    @property
    def cpf(self):
        return self._cpf

    @property
    def ra(self):
        return self._ra

    @property
    def course_id(self):
        return self._course_id
