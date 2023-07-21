package com.e2i.wemeet.web.exception.internal;

public class AwsS3ObjectUploadException extends InternalServerException {
    private static final String MESSAGE = "이미지 저장소에 이미지를 올리는 중에 문제가 발생했어..";

    public AwsS3ObjectUploadException() {
        super(MESSAGE);
    }
}
