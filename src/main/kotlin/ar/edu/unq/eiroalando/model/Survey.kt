package ar.edu.unq.eiroalando.model

import javax.persistence.Entity

@Entity
class Survey (var question:List<Question>, var year:Int, var semester:Int) {
}