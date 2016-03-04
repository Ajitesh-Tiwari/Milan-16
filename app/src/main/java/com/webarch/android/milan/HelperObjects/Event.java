package com.webarch.android.milan.HelperObjects;

import java.io.Serializable;

/**
 * Created by ajitesh on 12/2/16.
 */
public class Event implements Serializable {
    String name=null, domain=null, intro=null, notes[]=null, rules[]=null, coordinators[]=null;
    public Event(String name,String domain){
        this.name=name;
        this.domain=domain;
    }
    public void setIntro(String intro)
    {
        this.intro=intro;
    }
    public  void setNotes(String notes[]){
        this.notes=notes.clone();
    }
    public void setRules(String rules[]){
        this.rules=rules.clone();
    }
    public void setCoordinators(String coordinators[])
    {
        this.coordinators=coordinators.clone();
    }
    public String getName()
    {
        return name;
    }
    public String getDomain()
    {
        return domain;
    }
    public String getIntro()
    {
        return intro;
    }

    public String[] getNotes() {
        return notes;
    }

    public String[] getRules() {
        return rules;
    }

    public String[] getCoordinators()
    {
        return coordinators;
    }
}