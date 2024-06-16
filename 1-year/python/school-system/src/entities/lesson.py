from utils.string import genId


class Lesson:
    def __init__(self, id, name, course_id) -> None:
        self._id = id or genId()
        self._name = name
        self._course_id = course_id
        pass

    @property
    def id(self):
        return self._id

    @property
    def name(self):
        return self._name

    @property
    def course_id(self):
        return self._course_id
