## Observable _ Just,From,defer
- 새로운 Observable을 만드는 연산자들

1. Just : 객체 하나 또는 객채집합을 Observable로 변환, 변환된 Observable은 원본 객체들을 발행
2. From : 다른 객체나 자료 구조를 Observable로 변환, 순차적으로 발행하는 Observable을 생성
3. Defer : 옵저버가 구독하기 전까지는 Observable 생성을 지연하고 구독이 시작되면 옵저버 별로 새로운 Observable을 생성

> Just
```java
 private List<String> datas = new ArrayList<>();
Observable<Memo> forJust;
private void initObservable(){    // 발행할 준비
// just 생성
        Memo memo1 = new Memo("hi");
        Memo memo2 = new Memo("hi2");
        Memo memo3 = new Memo("hi3");
        Memo memo4 = new Memo("hi4");
        forJust = Observable.just(memo1,memo2,memo3,memo4);
}

public void btnJust(View v){  // 객체 하나,하나를 넘김
    forJust.subscribe(
            obj -> datas.add(obj.content),   // onNext와 같은 의미
            t   -> {/*일단 아무것도 안함 */},
            ()  -> adapter.notifyDataSetChanged()    // onComplete와 같은 의미
    );

}
```
![스크린샷 2017-07-19 오전 12.09.25](http://i.imgur.com/5JPNxxX.png)
> From
```java
Observable<String> forFrom;
private void initObservable(){    
        // from 생성
        String fromData[] = {"aa","bb","cc","dd","ee","ff"};
        forFrom = Observable.fromArray(fromData);   // 데이터 length 확인
}

public void btnFrom(View v){   // datas를 넘겨주는
    forFrom.subscribe(
            str -> datas.add(str),                  // 옵저버블(발행자 : emitter)로부터 데이터를 가져옴
            t   -> {/*일단 아무것도 안함 */},
            ()  -> adapter.notifyDataSetChanged()   // 완료되면 리스트에 알림
    );
}
```
![스크린샷 2017-07-19 오전 12.09.33](http://i.imgur.com/EXyLsra.png)
> defer
```java
Observable<String> forDefer;
private void initObservable(){
forDefer = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just("one","two","three","four");
            }
        });

    }

    public void btnDefer(View v){
    forDefer.subscribe(
            str -> datas.add(str),
            t   -> {/*일단 아무것도 안함 */},
            ()  -> adapter.notifyDataSetChanged()
    );

}
```
![스크린샷 2017-07-19 오전 12.09.45](http://i.imgur.com/sSo4tLo.png)

[전체 소스코드 보기](https://github.com/daaa08/RxAndroidBasic2/blob/master/app/src/main/java/com/example/da08/rxandroidbasic2/MainActivity.java)
