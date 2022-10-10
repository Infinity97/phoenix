package com.pheonix.user.management.utils.constants.rest;

public interface RestContants {
    
    String ADMIN = "/admin";
    String MOBILE = "/mobile";

    interface LOGIN {
        String LOGIN = "/login";
        String REGISTER_NEW_USER = "/registerNewUser";
        String FORGOT_PASSWORD = "/forgotPassword";
        String UPDATE_USER_DETAILS = "/updateUserDetails";
        String LOGIN_OR_SIGN_UP = "/loginOrSignUp";
    }

    interface PRODUCT {
        String PRODUCT_CONTROLLER = "/product";
        String ADD_PRODUCT = "/addProduct";
        String UPDATE_PRODUCT = "/updateProduct";
        String DELETE_PRODUCT = "/deleteProduct";
        String GET_PRODUCT = "/getProduct";
        String CATEGORY_CONTROLLER = "/category";
    }

    interface COMPANY{
        String COMPANY_CONTROLLER = "/company";
        String ADD_COMPANY = "/addCompany";
        String UPDATE_COMPANY = "/updateCompany";
        String GET_COMPANY_DETAILS = "/getCompanyDetails";
        String GET_COMPANIES_BY_CATEGORY = "/getCompaniesByCategory";
    }
}
