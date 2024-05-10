// Hàm tạo post
function createPost() {
    let postData = {
        postContent: $("#content").val(),
        access: $("#access").val(),
        postType: "POST",
        userTagIds: [],
    };

    // Tạo một FormData object để chứa dữ liệu cần gửi
    let formData = new FormData();

    for (let key in postData) {
        formData.append(key, postData[key]);
    }

    for (let i = 0; i < files.length; i++) {
        formData.append("files", files[i]);
    }

    // Thực hiện gửi yêu cầu POST đến API bằng AJAX
    $.ajax({
        url: "http://localhost:8080/api/posts",
        method: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            // Xử lý dữ liệu trả về khi gọi API thành công
            console.log("API Response:", data);
            clearForm();
            $('#exampleModal').modal('hide');
        },
        error: function (error) {
            // Xử lý lỗi khi gọi API thất bại
            console.error("There was a problem with the AJAX request:", error);
        },
    });
}

function clearForm() {
    $('#content').val('');
    $('#access').val('PUBLIC');
    files = null;
    $('#file-input').val('');
    $('#file-list').empty();
}

function getFriends() {
    // Dữ liệu bạn muốn gửi đi
    let type = "FRIEND";

    // Thực hiện gọi API GET bằng jQuery AJAX
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/relations",
        data: {
            type: type,
        },
        success: function (response) {
            console.log("API Response:", response);
            displayFriendsView(response);
        },
        error: function (xhr, status, error) {
            // Xử lý khi gọi API thất bại
            console.error("There was a problem with the AJAX request:", error);
        },
    });
}

function displayFriendsView(response) {
    const listFriends = $("#list-friends");
    listFriends.empty();

    response.forEach((user) => {
        const cardBody = $("<div>").addClass("card-body d-flex pt-4 ps-4 pe-4 pb-0 border-top-xs bor-0");

        const figure = $("<figure>").addClass("avatar me-3");
        const img = $("<img>").attr({
            src: 'images/user-7.png',
            alt: "image"
        }).addClass("shadow-sm rounded-circle w45");
        figure.append(img);

        const h4 = $("<h4>").addClass("fw-700 text-grey-900 font-xssss mt-2").text(user.fullName);

        cardBody.append(figure, h4);
        listFriends.append(cardBody);
    });
}


// Lắng nghe sự kiện click của nút có id là createPost
$(document).ready(function () {
    $("#createPost").on("click", function () {
        // Gọi hàm createPost khi nút được click
        createPost();
    });
    $("#tag-friends-icon").on("click", function () {
        getFriends();
    });
});
