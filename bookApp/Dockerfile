FROM node:17-alpine

WORKDIR /usr/src

COPY package*.json ./
ADD package.json /usr/src/app/package.json
RUN npm install

COPY . .

CMD["npm","start"]