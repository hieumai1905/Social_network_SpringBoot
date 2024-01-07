let btnGetCode = $("#btn-get-code");
let btnSendCode = $("#btn-send-code");
let inputEmail = $("#email");
let inputCode = $("#code");
let html = `
        <h6 class="text-danger font-xsss fw-500 mt-0 mb-0 lh-32" id="error-message">
        </h6>
    `;
window.onload = function () {
    btnSendCode.hide();
    inputCode.hide();
    btnGetCode.click(function () {
        $("#error-message").remove();
        if (!validateName(inputEmail.val())) {
            $("#container-error").append(html);
            $("#error-message").text('Please enter your email.');
            inputEmail.val('');
            inputEmail.focus();
            return;
        }
        if (!validateEmail(inputEmail.val())) {
            $("#container-error").append(html);
            $("#error-message").text('Please enter a valid email address.');
            inputEmail.val('');
            inputEmail.focus();
            return;
        }

        $.ajax({
            url: "http://localhost:8080/api/forgot",
            type: "POST",
            data: JSON.stringify({
                "email": inputEmail.val()
            }),
            contentType: "application/json",
            success: (response) => {
                btnGetCode.hide();
                inputEmail.attr('readonly', true);
                inputCode.show();
                btnSendCode.show();
            },
            error: (err) => {
                if (err.status === 500) {
                    $("#container-error").append(html);
                    $("#error-message").text("Server error. Please try again later.");
                } else {
                    $("#container-error").append(html);
                    $("#error-message").text(err.responseText);
                }
            }
        });
    });
}

function validateCodeForgot() {
    $("#error-message").remove();
    if (!validateName(inputCode.val())) {
        $("#container-error").append(html);
        $("#error-message").text('Please enter your code.');
        inputCode.val('');
        inputCode.focus();
        return false;
    }
    if (!validateCode(inputCode.val())) {
        $("#container-error").append(html);
        $("#error-message").text('Code must be 6 digits.');
        inputCode.val('');
        inputCode.focus();
        return false;
    }
    return true;
}