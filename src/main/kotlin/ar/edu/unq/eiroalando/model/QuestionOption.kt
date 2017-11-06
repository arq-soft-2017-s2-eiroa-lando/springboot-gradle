package ar.edu.unq.eiroalando.model


enum class QuestionOption(var description: String, var commision: Commision? = null) {
    ALREADYAPPROVED("Ya aprobe"),
    WONTDOIT("No voy a cursar"),
    WOULDLIKETO("Me gustaría cursar pero no puedo en ese horario"),
    WILLASSIST("Voy a cursar")

}