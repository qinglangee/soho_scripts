#include <iostream>
#include <fstream>
#include <sstream>
#include "util.h"

using namespace std;

// 判断字符串是不是整数
bool isNumber(string str){
    for (int i = 0; i < str.size(); i++){
        int tmp = (int)str[i];
        if ((tmp >= 48 && tmp <= 57)) {
            continue;
        } else {
            return false;
        }
    }
    return true;
}

// 判断字符串是不是浮点数
bool isFloat(string str){
    for (int i = 0; i < str.size(); i++){
        int tmp = (int)str[i];
        if ((tmp >= 48 && tmp <= 57) || tmp =='.') {
            continue;
        } else {
            return false;
        }
    }
    return true;
}

// 字符串切分函数
vector<string> split(const string& s, char delimiter){
    vector<std::string> tokens;
    string token;
    istringstream tokenStream(s);
    while (getline(tokenStream, token, delimiter))
    {
        tokens.push_back(token);
    }
    return tokens;
}


// 读取文件内容到列表中
list<string> FileUtil::readFile(string filename){
    list<string> lines;
    std::ifstream fin(filename, std::ios::in);
    char line[1024]={0};
    while(fin.getline(line, sizeof(line))){
        lines.push_back(line);
    }
    fin.close();
    return lines;
}

// 字符串写入到文件中
void FileUtil::writeFile(string filename, string content){

    ofstream   ofresult(filename, ios::ate); 
    ofresult<<content;
    ofresult.close();
}
// 列表内容写入到文件中
void FileUtil::writeList(string filename, list<string> lines){

    ofstream   ofresult(filename, ios::ate); 
    for(list<string>::iterator i = lines.begin(); i != lines.end(); ++i){
        ofresult<<*i<<endl;
    }
    ofresult.close();
}
