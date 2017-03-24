package adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.menuka.loginandregistration.R;

import java.util.List;

import models.Module;

/**
 * Created by menuka on 3/24/17.
 */

public class ModuleAdapter extends ArrayAdapter<Module> {
    private static final String TAG = ModuleAdapter.class.getSimpleName();

    List<Module> moduleList;

    public ModuleAdapter(@NonNull Context context, @LayoutRes int resource, List<Module> moduleList) {
        super(context, resource);
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View singleModuleView = convertView;
        Module currentModule = moduleList.get(position);

        if (singleModuleView == null) {
            singleModuleView = LayoutInflater.from(getContext()).inflate(R.layout.single_module_view, parent, false);
        }

        TextView codeTextView = (TextView) singleModuleView.findViewById(R.id.txtCode);
        codeTextView.setText(currentModule.getCode());

        TextView nameTextView = (TextView) singleModuleView.findViewById(R.id.txtName);
        nameTextView.setText(currentModule.getName());

        TextView creditsTextView = (TextView) singleModuleView.findViewById(R.id.txtCredits);
        creditsTextView.setText(currentModule.getCredits());

        TextView gradeTextView = (TextView) singleModuleView.findViewById(R.id.txtGrade);
        gradeTextView.setText(currentModule.getGrade());


        return singleModuleView;
    }
}
