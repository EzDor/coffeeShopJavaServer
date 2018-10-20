#!/usr/bin/env bash

RED='\033[0;31m'
GREEN='\033[0;32m'
CLIENT_ROOT_FOLDER=public

./gradlew bootRun &
cd ${CLIENT_ROOT_FOLDER}
npm i
npm start

if [ $? -eq 0 ]
         then
            echo -e "${RED}"
            echo "******************"
            echo "Compilation Error!"
            echo "******************"
            exit 1
        fi

