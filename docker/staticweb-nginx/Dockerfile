FROM nginx:alpine

RUN rm /etc/nginx/conf.d/*
COPY conf.d/hello.conf /etc/nginx/conf.d/default.conf
COPY html/index.html /usr/share/nginx/html/index.html