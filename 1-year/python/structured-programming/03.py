first_num = float(input('Digite um número qualquer\n'))
second_num = float(input('Digite outro número qualquer\n'))
third_num = float(input('Digite mais um número qualquer\n'))

if (first_num == second_num) and (second_num == third_num):
  print('Você digitou três números iguais')
else:
  nums_arr = [first_num, second_num, third_num]
  nums_arr.sort()
  less_num = nums_arr[0]

  print(f'O número menor digitado é {less_num}')
