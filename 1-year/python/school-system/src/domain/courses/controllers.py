from utils.string import printDivider


def create(use_case):
    print('Qual é o nome do curso?\n')
    name = input()
    print('Qual é a duração do curso?\n')
    duration = input()

    result = use_case(name, duration)

    if (isinstance(result, str)):
        print('Você digitou alguma informação inválida, tente novamente.')
        print(f'mensagem de erro: {result}')
        print('Digite:')
        print('1 - tentar novamente')
        print('0 - voltar para o menu anterior')
        option = int(input())

        if (option):
            return create(use_case)

        return False

    print('Curso criado com sucesso!\n\n')

    return True


def find(use_case):
    print('Qual é o identificador do curso?\n')
    id = input()

    result = use_case(id)

    if (isinstance(result, str)):
        print('Você digitou alguma informação inválida, tente novamente.')
        print(f'mensagem de erro: {result}')
        print('Digite:')
        print('1 - tentar novamente')
        print('0 - voltar para o menu anterior')
        option = int(input())

        if (option):
            return find(use_case)

        return False

    printDivider()

    course = result['course']

    print(f'Curso de: {course.name}')
    print(f'duração: {course.duration}')
    print('possui as aulas:')
    for lesson in result['lessons']:
        print(f'Identificador: {lesson.id}')
        print(f'aula de {lesson.name}')
        printDivider()
    printDivider()

    return True


def getAll(use_case):
    result = use_case()
    for item in result:
        course = item['course']
        lessons = item['lessons']

        print(f'Identificador: {course.id}')
        print(f'Curso de: {course.name}')
        print(f'duração: {course.duration}')
        print('possui as aulas:')
        for lesson in lessons:
            print(f'Identificador: {lesson.id}')
            print(f'aula de {lesson.name}')
            printDivider()
        printDivider()

    return True


def update(use_case):
    print('Qual é o identificador do curso?\n')
    id = input()
    print('Qual é o nome do curso?\n')
    name = input()
    print('Qual é a duração do curso?\n')
    duration = input()

    result = use_case(id, name, duration)

    if (isinstance(result, str)):
        print('Você digitou alguma informação inválida, tente novamente.')
        print(f'mensagem de erro: {result}')
        print('Digite:')
        print('1 - tentar novamente')
        print('0 - voltar para o menu anterior')
        option = int(input())

        if (option):
            return update(use_case)

        return False

    print('Curso atualizado com sucesso!\n\n')

    return True


def delete(use_case):
    print('Qual é o identificador do curso?\n')
    id = input()

    print(f'Você digitou o identificador: {id}\n')
    print(f'Esta informação está correta? Lembrando que uma vez feita a remoção, você não poderá voltar atrás')
    option = int(input())

    if (not (option)):
        print('Parece que você digitou o identificador do curso errado.')
        print('Digite:')
        print('1 - tentar novamente')
        print('0 - voltar para o menu anterior')
        option = int(input())

        if (option):
            return delete(use_case)

        return False

    result = use_case(id)

    if (isinstance(result, str)):
        print('Você digitou alguma informação inválida, tente novamente.')
        print(f'mensagem de erro: {result}')
        print('Digite:')
        print('1 - tentar novamente')
        print('0 - voltar para o menu anterior')
        option = int(input())

        if (option):
            return find(use_case)

        return False

    print('Curso deletado com sucesso!\n\n')

    return True
