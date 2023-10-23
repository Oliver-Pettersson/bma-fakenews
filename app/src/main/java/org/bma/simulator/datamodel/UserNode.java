package org.bma.simulator.datamodel;

public class UserNode {
  private final String id;
  private boolean isInfected;
  private String culpritId;
  private String infectionWave;
  private int amountOfFollowers;
  private int amountOfFollows;
  private double credibility;
  private double scepticism;

  public UserNode(String id) {
    this.id = id;
  }

  public Object[][] getData() {
    return new Object[][] {
        {"id", this.id},
        {"infected", this.isInfected},
        {"culprit_id", this.culpritId},
        {"wave", this.infectionWave},
        {"follower_count", this.amountOfFollowers},
        {"follow_count", this.amountOfFollows},
        {"credibility", this.credibility},
        {"scepticism", this.scepticism}
    };
  }

  public void setInfected(boolean infected) {
    isInfected = infected;
  }

  public void setCulpritId(String culpritId) {
    this.culpritId = culpritId;
  }

  public void setInfectionWave(String infectionWave) {
    this.infectionWave = infectionWave;
  }

  public void setAmountOfFollowers(int amountOfFollowers) {
    this.amountOfFollowers = amountOfFollowers;
  }

  public void setAmountOfFollows(int amountOfFollows) {
    this.amountOfFollows = amountOfFollows;
  }

  public void setCredibility(double credibility) {
    this.credibility = credibility;
  }

  public void setScepticism(double scepticism) {
    this.scepticism = scepticism;
  }
}
