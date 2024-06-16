#include <iostream>
#include <stdio.h>
#include <list>
#include <algorithm>

using namespace std;

int main()
{
  list<int> myList = list{12, 45, 8, 6};

  cout << "FRONT: " << myList.front() << endl;
  cout << "BACK: " << myList.back() << endl;

  // Remove front
  myList.pop_front();
  // Insert as last
  myList.push_back(18);
  // Insert as last
  myList.insert(myList.end(), 32);
  // Insert as first
  myList.insert(myList.begin(), 20);

  cout << "FRONT: " << myList.front() << endl;
  cout << "BACK: " << myList.back() << endl;

  // Remove specific item (all occurrences)
  myList.remove(18);

  // remove duplicated items
  myList.unique();

  // Iterator Pattern
  list<int>::iterator it;

  cout << "\n\nITERATOR:" << endl;
  for (it = myList.begin(); it != myList.end(); it++)
  {
    cout << *it << " ";
  }

  // Auto (`for_each` like)
  cout << "\nAUTO:" << endl;
  for (auto el : myList)
  {
    cout << el << " ";
  }

  cout << endl;

  // Get list size
  myList.size();

  // Verify if list is empty
  myList.empty();

  // Cleanup the list
  myList.clear();

  // Sort the list
  // default is ASC
  // not works correctly for custom sorts and complex lists
  myList.sort();

  // Reverses the list
  myList.reverse();

  it = find(myList.begin(), myList.end(), 18);

  if (*it == 18)
  {
    cout << "Element has been found!" << endl;
  }
  else
  {
    cout << "Element has not found!" << endl;
  }

  return 0;
}
