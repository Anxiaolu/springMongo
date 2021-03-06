/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    private String company;
    private String league;
    private String year;
    private String match;

    private List<Odds> odds;

    public Data() {
    }

    public Data(String company, String league, String year, String match, List<Odds> odds) {
        this.company = company;
        this.league = league;
        this.year = year;
        this.match = match;
        this.odds = odds;
    }
    
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public List<Odds> getOdds() {
        if (odds.isEmpty()) {
            odds.add(new Odds("无","无","无",0.0,new Date()));
        }
        return odds;
    }

    public void setOdds(List<Odds> Odds) {
        this.odds = Odds;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this._id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Data other = (Data) obj;
        return Objects.equals(this._id, other._id);
    }

    @Override
    public String toString() {
        return "Data{" + "_id=" + _id + ", company=" + company + ", league=" + league + ", year=" + year + ", match=" + match +  '}';
    }

}
