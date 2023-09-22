package com.dah.utility;

public class GUIUtility {

    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_LENGTH = 800;

    public static enum DAH_STATE {
        LOG_IN,
        SIGN_UP,
        DASHBOARD,                              // from this state forward the user is logGed in
        EDIT_PROFILE,
        ADD_A_POST,
        RETRIEVE_A_POST,
        REMOVE_A_POST,
        TOP_N_LIKES,
        TOP_N_SHARES,
        SAVE_POST,
        LOG_OUT
    }

}
