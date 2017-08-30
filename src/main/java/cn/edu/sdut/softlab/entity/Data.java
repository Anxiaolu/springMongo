/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author huanlu
 */
@Document
public class Data {
    
    @Id
    private String _id;
    private String Company;
    private String League;
    private String Year;
    private Odds odds;

    public Data() {
    }

    public Data(String _id, String Company, String League, String Year, Odds odds) {
        this._id = _id;
        this.Company = Company;
        this.League = League;
        this.Year = Year;
        this.odds = odds;
    }
    
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getLeague() {
        return League;
    }

    public void setLeague(String League) {
        this.League = League;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    @Override
    public String toString() {
        return "Data{" + "_id=" + _id + ", Company=" + Company + ", League=" + League + ", Year=" + Year + ", odds=" + odds + '}';
    }

}
