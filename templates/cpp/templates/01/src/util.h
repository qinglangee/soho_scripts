#pragma once
#include <string>
#include <list>
#include <vector>

using namespace std;
// 判断字符串是不是整数
bool isNumber(string str);
// 判断字符串是不是浮点数
bool isFloat(string str);

// 字符串切分函数
vector<string> split(const string& s, char delimiter);

// 文件操作方法工具
class FileUtil{
    public:
    static list<string> readFile(string filename);
    static void writeFile(string filename, string content);
    static void writeList(string filename, list<string> lines);
};
