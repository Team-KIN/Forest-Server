package team.kin.forest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ForestServerApplication

fun main(args: Array<String>) {
    runApplication<ForestServerApplication>(*args)
}
