package team.kin.forest.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // ACCOUNT
    DUPLICATE_ACCOUNT_EMAIL("중복된 이메일 입니다.", 409),

    // AUTHENTICATION
    AUTHENTICATION_NOT_FOUND("인증되지 않은 사용자 입니다.", 404),

    // GROUP
    GROUP_NOT_FOUND("그룹을 찾을 수 없습니다.", 404)

}