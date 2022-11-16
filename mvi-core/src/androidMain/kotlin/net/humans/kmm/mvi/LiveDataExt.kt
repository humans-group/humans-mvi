package net.humans.kmm.mvi

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Update LiveData value using [mutator] lambda
 */
@Deprecated("Use StateFlow instead of LiveData")
inline fun <reified T> MutableLiveData<T>.mutate(mutator: T.() -> T) {
    value?.let { old ->
        val new = mutator.invoke(old)
        // reference check only to avoid complex comparisons in main thread
        if (new !== old) {
            value = new
        }
    }
}

/**
 * Update LiveData value using [mutator] lambda from another thread
 */
@Deprecated("Use StateFlow instead of LiveData")
suspend inline fun <reified T> MutableLiveData<T>.postMutate(mutator: T.() -> T) {
    var isNotSet = true
    while (isNotSet) {
        value?.let { old ->
            val new = mutator.invoke(old)
            withContext(Dispatchers.Main) {
                // 1. reference check only to avoid complex comparisons in main thread
                // 2. check that old value does not changed since mutation
                if (old === value) {
                    value = new
                    isNotSet = false
                }
            }
        }
    }
}

/**
 * Transform LiveData value using state from another thread
 */
@Deprecated("Use StateFlow instead of LiveData")
suspend fun <T, U> MutableLiveData<U>.mutate(state: T, transform: (U?, T) -> U) {
    var isNotSet = true
    while (isNotSet) {
        val oldLiveDataValue = value
        val newLiveDataValue = transform.invoke(oldLiveDataValue, state)
        withContext(Dispatchers.Main) {
            if (oldLiveDataValue === value) {
                value = newLiveDataValue
                isNotSet = false
            }
        }
    }
}
