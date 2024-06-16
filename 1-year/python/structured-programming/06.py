products = [
    { "name": "mouse", "price": 25.00, "qdd_inventory": 10 },
    { "name": "notebook", "price": 2600.00, "qdd_inventory": 7 },
    { "name": "Computador", "price": 2000.00, "qdd_inventory": 5 },
    { "name": "placa de vídeo", "price": 10_000.00, "qdd_inventory": 8 },
    { "name": "memória ram", "price": 450.00, "qdd_inventory": 20 },
    { "name": "teclado", "price": 225.00, "qdd_inventory": 50 },
    { "name": "headset", "price": 1000.00, "qdd_inventory": 30 },
    { "name": "microfone", "price": 830.00, "qdd_inventory": 4 },
    { "name": "smartphone", "price": 3000.00, "qdd_inventory": 100 },  
    { "name": "Monitor", "price": 5000.00, "qdd_inventory": 32 },    
]

def calcTotal(qdd, unit_price):
  return qdd * unit_price

def getDiscount(qdd):
  if (qdd <= 5):
    return 0.02
  elif (qdd > 5 and qdd <=10):
    return 0.03
  else:
    return 0.05

def applyDiscountOnTotal(total, discount):
  return total - (discount * total)

def showProducts():
   for i in range(0 ,len(products)):
    print(f'{i} - {products[i]["name"]}, Preço: {products[i]["price"]}, Quantidade: {products[i]["qdd_inventory"]}') 

def main():
  showProducts()
  print("\n")

  product_option_selected = int(input("Selecione o Produto: \n"))

  if (product_option_selected >= len(products)):
    print('o produto não existe')
    return

  selected_product = products[product_option_selected]

  quantity = int(input('Digite a quantidade: \n'))

  if (quantity > selected_product["qdd_inventory"] or quantity <= 0):
    print("quantidade inválida")
    return

  total = calcTotal(quantity, selected_product["price"])
  discount = getDiscount(quantity)
  total_paid = applyDiscountOnTotal(total, discount)

  print(f'Desconto de {discount * 100}%, total a pagar R${total_paid}')

main()
