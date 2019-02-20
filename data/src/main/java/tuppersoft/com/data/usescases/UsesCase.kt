package tuppersoft.com.data.usescases

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import tuppersoft.com.domain.Either
import tuppersoft.com.domain.Failure

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {

        doAsync {
            val result = run(params)
            uiThread {
                onResult(result)
            }
        }
    }

    class None
}




