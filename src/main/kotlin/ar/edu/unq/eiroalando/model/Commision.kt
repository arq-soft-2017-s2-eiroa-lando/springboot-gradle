package ar.edu.unq.eiroalando.model

import javax.persistence.Entity

@Entity
class Commision(var name:String, var year:Int, var semester: Int, var subject: Subject) {
}