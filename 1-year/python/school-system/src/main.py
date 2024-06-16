from utils import os
from utils.string import sleep, printDivider
from domain.courses import factory as courses_factory
from domain.lessons import factory as lessons_factory
from domain.students import factory as students_factory
from constants import CRUD_ACTIONS, ENTITIES


def showLoadingInterface():
    printDivider()
    print('\n\n')
    print('Iniciando Sistema Escolar')
    print('         aguarde...')
    print('\n\n')
    printDivider()

    sleep()
    os.clear()


def loadEntitiesInterface():
    printDivider()
    print('\nSelecione uma entidade: \n\n')

    for i, entity in enumerate(ENTITIES):
        print(f'{i + 1} - {entity}')

    print('\n')
    selected_entity = int(input())

    if (selected_entity < 1 or selected_entity > len(ENTITIES)):
        print('Você digitou uma opção inexistente, tente novamente:')
        return loadEntitiesInterface()

    printDivider()
    os.clear()

    return selected_entity - 1


def getSelectedEntityFactory(selected_entity):
    match(selected_entity):
        case 0:
            return students_factory
        case 1:
            return courses_factory
        case 2:
            return lessons_factory


def callSelectedEntityAction(selected_action, selected_factory):
    match(selected_action):
        case 0:
            return selected_factory.create()
        case 1:
            return selected_factory.getAll()
        case 2:
            return selected_factory.find()
        case 3:
            return selected_factory.update()
        case 4:
            return selected_factory.delete()


def loadCrudActionsInterface():
    printDivider()
    print('\nSelecione uma das ações: \n\n')

    for i, action in enumerate(CRUD_ACTIONS):
        print(f'{i + 1}  - {action}')
    print('10 - voltar')
    print('\n')
    selected_action = int(input())

    if (selected_action == 10):
        os.clear()
        return main()

    if (selected_action < 1 or selected_action > len(CRUD_ACTIONS)):
        print('Você digitou uma opção inexistente, tente novamente:')
        return loadCrudActionsInterface()

    printDivider()
    os.clear()

    return selected_action - 1


def main():
    showLoadingInterface()

    control = None
    next_step = True

    while control != 10:
        if next_step:
            selected_entity = loadEntitiesInterface()

        selected_action = loadCrudActionsInterface()
        selected_factory = getSelectedEntityFactory(selected_entity)

        # when entity action call returns True: success then call `loadEntitiesInterface`
        # when entity action call returns False: fail then call `loadCrudActionsInterface`
        next_step = callSelectedEntityAction(selected_action, selected_factory)


main()
