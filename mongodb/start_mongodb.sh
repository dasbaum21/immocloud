#!/bin/bash

CONTAINER_NAME="mongodb_container"
MONGO_PORT=27017
MONGO_DATA_DIR="$(pwd)/mongo_data"
MONGO_IMAGE="mongo:latest"

# Überprüfen, ob das Datenverzeichnis existiert, und erstellen, falls nicht
if [ ! -d "$MONGO_DATA_DIR" ]; then
    mkdir -p "$MONGO_DATA_DIR"
    echo "Datenverzeichnis erstellt: $MONGO_DATA_DIR"
fi

if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "Ein Container mit dem Namen $CONTAINER_NAME läuft bereits."
    exit 1
fi

if [ "$(docker ps -a -q -f name=$CONTAINER_NAME)" ]; then
    echo "Ein Container mit dem Namen $CONTAINER_NAME existiert bereits. Entfernen Sie ihn zuerst."
    exit 1
fi

docker run -d \
    --name $CONTAINER_NAME \
    -p $MONGO_PORT:27017 \
    -v $MONGO_DATA_DIR:/data/db \
    $MONGO_IMAGE

if [ $? -eq 0 ]; then
    echo "MongoDB-Container erfolgreich gestartet."
    echo "Container-Name: $CONTAINER_NAME"
    echo "Port: $MONGO_PORT"
    echo "Datenverzeichnis: $MONGO_DATA_DIR"
else
    echo "Fehler beim Starten des MongoDB-Containers."
    exit 1
fi
