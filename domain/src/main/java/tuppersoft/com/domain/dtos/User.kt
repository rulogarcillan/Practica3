package tuppersoft.com.domain.dtos

data class User(
    var userId: String,
    var name: String,
    var email: String,
    var avatar: String? = null
)



