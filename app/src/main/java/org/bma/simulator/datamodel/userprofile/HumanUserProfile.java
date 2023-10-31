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

  @Override
  public Object[][] getData() {
    return Stream.concat(Arrays.stream(super.getData()), Arrays.stream(new Object[][] {
        {"political_type", politicalType.toString()},
        {"age_group", ageGroup.toString()}
    })).toArray(Object[][]::new);
  }
}
