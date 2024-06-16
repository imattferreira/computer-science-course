#include <iostream>
#include <stdio.h>

using namespace std;

int main()
{
  // memory pointer
  int *p;

  // int *pp = nullptr;

  // int var = 20;
  // int *pp;
  // pp = &var;

  // *p = -5; // bus error
  // a->b => (*a).b

  cout << p << endl;         // memory address -> 0x104023cf0 -> hexadecimal
  cout << &p << endl;        // memory reference -> 0x16bddeef0 -> hexadecimal
  cout << *p << endl;        // value stored in memory
  cout << sizeof(p) << endl; // 8 bytes (macOS)

  // memory allocation
  int *a = (int *)malloc(8); // C
  int *b = new int;          // C++

  *a = -5;
  *b = -5;

  cout << *a << endl;
  cout << *b << endl;

  // releasing memory
  free(a);
  delete b;

  return 0;
}
