package com.pheonix.user.management.utils.constants.rest;

public interface RestContants {
    
    String ADMIN = "/admin";
    String MOBILE = "/mobile";
    String SESSION_ID ="sessionId";
    String AUTH_USERNAME = "authUsername";
    String AUTH_PASSWORD = "authPassword";

    interface SESSION{
        String SESSION = "/session";
    }

    interface LOGIN {
        String LOGIN = "/login";
        String REGISTER_NEW_USER = "/registerNewUser";
        String FORGOT_PASSWORD = "/forgotPassword";
        String LOGIN_OR_SIGN_UP = "/loginOrSignUp";
        String AUTH = "/auth";
        String VALIDATE_SESSION = "/validateSession";
        String CREATE_SESSION = "/createSession";
    }

    interface USER {
        String USER = "/user";
        String ADD_ADDRESS_DETAILS = "/addAddressDetails";
        String UPDATE = "/update";
        String GET = "/get";

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
