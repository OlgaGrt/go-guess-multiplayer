// Include the Stomp.js library
// You can download it from https://github.com/jmesnil/stomp-websocket
// or use a CDN like https://cdnjs.com/libraries/stomp.js
// npm install stompjs
const Stomp = require('stompjs');
const WebSocket = require('ws');

// Set the WebSocket endpoint URL
const socketEndpoint = 'ws://localhost:8080/portfolio';

// Create a WebSocket client
const socket = new WebSocket(socketEndpoint);
const roomUUID = 'e0066214-83ef-4a81-a4da-095894f920d9';

// Create a Stomp client over the WebSocket
const stompClient = Stomp.over(socket);

// Connect to the WebSocket
stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);

    //Subscribe to a destination (e.g., a specific topic)
    const destination = '/topic/room/' + roomUUID;
    stompClient.subscribe(destination, function (message) {
       console.log('Received message:', message.body);
    });

    // Send a message to the destination
    const message_join = 'join';
    stompClient.send('/app/room/' + roomUUID, {}, message_join);

    const message_choose = 'choose';
    stompClient.send('/app/room/' + roomUUID, {}, message_choose);
});

// Handle disconnection
stompClient.onDisconnect = function () {
    console.log('Disconnected');
};

// Handle errors
stompClient.onerror = function (error) {
    console.error('STOMP Error:', error);
};