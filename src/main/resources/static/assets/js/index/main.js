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

window.onload = function () {
    // lat tat ca cac phan tu span co class la user-request-friend sau do lay gia tri trong do ra va su dang ham formatTime de thay doi gia tri va gan lai cho span
    let createAtSpans = document.getElementsByClassName("user-request-friend-at");
    Array.from(createAtSpans).forEach(function (span) {
            let createAtValue = span.getAttribute("data-createAt");
            let createAt = new Date(createAtValue);
            span.textContent = formatTime(createAt);
        }
    );
}
