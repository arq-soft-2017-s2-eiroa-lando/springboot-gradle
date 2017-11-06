package ar.edu.unq.eiroalando.model

import javax.persistence.Entity

@Entity
class SurveyAnswer(var question:List<Question>, var year:Int, var semester:Int) {
}