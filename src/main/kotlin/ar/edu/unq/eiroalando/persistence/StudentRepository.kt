package ar.edu.unq.eiroalando.persistence

import ar.edu.unq.eiroalando.model.Student
import org.springframework.data.repository.CrudRepository


interface StudentRepository:CrudRepository<Student,Long> {

    fun findByName(name: String): Student

    fun findByStudentFileNumber(fileNumber: Int?): Student
}