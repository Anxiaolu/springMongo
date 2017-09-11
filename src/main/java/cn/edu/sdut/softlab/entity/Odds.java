/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author huanlu
 */
public class Odds {

    private String win;
    private String draw;
    private String lose;
    private Double returnrate;
    private Date updatetime;

    public Odds() {
    }
    
    public Odds(String _win,String _draw,String _lose,Double _returnrate,Date _updatetime){
        this.win = _win;
        this.draw = _draw;
        this.lose = _lose;
        this.returnrate = _returnrate;
        this.updatetime = _updatetime;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public Double getReturnrate() {
        return returnrate;
    }

    public void setReturnrate(Double returnrate) {
        this.returnrate = returnrate;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Odds{" + "win=" + win + ", draw=" + draw + ", lose=" + lose + ", returnRate=" + returnrate + ", updateTime=" + updatetime + '}';
    }

//    @Override
//    public int hashCode() {
//        int hash = 7666;
//        hash = 17666 * hash + Objects.hashCode(this.returnrate);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Odds other = (Odds) obj;
//        return Objects.equals(this.returnrate, other.returnrate);
//    }
}