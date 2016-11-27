var stompClient = null;
var clientId = null;

function connect() {
    clientId = $('meta[name="client-id"]').attr("content");
    console.log("Client id: " + clientId);

    var socket = new SockJS('/omniscreen');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/widgets', function (message) {
            processMessage(message);
            sendResult();
        });
    });
}


function sendResult() {
    stompClient.send("/omni/result",
        {},
        JSON.stringify({'clientId': clientId,
            'body' : $("body#main").html()
        }));
}


function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function processMessage(messageText) {
    console.log("Message body: " + messageText.body);
    var message = JSON.parse(messageText.body);

    if (message.type == "PAYLOAD") {
        processPayloadMessage(message);
    }
}

function processPayloadMessage(message) {
    console.log("Payload definition detected");
    console.log("Selector: " + message.selector + ", payloadHtml: " + message.payloadHtml);
    $(message.selector).html(message.payloadHtml);
}

$(function() {
    connect()
})