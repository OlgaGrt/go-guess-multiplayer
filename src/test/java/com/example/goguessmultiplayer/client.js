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

// Create a Stomp client over the WebSocket
const stompClient = Stomp.over(socket);

// Connect to the WebSocket
stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);

    //Subscribe to a destination (e.g., a specific topic)
    const destination = '/topic/greeting';
    stompClient.subscribe(destination, function (message) {
       console.log('Received message:', message.body);
    });

    // Send a message to the destination
    const message = 'Hello, STOMP!';
    stompClient.send('/app/greeting', {}, message);
});

// Handle disconnection
stompClient.onDisconnect = function () {
    console.log('Disconnected');
};

// Handle errors
stompClient.onerror = function (error) {
    console.error('STOMP Error:', error);
};