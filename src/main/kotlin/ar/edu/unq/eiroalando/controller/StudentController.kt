package ar.edu.unq.eiroalando.controller

import ar.edu.unq.eiroalando.model.Student
import ar.edu.unq.eiroalando.model.Subject
import ar.edu.unq.eiroalando.service.StudentService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.beans.factory.annotation.Autowired



@RestController
@RequestMapping("/students")
class StudentController {

    @Autowired
    lateinit var studService: StudentService

    @GetMapping(value = "/{fileNumber}")
    fun getStudentByFileNumber(@PathVariable fileNumber: Int?): Student {
        return studService!!.getStudentByFileNumber(1)
    }

    @PostMapping(value = "/subscribe/{fileNumber}", consumes = arrayOf("application/json"))
    fun subscribe(@RequestBody subject: Subject, @PathVariable fileNumber: Int?) {
        val st = studService!!.getStudentByFileNumber(fileNumber!!)
        st.subjects?.single { s -> s.name.equals(subject.name) }?.subscribed = true
        studService!!.save(st)
    }

    @PostMapping(value = "/unsubscribe/{fileNumber}", consumes = arrayOf("application/json"))
    fun unsubscribe(@RequestBody subject: Subject, @PathVariable fileNumber: Int?) {
        val st = studService!!.getStudentByFileNumber(fileNumber!!)
        st.subjects?.single{ s -> s.name.equals(subject.name)}?.subscribed = false
        studService!!.save(st)
    }

}