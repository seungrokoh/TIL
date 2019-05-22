# __:book: Java 내용 정리__

:notebook: 목차
* 객체지향 프로그래밍 1
* 문자열 비교
***
# __:seedling: 객체지향이란?__
인간 중심적 프로그래밍 패러다임이다. 즉, 현실 세계를 프로그래밍으로 옮겨와 프로그래밍 하는것을 의미한다. 현실의 사물을 객체로 바라보고 그 객체로부터 불필요한 부분을 도려내가며 현실에 존재하는 복잡성을 제거하며 프로그래밍 하는 것이다. 이것을 추상화라고 한다.

#### __객체지향의 장점__
1. __코드의 재사용성이 높다__
    새로운 코드를 작성할 때 기존의 코드를 이용하여 쉽게 작성할 수 있다.
2. __코드의 관리가 용이하다__
    코드간의 관계를 이용해서 적은 노력으로 쉽게 코드를 변경할 수 있다.
3. __신뢰성이 높은 프로그래밍을 가능하게 한다__
    제어자와 메서드를 이용해서 데이터를 보호하고 올바른 길을 유지하도록 하며, 코드의 중복을 제거하여 코드의 불일치로 인한 오동작을 방지할 수 있다.

#### __객체지향의 설계 원칙__

1. SRP(Single Responsibility Principle) : 단일 책임 원칙
    클래스는 단 하나의 책임을 가져야 하며 클래스를 변경하는 이유는 단 하나의 이유여야 한다.
2. OCP(Open-Closed Principle) : 개방-폐쇄 원칙
    확장에는 열려 있어야 하고 변경에는 닫혀 있어야 한다.
3. LSP(Liskov Substitution Principle) : 리스코프 치환 원칙
    상위 타입의 객체를 하위 타입의 객체로 치환해도 상위 타입을 사용하는 프로그램은 정상적으로 동작해야 한다.
4. ISP(Interface Segregation Principle) : 인터페이스 분리 원칙
    인터페이스는 그 인터페이스를 사용하는 클라이언트를 기준으로 분리해야 한다.
5. DIP(Dependency Inversion Principle) : 의존 역전 원칙
    고수준 모듈은 저수준 모듈의 구현에 의존해서는 안된다.

#### __클래스란?__
클래스는 타입을 구현하기 위해 프로그래밍 언어에서 제공하는 __구현 메커니즘.__ 일반적으로 객체를 만들기 위한 설계 도면이라고 말한다.  
객체를 불류하는 기준은 타입이며, 타입을 나누는 기준은 객체가 수행하는 행동이다. __객체를 분류하기 위해 타입을 결정한 후 프로그래밍 언어를 이용해 타입을 구현할 수 있는 한 가지 방법이 클래스이다.__

#### __JVM의 메모리 구조__
JVM은 __스택 기반의 가상머신이며__ Java와 OS사이에서 **중개자 역할**을 수행하여 Java가 OS에 구애받지 않고 재사용을 가능하게 해준다. 그리고 가장 중요한 메모리관리, Garbage collection을 수행한다.  
JVM은 시스템으로부터 프로그램을 수행하는데 필요한 메모리를 할당받고 JVM은 이 메모리를 용도에 따라 여러 영역으로 나누어 관리한다. **그 중 3가지 주요 영역은 Method Area, Call Stack, Heap이 있다.**

__1. 메서드 영역(Method Area)__
프로그램 실행 중 어떤 클래스가 사용되면, JVM은 해당 클래스의 클래스파일을 읽어서 분석하여 클래스에 대한 정보(클래스 데이터)를 이곳에 저장한다. 이때 그 클래스의 클래스변수(class variable)도 이 영역에 함께 생성된다.

__2. 호출스택(Call Stack 또는 Execution Stack)__
호출스택은 메서드의 작업에 필요한 메모리 공간을 제공한다. 메서드가 호출되면, 호출스택에 호출된 메서드를 위한 메모리가 할당되며, 이 메모리는 메서드가 작업을 수행하는 동안 지역변수(매개변수 포함)들과 연산의 중간결과 등을 저장하는데 사용된다. 그리고 작업을 마치면 할당되었던 메모리 공간은 반환되어 비워진다.

__3. 힙(Heap)__
인스턴스가 생성되는 공간, 프로그램 실행 중 생성되는 인스턴스는 모두 이곳에 생성된다. 즉, 인스턴스 변수(instance variable)들이 생성되는 공간이다.

#### __상속이란?__
__상속이란 기존의 클래스를 재사용하여 새로운 클래스를 작성하는 것이다.(is a 관계)__ 상속을 통해서 클래스를 작성하면 보다 적은 양의 코드로 새로운 클래스를 작성할 수 있고 코드를 공통적으로 관리할 수 있다. __하지만 어떤 클래스를 상속받는다는 것은 그 클래스에 의존한다는 뜻이기 때문에 의존하는 클래스가 변경되면 영향을 받을 수 있다는 단점이 있다.__ 또한 같은 종류가 아닌(is a 관계가 아닌) 클래스의 구현을 재사용하기 위해 상속한다면 문제가 발생한다.

#### __객체 조립(Composition)__
객체 조립은 클래스 간에 포함(Composite)관계를 맺어주는 것이다. __클래스 간의 포함관계를 맺어 주는 것은 한 클래스의 멤버변수로 다른 클래스 타입의 참조변수를 선언하는 것을 뜻한다.__ 상속에 비해 조립을 통한 재사용의 단점은 상대적으로 런타임 구조가 복잡해지며 구현이 어렵다. __하지만 구현/구조의 복잡함보다 변경의 유연함을 확보하는데서 오는 장점이 크기 때문에, 상속보다는 조립하는 방법을 먼저 고려해야 한다.__


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
