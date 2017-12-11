package ar.edu.unq.eiroalando.persistence

import ar.edu.unq.eiroalando.model.InscriptionIntention
import org.springframework.data.repository.CrudRepository


interface InscriptionIntentionRepository :CrudRepository<InscriptionIntention,Long> {

    fun findById(id: Long): InscriptionIntention

}