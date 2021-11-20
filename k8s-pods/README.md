```shell
# Create local registry
docker run -d -p 5000:5000 --restart=always --name registry registry:2

# Create tag and push to local registry
docker tag staticweb-nodejs:latest localhost:5000/staticweb-nodejs:latest
docker image push localhost:5500/staticweb-nodejs:latest

# test
docker pull localhost:5500/staticweb-nodejs:latest

# Create pod from image 
kubectl run swebnode --image=localhost:5500/staticweb-nodejs:latest
```