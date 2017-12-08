package ar.edu.unq.eiroalando.model


enum class SubjectIntentionOption(var description: String, var commision: Commision? = null) {
    ALREADYAPPROVED("Ya aprobe"),
    WONTDOIT("No voy a cursar"),
    CANTASSIST("Me gustaría cursar pero no puedo en ese horario"),
    WILLASSIST("Voy a cursar" )

}