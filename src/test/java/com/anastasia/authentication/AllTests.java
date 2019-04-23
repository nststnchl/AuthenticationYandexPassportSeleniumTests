package com.anastasia.authentication;

import com.anastasia.authentication.basic.negative.UnregisteredEmailTest;
import com.anastasia.authentication.basic.positive.SuccessfulLoginTest;
import com.anastasia.authentication.usability.FieldPopulationTest;
import org.junit.runner.*;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                FieldPopulationTest.class,
                SuccessfulLoginTest.class,
                UnregisteredEmailTest.class
        }
)
public class AllTests {

}