```shell
hshb wordpress OY
hsrp bitnami wordpress OY
hsrpA wordpress

helm install wordpress bitnami/wordpress --values=helm/wordpress/wordpress-values.yaml

export NODE_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].nodePort}" services wordpress)
export NODE_IP=$(kubectl get nodes --namespace default -o jsonpath="{.items[0].status.addresses[0].address}")
echo "WordPress URL: http://$NODE_IP:$NODE_PORT/"
echo "WordPress Admin URL: http://$NODE_IP:$NODE_PORT/admin"

curl 192.168.49.2:31761

# need to enable service in minikube also
minikube service wordpress

# debug
k describe pods wordpress-mariadb-0
k logs wordpress-mariadb-0
```