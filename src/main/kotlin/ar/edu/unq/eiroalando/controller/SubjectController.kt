package ar.edu.unq.eiroalando.controller

import ar.edu.unq.eiroalando.model.Subject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import ar.edu.unq.eiroalando.persistence.SubjectRepository

@RestController
@RequestMapping("/subjects")
class SubjectController {

    @Autowired
    lateinit var repo: SubjectRepository

    @RequestMapping(produces = arrayOf("application/json"))
    @ResponseBody
    fun findAll(): Iterable<Subject> {
        return repo.findAll()
    }

    @RequestMapping(value = "/example", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    @ResponseBody
    fun example(): Subject {
        return Subject("orga")
    }
}
