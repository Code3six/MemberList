package com.example.memberlist

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit.Builder()
    .baseUrl("https://dummyjson.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

object UserApi {
    val retrofitService: UserService by lazy { retrofit.create(UserService::class.java) }
}

interface UserService{
    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id :Int): User

    @GET("/users")
    suspend fun getAllUsers():List<User>
}




class UserViewModel: ViewModel() {

    private val _users = MutableLiveData<List<User>>(emptyList())
    val user = _users as LiveData<List<User>>

    init {
        viewModelScope.launch {
            try {
                // Calling the repository is safe as it will move execution off
                // the main thread
                val user = UserApi.retrofitService.getAllUsers()
                _users.value = user
            } catch (error: Exception) {
                //
            }
        }
    }

}

