from utils.string import printDivider


def create(use_case):
    print('Qual é o nome do aluno?\n')
    name = input()
    print('Qual é o CPF do aluno?\n')
    cpf = input()
    print('Esse aluno faz parte de qual curso?\n')
    course_id = input()

    result = use_case(name, cpf, course_id)

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

    print('Aluno criado com sucesso!\n\n')

    return True


def find(use_case):
    print('Qual é o RA do aluno?\n')
    ra = input()

    result = use_case(ra)

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

    student = result['student']
    course = result['course']
    lessons = result['lessons']

    printDivider()
    print(f'aluno: {student.name}')
    print(f'possui o CPF: {student.cpf}')
    print(f'possui o RA: {student.ra}')
    print(f'faz parte do curso: {course.name}')
    print(f'duração: {course.duration}')
    print('possui as aulas:')

    for lesson in lessons:
        print(f'Identificador: {lesson.id}')
        print(f'aula de {lesson.name}')
        printDivider()
    printDivider()

    return True


def getAll(use_case):
    result = use_case()

    for student in result:
        printDivider()
        print(f'Identificador: {student.id}')
        print(f'aluno: {student.name}')
        print(f'possui o CPF: {student.cpf}')
        print(f'possui o RA: {student.ra}')
        print(f'faz parte do curso: {student.course_id}')

    return True


def update(use_case):
    print('Qual é o RA do aluno?\n')
    ra = input()
    print('Qual é o nome do aluno?\n')
    name = input()
    print('Qual é o CPF do aluno?\n')
    cpf = input()
    print('Esse aluno faz parte de qual curso?\n')
    course_id = input()

    result = use_case(ra, name, cpf, course_id)

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

    print('Aluno atualizado com sucesso!\n\n')

    return True


def delete(use_case):
    print('Qual é o identificador do estudante?\n')
    id = input()

    print(f'Você digitou o identificador: {id}\n')
    print(f'Esta informação está correta? Lembrando que uma vez feita a remoção, você não poderá voltar atrás')
    option = int(input())

    if (not (option)):
        print('Parece que você digitou o identificador do estudante errado.')
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
