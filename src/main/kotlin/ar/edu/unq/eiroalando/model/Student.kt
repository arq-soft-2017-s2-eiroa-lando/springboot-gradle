package ar.edu.unq.eiroalando.model

import java.util.TreeSet
import javax.persistence.*


@Entity
class Student(var name:String,var studentFileNumber: Int ) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @OneToMany(cascade = arrayOf(CascadeType.MERGE))
    var subjects: MutableSet<Subject> = TreeSet<Subject>()
}

