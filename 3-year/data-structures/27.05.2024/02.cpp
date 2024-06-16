#include <iostream>
#include <stdio.h>
#include <list>

using namespace std;

list<double> doublify(list<double> *l)
{
  list<double> r;

  for (auto i : *l)
  {
    r.push_back(i * 2);
  }

  return r;
}

int main()
{
  list<double> l{1, 2, 4, 8, 16, 32, 64, 128};

  cout << "BEFORE: " << endl;
  for (auto i : l)
  {
    cout << i << ", ";
  }
  cout << endl;

  list<double> d = doublify(&l);

  for (auto i : d)
  {
    cout << i << ", ";
  }

  cout << endl;

  return 0;
}
