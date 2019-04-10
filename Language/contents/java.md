# __:book: Java 내용 정리__
***
# __:seedling: 문자열 비교__

> Java에서 문자열을 비교하는 방법에 대해서 알아보자

java에서 문자열을 비교하는 방법은 총 3가지가 있다.
1. equals 메서드를 이용한 문자열 비교 `(동일성)`
2. compareTo 메서드를 이용한 문자열 비교 `(분류)`
3. == 연산자를 이용한 문자열 비교 `(참조 매칭)`

### 01 문자열 동일성 검사

문자열을 비교하는 방법 중 `문자열의 동일성`을 비교하는 함수는 `equals`함수 이다. `동일성`이란 말 그대로 두 개의 문자열이 동일한지 확인한 후 `boolean`값을 리턴 해줌.

먼저 함수의 원형과 사용방법은 다음과 같다.
```java
// 함수 원형
boolean equals(Object another)

// 사용 방법
String str1 = "java";
String str2 = "java";
String str3 = new String("java");
String str4 = "kotlin";

System.out.println(str1.equals(str2));
System.out.println(str1.equals(str3));
System.out.println(str1.equals(str4));
```
```
output : true
         true
         false
```

### 02 문자열 참조값 비교
문자열의 참조값을 검사하는 방법은 `==` 연산자를 이용하는 방법이다. 예제를 보면 바로 답이 나온다.  
위의 예제에서 출력 부분만 `equals`를 `==`로 수정해 보자.
```java
System.out.println(str1 == str2);
System.out.println(str1 == str3);
System.out.println(str3 == str4);
```
```
output : true
         false
         false
```
여기서 볼 점은 `str1 == str3`의 리턴값이 `false`라는 점이다.  
`==`연산자는 참조를 비교하기 때문에 이런 결과가 나왔는데 `str1`은 `java`라는 `리터럴`을 참조하고 있고 `str3`는 `String 객체`를 참조하고 있기 때문에 `false`값이 리턴 된다.

### 03 문자열 분류 비교

분류 비교라는 말이 이상하게 들릴지 모르겠지만 쉽게 말하면 문자열의 사전적 값을 비교하여 분류한다는 뜻이다. 이 함수는 3가지의 리턴값이 있는데 각 리턴값의 의미는 다음과 같다.

> return 0 : str1 == str2  
> return 1 : str1 > str2  
> return -1 : str1 < str2

함수 원형과 사용방법은 다음과 같다.

```java
// 함수 원형
public int compareTo(String anotherString)

// 사용 방법
String str1 = "Java";
String str2 = "Java";
String str3 = "Kotlin";

System.out.println(str1.compareTo(str2)); // 0
System.out.println(str1.compareTo(str3)); // -1 (str1 < str3)
System.out.println(str3.compareTo(str1)); // 1 (str3 > str1)
```
```
output : 0
         -1
         1
```

# 다른 기본 함수

### 01. 문자열 길이
```java
// 함수 원형
public int length()

// 사용 방법
String str = "abcdef"

int length = str.length(); // output : 6
```

### 02. 특정 위치의 문자
```java
// 함수 원형
public char charAt(int index)

// 사용 방법
String str = "abcdef"

char ch = str.charAt(2); // output : c
```

### 03. 지정한 문자의 위치 검색
```java
// 함수 원형
public char indexOf(int ch)

// 사용 방법
String str = "abcdef"

int index = str.indexOf("d"); // output : 3
```

### 04. 지정된 범위의 부분 문자열
```java
// 함수 원형
public String subString(int beginIndex, int endIndex)

// 사용 방법
String str = "abcdef"

String substr = str.subString(0, 2); // output : abc
```
