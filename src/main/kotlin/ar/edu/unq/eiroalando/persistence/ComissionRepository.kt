package ar.edu.unq.eiroalando.persistence

import ar.edu.unq.eiroalando.model.Commision
import org.springframework.data.repository.CrudRepository


interface ComissionRepository :CrudRepository<Commision,Long> {

    fun findByName(name: String): Commision

    fun findBySubjectId(subjectId: Long): Commision
}