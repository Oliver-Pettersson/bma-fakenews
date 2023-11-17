package org.bma.simulator.datamodel.userprofile;

public abstract class UserProfile {
  protected String type;

  protected UserProfile(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public Object[][] getData() {
    return new Object[][]{
        {"type", this.type}
    };
  }
}

