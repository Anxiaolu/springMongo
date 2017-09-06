/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.entity;


/**
 *
 * @author huanlu
 */
public class Odds {

    private Double win;
    private Double draw;
    private Double lose;
    private String returnRate;
    private String updateTime;

    public Odds() {
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

    public String getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(String returnRate) {
        this.returnRate = returnRate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Odds{" + "win=" + win + ", draw=" + draw + ", lose=" + lose + ", returnRate=" + returnRate + ", updateTime=" + updateTime + '}';
    }
}
