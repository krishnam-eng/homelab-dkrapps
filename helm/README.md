
# Elasticsearch in K8S Cluster
```shell
# Find repo
helm search hub elasticsearch

# check the repo details from search result
helm repo add elastic https://helm.elastic.co

# Install / Release version 7.15 es chart
helm install elasticsearch --version 7.15 elastic/elasticsearch

# Check the pod status
kubectl get pods --namespace=default -l app=elasticsearch-master

# Test
helm --namespace=default test elasticsearch

# delete all resources associated with the release 
helm uninstall elasticsearch
```

In order to properly support the required persistent volume claims for the Elasticsearch StatefulSet, the default-storageclass and storage-provisioner minikube addons must be enabled.
```shell
minikube addons enable default-storageclass
minikube addons enable storage-provisioner
```

You can now setup a port forward to query Elasticsearch API:
```shell
kubectl port-forward svc/elasticsearch-master 9200
curl localhost:9200/_cat/indices
```