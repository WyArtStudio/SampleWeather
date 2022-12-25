package com.optimasolution.sampleweather.data.util

import com.google.gson.Gson
import com.optimasolution.sampleweather.base.ErrorResponse
import com.optimasolution.sampleweather.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T, U> Flow<ApiResult<T>>.mapToDomain(mapper: (T) -> U): Flow<Resource<U>> =
    this.map {
        when (it) {
            is ApiResult.Success -> {
                Resource.Success(mapper.invoke(it.result!!))
            }
            else -> {
                Resource.Error(it.errorCode ?: 999, it.errorMessage ?: "")
            }
        }
    }

fun <T> Response<T>.call(): Flow<ApiResult<T>> =
    flow<ApiResult<T>> {
        try {
            val response = this@call
            if (response.isSuccessful) {
                emit(ApiResult.Success(response.body()))
            } else {
                response.errorBody()?.let {
                    val errorResponse = Gson().fromJson(it.string(), ErrorResponse::class.java)
                    val message = "CODE ${errorResponse.code}:\n${errorResponse.message.orEmpty()}"
                    emit(ApiResult.Error(errorResponse.code, message))
                }
            }
        } catch (e: UnknownHostException) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        } catch (e: ConnectException) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        } catch (e: SocketTimeoutException) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        } catch (e: IOException) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        } catch (e: Exception) {
            emit(ApiResult.Error(999, "CODE 999: \n${e.message.orEmpty()}"))
        }
    }.flowOn(Dispatchers.IO)