package sixthsense.caretest;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;


public class RegisterSafeContactActivity extends Activity
{
    class ItemAutoTextAdapter extends CursorAdapter
            implements android.widget.AdapterView.OnItemClickListener
    {
        private String mContactName = null;

        public ItemAutoTextAdapter()
        {
            super(RegisterSafeContactActivity.this, null);
        }

        public ItemAutoTextAdapter(String aContactName)
        {
            super(RegisterSafeContactActivity.this, null);
            mContactName = new String(aContactName);
        }

        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence aSearchString)
        {
            Cursor cursor = null;
            if (mContactName == null)
            {
                if (aSearchString != null)
                {
                    String selection = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ? ";
                    String[] selectionArgs = new String[]{"%" + aSearchString + "%"};
                    String orderBy = ContactsContract.Contacts.TIMES_CONTACTED + " DESC ";
                    cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, selection, selectionArgs, orderBy);
                }
            }
            else
            {
                String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE ? ";
                String[] selectionArgs = new String[]{mContactName};
                String orderBy = ContactsContract.CommonDataKinds.Phone.TIMES_CONTACTED + " DESC ";
                cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, selection, selectionArgs, orderBy);
            }
            return cursor;
        }

        @Override
        public String convertToString(Cursor cursor)
        {

            if (mContactName == null)
            {
                final int columnIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
                final String str = cursor.getString(columnIndex);
                return str;
            }
            else
            {
                final int columnIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
                final String str = cursor.getString(columnIndex);
                return str;
            }
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            final String text = convertToString(cursor);
            ((TextView) view).setText(text);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view =
                    inflater.inflate(android.R.layout.simple_dropdown_item_1line,
                            parent, false);

            return view;
        }

        @Override
        public void onItemClick(AdapterView<?> listView, View view, int position, long id)
        {
            if (mContactName == null)
            {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                ItemAutoTextAdapter adapter = new ItemAutoTextAdapter(contactName);
                mSafeContactPhoneNumberTextView.setAdapter(adapter);
                mSafeContactPhoneNumberTextView.setOnItemClickListener(adapter);
                mSafeContactPhoneNumberTextView.showDropDown();
            }
            else
            {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                mSafeContactPhoneNumberTextView.setText(contactNumber);
            }
        }

    }


    private AutoCompleteTextView mSafeContactNameTextView;
    private AutoCompleteTextView mSafeContactPhoneNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_safe_contact);

        mSafeContactNameTextView = (AutoCompleteTextView) findViewById(R.id.RegisterSafeContactNameEditText);
        mSafeContactPhoneNumberTextView = (AutoCompleteTextView) findViewById(R.id.RegisterSafeContactNumberEditText);
        ItemAutoTextAdapter adapter = this.new ItemAutoTextAdapter();
        mSafeContactNameTextView.setAdapter(adapter);
        mSafeContactNameTextView.setOnItemClickListener(adapter);

        Button addContactButton = (Button) findViewById(R.id.AddSafeContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                GenericUtils.ValidateOTP();
            }
        });
    }
}
