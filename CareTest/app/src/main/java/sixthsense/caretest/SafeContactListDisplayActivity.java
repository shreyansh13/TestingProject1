package sixthsense.caretest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SafeContactListDisplayActivity extends Activity
{
    String[] mSafeNames = {"Papa", "Mummy", "Prachi", "Manu"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_contact_list_display);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_safelist_element_view, mSafeNames);

        ListView listView = (ListView) findViewById(R.id.safe_contact_list);
        listView.setAdapter(adapter);
    }
}
