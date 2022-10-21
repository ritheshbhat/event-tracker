import pika

def send_message():
    credentials = pika.PlainCredentials(username='guest', password='guest')

    connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost', port=5672, credentials= credentials))
    channel = connection.channel()
    channel.queue_declare(queue='sports')
    channel.basic_publish(exchange='sports',
                          routing_key='',
                          body='Hello World!')
    print(" [x] Sent 'Hello World!'")
    connection.close()
# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    send_message()
# #
# # See PyCharm help at https://www.jetbrains.com/help/pycharm/

# example_publisher.py
