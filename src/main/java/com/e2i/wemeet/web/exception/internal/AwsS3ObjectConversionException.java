package com.e2i.wemeet.web.exception.internal;

public class AwsS3ObjectConversionException extends InternalServerException {
    private static final String MESSAGE = "AWS S3 Object 변환 중 오류가 발생했습니다.";

    public AwsS3ObjectConversionException() {
        super(MESSAGE);
    }
}
