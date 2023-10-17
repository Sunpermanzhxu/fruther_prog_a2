package com.dah.utility;

public enum DAH_STATE {
    LOGIN("LoginView.fxml");
    // REGISITER,
    // DASHBOARD,                              // from this state forward the user is logged in
    // EDIT_PROFILE,
    // ADD_A_POST,
    // RETRIEVE_A_POST,
    // REMOVE_A_POST,
    // TOP_N_LIKES,
    // TOP_N_SHARES,
    // SAVE_POST,
    // LOG_OUT;

    
    private final String FILE_NAME;

    private static final String BASE_PATH = "view/";

    DAH_STATE(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getFileName() {
        return BASE_PATH + FILE_NAME;
    }

}
