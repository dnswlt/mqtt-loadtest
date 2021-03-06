import pika
import json
from uuid import uuid4
from os import urandom, getpid
from base64 import b64encode
from socket import gethostname
import time
import random

credentials = pika.PlainCredentials('perf', 'ferp777')
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost', 5672, '/', credentials))
channel = connection.channel()

channel.exchange_declare(exchange='mqtt', exchange_type='topic')

sender_id = "%s:%d" % (gethostname(), getpid())
cnt = 0
clob = b64encode(urandom(768)).decode('ascii')
num_mqtt_topics = 10
while True:
    msg = json.dumps({'id': str(uuid4()), 'seq': cnt, 'sender': sender_id, 'content': clob})
    channel.basic_publish(exchange='mqtt', routing_key='perf-%d' % random.randint(1, num_mqtt_topics), body=msg)
    # print("Sent message number %d" % cnt)
    cnt += 1
    # time.sleep(0.1)
    

