import datetime

birth_date = input('Digite a data de seu nascimento (no formato dia/mês/ano)\n')

now = datetime.datetime.now()
converted_birth_date = datetime.datetime.strptime(birth_date, "%d/%m/%Y")

delta_date = now - converted_birth_date

print(f'Você tem {delta_date} dias de vida')
