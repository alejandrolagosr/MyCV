package com.lagos.mycv.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import com.lagos.domain.models.Education
import com.lagos.domain.models.Experience
import com.lagos.domain.models.Profile
import com.lagos.domain.usecases.GetCurriculumUseCase
import com.lagos.domain.usecases.GetEducationUseCase
import com.lagos.domain.usecases.GetExperienceUseCase
import com.lagos.domain.usecases.GetProfileUseCase
import com.lagos.mycv.MyCVApplication
import com.lagos.mycv.R
import com.lagos.mycv.custom.CardWithImage
import com.lagos.mycv.main.presenter.MainContract
import com.lagos.mycv.main.presenter.MainPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var mProfileUseCase: GetProfileUseCase

    @Inject
    lateinit var mCurriculumUseCase: GetCurriculumUseCase

    @Inject
    lateinit var mExperienceUseCase: GetExperienceUseCase

    @Inject
    lateinit var mEducationUseCase: GetEducationUseCase


    private val mMainPresenter by lazy {
        MainPresenter(this, mProfileUseCase, mCurriculumUseCase, mEducationUseCase, mExperienceUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyCVApplication).mAppComponent.inject(this)
        mMainPresenter.getCurriculum()
        fb_email.setOnClickListener {
            sendEmail()
        }
    }

    override fun onPause() {
        mMainPresenter.dispose()
        super.onPause()
    }

    override fun showProgress() {
        layout_loading.visibility = VISIBLE
    }

    override fun hideProgress() {
        layout_loading.visibility = GONE
    }

    override fun setData(profile: Profile) {
        collapsingToolbar.title = profile.name
        Picasso.get()
            .load(profile.image)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(toolbarImage)

        if (!TextUtils.isEmpty(profile.summary)) {
            cv_summary.visibility = VISIBLE
            tv_content.text = profile.summary
        }
    }

    override fun setEducationData(education: List<Education>) {
        if (education.isNotEmpty()) {
            addHeader(getString(R.string.header_education))
            education.forEach {
                val card = CardWithImage(this)
                card.setTitle(it.name)
                card.setSubtitle(it.type)
                card.setImage(it.image)
                layout_content.addView(card)
            }
        }
    }

    override fun setExperienceData(experience: List<Experience>) {
        if (experience.isNotEmpty()) {
            addHeader(getString(R.string.header_experience))
            experience.forEach {
                val card = CardWithImage(this)
                card.setTitle(it.name)
                card.setSubtitle(it.date_from + " to " + it.date_to)
                card.setImage(it.image)
                card.setDescription(it.description)
                layout_content.addView(card)
            }
        }
    }

    override fun setDataError() {
        layout_error.visibility = VISIBLE
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, "francisco.lagos@globant.com")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        if (intent.resolveActivity(this.packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun addHeader(title: String) {
        val header = TextView(this)
        header.text = title
        header.textSize = 20F
        layout_content.addView(header)
    }
}
