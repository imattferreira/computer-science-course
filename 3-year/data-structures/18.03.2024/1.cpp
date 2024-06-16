#include <iostream>
#include <stack>
#include <string>

using namespace std;

bool is_expr_valid(string expr) {
  stack<char> opened_brackets;

  for (int i = 0; i < expr.length(); i++) {
    char letter = expr[i];

    if (letter == '(') {
      opened_brackets.push('(');
      continue;
    }

    if (letter == ')') {
      if (!opened_brackets.empty()) {
        opened_brackets.pop();
      }

      return false;
    }
  }

  return opened_brackets.empty();
}

int main() {
  // string expr = "(1 + 1) * 3"; // valid
  string expr = "1 + 2) * 3"; // invalid
  string message = is_expr_valid(expr) ? "Is valid" : "Is invalid";

  cout << message << endl;

  return 0;
}
