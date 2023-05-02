package org.kranj.vtrci.service
import org.kranj.vtrci.model.Producer
import org.kranj.vtrci.repository.ProducerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class ProducerService(val repository: ProducerRepository) {
    fun getAll():List<Producer> = repository.findAll()

    fun getById(id: UUID): Producer = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(producer: Producer): Producer = repository.save(producer)

    fun remove(id: UUID) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id:UUID, producer: Producer): Producer {
        return if (repository.existsById(id)) {
            producer.id = id
            repository.save(producer)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}