package com.e2i.wemeet.web.exception.internal;

public class AwsS3ObjectDeleteException extends InternalServerException {
    private static final String MESSAGE = "이미지 저장소에서 이미지 삭제 중 오류가 발생했어..";

    public AwsS3ObjectDeleteException() {
        super(MESSAGE);
    }

}
