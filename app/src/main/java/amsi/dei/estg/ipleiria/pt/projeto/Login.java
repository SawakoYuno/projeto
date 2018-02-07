package amsi.dei.estg.ipleiria.pt.projeto;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

   // http://192.168.1.66:8888/users/
   // String url = "http://10.0.2.2:8888/users/";
    String url = "http://10.0.2.2:8888/users/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE);
        prefEditor = preferences.edit();

        String auth = preferences.getString("auth", "");

        if (!auth.isEmpty())
        {

            String tipo = preferences.getString("tipo", "");

            if(tipo.equals("cliente"))
            {
                //abrir menu cliente
                Intent intent = new Intent(getApplicationContext(), c_main.class);
                startActivity(intent);
                finish();
            } else if(tipo.equals("empregado"))
            {
                //abrir menu empregado
                Intent intent = new Intent(getApplicationContext(), e_main.class);
                startActivity(intent);
                finish();
            }
        }else
        {
            setTitle("Bem vindo!");
            mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);

            mPasswordView = (EditText) findViewById(R.id.password);
            mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == R.id.login || id == EditorInfo.IME_NULL) {
                        attemptLogin();
                        return true;
                    }
                    return false;
                }
            });

            mLoginFormView = findViewById(R.id.login_form);
            mProgressView = findViewById(R.id.login_progress);
        }

    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (password.trim().isEmpty() || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(username, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }


    public void onClickLogin(View view) {

        byte[] authBytes = (mUsernameView.getText().toString()+":"+mPasswordView.getText().toString()).getBytes();
        //MUITO IMPORTANTE -> guardar isto para usar SEMPRE (ex: num singleton, ou em todos)
        //AINDA NÃO GUARDASTE ESTA VARIÁVEL. SE TENS AUTENTICAÇÃO NO RESTO DA API, PFF GUARDA
        final String authorization = Base64.encodeToString(authBytes, Base64.NO_WRAP);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url + mUsernameView.getText().toString() + "/tipo",null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        //GUARDAR VARIÁVEL IMPORTANTE
                        prefEditor.putString("auth", authorization);

                        String tipo = "";

                        try {
                            tipo = response.getString("tipo");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (tipo.equals("cliente"))
                        {
                            prefEditor.putString("tipo", "cliente");
                            prefEditor.apply();
                            //Abrir parte de cliente
                            Intent intent = new Intent(getApplicationContext(), c_main.class);
                            startActivity(intent);
                            //Prevenir que o utilizador volte atrás ao login
                            finish();

                        }else if (tipo.equals("empregado") || tipo.equals("admin"))
                        {
                            prefEditor.putString("tipo", "empregado");
                            prefEditor.apply();
                            //Abrir parte do empregado
                            Intent intent = new Intent(getApplicationContext(), e_main.class);
                            startActivity(intent);
                            //Prevenir que o utilizador volte atrás ao login
                            finish();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Erro ao fazer login", Toast.LENGTH_SHORT).show();
                    }


                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json");
                //Necessário para passar na autenticação
                params.put("Authorization", "Basic " + authorization);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}


      interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
         private final String mPassword;

        UserLoginTask(String email, String password) {
           mEmail = email;
           mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            //for (String credential : DUMMY_CREDENTIALS) {
               // String[] pieces = credential.split(":");
               // if (pieces[0].equals(mEmail)) {
                     //Account exists, return true if the password matches.
                   // return pieces[1].equals(mPassword);
               // }
            //}

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
           /* mAuthTask = null;
            showProgress(false);


            //(username+":"+password)
            byte[] authBytes = (mUsernameView.getText().toString() +":"+mPasswordView.getText().toString()).getBytes();
            //MUITO IMPORTANTE -> guardar isto para usar SEMPRE (ex: num singleton, ou em todos)
            final String authorization = Base64.encodeToString(authBytes, Base64.DEFAULT);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.GET, url + mUsernameView.getText().toString(),null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response)
                        {
                            String tipo = "";

                            try {
                                tipo = response.getString("tipo");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (tipo == "cliente")
                            {
                                //Abrir parte de cliente
                                Intent intent = new Intent(getApplicationContext(), c_main.class);
                                startActivity(intent);

                            }else if (tipo == "empregado")
                            {
                                //Abrir parte do empregado
                                Intent intent = new Intent(getApplicationContext(), e_main.class);
                                startActivity(intent);
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "Erro ao fazer login", Toast.LENGTH_SHORT).show();
                        }


                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Accept", "application/json");
                    //Necessário para passar na autenticação
                    params.put("Authorization", "Basic " + authorization);
                    return params;
                }
            };


        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }*/
    }
}

