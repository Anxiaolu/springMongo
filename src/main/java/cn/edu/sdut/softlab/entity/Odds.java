/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.entity;

import java.util.Date;

/**
 *
 * @author huanlu
 */
public class Odds {
    
    private Double win;
    private Double draw;
    private Double lose;
    private Double returnrate;
    private Date   updatetime;

    public Odds() {
    }

    public Odds(Double win, Double draw, Double lose, Double returnrate, Date updatetime) {
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.returnrate = returnrate;
        this.updatetime = updatetime;
    }

    public Double getWin() {
        return win;
    }

    public void setWin(Double win) {
        this.win = win;
    }

    public Double getDraw() {
        return draw;
    }

    public void setDraw(Double draw) {
        this.draw = draw;
    }

    public Double getLose() {
        return lose;
    }

    public void setLose(Double lose) {
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
        return "Odds{" + "win=" + win + ", draw=" + draw + ", lose=" + lose + ", returnrate=" + returnrate + ", updatetime=" + updatetime + '}';
    }
    
}
