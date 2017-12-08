package ar.edu.unq.eiroalando.model

import java.util.TreeSet
import javax.persistence.*


@Entity
class User(var name:String,
           var studentFileNumber: Int,
           var email:String,
           var isStudent:Boolean = false,
           var isProfessor:Boolean = false,
           var isDirector:Boolean = false
           ) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @OneToMany(cascade = arrayOf(CascadeType.MERGE))
    var subjects: MutableSet<Subject> = TreeSet<Subject>()

    companion object Factory {
        fun createStudent(name:String, studentFileNumber: Int):User{
            var student = User(name,studentFileNumber,"test@test.test")
            student.isStudent = true

            return student
        }
    }
}

