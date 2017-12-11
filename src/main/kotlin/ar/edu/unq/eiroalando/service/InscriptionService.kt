package ar.edu.unq.eiroalando.service

import org.springframework.stereotype.Service
import ar.edu.unq.eiroalando.model.User
import ar.edu.unq.eiroalando.persistence.InscriptionIntentionRepository
import ar.edu.unq.eiroalando.persistence.SubjectInscriptionIntentionRepository
import ar.edu.unq.eiroalando.persistence.UserRepository
import org.springframework.beans.factory.annotation.Autowired



@Service
class InscriptionService {

    @Autowired
    lateinit var inscriptionRepo: InscriptionIntentionRepository

    @Autowired
    lateinit var subjectInscriptionRepo: SubjectInscriptionIntentionRepository

}