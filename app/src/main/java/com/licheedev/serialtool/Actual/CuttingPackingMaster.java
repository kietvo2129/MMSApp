package com.licheedev.serialtool.Actual;

public class CuttingPackingMaster {
    String rowNum,mt_sts_cd1,orgin_mt_cd,reg_id,reg_dt,chg_id,chg_dt,mt_cd,bb_no,remark,gr_qty,wmtid
    ,mt_no,date,lct_cd,mt_type,gr_qty1,mt_nm,mt_cd_mt,unit_cd,gr_qty_mt,del_yn,to_lct_cd
    ,from_lct_cd,type_name,status,location_status,lct_nm;
    boolean checked;

    public CuttingPackingMaster(String rowNum, String mt_sts_cd1, String orgin_mt_cd, String reg_id, String reg_dt, String chg_id, String chg_dt,
                                String mt_cd, String bb_no, String remark, String gr_qty, String wmtid, String mt_no, String date, String lct_cd,
                                String mt_type, String gr_qty1, String mt_nm, String mt_cd_mt, String unit_cd, String gr_qty_mt, String del_yn,
                                String to_lct_cd, String from_lct_cd, String type_name, String status, String location_status, String lct_nm, Boolean b) {
        this.rowNum = rowNum;
        this.mt_sts_cd1 = mt_sts_cd1;
        this.orgin_mt_cd = orgin_mt_cd;
        this.reg_id = reg_id;
        this.reg_dt = reg_dt;
        this.chg_id = chg_id;
        this.chg_dt = chg_dt;
        this.mt_cd = mt_cd;
        this.bb_no = bb_no;
        this.remark = remark;
        this.gr_qty = gr_qty;
        this.wmtid = wmtid;
        this.mt_no = mt_no;
        this.date = date;
        this.lct_cd = lct_cd;
        this.mt_type = mt_type;
        this.gr_qty1 = gr_qty1;
        this.mt_nm = mt_nm;
        this.mt_cd_mt = mt_cd_mt;
        this.unit_cd = unit_cd;
        this.gr_qty_mt = gr_qty_mt;
        this.del_yn = del_yn;
        this.to_lct_cd = to_lct_cd;
        this.from_lct_cd = from_lct_cd;
        this.type_name = type_name;
        this.status = status;
        this.location_status = location_status;
        this.lct_nm = lct_nm;
        checked = b;
    }

    public String getRowNum() {
        return rowNum;
    }
    public boolean isChecked(){
        return checked;
    }
    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getMt_sts_cd1() {
        return mt_sts_cd1;
    }

    public void setMt_sts_cd1(String mt_sts_cd1) {
        this.mt_sts_cd1 = mt_sts_cd1;
    }

    public String getOrgin_mt_cd() {
        return orgin_mt_cd;
    }

    public void setOrgin_mt_cd(String orgin_mt_cd) {
        this.orgin_mt_cd = orgin_mt_cd;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getReg_dt() {
        return reg_dt;
    }

    public void setReg_dt(String reg_dt) {
        this.reg_dt = reg_dt;
    }

    public String getChg_id() {
        return chg_id;
    }

    public void setChg_id(String chg_id) {
        this.chg_id = chg_id;
    }

    public String getChg_dt() {
        return chg_dt;
    }

    public void setChg_dt(String chg_dt) {
        this.chg_dt = chg_dt;
    }

    public String getMt_cd() {
        return mt_cd;
    }

    public void setMt_cd(String mt_cd) {
        this.mt_cd = mt_cd;
    }

    public String getBb_no() {
        return bb_no;
    }

    public void setBb_no(String bb_no) {
        this.bb_no = bb_no;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGr_qty() {
        return gr_qty;
    }

    public void setGr_qty(String gr_qty) {
        this.gr_qty = gr_qty;
    }

    public String getWmtid() {
        return wmtid;
    }

    public void setWmtid(String wmtid) {
        this.wmtid = wmtid;
    }

    public String getMt_no() {
        return mt_no;
    }

    public void setMt_no(String mt_no) {
        this.mt_no = mt_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLct_cd() {
        return lct_cd;
    }

    public void setLct_cd(String lct_cd) {
        this.lct_cd = lct_cd;
    }

    public String getMt_type() {
        return mt_type;
    }

    public void setMt_type(String mt_type) {
        this.mt_type = mt_type;
    }

    public String getGr_qty1() {
        return gr_qty1;
    }

    public void setGr_qty1(String gr_qty1) {
        this.gr_qty1 = gr_qty1;
    }

    public String getMt_nm() {
        return mt_nm;
    }

    public void setMt_nm(String mt_nm) {
        this.mt_nm = mt_nm;
    }

    public String getMt_cd_mt() {
        return mt_cd_mt;
    }

    public void setMt_cd_mt(String mt_cd_mt) {
        this.mt_cd_mt = mt_cd_mt;
    }

    public String getUnit_cd() {
        return unit_cd;
    }

    public void setUnit_cd(String unit_cd) {
        this.unit_cd = unit_cd;
    }

    public String getGr_qty_mt() {
        return gr_qty_mt;
    }

    public void setGr_qty_mt(String gr_qty_mt) {
        this.gr_qty_mt = gr_qty_mt;
    }

    public String getDel_yn() {
        return del_yn;
    }

    public void setDel_yn(String del_yn) {
        this.del_yn = del_yn;
    }

    public String getTo_lct_cd() {
        return to_lct_cd;
    }

    public void setTo_lct_cd(String to_lct_cd) {
        this.to_lct_cd = to_lct_cd;
    }

    public String getFrom_lct_cd() {
        return from_lct_cd;
    }

    public void setFrom_lct_cd(String from_lct_cd) {
        this.from_lct_cd = from_lct_cd;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation_status() {
        return location_status;
    }

    public void setLocation_status(String location_status) {
        this.location_status = location_status;
    }

    public String getLct_nm() {
        return lct_nm;
    }

    public void setLct_nm(String lct_nm) {
        this.lct_nm = lct_nm;
    }
}
