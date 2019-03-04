# __Design Patterns__

**:book: contents**
* 싱글턴(Singletone) 패턴
* 스트래티지(Strategy) 패턴

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

---

### __스트래티지(Strategy) 패턴__

* 알고리즘군을 정의하고 각각을 캡슐화하여 교환해서 사용할 수 있도록 만든다.
* 알고리즘을 사용하는 클라이언트와는 독립적으로 알고리즘을 변경할 수 있다.

정의가 너무 어렵게 되어 있지만 예제를 보면서 풀어나가면 이해하는데 도움이 많이 될 것이다. HeadFirst의 예제를 들고 이해를 해보자.

#### 오리세상 어플리케이션 만들기
오리를 좋아하는 어린이들을 위해서 연못에 오리들을 보여주는 시스템을 개발해달라는 부탁이 들어왔다. 오리는 헤엄칠 수 있고, 꽥꽥거리는 소리도 낼 수 있으며 여러 종류의 오리가 존재할 수 있다.

객체지향을 배우고 있는 '나'는 `오리`들이 공통적으로 가지고 있는 기능? 들을 뽑아내 `Duck`클래스를 만들었다.

```java
public abstract class Duck {
    public void quack(){
        System.out.print("꽥꽥");
    }
    public void swim(){
        System.out.print("헤엄친다.");
    }

    public void fly(){
        System.out.print("날아다닌다");
    }

    // 오리 모양을 재정의 하도록
    public abstract void display();
}
```

이제 오리들을 만들어 각자의 모양을 만들어주면 된다.

```java
public class MallardDuck extends Duck {
    @Override
    public void display(){
        System.out.print("MallardDuck 모양");
    }
}

public class RedheadDuck extends Duck {
    @Override
    public void display(){
        System.out.print("RedheadDuck 모양");
    }
}
```

하지만 여기서 `치명적인 문제점이 있다.` 만약 `장난감 오리`가 추가 된다면?? `장난감 오리`는 날지도 못 할 뿐더러 꽥꽥이 아닌 삑삑 소리를 낸다. `장난감 오리`가 `Duck`클래스를 상속한다면 문제가 될 수 있다.

이러한 문제점을 해결 할 수 있는 방법은 존재한다. `장난감 오리`는 `quack()과 fly()`를 오버라이드 하면 된다. `fly()` 메서드를 오버라이드 한 뒤 아무것도 하지 않게 하면 간단하게 문제가 해결 됩니다. 만약 `나무 오리`를 만들게 된다면 `quack()과 fly()`를 오버라이딩 해서 아무것도 행동하지 않게 하면 된다.

> 과연 이게 좋은 방법일까???

#### Interface를 활용한다면??

Duck클래스에서 fly() 와 quack()을 분리하여 `Flyable 인터페이스`와 `Quackable 인터페이스`를 만들어 사용 할 수 있다.

> 하지만 이렇게 한다면 코드 중복 뿐만 아니라, 한 행동을 바꿀 때마다 그 행동이 정의되어 있는 서로 다른 서브클래스들을 전부 찾아서 코드를 일일이 고쳐야 하는 문제점이 발생한다.

#### 디자인 원칙

> 애플리케이션에서 달라지는 부분을 찾아내고, 달라지지 않는 부분으로부터 분리 시킨다.

즉, 코드에 새로운 요구사항이 있을 때마다 바뀌는 부분이 있다면, `그 행동을 바뀌지 않는 다른 부분으로부터 골라내서 분리`해야 한다. 이 원칙은 다음과 같이 생각할 수 있다.  
`바뀌는 부분`은 따로 뽑아서 `캡슐화`시킨다. 그렇게 하면 나중에 바뀌지 않는 부분에는 영향을 미치지 않은 채로 `그 부분만 고치거나 확장`할 수 있다.

> 코드를 변경하는 과정에서 의도하지 않은 일이 일어나는 것을 줄이면서 시스템의 유연성은 향상시킬 수 있다.

#### 바뀌는 부분과 그렇지 않은 부분 분리하기

