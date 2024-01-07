// window.onload = function () {
//     let email = $("#email").val();
//     let reset = $("#reset");
//     reset.click(function () {
//         $.ajax({
//             url: "http://localhost:8080/api/refresh-code",
//             type: "POST",
//             data: JSON.stringify({
//                 "email": email
//             }),
//             contentType: "application/json",
//             success: (response) => {
//                 reset.remove();
//                 console.log('s');
//             },
//             error: (err) => {
//                 console.log('e');
//             }
//         });
//     });
// }

function checkValidateCodeRegister() {
    let reset = $("#reset");
    let html = `
        <h6 class="text-danger font-xsss fw-500 mt-0 mb-0 lh-32" id="error-message">
        </h6>
    `;
    reset.remove();
    let code = $("#code-confirm");

    if (!validateName(code.val())) {
        $("#container-error").append(html);
        $("#error-message").text('Please enter your code.');
        code.focus();
        return false;
    }
    if (!validateCode(code.val())) {
        $("#container-error").append(html);
        $("#error-message").text('Code must be 6 digits.');
        code.val('');
        code.focus();
        return false;
    }
    return true;
}