FROM postgres:latest

ENV POSTGRES_DB dslanchonete
ENV POSTGRES_USER dslanchonete
ENV POSTGRES_PASSWORD dslanchonete123

COPY init.sql /docker-entrypoint-initdb.d/