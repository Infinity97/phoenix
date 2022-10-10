package com.pheonix.user.management.utils.constants.enums;

import lombok.Getter;

@Getter
public enum FileType {

    /**
     * File Type with default bucket name of -> aws.s3.bucket.default
     */
    LOGO, PRODUCT;

    private String bucketName;

    FileType() {}

    FileType(String bucketName) {
        bucketName = this.bucketName;
    }
}
