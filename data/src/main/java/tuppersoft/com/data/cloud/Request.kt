package tuppersoft.com.data.cloud

import retrofit2.Call
import tuppersoft.com.domain.Either
import tuppersoft.com.domain.Failure

fun <T, R> cloudRequest(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
    return try {
        val response = call.execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform((response.body()!!)))
            false -> Either.Left(Failure.ServerError)
        }
    } catch (exception: Throwable) {
        Either.Left(Failure.ServerError)
    }
}