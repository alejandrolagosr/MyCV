package com.lagos.mycv.main

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.lagos.domain.models.Curriculum
import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile
import com.lagos.mycv.MyCVApplication
import com.lagos.mycv.R
import com.lagos.mycv.injection.AppModuleMock
import com.lagos.mycv.main.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    fun setupMainActivity() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as MyCVApplication
        val testComponent = DaggerAppComponentMock.builder()
            .appModuleMock(AppModuleMock(fillMainResponseModel()))
            .build()
        app.mAppComponent = testComponent
        activityRule.launchActivity(Intent())
    }

    private fun fillMainResponseModel(): Curriculum {
        var curriculum = Curriculum()
        val profile = Profile("Profile user summary", "User name", "User image URL")
        val education = Education("School name", "School type", "School image URL")
        val experience = Experience("Company name", "Date from", "Date to",
            "Company image URL", "Work description")
        val schoolTrajectoryList = ArrayList<Education>()
        val professionalTrajectoryList = ArrayList<Experience>()
        schoolTrajectoryList.add(education)
        professionalTrajectoryList.add(experience)
        curriculum.profile = profile
        curriculum.experience = professionalTrajectoryList
        curriculum.education = schoolTrajectoryList
        return curriculum
    }

    @Test
    fun checkLabels() {
        Espresso.onView(ViewMatchers.withId(R.id.tv_title_summary))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.title_summary)))
    }

    @Test
    fun checkValuesOfText() {
        Espresso.onView(ViewMatchers.withId(R.id.collapsingToolbar))
            .check(ViewAssertions.matches(ViewMatchers.withText("User name")))
        Espresso.onView(ViewMatchers.withId(R.id.cv_summary))
            .check(ViewAssertions.matches(ViewMatchers.withText("Profile user summary")))
    }

    @Test
    fun goToSchoolActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.fb_email)).perform(ViewActions.click())
    }
}