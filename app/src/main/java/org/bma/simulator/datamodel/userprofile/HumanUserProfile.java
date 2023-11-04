package org.bma.simulator.datamodel.userprofile;

import java.util.Arrays;
import java.util.stream.Stream;
import org.bma.simulator.datamodel.userprofile.attributetype.AgeGroup;
import org.bma.simulator.datamodel.userprofile.attributetype.PoliticalType;
import org.bma.simulator.datamodel.userprofile.utils.AttributeCalcUtils;

public class HumanUserProfile extends UserProfile {
  private PoliticalType politicalType;
  private AgeGroup ageGroup;

  public HumanUserProfile() {
    super(UserProfileConstants.AVERAGE, 1.0, 0.0); //placeholder for init
    this.scepticism = AttributeCalcUtils.calcUserScepticism(this);
  }

  public HumanUserProfile(PoliticalType politicalType, AgeGroup ageGroup) {
    super(UserProfileConstants.AVERAGE, 1.0, 0.0); //placeholder for init
    this.scepticism = AttributeCalcUtils.calcUserScepticism(this);
    this.politicalType = politicalType;
    this.ageGroup = ageGroup;
  }

  public HumanUserProfile(String type, PoliticalType politicalType, AgeGroup ageGroup) {
    super(type, 1.0, 1.0);
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
