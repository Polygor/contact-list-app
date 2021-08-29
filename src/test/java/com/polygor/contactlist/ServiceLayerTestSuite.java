package com.polygor.contactlist;

import com.polygor.contactlist.service.PeopleCsvServiceTest;
import com.polygor.contactlist.service.PeopleServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { PeopleCsvServiceTest.class, PeopleServiceTest.class } )
public class ServiceLayerTestSuite {
}
