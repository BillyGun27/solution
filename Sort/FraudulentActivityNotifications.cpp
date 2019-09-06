#include <bits/stdc++.h>

using namespace std;

vector<string> split_string(string);

// Function for calculating median 
double findMedian(vector<int> countfreq, int n ) 
{ 
    //count sort accumulate 
    for(int j=1;j<countfreq.size();j++){
        countfreq[j]+=countfreq[j-1];
    }
  
    int first =  n/2 + 1;
    int second = n/2;
    int a=0,b=0;

    //find count sort
    for(int j=1;j<countfreq.size();j++){

        if(first<=countfreq[j] && a==0 ){
                a=j;
        }

        if (n % 2 == 0) {
            if(second<=countfreq[j] && b==0 ){
                b=j;
            }

            if( a>0 && b>0){
                break;
            }

        }else{
            if( a>0){
                break;
            }
        }
        
    }

    
    // check for even case 
    if (n % 2 != 0) 
        return (double) a ;
  
    return (double)( a  + b ) / 2.0; 
    
} 

// Complete the activityNotifications function below.
int activityNotifications(vector<int> expenditure, int d) {
    // Declaring new vector for count sort
    vector<int> temp_array(201,0);

    for(int i=0;i<d;i++){
        temp_array[expenditure[i]]++;
    }
   
    int notif=0;
    for(int i=d;i<expenditure.size();i++){
       
        double med = findMedian(temp_array,d) *2; 
        if(  med <= expenditure[i]  ){
            notif++;
            
        }
        
        temp_array[expenditure[i]]++;
        temp_array[expenditure[i-d]]--;
    
    }
    return notif;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string nd_temp;
    getline(cin, nd_temp);

    vector<string> nd = split_string(nd_temp);

    int n = stoi(nd[0]);

    int d = stoi(nd[1]);

    string expenditure_temp_temp;
    getline(cin, expenditure_temp_temp);

    vector<string> expenditure_temp = split_string(expenditure_temp_temp);

    vector<int> expenditure(n);

    for (int i = 0; i < n; i++) {
        int expenditure_item = stoi(expenditure_temp[i]);

        expenditure[i] = expenditure_item;
    }

    int result = activityNotifications(expenditure, d);

    fout << result << "\n";

    fout.close();

    return 0;
}

vector<string> split_string(string input_string) {
    string::iterator new_end = unique(input_string.begin(), input_string.end(), [] (const char &x, const char &y) {
        return x == y and x == ' ';
    });

    input_string.erase(new_end, input_string.end());

    while (input_string[input_string.length() - 1] == ' ') {
        input_string.pop_back();
    }

    vector<string> splits;
    char delimiter = ' ';

    size_t i = 0;
    size_t pos = input_string.find(delimiter);

    while (pos != string::npos) {
        splits.push_back(input_string.substr(i, pos - i));

        i = pos + 1;
        pos = input_string.find(delimiter, i);
    }

    splits.push_back(input_string.substr(i, min(pos, input_string.length()) - i + 1));

    return splits;
}

