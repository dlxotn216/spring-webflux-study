package me.taesu.reactorwebfluxstudy.marblediagram

import reactor.core.publisher.Mono

fun main() {
    // 0..1 개의 데이터를 전달하는 Publisher
    Mono.just("Hello reactor")
        .subscribe(::println)

    // Empty operator는 데이터 전달 없이 작업의 완료를 알리고 그에따른 후처리만 하고 싶을때 사용 함
    // OnComplete만 호출 됨
    Mono.empty<String>()
        .subscribe(
            { println("onNext $it") },
            { println("onError $it") },
            { println("onComplete") }
        )
}
