package com.zelyder.physics.help;

public interface CorrectListCallback {
    void liked(int position);
    void unLiked(int position);
    boolean getLikedState(int position);
    void crossToggle(int position, boolean isDelete);
    boolean getCrossState(int position);
}
