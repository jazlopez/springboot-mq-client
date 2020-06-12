#!/usr/bin/env bash

find . -iname "*.xml" -exec curl -X POST http://localhost:18888 -F file=@{} \;
