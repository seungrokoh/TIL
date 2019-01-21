# __Recursion07__

### Recursion의 응용 : 멱집합(PowerSet)
멱집합은 임의의 집합 `data`의 모든 부분집합이다. 이를 `recursion`을 이용해서 해결해 본다.

### {a, b, c, d, e, f}의 모든 부분집합을 나열하라.
모든 부분집합을 나열하려면 다음과 같이 생각할 수 있다.  
1. a를 제외한 {b, c, d, e, f}의 모든 부분집합들을 나열하고 (a를 포함하지 않음)
2. {b, c, d, e, f}의 모든 부분집합에 {a}를 추가한 집합들을 나열한다. (a를 포함)  

원래의 집합에서 크기(원소)를 하나 뺀 집합을 생각해본다면 이 속에서 `recursion`을 찾을 수 있다.  
계속해서 나아간다면 다음과 같이 진행이된다.  

{b, c, d, e, f}의 모든 부분집합에 {a}를 추가한 집합들을 나열하려면
1. {c, d, e, f}의 모든 부분집합들에 {a}를 추가한 집합들을 나열하고 (b를 포함하지 않음)
2. {c, d, e, f}의 모든 부분집합에 {a,b}를 추가한 집합들을 나열한다. (b를 포함)

{c, d, e, f}의 모든 부분집합에 {a}를 추가한 집합들을 나열하려면
1. {d, e, f}의 모든 부분집합들에 {a}를 추가한 집합들을 나열하고 (c를 포함하지 않음)
2. {d, e, f}의 모든 부분집합에 {a,c}를 추가한 집합들을 나열한다. (c를 포함)

이러한 문제를 `pseudo code`로 나타내면 다음과 같다.

```c
// Mission : S의 멱집합을 출력하라.
powerset(S){
    if S is an empty set
        print nothing;
    else
        let t be the fist element of S;
        find all subsets of S-{t} by calling powerset(S-{t});
        print the subsets;
        print the subsets with adding t;
}
```
하지만 이렇게 하려면 powerset 함수는 여러 개의 집합들을 return해야 한다. 이러한 문제점은 다음과 같이 해결할 수 있다.

```c
// Mission : S의 멱집합을 구한 수 각각에 집합 P를 합집합하여 출력하라.
powerset(P, S){
    if S is an empty set
        print P;
    else
        let t be the fist element of S;
        powerset(P, S-{t});
        powerset(PU{t}, S-{t});
}
```
 `recursion` 함수가 두 개의 집합을 매개변수로 받고, 두 번째 집합의 모든 부분집합들에 첫 번째 집합을 `합집합`하여 출력한다.  
 > {c, d, e, f}의 모든 부분집합에 {a, b}를 추가한 집합들을 나열한다.  

 다음과 같은 상황에서 {c, d, e, f}는 `k번째`부터 `마지막 원소`까지 연속된 원소들인 집합 S이고, {a, b}는 `처음부터 k-1번째 원소들 중 일부`인 집합 P이다.  
 집합 P에 대해서는 `boolean include[]`를 이용해서 표현한다. `include[index]`가 `TRUE`라면 `data[index]`는 `집합 P에서`포함이 된다는 뜻이고 `FALSE`라면 포함되지 않는다는 뜻이다. 이를 코드로 표현한다면 다음과 같다.  

 ```java
 private static char data[] = {'a', 'd', 'c', 'd', 'e', 'f'}
 private static int n = data.length;
 private static boolean[] include = new boolean[n];

 public static void powerSet(int k){
     if(k == n){
         for (int i = 0; i < n; i++){
             if(include[i]) System.out.print(data[i] + " ");
         }
         System.out.println()
         return;
     }
     include[k] = false;
     powerSet(k+1);
     include[k] = true;
     powerSet(k+1);
 }
```
> 1. include[k] : 트리상에서 현재 나의 위치를 표현한다.  
> 2. if (k == n) : 만약 내 위치가 리프노드라면
> 3. include[k] = false : 포함하지 않는 쪽으로 내려간다.

### 상태공간트리 (state space tree)
* 해를 찾기 위해 탐색할 필요가 있는 모든 후보들을 포함하는 트리
* 트리의 모든 노드들을 방문하면 해를 찾을 수 있다.
* 루트에서 출발하여 체계적으로 모든 노드를 방문하는 절차를 기
