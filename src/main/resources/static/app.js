var stompClient = null;

function connect() {
    var socket = new SockJS('/omniscreen');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/widgets', function (widget) {
            addWidget(widget.body);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function addWidget(html) {
    $(document.body).html(html);
}

$(
    connect()
);

