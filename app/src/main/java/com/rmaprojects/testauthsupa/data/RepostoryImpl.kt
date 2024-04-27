package com.rmaprojects.testauthsupa.data

import com.rmaprojects.apirequeststate.RequestState
import com.rmaprojects.testauthsupa.MainActivity
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepostoryImpl @Inject constructor(
    private val client: SupabaseClient,
) : Repository {

    override suspend fun register(email: String, password: String): Flow<RequestState<Boolean>> =
        flow {
            emit(RequestState.Loading)
            try {
                val authProcess =
                    client.auth.signUpWith(Email) {
                        this.email = email
                        this.password = password
                    }
                emit(RequestState.Success(authProcess != null))
            } catch (e: Exception) {
                emit(RequestState.Error(e.toString()))
            }
        }
}