let conversations = null;
let conversationData = $("#conversation-data");
let blockChat = $("#modal-chat");
getConversations()
    .then(async function() {
        await showConversations();
    })
    .catch(function(error) {
        console.error("There was a problem:", error);
    });

async function showConversations() {
    let html = '';
    for (const conversation of conversations) {
        let conversationId = conversation.conversationId;
        let conversationName = '';
        let conversationAvatar = '';
        if (conversation.type === "GROUP") {
            conversationName = conversation.name;
            conversationAvatar = conversation.avatar;
        } else {
            let userCurrentId = conversation.userCurrentId;
            for (let i = 0; i < conversation.participants.length; i++) {
                if (conversation.participants[i].participantId.userId !== userCurrentId) {
                    conversationName = conversation.participants[i].nickname;
                    try {
                        const response = await getUserInfo(conversation.participants[i].participantId.userId);
                        conversationAvatar = response.avatar;
                    } catch (error) {
                        console.error("There was a problem:", error);
                    }
                    break;
                }
            }
        }
        html += `
            <li class="bg-transparent list-group-item no-icon pe-0 ps-0 pt-2 pb-2 border-0 d-flex align-items-center">
                <figure class="avatar float-left mb-0 me-2">
                    <img src="${conversationAvatar}" alt="image" class="w35">
                </figure>
                <h3 class="fw-700 mb-0 mt-0" onclick="showChat('${conversationId}')" style="cursor: grabbing;">
                    <p class="font-xssss text-grey-600 d-block text-dark model-popup-chat">${conversationName}</p>
                </h3>
                <span class="badge badge-primary text-white badge-pill fw-500 mt-0"></span>
            </li>
        `;
    }
    conversationData.html(html);
}

function showChat(conversationId){
// hien thi modal
    blockChat.modal('show');
}

function getConversations() {
    return new Promise(function(resolve, reject) {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/conversations",
            success: function(response) {
                conversations = response;
                resolve();
            },
            error: function(xhr, status, error) {
                console.error("There was a problem with the AJAX request:", error);
                reject(error);
            },
        });
    });
}

function getUserInfo(userId) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/users?id=" + userId,
            success: function(response) {
                resolve(response);
            },
            error: function(xhr, status, error) {
                console.error("There was a problem with the AJAX request:", error);
                reject(error);
            },
        });
    });
}