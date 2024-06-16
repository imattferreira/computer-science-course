/**
 * To execute, run:
 * - gcc main.cpp -lstdc++ && ./a.out
 * - g++ main.cpp && ./a.out
*/
#include <iostream>

using namespace std;

int main() {
   cout << "CHAR: " << sizeof(char) << " bytes" << endl;
   cout << "BOOL: " << sizeof(bool) << " bytes" << endl;
   cout << "INT: " << sizeof(int) << " bytes" << endl;
   cout << "FLOAT: " << sizeof(float) << " bytes" << endl;
   cout << "DOUBLE: " << sizeof(double) << " bytes" << endl;
   cout << "LONG LONG: " << sizeof(long long) << " bytes" << endl;
   cout << "LONG DOUBLE: " << sizeof(long double) << " bytes" << endl;

   int x = 2147483647; // INT_MAX
   unsigned int y = 2147483648; // INT_MAX++

   cout << x << " " << y << " " << endl;

   return 0;
}
