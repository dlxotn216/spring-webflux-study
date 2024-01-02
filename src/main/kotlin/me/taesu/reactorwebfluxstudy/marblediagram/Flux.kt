package me.taesu.reactorwebfluxstudy.marblediagram

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {
    Flux.just(1, 2, 3, 4)
        .map { it % 2 == 0 }
        .subscribe { println(it) } // true, false, true, false

    // 배열을 입력으로 받는 fromArray operator
    Flux.fromArray(intArrayOf(1, 2, 3, 4, 5).toTypedArray())
        .filter { it % 2 == 0 }
        .subscribe { println(it) } // 2, 4

    // https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#concatWith-org.reactivestreams.Publisher-
    val flux = Mono.justOrEmpty("Taesu").concatWith(Mono.justOrEmpty("lee"))

    // concatWith operator는 두개의 Publisher를 합쳐서 하나의 Flux로 만들어 준다.

    // Taesu
    // lee
    flux.subscribe { println(it) }

    // Empty publisher는 onNext 시그널이 없음을 주의
    Mono.justOrEmpty<String>(null)
        .concatWith(Mono.justOrEmpty("Taesu"))
        .subscribe { println(it) }  // Taesu만 출력 됨

    val concatFlux: Flux<String> = Flux.concat(
        Flux.just("Monday", "Tuesday", "Wednesday"),
        Flux.just("Thursday", "Friday", "Saturday"),
        Flux.just("Tuesday", "Sunday", "Wednesday"),
    )
    // https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#collectList--
    // 마블 다이어그램 상 Flux 원소가 하나의 Mono로 합쳐짐
    val monoList: Mono<List<String>> = concatFlux.collectList()
    monoList.subscribe { println(it) } // [Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Tuesday, Sunday, Wednesday]

}
