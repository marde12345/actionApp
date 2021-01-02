package com.mardefasma.influaction_java.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.mardefasma.influaction_java.MainActivity;
import com.mardefasma.influaction_java.Preferences;
import com.mardefasma.influaction_java.R;
import com.mardefasma.influaction_java.api.ApiClient;
import com.mardefasma.influaction_java.api.ApiInterface;
import com.mardefasma.influaction_java.api.api_res.LoginUser;
import com.mardefasma.influaction_java.api.model.User;
import com.mardefasma.influaction_java.MainInfActivity;
import com.mardefasma.influaction_java.data.model.LoggedInUser;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity{
    String TAG = "Login Activity";
    String DEFAULT_PASSWORD = "influaction123";

    private LoginViewModel loginViewModel;
    private SignInButton signInButton;
    private GoogleSignInClient googleSignInClient;
    private int RC_SIGN_IN;
    private ApiInterface mApiInterface;
    private ProgressBar loadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        mApiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button loginGoogleButton = findViewById(R.id.logingoogle);
        final Button loginSkipButton = findViewById(R.id.loginskip);
        loadingProgressBar = findViewById(R.id.loading);

        //        hide status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //        end hide status bar

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());

                    //Complete and destroy login activity once successful
                    finish();
                }
                setResult(Activity.RESULT_OK);

            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
//                loginViewModel.login(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());
                userLogin(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        loginGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginGoogle();
            }
        });

        loginSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                Toast.makeText(getApplicationContext(), getString(R.string.welcome_anonymous), Toast.LENGTH_SHORT).show();
            }
        });

        if (Preferences.getLoggedInStatus(getBaseContext())){
            roleRedirect(Preferences.getKeyRole(getBaseContext()));
        }
    }

    private void userLogin(String username, String password) {
        Call<LoginUser> userCall = mApiInterface.loginUser(username,password);
        userCall.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                if (response.isSuccessful() && response.body().getMessage() == null){
                    User userRes = response.body().getUser();
                    updatePreferences(userRes);
                    roleRedirect(userRes.getRole());
                }else{
                    Toast.makeText(LoginActivity.this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
                }
                loadingProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();

                loadingProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void LoginGoogle(){
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            final GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (!account.getId().equals("")&&!account.getId().equals(null)){

                Preferences.setKeyPhotoUrl(getBaseContext(),account.getPhotoUrl().toString());
                Preferences.setKeyIdGoogle(getBaseContext(),account.getIdToken());
                Preferences.setLoggedInUser(getBaseContext(),account.getDisplayName());
                Preferences.setLoggedInStatus(getBaseContext(),true);

                Call<LoginUser> userCall = mApiInterface.registerUser(
                                        account.getDisplayName(),
                                        account.getEmail(),
                                        DEFAULT_PASSWORD,
                                        DEFAULT_PASSWORD,
                                        account.getPhotoUrl().toString()
                                );
                userCall.enqueue(new Callback<LoginUser>() {
                    @Override
                    public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                        Log.d(TAG, "onResponse: Success Register");
                    }

                    @Override
                    public void onFailure(Call<LoginUser> call, Throwable t) {
                        Log.e(TAG, "onFailure: ",t );
                    }
                });

                roleRedirect("cust");
            }else{
                Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
            }
        } catch (ApiException e) {
            Log.d("error", ""+e);
            Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();

        Call<User> userCall = mApiInterface.getUserById(Integer.parseInt(model.getDisplayId()));
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userRes = response.body();
                updatePreferences(userRes);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });

        // TODO : initiate successful logged in experience
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void updatePreferences(User userRes) {
        Log.d(TAG, "updatePreferences: "+userRes.toString());
        Preferences.setKeyPhotoUrl(getBaseContext(),userRes.getPhoto_profile().toString());
        Preferences.setLoggedInUser(getBaseContext(),userRes.getName());
        Preferences.setKeyRole(getBaseContext(),userRes.getRole());
        Preferences.setKeyId(getBaseContext(), userRes.getId().toString());
        Preferences.setLoggedInStatus(getBaseContext(),true);
    }

    private void roleRedirect(String role){
        Intent intent;

        switch (role){
            case "inf":
                intent = new Intent(LoginActivity.this, MainInfActivity.class);
                break;
            case "cust":
            default:
                intent = new Intent(LoginActivity.this, MainActivity.class);
                break;
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);

    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}