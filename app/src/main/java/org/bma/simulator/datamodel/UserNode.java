package org.bma.simulator.datamodel;

import java.util.Arrays;
import java.util.stream.Stream;
import org.bma.simulator.datamodel.userprofile.HumanUserProfile;
import org.bma.simulator.datamodel.userprofile.UserProfile;

public class UserNode {
  private final String id;
  private boolean isInfected;
  private boolean isImmune;
  private String culpritId;
  private String infectionWave;
  private int amountOfFollowers;
  private int amountOfFollows;
  private boolean isCelebrity;
  private UserProfile profile;

  public UserNode(String id) {
    this.id = id;
    this.profile = new HumanUserProfile();
  }

  public Object[][] getData() {
    return Stream.concat(Arrays.stream(new Object[][] {
        {"id", this.id},
        {"infected", this.isInfected},
        {"immune", this.isImmune},
        {"culprit_id", this.culpritId},
        {"wave", this.infectionWave},
        {"follower_count", this.amountOfFollowers},
        {"follow_count", this.amountOfFollows},
        {"is_celebrity", this.isCelebrity}
    }), Arrays.stream(profile.getData())).toArray(Object[][]::new);
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

  public void setProfile(UserProfile profile) {
    this.profile = profile;
  }

  public void setAmountOfFollows(int amountOfFollows) {
    this.amountOfFollows = amountOfFollows;
  }

  public boolean isCelebrity() {
    return isCelebrity;
  }

  public void setCelebrity(boolean celebrity) {
    isCelebrity = celebrity;
  }

  public UserProfile getProfile() {
    return profile;
  }

  public void setImmune(boolean immune) {
    isImmune = immune;
  }
}
