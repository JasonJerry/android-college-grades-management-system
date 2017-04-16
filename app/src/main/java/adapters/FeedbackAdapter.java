package adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.menuka.loginandregistration.R;

import java.util.List;

import models.Feedback;

/**
 * Created by menuka on 4/6/17.
 */

public class FeedbackAdapter extends ArrayAdapter<Feedback>{
    private static final String TAG = FeedbackAdapter.class.getSimpleName();

    List<Feedback> feedbackList;

    public FeedbackAdapter(@NonNull Context context, @LayoutRes int resource, List<Feedback> feedbackList) {
        super(context, resource, feedbackList);
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View feedbackItemView = convertView;
        Feedback currentFeedback = feedbackList.get(position);
	Log.i("currentFeedbackObtained", "");

        if(feedbackItemView == null){
            feedbackItemView = LayoutInflater.from(getContext()).inflate(R.layout.single_feedback_card, parent, false);
        }

        TextView ratingTextView = (TextView) feedbackItemView.findViewById(R.id.rating_text_view);
        ratingTextView.setText(currentFeedback.getRating());

        TextView commentTextView = (TextView) feedbackItemView.findViewById(R.id.comment_text_view);
        commentTextView.setText(currentFeedback.getComment());

        TextView reviewerTextView = (TextView) feedbackItemView.findViewById(R.id.reviewer_text_view);
        reviewerTextView.setText(currentFeedback.getReviewer());

        TextView dateTextView = (TextView) feedbackItemView.findViewById(R.id.date_text_view);
        dateTextView.setText(currentFeedback.getDate());


        return feedbackItemView;
    }
}
