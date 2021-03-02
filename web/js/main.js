/* Editor: Johnson Gao 
 * Date This File Created: 2020-4-12 2:31:05
 * Description Of This Class:
 */

/**
 * Submit the sign up form.
 * @returns {undefined}
 */


function submitSignUp()
{
    //ger required information
    var usernameDOM = document.getElementById("username-input");
    var passwordDOM = document.getElementById('password-input');
    var canvasCodeDOM = document.getElementById('canvas-code-input');
    var emailInputDOM = document.getElementById('email-input');

    var username = usernameDOM.value + "";
    var password = passwordDOM.value + "";
    var email = emailInputDOM.value + "";

    //check if any cridential is missing
    /////////////////////check username
    if (username === "")
    {
        if (document.getElementById('username-input').innerHTML === "")
        {
            //first time fail
            usernameDOM.classList.toggle('failed');
            document.getElementById('usernameMessage').innerHTML = "Please enter a username!";
        }
    } else
    {
        //clear any warning message
        if (document.getElementById('username-input').innerHTML !== "")
        {
            usernameDOM.classList.toggle('failed');
            document.getElementById('usernameMessage').innerHTML = "";
        }



        ////////////////////check email empty
        var isEmailMessageEmpty = document.getElementById('emailMessage').innerHTML === "";

        if (!ValidateEmail(email))
        {
            if (isEmailMessageEmpty)
            {
                emailInputDOM.classList.toggle('failed');
                document.getElementById('emailMessage').innerHTML = "Please enter a valid email!";
            }
        } else
        {
            if (!isEmailMessageEmpty)
            {
                emailInputDOM.classList.toggle('failed');
                document.getElementById('emailMessage').innerHTML = "";
            }

            //////////////////////check password empty
            var isPasswordMessageEmpty = document.getElementById('passwordMessage').innerHTML === "";

            if (password === "")
            {
                if (isPasswordMessageEmpty)
                {
                    document.getElementById('password-input').classList.toggle('failed');
                    document.getElementById('passwordMessage').innerHTML = "Please enter a password!";
                }
            } else
            {
                if (!isPasswordMessageEmpty)
                {
                    document.getElementById('password-input').classList.toggle('failed');
                    document.getElementById('passwordMessage').innerHTML = "";
                }

                ///////////check password strength

                // check if the current password meets your desired
                // strength score (strong in this case)
                var passwordScore = Enzoic.currentPasswordScore;

                if (passwordScore <
                        Enzoic.PASSWORD_STRENGTH.Strong)
                {
                    //password not strong enough!


                    //toggle fail class if never done 
                    if (isPasswordMessageEmpty)
                    {
                        document.getElementById('password-input').classList.toggle('failed');
                    }

                    //let user see its password strength!
                    var strength;
                    switch (passwordScore)
                    {
                        case Enzoic.PASSWORD_STRENGTH.Hacked:
                            strength = "very weak and in the password compromised list.";
                            break;
                        case Enzoic.PASSWORD_STRENGTH.VeryWeak:
                            strength = "very weak.";
                            break;
                        case Enzoic.PASSWORD_STRENGTH.Weak:
                            strength = "weak.";
                            break;
                        case Enzoic.PASSWORD_STRENGTH.Medium:
                            strength = " medium strength";
                            break;
                        default :
                            strength = " already strong enough!";
                            break;
                    }

                    //write message
                    document.getElementById('passwordMessage').innerHTML = "Your password is " + strength;

                    //

                }
                ///to here, user must have passed everything!
                if (document.getElementById('captcha-input').value !== "")
                {
                    document.getElementById('signUpForm').submit();
                } else
                {
                    document.getElementById('captcha-message').innerHTML = "Please enter the verification code.";
                }

            }

        }


    }

}

function ValidateEmail(mail)
{
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))
    {
        return true;
    } else
    {
        return false;
    }

}

function checkSubmitGrade_points(index, max, min)
{
    var idwarning = "warning-message-" + index;
    var idgoodgrade = "good-grade-" + index;
    var idokgrade = "ok-grade-" + index;
    var idform = "form-" + index;
    var iddelay = "delay-" + index;

    if (document.getElementById(idgoodgrade).value !== "" && document.getElementById(idokgrade).value !== "")
    {
        var goodGrade = parseFloat(document.getElementById(idgoodgrade).value);
        var okGrade = parseFloat(document.getElementById(idokgrade).value);
        var delay = parseInt(document.getElementById(iddelay).value);
        if (!isNaN(goodGrade) && !isNaN(okGrade) && !isNaN(delay))
        {
            if (okGrade <= goodGrade || goodGrade <= max || goodGrade >= min || okGrade <= max || okGrade >= min || delay >= 0)
            {
                //submit users form
                document.getElementById(idform).submit();
            } else
            {
                document.getElementById(idwarning).innerHTML = "Please check your inputs. Conditions: Ok grade <= Good Grade,  Minimun of input =" + min + ", Maximum = " + max;
            }
        } else
        {
            document.getElementById(idwarning).innerHTML = "Please fill any fields required according to the instructions."
        }
    } else
    {
        document.getElementById(idwarning).innerHTML = "Please fill in all fields.";
    }

}

function copy(text)
{
    const el = document.createElement('textarea');
    el.value = text;
    document.body.appendChild(el);
    el.select();
    document.execCommand('copy');
    document.body.removeChild(el);
}


