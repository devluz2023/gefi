FROM node:14

WORKDIR /gefiServer

COPY /gefiServer/package.json .

RUN npm install

COPY /gefiServer .

EXPOSE 3000

CMD [ "npm", "start" ]