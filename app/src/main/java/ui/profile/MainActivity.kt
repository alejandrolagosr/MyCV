package ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import com.lagos.domain.models.Profile
import com.lagos.domain.usecases.GetProfileUseCase
import com.lagos.mycv.R
import com.lagos.mycv.base.BaseActivity
import com.lagos.mycv.custom.CardWithImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    @Inject
    lateinit var profileUseCase: GetProfileUseCase

    private lateinit var mMainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fb_email.setOnClickListener {
            sendEmail()
        }
    }

    override fun onResume() {
        super.onResume()
        mMainPresenter.getData()
    }

    override fun onPause() {
        mMainPresenter.onPause()
        super.onPause()
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this, profileUseCase)
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

        if (profile.experience.isNotEmpty()) {
            addHeader(getString(R.string.header_experience))
            profile.experience.forEach {
                val card = CardWithImage(this)
                card.setTitle(it.name)
                card.setSubtitle(it.date_from + " to " + it.date_to)
                card.setImage(it.image)
                card.setDescription(it.description)
                layout_content.addView(card)
            }
        }

        if (profile.education.isNotEmpty()) {
            addHeader(getString(R.string.header_education))
            profile.education.forEach {
                val card = CardWithImage(this)
                card.setTitle(it.name)
                card.setSubtitle(it.type)
                card.setImage(it.image)
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
