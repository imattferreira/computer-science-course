/**
 * To execute, run:
 * - gcc 01.cpp -lstdc++ -std=c++20 && ./a.out
*/
#include <iostream>
#include <stdio.h>
#include <string>

using namespace std;

struct Student {
  public:
    string name;
    char age[3]; // also stores "\0" (null char)
    int ra;
};

int main() {
  Student student =  Student { "Matheus", "20", 12 };

  cout << student.name << endl;

  return 0;
}

