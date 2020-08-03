package com.licheedev.serialtool.Actual;

public class Usercolor {
    private String Ten;
    private boolean isChecked;

    public Usercolor(String ten, boolean isChecked) {
        Ten = ten;
        this.isChecked = isChecked;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
