package com.developmethis.csguide.csquizmodule;

/**
 * Created by Faizan Ejaz on 1/22/2018.
 */

public class question {
    private int ID;
    private String QUIZ_ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String OPTD;
    private String ANSWER;
    public question()
    {
        ID=0;
        QUESTION="";
        OPTA="";
        OPTB="";
        OPTC="";
        OPTD="";
        ANSWER="";
        QUIZ_ID="";
    }
    public question(String qUIZ_ID, String qUESTION, String oPTA, String oPTB, String oPTC,
                    String oPTD,String aNSWER) {

        QUESTION = qUESTION;
        OPTA = oPTA;
        OPTB = oPTB;
        OPTC = oPTC;
        OPTD = oPTD;
        ANSWER = aNSWER;
        QUIZ_ID=qUIZ_ID;
    }
    public int getID()
    {
        return ID;
    }
    public String getQUIZ_ID(){return QUIZ_ID;}
    public String getQUESTION() {
        return QUESTION;
    }
    public String getOPTA() {
        return OPTA;
    }
    public String getOPTB() {
        return OPTB;
    }
    public String getOPTC() {
        return OPTC;
    }
    public String getOPTD() {
        return OPTD;
    }
    public String getANSWER() {
        return ANSWER;
    }
    public void setID(int id)
    {
        ID=id;
    }
    public void setQUIZ_ID(String qUIZ_ID){QUIZ_ID=qUIZ_ID;}
    public void setQUESTION(String qUESTION) {
        QUESTION = qUESTION;
    }
    public void setOPTA(String oPTA) {
        OPTA = oPTA;
    }
    public void setOPTB(String oPTB) {
        OPTB = oPTB;
    }
    public void setOPTC(String oPTC) {
        OPTC = oPTC;
    }
    public void setOPTD(String oPTD) {
        OPTC = oPTD;
    }
    public void setANSWER(String aNSWER) {
        ANSWER = aNSWER;
    }
}
