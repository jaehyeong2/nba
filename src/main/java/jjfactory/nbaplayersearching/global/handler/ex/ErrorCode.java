package jjfactory.nbaplayersearching.global.handler.ex;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "알 수 없는 에러 (서버 에러)"),
    INVALID_TYPE_VALUE(400, "타입이 올바르지 않습니다."),
    INVALID_PHONE_LENGTH(400, "번호 길이기 올바르지 않습니다."),
    NOT_FOUND_USER(500,  "존재하지 않는 회원입니다.."),
    WITH_DRAW_USER(500,  "접근할 수 없는 아이디입니다"),
    INVALID_TEAm_ID(500,  "같은 팀이 입력되었습니다."),

    NOT_FOUND(500,  "조회에 실패하였습니다"),
    COMMENT_HANDLE_DENIED(500,  "본인 댓글에는 좋아요/싫어요를 할 수 없습니다"),
    HANDLE_ACCESS_DENIED(403,  "권한이 없습니다."),
    HANDLE_INVALID_TOKEN(401,  "토큰이 없거나 올바르지 않습니다."),
    NOT_MATCH_PASSWORD(500,"아이디와 비밀번호를 확인 해 주세요."),
    DUPLICATE_LOGIN_ID(500,"이미 존재하는 ID입니다")
    ;


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
