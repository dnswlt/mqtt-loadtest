import pika
import json
from uuid import uuid4
from os import urandom, getpid
from base64 import b64encode
from socket import gethostname
import time

connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

channel.exchange_declare(exchange='mqtt', exchange_type='topic')

sender_id = "%s:%d" % (gethostname(), getpid())
cnt = 0
clob = b64encode(urandom(512)).decode('ascii')
while True:
    msg = json.dumps({'id': str(uuid4()), 'seq': cnt, 'sender': sender_id, 'content': clob})
    channel.basic_publish(exchange='mqtt', routing_key='perf', body=msg)
    # print("Sent message number %d" % cnt)
    cnt += 1
    # time.sleep(0.02)
    

