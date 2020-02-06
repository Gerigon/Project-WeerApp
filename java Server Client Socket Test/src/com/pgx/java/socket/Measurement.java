package com.pgx.java.socket;

public class Measurement 
{
	private int STN;
	private String DATE;
	private String TIME;
	private Float TEMP;
	private Float DEWP;
	private Float STP;
	private Float SLP;
	private Float VISIB;
	private Float WDSP;
	private Float PRCP;
	private Float SNDP;
	private String FRSHTT;
	private Float CLDC;
	private Integer WNDDIR;
	
	public int getSTN() {
		return STN;
	}
	public void setSTN(int sTN) {
		STN = sTN;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public Float getTEMP() {
		return TEMP;
	}
	public void setTEMP(Float tEMP) {
		TEMP = tEMP;
	}
	public Float getDEWP() {
		return DEWP;
	}
	public void setDEWP(Float dEWP) {
		DEWP = dEWP;
	}
	public Float getSTP() {
		return STP;
	}
	public void setSTP(Float sTP) {
		STP = sTP;
	}
	public Float getSLP() {
		return SLP;
	}
	public void setSLP(Float sLP) {
		SLP = sLP;
	}
	public Float getVISIB() {
		return VISIB;
	}
	public void setVISIB(Float vISIB) {
		VISIB = vISIB;
	}
	public Float getWDSP() {
		return WDSP;
	}
	public void setWDSP(Float wDSP) {
		WDSP = wDSP;
	}
	public Float getPRCP() {
		return PRCP;
	}
	public void setPRCP(Float pRCP) {
		PRCP = pRCP;
	}
	public Float getSNDP() {
		return SNDP;
	}
	public void setSNDP(Float sNDP) {
		SNDP = sNDP;
	}
	public String getFRSHTT() {
		return FRSHTT;
	}
	public void setFRSHTT(String fRSHTT) {
		FRSHTT = fRSHTT;
	}
	public Float getCLDC() {
		return CLDC;
	}
	public void setCLDC(Float cLDC) {
		CLDC = cLDC;
	}
	public Integer getWNDDIR() {
		return WNDDIR;
	}
	public void setWNDDIR(Integer wNDDIR) {
		WNDDIR = wNDDIR;
	}
	
	@Override
    public String toString() {
        return this.STN + "," + this.DATE +  "," +this.TIME +  "," + 
        this.TEMP +  "," + this.DEWP +  "," + this.STP +  "," + this.SLP +  "," + 
        this.VISIB +  "," + this.WDSP +  "," + this.PRCP +  "," + this.SNDP +  "," + 
        this.FRSHTT +  "," + this.CLDC +  "," + this.WNDDIR;
    }
}
