var stompClient = null;

function connect() {
    var socket = new SockJS('/omniscreen');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/widgets', function (message) {
            processMessage(message);
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

    if (message.type == "PAYLOAD") {
        processPayloadMessage(message);
    }
}

function processPayloadMessage(message) {
    console.log("Payload definition detected");
    console.log("Selector: " + message.selector + ", payloadHtml: " + message.payloadHtml);
    $(message.selector).html(message.payloadHtml);
}


$(
    connect()
);