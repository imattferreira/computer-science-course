#include <queue>

using namespace std;

struct Student
{
  int ra;
};

struct CompareStudents
{
  bool operator()(Student a1, Student a2)
  {
    return a1.ra > a2.ra;
  }
};

int main()
{
  // Heap
  priority_queue<int> priority_q;

  // crescent order
  priority_queue<int, vector<int>, greater<int>> greater_pq;

  // decrescent order
  priority_queue<int, vector<int>, less<int>> less_pq;

  // complex type with comparator
  priority_queue<Student, vector<Student>, CompareStudents> students_pq;

  return 0;
}
