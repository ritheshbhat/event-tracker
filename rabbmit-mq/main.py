import pika
from pika.adapters.blocking_connection import BlockingChannel


# def send_message():
#     credentials = pika.PlainCredentials(username='guest', password='guest')
#
#     connection = pika.BlockingConnection(
#         pika.ConnectionParameters(host='localhost', port=5671, credentials=credentials))
#     channel = connection.channel()
#
#     channel.queue_declare(queue='hello')
#
#     channel.basic_publish(exchange='', routing_key='hello', body='sending second request.')
#     print(" [x] Sent 'Hello World!'")
#     connection.close()
#

class RabbiMQManager:
    def __init__(self, host: str, port: int, username: str, password: str):
        self.host = host
        self.port = port
        self.username = username
        self.password = password
        self.channel: BlockingChannel = None

    def setup_connection_and_channel(self) -> BlockingChannel:
        credentials = pika.PlainCredentials(username=self.username, password=self.password)

        connection = pika.BlockingConnection(
            pika.ConnectionParameters(host=self.host, port=self.port, credentials=credentials))
        channel = connection.channel()
        self.channel = channel
        return channel

    def publish_message(self, queue: str, message: str):
        self.channel.exchange_declare(exchange='logs',
                                      exchange_type='fanout')
        #
        self.channel.basic_publish(exchange='logs', routing_key='', body=message)


if __name__ == '__main__':
    r = RabbiMQManager('localhost', 5671, 'guest', 'guest')
    r.setup_connection_and_channel()
    r.publish_message("music", "testing-music-data")
