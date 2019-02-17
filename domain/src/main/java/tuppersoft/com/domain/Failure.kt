package tuppersoft.com.domain

sealed class Failure {
    object ServerError : Failure()
    object DbError : Failure()
}