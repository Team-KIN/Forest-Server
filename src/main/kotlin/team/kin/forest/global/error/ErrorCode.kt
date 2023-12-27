package team.kin.forest.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // USER
    DUPLICATE_USER_EMAIL("중복된 이메일 입니다.", 409),
    USER_NOT_FOUND("계정을 찾을 수 없습니다.", 404),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", 400),

    // AUTHENTICATION
    AUTHENTICATION_NOT_FOUND("인증되지 않은 사용자 입니다.", 404),
    TOO_MANY_AUTHENTICATION_CODE_REQUEST("인증 메세지 요청 5번 초과 한 사용자 입니다.", 429),

    // GROUP
    NOT_GROUP_MEMBER("그룹 멤버가 아닙니다.", 403),
    GROUP_NOT_FOUND("그룹을 찾을 수 없습니다.", 404),
    PRIVATE_GROUP("공개되지 않은 그룹입니다.",409),
    ALREADY_JOIN_GROUP("이미 가입되어 있는 그룹입니다.", 409),
    MEMBER_NOT_FOUND("존재하지 않는 멤버 입니다.", 404),
    NOT_GROUP_MANAGER("그룹 매니저가 아닙니다.", 403),

    // Post
    FORBIDDEN_POST("게시글에 대한 권한이 없습니다.", 403),
    POST_NOT_FOUND("게시글을 찾을 수 없습니다.", 404),

    // TOKEN
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 401),
    INVALID_TOKEN_TYPE("유효하지 않은 토큰 타입 입니다.", 401),
    EXPIRED_ACCESS_TOKEN("만료된 accessToken 입니다.", 401),
    EXPIRED_REFRESH_TOKEN("만료된 refreshToken 입니다.", 401),

    // MESSAGE
    MESSAGE_SEND_FAILED("coolsms 메세지 전송에 실패하였습니다.", 500),

    // AUTH CODE
    AUTH_CODE_NOT_FOUND("인증 코드를 찾을 수 없습니다.", 404),
    AUTH_CODE_NOT_MATCH("인증 코드가 일치 하지 않습니다.", 400),
    TOO_MANY_AUTH_CODE_REQUEST("인증 코드 확인 요청을 5번 초과한 사용자 입니다.", 429)

}