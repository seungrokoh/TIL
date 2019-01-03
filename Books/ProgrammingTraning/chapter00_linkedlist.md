# __링크드리스트__

### 배열과 다르게 중간에 노드를 삽입하거나 제거할 때, 원소를 밀어내거나 이동할 필요가 없다.  
### 하지만 리스트에서 임의의 위치 `m번째 원소`를 찾는 과정이 오래 걸린다.

## __01 단일 연결리스트를 이용한 queue__
먼저 연결리스트를 구성하기 위한 `구조체(struct)`를 정의해보자. 간단하게 대기번호를 나타내는 int형 변수 하나만을 가지는 구조체이다.

``` c
struct _node{
    int key;            //대기번호가 저장 될 변수
    struct _node *next; //다음 노드의 주소값을 저장할 변수
};

typedef struct _node node_t;
```

다음은 데이터의 삽입을 나타내는 `insert_node(int n)` 함수이다. 데이터 n을 입력받아 연결리스트에 추가하는 함수이다.

``` c
void insert_node(int n){
    node_t *new_node = (node_t *) malloc(sizeof(node_t));   //새로운 노드 생성

    new_node->key = n;  //입력된 값을 노드에 저장
    new_node->next = NULL;  //입력된 값이 가장 뒤로 가므로 NULL

    if (head == NULL) { //list가 비어 있을 경우
        head = new_node;
        tail = new_node;
    } else {
        tail->next = new_node;  //마지막 원소의 뒤쪽에 삽입하는 과정
        tail = new_node;
    }
}
```
새로운 노드를 할당해 데이터를 입력하고, 마지막 노드의 포인터와 tail이 새로운 노드를 가리키게 한다.  

다음은 연결리스트의 첫 번째 노드의 값을 반환하는 `delete_node()`함수이다. `queue`를 구현해야 하므로 가장 맨 먼저 들어온 값인 `head`가 가리키고 있는 노드를 반환하는 함수이다.
```c
int delete_node(){
    node_t *node;   //반환 될 노드를 저장할 변수
    int result;     //반환 값 저장

    if (head == NULL)
        return -1;

    node = head;    // 첫 번째 원소인 head의 주소값 저장
    head = head->next;  //haed를 한칸 이동
    if (head == NULL)
        tail = NULL;

    result = node->key; // 첫 번째 원소 값 저장
    free(node);         // 노드를 메모리에서 삭제
    return result;
}
```

배열로 queue를 구성하였을 때에는 배열의 크기에 따라 입력할 수 있는 대기열의 개수가 제한된다는 문제가 있었다.`(물론 배열의 크기를 계속해서 늘려도 된다.)` 하지만 연결리스트로 구성한 queue의 경우 대기열의 개수를 무한정으로 늘릴 수 있다는 장점이 있다.`(메모리가 무한정이라는 가정)`
