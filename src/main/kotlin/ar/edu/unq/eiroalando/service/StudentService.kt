package ar.edu.unq.eiroalando.service

import org.springframework.stereotype.Service
import ar.edu.unq.eiroalando.model.Student
import ar.edu.unq.eiroalando.persistence.StudentRepository
import org.springframework.beans.factory.annotation.Autowired



@Service
class StudentService {

    @Autowired
    lateinit var studRepo: StudentRepository


    fun getStudentByFileNumber(fileNumber: Int): Student {
        return studRepo!!.findByStudentFileNumber(fileNumber)
    }

    fun save(s: Student) {
        studRepo!!.save(s)
    }
}