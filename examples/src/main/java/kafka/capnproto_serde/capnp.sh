#!/bin/bash

mkdir generated
capnp compile -ojava:./generated $1
