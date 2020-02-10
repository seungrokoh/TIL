# :book: Go_Language
***

# A Tour of Go

* [Practice 23](#practice-23)
* [Practice 36](#practice-36)
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
