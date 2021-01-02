package com.mardefasma.influaction_java.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private String displayId;

    public LoggedInUserView(String displayName, String displayId) {
        this.displayName = displayName;
        this.displayId = displayId;
    }

    public String getDisplayId() {
        return displayId;
    }
//... other data fields that may be accessible to the UI

    String getDisplayName() {
        return displayName;
    }
}