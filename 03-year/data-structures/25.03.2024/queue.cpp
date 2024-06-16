// TODO: circular queue
#include <iostream>
#include <stdio.h>
#include <stdexcept>

using namespace std;

struct Queue {
  private:
    int* items;
    int front;
    int tail;
    int capacity;
    int size = 0;

  public:
    Queue(unsigned c = 4): capacity(c) {
      if (c < 4) {
        throw runtime_error("FixedStack: invalid capacity!");
      }

      items = new int[c];
    }

    void clear() {
      for (int i = 0; i < this->size; i++) {
        this->items[i] = NULL;
      }

      this->size = 0;
    }

    int front() {
      return this->front;
    }

    void pop() {
      if (this->size == 0) {
        throw runtime_error("Queue: is empty!");
      }

      this->size--;
      this->front = this->items[1];

      for (int i = this->size; i > 0; i--) {
        int tmp = this->items[i - 1];

        this->items[i - 1] = this->items[i];
      }
    }

    bool empty() {
      return this->size == 0;
    }

    bool full() {
      return this->capacity == this->size;
    }

    void show() {
      for (int i = 0; i < this->size; i++) {
        cout << "Item: " << this->items[i] << " | " << "Position: " << i << endl;
      }
    }

    void push(int item) {
      items[this->size] = item;

      this->tail = item;
      this->size++;
    }
};


int main() {
  Queue queue = Queue(4);

  queue.push(1);
  queue.push(10);
  queue.push(21);
  queue.push(23);
  queue.push(32);
  queue.push(45);
  queue.push(54);

  return 0;
}
