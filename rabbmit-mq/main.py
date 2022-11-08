import pika

def send_message():
    credentials = pika.PlainCredentials(username='guest', password='guest')

    connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='localhost', port=5671, credentials=credentials))
    channel = connection.channel()

    channel.queue_declare(queue='hello')

    channel.basic_publish(exchange='', routing_key='hello', body='Hello World!')
    print(" [x] Sent 'Hello World!'")
    connection.close()

if __name__ == '__main__':
    send_message()

