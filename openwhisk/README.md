## Open Whisk Setup using k8s & helm

### Deploying Released Charts from Helm Repository

The OpenWhisk project maintains a Helm repository at https://openwhisk.apache.org/charts. You may install officially released versions of OpenWhisk from this repository:
```shell
helm repo add openwhisk https://openwhisk.apache.org/charts
helm repo update
helm install owdev openwhisk/openwhisk -n openwhisk --create-namespace -f helm/openwhisk/owcluster.yaml
```

more details:  https://github.com/apache/openwhisk-deploy-kube/blob/master/README.md#prerequisites-kubernetes-and-helm

#### Chart Release Output
```shell
NAME: owdev
LAST DEPLOYED: Mon Dec 13 20:56:33 2021
NAMESPACE: openwhisk
STATUS: deployed
REVISION: 1
NOTES:
Apache OpenWhisk
Copyright 2016-2020 The Apache Software Foundation

This product includes software developed at
The Apache Software Foundation (http://www.apache.org/).

To configure your wsk cli to connect to it, set the apihost property
using the command below:

  $ wsk property set --apihost localhost:31001

Your release is named owdev.

To learn more about the release, try:

  $ helm status owdev
  $ helm get owdev

Once the 'owdev-install-packages' Pod is in the Completed state, your OpenWhisk deployment is ready to be used.

Once the deployment is ready, you can verify it using: 

  $ helm test owdev --cleanup
```

#### Status Check

```shell
helm status owdev -n openwhisk
kubectl get pods -n openwhisk --watch
```

> Error: install packages pods were failing and after a painful google search. 
> Found out that , openwhisk helm chart stopped supporting minikube. 
> After using Docker-Desktop-for-mac for starting k8s cluster. this problem solved. Thank god. 

```shell
wsk property set --apihost localhost:31001

# -i to ignore cert warnings
wsk -i property get
wsk -i package list 

```
## Action 

#### Create 
```shell
wsk -i action create action_py helm/openwhisk/actions/pyaction.py
ok: created action action_py
```
#### Invoke 
```shell
wsk -i action invoke action_py --result --param name Bala
{
    "greeting": "Hello Bala!"
}
```