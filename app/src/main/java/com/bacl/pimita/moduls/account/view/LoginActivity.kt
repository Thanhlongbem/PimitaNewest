package com.bacl.pimita.moduls.account.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.bacl.pimita.R
import com.bacl.pimita.base.BaseActivity
import com.bacl.pimita.model.SignUpRequest
import com.bacl.pimita.moduls.account.presenter.LoginPresenter
import com.bacl.pimita.moduls.home.view.HomeActivity
import com.bacl.solution.Extensions.isValidEmail
import com.bacl.solution.Extensions.isValidPassword
import com.bacl.solution.Extensions.isValidPhone
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.view_change_pass.*
import kotlinx.android.synthetic.main.view_forgot_pass.*
import kotlinx.android.synthetic.main.view_forgot_pass.tvSignupFromForgotPassScreen
import kotlinx.android.synthetic.main.view_input_verify_code.*
import kotlinx.android.synthetic.main.view_login.*
import kotlinx.android.synthetic.main.view_login.btnLogin
import kotlinx.android.synthetic.main.view_sign_up.*


class LoginActivity : BaseActivity(), View.OnClickListener, LoginView{
    private var viewShowing: View? = null
    private lateinit var fadeIn: Animation
    private lateinit var fadeOut: Animation
    private lateinit var gso: GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN: Int = 333
    private val RESULT_LOAD_IMG = 1000
    private val callbackManager: CallbackManager = CallbackManager.Factory.create()
    private lateinit var presenter: LoginPresenter

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        presenter = LoginPresenter(this, applicationContext)
        setViewAndOnClick()
        setUpSignUpGoogle()
        setState()

    }

    private fun setViewAndOnClick(){
        fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
        fadeIn.duration = 100
        fadeOut.duration = 100
        viewShowing = viewLogin
        tvSignupFromLoginScreen.setOnClickListener(this)
        tvForgotPass.setOnClickListener(this)
        tvSignupFromForgotPassScreen.setOnClickListener(this)
        btnSendVerifyCode.setOnClickListener(this)
        btnVerifyCode.setOnClickListener(this)
        btnChangePass.setOnClickListener(this)
        lnContentButtonGoogle.setOnClickListener (this)
        btnLogin.setOnClickListener(this)
        tvLogin.setOnClickListener(this)
        btnSignUpOfSignUpScreen.setOnClickListener(this)
        lnContentButtonFacebook.z = 1000f
        clFacebook.setOnClickListener(this)
        //lnContentButtonFacebook.z = 1000f
        lnContentButtonGoogle.z = 1000f

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                Log.d("ChoanhChoanh", "onSuccess")
                // App code
            }

            override fun onCancel() {
                // App code
                Log.d("ChoanhChoanh", "onCancel")
            }

            override fun onError(exception: FacebookException) {
                // App code
                Log.d("ChoanhChoanh", "onError")
            }
        })
    }

    private fun setState(){
        edtEmailLogin.addTextChangedListener{
            if (it.toString().isValidEmail()) imgMailInLoginScState.setImageResource(R.drawable.ic_check)
            else imgMailInLoginScState.setImageResource(R.drawable.icon_untick)
        }

        edtPassLogin.addTextChangedListener{
            if (it.toString().isValidPassword()) imgPassInLoginScState.setImageResource(R.drawable.ic_check)
            else imgPassInLoginScState.setImageResource(R.drawable.icon_untick)
        }

        edtEmailAndPhoneNoToResetPass.addTextChangedListener{
            if (it.toString().isValidEmail() || it.toString().isValidPhone()){
                imgCheckValidateFPScreen.setImageResource(R.drawable.ic_check)
            }else{
                imgCheckValidateFPScreen.setImageResource(R.drawable.icon_untick)
            }
        }

        edtInputVerifyCode.addTextChangedListener{
            if (it.toString().length < 4){
                imgCheckValidateInputVerifyCodeScreen.setImageResource(R.drawable.icon_untick)
            }else{
                imgCheckValidateInputVerifyCodeScreen.setImageResource(R.drawable.ic_check)
            }
        }

        edtEnterNewPass.addTextChangedListener{
            if(it.toString().isValidPassword()){
                imgCheckValidateNewPass.setImageResource(R.drawable.ic_check)
            }else{
                imgCheckValidateNewPass.setImageResource(R.drawable.icon_untick)
            }
        }

        edtReEnterNewPass.addTextChangedListener{
            if(it.toString().isValidPassword() && it.toString().equals(edtEnterNewPass.text.toString())){
                imgCheckValidateReEnterNewPass.setImageResource(R.drawable.ic_check)
            }else{
                imgCheckValidateReEnterNewPass.setImageResource(R.drawable.icon_untick)
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val v = currentFocus

        if (v != null &&
                (ev!!.action === MotionEvent.ACTION_UP || ev!!.action === MotionEvent.ACTION_MOVE) &&
                v is EditText &&
                !v.javaClass.name.startsWith("android.webkit.")) {
            val scrcoords = IntArray(2)
            v.getLocationOnScreen(scrcoords)
            val x = ev!!.rawX + v.getLeft() - scrcoords[0]
            val y = ev!!.rawY + v.getTop() - scrcoords[1]
            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) hideKeyboard(this)
        }
        return super.dispatchTouchEvent(ev)
    }

    fun hideKeyboard(activity: Activity?) {
        if (activity != null && activity.window != null) {
            val imm: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }*/


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tvSignupFromLoginScreen -> {
                if (!isViewShowing(viewSignup)) {
                    fadeInView(viewSignup)
                }
            }
            R.id.tvForgotPass -> {
                if(!isViewShowing(viewForgotPass)){
                    fadeInView(viewForgotPass)
                }
            }
            R.id.tvSignupFromForgotPassScreen -> {
                if (!isViewShowing(viewSignup)) {
                    fadeInView(viewSignup)
                }
            }
            R.id.btnSendVerifyCode -> {
                if (!edtEmailAndPhoneNoToResetPass.text.toString().isValidEmail() && !edtEmailAndPhoneNoToResetPass.text.toString().isValidPhone()){
                    imgCheckValidateFPScreen.setImageResource(R.drawable.icon_untick)
                }else{
                    if(!isViewShowing(viewInputVerifyCode)){
                        fadeInView(viewInputVerifyCode)
                    }
                }
            }
            R.id.btnVerifyCode -> {
                if (edtInputVerifyCode.text.toString().length < 4){
                    imgCheckValidateInputVerifyCodeScreen.setImageResource(R.drawable.icon_untick)
                }else{
                    if(!isViewShowing(viewChangePass)){
                        fadeInView(viewChangePass)
                    }
                }
            }
            R.id.btnChangePass -> {
                if (edtEnterNewPass.text.toString().isValidPassword() && edtReEnterNewPass.text.toString().isValidPassword() && edtReEnterNewPass.text.toString().equals(edtEnterNewPass.text.toString())){
                    if(!isViewShowing(viewChangePassSuccess)){
                        fadeInView(viewChangePassSuccess)
                    }
                }else{
                    if(edtEnterNewPass.text.toString().isValidPassword()){
                        imgCheckValidateNewPass.setImageResource(R.drawable.ic_check)
                    }else{
                        imgCheckValidateNewPass.setImageResource(R.drawable.icon_untick)
                    }

                    if(!edtReEnterNewPass.text.toString().isValidPassword() || !edtReEnterNewPass.text.toString().equals(edtEnterNewPass.text.toString())){
                        imgCheckValidateReEnterNewPass.setImageResource(R.drawable.icon_untick)
                    }else{
                        imgCheckValidateReEnterNewPass.setImageResource(R.drawable.ic_check)
                    }
                }
            }
            R.id.btnLogin -> {
                if(edtEmailLogin.text.toString().isValidEmail() && edtPassLogin.text.toString().isValidPassword()){
                    //do st here
                    startActivity(Intent(this, HomeActivity::class.java))
                }else {
                    if (!edtEmailLogin.text.toString().isValidEmail()) imgMailInLoginScState.setImageResource(R.drawable.icon_untick)
                    if (!edtPassLogin.text.toString().isValidPassword()) imgPassInLoginScState.setImageResource(R.drawable.icon_untick)
                    //TODO: REMOVE LATER
                    startActivity(Intent(this, HomeActivity::class.java))
                }
            }

            R.id.lnContentButtonGoogle-> {
                signUpGoogle()
            }
            R.id.tvLogin-> {
                if (!isViewShowing(viewLogin)) {
                    fadeInView(viewLogin)
                }
            }
            R.id.clFacebook -> {
                LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "user_friends"))
            }
            R.id.btnSignUpOfSignUpScreen -> {
                val signUpRequest = SignUpRequest(edtFullNameSignup.text.toString(),
                        edtEmailSignup.text.toString(), 1, null, edtPhoneSignup.text.toString(),
                        1, 1, edtPassSignup.text.toString())
                presenter.signUp(signUpRequest)
            }
        }
    }


    override fun onBackPressed() {
        if (isViewShowing(viewLogin)) {
            super.onBackPressed()
        } else {
            fadeInView(viewLogin)
        }
    }

    private fun isViewShowing(view: View): Boolean {
        return view.id == viewShowing!!.id
    }

    private fun fadeInView(view: View) {
        if (viewShowing != null) {
            viewShowing!!.startAnimation(fadeOut)
            viewShowing!!.visibility = View.INVISIBLE
        }
        view.startAnimation(fadeIn)
        view.visibility = View.VISIBLE
        viewShowing = view
    }
    private fun setUpSignUpGoogle() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }
    private fun signUpGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        } else if (requestCode == RESULT_LOAD_IMG && data != null && data.data != null) {
            val selectedImage: Uri? = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

            val cursor = contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
            cursor!!.moveToFirst()

            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val picturePath = cursor.getString(columnIndex)
            cursor.close()
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
        }
    }

    override fun signUpSuccess() {
        if (!isViewShowing(viewInputVerifyCode)) {
            fadeInView(viewInputVerifyCode)
        }
    }

    override fun signUpFail(msg: String, errorCode: Int) {

    }

    override fun showProgress(show: Boolean) {
        if (show) showProgressBar() else dismissProgressBar()
    }

    override fun onErrorNetwork(error: Boolean) {
        showSheetNetwork(error)
    }
}