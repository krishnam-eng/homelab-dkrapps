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
https://
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

###### Create 
```shell
> wsk -i action create action_py helm/openwhisk/actions/pyaction.py
ok: created action action_py
```
###### Invoke 
```shell
> wsk -i action invoke action_py --result --param name Bala
{
    "greeting": "Hello Bala!"
}
```
## Trigger
```shell
> wsk trigger create testTrigger
ok: created trigger testTrigger
```

```shell
> wsk trigger fire testTrigger
ok: triggered /_/testTrigger with id 
```

## Rule 
```shell
> wsk rule create myRule testTrigger pyaction
ok: created rule myRule
```

## Activation
Now, we get Activation ID
```shell
> wsk trigger fire testTrigger --param name hi
ok: triggered /_/testTrigger with id ccba94d792144492ba94d7921454926e
```

```shell
> wsk activation list --limit 10
Datetime            Activation ID                    Kind     Start Duration   Status  Entity
2021-12-17 19:50:06 7b4739b0cb344e208739b0cb340e2013 python:3 warm  9ms        success guest/pyaction:0.0.1
2021-12-17 19:50:06 ccba94d792144492ba94d7921454926e unknown  warm  0s         success guest/testTrigger:0.0.1
2021-12-17 19:49:55 2baec3182bc6455eaec3182bc6055e22 python:3 cold  39ms       success guest/pyaction:0.0.1
2021-12-17 19:49:51 2596af5766424dbf96af576642edbf4f unknown  warm  0s         success guest/testTrigger:0.0.1
2021-12-17 12:14:02 104a502d45194c648a502d45196c644d python:3 warm  20ms       success guest/pyaction:0.0.1
2021-12-17 12:14:01 81e20798ab314597a20798ab318597a8 python:3 warm  9ms        success guest/pyaction:0.0.1
2021-12-17 12:13:57 d3b9f191690d4709b9f191690d8709b6 python:3 warm  6ms        success guest/pyaction:0.0.1
2021-12-17 12:13:54 7f43665d896f4a5583665d896f8a55d6 python:3 cold  595ms      success guest/pyaction:0.0.1
```

```shell
> wsk activation result 7b4739b0cb344e208739b0cb340e2013
{
    "greeting": "Hello hi!"
}
```