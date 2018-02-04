# mqtt-loadtest

A (very) simple load test for RabbitMQ MQTT throughput, with a Python-based sender
and a JavaScript Eclipse Paho based receiver. 

Essentially, what I want to understand is how many messages a Browser client can handle.

## Installation

If you don't have the Python `pika` module installed, do

    pip install -r requirements.txt

## RabbitMQ Configuration

In `%appdata%\RabbitMQ\rabbitmq.conf`, add the following line (create file if it doesn't exist)

    mqtt.exchange = mqtt

Restart RabbitMQ for the config to take effect, and add the `perf` user. Open the RabbitMq admin shell and do:

    rabbitmq-service.bat stop
    rabbitmq-service.bat start
    rabbitmqctl.bat add_user perf ferp777
    rabbitmqctl set_permissions -p / perf ".*" ".*" ".*"
    
## Run

Start one or more Python processes that will generated and send messages at full throttle.

    python ./mqttperf.py

Open `mqtt.html` in one or more Browsers and see what happens :-)



