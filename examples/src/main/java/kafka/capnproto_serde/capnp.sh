#!/bin/bash

mkdir -p generated
capnp compile -ojava:./generated $1
