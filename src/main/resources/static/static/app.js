var stompClient = null;
var clientId = null;

function connect() {
    clientId = $('meta[name="client-id"]').attr("content");
    console.log("Client id: " + clientId);

    var socket = new SockJS('/omniscreen');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/submit-topic/' + clientId, function (message) {
            processMessage(message);
            //sendResult();
        });
    });
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

    if (message.type === "TEMPLATE") {
        processTemplateMessage(message);
    }
}

function processTemplateMessage(message) {
    console.log("Processing Template message" + message);
    //console.log("Selector: " + message.selector + ", payloadHtml: " + message.payloadHtml);
    //$(message.selector).html(message.payloadHtml);

    $("body").html(message.template);
}

$(function() {
    connect()
})