package com.dah.utility;

public enum DAH_STATE {
    LOGIN("LoginView.fxml"),
    REGISITER("RegisterView.fxml"),
    DASHBOARD("DashboardView.fxml"),                              // from this state forward the user is logged in
    EDIT_PROFILE("EditProfileView.fxml"),
    ADD_A_POST("AddAPostView.fxml"),
    RETRIEVE_A_POST("RetrivePostView.fxml"),
    REMOVE_A_POST("RemovePostView.fxml");
    // TOP_N_LIKES,
    // SAVE_POST,
    // LOGOUT("LogoutView.fxml");                                           // not actually a state

    
    private final String FILE_NAME;

    private static final String BASE_PATH = "view/";

    DAH_STATE(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getFileName() {
        return BASE_PATH + FILE_NAME;
    }

}
