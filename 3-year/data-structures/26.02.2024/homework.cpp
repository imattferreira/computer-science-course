/**
 * To execute, run:
 * - gcc homework.cpp -lstdc++ -std=c++20 && ./a.out
*/
#include <iostream>
#include <random>
#include <stdio.h>

using namespace std;

struct Account {
  private:
    unsigned int account_id;
    double balance;
    vector<unsigned int> deposits_via_transfer { };

  public:
    Account(unsigned int id, double b): account_id(id), balance(b) { }

    unsigned int get_account_id() {
      return account_id;
    }

    void deposit(double amount) {
      balance += amount;
    };

    void show() {
      cout << "Conta: " << account_id << endl;
      cout << "Saldo: " << balance << endl;
    };

    void transfer(double amount, Account &account_origin) {
      bool is_origin_withdraw_ok = account_origin.withdraw(amount);

      if (!is_origin_withdraw_ok) {
        return;
      }

      deposits_via_transfer.push_back(account_origin.get_account_id());
      deposit(amount);
    };

    bool withdraw(double amount) {
      if (amount > balance) {
        cout << "Valor a sacar é maior que saldo" << endl;
        return false;
      }

      balance -= amount;

      return true;
    };
};

int generate_random() {
  return rand() % 9;
}

void draw_breakline() {
  cout << endl;
  cout << "------------------------" << endl;
  cout << endl;
}

int main() {
  srand((unsigned) time(NULL));

  vector<Account> accounts{ };

  for (unsigned  i = 0; i < 10; i++) {
    Account account(i + 1, 500.00);

    accounts.push_back(account);
  }

  int i = generate_random();
  int j = generate_random();
  int k = generate_random();
  int l = generate_random();

  cout << "Depositando R$1200,00" << endl;
  accounts[i].deposit(1200);
  accounts[i].show();
  draw_breakline();

  cout << "Sacando R$100,00" << endl;
  accounts[j].withdraw(100);
  accounts[j].show();
  draw_breakline();

  cout << "Transferindo R$100,00" << endl;
  accounts[k].transfer(100, accounts[l]);

  cout << "Origem:" << endl;
  accounts[l].show();
  cout << endl;

  cout << "Destino:" << endl;
  accounts[k].show();

  return 0;
}
