#!/bin/bash

echo "Test executing a shell script: "
echo "-----------------------------------------"
echo "First command: " $1
${1}

echo "-----------------------------------------"
echo "Second command: " $2
${2}