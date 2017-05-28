package org.dididu

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class OmniscreenApplication {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(OmniscreenApplication::class.java, *args)
        }
    }
}
