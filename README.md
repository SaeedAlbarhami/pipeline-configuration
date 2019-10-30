## Pipeline-Configuration

Start by deploying a Jenkins master instance onto a Kubernetes cluster which enable Jenkins to scale on the cluster by provisioning dynamic agents to accommodate its current workloads. The plugin will create a Kubernetes Pod for each build by launching an agent based on a specific Docker image. When the build completes, Jenkins will remove the Pod to save resources.


## Workflow Steps:
1.	Push code to a source control
2.	hook to Jenkins master
3.	Launch job at Jenkins slave 
4.	Checkout source code 
5.	Build, Run Tests(Unit, Integration etc)
6.	Run code analysis (sonarqube)
7.	Push report back to a review(requires developer edition)
8.	Create artifacts
9.	Create a container based on commit id
10.	Push a container
11.	Checkout helm chart 
12.	Upgrade helm chart release based on container registry
13.	List history (last 10) deployment & show rollback details

## Repository Structure
vars: Contains a shared golbal library for common functions like cloning the code from repository, buiding and pushing an image, upgrade chart release etc.

helm: It allows describing the application structure through convenient helm-charts and managing it with simple commands
