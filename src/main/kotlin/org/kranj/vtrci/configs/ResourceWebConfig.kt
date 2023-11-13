package org.kranj.vtrci.configs

import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class ResourceWebConfig(val environment: Environment) : WebMvcConfigurer {
//    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
//        val location = environment.getProperty("app.file.storage.mapping")
//        registry.addResourceHandler("/uploads/**").addResourceLocations(location)
//    }
}