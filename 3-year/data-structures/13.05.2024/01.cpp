#include <stdio.h>
#include <iostream>

using namespace std;

struct Node
{
public:
  int value;
  Node *next = nullptr;
  Node *previous = nullptr;

  Node(int v) : value(v) {}
};

struct DoublyLinkedList
{
private:
  int size = 0;
  Node *head = nullptr;

public:
  void insert(int value)
  {
    Node *tmp = this->head;
    Node *node = new Node(value);

    this->size++;

    do
    {
      if (this->head == nullptr)
      {
        this->head = node;
        break;
      }

      if (tmp->next == nullptr)
      {
        node->previous = tmp;
        tmp->next = node;
        break;
      }

      tmp = tmp->next;
    } while (tmp != nullptr);
  }

  void insertAtBeginning(int value)
  {
    Node *node = new Node(value);

    this->size++;

    if (this->head == nullptr)
    {
      this->head = node;
      return;
    }

    node->next = this->head;
    this->head->previous = node;
    this->head = node;
  }

  void pop()
  {
    Node *tmp = this->head;

    this->size--;

    while (tmp != nullptr)
    {
      if (tmp->next == nullptr)
      {
        tmp->previous->next = nullptr;
        break;
      }

      tmp = tmp->next;
    }
  }

  void show()
  {
    Node *tmp = this->head;

    while (tmp != nullptr)
    {
      cout << tmp->value << endl;
      tmp = tmp->next;
    }
  }

  int size()
  {
    return this->size;
  }
};

int main()
{
  DoublyLinkedList *list = new DoublyLinkedList();

  list->insert(10);
  list->insertAtBeginning(5);
  list->insert(20);
  list->insert(30);
  list->insertAtBeginning(2);
  list->insert(40);
  list->pop();
  list->show();

  return 0;
}
