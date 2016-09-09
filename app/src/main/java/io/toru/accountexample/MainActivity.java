package io.toru.accountexample;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AccountAuthenticatorActivity {
    
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TYPE = "AccountExample";

    private EditText    emailEdit;
    private EditText    passwordEdit;
    private Button      signInBtn;

    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEdit       = (EditText)findViewById(R.id.email);
        passwordEdit    = (EditText)findViewById(R.id.password);
        signInBtn       = (Button)findViewById(R.id.sign_in);

        accountManager = AccountManager.get(this);

        Account[] storedAccounts = accountManager.getAccountsByType(TYPE);
        if(storedAccounts.length > 0){
            for(Account account : storedAccounts){
                Log.w(TAG, "onCreate: name:: " + account.name);
                Log.w(TAG, "onCreate: type:: " + account.type);
                Log.w(TAG, "onCreate: pwd:: " + accountManager.getPassword(account));

            }
        }
    }

    public void doSignIn(View view){
        Log.w(TAG, "doSignIn: ");
        Account myAccount = new Account(emailEdit.getEditableText().toString(), TYPE);
        accountManager.addAccountExplicitly(myAccount, passwordEdit.getEditableText().toString(), null);
    }
}
