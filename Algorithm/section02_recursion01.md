# __Recursive01__

## 무한 루프에 빠지지 않으려면??

### 01_base case
-> `적어도 하나`의 recursion에 빠지지 않는 경우가 존재해야 한다.

### 02_recursive case
-> recursion을 반복하다 보면 결국 `base case`로 수렴해야 한다.

```java

public class Recursive01{
    public static void main(String [] args){
        int n = 4;
        func(n);
    }
    public static void func(int n){
        if(n <= 0)      // base case
            return;
        else {
            System.out.println("Hello...");
            func(n-1);  // recursive case
        }
    }
}
```

### recursion의 예제
가장 많이 사용되는 예제로 factorial과 fibonacci가 있다. 그중에서 최대공약수(gcd) 함수를 구현해보자.  
최대공약수를 유클리드 방법으로 구하는 방법을 코드로 옮겼다.  

유클리드 방법의 기본 전제는 `m >= n`이고 m이 n의 배수면 `gcd(m,n) = n`이고 , 그렇지 않으면 `gcd(m,n) = gcd(n, m%n)`이다.

```java
public static int gcd(int m, int n){
    if(m < n){
        int temp = m;
        m = n;
        n = temp;
    }

    if(m % n == 0){
        return n;
    } else {
        return gcd(n, m%n);
    }
}
```

__<유클리드 좀더 단순한 버전>__
```java
public static int gcd(int p, int q){
    if(q == 0){
        return p;
    } else {
        return gcd(q, p%q);
    }
}
```
