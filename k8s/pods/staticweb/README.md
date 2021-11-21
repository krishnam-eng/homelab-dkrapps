## Image Distribution

### Hosted Registry with Public Repositories
```shell
docker tag staticweb-nginx:latest balakrishnam/sweb-nginx:v0.0.1
docker image ls

docker login
docker push balakrishnam/sweb-nginx:v0.0.1
```

### Private Registry using Registry Software
```shell
# Create local registry
docker run -d -p 5000:5000 --restart=always --name registry registry:2

# Create tag and push to local registry
docker tag staticweb-nodejs:latest localhost:5000/staticweb-nodejs:latest
docker image push localhost:5500/staticweb-nodejs:latest

# test
docker pull localhost:5500/staticweb-nodejs:latest
```

## K8S

### Creating Pods
#### Imperative
```shell
# Create pod from image
kubectl run swebnginx-imp --image=balakrishnam/sweb-nginx:v0.0.1

kubectl logs swebnginx-imp
```
#### Declarative
```shell
kubectl create -f pods/staticweb/staticweb-nginx.yaml
kubectl port-forward sweb-nginx 8080:8080
```