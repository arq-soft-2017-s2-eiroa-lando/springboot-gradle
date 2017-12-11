package ar.edu.unq.eiroalando.service

import org.springframework.stereotype.Service
import ar.edu.unq.eiroalando.model.User
import ar.edu.unq.eiroalando.persistence.UserRepository
import org.springframework.beans.factory.annotation.Autowired



@Service
class SubjectService {

    @Autowired
    lateinit var studRepo: UserRepository


    fun getStudentByFileNumber(fileNumber: Int): User {
        return studRepo!!.findByStudentFileNumber(fileNumber)
    }

    fun save(s: User) {
        studRepo!!.save(s)
    }

    fun update(u: User){
        if(studRepo.findById(u.id!!)!= null){
            studRepo.save(u)
        }else{
            println("user not found")
        }

    }

    fun findStudents(): MutableIterable<User>? { return studRepo.findAll()}
}