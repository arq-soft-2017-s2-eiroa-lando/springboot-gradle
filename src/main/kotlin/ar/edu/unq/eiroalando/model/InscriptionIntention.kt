package ar.edu.unq.eiroalando.model

import java.util.*
import javax.persistence.*

/*

Los alumnos generan intenciones de inscripcion, esta entidad representa la respuesta a un encuesta
 */
@Entity
class InscriptionIntention(var year:Int,
                           var semester:Int,
                           var lastTimeModified:Date) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne(cascade =  arrayOf(CascadeType.MERGE))
    var student:User? = null

    @OneToMany(cascade = arrayOf(CascadeType.MERGE))
    var subjectsInscriptionIntentions: MutableSet<SubjectInscriptionIntention> = TreeSet<SubjectInscriptionIntention>()
}