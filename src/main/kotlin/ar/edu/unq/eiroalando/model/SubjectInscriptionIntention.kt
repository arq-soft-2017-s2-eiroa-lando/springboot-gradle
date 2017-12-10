package ar.edu.unq.eiroalando.model

import javax.persistence.*

@Entity
class SubjectInscriptionIntention (var option:SubjectIntentionOption
                                   ){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @OneToOne(cascade = arrayOf(CascadeType.MERGE))
    var subject:Subject? = null

    @OneToOne(cascade = arrayOf(CascadeType.MERGE))
    var commision: Commision? =null

    @OneToOne(cascade = arrayOf(CascadeType.MERGE))
    val student: User? = null


}