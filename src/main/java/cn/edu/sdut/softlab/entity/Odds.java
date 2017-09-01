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
    
    private Double Win;
    private Double Draw;
    private Double Lose;
    private Double ReturnRate;
    private Date   UpdateTime;

    public Odds() {
    }

    public Odds(Double Win, Double Draw, Double Lose, Double ReturnRate, Date UpdateTime) {
        this.Win = Win;
        this.Draw = Draw;
        this.Lose = Lose;
        this.ReturnRate = ReturnRate;
        this.UpdateTime = UpdateTime;
    }

    public Double getWin() {
        return Win;
    }

    public void setWin(Double Win) {
        this.Win = Win;
    }

    public Double getDraw() {
        return Draw;
    }

    public void setDraw(Double Draw) {
        this.Draw = Draw;
    }

    public Double getLose() {
        return Lose;
    }

    public void setLose(Double Lose) {
        this.Lose = Lose;
    }

    public Double getReturnRate() {
        return ReturnRate;
    }

    public void setReturnRate(Double ReturnRate) {
        this.ReturnRate = ReturnRate;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    @Override
    public String toString() {
        return "Odds{" + "Win=" + Win + ", Draw=" + Draw + ", Lose=" + Lose + ", ReturnRate=" + ReturnRate + ", UpdateTime=" + UpdateTime + '}';
    }
    
}
