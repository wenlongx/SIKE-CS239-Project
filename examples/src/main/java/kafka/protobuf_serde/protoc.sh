#!/bin/bash

protoc -I=. --java_out=../../ $1
