package com.rmaprojects.testauthsupa.di

import com.rmaprojects.testauthsupa.data.Repository
import com.rmaprojects.testauthsupa.data.RepostoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://fdpznhscsdkkftbtkhty.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZkcHpuaHNjc2Rra2Z0YnRraHR5Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTE0Mjc1NTUsImV4cCI6MjAyNzAwMzU1NX0.pB3_cZd7RnOhP2NZunimeOb4skhUwi5vUOLKzbsAcQM"
        ) {
            install(Auth)
        }
    }

    @Provides
    @Singleton
    fun provideRepository(
        client: SupabaseClient
    ): Repository {
        return RepostoryImpl(client)
    }

}
