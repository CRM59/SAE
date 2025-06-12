FROM node:23.11.0-alpine

RUN npm install -g http-server

COPY package*.json .

RUN npm i

COPY . .

RUN npm run build

EXPOSE 8080

CMD ["http-server", "dist"]