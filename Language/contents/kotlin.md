# __:book: Kotlin 내용 정리__
***
# __:seedling: Types__
기본적인 타입과 어떻게 사용되는 지에 대해 정리
### :pushpin: Kotlin Basic Types
    - Double 64 : 123.5
    - Float 32 : 123.5F/123.5f
    - Long 64 : 123L/123l
    - Int 32 : 123
    - Short 16
    - Byte 8
    - String

### :pushpin: 값의 비교

##### java에서는 == 메모리 비교
```java
val a: Int = 10_000
print(a === a) // Prints 'true'
val boxedA: Int? = a
val anotherBoxedA: Int? = a
print(boxedA === anotherBoxedA) // Prints 'false'
```

##### equals
```java
val a: Int = 10_000
print(a == a) // Prints 'true'
val boxedA: Int? = a
val anotherBoxedA: Int? = a
print(boxedA == anotherBoxedA) // Prints 'true'
```

### :pushpin: 형 변환 : toType() 메서드를 사용

##### 형 변환 예제
```java
// case 1
val a: Int? = 1
val b: Long? = a // Type mismatch
->
val a: Int? = 1
val b: Long? = a?.toLong()

// case 2
var name = "Name"
name = 0 // 오류 발생!!
->
var name = "Name"
name = 0.toString()
// or
name = "0"
```

##### String 한 자씩 출력 비교 (Java vs Kotlin)

```java
// Java code
String name = "abc";
for (int i = 0; i < name.length(); i++){
    System.out.println("charAt(" + name.charAt(i) + ")");
}

// vs

// Kotlin code
val name = "abc"
for (ch in name){
    println("charAt($ch)")
}
```

### :pushpin: String Templates

##### String 출력
```java
// java code
System.out.println("charAt(" + name.charAt(i) + ")");

// kotlin code
println("charAt($ch)")
```

##### list 출력 및 scope 지정
```java
// kotlin list 전체 출력
// "list $list" or "listSize : ${list.size}"
val list = mutableListOf("a", 0, 3, "B")
println("list $list")

// output
// list [a, 0, 3, B]

// list size 출력
println("list {$list.size}")
// output
// list 4

println("list {$list.size > 4}")
// output
// list false
```

# __:seedling: Properties and Fields__

### :pushpin: 변수 선언 방법

```java
// java
// 변수타입 변수이름 = 초기화;
String name = "";

// kotlin
// val/var 변수이름 : 변수타입 = 초기화
val/var name: String = ""
```

- valuable/immutable (val) : Raed-only -> java final
- variable/mutable (var) : Read/Write

### :pushpin: mutable/variable & immutable/valuable

##### mutable/variable
```java
// java
String name = "Name";
// kotlin
var name: String = "Name"
// 유추하기
var name = "Name"
name = "ABC"
```
##### immutable/valuable
```java
// java
final String name = "Name";
// kotlin
val name: String = "Name"
// 유추하기
val name = "Name"
// 대체 불가능
name = "ABC" // Val connot be reassigned
```

### :pushpin: NULL 정의
Kotlin에서는 `null`을 허용하지 않기 때문에 항상 `non-null` 타입으로 변수들이 초기화가 된다.

##### NonNull
```java
var name: String = null // 오류 발생!!
name = "ABC"
```
Kotlin에서 @Nullable 변수를 선언하고 싶다면 타입뒤에 `?`를 붙여줘야 한다.

##### Nullable
```java
var name: String? = null
name = "ABC"
```

Nullable한 변수를 호출하기 위해서는 `safe call (?.)`을 하거나 `non-null asserted (!!.)`를 해야한다.

```java

var name: String? = null
name = "ABC"
name = null
// non-null (!!.) -> 강제로 non-null임을 나타냄
print(name!!.length) // NullPointerException 발생!!

// safe call (?.) null일 경우 null로 출력
print(name!!.length) // output : null
```

### :pushpin: Getters and Setters
    var <propertyName> [: <PropertyType>] [= <property_initializer>]
        [<getter>]
        [<setter>]

    // [] 안에 있는건 생략 가능

```java
class Sample<T> {
    var list: List<T> = mutableListOf()
        set(value) {
            if (value.isNotEmpty()){
                field = value
            }
        }
        get() = field

    val isEmpty: Boolean
        get() = this.list.isEmpty();
}
```

### :pushpin: private 변수 만들기
``` kotlin
// java
class Test {
    private String name = "userName";

    public String getName() {
        return name;
    }
}

// kotlin
// setter가 private 이기 때문에 내부에서는 접근이 가능
// 외부에서는 접근이 불가능하다.
class Test {
    var name: String = "userName"
        private set()
}
// 만약 getter, setter 모두 private 으로 하고 싶다면 변수를 private로
class Test {
    private name: String = "userName"
}
```
### :pushpin: 늦은 초기화

* __:bulb: lazy__
    - 호출 시점에 초기화가 됨
    - val(immutable)과 함께 사용 가능

> lazy 는 클래스 생성 시점이 아닌 변수를 직접 호출 할 때 초기화가 이루어진다.

* __:bulb: lateinit__
    - var(mutable)만 사용 가능
    - null 또는 초깃값을 적용할 필요 없음
    - 늦은 초기화이므로 초기화 전에 사용하면 오류 발생
        - liateinit property subject has not been initialized
    - 변수에 대한 setter/getter 사용할 수 없음

