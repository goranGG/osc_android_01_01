package hr.ferit.bruno.exercise1.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.ferit.bruno.exercise1.R;
import hr.ferit.bruno.exercise1.TasksRepository;
import hr.ferit.bruno.exercise1.model.Task;

public class MainActivity extends AppCompatActivity {

    TasksRepository mRepository;

    @BindView(R.id.edittext_newtask_title) EditText mTitle;
    @BindView(R.id.edittext_newtask_summary) EditText mSummary;
    @BindView(R.id.edittext_newtask_importance) EditText mImportance;
    @BindView(R.id.textview_newtask_display) TextView mTextview;

    private static final String TAG = "Log: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();

        mRepository = TasksRepository.getInstance();
    }

    private void initializeUI() {
        ButterKnife.bind(this);
    }

    public void saveTask(View view) {

        try {
            // Parse data from the widgets and store it in a task
            Task mTask = new Task(Integer.parseInt(mImportance.getText().toString()), mTitle.getText().toString(), mSummary.getText().toString());
            // Store the task in the fake database using the repository
            mRepository.save(mTask);

            // Clear all of the editText controls
            mTitle.getText().clear();
            mSummary.getText().clear();
            mImportance.getText().clear();

            // Create a method for displaying the tasks in the textview as strings
            // one below the other and call it on each new task.
            mTextview.setText("");
            displayTasks();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void displayTasks() {
        for (Task task : mRepository.getTasks()) {
            mTextview.append("Title: " +task.getTitle()+" Importance: "+task.getImportance()+"\nSummary: "+task.getSummary()+"\n");
        }
    }
}
