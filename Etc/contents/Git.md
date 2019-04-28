# :seedling: __Git__
---
:book: contents

* 이미 push 된 파일 `.gitignore` 적용하기
* 특정 브랜치 clone
* git branch merge options

### :pushpin: 이미 push 된 파일 `.gitignore` 적용하기

`Github`에 이미 올려져 있는 파일중 `.gitignore`를 적용하고 싶을 때

```c
$ git rm -r --cached .
$ git add .
$ git commit -m "Apply .gitignore"
$ git push
```

### :pushpin: 특정 브랜치 clone

git clone [address] 를 하면 기본적으로 `master` 브랜치를 받게 되는데 __특정 브랜치를 가져오는 방법은__ 다음과 같다.

```c
$ git clone -b <branch-name> <address>
```