그렇다면 어떻게 접근을 해야 할 지 생각해보자. `Duck`클래스에서 오리마다 달라지는 부분은 `fly()와 quack()`메서드 이다. 이러한 행동을 `Duck`클래스로부터 끄집어내서 각 행동을 나타낼 클래스 집합을 새로 만들자.

> 구현이 아닌 인터페이스에 맞춰서 프로그래밍한다.

먼저 오리들이 날아다니는 행동을 분리해 보자.
```java
public interface FlyBehavior {
    public void fly();
}

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly(){
        System.out.print("날아 다닌다~");
    }
}

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly(){
        System.out.print("날아 다닐 수 없어요~");
    }
}
```

Duck의 날아다니는 행동에 대해서 `날 수 있는 오리`와 `날지 못하는 오리`에 대해 구체적인 행동을 구현하는 클래스를 만들 수 있다. Quack 또한 마찬가지로 구체적인 행동에 따른 클래스를 추가할 수 있다.

```java
public interface QuackBehavior {
    public void quack();
}

public class Quack implements QuackBehavior {
    public void quack(){
        System.out.print("꽥~");
    }
}

public class MuteQuack implements QuackBehavior {
    public void quack(){
        System.out.print("조용~");
    }
}

public class Squeak implements QuackBehavior {
    public void quack(){
        System.out.print("삑~");
    }
}
```

#### Duck 행동 통합하기

가장 중요한 일이 남아 있다. Duck에서 나는 행동과 꽥꽥 소리를 내는 행동을 Duck 클래스(또는 서브클래스)에서 정의한 메소드를 써서 구현하지 않고 다른 클래스에 위임한다. 기존의 메서드인 fly()와 quack()을 직접 구현하는 것이 아닌 다른 클래스에 위임하는 것이다. 그리고 fly()와 quack()을 행동하는 performFly()와 performQuack()을 구현한다.

첫 번째로, Duck 클래스에서 flyBehavior와 quackBehavior라는 `두 개의 인터페이스 형식의 인스턴스 변수를 추가`한다.(특정 구상 클래스 형식으로 선언하지 않음) 이는 각 오리 객체에서 실행 시 이 변수에 특정 행동 형식에 대한 레퍼런스를 `다형적으로` 설정한다.

두 번째로, 오리의 행동 형식을 생성자에서 인스턴스를 만드는 방법이 아닌 Duck의 서브클래스에서 세터 메서드(Setter)를 호출하는 방법으로 동적으로 행동을 지정할 수 있도록 한다.

```java
public class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    public Duck() { }

    public abstract void display() { }

    public void swim(){
        System.out.print("모든 오리는 물에 떠다녀요~");
    }
    public void performFly(){
        flyBehavior.fly();
    }

    public performQuack(){
        quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehavior fb){
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb){
        quackBehavior = qb;
    }
}
```

이제 Duck의 서브클래스를 새로 만들면서 원하는 오리들을 추가할 수 있습니다.

```java
public class ModelDuck extends Duck {
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    public void display(){
        System.out.print("저는 모형 오리입니다.");
    }
}
```

##### 테스트 code
```java
public class MiniDuckSimulator {
    public static void main(String[] args){
        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();

        Duck mallard = new MallardDuck();
        mallard.performFly();
        mallard.performQuack();
    }
}
```

이렇게 새로운 클래스구조를 통해 모든 오리들은 Duck클래스를 확장해서 만들고 나는 행동과 꽥꽥소리를 내는 행동을 flyBehavior와 quackBehavior를 구현해서 만들게 된다. 즉, `오리의 행동들을 일련의 행동으로 생각하는 대신, 알고리즘군으로 생각한다.`

#### A에는 B가 있다

`A는 B이다`보다 `A에는 B가 있다`가 나을 수 있다. 각 오리에는 FlyBehavior와 QuackBehavior가 있으며, 각각 행동과 꽥꽥거리는 행동을 위임 받는다.  
두 클래스를 이런 식으로 합치는 것을 `구성(composition)`을 이용하는 것이라고 부른다.

> 상속보다는 구성을 활용한다.

### Reference

* Head First Design Patterns
