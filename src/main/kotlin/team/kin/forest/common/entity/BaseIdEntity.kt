package team.kin.forest.common.entity

import javax.persistence.*

@MappedSuperclass
abstract class BaseIdEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    open val id: Long = 0
) : BaseTimeEntity()