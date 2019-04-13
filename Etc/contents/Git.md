# :seedling: __Git__
---

### :pushpin: 이미 push 된 파일 `.gitignore` 적용하기

`Github`에 이미 올려져 있는 파일중 `.gitignore`를 적용하고 싶을 때

```c
$ git rm -r --cached .
$ git add .
$ git commit -m "Apply .gitignore"
$ git push
```
