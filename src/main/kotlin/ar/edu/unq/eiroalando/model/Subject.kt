package ar.edu.unq.eiroalando.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Subject( var name: String ):Comparable<Subject> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    override operator fun compareTo(s: Subject): Int {
        return s.name.compareTo(this.name)
    }
}