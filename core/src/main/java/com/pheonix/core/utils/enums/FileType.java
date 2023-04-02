package com.pheonix.core.utils.enums;

import com.pheonix.core.utils.constants.AppConstants;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum FileType {

    /**
     * File Type with default bucket name of -> aws.s3.bucket.default
     */
    LOGO_CATEGORY(AppConstants.CATEGORY),
    LOGO_BRAND(AppConstants.BRAND),
    LOGO_SUBSCRIPTIONS(AppConstants.SUBSCRIPTION),
    PRODUCT(AppConstants.PRODUCT),
    BILL(AppConstants.DEVICE),
    WARRANTY(AppConstants.DEVICE),
    DEVICE(AppConstants.DEVICE),
    HOME(AppConstants.GENERIC),
    SUBSCRIPTION_BILL(AppConstants.SUBSCRIPTION)
    ;

    private String tableMappedTo;

    FileType(String tableMappedTo){
        this.tableMappedTo = tableMappedTo;
    }

    List<FileType> getFileTypesMappedToTable(String tableMappedTo){
        return Arrays.stream(FileType.values()).filter(fileType -> tableMappedTo.equalsIgnoreCase(fileType.getTableMappedTo()))
          .collect(Collectors.toList());
    }
}
