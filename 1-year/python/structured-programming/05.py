def toPercentage(qdd, total):
  percentage = (qdd * 100) / total 
  return '{:.2f}%'.format(percentage)

def printTable(data, columns):
  infos = [
    'Eleitores      ' , 
    'Votos em branco', 
    'Votos nulos    ' ,  
    'Votos Válidos  '
]
  format_row = '{:>12}' * columns

  for i,row in zip(infos, data):
    print(format_row.format(i, *row))

def calcValidVotes(blank_votes, null_votes, votes):
  return votes - (blank_votes + null_votes)

class City:
  def __init__(self, city_name, votes, blank_votes, null_votes) -> None:
    self.city_name = city_name
    self.votes = votes
    self.blank_votes = blank_votes
    self.null_votes = null_votes
    pass

  def getValidVotes(self) -> int:
    return calcValidVotes(self.blank_votes, self.null_votes, self.votes)

city_with_most_votes = { 'name': '', 'qdd': 0 }
city_with_most_blank_votes = { 'name': '', 'qdd': 0 }
city_with_most_null_votes = { 'name': '', 'qdd': 0 }
city_with_most_valid_votes = { 'name': '', 'qdd': 0 }
total_votes = 0
total_blank_votes = 0 
total_null_votes = 0
total_valid_votes = 0

for i in range(0,11):
  city_name = input('Digite sua Cidade \n')
  print('\n')
  votes = int(input('Quantidade de Eleitores \n'))
  print('\n')
  blank_votes = int(input('Quantidade de Votos em Brancos \n'))
  print('\n')
  null_votes = int(input('Quantidade de  Votos em nulos \n'))

  city = City(city_name, votes, blank_votes, null_votes)

  if (city_with_most_votes['qdd'] < votes):
    city_with_most_votes['name'] = city_name
    city_with_most_votes['qdd'] = votes   
  
  if (city_with_most_blank_votes['qdd'] < blank_votes):
    city_with_most_blank_votes['name'] = city_name
    city_with_most_blank_votes['qdd'] = blank_votes  

  if (city_with_most_null_votes['qdd'] < null_votes):
    city_with_most_null_votes['name'] = city_name
    city_with_most_null_votes['qdd'] = null_votes   

  if (city_with_most_valid_votes['qdd'] < city.getValidVotes()):
    city_with_most_valid_votes['name'] = city_name
    city_with_most_valid_votes['qdd'] = city.getValidVotes() 

  total_votes = total_votes + votes
  total_blank_votes = total_blank_votes + blank_votes
  total_null_votes = total_null_votes + null_votes
  total_valid_votes = total_valid_votes + city.getValidVotes()

  data = [
    [votes, '100%'],
    [blank_votes, toPercentage(blank_votes, votes)],
    [null_votes, toPercentage(null_votes, votes)],
    [city.getValidVotes(), toPercentage(city.getValidVotes(), votes)],
  ]

  printTable(data, 3)
  print('\n')

total_data = [
  [
    city_with_most_votes['name'], 
    f'{city_with_most_votes["qdd"]}  ', 
    toPercentage(city_with_most_votes['qdd'], total_votes)
  ],
  [
    city_with_most_blank_votes['name'], 
    f'{city_with_most_blank_votes["qdd"]}  ', 
    toPercentage(city_with_most_blank_votes['qdd'], total_blank_votes)
  ],
  [
    city_with_most_null_votes['name'], 
    f'{city_with_most_null_votes["qdd"]}  ', 
    toPercentage(city_with_most_null_votes['qdd'], total_null_votes)
  ],
  [
    city_with_most_valid_votes['name'], 
    f'{city_with_most_valid_votes["qdd"]}  ', 
    toPercentage(city_with_most_valid_votes['qdd'], total_valid_votes)
  ],
]

print('\nResumo:')
printTable(total_data, 4)
