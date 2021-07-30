package Utilities;

import common.Basecls;

public class ReportingUtils extends Basecls {


    public void CreateTestCase(String TestID) {
        objTest = objExtent.createTest(TestID);
    }

}