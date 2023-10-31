package org.bma.simulator.datamodel.userprofile;

public abstract class UserProfile {
  protected String type;
  protected Double credibility;
  protected Double scepticism;

  protected UserProfile(String type, Double credibility, Double scepticism) {
    this.type = type;
    this.credibility = credibility;
    this.scepticism = scepticism;
  }

  public String getType() {
    return type;
  }

  public Object[][] getData() {
    return new Object[][]{
        {"type", this.type},
        {"credibility", this.credibility},
        {"scepticism", this.scepticism}
    };
  }

  public double getCredibility() {
    return credibility;
  }

  public double getScepticism() {
    return scepticism;
  }

  public void setCredibility(Double credibility) {
    this.credibility = credibility;
  }

  public void setScepticism(Double scepticism) {
    this.scepticism = scepticism;
  }
}

