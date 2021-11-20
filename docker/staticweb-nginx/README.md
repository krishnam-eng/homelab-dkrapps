```shell
docker image build -t staticweb-nginx:latest static-web-nginx 
```

```shell
CID = $(docker container run -d -p 8080:80 staticweb-nginx:latest)
docker container exec -it $CID sh
docker container logs $CID
```

```shell
curl localhost:8080 | grep Server 

  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  7792    0  7792    0     0   658k      0 --:--:-- --:--:-- --:--:-- 1521k
    <p><span>Server&nbsp;address:</span> <span>172.17.0.2:80</span></p>
    <p><span>Server&nbsp;name:</span> <span>a979acd48d12</span></p>
```