package org.kranj.vtrci

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class VtrciApplication

fun main(args: Array<String>) {
	runApplication<VtrciApplication>(*args)
}

@RestController
class MessageController {
	@GetMapping("/")
	fun index() = listOf(
		Message("1", "Hello!"),
		Message("2", "Bonjour!"),
		Message("3", "Privet!"),
	)
}

data class Message(val id: String?, val text: String)