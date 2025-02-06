FROM ubuntu:latest
LABEL authors="eatalay"

ENTRYPOINT ["top", "-b"]