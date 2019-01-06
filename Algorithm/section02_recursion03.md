# __Recursion03__

## 순환 알고리즘의 설계
recursion으로 작성을 할 때 어떠한 요령으로 작성을 하는지??

1. `적어도 하나`의 base case, 즉 순환되지 않고 종료되는 case가 있어야함
2. 모든 case는 결국 base case로 수렴해야 함

## `* __암시적(implicit) 매개변수를 명시적(explicit) 매개변수로 바꾸어라.__`

### 01. 순차탐색(Sequential Search)
아래함수는 배열안에 `target`인 원소가 있는지 확인하는 함수이다~~(일반적인)~~. 만약 배열이 정렬이 되어 있다면 `이진탐색`을 이용하여 검색하겠지만 그렇지 않다면 순차적으로 배열을 검색하면 원하는 원소가 있는지 확인해야 한다.  

아래 함수의 미션은 `data[0]에서 data[n-1] 사이`에서 `target`을 검색하는 것이다.  
하지만 검색 구간의 `시작 인덱스 0은 보통 생략`한다. `즉 암시적 매개변수이다.`  

__여기서 과연 `암시적 매개변수`는 어떤것을 의미하는 것일까?__  

배열안에 target을 찾기 위해서 `구간 [0, n-1]`에서 순차적으로 탐색을 한다. 여기서 `n-1`은 `n`이라는 매개변수에 의해서 `명시적`으로 표현이 되었다. 하지만 `0`의 경우에는 `암시적`으로 표현이 된 것이다. 당연히 배열의 0번째부터 시작이 되겠지라고 `암묵적으로 동의`를 한 것이지 데이터가 저장되어 있는 공간의 시작지점이 `명시적으로`표현이 되어있지 않다는 뜻이다.

```java
int search(int [] data, int n, int target){
    for(int i = 0; i < n; i++)
        if (data[i] == target)
            return i;
    return -1;
}

```

### 02. 매개변수의 명시화 : 순차탐색
아래 함수의 미션은 data[begin]에서 data[end] 사이에서 target을 검색한다. 즉, `검색구간의 시작점을 명시적(explicit)으로 지정한다.`

```java
int search(int [] data, int begin, int end, int target){
    if(begin > end)
        return -1;      // base case : 검색할 데이터가 0개 일 경우
    else if (target == data[begin])
        return begin;
    else
        return search(data, begin+1, end, target);  // 시작점을 한 칸씩 뒤로 옮김
}
```
이 함수를 search(data, 0, n-1, target)으로 호출한다면 `01. 순차탐색`의 함수와 동일한 일을 한다.  
이렇게 `명시적으로` 표현을 하는 것은 `recursion`을 작성할 때 염두에 둬야 하는 `가장 기본적인 원칙중 하나`이다. 즉, 외부에서 함수를 호출할 때만 신경쓸 것이 아니라 내부에서 자기자신을 호출할 때 매개변수까지 신경을 써야한다는 것이다.

### 03. 순차탐색 : 다른버전

`binary search`와 비슷하게는 생겼지만 다른 함수이다. 먼저 배열을 반으로 나누고 배열의 가운데 값이 `target`과 같다면 target을 return하고 그렇지 않다면 `배열의 왼쪽 부분`을 탐색한다. `왼쪽 부분`에서 `target`을 찾지 못했다면 `배열의 오른쪽 부분`을 탐색하는 함수이다.

```java
int search(int [] data, int begin, int end, int target){
    if(begin > end)
        return -1;  // base case
    else {
        int middle = (begin + end)/2;   //배열의 중간지점
        if (data[middle] == target)
            return middle;
        int index = search(data, begin, middle-1, target);  //begin부터 middle사이를 검색
        if(index != -1)
            return index;   // 배열의 왼쪽에서 답을 찾았다면 종료
        else
            return search(data, middle+1, end, target); // 아니라면 배열의 오른쪽 탐색
    }
}
```

### 04. 매개변수의 명시화 : 최대값 찾기
아래 함수의 미션은 data[begin]에서 data[end] 사이에서 최대값을 찾아 반환한다. `begin<=end라고 가정`한다.  

기본 `recursion`의 아이디어는 배열의 `첫 번째 값을 제외한 나머지 원소들 중의 최대값`과 `첫 번째 원소`와 비교하여 최대값을 찾는 것이다.

```java
int findMax(int [] data, int begin, int end){
    if (begin == end)
        return data[begin];     // base case : 배열의 원소가 1개 일 때
    else
        return Math.max(data[begin], findMax(data, begin+1, end));
}
```

### 05. 최대값 찾기 : 다른버전
```java
int findMax(int [] data, int begin, int end){
    if(begin == end)
        return data[begin];
    else {
        int middle = (begin + end) / 2;
        int max1 = findMax(data, begin, middle);
        int max2 = findMax(data, middle+1, end);
        return Math.max(max1, max2);
    }
}
```

### 06. Binary Search(이진검색)

```java
public static int binarySearch(String[] items, String target, int begin, int end){
    if(begin > end)
        return -1;      // base case : 데이터의 개수가 0일 때
    else {
        int middle = (begin + end) / 2;
        int compResult = target.compareTo(items[middle]);
        if (compResult == 0)
            return middle;
        else if(compResult < 0)
            return binarySearch(items, target, begin, middle-1);
        else
            return binarySearch(items, target, middle+1, end);
    }
}
```
