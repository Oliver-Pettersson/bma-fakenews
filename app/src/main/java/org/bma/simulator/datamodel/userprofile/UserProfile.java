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

  public double getCredibility() {
    return credibility;
  }

  public double getScepticism() {
    return scepticism;
  }
}
