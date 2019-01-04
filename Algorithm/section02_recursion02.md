# __Recursive02__

## 순환적으로 사고하기
수학함수뿐 아니라 다른 많은 문제들을 Recursion으로 해결할 수 있다.

### 01. 문자열의 길이 계산
문자열의 길이를 계산하는 방법을 일반적으로 `for문`이나 `while문`으로 해결을 하지만 여기서는 다르게 접근을 했다.

문자열의 길이는 `첫 번째 문자를 제외한 나머지 문자열의 길이 + 1이다.`  

```java
public static int lenth(String str){
    if(str.equals(""))
        return 0;   //base case : 문자열이 공백일 경우
    else
        return 1 + lenth(str.substring(1)); //substring(1) : 첫 번째 문자를 제외한 나머지 문자열
}
```

### 02. 문자열의 프린트
문자열을 프린트하는 것을 `recursion`을 이용해 해결했다.

```java
public static void printChars(String str){
    if(str.lenth() == 0){
        return;     // base case : 문자열이 0일 경우
    } else {
        System.out.print(str.charAt(0));    // charAt(0) : index 0 번째 문자
        printChars(str.substring(1));
    }
}
```

### 03. 문자열 역순 프린트
문자열을 역순으로 출력하는 방법을 `recursion`을 이용하여 해결했는데 `02 문자열의 프린트`와 순서만 다르다는게 신기했다. 생각을 조금만 바꿔 생각해보면 당연한 결과였지만 평소에 생각하지 않았던 방법이기에 신선함을 느꼈다.  

문자열을 뒤집어 출력하려면 문자열의 `첫 번째를 제외한 나머지 문자열을 뒤집어 출력`한 뒤 마지막으로 `첫 글자를 출력한다`는 개념으로 접근한다.

```java
public static void printCharsReverse(String str){
    if(str.lenth() == 0)
        return;
    else {
        printCharsReverse(str.substring(1));
        System.out.print(str.charAt(0));
    }
}
```

### 04. 2진수로 변환하여 출력
2진수를 출력하라는 문제를 받았을 때 평소와 같았으면 `2로 나눈 몫`과 `나머지`를 이용해 배열에 넣고 배열을 역순으로 출력하는 방법을 사용했을 것이다. 하지만 `recursion`을 이용해 풀이한 방식은 `간단하고 명료하다.` 천천히 생각을 해보면 당연한 것 같지만 쉽게 접근하기가 어렵다.
```java
public void printInBinary(int n){
    if(n < 2)
        return n;       // base case : 0과 1은 자체가 2진수 이기 때문에
    else {
        printInBinary(n/2);
        System.out.print(n%2);
    }
}
```

### 05. 배열의 합 구하기
배열 data[]에서 `data[0]`에서 `data[n-1]`까지의 합을 구하여 반환한다.

```java
public static int sum(int n, int [] data){
    if(n <= 0)
        return 0;
    else
        return sum(n-1, data) + data[n-1];
}
```

### 06. 데이터파일로 부터 n개의 정수 읽어오기
Scanner in이 참조하는 파일로 부터 n개의 정수를 입력받아 배열 data의 data[0],...,data[n-1]에 저장한다.  
보통 이렇게 하지는 않지만 이렇게 하는 방식도 있다는 것만 참고하면 될 것 같다.

```java
public void readFrom(int n, int [] data, Scanner in){
    if(n == 0)
        return;
    else {
        readFrom(n-1, data, in);
        data[n-1] = in.nextInt();
    }
}
```

## __정리__

1. 모든 순환함수는 반복문(iteration)으로 변경 가능
2. 그 역도 성립함. 즉 `모든 반복문은 recursion으로 표현 가능함`
3. 순환함수는 복잡한 알고리즘을 `단순하고 알기쉽게` 표현하는 것을 가능하게 함
4. 하지만 함수 호출에 따른 오버해드가 있음 (매개변수의 전달, 액티베이션 프레임 생성 등)
