package com.example.deliveryapp.data.commun

import com.example.deliveryapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

/**
 * extension function for Flow Class to emit loading state before the flow starts
 */
fun <T> Flow<ViewStateResult<T>>.onFlowStarts() =
        onStart {
            emit(ViewStateResult.Loading)
        }.catch {
            emit(ViewStateResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message)))
        }
