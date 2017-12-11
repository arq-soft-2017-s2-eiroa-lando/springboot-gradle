package ar.edu.unq.eiroalando.persistence

import ar.edu.unq.eiroalando.model.Subject
import ar.edu.unq.eiroalando.model.User
import org.springframework.data.repository.CrudRepository

interface SubjectRepository: CrudRepository<Subject, Long> {
     fun findById(id: Long): Subject
     fun findByName(name: String): List<Subject>
}