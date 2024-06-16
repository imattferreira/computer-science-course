#include <iostream>

void call()
{

  while (true)
  {
    long double *x = (long double *)malloc(sizeof(long double));
  }
  call();
}

int main()
{

  call();

  return 0;
}
