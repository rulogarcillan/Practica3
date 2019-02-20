package tuppersoft.com.data.usescases

import android.app.Application
import tuppersoft.com.data.repositories.UsersRepository
import tuppersoft.com.domain.dtos.User

class SaveUser private constructor() : UseCase<User, SaveUser.Params>() {

    private val repository = UsersRepository.Network()

    companion object {
        fun newInstance(): SaveUser = SaveUser()
    }

    override suspend fun run(params: Params) = repository.saveUser(params.app, params.user)

    data class Params(val app: Application, val user: User)
}