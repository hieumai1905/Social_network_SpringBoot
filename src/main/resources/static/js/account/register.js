function validateRegister(){
    let container = $("#container-error");
    $("#error-message").remove();
    let fullName = $("#fullName");
    let email = $("#email");
    let password = $("#password");
    let confirmPassword = $("#confirmPassword");
    let html = `
        <h6 class="text-danger font-xsss fw-500 mt-0 mb-0 lh-32" id="error-message">
        </h6>
    `;

    if(!validateName(fullName.val())){
        container.append(html);
        $("#error-message").text('Please enter your full name.');
        fullName.focus();
        return false;
    }
    if(!validateEmail(email.val())){
        container.append(html);
        $("#error-message").text('Please enter your email.');
        email.val('');
        email.focus();
        return false;
    }
    if(!validateName(password.val())){
        container.append(html);
        $("#error-message").text('Please enter your password.');
        password.focus();
        return false;
    }
    if(!validateName(confirmPassword.val())){
        container.append(html);
        $("#error-message").text('Please enter your confirm password.');
        confirmPassword.focus();
        return false;
    }
    if(!validatePassword(password.val())||!validatePassword(confirmPassword.val())){
        container.append(html);
        $("#error-message").text('Password must be at least 6 characters long');
        password.val('');
        confirmPassword.val('');
        password.focus();
        return false;
    }
    if(password.val()!==confirmPassword.val()){
        container.append(html);
        $("#error-message").text('Password and confirm password must be the same.');
        password.val('');
        confirmPassword.val('');
        password.focus();
        return false;
    }
    return true;
}