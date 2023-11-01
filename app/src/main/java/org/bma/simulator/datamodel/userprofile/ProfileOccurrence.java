package org.bma.simulator.datamodel.userprofile;

public class ProfileOccurrence {
  private UserProfile profile;
  private int occurrence;

  public ProfileOccurrence(UserProfile profile, int occurrence) {
    this.profile = profile;
    this.occurrence = occurrence;
  }

  public UserProfile getProfile() {
    return profile;
  }

  public void setOccurrence(int occurrence) {
    this.occurrence = occurrence;
  }

  public int getOccurrence() {
    return occurrence;
  }
}
