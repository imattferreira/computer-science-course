/**
 * To execute, run:
 * - gcc 01.cpp -lstdc++ -std=c++20 && ./a.out
*/
#include <iostream>
#include <stdio.h>
#include <stdexcept>
// #include <stack> // STL (Standard Template Library)

using namespace std;

/**
 * Stack
 * - LIFO - Last In First Out
*/
struct FixedStack {
  private:
    int* items;
    unsigned int capacity;

  public:
    unsigned int size = 0;

    FixedStack(unsigned int c = 4): capacity(c) {
      if (c < 1) {
        throw runtime_error("FixedStack: invalid capacity!");
      }

      items = new int[c];
    }

    void clear(unsigned int index) {
      if (index > size) {
        throw runtime_error("FixedStack: index overflows size");
      }

      size--;

      int tmp = index;

      while (tmp != size) {
        items[tmp] = items[tmp + 1];

        tmp++;
      }
    }

    void empty() {
      while (size != 0) {
        items[size - 1] = NULL;

        size--;
      }
    }

    bool full() {
      return size == capacity;
    }

    void push(int i) {
      items[size] = i;

      size++;
    }

    int pop() {
      if (size == 0) {
        throw runtime_error("FixedStack: stack is empty!");
      }

      size--;

      int last = items[size];

      items[size] = NULL;

      return last;
    }

    void show() {
      int tmp = size - 1;

      while (tmp != -1) {
        cout << "Position: " << tmp << " | Value: " << items[tmp] << endl;

        tmp--;
      }
    }

    int top() {
      if (size == 0) {
        throw runtime_error("FixedStack is empty!");
      }

      return items[size - 1];
    }
};

int main() {
  FixedStack stack = FixedStack(5);

  stack.push(10);
  stack.push(12);
  stack.push(15);
  stack.push(18);

  cout << "Size: " << stack.size << endl;
  cout << "Last Item: " << stack.top() << endl;

  stack.pop();

  cout << "Last Item After Pop: " << stack.top() << endl;
  cout << "Stored Items: " << endl;

  stack.show();

  // TODO test `clear()`

  stack.empty();

  cout << "Size After Empty: " << stack.size << endl;

  return 0;
}
