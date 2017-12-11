package ar.edu.unq.eiroalando.service

import org.springframework.stereotype.Service
import ar.edu.unq.eiroalando.model.User
import ar.edu.unq.eiroalando.persistence.CommissionRepository
import ar.edu.unq.eiroalando.persistence.UserRepository
import org.springframework.beans.factory.annotation.Autowired



@Service
class SurveyTokenService {

    @Autowired
    lateinit var userService: UserService

}