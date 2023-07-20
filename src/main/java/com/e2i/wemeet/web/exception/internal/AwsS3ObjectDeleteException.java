package com.e2i.wemeet.web.exception.internal;

public class AwsS3ObjectDeleteException extends InternalServerException {
    private static final String MESSAGE = "AWS S3 Object 삭제 중 오류가 발생했습니다.";

    public AwsS3ObjectDeleteException() {
        super(MESSAGE);
    }

}
