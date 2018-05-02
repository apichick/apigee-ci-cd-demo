# Apigee CI/CD Demo 

CI/CD demo for building, deploying and testing API proxies

# Setting up Jenkins

1. Clone this repository;

        $ git clone git@github.com:apichick/apigee-ci-cd-demo.git

2. Change to the apigee-ci-cd-demo directory.

        $ cd apigee-ci-cd-demo

3. Create a folder called secrets inside the apigee-ci-cd-demo folder.

        $ mkdir secrets

4. Create three files below containing your Apigee username and password, an Apigee organization and the private key that you would use to authenticate against your public repo.

        $ echo "APIGEE-ORGANIZATION" > secrets/apigee-organization
        $ echo "APIGEE-USERNAME" > secrets/apigee-username
        $ echo "APIGEE-PASSWORD" > secrets/apigee-password
        $ echo "GIT-PRIVATE-KEY" > secrets/git-private-key

5. Run the following command:

        $ docker compose-up

6. Load the jenkins home page (http://localhost:8080) in your browser and double-check that everything works properly.

# Configuring the job

1. Create a fork for the following repository in github:

        git@github.com:apichick/book-api-v1.git

2. Add the public key corresponding to the private key that you used in step 4 of the previous section as a deploy key to your fork.

3. Clone the fork.

5. Create a branch for each environment available in the Apigee organization that you are using, except for the environment that you are using for development. If you are using a trial organization, the environment named "test" is your development environment. Once the branch is created, push it to the remote.

        $ git checkout -b <environment-name>
        $ git push origin <environment-name>


9. In Jenkins click on the job named job-template and then trigger a build supplying as job name the value book-api-v1 and as repository URL the one of your fork. The job-template job is a seed job used to create a multibranch pipeline for the API proxies that we would like to build.

10. Every time a branch is created or updated in the remote a build of the job created in the previous step will be triggered. If the branch name matches "feature/*" the proxy will be deployed to the development environment and the name will be book-api-jenkins-v1. If the name of the branch is "master", the proxy will be deployed too the development environment too but the name will be book-api-v1. For the other branches, the proxy will be deployed to the environment named as the branch and the name will also be book-api-v1.    

