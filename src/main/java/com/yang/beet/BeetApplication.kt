package com.yang.beet

import org.beetl.core.resource.WebAppResourceLoader
import org.beetl.ext.spring.BeetlGroupUtilConfiguration
import org.beetl.ext.spring.BeetlSpringViewResolver
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.core.io.support.ResourcePatternUtils

import java.io.IOException
import java.util.ArrayList

@SpringBootApplication
class BeetApplication {

    // WebAppResourceLoader 配置root路径是关键
    //读取配置文件信息
    val beetlGroupUtilConfiguration: BeetlGroupUtilConfiguration
        @Bean(initMethod = "init", name = arrayOf("beetlConfig"))
        get() {
            val beetlGroupUtilConfiguration = BeetlGroupUtilConfiguration()
            val patternResolver = ResourcePatternUtils.getResourcePatternResolver(DefaultResourceLoader())
            try {
                val webAppResourceLoader = WebAppResourceLoader(patternResolver.getResource("classpath:/").file.path)
                beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return beetlGroupUtilConfiguration
        }

    @Bean(name = arrayOf("beetlViewResolver"))
    fun getBeetlSpringViewResolver(@Qualifier("beetlConfig") beetlGroupUtilConfiguration: BeetlGroupUtilConfiguration): BeetlSpringViewResolver {
        val beetlSpringViewResolver = BeetlSpringViewResolver()
        beetlSpringViewResolver.setPrefix("WEB-INF/views/")
        beetlSpringViewResolver.setSuffix(".html")
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8")
        beetlSpringViewResolver.order = 0
        beetlSpringViewResolver.config = beetlGroupUtilConfiguration
        return beetlSpringViewResolver
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(BeetApplication::class.java, *args)
        }
    }


}
