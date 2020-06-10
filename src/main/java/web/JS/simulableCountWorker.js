let webSocket = prepareSocket();

onmessage = function getEvents() {
    if(webSocket.readyState === webSocket.OPEN) wsSendMessage();
    setTimeout(getEvents,100);
};


function prepareSocket() {
    let webSocket = new WebSocket("ws://localhost:8080/RestaurantSimulator_war_exploded/simulableCounterSocketEndpoint");
    webSocket.onopen = function (message) {
        wsOpen(message);
    };
    webSocket.onmessage = function (message) {
        wsGetMessage(message);
    };
    webSocket.onclose = function (message) {
        wsClose(message);
    };
    webSocket.onerror = function (message) {
        wsError(message);
    };
    return webSocket;

}

function wsOpen(){
    console.log("Connected to socket...");
}
function wsSendMessage(){
    webSocket.send("getEvents");
}
function wsGetMessage(message){
    postMessage(message.data);
}
function wsClose(){
    console.log("Disconnected...");
}

function wsError(message){
    console.log(message.data);
}