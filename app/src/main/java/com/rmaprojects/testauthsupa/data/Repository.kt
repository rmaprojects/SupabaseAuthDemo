package com.rmaprojects.testauthsupa.data

import com.rmaprojects.apirequeststate.RequestState
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun register(email: String, password: String): Flow<RequestState<Boolean>>
}