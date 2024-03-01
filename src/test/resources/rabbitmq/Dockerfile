FROM rabbitmq:management

RUN rabbitmq-plugins enable --offline rabbitmq_mqtt

COPY ./cert /etc/rabbitmq/cert
RUN chown -R rabbitmq:rabbitmq /etc/rabbitmq/cert
COPY ./rabbitmq.conf /etc/rabbitmq
