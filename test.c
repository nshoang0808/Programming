#include <stdio.h>
int main()
{
  int x = 7, y = 6;
  int z = x /= y %= 6;
  printf("%d\n", z);
  return 0;
}
