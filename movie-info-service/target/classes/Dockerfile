version: "3"
services:
  app:
    image: quiz-server:latest
    container_name: 'quiz-server'
    build:
      context: ./
      dockerfile: Dockerfile
    ports:
    - '8080:8080'
  prometheus:
    image: prom/prometheus
    container_name: 'prometheus'
    volumes:
    - ./monitor/:/etc/prometheus/
    ports:
    - '9090:9090'
  grafana:
    image: grafana/grafana
    container_name: 'grafana'
    ports:
    - '3000:3000'
    depends_on:
    - prometheus