package netsurf_app.netsurf_direct.widgets;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


import netsurf_app.netsurf_direct.NetsurfUsApp;
import netsurf_app.netsurf_direct.R;

/**
 * Created by shivam on 8/2/15.
 */
public class TextViewFont extends AppCompatTextView {

    private static NetsurfUsApp baseApplication = null;

    public TextViewFont(final Context context) {
        this(context, null);
    }

    public TextViewFont(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewFont(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        if (baseApplication == null) {
            baseApplication = NetsurfUsApp.getInstance();
        }

        // prevent exception in Android Studio / ADT interface builder
        if (this.isInEditMode()) {
            return;
        }

        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextViewFont);
        if (array != null) {
            final String typefaceAssetPath = array.getString(R.styleable.TextViewFont_font_custom);

            if (typefaceAssetPath != null) {

                AssetManager assets = context.getAssets();

                if (typefaceAssetPath.equalsIgnoreCase(getResources().getString(R.string
                        .font_roboto_black))) {
                    setTypeface(baseApplication.getTypefaceRobotoBlack());

                } else if (typefaceAssetPath.equalsIgnoreCase(getResources().getString(R.string
                        .font_roboto_condensed_light))) {
                    setTypeface(baseApplication.getTypefaceRobotoCondensedLight());
                } else if (typefaceAssetPath.equalsIgnoreCase(getResources().getString(R.string
                        .font_roboto_condensed_regular))) {
                    setTypeface(baseApplication.getTypefaceRobotoCondensedRegular());
                } else if (typefaceAssetPath.equalsIgnoreCase(getResources().getString(R.string
                        .font_roboto_light))) {
                    setTypeface(baseApplication.getTypefaceRobotoLight());
                } else if (typefaceAssetPath.equalsIgnoreCase(getResources().getString(R.string
                        .font_roboto_medium))) {
                    setTypeface(baseApplication.getTypefaceRobotoMedium());
                } else if (typefaceAssetPath.equalsIgnoreCase(getResources().getString(R.string
                        .font_roboto_regular))) {
                    setTypeface(baseApplication.getTypefaceRobotoRegular());
                } else if (typefaceAssetPath.equalsIgnoreCase(getResources().getString(R.string
                        .font_roboto_condensed_bold))) {
                    setTypeface(baseApplication.getTypefaceRobotoCondensedBold());
                } else if (typefaceAssetPath.equalsIgnoreCase(getResources().getString(R.string
                        .font_roboto_bold))) {
                    setTypeface(baseApplication.getTypefaceRobotoBold());
                } else {
                    setTypeface(baseApplication.getTypefaceRobotoThin());
                }

            }
            array.recycle();
        }
    }
}