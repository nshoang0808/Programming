#include <string>
#include <queue>
#include <map>
#include <set>
#include <stdlib.h>
#include <string>
#include <iostream>
#include <fstream>
#include <map>

//#include "inverter.h"

using namespace std;


string build_inverted_index(string filename){
	string line;
	ifstream finput (filename.c_str());
	if (finput.is_open()) {
		while (getline(finput, line)) {
			cout << line << '\n';
		}
		finput.close();
	}
	return "";
}

int main() {
	build_inverted_index("inputs.txt");
	//printf("Hello World");
}
