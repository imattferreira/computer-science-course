#include <stdio.h>
#include <queue>
#include <iostream>

using namespace std;

int main()
{
  priority_queue<int, vector<int>, less<int>> odd;
  priority_queue<int, vector<int>, greater<int>> even;
  int size, tmp;

  cin >> size;

  for (int i = 0; i < size; i++)
  {
    cin >> tmp;

    if (tmp % 2 == 0)
    {
      even.push(tmp);
      continue;
    }

    odd.push(tmp);
  }

  while (!even.empty())
  {
    cout << even.top() << endl;
    even.pop();
  }

  while (!odd.empty())
  {
    cout << odd.top() << endl;

    odd.pop();
  }

  return 0;
}
