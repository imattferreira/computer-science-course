from utils.string import genId


class Course:
    def __init__(self, name, duration, id) -> None:
        self._id = id or genId()
        self._name = name
        self._duration = duration
        pass

    @property
    def id(self):
        return self._id

    @property
    def name(self):
        return self._name

    @property
    def duration(self):
        return self._duration
