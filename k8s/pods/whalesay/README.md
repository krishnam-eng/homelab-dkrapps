## K8S

### Creating Pods

#### Declarative
```shell
kubectl create -f k8s/pods/whalesay/whalesay-nginx.yaml
kubectl port-forward whalesay 8080:80
```