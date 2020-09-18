package com.company.sokolov.constants;

public final class CommonAppConstants {

    public static final String REGISTRATION_VIEW = "registration";
    public static final String HOME_VIEW = "home";

    public static final String REDIRECT_LOGIN = "redirect:/login";
    public static final String REDIRECT_HOME = "redirect:/home";
    public static final String ERRORS_VIEW_ATTRIBUTE = "errors";
    public static final String ERROR_VIEW_ATTRIBUTE = "error";
    public static final String MESSAGE_VIEW_ATTRIBUTE = "message";

    public static final String UPLOAD_DIRECTORY = "uploads/";
    public static final String UPLOAD_DIRECTORY_PATH = "D:\\0_JAVA\\SpringProjects\\SimpleSecurity\\uploads";



    //    user@domain.com :       true
//    user@domain.co.in :     true
//    user1@domain.com :      true
//    user.name@domain.com :  true
//    user#@domain.co.in :    true
//    user@domaincom :        true
//
//    user#domain.com :       false
//    @yahoo.com :            false
    public static final String EMAIL_PATTERN = "^(.+)@(.+)$";

    //    ^                 # start-of-string
//    (?=.*[0-9])       # a digit must occur at least once
//    (?=.*[a-z])       # a lower case letter must occur at least once
//    (?=.*[A-Z])       # an upper case letter must occur at least once
//    (?=.*[@#$%^&+=])  # a special character must occur at least once
//    (?=\S+$)          # no whitespace allowed in the entire string
//    .{8,}             # anything, at least eight places though
//    $                 # end-of-string
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    // +375291234567
    public static final String PHONE_NUMBER_PATTERN = "^\\+\\d{12}$";

    private CommonAppConstants() {

    }
}
