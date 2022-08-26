#include <iostream>
using namespace std;

#define EXPAND 1
#define SHRINK 0

class Stack{
    int total_size;
    int top;
    int *arr;
    int current_size;

public:
    Stack();
    Stack(int p);
    Stack(const Stack& object);
    void Resize(int n, int a);///expand
    void Resize(int n);///shrink
    void Push(int n);
    void Push(int *p, int n);
    void Push(Stack p, int n);
    int Pop();
    int Top();
    int Size();
    float Similarity(Stack p);
    void PrintStack();
    int getValue(int n);
    int getTotalSize();
    int getTop();
    ~Stack();
};

Stack::Stack()
{
    top=-1;
    total_size=10;
    arr=new int[total_size];
    cout << "Default constructor. Initially totalsize is 10" << endl;
}

Stack::Stack(int p)
{
    top=-1;
    total_size=p;
    arr=new int[total_size];
    cout << "It takes argument" << endl;
}

Stack::Stack(const Stack& object) /// copy constructor
{
    int i;
    total_size=object.total_size;
    top=object.top;

    delete[] arr;
    arr=new int[total_size];

    for(i=0;i<=object.top;i++)
    {
        arr[i]=object.arr[i];
    }
}


void Stack::Resize(int n, int a)
{
    if(top+n+1 > total_size)
    {
        total_size=total_size+10;
    }
}

void Stack::Resize(int n)
{
    if(total_size-top<=10)
    {
        total_size=total_size-10;
    }
}

void Stack::Push(int n)
{
    Resize(1,EXPAND);
    top++;
    arr[top]=n;
}

void Stack::Push(int *p, int n)
{
    Resize(n,EXPAND);
    for(int i=0; i<n ; i++)
    {
        top++;
        arr[top]=*(p+i);
    }
}

void Stack::Push(Stack p, int n)  /// stack push
{
    Resize(n,EXPAND);
    for(int i=p.top; i>=0 ; i--)
    {
        top++;
        arr[top]=p.getValue(i);
    }
}

int Stack::Pop()
{
    Resize(SHRINK);
    return arr[top--];
}

int Stack::Size()
{
    current_size=top+1;
    return current_size;
}

int Stack::Top()
{
    return arr[top];
}

float Stack::Similarity(Stack p)
{
    float c=0;
    int i=p.top;
    int j=top;
    float avg=((float)i+(float)j+2)/2;
    while(i>=0 || j>=0)
    {
        if(p.arr[i] == arr[j])
        {
            c++;
        }
        i--;
        j--;
    }

    return (c/avg);
}

void Stack::PrintStack()
{
    int i=0;
    while(i<=top){
        cout << arr[i] << " "  ;
        i++;

    }
}

int Stack::getTotalSize()
{
    return total_size;
}

int Stack::getValue(int n)
{
    return arr[n];
}

int Stack::getTop()
{
    return top;
}

Stack::~Stack()
{
    delete[] arr;
    cout << "Destructor is called" << endl;
}

int main()
{
    int todo,tempSize,temp;
    Stack mainStack;
    Stack *tempStack;
    cout << "1:	Push an element" << endl;
    cout << "2:	Push an array" << endl;
    cout << "3:	Push a stack" << endl;
    cout << "4:	Pop" << endl;
    cout << "5:	Top" << endl;
    cout << "6:	Size" << endl;
    cout << "7:	Similarity" << endl;
    cout << "8:	Exit" << endl;

    do
    {
        cin >> todo;
        switch(todo){
    case 1:
        cout << "Enter a number :";
        int a;
        cin >> a;
        mainStack.Push(a);
        break;

    case 2:
        cout << "Enter the array size :" << endl;
        int n;
        cin >> n;
        int num[100];

        cout << "Enter the values" << endl;
        for(int i=0;i<n;i++)
            cin >> num[i];
        mainStack.Push(&num[0],n);
        break;

    case 3:
        cout << "Enter stack size :" << endl;
        cin >> tempSize;
        tempStack=new Stack(tempSize);
        cout << "Enter the values for mew stack :" << endl; ///
        for(int i=0;i<tempSize;i++)
        {
            cin >> temp;
            tempStack->Push(temp);
        }
        tempStack->PrintStack();
        mainStack.Push(*tempStack,tempSize);
        break;

    case 4:
        cout << mainStack.Pop() << " value popped!" << endl;
        break;

    case 5:

        cout << "The top value is " << mainStack.Top() << endl;
        break;

    case 6:
        cout << "The size of stack is " << mainStack.Size() << endl;
        break;

    case 7:
        cout << "Enter stack size :" << endl;
        cin >> tempSize;
        tempStack=new Stack(tempSize);
        cout << "Enter the values for mew stack :" << endl; ///
        for(int i=0;i<tempSize;i++)
        {
            cin >> temp;
            tempStack->Push(temp);
        }
        tempStack->PrintStack();
        cout << "Similarity score is " << mainStack.Similarity(*tempStack) << endl;
        break;

    case 8:
        cout << "The values are :" << endl;
        for(int i=mainStack.getTop();i>=0;i--)
            cout << mainStack.Pop() << endl;
        }
    }while(todo!=8);




}
