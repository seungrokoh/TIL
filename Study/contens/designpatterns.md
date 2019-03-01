# __Design Patterns__

**:book: contents**
* 싱글턴(Singletone) 패턴

---
### __싱글턴(Singletone) 패턴__

* 특정 클래스에 대해서 객체 인스턴스가 하나만 만들어질 수 있도록 해 주는 패턴  
* 어디서든지 그 인스턴스에 접근할 수 있도록 하기 위한 패턴

#### 고전적인 싱글턴 구현법

```java
public class Singleton {
    private static Singleton uniqueInstance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
```
위의 싱글턴 패턴은 두 개 이상의 객체가 생성 될 수 있는 위험이 존재한다!  
`멀티 쓰레드 환경`에서 `if (uniqueInstance == null)`의 코드를 동시에 실행한다면?? 두 개의 객체가 생성될 수 있는 위험이 있다. 그렇다면 어떻게 해결할 수 있을까??

#### getInstance()를 동기화 시키자

문제를 해결하는 방법은 객체를 반환하는 `getInstance()`를 동기화 하는 방법이다.

```java
public static synchronized Singleton getInstance() {
    if (uniqueInstance == null){
        uniqueInstance = new Singleton();
    }
    return uniqueInstance;
}
```
동기화를 시킴으로써 멀티 쓰레드 환경에서 안전한 코드를 작성할 수 있다. 하지만 과연 최적일까??  
조금 더 생각해보면 사실 동기화가 꼭 필요한 시점은 getInstance() 시작되는 때뿐이다. 즉 uniqueInstance 변수에 Singleton 인스턴스를 대입하고 나면 굳이 이 메소드를 동기화된 상태로 유지시킬 이유가 없다는 뜻이다.  
첫 번째 과정을 제외하면 동기화는 불필요한 오버헤드만 증가시킬 뿐이다.

#### 더 효율적인 방법은?

1. getInstance()의 속도가 그리 중요하지 않다면 그냥 놔두자
    * getInstance() 메서드가 애플리케이션에 큰 부담을 주지 않는다면 그냥 놔둬도 된다. 다만 머스드를 동기화 하면 성능이 100배 정도 저하된다는 것만 기억해두자!! 만약 getInstance()가 애플리케이션에 병목적으로 작용한다면 `다른 방법`을 생각해봐야 한다.

2. 인스턴스를 필요할 때 생성하지 말고, 처음부터 만들어 버리자
    * 애플리케이션에서 반드시 Singleton의 인스턴스를 생성하고, 그 인스턴스를 항상 사용한다면, 또는 인스턴스를 실행중에 수시로 만들고 관리하기가 성가시다면 처음부터 Singleton인스턴스를 만들어버리는 방법도 좋다!

    ```java
    private static Singleton uniqueInstance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance() {
        return uniqueInstance;
    }
    ```

    이런 접근법은 클래스가 로딩될 때 JVM에서 Singletone의 유일한 인스턴스를 생성해준다.

#### DCL(Double-Checking Locking)

`DCL(Double-Checking Locking)`을 사용해 getInstance()에서 동기화되는 부분을 줄이는 방법이다. 일단 인스턴스가 생성되어 있는지 확인한 다음, 생성되어 있지 않았을 때만 동기화를 할 수 있다. 이렇게 하면 `처음에만 동기화`를 하고 나중에는 동기화를 하지 않아도 된다.

```java
public class Singleton {
    private volatile static Singleton uniqueInstance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
```
`싱글턴 패턴`을 위와 같이 구현했을 때, 오버헤드를 줄일 수 있다.
getInstance()을 호출했을 때 인스턴스가 있는지를 확인하고 없으면 동기화된 블럭으로 들어가게 된다. 이렇게 하면 처음에만 동기화 된다.  
`volatile` 키워드를 사용하면 멀티스레딩을 쓰더라도 uniqueInstance 변수가 Singleton 인스턴스로 초기화 되는 과정이 올바르게 진행되도록 할 수 있다.

> DCL은 자바 1.4 이전 버전에서는 사용할 수 없다.

### Reference

* Head First Design Patterns
