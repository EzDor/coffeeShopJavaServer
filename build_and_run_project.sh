#!/usr/bin/env bash

RED='\033[0;31m'
GREEN='\033[0;32m'
CLIENT_ROOT_FOLDER=public

./gradlew bootRun &
npm i --prefix ${CLIENT_ROOT_FOLDER}
npm start --prefix ${CLIENT_ROOT_FOLDER}

if [ $? -eq 0 ]
         then
            echo -e "${RED}"
            echo "******************"
            echo "Compilation Error!"
            echo "******************"
            exit 1
        fi

