package ar.edu.unq.eiroalando.model

import javax.persistence.*

@Entity
class Commission(var name: String,
                 var year: Int,
                 var semester: Int,
                 var classTime: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @OneToOne(cascade = arrayOf(CascadeType.MERGE))
    var subject: Subject? =null;

    @OneToMany(cascade = arrayOf(CascadeType.MERGE))
    var professors: List<User> = emptyList()
}