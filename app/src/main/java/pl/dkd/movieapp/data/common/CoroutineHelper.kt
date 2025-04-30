package pl.dkd.movieapp.data.common

import kotlinx.coroutines.delay

suspend fun <T> retry(
    times: Int = 3,
    initialDelay: Long = 100,
    maxDelay: Long = 1000,
    factor: Double = 2.0,
    work: suspend () -> T
): T {
    var currentDelay = initialDelay
    repeat(times - 1) {
        try {
            return work()
        } catch (e: Exception) {
            println(e)
        }
        delay(100)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    return work()
}
