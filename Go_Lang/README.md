# :book: Go_Language
***

# A Tour of Go

* [Practice 23](#practice-23)
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
