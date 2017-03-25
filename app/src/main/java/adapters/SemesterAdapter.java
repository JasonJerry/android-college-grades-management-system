package adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.menuka.loginandregistration.R;

import java.util.List;

import models.Semester;

/**
 * Created by menuka on 3/25/17.
 */

public class SemesterAdapter extends ArrayAdapter<Semester> {
    private static final String TAG = SemesterAdapter.class.getSimpleName();

    private Button btnMore;
    private Button btnRemove;

    List<Semester> semesterList;


    public SemesterAdapter(@NonNull Context context, @LayoutRes int resource, List<Semester> semesterList) {
        super(context, resource, semesterList);
        this.semesterList = semesterList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View semesterItemView= convertView;
        Semester currentSemester = semesterList.get(position);

        if(semesterItemView == null){
            semesterItemView = LayoutInflater.from(getContext()).inflate(R.layout.single_semester_on_profile, parent, false);
        }

        TextView numberTextView = (TextView) semesterItemView.findViewById(R.id.number_text_view);
        numberTextView.setText("Semester " + currentSemester.getNumber());

        TextView sgpaTextView = (TextView) semesterItemView.findViewById(R.id.sgpa_text_view);
        sgpaTextView.setText("SGPPA: " + currentSemester.getSgpa());

        btnMore = (Button) semesterItemView.findViewById(R.id.btnMore);
        btnRemove = (Button) semesterItemView.findViewById(R.id.btnRemove);

        return semesterItemView;
    }
}
