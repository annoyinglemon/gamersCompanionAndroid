package lemond.annoying.gamerscompanion.main_activity.fragment_news.view


import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.databinding.FragmentNewsDetailBinding
import lemond.annoying.gamerscompanion.repository.objects.Pulse
import android.webkit.WebViewClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.os.Build
import android.annotation.TargetApi
import android.graphics.Bitmap


private const val PULSE_PARAM = "pulse_param"


@Suppress("OverridingDeprecatedMember")
class NewsDetailFragment : Fragment() {

    private lateinit var viewBinding: FragmentNewsDetailBinding
    private var pulse: Pulse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pulse = it.getParcelable(PULSE_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_detail, container, false)
        viewBinding.imageViewNewsDetailsFragmentClose.setOnClickListener {
            parentFragment?.childFragmentManager?.popBackStack()
        }

        if (pulse != null) {
            viewBinding.toolbarNewsDetailsFragment.title = pulse?.title
            viewBinding.toolbarNewsDetailsFragment.subtitle = pulse?.url
            viewBinding.webViewNewsDetailsFragment.loadUrl(pulse?.url)
            viewBinding.webViewNewsDetailsFragment.settings.setJavaScriptEnabled(true)
            viewBinding.webViewNewsDetailsFragment.webViewClient = object: WebViewClient() {
                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                    // TODO Auto-generated method stub
                    super.onPageStarted(view, url, favicon)
                }

                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    val uri = Uri.parse(url)
                    return true
                }

                @TargetApi(Build.VERSION_CODES.N)
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    view.loadUrl(request.url.toString())
                    return true
                }
            }
        }
        return viewBinding.root
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: Pulse) =
                NewsDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(PULSE_PARAM, param1)
                    }
                }
    }
}
