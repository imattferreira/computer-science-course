from utils.string import printDivider


def create(use_case):
    print('Qual é o nome da aula?\n')
    name = input()
    print('Essa aula faz parte de qual curso?\n')
    course_id = input()

    result = use_case(name, course_id)

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

    print('Aula criada com sucesso!\n\n')

    return True


def find(use_case):
    print('Qual é o identificador da aula?\n')
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
    print(f'aula de: {result.name}')
    print(f'do curso: {result.course_id}')
    printDivider()

    return True


def getAll(use_case):
    result = use_case()

    for lesson in result:
        print(f'Identificador: {lesson.id}')
        print(f'aula de {lesson.name}')
        print(f'faz parte do curso: {lesson.course_id}')
        printDivider()

    return True


def update(use_case):
    print('Qual é o identificador da aula?\n')
    id = input()
    print('Qual é o nome da aula?\n')
    name = input()
    print('Essa aula faz parte de qual curso?\n')
    course_id = input()

    result = use_case(id, name, course_id)

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

    print('Aula atualizada com sucesso!\n\n')

    return True


def delete(use_case):
    print('Qual é o identificador da aula?\n')
    id = input()

    print(f'Você digitou o identificador: {id}\n')
    print(f'Esta informação está correta? Lembrando que uma vez feita a remoção, você não poderá voltar atrás')
    option = int(input())

    if (not (option)):
        print('Parece que você digitou o identificador da aula errado.')
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

    print('aula deletada com sucesso!\n\n')

    return True
