# Go-Module

* Go Module이란 프로그램 안에서 사용하는 dependency를 관리하기 위한 도구이다. 

* Go에서는 go get이라는 command로 module을 다운로드하고 import할 수 있었지만 version 관리 기능이 없다. 

* Go Module은 이러한 불편함을 해소하기 위해 만들어졌다.
* Go의 module system은 [semantic versioning](https://semver.org)을 기본으로 versioning 한다.
    * v (major) . (minor) . (patch) - (ex. v1.0.0)



모듈은 관련된 패키지들을 모아놓은 것이고, 그 전체를 하나의 유닛으로 버전관리한다.

이를 통해 모듈에 필요한 패키지들의 정확한 버전을 알 수 있다.



여러 모듈을 하나의 repository에 관리할 수도 있으나, 하나의 저장소에 하나의 모듈을 관리하는 것을 원칙으로 한다.



## Repository, Module, Package의 관계

* Repository는 하나 이상의 Go Module을 가진다.
* Module은 하나 이상의 Go Package를 가진다.
* Package는 하나의 directory안에, 하나 이상의 Go source file을 가진다.



## Module 만들기

* 작업할 디렉토리를 만든다. $GOPATH/src가 아닌 곳에 만들어야 한다.
* Working directory안에서 해당 directory를 go module로 만드는 초기화 작업을 해준다.

```powershell
$ go mod init github.com/seungrokoh/go-module-tutorial
```

* 초기화 작업 후 go.mod 파일이 생성된다.
* subdirectory에 여러개 존재하지 않으며 오직 root module에 딱 하나만 의미가 있다.
* subdirectory를 import하는 방법은 module 이름에 subdirectory를 적기만 하면 된다.

```go
import (
	"github.com/seungrokoh/go-module-tutorial/sub1"
    "github.com/seungrokoh/go-module-tutorial/sub2"
    "github.com/seungrokoh/go-module-tutorial/sub2/subsub1"
)
```



## Module 적용하기

.go 에서 사용하고 싶은 package를 import 한다. 이때 일반적으로 사용하는 방법은 다음과 같다.

* .go에 사용하고 싶은 package를 import한다.
* go build 또는 go test를 진행하면 추가한 package dependency 들이 반영된다.
    * go.mod가 업데이트 되고
    * 추가한 dependency가 다운로드 된다.
* 사용하고 싶은 package의 버전이 있다면 수정할 수 있다.
    * go get에서 명시 하거나
    * go.mod를 직접 수정한다.



## Module Command 사용

* 빌드시 사용된 module 들의 버전을 확인할 수 있다.

```powershell
$ go list -m all
> github.com/seungrokoh/go-module-tutorial
> golang.org/x/text v0.3.0 -> /tmp/text
> rsc.io/pdf v0.1.1
```

* package 들의 minor, patch 가능 여부를 확인할 수 있다.

```powershell
$ go list -u -m all
> github.com/seungrokoh/go-module-tutorial
> golang.org/x/text v0.3.0 [v0.4.0] => /tmp/text
> rsc.io/pdf v0.1.1 [v0.1.2]
```

* 최신 버전으로 업데이트 할 수 있다.

```powershell
$ go get -u
or
$ go get -u=patch
```

* 불필요하게 dependency를 제거하고 필요한 것들을 추가할 수 있다.

```powershell
$ go mod tidy
```

