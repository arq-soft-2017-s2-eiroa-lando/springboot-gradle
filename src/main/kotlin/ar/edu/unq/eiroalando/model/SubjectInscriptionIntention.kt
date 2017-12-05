package ar.edu.unq.eiroalando.model

import javax.persistence.Entity

@Entity
class SubjectInscriptionIntention (var subject:Subject?,
                                   var commision: Commision,
                                   val student: User,
                                   var option:SubjectIntentionOption
                                   ){

}