package com.baeldung.um.spring

import com.baeldung.um.persistence.setup.MyApplicationContextInitializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.baeldung.um")
class UmApp : SpringBootServletInitializer() {

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder =
            application.sources(UmApp::class.java).initializers(MyApplicationContextInitializer())

}

fun main(args: Array<String>) {
    SpringApplicationBuilder(UmApp::class.java)
            .initializers(MyApplicationContextInitializer())
            .listeners()
            .run(*args)
}