#!/bin/sh

KAFKA_HOST=$1
shift

echo "Aguardando Kafka em $KAFKA_HOST..."
until nc -z $(echo $KAFKA_HOST | cut -d: -f1) $(echo $KAFKA_HOST | cut -d: -f2); do
  sleep 2
done
echo "Kafka está disponível!"

exec "$@"