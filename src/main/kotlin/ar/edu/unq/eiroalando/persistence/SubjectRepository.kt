package ar.edu.unq.eiroalando.persistence

import ar.edu.unq.eiroalando.model.Subject
import org.springframework.data.repository.CrudRepository

interface SubjectRepository: CrudRepository<Subject, Long> {
     fun findByName(name: String): List<Subject>
}