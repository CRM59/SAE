FROM postgres:17

ENV POSTGRES_DB=postgres
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

VOLUME /var/lib/postgresql/data

EXPOSE 5432

COPY init.sql /docker-entrypoint-initdb.d/