#include <iostream>
#include "console_util.h"

using namespace std;

int readInt(){
    int num;
    cin>>num;
    while(cin.fail()){
        cin.clear();
        cin.ignore();
        while(cin.get()!='\n')   // ignore all error input
            continue;
        cout<<"Error input, try again please: ";
        cin >> num;
    }
    return num;
}