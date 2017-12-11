package ar.edu.unq.eiroalando.persistence

import ar.edu.unq.eiroalando.model.SubjectInscriptionIntention
import org.springframework.data.repository.CrudRepository


interface SubjectInscriptionIntentionRepository :CrudRepository<SubjectInscriptionIntention,Long> {

    fun findById(id: Long): SubjectInscriptionIntention

}