data class UserInput(val name: String?, val email: String?, val age: String?)
data class UserProfile(
    var name: String = "",
    var email: String = "",
    var age: Int = 0,
    var isAdult: Boolean = false
)

fun buildProfile(input: UserInput?, logs: MutableList<String>): UserProfile? {

    input ?: run{
        logs.add("Input is null")
        return null
    }

    input.name ?: run{
        logs.add("Name is null")
        return null
    }


    val name = input.name.trim()
    name.let{
        if (it.length < 3){
            logs.add("Name is too short")
            return null
        }
    }


    input.email ?: run {
        logs.add("Email is null")
        return null
    }

    val email = input.email.trim().lowercase()
    email.let{
        if (!it.contains("@")){
            logs.add("Invalid email")
            return null
        }
    }

    input.age ?: run {
        logs.add("Age is null")
        return null
    }

    val age = input.age.toIntOrNull()
    age ?: run {
        logs.add("Age is null")
        return null
    }

    val profile = UserProfile().apply{
        this.name = name
        this.email = email
        this.age = age
        this.isAdult = age >= 18
    }



    logs.add("Profile created for $email")
    return profile
}

fun main(){
    val input = UserInput( "null" , "test@mail.com", "77")
    val logs = mutableListOf<String>()
    val user = buildProfile(input, logs)
    println(logs)
    println(user)
}