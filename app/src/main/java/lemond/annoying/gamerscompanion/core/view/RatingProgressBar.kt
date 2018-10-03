package lemond.annoying.gamerscompanion.core.view

import android.content.Context
import android.content.res.TypedArray
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.databinding.ProgressRatingBinding

class RatingProgressBar(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private var progressRatingBinding : ProgressRatingBinding
    private var mRating : Int
    private var mLabel : String?

    init {
        val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.RatingProgressBar, 0, 0)

        try {
            mRating = typedArray.getInteger(R.styleable.RatingProgressBar_rating, 0)
            mLabel = typedArray.getString(R.styleable.RatingProgressBar_label)
        } finally {
            typedArray.recycle()
        }

        progressRatingBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.progress_rating, this, true)

        if (mRating > 0) {
            progressRatingBinding.progressBarRating.progress = mRating
            progressRatingBinding.textViewRating.setText(Integer.toString(mRating))
        }
        progressRatingBinding.textViewLabel.setText(mLabel)

    }

    fun setLabel(label: String) {
        this.mLabel = label
        progressRatingBinding.textViewLabel.setText(mLabel)
        invalidate()
        requestLayout()
    }

    fun setRating(rating: Int) {
        this.mRating = rating
        progressRatingBinding.textViewRating.setText(Integer.toString(mRating))
        progressRatingBinding.progressBarRating.progress = mRating
        invalidate()
        requestLayout()
    }


}

