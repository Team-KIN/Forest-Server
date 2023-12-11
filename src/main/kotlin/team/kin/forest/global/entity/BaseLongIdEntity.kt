package team.kin.forest.global.entity

import javax.persistence.*

@MappedSuperclass
abstract class BaseLongIdEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    open val id: Long = 0
) : BaseTimeEntity()