package ar.edu.unq.eiroalando.persistence

import ar.edu.unq.eiroalando.model.User
import org.springframework.data.repository.CrudRepository


interface UserRepository :CrudRepository<User,Long> {

    fun findByName(name: String): User

    fun findByStudentFileNumber(fileNumber: Int?): User

    fun findByIsStudent(isStudent:Boolean): List<User>
}