# Animatino

Animation을 적용하다가 발생한 issue에 대해 정리하는 공간입니다.
***

### __Issue 01 : Circular Reveal Animation 종료 후 화면 깜빡임__

총 2개의 `Fragment`를 가지고 Circular Reveal Animation을 구현하다 __화면이 종료되고 깜빡이는 현상이 발생__ 하는 문제를 해결한 내용입니다.

`Fragment 1`이 띄워져 있는 상태에서 `Fragment 2`를 Circular Animation을 이용해 open하고 __다시 Circular Animation을 이용해 닫을 때 Animation이 종료되고 Fragment 2가 잠깐 보였다 사라지는 현상이 발생하였다.__ 매끄럽지 못한 화면 전환 때문에 거슬려 문제해결 방법을 찾던 도중 `fillAfter 속성`이 있다는 사실을 발견하고 해당 내용을 찾아보았다.

안드로이드 Animation 작업을 할 때 `android:fillAfter` 속성을 정의할 수 있다. 이 속성의 의미는 __Animation이 끝났을 떄 그 상태를 유지 할 것인가 처음 상태로 돌아갈 것인가를 설정하는 속성이다.__ 간단하게 예를 들자면 `fillAfter = true`일 경우 `Fragment 2`가 작아지는 Animation이 끝난 후 __그 상태를 계속 유지하고 있겠다는 뜻이다.__ 반대로 `fillAfter = false`일 경우 Animation이 끝난 후 __다시 원래의 상태로 돌아가기 때문에 화면이 잠깐 그려졌다 Fragment remove에 의해 사라지기 때문에 화면이 깜빡이는 것 처럼 보이는 것이다.__

만약 Custom Animation을 작성하여 `fillAfter` 속성을 적용하고 싶다면 __해당 Animation xml 파일에 속성을 정의해주기만 하면 된다.__

```java
<set
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:fillAfter="false">
    <alpha android:fromAlpha="0.0" android:toAlpha="1.0"
        android:duration="@android:integer/config_longAnimTime" />
</set>
```

> translation 태그 안에서는 적용이 안될 수 있다.

하지만 Circular Animation을 적용할 때 `Animation xml`파일을 사용하지 않았기 때문에 다른 방법을 사용하기로 하였다. __Animation이 끝났을 때 해당 View의 visibility를 INVISIBLE 상태로 변경__ 하면 된다.


:seedling: revealAnimation 등록
```java
View.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
    @Override
    public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        view.removeOnLayoutChangeListener(this);
        // ...

        v.setVisibility(View.VISIBLE);
        revealAnimation.start();
    }
})
```

:seedling: unRevealAnimation
```java
@Override
public void onUnrevealFragment(View view, Fragment fragment, int x, int y) {
    if (fragment instanceof SecondFragment) {
        final SecondFragment myFragment = (SecondFragment) fragment;

        Animator unrevealAnimator = myFragment.prepareUnrevealAnimator(x, y);
        unrevealAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    // remove the fragment only when the animation finishes
                    getFragmentManager().beginTransaction().remove(myFragment).setCustomAnimations(R.anim.fade_out, R.anim.fade_out).commit();
                    //to prevent flashing the fragment before removing it, execute pending transactions inmediately
                    view.setVisibility(View.INVISIBLE);
                    getFragmentManager().popBackStack();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            unreveal.start();
    }
}

```
