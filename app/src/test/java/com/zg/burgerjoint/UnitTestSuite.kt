package com.zg.burgerjoint

import com.zg.burgerjoint.mvp.presenters.impl.MainPresenterImplTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

// Runs all unit tests.
@RunWith(Suite::class)
@Suite.SuiteClasses(ExampleUnitTest::class,
    MainPresenterImplTest::class)
class UnitTestSuite