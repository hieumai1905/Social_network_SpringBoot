window.onload = function () {
    let avatar = $("#user_current_avatar").val();
    let fullName = $("#user_current_name").val();
    let userId = $("#user_current_id").val();
    let btnComments = document.getElementsByClassName("btn-comment");
    let commentsList = document.getElementsByClassName("comments-list");
    if (btnComments != null) {
        Array.from(btnComments).forEach(function (btnComment, i) {
            btnComment.addEventListener("click", function (event) {
                event.preventDefault();
                if (
                    commentsList[i].style.display === "none" ||
                    commentsList[i].style.display === ""
                ) {
                    commentsList[i].style.display = "block";
                } else {
                    commentsList[i].style.display = "none";
                }
            });
        });
    }

    let btnCommentsReply = document.getElementsByClassName("btn-comment-reply");
    let commentsReplyList = document.getElementsByClassName("comments-reply-list");
    if (btnCommentsReply != null) {
        Array.from(btnCommentsReply).forEach(function (btnCommentsReply, i) {
            btnCommentsReply.addEventListener("click", function (event) {
                event.preventDefault();
                if (
                    commentsReplyList[i].style.display === "none" ||
                    commentsReplyList[i].style.display === ""
                ) {
                    commentsReplyList[i].style.display = "block";
                } else {
                    commentsReplyList[i].style.display = "none";
                }
            });
        });
    }

    // post
    let createAtSpans = document.getElementsByClassName("createAtSpan");
    Array.from(createAtSpans).forEach(function (span) {
        let createAtValue = span.getAttribute("data-createAt");
        let createAt = new Date(createAtValue);
        span.textContent = formatTime(createAt);
    });

    // comment
    let commentAtSpans = document.getElementsByClassName("commentAtSpan");
    Array.from(commentAtSpans).forEach(function (span) {
        let commentAtValue = span.getAttribute("data-commentAt");
        let commentAt = new Date(commentAtValue);
        span.textContent = formatTime(commentAt);
    });
    // ----

    // comment reply
    let commentReplyAtSpans = document.getElementsByClassName("commentReplyAtSpan");
    Array.from(commentReplyAtSpans).forEach(function (span) {
        let commentReplyAtValue = span.getAttribute("data-commentReplyAt");
        let commentReplyAt = new Date(commentReplyAtValue);
        span.textContent = formatTime(commentReplyAt);
    });
    // ----

    // like
    let btnLikes = document.getElementsByClassName("btn-like");
    Array.from(btnLikes).forEach(function (btnLike) {
        btnLike.addEventListener("click", function (event) {
            event.preventDefault();
            let i = btnLike.getElementsByTagName("i")[0];
            let postIndex = Array.from(document.getElementsByClassName("btn-like")).indexOf(btnLike);
            let likeCount = $(".like_count_current").eq(postIndex);
            let className = i.className;
            if (className === "feather-thumbs-up text-white bg-primary-gradiant me-1 btn-round-xs font-xss") {
                i.className = "feather-thumbs-up text-grey-900 me-1 btn-round-xs font-xss";
                if (parseInt(likeCount.text()) > 1) {
                    likeCount.text(parseInt(likeCount.text()) - 1);
                } else {
                    likeCount.text("");
                }
            } else {
                i.className = "feather-thumbs-up text-white bg-primary-gradiant me-1 btn-round-xs font-xss";
                if (likeCount.text() === "") {
                    likeCount.text(1);
                } else {
                    likeCount.text(parseInt(likeCount.text()) + 1);
                }
            }
            let postId = $(".post_current_id").eq(postIndex).val();
            likePost(postId);
        });
    });
    // -----

    // comment
    let btnAddComments = document.getElementsByClassName("btn-comment-post");
    Array.from(btnAddComments).forEach(function (btnAddComment) {
        btnAddComment.addEventListener("click", function (event) {
            event.preventDefault();
            let postIndex = Array.from(document.getElementsByClassName("btn-comment-post")).indexOf(btnAddComment);
            let commentInput = $(".comment_input").eq(postIndex).val();
            if(commentInput !== "") {
                let postId = $(".post_current_id").eq(postIndex).val();
                commentPost(postId, commentInput);

                // commentsList
                let html = ` <div class="comment">
                                    <div class="card-body p-0 d-flex">
                                        <figure class="avatar me-3"><img src="${avatar}" alt="image"
                                                                         class="shadow-sm rounded-circle w45"></figure>

                                        <div class="comment bg-light rounded-xxl p-2">
                                            <h4 class="fw-700 text-grey-900 font-xssss mt-1">${fullName}</h4>
                                            <p>${commentInput}</p>
                                        </div>
                                    </div>
                                    <div class="ms-6">
                                        <div class="card-body d-flex p-0 mt-1">
                                            <span class="commentAtSpan d-flex align-items-center fw-600 text-grey-500 lh-26 font-xssss me-2">${formatTime(new Date())}</span>
                                            <a href="#"
                                               class="emoji-bttn d-flex align-items-center fw-600 text-grey-900 text-dark lh-26 font-xssss me-2">
                                                <span>
                                             <i class="feather-thumbs-up text-grey-900 me-1 btn-round-xs font-xss"></i>
                                            </span>
                                                <span></span> &nbsp; Like
                                            </a>
                                            <a class="btn-comment-reply d-flex align-items-center fw-600 text-grey-900 text-dark lh-26 font-xssss"><i
                                                    class="feather-message-circle text-dark text-grey-900 btn-round-sm font-lg"></i>
                                                <span class="d-none-xss"></span> &nbsp; Reply
                                            </a>
                                        </div>
                                    </div>
                                    <div class="comments-reply-list" style="margin-left: 15%; display: none;">
                                    </div>
                                </div>`;
                commentsList[0].insertAdjacentHTML('beforeend', html);

                $(".comment_input").eq(postIndex).val("");
                let commentCount = $(".comment_count_current").eq(postIndex);
                if (commentCount.text() === "") {
                    commentCount.text(1);
                }else{
                    commentCount.text(parseInt(commentCount.text()) + 1);
                }
            }
        });
        // -----

    })
};

function commentPost(postId, commentInput){
    $.ajax({
        url: "http://127.0.0.1:8080/api/comments/posts",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            content: commentInput,
            postId: postId,
        }),
        success: function (response) {
            console.log(response);
        },
        error: function (error) {
            console.log(error);
        }
    });
}


function likePost(postId) {
    $.ajax({
        url: "http://127.0.0.1:8080/api/likes",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            postId: postId,
            commentId: 0,
            commentReplyId: 0
        }),
        success: function (response) {
            console.log(response);
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function formatTime(time) {
    let currentTime = new Date();
    let timeDiff = currentTime - time;
    let minutesDiff = Math.floor(timeDiff / (1000 * 60));

    if (minutesDiff < 60) {
        if (minutesDiff === 0) {
            return "Just now";
        }
        return minutesDiff + " minutes ago";
    } else if (minutesDiff < 24 * 60) {
        let hoursDiff = Math.floor(minutesDiff / 60);
        return hoursDiff + " hours ago";
    } else {
        let daysDiff = Math.floor(minutesDiff / (60 * 24));
        return daysDiff + " days ago";
    }
}

function likeComment(commentId, check) {
    console.log(check);
}