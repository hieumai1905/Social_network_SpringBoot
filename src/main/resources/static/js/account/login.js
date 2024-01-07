function checkValidateLogin(){
    let container = $("#container-error");
    $("#error-message").remove();
    let email = $("#email");
    let password = $("#password");
    let html = `
        <h6 class="text-danger font-xsss fw-500 mt-0 mb-0 lh-32" id="error-message">
        </h6>
    `;
    if(!validateName(email.val())){
        container.append(html);
        $("#error-message").text('Please enter your email.');
        email.val('');
        email.focus();
        return false;
    }
    if(!validateEmail(email.val())){
        container.append(html);
        $("#error-message").text('Email is valid.');
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
    return true;
}