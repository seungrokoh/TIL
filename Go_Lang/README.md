# :book: Go_Language
***

# A Tour of Go

* [String과 Byte](#string과-byte)
* [배열과 슬라이스](#배열과-슬라이스)
* [슬라이스 자르기](#슬라이스-자르기)
* [슬라이스 덧붙이기](#슬라이스-덧붙이기)
* [슬라이스 용량](#슬라이스-용량)
* [Practice 23](#practice-23)
* [Practice 36](#practice-36)

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
