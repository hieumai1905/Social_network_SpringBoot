window.onload = function () {
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
    Array.from(createAtSpans).forEach(function(span) {
        let createAtValue = span.getAttribute("data-createAt");
        let createAt = new Date(createAtValue);
        span.textContent = formatTime(createAt);
    });


    // comment
    let commentAtSpans = document.getElementsByClassName("commentAtSpan");
    Array.from(commentAtSpans).forEach(function(span) {
        let commentAtValue = span.getAttribute("data-commentAt");
        let commentAt = new Date(commentAtValue);
        span.textContent = formatTime(commentAt);
    });
    // ----

    // comment reply
    let commentReplyAtSpans = document.getElementsByClassName("commentReplyAtSpan");
    Array.from(commentReplyAtSpans).forEach(function(span) {
        let commentReplyAtValue = span.getAttribute("data-commentReplyAt");
        let commentReplyAt = new Date(commentReplyAtValue);
        span.textContent = formatTime(commentReplyAt);
    });
    // ----
};

function formatTime(time) {
    let currentTime = new Date();
    let timeDiff = currentTime - time;
    let minutesDiff = Math.floor(timeDiff / (1000 * 60));

    if (minutesDiff < 60) {
        return minutesDiff + " minutes ago";
    } else if (minutesDiff < 24 * 60) {
        let hoursDiff = Math.floor(minutesDiff / 60);
        return hoursDiff + " hours ago";
    } else {
        let daysDiff = Math.floor(minutesDiff / (60 * 24));
        return daysDiff + " days ago";
    }
}