package ar.edu.unq.eiroalando.model

import java.util.*
import javax.persistence.Entity

/*

Los alumnos generan intenciones de inscripcion, esta entidad representa la respuesta a un encuesta
 */
@Entity
class InscriptionIntention(var student:User,
                           var question:List<SubjectInscriptionIntention>,
                           var year:Int,
                           var semester:Int,
                           var lastTimeModified:Date) {
}