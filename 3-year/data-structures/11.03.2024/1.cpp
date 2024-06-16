#include <stdio.h>
#include <iostream>
#include <string>

using namespace std;

int main() {
    string str;
    int open_tags = 0, close_tags = 0;

    cin >> str;

    for (int i = 0; i < str.length(); i++) {
        if (str[i] == '(') {
            open_tags++;
        }

        if (str[i] == ')') {
          if (open_tags > close_tags) {
            close_tags++;
            continue;
          }

          // make a diff to cause an error to show correct message
          open_tags++;
          break;
        }
    }

    if (open_tags == close_tags) {
        cout << "correct" << endl;
    } else {
        cout << "incorrect" << endl;
    }

    return 0;
}
