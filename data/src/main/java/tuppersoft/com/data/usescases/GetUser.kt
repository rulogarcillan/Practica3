package tuppersoft.com.data.usescases

import android.app.Application
import tuppersoft.com.data.repositories.UsersRepository
import tuppersoft.com.domain.dtos.User


class GetUser private constructor() : UseCase<User, GetUser.Params>() {

    companion object {
        fun newInstance(): GetUser = GetUser()
    }

    override suspend fun run(params: Params) = UsersRepository.Network.getUser(params.app, params.userId)

    data class Params(val app: Application, val userId: String)
}
