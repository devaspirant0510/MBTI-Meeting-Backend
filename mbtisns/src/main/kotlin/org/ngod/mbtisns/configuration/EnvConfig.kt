package org.ngod.mbtisns.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class EnvConfig(val env: Environment) {
}