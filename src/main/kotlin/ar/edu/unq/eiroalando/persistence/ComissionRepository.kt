package ar.edu.unq.eiroalando.persistence

import ar.edu.unq.eiroalando.model.Commission
import org.springframework.data.repository.CrudRepository


interface CommissionRepository :CrudRepository<Commission,Long> {

    fun findById(id: Long): Commission

    fun findByName(name: String): Commission

}