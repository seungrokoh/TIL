# __알고리즘의 시작__

### 프로그래밍의 기초
책의 시작은 알고리즘을 시작하기 전 프로그래밍의 기본적인 내용으로 구성되어 있다.

두 수의 합을 구하는 함수 `#define max(x,y) ((x) > (y) ? (x) : (y))` 로 시작해서 `swap(int *a, int *b)` , 배열의 회전 등에 대해서 간단하게 설명이 되어 있다.

### Queue(First In First Out)
기본적인 자료구조로 먼저 입력 된 데이터가 먼저 나오는 형식이다.(일반적으로 `대기줄`에 비유)  
현재 출력순서의 데이터를 나타내는 `front(head)`와 `rear(tail)`로 위치를 나타낸다.  
가장 간단한 구조인(선형구조) `queue`를 살펴보면 다음과 같다.  

```c
#define QUEUE_CAPACITY 8

int queue[QUEUE_CAPACITY];
int head = 0;
int tail = -1;
int queue_size = 0;

/*
 * 입력 된 데이터 n을 queue에 입력하는 함수
 */
void enqueue(int n){
    if(queue_size == QUEUE_CAPACITY){
        //큐가 꽉 차있는 상태
        ...
    }
    tail++;
    queue_size++;
    queue[tail] = n;    //현재 데이터를 넣을 수 있는 위치를 나타내는 tail에 데이터 입력
}

/*
 * queue에서 데이터를 출력하는 함수
 */
int dequeue(){
    int result;
    if(queue_size == 0){
        //큐가 비어있는 상태
        ...
    }
    result = queue[head];   //출력 순서인 데이터
    head++;
    queue_size--;
    return result;
}
```

하지만 이러한 `선형큐`는 데이터를 입력하고 출력하는 과정에서 배열의 앞 부분에 빈자리가 생겨도 다시 사용할 수 없다는 단점이 존재한다. 문제를 해결할 수 있는 방법은 ~~원소를 하나 꺼낼 때 마다 배열의 모든 원소를 한 칸씩 앞으로 움직이는 방법이다.~~ 하지만 배열의 크기가 크다면?? 정말 많은 시간이 걸릴 것이다.  
이를 보완한 함수가 `원형큐(circular queue)` 이다.(배열의 첫 시작점과 끝점이 이어져 있다고 생각)  

```c

void enqueue(int n){
    if(queue_size == QUEUE_CAPACITY){
        //큐가 꽉 차있는 상태
        ...
    }
    tail = (tail + 1) % QUEUE_CAPACITY;
    queue_size++;
    queue[tail] = n;    //현재 데이터를 넣을 수 있는 위치를 나타내는 tail에 데이터 입력
}

int dequeue(){
    int result;
    if(queue_size == 0){
        //큐가 비어있는 상태
        ...
    }
    result = queue[head];   //출력 순서인 데이터
    head = (head + 1) % QUEUE_CAPACITY;
    queue_size--;
    return result;
}
```

하지만 원형큐에도 문제가 있는데 만약 배열의 크기를 처음부터 너무 크게 잡으면 메모리를 너무 많이 차지하고 있다. 그렇다고 메모리를 너무 작게 잡으면 `오버플로우`가 생길 수 있다. 이러한 문제점을 해결할 수 있는 방법 중 한 가지는 `LinkedList`로 큐를 만들면 된다.

`LinkedList`로 큐를 만드는 방법은 따로 정리를 하도록 하자.
