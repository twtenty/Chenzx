package com.example.uiinterfaceplus.useless;

import android.view.View;

public class FragmentState {
    private View main_page_view;
    private View guang_view;
    private View message_view;
    private View buycar_view;
    private View mine_view;

    public FragmentState() {
        this.main_page_view = null;
        this.guang_view = null;
        this.message_view = null;
        this.buycar_view = null;
        this.mine_view = null;
    }

    public View getMain_page_view() {
        return main_page_view;
    }

    public void setMain_page_view(View main_page_view) {
        this.main_page_view = main_page_view;
    }

    public View getGuang_view() {
        return guang_view;
    }

    public void setGuang_view(View guang_view) {
        this.guang_view = guang_view;
    }

    public View getMessage_view() {
        return message_view;
    }

    public void setMessage_view(View message_view) {
        this.message_view = message_view;
    }

    public View getBuycar_view() {
        return buycar_view;
    }

    public void setBuycar_view(View buycar_view) {
        this.buycar_view = buycar_view;
    }

    public View getMine_view() {
        return mine_view;
    }

    public void setMine_view(View mine_view) {
        this.mine_view = mine_view;
    }
}
