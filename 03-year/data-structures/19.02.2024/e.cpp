#include <stdio.h>
#include <iostream>
#include <iomanip>

using namespace std;

int main() {
    cout << fixed << setprecision(4);

    float a, b;

    cin >> a >> b;

    double avg = (a * 3.5 + b * 7.5) / (3.5 + 7.5);

    cout << "MEDIA = " << avg << endl;

    return 0;
}
