package ar.edu.unq.eiroalando.model

class Question(var text:String, var options: List<QuestionOption>, var subject: Subject, var answered: Boolean = false) {
}