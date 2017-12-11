package ar.edu.unq.eiroalando.controller

import ar.edu.unq.eiroalando.model.Subject
import ar.edu.unq.eiroalando.model.User
import ar.edu.unq.eiroalando.service.UserService
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
    lateinit var studService: UserService

    @GetMapping(value = "/{fileNumber}")
    fun getStudentByFileNumber(@PathVariable fileNumber: Int): User {
        return studService!!.getStudentByFileNumber(fileNumber)
    }

    @GetMapping(value = "/all")
    fun getAllStudents(): List<User>? {
        return studService.findStudents()?.toList()
    }

    @PostMapping(value = "/subscribe/{fileNumber}", consumes = arrayOf("application/json"))
    fun subscribe(@RequestBody subject: Subject, @PathVariable fileNumber: Int?) {
        val st = studService!!.getStudentByFileNumber(fileNumber!!)
        studService!!.save(st)
    }

    @PostMapping(value = "/add", consumes = arrayOf("application/json"))
    fun register(@RequestBody student: User) {
        studService.save(student)
    }

    @PostMapping(value = "/update", consumes = arrayOf("application/json"))
    fun update(@RequestBody student: User) {
        studService.update(student)
    }

    @PostMapping(value = "/unsubscribe/{fileNumber}", consumes = arrayOf("application/json"))
    fun unsubscribe(@RequestBody subject: Subject, @PathVariable fileNumber: Int?) {
        val st = studService!!.getStudentByFileNumber(fileNumber!!)
        studService!!.save(st)
    }

}