##### lazy 초기화 시점

```java
private val name: String by lazy {
    println("---- lazy init")
    "lazy init"
}

println(name)
println(name)
println(name)
println(name)

// output
// ---- lazy init
// lazy init
// lazy init
// lazy init
// lazy init
```

##### 안드로이드에서 늦은 초기화 사용하기

##### lazy
MainActivity class가 만들어 질 때 초기화 하는 것이 아닌 textView가 호출될 때 초기화를 진행한다.
``` kotlin
class MainActivity : AppCompatActivity() {
    private val textView: TextView by lazy {
        findViewById<TextView>(R.id.text)
    }
}
```
##### lateinit
``` kotlin
class MainActivity : AppCompatActivity() {
    // 무조건 Non-Null이다.
    private lateinit var lateInitName: String

    // 반드시 초기화를 해줘야함
    lateInitName = "lateInitName"
    println(lateInitName)
}
```

# __:seedling: Class__

### :pushpin: 클래스 선언 Java vs Kotlin

```kotlin
// Java
class 클래스이름 {
    // 생성자
    클래스이름(변수) {}
}

// Kotlin
class 클래스이름 constructor(변수) { }

// constructor 생략 가능
class 클래스이름(변수){ }
```

### :pushpin: 다중생성자 Java vs Kotlin

##### java code
```java
class Sample {
    private String name;
    private int age;

    Sample(String name) {
        this.name = name;
    }
    Sample(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

```
##### kotlin code
```kotlin
// primary constuctor - constructor 생략 가능
class Sample constructor (val name: String, val age: Int) {

    // secondary constructor - 생략불가
    // 항상 this에 값을 넘겨 줘야함
    constuctor(name: String) : this(name, 0)
}
```
### :pushpin: Default 생성자

```kotlin
class Sample(val name: String, val age: Int = 0)
```
Sample 클래스를 호출할 때 name과 age 모두를 호출할 수 있는 오버로딩 형태로 호출이 된다. Java에서는 존재하지 않는 개념이지만 아주 좋다. 바로 위의 kotlin코드에서 __secondary constructor를 만들지 않아도 자동으로 생성해준다.__

##### :zap: __사용방법__
```kotlin
// name = name, age = 0
Sample("name")
// name = kotlin, age = 20
Sample("kotlin", 20)
```
__Default 생성자는 위치에 상관 없이 사용이 가능하다.__ 만약 __class Sample(val name: String = "name", val age: Int)__ 로 구현이 되어 있으면 어떻게 활용해야 할까??

```kotlin
// name은 default로 하고 age만 설정하고 싶을 때 - 오류 발생
Sample(0)
```
parameter는 순서를 따르므로 0을 대입했을 때 첫 번째 순서인 name에 대입되기 때문에 형이 맞지 않아 오류가 발생하게 된다. 따라서 __age만 설정하고 싶다면 명시적으로 age를 명시해주고 대입하면 된다.__

```kotlin
Sample(age = 0)
```

### :pushpin: init(초기화)

##### java code
```java
class Sample {
    private String name;
    private int age;

    Sample(String name) {
        this.name = name;
        Log.d("TEMP", "name " + name + "age " + age);
    }
    Sample(String name, int age) {
        this(name);
        this.age = age;
        Log.d("TEMP", "name " + name + "age " + age);
    }
}
```
##### kotlin code
```kotlin
class Sample(val name: String, val age: Int = 0) {
    init {
        println("name : $name, age : $age")
    }
}
```

### :pushpin: 클래스 상속

클래스를 상속할 때 __extends__ 를 사용하지 않고 __:__ 으로 간단하게 상속할 수 있다.
##### java code
```java
public abstract class Base {
    public Base(int age) {

    }
}

// 추상 클래스의 상속을 다음과 같이 사용
public class UseBase extends Base {
    public UseBase(int age) {
        super(age);
    }
}
```
##### kotlin code
```kotlin
abstract class Base(age: Int)

// 추상 클래스를 다음과 같이 사용
class UseBase(age: Int) : Base(age)
```

__Interface 상속__

##### java code
```java
interface Base {
    String getName();
}

// 추상 클래스를 다음과 같이 사용
class UseBase implements Base {
    @override
    public String getName() {
        return "name";
    }
}
```

##### kotlin code
```kotlin
interface Base {
    fun getName(): String
}

// 추상 클래스를 다음과 같이 사용
class UseBase : Base {
    override fun getName() = "name"
}
```

### __:pushpin: class open__

kotlin은 abstract/interface을 __제외한 모든 클래스는 final이다.__ 따라서 확장이 불가능하다. 만약 클래스를 확장 가능하게 하기 위해서는 __open__ 키워드를 사용해야 한다.

##### __상속 불가능__
```kotlin
class Customer (age: Int) : AbstractBase(age), InterfaceBase {
    ...
}

// 상속이 불가능하다.
class User(age: Int) : Customer(age) {

}
```
##### __상속 가능__
```kotlin
open class Customer (age: Int) : AbstractBase(age), InterfaceBase {
    ...
}

// 상속이 가능하다.
class User(age: Int) : Customer(age) {

}
```
