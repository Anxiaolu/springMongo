/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.entity;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    private String Match;
    @DBRef
    private List<Odds> Odds;

    public Data() {
    }

    public Data(String _id, String Company, String League, String Year, String Match, List<Odds> odds) {
        this._id = _id;
        this.Company = Company;
        this.League = League;
        this.Year = Year;
        this.Match = Match;
        this.Odds = odds;
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

    public List<Odds> getOdds() {
        return Odds;
    }

    public void setOdds(List<Odds> odds) {
        this.Odds = odds;
    }

    public String getMatch() {
        return Match;
    }

    public void setMatch(String Match) {
        this.Match = Match;
    }
    
//    public String oddsToString(Odds[] oddses){
//        String str = null;
//        for (Odds oddse : oddses) {
//            str += oddse.toString();
//        }
//        return str;
//    }

    @Override
    public String toString() {
        return "Data{" + "_id=" + _id + ", Company=" + Company + ", League=" + League + ", Year=" + Year + ", Match=" + Match + ", odds=" + Odds + '}';
    }

}
