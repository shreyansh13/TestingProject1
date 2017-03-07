package sixthsense.caretest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GroupListDisplayActivity extends AppCompatActivity
{
    String[] mSafeNames = {"Home", "Work", "Play", "Superstore"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list_display);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_group_list_element_view, mSafeNames);

        ListView listView = (ListView) findViewById(R.id.group_list);
        listView.setAdapter(adapter);
    }
}
