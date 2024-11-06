package org.ngod.mbtisns.configuration

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.Storage
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfiguration  {
    @Value("\${SUPABASE_URL}")
    private lateinit var supabaseUrl:String
    @Value("\${SUPABASE_KEY}")
    private lateinit var supabaseKey:String
    @Bean
    fun getSupabaseClient(): SupabaseClient {
        println("sup $supabaseUrl $supabaseKey")
        val supabase  = createSupabaseClient(supabaseUrl, supabaseKey = supabaseKey){
            print("sup ")
            install(Auth)
            install(Storage)
        }
        println("sup $supabase")
        return  supabase

    }
}