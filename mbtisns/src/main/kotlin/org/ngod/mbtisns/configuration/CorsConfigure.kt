package org.ngod.mbtisns.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfigure : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")  // 모든 경로에 대해 CORS 허용
            .allowedOrigins("http://localhost:3000","http://localhost:50573")  // 허용할 도메인
            .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH")  // 허용할 HTTP 메서드
            .allowedHeaders("*")  // 허용할 헤더
            .allowCredentials(true)  // 쿠키 및 인증 정보 허용
            .maxAge(3600);  // 캐시 시간(초)    }

    }
}