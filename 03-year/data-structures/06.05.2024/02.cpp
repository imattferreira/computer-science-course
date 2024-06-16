// Singly Linked-List, Doubly Linked-List, Circular Linked-List, Doubly Linked-List

#include <stdio.h>
#include <iostream>

using namespace std;

struct Node
{
public:
  Node *next = nullptr;
  int value;

  Node(int v) : value(v)
  {
  }
};

struct LinkedList
{
private:
  Node *head = nullptr;

public:
  void insert(int value)
  {
    if (this->head == nullptr)
    {
      this->head = new Node(value);
      return;
    }

    Node *tmp = this->head;

    while (tmp != nullptr)
    {
      if (tmp->next == nullptr)
      {
        tmp->next = new Node(value);
        break;
      }

      tmp = tmp->next;
    };
  }

  void insertAtBeginning(int value)
  {
    Node *node = new Node(value);

    node->next = this->head;
    this->head = node;
  }

  int pop()
  {
    Node *tmp = this->head;

    while (tmp != nullptr)
    {
      if (tmp->next == nullptr)
      {
        return tmp->value;
      }

      tmp = tmp->next;
    }

    return -1;
  }

  void iterate()
  {
    Node *tmp = this->head;

    while (tmp != nullptr)
    {
      cout << tmp->value << endl;
      tmp = tmp->next;
    }
  }
};

int main()
{
  LinkedList *l = new LinkedList();

  l->insert(10);
  l->insert(20);
  l->insertAtBeginning(2);
  l->insert(30);
  l->insert(40);
  l->insertAtBeginning(-1);
  l->pop();

  l->iterate();

  return 0;
}
