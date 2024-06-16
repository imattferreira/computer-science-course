/**
 * To execute, run:
 * - gcc mcdonato.cpp -lstdc++ -std=c++20 && ./a.out
 */
#include <stdio.h>
#include <iostream>
#include <queue>
#include <string>

using namespace std;

struct OrderItem
{
private:
  string name;
  int quantity;
  double price;

public:
  OrderItem(string n, int q, double p) : name(n), quantity(q), price(p) {}

  void print()
  {
    cout << "Item: " << this->name << " Quantidade: " << this->quantity << " Valor: " << this->total_price() << endl;
  }

  double total_price()
  {
    return this->price * this->quantity;
  }

  double unit_price()
  {
    return this->price;
  }
};

struct OrderItemBuilder
{
  static OrderItem build_mc_happy(int quantity)
  {
    return OrderItem("MC Happy", 12.99, quantity);
  }

  static OrderItem build_mc_sad(int quantity)
  {
    return OrderItem("MC Sad", 15.99, quantity);
  }

  static OrderItem build_coquinha(int quantity)
  {
    return OrderItem("Coquinha", 3.98, quantity);
  }

  static OrderItem build_sorvetao(int quantity)
  {
    return OrderItem("Sorvetão", 9.99, quantity);
  }
};

struct Order
{
private:
  int table;
  vector<OrderItem> items;

public:
  Order(int t, vector<OrderItem> i) : table(t), items(i) {}

  static Order create()
  {
    int table;
    vector<OrderItem> items;

    cout << "Mesa:" << endl;
    cin >> table;

    while (true)
    {
      int option, quantity;

      cout << "Selecione um produto:" << endl;
      cout << "1. MC Happy" << endl;
      cout << "2. MC Sad" << endl;
      cout << "3. Coquinha" << endl;
      cout << "4. Sorvetão" << endl;
      cout << "5. Finalizar" << endl;

      cin >> option;

      while (true)
      {
        cout << "Digite a quantidade:" << endl;
        cin >> quantity;

        if (quantity > 0 && quantity < 10)
        {
          break;
        }

        cout << "Quantidade inválida, ele deve ser maior que 0 e menor que 10" << endl;
      }

      switch (option)
      {
      case 1:
        items.push_back(OrderItemBuilder::build_mc_happy(quantity));
        break;
      case 2:
        items.push_back(OrderItemBuilder::build_mc_sad(quantity));
        break;
      case 3:
        items.push_back(OrderItemBuilder::build_coquinha(quantity));
        break;
      case 4:
        items.push_back(OrderItemBuilder::build_sorvetao(quantity));
        break;
      case 5:
        goto loop;
        break;
      default:
        cout << "Opção inválida, tente novamente" << endl;
        continue;
        break;
      }
    }
  loop:;

    return Order(table, items);
  }

  double total_price()
  {
    double result = 0;

    for (int i = 0; i < items.size(); i++)
    {
      result += items[i].total_price();
    }

    return result;
  }

  void print()
  {
    cout << "Mesa: " << this->table << endl;

    for (int i = 0; i < items.size(); i++)
    {
      items[i].print();
    }

    cout << "Total: " << this->total_price() << endl;
  }
};

struct OrdersQueue
{
private:
  queue<Order> orders;

  Order purge()
  {
    Order order = this->orders.front();

    this->orders.pop();

    return order;
  }

  void insert(Order order)
  {
    this->orders.push(order);
  }

public:
  void create()
  {
    this->insert(Order::create());
  }

  void remove()
  {
    Order order = this->purge();

    order.print();
  }

  bool empty()
  {
    return this->orders.empty();
  }
};

int main()
{
  OrdersQueue orders_queue;

  while (true)
  {
    int option;

    cout << "Selecione uma opção:" << endl;
    cout << "1. Sair" << endl;
    cout << "2. Inserir pedido" << endl;
    cout << "3. Atender pedido" << endl;

    cin >> option;

    switch (option)
    {
    case 1:
      cout << "Encerrando..." << endl;
      goto loop;
      break;
    case 2:
      orders_queue.create();
      break;
    case 3:
      if (orders_queue.empty())
      {
        cout << "Fila de pedidos está vazia!" << endl;
        continue;
      }

      orders_queue.remove();
      break;
    default:
      cout << "Opção desconhecida, tente novamente!" << endl;
    }
  }
loop:;
}
