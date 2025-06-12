# Use the node image from official Docker Hub
FROM node:23.11.0-alpine

RUN npm install -g http-server

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

RUN npm run build

EXPOSE 8080
CMD [ "http-server", "dist" ]
