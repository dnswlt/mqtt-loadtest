# mqtt-loadtest

A (very) simple load test for RabbitMQ MQTT throughput, with a Python-based sender
and a JavaScript Eclipse Paho based receiver. 

Essentially, what I want to understand is how many messages a Browser client can handle.

## Installation

If you don't have the Python `pika` module installed, do

    pip install -r requirements.txt

## Run

Start one or more Python process that will generated and send messages at full throttle.
Open `mqtt.html` in one or more Browsers and see what happens :-)



