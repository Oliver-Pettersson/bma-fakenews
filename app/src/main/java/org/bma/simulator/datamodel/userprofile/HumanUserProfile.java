package org.bma.simulator.datamodel.userprofile;

import java.util.Arrays;
import java.util.stream.Stream;
import org.bma.simulator.datamodel.userprofile.attributetype.AgeGroup;
import org.bma.simulator.datamodel.userprofile.attributetype.PoliticalType;

public class HumanUserProfile extends UserProfile {
  private final static PoliticalType DEFAULT_POLITICAL_TYPE = PoliticalType.NONE;
  private final static AgeGroup DEFAULT_AGE_GROUP = AgeGroup.ADULT;
  private PoliticalType politicalType;
  private AgeGroup ageGroup;

  public HumanUserProfile() {
    super(UserProfileConstants.DEFAULT_USER);
    this.politicalType = DEFAULT_POLITICAL_TYPE;
    this.ageGroup = DEFAULT_AGE_GROUP;
  }

  public HumanUserProfile(PoliticalType politicalType, AgeGroup ageGroup) {
    super(UserProfileConstants.DEFAULT_USER);
    this.politicalType = politicalType;
    this.ageGroup = ageGroup;
  }

  public HumanUserProfile(String type, PoliticalType politicalType, AgeGroup ageGroup) {
    super(type);
    this.politicalType = politicalType;
    this.ageGroup = ageGroup;
  }

  @Override
  public Object[][] getData() {
    return Stream.concat(Arrays.stream(super.getData()), Arrays.stream(new Object[][] {
        {"political_type", politicalType.name()},
        {"age_group", ageGroup.name()}
    })).toArray(Object[][]::new);
  }

  public PoliticalType getPoliticalType() {
    return politicalType;
  }

  public AgeGroup getAgeGroup() {
    return ageGroup;
  }

  public void setPoliticalType(PoliticalType politicalType) {
    this.politicalType = politicalType;
  }

  public void setAgeGroup(AgeGroup ageGroup) {
    this.ageGroup = ageGroup;
  }
}
