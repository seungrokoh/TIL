# :book: Go_Language
***

# A Tour of Go

* [String과 Byte](#string과-byte)
* [배열과 슬라이스](#배열과-슬라이스)
* [슬라이스 자르기](#슬라이스-자르기)
* [슬라이스 덧붙이기](#슬라이스-덧붙이기)
* [슬라이스 용량](#슬라이스-용량)
***
* [함수](#함수)
* [값으로 취급되는 함수](#값으로-취급되는-함수)
* [고차 함수 (higher-order function)](#고차-함수))
* [클로저](#클로저)
* [메서드](#메서드)
* [포인터 리시버](#포인터-리시버)
* [구조체](#구조체)
* [const와 iota](#const와-iota)
* [구조체 내장](#구조체-내장)
***
* [Practice 23](#practice-23)
* [Practice 36](#practice-36)
* [Practice 41](#practice-41)
* [Practice 44](#practice-44)
* [Practice 58](#practice-58)

### String과 Byte

:bulb: **golang에서 문자열은 바이트들이 연속적으로 나열되어 있는 것**

golang에서 byte의 연속을 `string` 또는 `[]byte`로 나타낼 수 있다.

> []byte와 달리 string은 읽기 전용이다.

:heavy_check_mark: 에러 발생 코드
```go
str := "가나다"
s[2]++  // 에러 발생 (읽기 전용)
fmt.Println(str)
```

:heavy_check_mark: 정상 동작 코드
```go
bytes := []byte("가나다")
b[2]++
fmt.Println(string(b))
```

> []byte To String -> string([]byte)


:bulb: **golang의 소스코드는 UTF-8로 되어있다.**  

소스코드가 UTF-8로 인코딩 되어있다는 의미는 표시되는 문자열 또한 UTF-8로 인코딩 되어있다는 뜻

**하지만** UTF-8은 유니코드 포인트를 나타내기 위한 바이트 수가 **가변적이다.**

> golang은 가변적인 byte를 쉽게 처리할 수 있다.

`string`과 `[]byte` 어떤 자료형을 사용할지 고민 될 때, **어떤 문자들이 들어 있는지를 중시한다면** `string`, **실제 바이트 표현이 어떤지를 중시한다면** `[]byte`를 이용하는 습관을 붙여보도록 하자

### **배열과 슬라이스**

:bulb: `배열`과 `슬라이스` 모두 연속된 메모리 공간을 순차적으로 이용하는 자료구조이다.

> 배열을 직접 사용하기 보단 주로 슬라이스를 이용하여 간접적으로 배열을 이용한다.

:heavy_check_mark: 배열 선언 및 사용
```go
fruits := [3]string{"사과", "바나나", "토마토"}
for _, fruit := range fruits {
    fmt.Printf("%s는 맛있다.\n", fruit)
}
```
**배열은 크기가 자료형에 고정이 되어있다.** 즉, `[3]string` 자료형은 3개의 문자열로 구성된 배열 자료형이다. 위와 같은 이유때문에 **길이와 용량을 갖고 있고 길이가 변할 수 있는 구조인 슬라이스를 주로 사용한다.**

:heavy_check_mark: 슬라이스 선언 및 사용
```go
fruits := make([]string, 3)
fruits[0] = "사과"
fruits[1] = "바나나"
fruits[2] = "토마토"
```

> var fruits []string

위와 같이 선언한 경우 빈 문자열 슬라이스가 생성된다. 기본적으로 빈 슬라이스에는 nil 값이 들어간다.

> fruits := make([]string, n)

위와 같이 선언 할 경우 슬라이스에 몇 개가 들어가는지 미리 알고있는 경우 편리하다. 또한 반복문 등으로 각각을 덮어쓸 수도 있다.

:bulb: **make로 크기를 잡고 만든 슬라이스에는 해당 자료형의 기본형이 들어간다.**

문자열의 경우 `""`이 들어가고, 정수 슬라이스는 `0`값이 들어간다. 포인터형과 슬라이스를 비롯하여 맵, 인터페이스등의 기본값은 `nil`이 들어간다.

### **슬라이스 자르기**

슬라이스는 **원하는 만큼 잘라내서 사용할 수 있다.** 예를 들어, 총 3개의 슬라이스 중 2개만을 사용하고 싶다면 `fruits[:2]`를 쓰면된다. `fruits = fruits[:2]`처럼 잘라서 재 할당을 해도되고, `range fruits[:2]`처럼 for문에서 사용해도 된다.

```go
func slicing() {
    nums := []int{1, 2, 3, 4, 5}
    fmt.Println(nums)
    fmt.Println(nums[1:3])
    fmt.Println(nums[2:])
    fmt.Println(nums[:3])

    // output
    [1, 2, 3, 4, 5]
    [2, 3]
    [3, 4, 5]
    [1, 2, 3]
}
```

**슬라이싱에서 마지막 원소를 제거하고 싶을 땐,** `fruits[:len(frutis) - 1]`을 사용한다. 자주 사용되나 **범위가 넘어가지 않도록 조심해야 한다.**

### **슬라이스 덧붙이기**

std::vector, java.util.ArrayList 등과 같이 슬라이스는 덧붙일 수 있다.

```go
fruits = append(fruits, "포도")
or
fruits = append(frutis, "포도", "딸기")
```

`append()`는 가변인자를 받을 수 있어 여러개를 한 번에 덮붙일 수 있다.

:bulb: **두 슬라이스 이어붙이기**
```go
func sliceAppend() {
    slice1 := []string{"사과", "바나나", "토마토"}
    slice2 := []string{"포도", "딸기"}
    slice3 := append(slice1, slice2...)
    slice4 := append(slice1[:2], slice2...)

    fmt.Println(slice1)
    fmt.Println(slice2)
    fmt.Println(slice3)
    fmt.Println(slice4)

    // Output :
    [사과 바나나 토마토]
    [포도 딸기]
    [사과 바나나 토마토 포도 딸기]
    [사과 바나나 포도 딸기]
}
```

`append`함수는 가변 인자를 받는 함수이기 때문에 **기존 슬라이스에 추가하여 할당하기 위해선** `slice3 := append(slice1, "포도", "딸기")`와 같이 써야한다. 하지만 `append`함수 뒤는 가변인자이기 때문에 `slice`를 넘겨준다면 추가할 수 없다. **왜냐하면 넘겨진 slice는 문자열이 아니고 문자열 슬라이스이기 때문이다.**

따라서 **slice에 있는 값들을 인자를 늘어놓듯이 나열하는 방법은** 위의 코드에서 볼 수 있듯이 slice뒤에 점 3개를 붙여 `slice2...`와 같이 코드를 작성하면 된다.

> 슬라이스를 인자 늘어 놓듯이 나열하기 : slice...

### **슬라이스 용량**

슬라이스는 **연속된 메모리 공간을 활용하는 것** 이므로 용량에 제한이 있다. 따라서 남은 자리가 없는데 덧붙이고자 한다면 **더 넓은 공간을 할당받기 위해 이전 내용이 복사가 되며, 새로운 공간 맨 뒤에 남는 공간에 덧붙이게 된다.**

> 새로운 원소를 추가할 때 슬라이스에 더이상 공간이 없다면 전체를 복사하는 과정을 거친 뒤 추가한다.

슬라이스의 길이를 알아볼 땐 `len(slice)`, 용량을 알아볼 땐 `cap(slice)`를 사용할 수 있다.

```go
func sliceCapacity() {
	nums := []int{1, 2, 3, 4, 5}
	printSlice(nums)
	slice1 := nums[:3]
	printSlice(slice1)
	slice2 := nums[2:]
	printSlice(slice2)
	slice3 := slice1[:4]
	printSlice(slice3)

	nums[2] = 100
	fmt.Println(nums, slice1, slice2, slice3)
}

func printSlice(slice []int) {
	fmt.Println(slice)
	fmt.Println("len :", len(slice))
	fmt.Println("capacity : ", cap(slice))
	fmt.Println()
}

// Output :

[1 2 3 4 5]
len : 5
capacity :  5

[1 2 3]
len : 3
capacity :  5

[3 4 5]
len : 3
capacity :  3

[1 2 3 4]
len : 4
capacity :  5

[1 2 100 4 5] [1 2 100] [100 4 5] [1 2 100 4]

```

> 용량(Capacity)는 뒤에 남은 공간에 따라 결정된다.

위의 예제에서 보면 `nums[:3]`의 경우 **뒤의 2개를 잘랐지만 뒤의 공간은 여전히 2개가 남게 되므로 len = 3, cap = 5가 된다.** 반면에 `nums[2:]`의 경우 **앞에서 2개를 잘라낸 경우 뒤에 남는 공간이 없게 되므로 len = 3, cap = 3이 된다.**

> 잘라냈더라도 뒤에 공간이 있으면 그 공간은 살릴 수 있다.

slice1의 경우 **뒤에 공간이 2개가 빈 상태로 남아있다.** 여기서 `slice3 := slice1[:4]`를 할 경우 뒷 공간이 남아있기 때문에 살릴 수 있다.

> 슬라이스의 용량도 미리 지정할 수 있다.

```go
nums := make([]int, 3, 5) // len = 3, cap = 5

==

nums := make([]int, 5)
nums = nums[:3] // len = 3, cap 5
```

> 한 슬라이스에서 잘려진 슬라이스들은 모두 동일한 메모리를 보고 있다는 사실을 잊지말자 :heavy_exclamation_mark::heavy_exclamation_mark:

:bulb: **빈 슬라이스지만 미리 공간을 할당하고 싶을 때**
```go
nums := make([]int, 0, 15)
```

이 경우 **N개 까지 길이가 늘어나더라도 복사가 일어나지 않게 할 수 있으므로 추가될 개수를 미리 예측할 수 있다면 성능에 도움이 될 수 있다.**

### **슬라이스 내부 구현**

슬라이스는 **배열을 가리키고 있는 구조체라고 볼 수 있다.**

* 슬라이스는 3개의 필드 **( 시작 주소, 길이, 용량 )** 로 이루어져 있다.
* 복사가 일어나서 이동이 일어날 경우 그 슬라이스는 **이동이 이루어진 다른 배열을 보고 있다.**

:bulb: **슬라이스 append 내부 동작**

```go
nums = append(nums, 10)
```
위와 같은 코드가 동작한다고 했을 때 **일단 nums의 늘어난 길이가 용량을 초과할 것인지 아닌지를 조사한다.**

1. **len(nums) + 1 <= cap(nums).** 즉, 용량을 초과하지 않는 경우.
    * 시작 위치에서 길이만큼 오른쪽으로 이동한 위치에 새로운 값을 집어 넣고 길이가 증가한 슬라이스를 반환
2. **len(nums) + 1 > caps(nums).** 즉, 용량을 초과하는 경우.
    * 더 큰 크기의 배열을 새로 하나 더 만들고 슬라이스도 거기에 맞게 고쳐서 반환

즉, **길이가 증가한 슬라이스 또는 새로운 슬라이스를 반환하므로 무조건 그 값을 받아야 한다.**

:heavy_check_mark: 슬라이스 복사
```go
func sliceCopy() {
    src := []int{1, 2, 3, 4, 5}
    dest := make([]int, len(src))
    for index, item := range src {
        dest[index] = item
    }
}
```

직접 for 문으로 만들어도 되지만 **복사 함수인 copy(dest, src)** 를 사용하면 된다.

> 슬라이스 복사 함수 : copy(dest, src)

하지만 **무조건 모든 값이 복사되는건 아니다.** 만약 dest가 src보다 작다면? **copy 함수는 len(src)와 len(dest) 중에서 더 작은 값만큼만 복사를 한다.** 또한 copy 함수는 **복사된 원소의 개수를 반환한다.**

```go
if n := copy(dest, src); n != len(src) {
    fmt.Println("복사가 덜 됐습니다.")
}
```

> copy 함수의 반환 값 : 실제로 복사된 원소의 개수

### **함수**

:heavy_check_mark: 둘 이상의 반환 값 정의
```go
func WriteTo(w io.Writer, lines []string) (int64, error) {
    // body ...
    return n, err
}
```
Go 에서 함수는 **둘 이상의 반환값을 둘 수 있다.**  
만약 위의 함수에서 `n`의 값은 버리고 `err`만 반환받고 싶을 땐 `_ (under-bar)`를 사용하면 된다.

:heavy_check_mark: 선택적으로 return 값 받기
```go
_, err := WriteTo(w, lines)
```

> Golang에서는 관례상 에러는 가장 마지막 값으로 반환한다.

:heavy_check_mark: 조건문에서 error 사용하기
```go
if err := MyFunc(); err != nil {
    // Something ...
}
```

조건문 안에서 변수를 새로 만들고 **조건문 안에서만 사용할 수 있다.**

:heavy_check_mark: 상위 호출자에게 에러 떠넘기기
```go
if err := MyFunc(); err != nil {
    return nil, err
}
```

예외는 **발생한 곳과 처리할 수 있는 곳이 다른 경우가 많다.** 함수를 호출한 함수나 그 함수를 호출한 더 상위의 함수는 **제대로 입력하라는 메세지와 함께 다시 입력을 받거나 하는 등의 처리를 할 수 있다.**

Go에서의 방식은 **예외를 현재 문맥에서 처리할 수 없을 때에는 해당 에러를 그대로 반환할 수 있다.**

:heavy_check_mark: 새로운 에러 생성하기
```go
return errors.New("stringlist.ReadFrom: line is too long")
return fmt.Errorf("stringlist: too long line at %d", count)
```

> 가장 단순한 에러 생성 방법은 "문자열 메세지를 주는 방법이다."

:heavy_check_mark: 가변 인자
```go
func WriteTo(w io.Writer, lines... string) (n int64, err error)

// use
WriteTo(w, "hello", "world", "Go language")
```

:heavy_check_mark: 슬라이스 가변인자로 넘기기
```go
lines := []string{"hello", "world", "Go language"}
WriteTo(w, lines...)
```

### **값으로 취급되는 함수**
Golang에서 함수는 **일급 시민( First-class citizen )으로 분류된다.** 일급 시민으로 분류된다는 뜻은 **값으로 변수에 담길 수 있고, 다른 함수로 넘기거나 돌려받을 수 있다는 뜻이다.**

:heavy_check_mark: 함수 리터럴
```go
func(a, b int) int {
    return a + b
}
```

**함수 리터럴( Function literal)은** 함수명이 없이 순수 return값을 가진 것. 이를 **익명 함수** 라고도 부른다.

> 일반적인 함수에서 함수 이름이 함수의 값을 담는 변수라고 생각해보자

:heavy_check_mark: 함수 리터럴 이해하기
```go
// 일반적인 함수
func printHello() {
    fmt.Println("Hello!")
}
```
위의 함수에서 **이름을 없애고 함수 리터럴만 남기기**
```go
func FunctionLiteral() {
    func() {
        fmt.Println("Hello!")
    }()
}
```
> 함수 리터럴을 작성하고 마지막에 '()'이 해당 함수를 호출한 것이다.

위의 코드가 이해가 가지 않는다면 아래 코드를 보면 이해하기 쉽다.

```go
func FunctionLiteral() {
    printHello := func() {
        fmt.Println("Hello!")
    }
    printHello()
}
```
> 함수 리터럴을 printHello 변수에 담고 해당 함수 리터럴을 호출하는 모양이 같다

### **고차 함수**

> 함수를 넘기고 받기만 하면 고차 함수이다.

:bulb: 고계 함수 이해하기
기존에 만들어 놓은 함수를 변형해가면 이해해보기

:heavy_check_mark: 기존 ReadFrom 함수
```go
func ReadFrom(r io.Reader, lines *[]string) error {
    scanner := bufio.NewScanner(r)
    for scanner.Scan() {
        *lines = append(*lines, scanner.Text())
    }
    if err := scanner.Err(); err != nil {
        return err
    }
    return nil
}
```
위 함수는 파일을 읽어 **무조건 슬라이스에만 추가할 수 있다.** 만약, 맵에 추가하거나, 네트워크를 통하여 데이터를 보내고 싶을땐?? **함수를 추가적으로 생성할 수는 있다.** 하지만 그렇기엔 비용이 너무 많이 든다.

> 이런 상황에서 고차 함수를 사용해보자

:heavy_check_mark: 고차 함수를 이용해 ReadFrom 함수 변경
```go
func ReadFrom(r io.Reader, f func(line string)) error {
    scanner := bufio.NewScanner(r)
    for scanner.Scan() {
        f(scanner.Text())
    }
    if err := scanner.Err(); err != nil {
        return err
    }
    return nil
}
```
위와 같이 `고차 함수(higher-order function)`을 이용하면 **호출자가 자유롭게 원하는 동작을 지시할 수 있게 된다.**

:heavy_check_mark: 고차 함수를 이용해 호출자가 동작 지시
```go
func HigherOrderFunction() {
    r := strings.NewReader("bill\ntom\njane\n")
    err := ReadFrom(r, func(line string) {
        fmt.Println("(", line, ")")
    })
    if err != nil {
        fmt.Println(err)
    }
}
```

### **클로저**
클로저란 **외부에서 선언한 변수**를 **함수 리터럴 내에서 마음대로 접근**할 수 있는 코드를 의미

위의 예제에서 **바로 출력하지 않고 슬라이스에 저장하는 코드로 변경했을 때**

:heavy_check_mark: 파일 읽고 슬라이스에 저장하기 (클로저 사용)
```go
func HigherOrderFunction() {
    r := strings.NewReader("bill\ntom\njane\n")
    var lines []string
    err := ReadFrom(r, func(line string) {
        lines = append(lines, line)
    })
    if err != nil {
        fmt.Println(err)
    }
}
```
위 예제에서 보면 3번째 라인에 선언한 lines 슬라이스를 **ReadFrom에 넘겨주는 함수 리터럴에서 사용하고 있다.**

> ReadFrom에 넘기는 함수는 그 함수가 이용하는 변수들도 함께 가지고 간다.
### **메서드**

__서브루틴__ : 코드의 덩어리를 만든 다음에 그것을 호출하고 반환할 수 있는 구조.
__메서드__ : 서브루틴에 **리시버가 붙은 것**

:heavy_check_mark: 메서드 작성법
```go
func (recv T) MethodName(p1 T1, p2 T2) R1
```
메서드 안에서 recv를 사용할 수 있고, **recv에 담겨 있는 자료에 대한 연산들을 메서드로 정의할 수 있다.**

:heavy_check_mark: 문자열 다중 집합
```go
type MultiSet map[string]int

func (m MultiSet) Insert(val string) {
	m[val]++
}

func (m MultiSet) Erase(val string) {
	if m[val] <= 1 {
		delete(m, val)
	} else {
		m[val]--
	}
}

func (m MultiSet) Count(val string) int {
	return m[val]
}

func (m MultiSet) String() string {
	s := "{ "
	for val, count := range m {
		s += strings.Repeat(val + " ", count)
	}
	return s + "}"
}
```

> 메서드를 활용하면 자료 추상화를 할 수 있다.

단지 map[string]int에 지나지 않았던 자료형에 이름을 붙여 MultiSet이라는 추상 자료형을 만들었다. 이렇게 함으로써 **철저히 MultiSet에 집중하여 이 자료를 다루는 메소드들을 정의할 수 있다.** (코틀린 Extension이랑 비슷한건가..?)

### **포인터 리시버**

* 포인터 리시버 : 자료형이 포인터인 리시버

리시버 역시 다른 함수들과 마찬가지로 **값으로 전달** 된다. 따라서 **포인터로 전달해야 할 경우에는 포인터 리시버를 사용해야 한다.**

:heavy_check_mark: 인접리스트 읽고 쓰기 기본형
```go
adjList [][]int

func WriteTo(w io.Writer, adjList [][]int) error
func ReadFrom(r io.Reader, adjList *[][]int) error
```

:heavy_check_mark: 인접리스트 읽고 쓰기 포인터 리시버
```go
type Graph [][]int

func (adjList Graph) WriteTo(w io.Writer) error
func (adjList *Graph) ReadFrom(r io.Reader) error
```

### **공개 및 비공개**

* 대문자로 시작 할 경우 **다른 모듈에서 보일 수 있다. (호출 가능)**
* 소문자로 시작 할 경우 **다른 모듈에서 보이지 않는다. (호출 불가능)**

> 공개된 요소들은 반드시 주석을 달도록 하자 (godoc이 문서를 만들어 준다)

### **구조체**

* 구조체 : 필드들의 모음 혹은 묶음을 말한다.

:heavy_check_mark: **구조체 생성**
```go
type Task struct {
    title string
    done bool
    due *time.Time
}
```
:heavy_check_mark: **구조체 선언**
```go
var myTask Task

or

var myTask = Task{"laundry", false, nil}

or

var myTask = Task{title: "laundry"}

or

var myTask = Task{title: "laundry", done: true}
```

### const와 iota

> 확장성을 위해서 bool형을 쓸 곳에 enum형을 쓰자

Task의 done 필드가 bool 형으로 되어 있어 **할 일을 완료했다, 완료하지 못했다** 로 표시될 수 있다. **하지만** 현재 상황을 알지 못한다는 값을 추가한다고 한다면????

bool로는 표현을 할 수 없을 뿐더러 **앞으로 더 많은 종류의 상태가 추가 될 가능성이 있기 때문에** done 필드를 **포인터 자료형으로 만들 수 있다.**

```go
type status int

type Task struct {
    title string
    status status
    due *time.Time
}
```

> Go에는 enum이 따로 없고 상수로 정의해서 사용한다.

```go
const UNKNOWN status = 0
const TODO status = 1
const DONE status = 2
```

위의 세 가지 상수는 연관되어 있기 때문에 묶어서 쓸 수 있다.

```go
const (
    UNKNOWN status = 0
    TODO status = 1
    DONE status = 2
)
```

모두 동일한 타입이고 0, 1, 2 순서대로 증가하는 값을 일일히 다 써줘야 하는 노가다성을 없애기 위해서 **iota를 사용하자**

```go
const (
    UNKNOWN status = iota
    TODO
    DONE
)
```

> iota는 순서대로 0부터 증가한 값이 들어가며 자료형도 함께 들어간다

### **구조체 내장**

Golang에서 구조체의 특별한 점은 **여러 자료형의 필드를 가질 수 있다는 점** 이다.

* 상속 : Is A 관계
* 포함 : Has A 관계

:bulb: **Go에서 포함관계 쉽게 활용하기**

위의 예제에서 Deadline이라는 자료형을 만드는데 **마감 시간 필드 하나만 있는 구조체를 굳이 만들 필요가 없다.**

> 명명된 자료형, 자료형에 이름을 붙여 메서드를 작성하면 된다.

:heavy_check_mark: **Deadline자료형에 OverDue 메서드 정의**
```go
type Deadline time.Time

// Deadline이 없는 경우를 위해 포인터 리시버로 정의
func (d *Deadline) OverDue() bool {
	return d != nil && time.Time(*d).Before(time.Now())
}
```

:heavy_check_mark: **마감시간을 가진 Task 구조체 정의**
```go
type Task struct {
	Title string
	Status status
	Deadline *Deadline
}
```

:heavy_check_mark: **Task에 OverDue 메서드 정의**
```go
func (t Task) OverDue() bool {
	return t.Deadline.OverDue()
}
```

> Task의 OverDue 메서드는 그저 Deadline에 자기가 할 일을 위임할 뿐이다.

Task 구조체가 **Deadline 자료형의 필드를 가지고 있어 메서드도 이용할 수 있다.** 하지만, 메서드마다 모두 같은 이름의 메서드를 호출하는 코드를 작성해야 하지만 **내장 기능을 이용한다면 이 귀찮음을 덜을 수 있다.**

:heavy_check_mark: **내장 기능**
```go
type Task struct {
    Title string
    Status status
    *Deadline
}
```
필드의 이름을 생략하면 **Task에 대하여 의미없는 OverDue 메서드를 작성할 필요가 없다.** Task가 내장하고 있는 자료형(Deadline) 이름과 같은 필드를 가지게 되고 **정의되어 있는 메서드도 바로 호출할 수 있게 된다.**

:bulb: **필드 내장의 문제점**
필드가 내장되어 있으면 **내장된 필드가 구조체 전체의 직렬화 결과를 바꿔버리는 문제점이 있다.**

:heavy_check_mark: **구조체 내장 예제**
```go
type status int

type Deadline struct {
	time.Time
}

func NewDeadline(t time.Time) *Deadline {
	return &Deadline{t}
}
type Task struct {
	Title string
	Status status
	Deadline *Deadline
}

type Address struct {
	City string
	State string
}
type Telephone struct {
	Mobile string
	Direct string
}

type Contact struct {
	Address
	Telephone
}

func main() {
	var c Contact
	c.Mobile = "123-456-789"
	fmt.Println(c.Telephone.Mobile)
	c.Address.City = "San Francisco"
	c.State = "CA"
	c.Direct = "N/A"
	fmt.Println(c)
}
```

> 구조체를 내장하게 되면 내장된 구조체에 들어있는 필드들도 바로 접근 가능하다.

구조체 내장을 이용하면 **여러 구조체에 잇는 필드들이 모두 합쳐진 구조체 같은 것을 만들 수 있다.** 그렇다고 실제로 합쳐진 것은 아니고 **필드에 접근할 때 굳이 길게 쓸 필요가 없다는 뜻**

> 상속과 같은 개념과는 달리 실제로는 내부에 필드로 내장하고 있으면서 편의를 제공해주는 것 뿐이다.

***

### Practice 23

```go
package main

import (
    "fmt"
    "math"
)

func Sqrt(x float64) float64 {
    var z = x

    for {
        nextValue := z - (z * z - x) / (2 * z)
        if float32(nextValue) == float32(z) {
            return nextValue
        }
        z = nextValue
    }
}


func main() {
    fmt.Println(Sqrt(2))
    fmt.Println(math.Sqrt(2))
}

```

### Practice 36

```go
package main

import "code.google.com/p/go-tour/pic"

func Pic(dx, dy int) [][]uint8 {
    var arr = make([][]uint8, dy)
    for y := range arr {
        arr[y] = make([]uint8, dx)

        for x := range arr[y] {
            arr[y][x] = uint8(x^y)   
        }
    }
    return arr
}

func main() {
    pic.Show(Pic)
}
```

### Practice 41

```go
package main

import (
    "code.google.com/p/go-tour/wc"
    "strings"
)

func WordCount(s string) map[string]int {
    wordMap := make(map[string]int)

    for _, key := range strings.Fields(s) {
        wordMap[key]++
    }
    return wordMap
}

func main() {
    wc.Test(WordCount)
}

```

### Practice 44

```go
package main

import "fmt"

// fibonacci is a function that returns
// a function that returns an int.
func fibonacci() func() int {
    num1, num2, temp:= 0, 1, 0
    return func() int {
        temp = num1
        num2, num1 = num1 + num2, num2
        return temp
    }
}

func main() {
    f := fibonacci()
    for i := 0; i < 10; i++ {
        fmt.Println(f())
    }
}
```

### Practice 58

```go
package main

import (
	"fmt"
	"net/http"
)

func main() {
	http.Handle("/string", String("I'm a frayed knot."))
	http.Handle("/struct", &Struct{"Hello", ":", "Gophers!"})
	http.ListenAndServe("localhost:4000", nil)
}

type String string

type Struct struct{
	Greeting string
	Punct string
	Who string
}

func (s String) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, s)
}

func (s Struct) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, s.Greeting + s.Punct + s.Who)
}
```
