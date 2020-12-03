package benchmark

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime

@ExperimentalStdlibApi
@State(Scope.Benchmark)
class TestBenchmark {

    private fun benchmark() : List<Int> {
        return buildList {
            addAll(0..100)
            shuffle()
            sortDescending()
        }
    }

    final fun measureTime() {
        val result: Any?
        val time = measureNanoTime {
            result = benchmark()
        }
        println("$time ns")
    }

    @ExperimentalStdlibApi
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    final fun benchmarkFunction() {
        benchmark()
    }
}

@ExperimentalStdlibApi
fun main() {
    TestBenchmark().measureTime()
}
