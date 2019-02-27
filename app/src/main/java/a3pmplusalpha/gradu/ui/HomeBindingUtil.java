package a3pmplusalpha.gradu.ui;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class HomeBindingUtil {
    String welcome = "Welcome, ";

    @BindingAdapter(value = "UserName")
    public void setBold(TextView textView, String str){
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD),
                0,
                welcome.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}